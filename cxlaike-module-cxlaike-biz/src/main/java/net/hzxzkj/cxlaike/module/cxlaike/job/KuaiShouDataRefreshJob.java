package net.hzxzkj.cxlaike.module.cxlaike.job;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.github.kwai.open.KwaiOpenException;
import com.github.kwai.open.model.VideoInfo;
import com.github.kwai.open.response.VideoInfoResponse;
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
import net.hzxzkj.cxlaike.module.cxlaike.service.kuaishou.KuaiShouService;
import net.hzxzkj.cxlaike.module.cxlaike.service.matrixtaskorder.MatrixTaskOrderService;
import net.hzxzkj.cxlaike.module.cxlaike.service.memberdybind.MemberDyBindService;
import net.hzxzkj.cxlaike.module.cxlaike.service.memberdytoken.MemberDyTokenService;
import net.hzxzkj.cxlaike.module.cxlaike.service.taskdydatastatistics.TaskDyDataStatisticsService;
import net.hzxzkj.cxlaike.module.cxlaike.service.taskorder.TaskOrderService;
import net.hzxzkj.cxlaike.module.cxlaike.service.videodydatastatistics.VideoDyDataStatisticsService;
import net.hzxzkj.cxlaike.module.cxlaike.utils.douyin.DouYinApiUtils;
import net.hzxzkj.cxlaike.module.cxlaike.utils.douyin.vo.*;
import net.hzxzkj.cxlaike.module.cxlaike.utils.kuaishou.KuaiShouApiUtils;
import net.hzxzkj.cxlaike.module.member.enums.DyAccreditStatusEnum;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Component
//@TenantJob // 标记多租户
@Slf4j
public class KuaiShouDataRefreshJob implements JobHandler {


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

    @Resource
    private KuaiShouApiUtils kuaiShouApiUtils;

    @Resource
    private KuaiShouService kuaiShouService;

    @Override
    @TenantIgnore
    public String execute(String param) {
        log.info("【快手刷新数据】开始++++++++++++++++++");

        //账号数据刷新
        List<MemberDyBindDO> memberDyBindDOS = this.refreshAccountData();

        //订单视频数据
        this.refreshVideoData(memberDyBindDOS);

        //任务数据刷新
        this.refreshTaskData();

        log.info("【快手刷新数据】结束++++++++++++++++++");
        return "";
    }

    private TaskDyDataStatisticsDO buildTaskData(List<VideoDyDataStatisticsDO> dataStatisticsDOS, Long taskId) {
        TaskDyDataStatisticsDO YesterdayStatisticsDO = taskDyDataStatisticsService.getYesterdayDataByTaskId(taskId, LocalDateTimeUtils.getMinusDaysTime(1));
        if (YesterdayStatisticsDO == null) {
            YesterdayStatisticsDO = new TaskDyDataStatisticsDO();
            YesterdayStatisticsDO.setTaskId(taskId);
            YesterdayStatisticsDO.setTaskType(dataStatisticsDOS.get(0).getDyType());
            YesterdayStatisticsDO.setPlatformType(PlatformType.KUAISHOU.getType());
            YesterdayStatisticsDO.setStatisticsTime(LocalDateTimeUtils.getMinusDaysTime(1));
        }
        return YesterdayStatisticsDO;
    }

