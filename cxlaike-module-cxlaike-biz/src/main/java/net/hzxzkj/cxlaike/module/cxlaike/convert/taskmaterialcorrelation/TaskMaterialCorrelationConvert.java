package net.hzxzkj.cxlaike.module.cxlaike.convert.taskmaterialcorrelation;

import java.util.*;

import net.hzxzkj.cxlaike.framework.common.pojo.PageResult;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.taskmaterialcorrelation.vo.*;
import net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.taskmaterialcorrelation.TaskMaterialCorrelationDO;

/**
 * 商家任务与素材关联 Convert
 *
 * @author 宵征源码
 */
@Mapper
public interface TaskMaterialCorrelationConvert {

    TaskMaterialCorrelationConvert INSTANCE = Mappers.getMapper(TaskMaterialCorrelationConvert.class);

    TaskMaterialCorrelationDO convert(TaskMaterialCorrelationCreateReqVO bean);

    TaskMaterialCorrelationDO convert(TaskMaterialCorrelationUpdateReqVO bean);

    TaskMaterialCorrelationRespVO convert(TaskMaterialCorrelationDO bean);

    List<TaskMaterialCorrelationRespVO> convertList(List<TaskMaterialCorrelationDO> list);

    PageResult<TaskMaterialCorrelationRespVO> convertPage(PageResult<TaskMaterialCorrelationDO> page);

    List<TaskMaterialCorrelationExcelVO> convertList02(List<TaskMaterialCorrelationDO> list);

}
