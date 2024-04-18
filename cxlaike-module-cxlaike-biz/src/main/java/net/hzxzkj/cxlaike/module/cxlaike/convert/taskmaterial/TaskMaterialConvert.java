package net.hzxzkj.cxlaike.module.cxlaike.convert.taskmaterial;

import java.util.*;

import net.hzxzkj.cxlaike.framework.common.pojo.PageResult;

import net.hzxzkj.cxlaike.module.cxlaike.controller.app.taskorder.vo.TaskMaterialListVO;
import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.taskmaterial.vo.TaskMaterialCreateReqVO;
import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.taskmaterial.vo.TaskMaterialRespVO;
import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.taskmaterial.vo.TaskMaterialUpdateReqVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.taskmaterial.TaskMaterialDO;

/**
 * 商家任务素材 Convert
 *
 * @author 宵征源码
 */
@Mapper
public interface TaskMaterialConvert {

    TaskMaterialConvert INSTANCE = Mappers.getMapper(TaskMaterialConvert.class);

    TaskMaterialDO convert(TaskMaterialCreateReqVO bean);

    TaskMaterialDO convert(TaskMaterialUpdateReqVO bean);

    TaskMaterialRespVO convert(TaskMaterialDO bean);

    List<TaskMaterialRespVO> convertList(List<TaskMaterialDO> list);

    PageResult<TaskMaterialRespVO> convertPage(PageResult<TaskMaterialDO> page);

    List<TaskMaterialListVO> convertList01(List<TaskMaterialDO> list);

}
