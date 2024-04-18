package net.hzxzkj.cxlaike.module.cxlaike.service.videodydatastatistics;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.date.LocalDateTimeUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.diboot.core.binding.Binder;
import com.diboot.core.service.impl.BaseServiceImpl;
import net.hzxzkj.cxlaike.framework.common.pojo.PageResult;
import net.hzxzkj.cxlaike.framework.common.util.date.DateUtils;
import net.hzxzkj.cxlaike.framework.common.util.date.LocalDateTimeUtils;
import net.hzxzkj.cxlaike.framework.mybatis.core.query.LambdaQueryWrapperX;
import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.accountdydatastatistics.vo.AccountDyDataStatisticsAnalyzeRespVO;
import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.taskdydatastatistics.vo.TaskDyDataStatisticsAnalyzeRespVO;
import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.videodydatastatistics.vo.*;
import net.hzxzkj.cxlaike.module.cxlaike.convert.taskdydatastatistics.TaskDyDataStatisticsConvert;
import net.hzxzkj.cxlaike.module.cxlaike.convert.videodydatastatistics.VideoDyDataStatisticsConvert;
import net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.accountgroupcorrelation.AccountGroupCorrelationDO;
import net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.matrixtaskorder.MatrixTaskOrderDO;
import net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.taskdydatastatistics.TaskDyDataStatisticsDO;
import net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.videodydatastatistics.VideoDyDataStatisticsDO;
import net.hzxzkj.cxlaike.module.cxlaike.dal.mysql.accountgroupcorrelation.AccountGroupCorrelationMapper;
import net.hzxzkj.cxlaike.module.cxlaike.dal.mysql.matrixtaskorder.MatrixTaskOrderMapper;
import net.hzxzkj.cxlaike.module.cxlaike.dal.mysql.videodydatastatistics.VideoDyDataStatisticsMapper;
import net.hzxzkj.cxlaike.module.cxlaike.enums.PlatformType;
import net.hzxzkj.cxlaike.module.cxlaike.enums.douyin.DyUserTypeEnum;
import net.hzxzkj.cxlaike.module.cxlaike.enums.statisticsdate.StatisticsDateOrderByEnum;
import net.hzxzkj.cxlaike.module.cxlaike.enums.statisticsdate.StatisticsDateTypeEnum;
import net.hzxzkj.cxlaike.module.cxlaike.enums.task.TaskTypeEnum;
import net.hzxzkj.cxlaike.module.cxlaike.service.matrixtaskorder.MatrixTaskOrderService;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;

import static net.hzxzkj.cxlaike.framework.common.exception.util.ServiceExceptionUtil.exception;
import static net.hzxzkj.cxlaike.module.cxlaike.enums.ErrorCodeConstants.PARAMS_ERROR;

/**
 * 视频纬度抖音数据统计 Service 实现类
 *
 * @author 宵征源码
 */
@Service
@Validated
public class VideoDyDataStatisticsServiceImpl extends BaseServiceImpl<VideoDyDataStatisticsMapper, VideoDyDataStatisticsDO> implements VideoDyDataStatisticsService {

    @Resource
    private VideoDyDataStatisticsMapper videoDyDataStatisticsMapper;
    @Resource
    private AccountGroupCorrelationMapper accountGroupCorrelationMapper;

    @Resource
    private MatrixTaskOrderMapper matrixTaskOrderMapper;

    @Override
    public VideoDyDataStatisticsRespVO getVideoDyDataStatisticsByDate(VideoDyDataStatisticsReqVO reqVO) {
        VideoDyDataStatisticsRespVO respVO = new VideoDyDataStatisticsRespVO();
        //昨日(单日数据统计)
        if (StatisticsDateTypeEnum.YESTERDAY.getCode().equals(reqVO.getDateType())) {
            LocalDate statisticsTime = LocalDateTimeUtils.getMinusDaysTime(reqVO.getDateType());
            List<VideoDyDataStatisticsDO> videoDyDataStatisticsDOS = videoDyDataStatisticsMapper.selectList(new LambdaQueryWrapper<VideoDyDataStatisticsDO>()
                    .eq(VideoDyDataStatisticsDO::getStatisticsTime, statisticsTime)
                    .eq(VideoDyDataStatisticsDO::getDyType, DyUserTypeEnum.SJ.getCode())
                    .eq(VideoDyDataStatisticsDO::getPlatformType, reqVO.getPlatformType()));

            if (CollectionUtil.isEmpty(videoDyDataStatisticsDOS)) {
                return respVO;
            }
            this.buildYesterdayData(respVO, statisticsTime, videoDyDataStatisticsDOS);
            return respVO;
        }
        this.buildMoreDaysData(reqVO, respVO);
        return respVO;
    }

