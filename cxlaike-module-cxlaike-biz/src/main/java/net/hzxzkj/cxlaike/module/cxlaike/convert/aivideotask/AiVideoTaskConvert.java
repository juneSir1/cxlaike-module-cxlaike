package net.hzxzkj.cxlaike.module.cxlaike.convert.aivideotask;

import java.util.*;

import net.hzxzkj.cxlaike.framework.common.pojo.PageResult;

import net.hzxzkj.cxlaike.module.cxlaike.controller.app.config.vo.AppHumanConfigRespVO;
import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.aivideotask.vo.*;
import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.aivideotaskmaterial.vo.AiVideoTaskMaterialBaseVO;
import net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.aivideotaskmaterial.AiVideoTaskMaterialDO;
import net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.metahumanconfig.MetaHumanConfigDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.aivideotask.AiVideoTaskDO;

/**
 * ai视频任务 Convert
 *
 * @author 宵征源码
 */
@Mapper
public interface AiVideoTaskConvert {

  AiVideoTaskConvert INSTANCE = Mappers.getMapper(AiVideoTaskConvert.class);

  AiVideoTaskDO convert(AiVideoTaskCreateReqVO bean);

  AiVideoTaskDO convert(AiVideoTaskUpdateReqVO bean);

  AiVideoTaskRespVO convert(AiVideoTaskDO bean);

  AiMaterialClipVideoTaskRespVO convertToAiMaterialClip(AiVideoTaskDO bean);

  AiMaterialClipVideoTaskVO convertToAiMaterialClipVideoTaskVO(AiVideoTaskDO bean);

  AiMaterialClipVideoTaskRespVO convertToAiMaterialClipVideoTaskRespVO(AiVideoTaskDO bean);

  List<AiVideoTaskRespVO> convertList(List<AiVideoTaskDO> list);

  PageResult<AiVideoTaskRespVO> convertPage(PageResult<AiVideoTaskDO> page);


  List<AiVideoTaskMaterialDO> convertList1(List<AiVideoTaskMaterialBaseVO> materialList);

  List<MetaHumanConfigRespVO> convertList2(List<MetaHumanConfigDO> metaHumanConfig);

  List<AppHumanConfigRespVO> convertList3(List<MetaHumanConfigDO> metaHumanConfig);

  AiVideoTaskPageRespVO convert1(AiVideoTaskDO aiVideoTaskDO);

  AiVideoTaskDO convert2(DigitalClipOnlyVideoCreateReqVO createReqVO);

  AiVideoTaskDO convert3(DigitalClipVideoCreateReqVO createReqVO);

  AiVideoTaskDO convert4(AiClipVideoTaskCreateReqVO createReqVO);

  AiVideoTaskDO convert(AiMaterialClipVideoTaskCreateReqVO bean);
}
