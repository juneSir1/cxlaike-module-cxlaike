package net.hzxzkj.cxlaike.module.cxlaike.convert.template;

import java.util.*;

import net.hzxzkj.cxlaike.framework.common.pojo.PageResult;

import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.template.vo.TemplateCreateReqVO;
import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.template.vo.TemplateExcelVO;
import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.template.vo.TemplateRespVO;
import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.template.vo.TemplateUpdateReqVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.template.TemplateDO;

/**
 * ai素材混剪视频模板 Convert
 *
 * @author 宵征源码
 */
@Mapper
public interface TemplateConvert {

    TemplateConvert INSTANCE = Mappers.getMapper(TemplateConvert.class);

    TemplateDO convert(TemplateCreateReqVO bean);

    TemplateDO convert(TemplateUpdateReqVO bean);

    TemplateRespVO convert(TemplateDO bean);

    List<TemplateRespVO> convertList(List<TemplateDO> list);

    PageResult<TemplateRespVO> convertPage(PageResult<TemplateDO> page);

    List<TemplateExcelVO> convertList02(List<TemplateDO> list);

}