    private void buildMoreDaysData(VideoDyDataStatisticsReqVO reqVO, VideoDyDataStatisticsRespVO respVO) {
        LocalDate startTime = null;
        LocalDate endTime = null;

        if (reqVO.getDateType() != null) {
            startTime = LocalDateTimeUtils.getMinusDaysTime(reqVO.getDateType());
            endTime = LocalDateTimeUtils.getMinusDaysTime(1);
        } else {
            if (reqVO.getStatisticsTime() == null) {
                throw exception(PARAMS_ERROR);
            }
            startTime = reqVO.getStatisticsTime()[0];
            endTime = reqVO.getStatisticsTime()[1];
            if (endTime.compareTo(LocalDateTimeUtils.getMinusDaysTime(1)) > 0) {
                endTime = LocalDateTimeUtils.getMinusDaysTime(1);
            }
        }
        List<VideoDyDataStatisticsDO> videoDyDataStatisticsAllDOS = new ArrayList<>();

        if (startTime.compareTo(endTime) > 0) {
            return;
        }
        if (startTime.compareTo(LocalDateTimeUtils.getMinusDaysTime(1)) > 0) {
            return;
        }
        if (startTime.compareTo(endTime) == 0) {
            videoDyDataStatisticsAllDOS = videoDyDataStatisticsMapper.selectList(new LambdaQueryWrapper<VideoDyDataStatisticsDO>()
                    .eq(VideoDyDataStatisticsDO::getStatisticsTime, endTime)
                    .eq(VideoDyDataStatisticsDO::getDyType, DyUserTypeEnum.SJ.getCode())
                    .eq(VideoDyDataStatisticsDO::getPlatformType, reqVO.getPlatformType()));
        } else {
            videoDyDataStatisticsAllDOS = videoDyDataStatisticsMapper.selectList(new LambdaQueryWrapper<VideoDyDataStatisticsDO>()
                    .between(VideoDyDataStatisticsDO::getStatisticsTime, startTime, endTime)
                    .eq(VideoDyDataStatisticsDO::getDyType, DyUserTypeEnum.SJ.getCode())
                    .eq(VideoDyDataStatisticsDO::getPlatformType, reqVO.getPlatformType()));
        }

        Map<LocalDate, List<VideoDyDataStatisticsDO>> listMap = videoDyDataStatisticsAllDOS.stream().collect(Collectors.groupingBy(VideoDyDataStatisticsDO::getStatisticsTime));
        List<VideoDyDataDateRespVO> playDateIncrement = new ArrayList<>();
        List<VideoDyDataDateRespVO> diggDateIncrement = new ArrayList<>();
        List<VideoDyDataDateRespVO> commentDateIncrement = new ArrayList<>();
        List<VideoDyDataDateRespVO> shareDateIncrement = new ArrayList<>();
        List<VideoDyDataDateRespVO> collectDateIncrement = new ArrayList<>();
        for (Map.Entry<LocalDate, List<VideoDyDataStatisticsDO>> entry : listMap.entrySet()) {
            VideoDyDataDateRespVO playDateRespVO = new VideoDyDataDateRespVO();
            playDateRespVO.setStatisticsTime(entry.getKey());
            playDateRespVO.setCountIncrement(entry.getValue().stream().mapToInt(VideoDyDataStatisticsDO::getPlayCountIncrement).sum());
            playDateIncrement.add(playDateRespVO);

            VideoDyDataDateRespVO diggDateRespVO = new VideoDyDataDateRespVO();
            diggDateRespVO.setStatisticsTime(entry.getKey());
            diggDateRespVO.setCountIncrement(entry.getValue().stream().mapToInt(VideoDyDataStatisticsDO::getDiggCountIncrement).sum());
            diggDateIncrement.add(diggDateRespVO);

            VideoDyDataDateRespVO commentDateRespVO = new VideoDyDataDateRespVO();
            commentDateRespVO.setStatisticsTime(entry.getKey());
            commentDateRespVO.setCountIncrement(entry.getValue().stream().mapToInt(VideoDyDataStatisticsDO::getCommentCountIncrement).sum());
            commentDateIncrement.add(commentDateRespVO);

            VideoDyDataDateRespVO shareDateRespVO = new VideoDyDataDateRespVO();
            shareDateRespVO.setStatisticsTime(entry.getKey());
            shareDateRespVO.setCountIncrement(entry.getValue().stream().mapToInt(VideoDyDataStatisticsDO::getShareCountIncrement).sum());
            shareDateIncrement.add(shareDateRespVO);

            VideoDyDataDateRespVO collectDateRespVO = new VideoDyDataDateRespVO();
            collectDateRespVO.setStatisticsTime(entry.getKey());
            collectDateRespVO.setCountIncrement(entry.getValue().stream().mapToInt(VideoDyDataStatisticsDO::getCollectCountIncrement).sum());
            collectDateIncrement.add(collectDateRespVO);
        }

        respVO.setPlayCountIncrement(playDateIncrement.stream().mapToInt(VideoDyDataDateRespVO::getCountIncrement).sum());
        respVO.setDiggCountIncrement(diggDateIncrement.stream().mapToInt(VideoDyDataDateRespVO::getCountIncrement).sum());
        respVO.setCommentCountIncrement(commentDateIncrement.stream().mapToInt(VideoDyDataDateRespVO::getCountIncrement).sum());
        respVO.setShareCountIncrement(shareDateIncrement.stream().mapToInt(VideoDyDataDateRespVO::getCountIncrement).sum());
        respVO.setCollectCountIncrement(collectDateIncrement.stream().mapToInt(VideoDyDataDateRespVO::getCountIncrement).sum());
        respVO.setDiggDateIncrement(diggDateIncrement.stream().sorted(Comparator.comparing(VideoDyDataDateRespVO::getStatisticsTime)).collect(Collectors.toList()));
        respVO.setPlayDateIncrement(playDateIncrement.stream().sorted(Comparator.comparing(VideoDyDataDateRespVO::getStatisticsTime)).collect(Collectors.toList()));
        respVO.setCommentDateIncrement(commentDateIncrement.stream().sorted(Comparator.comparing(VideoDyDataDateRespVO::getStatisticsTime)).collect(Collectors.toList()));
        respVO.setShareDateIncrement(shareDateIncrement.stream().sorted(Comparator.comparing(VideoDyDataDateRespVO::getStatisticsTime)).collect(Collectors.toList()));
        respVO.setCollectDateIncrement(collectDateIncrement.stream().sorted(Comparator.comparing(VideoDyDataDateRespVO::getStatisticsTime)).collect(Collectors.toList()));

    }

