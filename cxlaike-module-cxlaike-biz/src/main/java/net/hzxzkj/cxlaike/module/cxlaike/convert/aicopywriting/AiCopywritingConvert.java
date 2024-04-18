package net.hzxzkj.cxlaike.module.cxlaike.convert.aicopywriting;

import java.util.*;

import net.hzxzkj.cxlaike.framework.common.pojo.PageResult;

import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.aicopywriting.vo.AiCopywritingCreateReqVO;
import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.aicopywriting.vo.AiCopywritingRespVO;
import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.aicopywriting.vo.AiCopywritingUpdateReqVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.aicopywriting.AiCopywritingDO;

/**
 * ai文案 Convert
 *
 * @author 宵征源码
 */
@Mapper
public interface AiCopywritingConvert {

    AiCopywritingConvert INSTANCE = Mappers.getMapper(AiCopywritingConvert.class);

    AiCopywritingDO convert(AiCopywritingCreateReqVO bean);

    AiCopywritingDO convert(AiCopywritingUpdateReqVO bean);

    AiCopywritingRespVO convert(AiCopywritingDO bean);

    List<AiCopywritingRespVO> convertList(List<AiCopywritingDO> list);

    PageResult<AiCopywritingRespVO> convertPage(PageResult<AiCopywritingDO> page);



}
