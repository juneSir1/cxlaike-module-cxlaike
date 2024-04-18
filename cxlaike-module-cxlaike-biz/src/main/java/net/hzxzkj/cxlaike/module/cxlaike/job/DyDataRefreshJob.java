package net.hzxzkj.cxlaike.module.cxlaike.job;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.extern.slf4j.Slf4j;
import net.hzxzkj.cxlaike.framework.common.util.date.LocalDateTimeUtils;
import net.hzxzkj.cxlaike.framework.quartz.core.handler.JobHandler;
import net.hzxzkj.cxlaike.framework.tenant.core.aop.TenantIgnore;
import net.hzxzkj.cxlaike.framework.tenant.core.util.TenantUtils;
import net.hzxzkj.cxlaike.module.cxlaike.convert.videodydatastatistics.VideoDyDataStatisticsConvert;
import net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.accountdydatastatistics.AccountDyDataStatisticsDO;
import net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.matrixtaskorder.MatrixTaskOrderDO;
import net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.memberdybind.MemberDyBindDO;
import net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.memberdytoken.MemberDyTokenDO;
import net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.taskdydatastatistics.TaskDyDataStatisticsDO;
import net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.taskorder.TaskOrderDO;
import net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.taskordergather.TaskOrderGatherDO;
import net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.videodydatastatistics.VideoDyDataStatisticsDO;
import net.hzxzkj.cxlaike.module.cxlaike.dal.mysql.taskordergather.TaskOrderGatherMapper;
import net.hzxzkj.cxlaike.module.cxlaike.enums.PlatformType;
import net.hzxzkj.cxlaike.module.cxlaike.enums.douyin.DyUserTypeEnum;
import net.hzxzkj.cxlaike.module.cxlaike.enums.taskorder.VideoStatusEnum;
import net.hzxzkj.cxlaike.module.cxlaike.service.accountdydatastatistics.AccountDyDataStatisticsService;
import net.hzxzkj.cxlaike.module.cxlaike.service.matrixtaskorder.MatrixTaskOrderService;
import net.hzxzkj.cxlaike.module.cxlaike.service.memberdybind.MemberDyBindService;
import net.hzxzkj.cxlaike.module.cxlaike.service.memberdytoken.MemberDyTokenService;
import net.hzxzkj.cxlaike.module.cxlaike.service.taskdydatastatistics.TaskDyDataStatisticsService;
import net.hzxzkj.cxlaike.module.cxlaike.service.taskorder.TaskOrderService;
import net.hzxzkj.cxlaike.module.cxlaike.service.videodydatastatistics.VideoDyDataStatisticsService;
import net.hzxzkj.cxlaike.module.cxlaike.utils.douyin.DouYinApiUtils;
import net.hzxzkj.cxlaike.module.cxlaike.utils.douyin.vo.*;
import net.hzxzkj.cxlaike.module.member.enums.DyAccreditStatusEnum;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Component
//@TenantJob // 标记多租户
@Slf4j
public class DyDataRefreshJob implements JobHandler {


    @Resource
    private DouYinApiUtils douYinApiUtils;
    @Resource
    private TaskOrderService taskOrderService;
    @Resource
    private MemberDyBindService memberDyBindService;
    @Resource
    private MemberDyTokenService memberDyTokenService;
    @Resource
    private VideoDyDataStatisticsService videoDyDataStatisticsService;
    @Resource
    private MatrixTaskOrderService matrixTaskOrderService;
    @Resource
    private AccountDyDataStatisticsService accountDyDataStatisticsService;
    @Resource
    private TaskOrderGatherMapper taskOrderGatherMapper;
    @Resource
    private TaskDyDataStatisticsService taskDyDataStatisticsService;

    @Override
    @TenantIgnore
    public String execute(String param) {
        log.info("【抖音刷新数据】开始++++++++++++++++++");

        //账号数据刷新
        List<MemberDyBindDO> memberDyBindDOS = this.refreshAccountData();

        //订单视频数据
        this.refreshVideoData(memberDyBindDOS);

        //任务数据刷新
        this.refreshTaskData();

        log.info("【抖音刷新数据】结束++++++++++++++++++");
        return "";
    }

