package net.hzxzkj.cxlaike.module.cxlaike.convert.aivideotemplate;

import java.util.*;

import net.hzxzkj.cxlaike.framework.common.pojo.PageResult;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import net.hzxzkj.cxlaike.module.cxlaike.controller.admin.aivideotemplate.vo.*;
import net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.aivideotemplate.AiVideoTemplateDO;

/**
 * ai视频模板 Convert
 *
 * @author 宵征源码
 */
@Mapper
public interface AiVideoTemplateConvert {

    AiVideoTemplateConvert INSTANCE = Mappers.getMapper(AiVideoTemplateConvert.class);

    AiVideoTemplateDO convert(AiVideoTemplateCreateReqVO bean);

    AiVideoTemplateDO convert(AiVideoTemplateUpdateReqVO bean);

    AiVideoTemplateRespVO convert(AiVideoTemplateDO bean);

    List<AiVideoTemplateRespVO> convertList(List<AiVideoTemplateDO> list);

    PageResult<AiVideoTemplateRespVO> convertPage(PageResult<AiVideoTemplateDO> page);

    List<AiVideoTemplateExcelVO> convertList02(List<AiVideoTemplateDO> list);

}
