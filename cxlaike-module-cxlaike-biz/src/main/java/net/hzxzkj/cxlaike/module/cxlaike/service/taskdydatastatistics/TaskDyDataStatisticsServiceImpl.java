package net.hzxzkj.cxlaike.module.cxlaike.service.taskdydatastatistics;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.LocalDateTimeUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.diboot.core.binding.Binder;
import com.diboot.core.service.impl.BaseServiceImpl;
import net.hzxzkj.cxlaike.framework.common.util.date.DateUtils;
import net.hzxzkj.cxlaike.framework.common.util.date.LocalDateTimeUtils;
import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.taskdydatastatistics.vo.TaskDyDataStatisticsAnalyzeRespVO;
import net.hzxzkj.cxlaike.module.cxlaike.convert.taskdydatastatistics.TaskDyDataStatisticsConvert;
import net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.taskdydatastatistics.TaskDyDataStatisticsDO;
import net.hzxzkj.cxlaike.module.cxlaike.dal.mysql.taskdydatastatistics.TaskDyDataStatisticsMapper;
import net.hzxzkj.cxlaike.module.cxlaike.enums.douyin.DyUserTypeEnum;
import net.hzxzkj.cxlaike.module.cxlaike.enums.statisticsdate.StatisticsDateOrderByEnum;
import net.hzxzkj.cxlaike.module.cxlaike.enums.statisticsdate.StatisticsDateTypeEnum;
import net.hzxzkj.cxlaike.module.cxlaike.enums.task.TaskTypeEnum;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 任务纬度抖音数据统计 Service 实现类
 *
 * @author 宵征源码
 */
@Service
@Validated
public class TaskDyDataStatisticsServiceImpl extends BaseServiceImpl<TaskDyDataStatisticsMapper, TaskDyDataStatisticsDO> implements TaskDyDataStatisticsService {

    @Resource
    private TaskDyDataStatisticsMapper taskDyDataStatisticsMapper;

    @Override
    public List<TaskDyDataStatisticsAnalyzeRespVO> getStatisticsForAnalyze(Integer dateType, Integer platformType, Integer orderBy, Boolean isAsc) {
        List<TaskDyDataStatisticsAnalyzeRespVO> respVOList = new ArrayList<>();
        String sc = isAsc ? " asc" : " desc";
        if (StatisticsDateTypeEnum.YESTERDAY.getCode().equals(dateType)) {
            LocalDate statisticsTime = LocalDateTimeUtils.getMinusDaysTime(dateType);
            String orderByStr = "order by " + StatisticsDateOrderByEnum.getEnumByCode(orderBy).getValue() + sc + " limit 5";
            List<TaskDyDataStatisticsDO> taskDyDataStatisticsDOS = taskDyDataStatisticsMapper.selectList(new LambdaQueryWrapper<TaskDyDataStatisticsDO>()
                    .eq(TaskDyDataStatisticsDO::getStatisticsTime, statisticsTime)
                    .eq(TaskDyDataStatisticsDO::getTaskType, DyUserTypeEnum.SJ.getCode())
                    .eq(TaskDyDataStatisticsDO::getPlatformType, platformType)
                    .last(orderByStr));

            if (CollectionUtil.isEmpty(taskDyDataStatisticsDOS)) {
                return respVOList;
            }

            List<TaskDyDataStatisticsAnalyzeRespVO> voList = Binder.convertAndBindRelations(taskDyDataStatisticsDOS, TaskDyDataStatisticsAnalyzeRespVO.class);
            return voList;
        }
        List<TaskDyDataStatisticsAnalyzeRespVO> analyzeRespVOS = this.buildMoreDaysData(dateType, platformType, orderBy, isAsc, respVOList, sc);

        List<TaskDyDataStatisticsAnalyzeRespVO> voList = Binder.convertAndBindRelations(analyzeRespVOS, TaskDyDataStatisticsAnalyzeRespVO.class);

        return voList;
    }