    private void buildYesterdayData(VideoDyDataStatisticsRespVO respVO, LocalDate statisticsTime, List<VideoDyDataStatisticsDO> videoDyDataStatisticsDOS) {
        //播放
        respVO.setPlayCountIncrement(videoDyDataStatisticsDOS.stream().mapToInt(VideoDyDataStatisticsDO::getPlayCountIncrement).sum());
        List<VideoDyDataDateRespVO> playCountList = new ArrayList<>();
        VideoDyDataDateRespVO playDateRespVO = new VideoDyDataDateRespVO();
        playDateRespVO.setStatisticsTime(statisticsTime);
        playDateRespVO.setCountIncrement(videoDyDataStatisticsDOS.stream().mapToInt(VideoDyDataStatisticsDO::getPlayCountIncrement).sum());
        playCountList.add(playDateRespVO);
        respVO.setPlayDateIncrement(playCountList);
        //点赞
        respVO.setDiggCountIncrement(videoDyDataStatisticsDOS.stream().mapToInt(VideoDyDataStatisticsDO::getDiggCountIncrement).sum());
        List<VideoDyDataDateRespVO> diggCountList = new ArrayList<>();
        VideoDyDataDateRespVO diggDateRespVO = new VideoDyDataDateRespVO();
        diggDateRespVO.setStatisticsTime(statisticsTime);
        diggDateRespVO.setCountIncrement(videoDyDataStatisticsDOS.stream().mapToInt(VideoDyDataStatisticsDO::getDiggCountIncrement).sum());
        diggCountList.add(diggDateRespVO);
        respVO.setDiggDateIncrement(diggCountList);
        //评论
        respVO.setCommentCountIncrement(videoDyDataStatisticsDOS.stream().mapToInt(VideoDyDataStatisticsDO::getCommentCountIncrement).sum());
        List<VideoDyDataDateRespVO> commentCountList = new ArrayList<>();
        VideoDyDataDateRespVO commentDateRespVO = new VideoDyDataDateRespVO();
        commentDateRespVO.setStatisticsTime(statisticsTime);
        commentDateRespVO.setCountIncrement(videoDyDataStatisticsDOS.stream().mapToInt(VideoDyDataStatisticsDO::getCommentCountIncrement).sum());
        commentCountList.add(commentDateRespVO);
        respVO.setCommentDateIncrement(commentCountList);
        //转发
        respVO.setShareCountIncrement(videoDyDataStatisticsDOS.stream().mapToInt(VideoDyDataStatisticsDO::getShareCountIncrement).sum());
        List<VideoDyDataDateRespVO> shareCountList = new ArrayList<>();
        VideoDyDataDateRespVO shareDateRespVO = new VideoDyDataDateRespVO();
        shareDateRespVO.setStatisticsTime(statisticsTime);
        shareDateRespVO.setCountIncrement(videoDyDataStatisticsDOS.stream().mapToInt(VideoDyDataStatisticsDO::getShareCountIncrement).sum());
        shareCountList.add(shareDateRespVO);
        respVO.setShareDateIncrement(shareCountList);
        //收藏
        respVO.setCollectCountIncrement(videoDyDataStatisticsDOS.stream().mapToInt(VideoDyDataStatisticsDO::getCollectCountIncrement).sum());
        List<VideoDyDataDateRespVO> collectCountList = new ArrayList<>();
        VideoDyDataDateRespVO collectDateRespVO = new VideoDyDataDateRespVO();
        collectDateRespVO.setStatisticsTime(statisticsTime);
        collectDateRespVO.setCountIncrement(videoDyDataStatisticsDOS.stream().mapToInt(VideoDyDataStatisticsDO::getShareCountIncrement).sum());
        collectCountList.add(collectDateRespVO);
        respVO.setCollectDateIncrement(collectCountList);
    }