    private void refreshTaskData() {
        //前一天所有视频数据统计
        List<VideoDyDataStatisticsDO> videoStatisticsDOS = videoDyDataStatisticsService.getBeforeData(LocalDateTimeUtils.getMinusDaysTime(1), PlatformType.KUAISHOU.getType());

        Map<Long, List<VideoDyDataStatisticsDO>> taskVideoResp = videoStatisticsDOS.stream().collect(Collectors.groupingBy(VideoDyDataStatisticsDO::getTaskId));
        for (Map.Entry<Long, List<VideoDyDataStatisticsDO>> entry : taskVideoResp.entrySet()) {
            Long taskId = entry.getKey();
            List<VideoDyDataStatisticsDO> dataStatisticsDOS = entry.getValue();

            Long publishCount = taskOrderGatherMapper.selectCount(new LambdaQueryWrapper<TaskOrderGatherDO>()
                    .eq(TaskOrderGatherDO::getTaskId, taskId));
            TaskDyDataStatisticsDO YesterdayStatisticsDO = this.buildTaskData(dataStatisticsDOS, taskId);
            int playCount = dataStatisticsDOS.stream().mapToInt(VideoDyDataStatisticsDO::getPlayCount).sum();
            YesterdayStatisticsDO.setPlayCount(playCount);
            int diggCount = dataStatisticsDOS.stream().mapToInt(VideoDyDataStatisticsDO::getDiggCount).sum();
            YesterdayStatisticsDO.setDiggCount(diggCount);
            int commentCount = dataStatisticsDOS.stream().mapToInt(VideoDyDataStatisticsDO::getCommentCount).sum();
            YesterdayStatisticsDO.setCommentCount(commentCount);
//            int shareCount = dataStatisticsDOS.stream().mapToInt(VideoDyDataStatisticsDO::getShareCount).sum();
//            YesterdayStatisticsDO.setShareCount(shareCount);
            YesterdayStatisticsDO.setPublishCount(publishCount.intValue());
            YesterdayStatisticsDO.setPublishCountIncrement(publishCount.intValue());
            YesterdayStatisticsDO.setPlayCountIncrement(playCount);
            YesterdayStatisticsDO.setDiggCountIncrement(diggCount);
            YesterdayStatisticsDO.setCommentCountIncrement(commentCount);
//
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

        List<MemberDyBindDO> drMemberDyBindDOS = memberDyBindService.getList(PlatformType.KUAISHOU.getType());
        for (MemberDyBindDO memberDyBindDO : drMemberDyBindDOS) {
            MemberDyTokenDO memberDyTokenDO = memberDyTokenService.getById(memberDyBindDO.getId());
            if (memberDyTokenDO == null) {
                log.warn("【快手刷新数据失败】,快手token不存在:id:{}", memberDyBindDO.getId());
                continue;
            }

            UserInfoRespDTO userInfo = null;
            try {
                userInfo = kuaiShouApiUtils.getUserInfo(memberDyTokenDO.getDyAccessToken(), null);
            } catch (Exception e) {
                log.error("【快手】,获取用户信息错误,错误信息:{},openId:{}", e, memberDyBindDO.getDyOpenId());
                continue;
            }
            int totalFans = Math.toIntExact(userInfo.getFan());
//
            //发布数
            Long publishCount = taskOrderGatherMapper.selectCount(new LambdaQueryWrapper<TaskOrderGatherDO>()
                    .eq(TaskOrderGatherDO::getAccountBindId, memberDyBindDO.getId())
                    .between(TaskOrderGatherDO::getCreateTime,LocalDateTimeUtils.getTodayStart().minusDays(1),LocalDateTimeUtils.getTodayStart()));

            AccountDyDataStatisticsDO yesterdayStatisticsDO = this.buildNewStatisticsDO(memberDyBindDO);

            yesterdayStatisticsDO.setFansCount(totalFans);
            yesterdayStatisticsDO.setPublishCount(publishCount.intValue());
            yesterdayStatisticsDO.setPublishCountIncrement(publishCount.intValue());

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
            dyDataStatisticsDO.setPlatformType(PlatformType.KUAISHOU.getType());
        }
        return dyDataStatisticsDO;
    }


    private void refreshVideoData(List<MemberDyBindDO> memberDyBindDOS) {
        List<Long> accountIds = memberDyBindDOS.stream().map(MemberDyBindDO::getId).collect(Collectors.toList());
        if (CollectionUtil.isEmpty(accountIds)) {
            return;
        }
        //近两天的数据
        List<TaskOrderGatherDO> taskOrderGatherDOS = taskOrderGatherMapper.selectList(new LambdaQueryWrapper<TaskOrderGatherDO>()
                .in(TaskOrderGatherDO::getAccountBindId, accountIds)
                .ge(TaskOrderGatherDO::getCreateTime,LocalDateTimeUtils.getMinusDaysTime(2)));

        for (TaskOrderGatherDO taskOrderGatherDO : taskOrderGatherDOS) {
            Long accountBindId = taskOrderGatherDO.getAccountBindId();
            MemberDyBindDO memberDyBindDO = memberDyBindService.getById(accountBindId);
            if (memberDyBindDO == null) {
                log.warn("【快手刷新数据失败】,快手绑定数据异常,id:{}", accountBindId);
                continue;
            }
            MemberDyTokenDO memberDyTokenDO = memberDyTokenService.getById(memberDyBindDO.getId());
            if (memberDyTokenDO == null) {
                log.warn("【快手刷新数据失败】,快手token不存在:id:{}", memberDyBindDO.getId());
                continue;
            }
            VideoInfoResponse response = null;
            try {
                response = kuaiShouApiUtils.getVideoData(taskOrderGatherDO.getVideoId(), memberDyTokenDO.getDyAccessToken());
                kuaiShouService.checkAccessToken(accountBindId, response.getResult(), response.getErrorMsg());
            } catch (Exception e){
                e.printStackTrace();
                continue;
            }

            //视频是否删除
            VideoInfo videoInfo = response.getVideoInfo();
            if (null == videoInfo) {
                log.warn("【快手刷新数据失败】,视频已删除，id:{}", taskOrderGatherDO.getOrderId());
                continue;
            }
            //视频是否公开
            if (videoInfo.getPending()) {
                log.warn("【快手刷新数据失败】,视频还在处理中，id:{}", taskOrderGatherDO.getOrderId());
                continue;
            }

            MatrixTaskOrderDO taskOrderDO = matrixTaskOrderService.getEntity(taskOrderGatherDO.getOrderId());
            if(taskOrderDO != null){
                taskOrderDO.setVideoLink(videoInfo.getPlayUrl());
                taskOrderDO.setExternalVideoId(videoInfo.getPhotoId());
                taskOrderDO.setVideoStatus(VideoStatusEnum.PUBLICITY.getCode());
                taskOrderDO.setPlayCount(Math.toIntExact(videoInfo.getViewCount()));
                taskOrderDO.setDiggCount(Math.toIntExact(videoInfo.getLikeCount()));
                taskOrderDO.setCommentCount(Math.toIntExact(videoInfo.getCommentCount()));
                matrixTaskOrderService.updateEntity(taskOrderDO);
            }

            //统计表
//            VideoDyDataStatisticsDO beforeDO = videoDyDataStatisticsService.getBeforeDataByOrderId(taskOrderGatherDO.getOrderId(), taskOrderGatherDO.getTaskId(), taskOrderGatherDO.getOrderType(), LocalDateTimeUtils.getMinusDaysTime(2));
            VideoDyDataStatisticsDO yesterdayDO = this.buildVideoData(taskOrderGatherDO, videoInfo,taskOrderDO.getPublishTime());

            if (taskOrderGatherDO.getTenantId() == null) {
                videoDyDataStatisticsService.createOrUpdateEntity(yesterdayDO);
            } else {
                TenantUtils.execute(taskOrderGatherDO.getTenantId(), () -> {
                    videoDyDataStatisticsService.createOrUpdateEntity(yesterdayDO);
                });
            }

        }

        //更新账号数据
        for (MemberDyBindDO memberDyBindDO : memberDyBindDOS) {
            //该账户当天的视频数据统计
            List<VideoDyDataStatisticsDO> videoStatisticsDOS = videoDyDataStatisticsService.getBeforeDataByBindId(memberDyBindDO.getId(), null);
            AccountDyDataStatisticsDO yesterdayStatisticsDO = this.buildNewStatisticsDO(memberDyBindDO);

            int playCount = videoStatisticsDOS.stream().mapToInt(VideoDyDataStatisticsDO::getPlayCount).sum();
            yesterdayStatisticsDO.setPlayCount(playCount);
            int diggCount = videoStatisticsDOS.stream().mapToInt(VideoDyDataStatisticsDO::getDiggCount).sum();
            yesterdayStatisticsDO.setDiggCount(diggCount);
            int commentCount = videoStatisticsDOS.stream().mapToInt(VideoDyDataStatisticsDO::getCommentCount).sum();
            yesterdayStatisticsDO.setCommentCount(commentCount);
//            int shareCount = videoStatisticsDOS.stream().mapToInt(VideoDyDataStatisticsDO::getShareCount).sum();
//            yesterdayStatisticsDO.setShareCount(shareCount);
            yesterdayStatisticsDO.setPlayCountIncrement(playCount);
            yesterdayStatisticsDO.setDiggCountIncrement(diggCount);
            yesterdayStatisticsDO.setCommentCountIncrement(commentCount);

            if (memberDyBindDO.getTenantId() == null) {
                accountDyDataStatisticsService.createOrUpdateEntity(yesterdayStatisticsDO);
            } else {
                TenantUtils.execute(memberDyBindDO.getTenantId(), () -> {
                    accountDyDataStatisticsService.createOrUpdateEntity(yesterdayStatisticsDO);
                });
            }

        }

    }

    private VideoDyDataStatisticsDO buildVideoData(TaskOrderGatherDO taskOrderGatherDO, VideoInfo videoInfo,LocalDateTime publishTime) {
        VideoDyDataStatisticsDO videoDyDataStatisticsDO = videoDyDataStatisticsService.getYesterdayDataByOrderId(taskOrderGatherDO.getOrderId(),
                taskOrderGatherDO.getTaskId(), taskOrderGatherDO.getOrderType(), publishTime.toLocalDate());
        if (videoDyDataStatisticsDO == null) {
            videoDyDataStatisticsDO = new VideoDyDataStatisticsDO();
        }
        videoDyDataStatisticsDO.setOrderId(taskOrderGatherDO.getOrderId());
        videoDyDataStatisticsDO.setTaskId(taskOrderGatherDO.getTaskId());
        videoDyDataStatisticsDO.setDyType(taskOrderGatherDO.getOrderType());
        videoDyDataStatisticsDO.setDyBindId(taskOrderGatherDO.getAccountBindId());
        videoDyDataStatisticsDO.setPlatformType(PlatformType.KUAISHOU.getType());
        videoDyDataStatisticsDO.setVideoLink(videoInfo.getPlayUrl());

        videoDyDataStatisticsDO.setPlayCount(Math.toIntExact(videoInfo.getViewCount()));
        videoDyDataStatisticsDO.setDiggCount(Math.toIntExact(videoInfo.getLikeCount()));
        videoDyDataStatisticsDO.setCommentCount(Math.toIntExact(videoInfo.getCommentCount()));
        videoDyDataStatisticsDO.setShareCount(0);
        videoDyDataStatisticsDO.setPlayCountIncrement(Math.toIntExact(videoInfo.getViewCount()));
        videoDyDataStatisticsDO.setDiggCountIncrement(Math.toIntExact(videoInfo.getLikeCount()));
        videoDyDataStatisticsDO.setCommentCountIncrement(Math.toIntExact(videoInfo.getCommentCount()));
        videoDyDataStatisticsDO.setShareCountIncrement(0);
        videoDyDataStatisticsDO.setStatisticsTime(publishTime.toLocalDate());
        videoDyDataStatisticsDO.setPublishTime(publishTime);
        return videoDyDataStatisticsDO;
    }


}
