package net.hzxzkj.cxlaike.module.cxlaike.dal.mysql.taskdydatastatistics;

import java.util.*;

import net.hzxzkj.cxlaike.framework.common.pojo.PageResult;
import net.hzxzkj.cxlaike.framework.mybatis.core.query.LambdaQueryWrapperX;
import net.hzxzkj.cxlaike.framework.mybatis.core.mapper.BaseMapperX;
import net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.taskdydatastatistics.TaskDyDataStatisticsDO;
import org.apache.ibatis.annotations.Mapper;
import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.taskdydatastatistics.vo.*;

/**
 * 任务纬度抖音数据统计 Mapper
 *
 * @author 宵征源码
 */
@Mapper
public interface TaskDyDataStatisticsMapper extends BaseMapperX<TaskDyDataStatisticsDO> {

//    default PageResult<TaskDyDataStatisticsDO> selectPage(TaskDyDataStatisticsPageReqVO reqVO) {
//        return selectPage(reqVO, new LambdaQueryWrapperX<TaskDyDataStatisticsDO>()
//                .eqIfPresent(TaskDyDataStatisticsDO::getTaskId, reqVO.getTaskId())
//                .betweenIfPresent(TaskDyDataStatisticsDO::getStatisticsTime, reqVO.getStatisticsTime())
//                .eqIfPresent(TaskDyDataStatisticsDO::getPublishCountIncrement, reqVO.getPublishCountIncrement())
//                .eqIfPresent(TaskDyDataStatisticsDO::getPublishCount, reqVO.getPublishCount())
//                .eqIfPresent(TaskDyDataStatisticsDO::getPlayCountIncrement, reqVO.getPlayCountIncrement())
//                .eqIfPresent(TaskDyDataStatisticsDO::getPlayCount, reqVO.getPlayCount())
//                .eqIfPresent(TaskDyDataStatisticsDO::getDiggCountIncrement, reqVO.getDiggCountIncrement())
//                .eqIfPresent(TaskDyDataStatisticsDO::getDiggCount, reqVO.getDiggCount())
//                .eqIfPresent(TaskDyDataStatisticsDO::getCommentCountIncrement, reqVO.getCommentCountIncrement())
//                .eqIfPresent(TaskDyDataStatisticsDO::getCommentCount, reqVO.getCommentCount())
//                .eqIfPresent(TaskDyDataStatisticsDO::getShareCountIncrement, reqVO.getShareCountIncrement())
//                .eqIfPresent(TaskDyDataStatisticsDO::getShareCount, reqVO.getShareCount())
//                .eqIfPresent(TaskDyDataStatisticsDO::getCollectCountIncrement, reqVO.getCollectCountIncrement())
//                .eqIfPresent(TaskDyDataStatisticsDO::getCollectCount, reqVO.getCollectCount())
//                .betweenIfPresent(TaskDyDataStatisticsDO::getCreateTime, reqVO.getCreateTime())
//                .orderByDesc(TaskDyDataStatisticsDO::getId));
//    }
//
//    default List<TaskDyDataStatisticsDO> selectList(TaskDyDataStatisticsExportReqVO reqVO) {
//        return selectList(new LambdaQueryWrapperX<TaskDyDataStatisticsDO>()
//                .eqIfPresent(TaskDyDataStatisticsDO::getTaskId, reqVO.getTaskId())
//                .betweenIfPresent(TaskDyDataStatisticsDO::getStatisticsTime, reqVO.getStatisticsTime())
//                .eqIfPresent(TaskDyDataStatisticsDO::getPublishCountIncrement, reqVO.getPublishCountIncrement())
//                .eqIfPresent(TaskDyDataStatisticsDO::getPublishCount, reqVO.getPublishCount())
//                .eqIfPresent(TaskDyDataStatisticsDO::getPlayCountIncrement, reqVO.getPlayCountIncrement())
//                .eqIfPresent(TaskDyDataStatisticsDO::getPlayCount, reqVO.getPlayCount())
//                .eqIfPresent(TaskDyDataStatisticsDO::getDiggCountIncrement, reqVO.getDiggCountIncrement())
//                .eqIfPresent(TaskDyDataStatisticsDO::getDiggCount, reqVO.getDiggCount())
//                .eqIfPresent(TaskDyDataStatisticsDO::getCommentCountIncrement, reqVO.getCommentCountIncrement())
//                .eqIfPresent(TaskDyDataStatisticsDO::getCommentCount, reqVO.getCommentCount())
//                .eqIfPresent(TaskDyDataStatisticsDO::getShareCountIncrement, reqVO.getShareCountIncrement())
//                .eqIfPresent(TaskDyDataStatisticsDO::getShareCount, reqVO.getShareCount())
//                .eqIfPresent(TaskDyDataStatisticsDO::getCollectCountIncrement, reqVO.getCollectCountIncrement())
//                .eqIfPresent(TaskDyDataStatisticsDO::getCollectCount, reqVO.getCollectCount())
//                .betweenIfPresent(TaskDyDataStatisticsDO::getCreateTime, reqVO.getCreateTime())
//                .orderByDesc(TaskDyDataStatisticsDO::getId));
//    }

}