    @Override
    public List<VideoDyDataStatisticsAnalyzeRespVO> getStatisticsForAnalyze(Integer dateType, Integer platformType, Integer orderBy, Boolean isAsc) {
        List<VideoDyDataStatisticsAnalyzeRespVO> respVOList = new ArrayList<>();
        String sc = isAsc ? " asc" : " desc";
        if (StatisticsDateTypeEnum.YESTERDAY.getCode().equals(dateType)) {
            LocalDate statisticsTime = LocalDateTimeUtils.getMinusDaysTime(dateType);
            String orderByStr = "order by " + StatisticsDateOrderByEnum.getEnumByCode(orderBy).getValue() + sc + " limit 5";
            List<VideoDyDataStatisticsDO> videoDyDataStatisticsDOS = videoDyDataStatisticsMapper.selectList(new LambdaQueryWrapper<VideoDyDataStatisticsDO>()
                    .eq(VideoDyDataStatisticsDO::getStatisticsTime, statisticsTime)
                    .eq(VideoDyDataStatisticsDO::getDyType, DyUserTypeEnum.SJ.getCode())
                    .eq(VideoDyDataStatisticsDO::getPlatformType, platformType)
                    .last(orderByStr));

            if (CollectionUtil.isEmpty(videoDyDataStatisticsDOS)) {
                return respVOList;
            }

            List<VideoDyDataStatisticsAnalyzeRespVO> voList = Binder.convertAndBindRelations(videoDyDataStatisticsDOS, VideoDyDataStatisticsAnalyzeRespVO.class);
            return voList;
        }
        List<VideoDyDataStatisticsAnalyzeRespVO> analyzeRespVOS = this.buildMoreDaysData(dateType, platformType, orderBy, null, null, respVOList, sc, 0, 5, null,null,null);

        List<VideoDyDataStatisticsAnalyzeRespVO> voList = Binder.convertAndBindRelations(analyzeRespVOS, VideoDyDataStatisticsAnalyzeRespVO.class);

        return voList;
    }

