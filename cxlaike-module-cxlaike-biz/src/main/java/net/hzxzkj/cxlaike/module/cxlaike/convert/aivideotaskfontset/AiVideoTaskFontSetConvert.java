package net.hzxzkj.cxlaike.module.cxlaike.convert.aivideotaskfontset;

import java.util.*;

import net.hzxzkj.cxlaike.framework.common.pojo.PageResult;

import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.aivideotaskfontset.vo.AiVideoTaskFontSetCreateReqVO;
import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.aivideotaskfontset.vo.AiVideoTaskFontSetExcelVO;
import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.aivideotaskfontset.vo.AiVideoTaskFontSetRespVO;
import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.aivideotaskfontset.vo.AiVideoTaskFontSetUpdateReqVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.aivideotaskfontset.AiVideoTaskFontSetDO;

/**
 * ai视频任务-文字设置 Convert
 *
 * @author 宵征源码
 */
@Mapper
public interface AiVideoTaskFontSetConvert {

    AiVideoTaskFontSetConvert INSTANCE = Mappers.getMapper(AiVideoTaskFontSetConvert.class);

    AiVideoTaskFontSetDO convert(AiVideoTaskFontSetCreateReqVO bean);

    AiVideoTaskFontSetDO convert(AiVideoTaskFontSetUpdateReqVO bean);

    AiVideoTaskFontSetRespVO convert(AiVideoTaskFontSetDO bean);

    List<AiVideoTaskFontSetRespVO> convertList(List<AiVideoTaskFontSetDO> list);

    PageResult<AiVideoTaskFontSetRespVO> convertPage(PageResult<AiVideoTaskFontSetDO> page);

    List<AiVideoTaskFontSetExcelVO> convertList02(List<AiVideoTaskFontSetDO> list);

}
