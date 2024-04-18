package net.hzxzkj.cxlaike.module.cxlaike.dal.mysql.taskmaterialcorrelation;

import java.util.*;

import net.hzxzkj.cxlaike.framework.common.pojo.PageResult;
import net.hzxzkj.cxlaike.framework.mybatis.core.query.LambdaQueryWrapperX;
import net.hzxzkj.cxlaike.framework.mybatis.core.mapper.BaseMapperX;
import net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.taskmaterialcorrelation.TaskMaterialCorrelationDO;
import org.apache.ibatis.annotations.Mapper;
import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.taskmaterialcorrelation.vo.*;

/**
 * 商家任务与素材关联 Mapper
 *
 * @author 宵征源码
 */
@Mapper
public interface TaskMaterialCorrelationMapper extends BaseMapperX<TaskMaterialCorrelationDO> {

    default PageResult<TaskMaterialCorrelationDO> selectPage(TaskMaterialCorrelationPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<TaskMaterialCorrelationDO>()
                .eqIfPresent(TaskMaterialCorrelationDO::getTaskType, reqVO.getTaskType())
                .eqIfPresent(TaskMaterialCorrelationDO::getPlatformType, reqVO.getPlatformType())
                .eqIfPresent(TaskMaterialCorrelationDO::getTaskId, reqVO.getTaskId())
                .eqIfPresent(TaskMaterialCorrelationDO::getMaterialId, reqVO.getMaterialId())
                .eqIfPresent(TaskMaterialCorrelationDO::getTaskStatus, reqVO.getTaskStatus())
                .betweenIfPresent(TaskMaterialCorrelationDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(TaskMaterialCorrelationDO::getId));
    }

    default List<TaskMaterialCorrelationDO> selectList(TaskMaterialCorrelationExportReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<TaskMaterialCorrelationDO>()
                .eqIfPresent(TaskMaterialCorrelationDO::getTaskType, reqVO.getTaskType())
                .eqIfPresent(TaskMaterialCorrelationDO::getPlatformType, reqVO.getPlatformType())
                .eqIfPresent(TaskMaterialCorrelationDO::getTaskId, reqVO.getTaskId())
                .eqIfPresent(TaskMaterialCorrelationDO::getMaterialId, reqVO.getMaterialId())
                .eqIfPresent(TaskMaterialCorrelationDO::getTaskStatus, reqVO.getTaskStatus())
                .betweenIfPresent(TaskMaterialCorrelationDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(TaskMaterialCorrelationDO::getId));
    }

}
