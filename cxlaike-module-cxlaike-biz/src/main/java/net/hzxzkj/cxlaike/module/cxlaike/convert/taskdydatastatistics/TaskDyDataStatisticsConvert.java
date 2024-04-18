package net.hzxzkj.cxlaike.module.cxlaike.convert.taskdydatastatistics;

import java.util.*;

import net.hzxzkj.cxlaike.framework.common.pojo.PageResult;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.taskdydatastatistics.vo.*;
import net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.taskdydatastatistics.TaskDyDataStatisticsDO;

/**
 * 任务纬度抖音数据统计 Convert
 *
 * @author 宵征源码
 */
@Mapper
public interface TaskDyDataStatisticsConvert {

    TaskDyDataStatisticsConvert INSTANCE = Mappers.getMapper(TaskDyDataStatisticsConvert.class);

//    TaskDyDataStatisticsDO convert(TaskDyDataStatisticsCreateReqVO bean);
//
//    TaskDyDataStatisticsDO convert(TaskDyDataStatisticsUpdateReqVO bean);
//
//    TaskDyDataStatisticsRespVO convert(TaskDyDataStatisticsDO bean);
//
//    List<TaskDyDataStatisticsRespVO> convertList(List<TaskDyDataStatisticsDO> list);
//
//    PageResult<TaskDyDataStatisticsRespVO> convertPage(PageResult<TaskDyDataStatisticsDO> page);
//
//    List<TaskDyDataStatisticsExcelVO> convertList02(List<TaskDyDataStatisticsDO> list);

    TaskDyDataStatisticsAnalyzeRespVO convert(TaskDyDataStatisticsDO bean);
}
