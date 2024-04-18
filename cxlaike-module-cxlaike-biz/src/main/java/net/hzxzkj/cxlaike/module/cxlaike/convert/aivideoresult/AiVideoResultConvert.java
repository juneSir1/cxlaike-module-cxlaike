package net.hzxzkj.cxlaike.module.cxlaike.convert.aivideoresult;

import java.util.*;

import net.hzxzkj.cxlaike.framework.common.pojo.PageResult;

import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.aivideoresult.vo.*;
import net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.aivideoresult.AiVideoResultDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * ai视频生成结果 Convert
 *
 * @author 宵征源码
 */
@Mapper
public interface AiVideoResultConvert {

    AiVideoResultConvert INSTANCE = Mappers.getMapper(AiVideoResultConvert.class);

    AiVideoResultDO convert(AiVideoResultCreateReqVO bean);

    AiVideoResultDO convert(AiVideoResultUpdateReqVO bean);

    AiVideoResultRespVO convert(AiVideoResultDO bean);

    List<AiVideoResultRespVO> convertList(List<AiVideoResultDO> list);

    List<AiMaterialClipVideoResultVO> convertList1(List<AiVideoResultDO> list);

    PageResult<AiVideoResultRespVO> convertPage(PageResult<AiVideoResultDO> page);

    PageResult<AiMaterialClipVideoResultPageRespVO> convertPage1(PageResult<AiVideoResultDO> page);


}