    private List<VideoDyDataStatisticsAnalyzeRespVO> buildMoreDaysData(Integer dateType, Integer platformType, Integer orderBy, Long dyBindId,
                                                                       List<Long> dyBindIds, List<VideoDyDataStatisticsAnalyzeRespVO> respVOList,
                                                                       String sc, int startNum, int pageSize, Map map,LocalDateTime startTime1,LocalDateTime endTime1) {
        LocalDate startTime = dateType==null?null:LocalDateTimeUtils.getMinusDaysTime(dateType);
        String orderByStr = StatisticsDateOrderByEnum.getEnumByCode(orderBy).getValue();
        LambdaQueryWrapperX<VideoDyDataStatisticsDO> lambdaQueryWrapper = (LambdaQueryWrapperX<VideoDyDataStatisticsDO>) new LambdaQueryWrapperX<VideoDyDataStatisticsDO>()
                .ge(startTime!=null,VideoDyDataStatisticsDO::getStatisticsTime, LocalDateTimeUtil.format(startTime, DateUtils.FORMAT_YEAR_MONTH_DAY))
                .between(startTime1!=null && endTime1!=null,VideoDyDataStatisticsDO::getStatisticsTime,startTime1,endTime1)
                .eq(VideoDyDataStatisticsDO::getDyType, DyUserTypeEnum.SJ.getCode())
                .eq(VideoDyDataStatisticsDO::getPlatformType, platformType)
                .eq(dyBindId!=null,VideoDyDataStatisticsDO::getDyBindId, dyBindId)
                .in(dyBindIds!=null,VideoDyDataStatisticsDO::getDyBindId, dyBindIds);
        if (map != null) {
            Long count = videoDyDataStatisticsMapper.selectCount(lambdaQueryWrapper);
            map.put("count", count);
        }
        lambdaQueryWrapper.last("order by " + orderByStr + " " + sc + " limit " + startNum + "," + pageSize);
        List<VideoDyDataStatisticsDO> videoDyDataStatisticsDOS = videoDyDataStatisticsMapper.selectList(lambdaQueryWrapper);

        if (CollectionUtil.isEmpty(videoDyDataStatisticsDOS)) {
            return respVOList;
        }
        this.buildAnalyzeResp(respVOList, startTime, videoDyDataStatisticsDOS);
        return respVOList;
    }

    private void buildAnalyzeResp(List<VideoDyDataStatisticsAnalyzeRespVO> respVOList, LocalDate startTime, List<VideoDyDataStatisticsDO> accountDyDataStatisticsDOS) {
        for (VideoDyDataStatisticsDO endStatisticsDO : accountDyDataStatisticsDOS) {
            VideoDyDataStatisticsAnalyzeRespVO statisticsAnalyzeRespVO = VideoDyDataStatisticsConvert.INSTANCE.convert(endStatisticsDO);
            VideoDyDataStatisticsDO startStatisticsDO = videoDyDataStatisticsMapper.selectOne(new LambdaQueryWrapper<VideoDyDataStatisticsDO>()
                    .ge(VideoDyDataStatisticsDO::getStatisticsTime, LocalDateTimeUtil.format(startTime, DateUtils.FORMAT_YEAR_MONTH_DAY))
                    .eq(VideoDyDataStatisticsDO::getDyType, DyUserTypeEnum.SJ.getCode())
                    .eq(VideoDyDataStatisticsDO::getOrderId, endStatisticsDO.getOrderId())
                    .orderByAsc(VideoDyDataStatisticsDO::getStatisticsTime)
                    .last("limit 1")
            );
            if (startStatisticsDO != null) {
                if (startStatisticsDO.getId().equals(endStatisticsDO.getId())) {
                    statisticsAnalyzeRespVO.setPlayCountIncrement(endStatisticsDO.getPlayCount());
                    statisticsAnalyzeRespVO.setDiggCountIncrement(endStatisticsDO.getDiggCount());
                    statisticsAnalyzeRespVO.setCommentCountIncrement(endStatisticsDO.getCommentCount());
                    statisticsAnalyzeRespVO.setShareCountIncrement(endStatisticsDO.getShareCount());
                    statisticsAnalyzeRespVO.setCollectCountIncrement(endStatisticsDO.getCollectCount());
                } else {
                    statisticsAnalyzeRespVO.setPlayCountIncrement(Math.max(endStatisticsDO.getPlayCount() - startStatisticsDO.getPlayCount(), 0));
                    statisticsAnalyzeRespVO.setDiggCountIncrement(Math.max(endStatisticsDO.getDiggCount() - startStatisticsDO.getDiggCount(), 0));
                    statisticsAnalyzeRespVO.setCommentCountIncrement(Math.max(endStatisticsDO.getCommentCount() - startStatisticsDO.getCommentCount(), 0));
                    statisticsAnalyzeRespVO.setShareCountIncrement(Math.max(endStatisticsDO.getShareCount() - startStatisticsDO.getShareCount(), 0));
                    statisticsAnalyzeRespVO.setCollectCountIncrement(Math.max(endStatisticsDO.getCollectCount() - startStatisticsDO.getCollectCount(), 0));
                }

            }
            MatrixTaskOrderDO matrixTaskOrderDO = matrixTaskOrderMapper.selectOne(MatrixTaskOrderDO::getId,endStatisticsDO.getOrderId());
            if(matrixTaskOrderDO!=null){
                statisticsAnalyzeRespVO.setPoiName(matrixTaskOrderDO.getPoiName());
            }
            respVOList.add(statisticsAnalyzeRespVO);
        }
    }

