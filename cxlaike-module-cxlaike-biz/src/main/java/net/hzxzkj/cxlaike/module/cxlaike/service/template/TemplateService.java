package net.hzxzkj.cxlaike.module.cxlaike.service.template;

import java.util.*;
import javax.validation.*;

import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.template.vo.TemplateCreateReqVO;
import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.template.vo.TemplateExportReqVO;
import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.template.vo.TemplatePageReqVO;
import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.template.vo.TemplateUpdateReqVO;
import net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.template.TemplateDO;
import net.hzxzkj.cxlaike.framework.common.pojo.PageResult;

/**
 * ai素材混剪视频模板 Service 接口
 *
 * @author 宵征源码
 */
public interface TemplateService {

    /**
     * 创建ai素材混剪视频模板
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createTemplate(@Valid TemplateCreateReqVO createReqVO);

    /**
     * 更新ai素材混剪视频模板
     *
     * @param updateReqVO 更新信息
     */
    void updateTemplate(@Valid TemplateUpdateReqVO updateReqVO);

    /**
     * 删除ai素材混剪视频模板
     *
     * @param id 编号
     */
    void deleteTemplate(Long id);


    /**
     * 获得ai素材混剪视频模板
     *
     * @param id 编号
     * @return ai素材混剪视频模板
     */
    TemplateDO getTemplate(Long id);

    /**
     * 获得ai素材混剪视频模板分页
     *
     * @param pageReqVO 分页查询
     * @return ai素材混剪视频模板分页
     */
    PageResult<TemplateDO> getTemplatePage(TemplatePageReqVO pageReqVO);


    void updateTemplateByAiTaskId(TemplateDO templateDO);
}
