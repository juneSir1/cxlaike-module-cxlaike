package net.hzxzkj.cxlaike.module.cxlaike.convert.taskfile;

import java.util.List;

import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.aivideotaskmaterial.vo.AiMaterialClipVideoTaskMaterialCreateReqVO;
import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.aivideotaskmaterial.vo.AiVideoTaskMaterialBaseVO;
import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.taskmaterial.vo.TaskMaterialVO;
import net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.taskfile.TaskFileDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * 来客任务文件 Convert
 *
 * @author 宵征源码
 */
@Mapper
public interface TaskFileConvert {

  TaskFileConvert INSTANCE = Mappers.getMapper(TaskFileConvert.class);

  List<TaskFileDO> convertList(List<TaskMaterialVO> materialList);

  List<TaskMaterialVO> convertList1(List<TaskFileDO> selectList);

  List<TaskFileDO> convertList2(List<AiVideoTaskMaterialBaseVO> materialList);

  List<AiVideoTaskMaterialBaseVO> convertList3(List<TaskFileDO> taskFileList);

  List<TaskFileDO> convertList4(List<AiMaterialClipVideoTaskMaterialCreateReqVO> materialList);
}