    @Override
    public PageResult<VideoDyDataStatisticsAnalyzeRespVO> getStatisticsForPage(VideoDyDataStatisticsAnalyzePageReqVO videoDyDataStatisticsAnalyzePageReqVO) {
        boolean isAsc = videoDyDataStatisticsAnalyzePageReqVO.getIsAsc();
        Integer dateType = videoDyDataStatisticsAnalyzePageReqVO.getDateType();
        Integer platformType = videoDyDataStatisticsAnalyzePageReqVO.getPlatformType();
        Integer orderBy = videoDyDataStatisticsAnalyzePageReqVO.getOrderBy();
        int pageSize = videoDyDataStatisticsAnalyzePageReqVO.getPageSize();
        int pageNo = videoDyDataStatisticsAnalyzePageReqVO.getPageNo() - 1;
        int startNum = pageNo * pageSize;
        List<Long> dyBindIds = null;
        if (null != videoDyDataStatisticsAnalyzePageReqVO.getGroupId() && null == videoDyDataStatisticsAnalyzePageReqVO.getDyBindId()) {
            List<AccountGroupCorrelationDO> correlationDOS = accountGroupCorrelationMapper.selectList(new LambdaQueryWrapper<AccountGroupCorrelationDO>()
                    .eq(AccountGroupCorrelationDO::getGroupId, videoDyDataStatisticsAnalyzePageReqVO.getGroupId()));
            dyBindIds = correlationDOS.stream().map(AccountGroupCorrelationDO::getDyBindId).collect(Collectors.toList());
        }
        PageResult<VideoDyDataStatisticsAnalyzeRespVO> pageResult = new PageResult<>();

        List<VideoDyDataStatisticsAnalyzeRespVO> respVOList = new ArrayList<>();
        String sc = isAsc ? " asc" : " desc";
        if (StatisticsDateTypeEnum.YESTERDAY.getCode().equals(dateType)) {
            LocalDate statisticsTime = LocalDateTimeUtils.getMinusDaysTime(dateType);
            String orderByStr = "order by " + StatisticsDateOrderByEnum.getEnumByCode(orderBy).getValue() + sc + " limit " + startNum + "," + pageSize;
            LambdaQueryWrapperX<VideoDyDataStatisticsDO> lambdaQueryWrapper = new LambdaQueryWrapperX<VideoDyDataStatisticsDO>()
                    .eq(VideoDyDataStatisticsDO::getStatisticsTime, statisticsTime)
                    .eq(VideoDyDataStatisticsDO::getDyType, DyUserTypeEnum.SJ.getCode())
                    .eq(VideoDyDataStatisticsDO::getPlatformType, platformType)
                    .eqIfPresent(VideoDyDataStatisticsDO::getDyBindId, videoDyDataStatisticsAnalyzePageReqVO.getDyBindId())
                    .inIfPresent(VideoDyDataStatisticsDO::getDyBindId, dyBindIds);
            Long count = videoDyDataStatisticsMapper.selectCount(lambdaQueryWrapper);

            lambdaQueryWrapper.last(orderByStr);
            List<VideoDyDataStatisticsDO> videoDyDataStatisticsDOS = videoDyDataStatisticsMapper.selectList(lambdaQueryWrapper);
            pageResult.setTotal(count);
            if (CollectionUtil.isEmpty(videoDyDataStatisticsDOS)) {
                pageResult.setList(respVOList);
                return pageResult;
            }

            List<VideoDyDataStatisticsAnalyzeRespVO> voList = Binder.convertAndBindRelations(videoDyDataStatisticsDOS, VideoDyDataStatisticsAnalyzeRespVO.class);
            pageResult.setList(voList);
            return pageResult;
        }
        Map<String, Long> map = new HashMap();
        LocalDateTime[] times = videoDyDataStatisticsAnalyzePageReqVO.getStatisticsTime();
        LocalDateTime startTime = times!=null && times.length>0?times[0]:null;
        LocalDateTime endTime = times!=null && times.length>1?times[1]:null;
        List<VideoDyDataStatisticsAnalyzeRespVO> analyzeRespVOS = this.buildMoreDaysData(dateType, platformType, orderBy, videoDyDataStatisticsAnalyzePageReqVO.getDyBindId(), dyBindIds, respVOList, sc, startNum, pageSize, map
        ,startTime, endTime);

        List<VideoDyDataStatisticsAnalyzeRespVO> voList = Binder.convertAndBindRelations(analyzeRespVOS, VideoDyDataStatisticsAnalyzeRespVO.class);
        pageResult.setTotal(map.get("count"));
        pageResult.setList(voList);
        return pageResult;
    }

