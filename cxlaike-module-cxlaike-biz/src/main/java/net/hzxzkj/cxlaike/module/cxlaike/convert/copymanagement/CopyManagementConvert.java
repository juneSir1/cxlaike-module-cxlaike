package net.hzxzkj.cxlaike.module.cxlaike.convert.copymanagement;

import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.aivideotask.vo.AiMaterialClipVideoTaskCopyingwritingContentVO;
import net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.copymanagement.CopyManagementDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 任务口播文案管理 Convert
 *
 * @author 宵征源码
 */
@Mapper
public interface CopyManagementConvert {

  CopyManagementConvert INSTANCE = Mappers.getMapper(CopyManagementConvert.class);

  List<AiMaterialClipVideoTaskCopyingwritingContentVO> convert(List<CopyManagementDO> copyManagementDOList);
}
