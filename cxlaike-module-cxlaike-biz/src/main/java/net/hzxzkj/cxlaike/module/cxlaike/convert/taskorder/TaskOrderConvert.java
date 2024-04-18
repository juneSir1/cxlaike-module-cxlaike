package net.hzxzkj.cxlaike.module.cxlaike.convert.taskorder;

import java.util.*;

import net.hzxzkj.cxlaike.framework.common.pojo.PageResult;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import net.hzxzkj.cxlaike.module.cxlaike.controller.app.taskorder.vo.*;
import net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.taskorder.TaskOrderDO;

/**
 * 达人任务订单 Convert
 *
 * @author 宵征源码
 */
@Mapper
public interface TaskOrderConvert {

    TaskOrderConvert INSTANCE = Mappers.getMapper(TaskOrderConvert.class);

    TaskOrderRespVO convert(TaskOrderDO bean);

    List<TaskOrderRespVO> convertList(List<TaskOrderDO> list);

    PageResult<TaskOrderRespVO> convertPage(PageResult<TaskOrderDO> page);


}
