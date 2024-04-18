package net.hzxzkj.cxlaike.module.cxlaike.convert.aivideotaskmaterial;

import java.util.*;

import net.hzxzkj.cxlaike.framework.common.pojo.PageResult;

import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.aivideotaskmaterial.vo.AiMaterialClipVideoTaskMaterialCreateReqVO;
import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.aivideotaskmaterial.vo.AiVideoTaskMaterialCreateReqVO;
import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.aivideotaskmaterial.vo.AiVideoTaskMaterialRespVO;
import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.aivideotaskmaterial.vo.AiVideoTaskMaterialUpdateReqVO;
import net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.aivideotaskmaterial.AiVideoTaskMaterialDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * ai视频任务素材 Convert
 *
 * @author 宵征源码
 */
@Mapper
public interface AiVideoTaskMaterialConvert {

    AiVideoTaskMaterialConvert INSTANCE = Mappers.getMapper(AiVideoTaskMaterialConvert.class);

    AiVideoTaskMaterialDO convert(AiVideoTaskMaterialCreateReqVO bean);

    AiVideoTaskMaterialDO convert(AiVideoTaskMaterialUpdateReqVO bean);

    AiVideoTaskMaterialRespVO convert(AiVideoTaskMaterialDO bean);

    List<AiVideoTaskMaterialRespVO> convertList(List<AiVideoTaskMaterialDO> list);

    List<AiMaterialClipVideoTaskMaterialCreateReqVO> convertList1(List<AiVideoTaskMaterialDO> list);

    AiMaterialClipVideoTaskMaterialCreateReqVO convert1(AiVideoTaskMaterialDO bean);

    PageResult<AiVideoTaskMaterialRespVO> convertPage(PageResult<AiVideoTaskMaterialDO> page);


}