    private TaskDyDataStatisticsDO buildTaskData(List<VideoDyDataStatisticsDO> dataStatisticsDOS, Long taskId) {
        TaskDyDataStatisticsDO YesterdayStatisticsDO = taskDyDataStatisticsService.getYesterdayDataByTaskId(taskId, LocalDateTimeUtils.getMinusDaysTime(1));
        if (YesterdayStatisticsDO == null) {
            YesterdayStatisticsDO = new TaskDyDataStatisticsDO();
            YesterdayStatisticsDO.setTaskId(taskId);
            YesterdayStatisticsDO.setTaskType(dataStatisticsDOS.get(0).getDyType());
            YesterdayStatisticsDO.setStatisticsTime(LocalDateTimeUtils.getMinusDaysTime(1));
        }
        return YesterdayStatisticsDO;
    }

    private void refreshTaskData() {
        //前一天所有视频数据统计
        List<VideoDyDataStatisticsDO> videoStatisticsDOS = videoDyDataStatisticsService.getBeforeData(LocalDateTimeUtils.getMinusDaysTime(1), PlatformType.TIKTOK.getType());

        Map<Long, List<VideoDyDataStatisticsDO>> taskVideoResp = videoStatisticsDOS.stream().collect(Collectors.groupingBy(VideoDyDataStatisticsDO::getTaskId));
        for (Map.Entry<Long, List<VideoDyDataStatisticsDO>> entry : taskVideoResp.entrySet()) {
            Long taskId = entry.getKey();
            List<VideoDyDataStatisticsDO> dataStatisticsDOS = entry.getValue();

            Long publishCount = taskOrderGatherMapper.selectCount(new LambdaQueryWrapper<TaskOrderGatherDO>()
                    .eq(TaskOrderGatherDO::getTaskId, taskId));
            TaskDyDataStatisticsDO beforeStatisticsDO = taskDyDataStatisticsService.getBeforeDataByTaskId(taskId, LocalDateTimeUtils.getMinusDaysTime(2));
            TaskDyDataStatisticsDO YesterdayStatisticsDO = this.buildTaskData(dataStatisticsDOS, taskId);
            int playCount = dataStatisticsDOS.stream().mapToInt(VideoDyDataStatisticsDO::getPlayCount).sum();
            YesterdayStatisticsDO.setPlayCount(playCount);
            int diggCount = dataStatisticsDOS.stream().mapToInt(VideoDyDataStatisticsDO::getDiggCount).sum();
            YesterdayStatisticsDO.setDiggCount(diggCount);
            int commentCount = dataStatisticsDOS.stream().mapToInt(VideoDyDataStatisticsDO::getCommentCount).sum();
            YesterdayStatisticsDO.setCommentCount(commentCount);
            int shareCount = dataStatisticsDOS.stream().mapToInt(VideoDyDataStatisticsDO::getShareCount).sum();
            YesterdayStatisticsDO.setShareCount(shareCount);
            YesterdayStatisticsDO.setPublishCount(publishCount.intValue());
            YesterdayStatisticsDO.setPublishCountIncrement(publishCount.intValue());
            YesterdayStatisticsDO.setPlayCountIncrement(playCount);
            YesterdayStatisticsDO.setDiggCountIncrement(diggCount);
            YesterdayStatisticsDO.setCommentCountIncrement(commentCount);
            YesterdayStatisticsDO.setShareCountIncrement(shareCount);
            if (beforeStatisticsDO != null) {
                YesterdayStatisticsDO.setPublishCountIncrement(publishCount.intValue() - beforeStatisticsDO.getPublishCount() < 0 ? 0 : publishCount.intValue() - beforeStatisticsDO.getPublishCount());
                YesterdayStatisticsDO.setPlayCountIncrement(playCount - beforeStatisticsDO.getPlayCount() < 0 ? 0 : playCount - beforeStatisticsDO.getPlayCount());
                YesterdayStatisticsDO.setDiggCountIncrement(diggCount - beforeStatisticsDO.getDiggCount() < 0 ? 0 : diggCount - beforeStatisticsDO.getDiggCount());
                YesterdayStatisticsDO.setCommentCountIncrement(commentCount - beforeStatisticsDO.getCommentCount() < 0 ? 0 : commentCount - beforeStatisticsDO.getCommentCount());
                YesterdayStatisticsDO.setShareCountIncrement(shareCount - beforeStatisticsDO.getShareCount() < 0 ? 0 : shareCount - beforeStatisticsDO.getShareCount());
            }

            if (dataStatisticsDOS.get(0).getTenantId() == null) {
                taskDyDataStatisticsService.createOrUpdateEntity(YesterdayStatisticsDO);
            } else {
                TenantUtils.execute(dataStatisticsDOS.get(0).getTenantId(), () -> {
                    taskDyDataStatisticsService.createOrUpdateEntity(YesterdayStatisticsDO);
                });
            }


        }
    }