    @Override
    public TaskDyDataStatisticsDO getYesterdayDataByTaskId(Long taskId, LocalDate statisticsTime) {
        return taskDyDataStatisticsMapper.selectOne(TaskDyDataStatisticsDO::getTaskId, taskId, TaskDyDataStatisticsDO::getStatisticsTime, statisticsTime);
    }

    @Override
    public TaskDyDataStatisticsDO getBeforeDataByTaskId(Long taskId, LocalDate statisticsTime) {
        return taskDyDataStatisticsMapper.selectOne(new LambdaQueryWrapper<TaskDyDataStatisticsDO>()
                .eq(TaskDyDataStatisticsDO::getTaskId, taskId)
                .ge(TaskDyDataStatisticsDO::getStatisticsTime, LocalDateTimeUtil.format(statisticsTime, DateUtils.FORMAT_YEAR_MONTH_DAY))
                .orderByAsc(TaskDyDataStatisticsDO::getStatisticsTime)
                .last("limit 1"));
    }


    private List<TaskDyDataStatisticsAnalyzeRespVO> buildMoreDaysData(Integer dateType, Integer platformType, Integer orderBy, Boolean isAsc, List<TaskDyDataStatisticsAnalyzeRespVO> respVOList, String sc) {
        LocalDate startTime = LocalDateTimeUtils.getMinusDaysTime(dateType);
        LocalDate endTime = LocalDateTimeUtils.getMinusDaysTime(1);
        if (StatisticsDateOrderByEnum.PUBLISH_COUNT.getCode().equals(orderBy)) {
            List<TaskDyDataStatisticsDO> taskDyDataStatisticsDOS = taskDyDataStatisticsMapper.selectList(new LambdaQueryWrapper<TaskDyDataStatisticsDO>()
                    .eq(TaskDyDataStatisticsDO::getStatisticsTime, endTime)
                    .eq(TaskDyDataStatisticsDO::getTaskType, DyUserTypeEnum.SJ.getCode())
                    .eq(TaskDyDataStatisticsDO::getPlatformType, platformType)
                    .last("order by " + StatisticsDateOrderByEnum.PUBLISH_COUNT.getValue() + sc + " limit 5"));
            if (CollectionUtil.isEmpty(taskDyDataStatisticsDOS)) {
                return respVOList;
            }
            this.buildAnalyzeResp(respVOList, startTime, taskDyDataStatisticsDOS);
            return respVOList;
        } else if (StatisticsDateOrderByEnum.PLAY_COUNT.getCode().equals(orderBy)) {
            List<TaskDyDataStatisticsDO> taskDyDataStatisticsDOS = taskDyDataStatisticsMapper.selectList(new LambdaQueryWrapper<TaskDyDataStatisticsDO>()
                    .eq(TaskDyDataStatisticsDO::getStatisticsTime, endTime)
                    .eq(TaskDyDataStatisticsDO::getTaskType, DyUserTypeEnum.SJ.getCode())
                    .eq(TaskDyDataStatisticsDO::getPlatformType, platformType)
                    .last("order by " + StatisticsDateOrderByEnum.PLAY_COUNT.getValue() + sc + " limit 5"));
            if (CollectionUtil.isEmpty(taskDyDataStatisticsDOS)) {
                return respVOList;
            }
            this.buildAnalyzeResp(respVOList, startTime, taskDyDataStatisticsDOS);
            return respVOList;
        } else {
            List<TaskDyDataStatisticsDO> taskDyDataStatisticsDOS = taskDyDataStatisticsMapper.selectList(new LambdaQueryWrapper<TaskDyDataStatisticsDO>()
                    .eq(TaskDyDataStatisticsDO::getStatisticsTime, endTime)
                    .eq(TaskDyDataStatisticsDO::getTaskType, DyUserTypeEnum.SJ.getCode())
                    .eq(TaskDyDataStatisticsDO::getPlatformType, platformType));
            if (CollectionUtil.isEmpty(taskDyDataStatisticsDOS)) {
                return respVOList;
            }
            this.buildAnalyzeResp(respVOList, startTime, taskDyDataStatisticsDOS);
            Comparator<TaskDyDataStatisticsAnalyzeRespVO> comparator = Comparator.comparing(TaskDyDataStatisticsAnalyzeRespVO::getPlayCountIncrement);
            List<TaskDyDataStatisticsAnalyzeRespVO> analyzeRespVOS;
            if (isAsc) {
                analyzeRespVOS = respVOList.stream().sorted(comparator).limit(5).collect(Collectors.toList());
            } else {
                analyzeRespVOS = respVOList.stream().sorted(comparator.reversed()).limit(5).collect(Collectors.toList());
            }
            return analyzeRespVOS;
        }
    }