    @Override
    public VideoDyDataStatisticsDO getBeforeDataByOrderId(Long orderId, Long taskId, Integer type, LocalDate statisticsTime) {
        return videoDyDataStatisticsMapper.selectOne(new LambdaQueryWrapper<VideoDyDataStatisticsDO>()
                .eq(VideoDyDataStatisticsDO::getOrderId, orderId)
                .eq(VideoDyDataStatisticsDO::getTaskId, taskId)
                .eq(VideoDyDataStatisticsDO::getDyType, type)
                .ge(statisticsTime!=null,VideoDyDataStatisticsDO::getStatisticsTime, LocalDateTimeUtil.format(statisticsTime, DateUtils.FORMAT_YEAR_MONTH_DAY))
                .orderByAsc(VideoDyDataStatisticsDO::getStatisticsTime)
                .last("limit 1")
        );
    }

    @Override
    public VideoDyDataStatisticsDO getYesterdayDataByOrderId(Long orderId, Long taskId, Integer type, LocalDate statisticsTime) {
        return videoDyDataStatisticsMapper.selectOne(
                VideoDyDataStatisticsDO::getOrderId, orderId,
                VideoDyDataStatisticsDO::getTaskId, taskId,
                VideoDyDataStatisticsDO::getDyType, type,
                VideoDyDataStatisticsDO::getStatisticsTime, statisticsTime);
    }

    @Override
    public List<VideoDyDataStatisticsDO> getBeforeDataByBindId(Long bindId, LocalDate statisticsTime) {
        return videoDyDataStatisticsMapper.selectList(new LambdaQueryWrapper<VideoDyDataStatisticsDO>()
                .eq(VideoDyDataStatisticsDO::getDyBindId, bindId)
                .eq(statisticsTime!=null,VideoDyDataStatisticsDO::getStatisticsTime, statisticsTime));
    }

    @Override
    public List<VideoDyDataStatisticsDO> getBeforeData(LocalDate statisticsTime, Integer platformType) {
        return videoDyDataStatisticsMapper.selectList(new LambdaQueryWrapper<VideoDyDataStatisticsDO>()
                .eq(VideoDyDataStatisticsDO::getStatisticsTime, statisticsTime)
                .eq(VideoDyDataStatisticsDO::getPlatformType, platformType));
    }
}