    private List<MemberDyBindDO> refreshAccountData() {

        List<MemberDyBindDO> refreshAccount = new ArrayList<>();

        List<MemberDyBindDO> drMemberDyBindDOS = memberDyBindService.getList(PlatformType.TIKTOK.getType());
        for (MemberDyBindDO memberDyBindDO : drMemberDyBindDOS) {
            MemberDyTokenDO memberDyTokenDO = memberDyTokenService.getById(memberDyBindDO.getId());
            if (memberDyTokenDO == null) {
                log.warn("【抖音刷新数据失败】,抖音token不存在:id:{}", memberDyBindDO.getId());
                continue;
            }
            UserFanDataListRespDTO userFanData = douYinApiUtils.getUserFanDatas(memberDyBindDO.getDyOpenId(), memberDyTokenDO.getDyAccessToken());
//            log.info("========dyOpenID:{}=======粉丝fans:{}",memberDyBindDO.getDyOpenId(),userFanData!=null?userFanData.toString():memberDyBindDO.getDyOpenId());

            //用户token是否过期
            if (userFanData != null && userFanData.getData().getError_code() == 2190008) {//token过期
                log.warn("【抖音】,获取粉丝数据错误,错误信息:{},openId:{}", userFanData.getExtra().getDescription(), memberDyBindDO.getDyOpenId());
                memberDyTokenService.updateDyAccreditStatus(memberDyBindDO.getId(), DyAccreditStatusEnum.UN_ACCREDIT);
                continue;
            }
            List<FanDataListRespDTO> fanDataListRespDTOS = new ArrayList<>();
            if (userFanData != null && userFanData.getExtra().getError_code() == 0) {
                fanDataListRespDTOS = userFanData.getData().getResult();
            }
//
            //发布数
            Long publishCount = taskOrderGatherMapper.selectCount(new LambdaQueryWrapper<TaskOrderGatherDO>()
                    .eq(TaskOrderGatherDO::getAccountBindId, memberDyBindDO.getId())
                    .between(TaskOrderGatherDO::getCreateTime,LocalDateTimeUtils.getTodayStart().minusDays(1),LocalDateTimeUtils.getTodayStart()));

            //AccountDyDataStatisticsDO beforeStatisticsDO = accountDyDataStatisticsService.getYesterdayDataByOrderId(memberDyBindDO.getId(), memberDyBindDO.getUserType(), null);
            AccountDyDataStatisticsDO yesterdayStatisticsDO = this.buildNewStatisticsDO(memberDyBindDO);
            Integer totalFans = 0;
            if (CollectionUtil.isNotEmpty(fanDataListRespDTOS)) {
                totalFans = fanDataListRespDTOS.get(0).getTotal_fans();
            }
            yesterdayStatisticsDO.setFansCount(totalFans);
            yesterdayStatisticsDO.setPublishCount(publishCount.intValue());
            yesterdayStatisticsDO.setPublishCountIncrement(publishCount.intValue());

//            if (beforeStatisticsDO != null) {
//                yesterdayStatisticsDO.setFansCountIncrement(totalFans);
//                yesterdayStatisticsDO.setPublishCountIncrement(publishCount.intValue());
//
//            }
            if (memberDyBindDO.getTenantId() == null) {
                accountDyDataStatisticsService.createOrUpdateEntity(yesterdayStatisticsDO);
            } else {
                TenantUtils.execute(memberDyBindDO.getTenantId(), () -> {
                    accountDyDataStatisticsService.createOrUpdateEntity(yesterdayStatisticsDO);
                });
            }

            refreshAccount.add(memberDyBindDO);

        }
        return refreshAccount;
    }