    private void buildAnalyzeResp(List<TaskDyDataStatisticsAnalyzeRespVO> respVOList, LocalDate startTime, List<TaskDyDataStatisticsDO> accountDyDataStatisticsDOS) {
        for (TaskDyDataStatisticsDO endStatisticsDO : accountDyDataStatisticsDOS) {
            TaskDyDataStatisticsAnalyzeRespVO statisticsAnalyzeRespVO = TaskDyDataStatisticsConvert.INSTANCE.convert(endStatisticsDO);
            TaskDyDataStatisticsDO startStatisticsDO = taskDyDataStatisticsMapper.selectOne(new LambdaQueryWrapper<TaskDyDataStatisticsDO>()
                    .ge(TaskDyDataStatisticsDO::getStatisticsTime, LocalDateTimeUtil.format(startTime, DateUtils.FORMAT_YEAR_MONTH_DAY))
                    .eq(TaskDyDataStatisticsDO::getTaskType, DyUserTypeEnum.SJ.getCode())
                    .eq(TaskDyDataStatisticsDO::getTaskId, endStatisticsDO.getTaskId())
                    .orderByAsc(TaskDyDataStatisticsDO::getStatisticsTime)
                    .last("limit 1"));
            if (startStatisticsDO != null) {
                if(startStatisticsDO.getId().equals(endStatisticsDO.getId())){
                    statisticsAnalyzeRespVO.setPlayCountIncrement(endStatisticsDO.getPlayCount());
                    statisticsAnalyzeRespVO.setDiggCountIncrement(endStatisticsDO.getDiggCount());
                    statisticsAnalyzeRespVO.setCommentCountIncrement(endStatisticsDO.getCommentCount());
                    statisticsAnalyzeRespVO.setShareCountIncrement(endStatisticsDO.getShareCount());
                    statisticsAnalyzeRespVO.setCollectCountIncrement(endStatisticsDO.getCollectCount());
                }else {
                    statisticsAnalyzeRespVO.setPlayCountIncrement(endStatisticsDO.getPlayCount() - startStatisticsDO.getPlayCount() <= 0 ? 0 : endStatisticsDO.getPlayCount() - startStatisticsDO.getPlayCount());
                    statisticsAnalyzeRespVO.setDiggCountIncrement(endStatisticsDO.getDiggCount() - startStatisticsDO.getDiggCount() <= 0 ? 0 : endStatisticsDO.getDiggCount() - startStatisticsDO.getDiggCount());
                    statisticsAnalyzeRespVO.setCommentCountIncrement(endStatisticsDO.getCommentCount() - startStatisticsDO.getCommentCount() <= 0 ? 0 : endStatisticsDO.getCommentCount() - startStatisticsDO.getCommentCount());
                    statisticsAnalyzeRespVO.setShareCountIncrement(endStatisticsDO.getShareCount() - startStatisticsDO.getShareCount() <= 0 ? 0 : endStatisticsDO.getShareCount() - startStatisticsDO.getShareCount());
                    statisticsAnalyzeRespVO.setCollectCountIncrement(endStatisticsDO.getCollectCount() - startStatisticsDO.getCollectCount() <= 0 ? 0 : endStatisticsDO.getCollectCount() - startStatisticsDO.getCollectCount());
                }
            }
            respVOList.add(statisticsAnalyzeRespVO);
        }
    }

}
