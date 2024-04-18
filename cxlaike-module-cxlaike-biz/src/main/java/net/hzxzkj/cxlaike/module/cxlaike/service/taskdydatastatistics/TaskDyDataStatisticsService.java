package net.hzxzkj.cxlaike.module.cxlaike.service.taskdydatastatistics;

import com.diboot.core.service.BaseService;
import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.taskdydatastatistics.vo.TaskDyDataStatisticsAnalyzeRespVO;
import net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.taskdydatastatistics.TaskDyDataStatisticsDO;

import java.time.LocalDate;
import java.util.List;

/**
 * 任务纬度抖音数据统计 Service 接口
 *
 * @author 宵征源码
 */
public interface TaskDyDataStatisticsService extends BaseService<TaskDyDataStatisticsDO> {

    List<TaskDyDataStatisticsAnalyzeRespVO> getStatisticsForAnalyze(Integer dateType,Integer platformType,Integer orderBy,Boolean isAsc);

    TaskDyDataStatisticsDO getYesterdayDataByTaskId(Long taskId, LocalDate statisticsTime);

    TaskDyDataStatisticsDO getBeforeDataByTaskId(Long taskId, LocalDate statisticsTime);

}