    private AccountDyDataStatisticsDO buildNewStatisticsDO(MemberDyBindDO memberDyBindDO) {
        AccountDyDataStatisticsDO dyDataStatisticsDO = accountDyDataStatisticsService.getYesterdayDataByOrderId(memberDyBindDO.getId(), memberDyBindDO.getUserType(), null);
        if (dyDataStatisticsDO == null) {
            dyDataStatisticsDO = new AccountDyDataStatisticsDO();
            dyDataStatisticsDO.setDyBindId(memberDyBindDO.getId());
            dyDataStatisticsDO.setDyType(memberDyBindDO.getUserType());
            dyDataStatisticsDO.setStatisticsTime(LocalDateTimeUtils.getMinusDaysTime(1));
            dyDataStatisticsDO.setPlatformType(PlatformType.TIKTOK.getType());
        }
        return dyDataStatisticsDO;
    }


    private void refreshVideoData(List<MemberDyBindDO> memberDyBindDOS) {
        List<Long> accountIds = memberDyBindDOS.stream().map(MemberDyBindDO::getId).collect(Collectors.toList());
        if (CollectionUtil.isEmpty(accountIds)) {
            return;
        }
        List<TaskOrderGatherDO> taskOrderGatherDOS = taskOrderGatherMapper.selectList(new LambdaQueryWrapper<TaskOrderGatherDO>()
                .in(TaskOrderGatherDO::getAccountBindId, accountIds)
                .ge(TaskOrderGatherDO::getCreateTime,LocalDateTimeUtils.getMinusDaysTime(2)));
        for (TaskOrderGatherDO taskOrderGatherDO : taskOrderGatherDOS) {
            MemberDyBindDO memberDyBindDO = memberDyBindService.getById(taskOrderGatherDO.getAccountBindId());
            if (memberDyBindDO == null) {
                log.warn("【抖音刷新数据失败】,抖音绑定数据异常,id:{}", taskOrderGatherDO.getAccountBindId());
                continue;
            }
            MemberDyTokenDO memberDyTokenDO = memberDyTokenService.getById(memberDyBindDO.getId());
            if (memberDyTokenDO == null) {
                log.warn("【抖音刷新数据失败】,抖音token不存在:id:{}", memberDyBindDO.getId());
//                this.createVideoDyDataStatistics(taskOrderGatherDO,memberDyBindDO);
                continue;
            }
            VideoDataRespDTO videoData = douYinApiUtils.getVideoData(taskOrderGatherDO.getVideoId(), memberDyBindDO.getDyOpenId(), memberDyTokenDO.getDyAccessToken());
            if (videoData == null) {
                log.warn("【抖音刷新数据失败】,videoVideoId:{},orderId:{}", taskOrderGatherDO.getVideoId(), taskOrderGatherDO.getOrderId());
//                this.createVideoDyDataStatistics(taskOrderGatherDO,memberDyBindDO);
                continue;
            }
            //用户token是否过期
            if (videoData.getData().getError_code() == 2190008) {//token过期
                log.warn("【抖音刷新数据失败】,错误信息:{},id:{}", videoData.getExtra().getDescription(), taskOrderGatherDO.getOrderId());
//                this.createVideoDyDataStatistics(taskOrderGatherDO,memberDyBindDO);
                continue;
            }
            //视频是否删除
            List<VideoListDTO> videoListDTOS = videoData.getData().getList();
            if (CollectionUtil.isEmpty(videoListDTOS)) {
                if (DyUserTypeEnum.DR.getCode().equals(taskOrderGatherDO.getOrderType())) {
                    taskOrderService.cancelOrderByDy(taskOrderGatherDO.getOrderId(), "视频已删除");
                }
                MatrixTaskOrderDO matrixTaskOrderDO = matrixTaskOrderService.getEntity(taskOrderGatherDO.getOrderId());
                matrixTaskOrderDO.setVideoStatus(VideoStatusEnum.UN_PUBLICITY.getCode());
                matrixTaskOrderService.updateEntity(matrixTaskOrderDO);
                log.warn("【抖音刷新数据失败】,视频已删除，id:{}", taskOrderGatherDO.getOrderId());
//                this.createVideoDyDataStatistics(taskOrderGatherDO,memberDyBindDO);
                continue;
            }
            MatrixTaskOrderDO matrixTaskOrderDO = matrixTaskOrderService.getEntity(taskOrderGatherDO.getOrderId());
            //视频是否公开
            if (VideoStatusEnum.UN_PUBLICITY.getCode().equals(videoListDTOS.get(0).getVideo_status())) {
                if (DyUserTypeEnum.DR.getCode().equals(taskOrderGatherDO.getOrderType())) {
                    taskOrderService.cancelOrderByDy(taskOrderGatherDO.getOrderId(), "视频未公开");
                }
                matrixTaskOrderDO.setVideoStatus(VideoStatusEnum.UN_PUBLICITY.getCode());
                matrixTaskOrderService.updateEntity(matrixTaskOrderDO);
                log.warn("【抖音刷新数据失败】,视频未公开，id:{}", taskOrderGatherDO.getOrderId());
//                this.createVideoDyDataStatistics(taskOrderGatherDO,memberDyBindDO);
                continue;
            }
            VideoListDTO videoDTO = videoListDTOS.get(0);
            VideoStatisticsDTO videoDataDTO = videoListDTOS.get(0).getStatistics();
            taskOrderGatherDO.setItemId(videoDTO.getItem_id());
            taskOrderGatherMapper.updateById(taskOrderGatherDO);
            if (DyUserTypeEnum.DR.getCode().equals(taskOrderGatherDO.getOrderType())) {
                TaskOrderDO taskOrderDO = taskOrderService.getById(taskOrderGatherDO.getOrderId());
                taskOrderDO.setVideoItemId(videoDTO.getItem_id());
                taskOrderDO.setVideoWatchCount(videoDataDTO.getPlay_count());
                taskOrderDO.setUpvoteCount(videoDataDTO.getDigg_count());
                taskOrderDO.setShareCount(videoDataDTO.getShare_count());
                taskOrderDO.setDownloadCount(videoDataDTO.getDownload_count());
                taskOrderDO.setCommentCount(videoDataDTO.getComment_count());
                taskOrderDO.setVideoStatus(videoData.getData().getList().get(0).getVideo_status());
                taskOrderDO.setVideoPublishTime(LocalDateTime.ofEpochSecond(videoData.getData().getList().get(0).getCreate_time(), 0, ZoneOffset.ofHours(8)));
                taskOrderService.updateById(taskOrderDO);
            } else {
                MatrixTaskOrderDO taskOrderDO = matrixTaskOrderService.getEntity(taskOrderGatherDO.getOrderId());
                if(taskOrderDO != null){
                    taskOrderDO.setVideoLink(videoDTO.getShare_url());
                    taskOrderDO.setExternalVideoId(videoDTO.getVideo_id());
                    taskOrderDO.setVideoStatus(videoData.getData().getList().get(0).getVideo_status());
                    taskOrderDO.setPlayCount(videoDataDTO.getPlay_count());
                    taskOrderDO.setDiggCount(videoDataDTO.getDigg_count());
                    taskOrderDO.setShareCount(videoDataDTO.getShare_count());
                    taskOrderDO.setDownloadCount(videoDataDTO.getDownload_count());
                    taskOrderDO.setCommentCount(videoDataDTO.getComment_count());
                    matrixTaskOrderService.updateEntity(taskOrderDO);
                }

            }

            VideoDyDataStatisticsDO YesterdayStatisticsDO = this.buildVideoData(taskOrderGatherDO, videoDTO.getShare_url(),matrixTaskOrderDO.getPublishTime());
            YesterdayStatisticsDO.setPlayCount(videoDataDTO.getPlay_count());
            YesterdayStatisticsDO.setDiggCount(videoDataDTO.getDigg_count());
            YesterdayStatisticsDO.setCommentCount(videoDataDTO.getComment_count());
            YesterdayStatisticsDO.setShareCount(videoDataDTO.getShare_count());
            YesterdayStatisticsDO.setPlayCountIncrement(videoDataDTO.getPlay_count());
            YesterdayStatisticsDO.setDiggCountIncrement(videoDataDTO.getDigg_count());
            YesterdayStatisticsDO.setCommentCountIncrement(videoDataDTO.getComment_count());
            YesterdayStatisticsDO.setShareCountIncrement(videoDataDTO.getShare_count());
            YesterdayStatisticsDO.setPublishTime(matrixTaskOrderDO.getPublishTime());

            //获取视频的平均播放时长
            VideoBaseRespDTO videoBaseDatas = douYinApiUtils.getVideoBaseDatas(memberDyTokenDO.getDyAccessToken(), memberDyBindDO.getDyOpenId(), videoDTO.getItem_id());
            if (videoBaseDatas.getData().getError_code() == 2190008) {//token过期
                log.warn("【抖音刷新数据失败】,错误信息:{},id:{}", videoBaseDatas.getExtra().getDescription(), taskOrderGatherDO.getOrderId());
                memberDyTokenService.updateDyAccreditStatus(memberDyBindDO.getId(), DyAccreditStatusEnum.UN_ACCREDIT);
//                this.createVideoDyDataStatistics(taskOrderGatherDO,memberDyBindDO);
           }
            if (videoBaseDatas.getData() != null && videoBaseDatas.getData().getResult() != null) {
                //设置平均播放时长
                YesterdayStatisticsDO.setPlayDuration(videoBaseDatas.getData().getResult().getAvg_play_duration());
            }
            //暂时不做新增计算，新增的即是全部
//            if (beforeStatisticsDO != null) {
//                YesterdayStatisticsDO.setPlayCountIncrement(videoDataDTO.getPlay_count() - beforeStatisticsDO.getPlayCount() < 0 ? 0 : videoDataDTO.getPlay_count() - beforeStatisticsDO.getPlayCount());
//                YesterdayStatisticsDO.setDiggCountIncrement(videoDataDTO.getDigg_count() - beforeStatisticsDO.getDiggCount() < 0 ? 0 : videoDataDTO.getDigg_count() - beforeStatisticsDO.getDiggCount());
//                YesterdayStatisticsDO.setCommentCountIncrement(videoDataDTO.getComment_count() - beforeStatisticsDO.getCommentCount() < 0 ? 0 : videoDataDTO.getComment_count() - beforeStatisticsDO.getCommentCount());
//                YesterdayStatisticsDO.setShareCountIncrement(videoDataDTO.getShare_count() - beforeStatisticsDO.getShareCount() < 0 ? 0 : videoDataDTO.getShare_count() - beforeStatisticsDO.getShareCount());
//            }
            if (taskOrderGatherDO.getTenantId() == null) {
                videoDyDataStatisticsService.createOrUpdateEntity(YesterdayStatisticsDO);
            } else {
                TenantUtils.execute(taskOrderGatherDO.getTenantId(), () -> {
                    videoDyDataStatisticsService.createOrUpdateEntity(YesterdayStatisticsDO);
                });
            }

        }

        //更新账号数据
        for (MemberDyBindDO memberDyBindDO : memberDyBindDOS) {
            //该账户当天的视频数据统计
            List<VideoDyDataStatisticsDO> videoStatisticsDOS = videoDyDataStatisticsService.getBeforeDataByBindId(memberDyBindDO.getId(), null);
//            AccountDyDataStatisticsDO beforeStatisticsDO = accountDyDataStatisticsService.getBeforeDataByOrderId(memberDyBindDO.getId(), memberDyBindDO.getUserType(), LocalDateTimeUtils.getMinusDaysTime(2));
            AccountDyDataStatisticsDO yesterdayStatisticsDO = this.buildNewStatisticsDO(memberDyBindDO);

            int playCount = videoStatisticsDOS.stream().mapToInt(VideoDyDataStatisticsDO::getPlayCount).sum();
            yesterdayStatisticsDO.setPlayCount(playCount);
            int diggCount = videoStatisticsDOS.stream().mapToInt(VideoDyDataStatisticsDO::getDiggCount).sum();
            yesterdayStatisticsDO.setDiggCount(diggCount);
            int commentCount = videoStatisticsDOS.stream().mapToInt(VideoDyDataStatisticsDO::getCommentCount).sum();
            yesterdayStatisticsDO.setCommentCount(commentCount);
            int shareCount = videoStatisticsDOS.stream().mapToInt(VideoDyDataStatisticsDO::getShareCount).sum();
            yesterdayStatisticsDO.setShareCount(shareCount);
            yesterdayStatisticsDO.setPlayCountIncrement(playCount);
            yesterdayStatisticsDO.setDiggCountIncrement(diggCount);
            yesterdayStatisticsDO.setCommentCountIncrement(commentCount);
            yesterdayStatisticsDO.setShareCountIncrement(shareCount);

            //暂时不做新增计算，新增即是全部
//            if (beforeStatisticsDO != null) {
//                yesterdayStatisticsDO.setPlayCountIncrement(playCount - beforeStatisticsDO.getPlayCount() < 0 ? 0 : playCount - beforeStatisticsDO.getPlayCount());
//                yesterdayStatisticsDO.setDiggCountIncrement(diggCount - beforeStatisticsDO.getDiggCount() < 0 ? 0 : diggCount - beforeStatisticsDO.getDiggCount());
//                yesterdayStatisticsDO.setCommentCountIncrement(commentCount - beforeStatisticsDO.getCommentCount() < 0 ? 0 : commentCount - beforeStatisticsDO.getCommentCount());
//                yesterdayStatisticsDO.setShareCountIncrement(shareCount - beforeStatisticsDO.getShareCount() < 0 ? 0 : shareCount - beforeStatisticsDO.getShareCount());
//            }
            if (memberDyBindDO.getTenantId() == null) {
                accountDyDataStatisticsService.createOrUpdateEntity(yesterdayStatisticsDO);
            } else {
                TenantUtils.execute(memberDyBindDO.getTenantId(), () -> {
                    accountDyDataStatisticsService.createOrUpdateEntity(yesterdayStatisticsDO);
                });
            }

        }

    }


    private VideoDyDataStatisticsDO buildVideoData(TaskOrderGatherDO taskOrderGatherDO, String shareUrl, LocalDateTime publishTime) {
        VideoDyDataStatisticsDO YesterdayStatisticsDO = videoDyDataStatisticsService.getYesterdayDataByOrderId(taskOrderGatherDO.getOrderId(), taskOrderGatherDO.getTaskId(), taskOrderGatherDO.getOrderType(), publishTime.toLocalDate());
        if (YesterdayStatisticsDO == null) {
            YesterdayStatisticsDO = new VideoDyDataStatisticsDO();
            YesterdayStatisticsDO.setOrderId(taskOrderGatherDO.getOrderId());
            YesterdayStatisticsDO.setTaskId(taskOrderGatherDO.getTaskId());
            YesterdayStatisticsDO.setDyType(taskOrderGatherDO.getOrderType());
            YesterdayStatisticsDO.setDyBindId(taskOrderGatherDO.getAccountBindId());
            YesterdayStatisticsDO.setVideoLink(shareUrl);
            YesterdayStatisticsDO.setStatisticsTime(publishTime.toLocalDate());
        }
        return YesterdayStatisticsDO;
    }


}
