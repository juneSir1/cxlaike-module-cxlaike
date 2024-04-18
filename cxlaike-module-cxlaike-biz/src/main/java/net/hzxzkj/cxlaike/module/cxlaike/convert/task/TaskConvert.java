package net.hzxzkj.cxlaike.module.cxlaike.convert.task;

import java.util.List;
import net.hzxzkj.cxlaike.framework.common.pojo.PageResult;
import net.hzxzkj.cxlaike.module.cxlaike.controller.app.task.vo.AppTaskRespVO;
import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.task.vo.TaskCreateReqVO;
import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.task.vo.TaskPageRespVO;
import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.task.vo.TaskRespVO;
import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.task.vo.TaskUpdateReqVO;
import net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.task.TaskDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * 商家发布任务 Convert
 *
 * @author 宵征源码
 */
@Mapper
public interface TaskConvert {

  TaskConvert INSTANCE = Mappers.getMapper(TaskConvert.class);

  TaskDO convert(TaskCreateReqVO bean);

  TaskDO convert(TaskUpdateReqVO bean);

  TaskRespVO convert(TaskDO bean);

  List<TaskRespVO> convertList(List<TaskDO> list);

  PageResult<TaskRespVO> convertPage(PageResult<TaskDO> page);

  PageResult<TaskPageRespVO> convertPage1(PageResult<TaskDO> selectPage);

  AppTaskRespVO convert2(TaskDO bean);
}
