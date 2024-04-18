package net.hzxzkj.cxlaike.module.cxlaike.service.aivideotemplate;

import java.util.*;
import javax.validation.*;
import net.hzxzkj.cxlaike.module.cxlaike.controller.admin.aivideotemplate.vo.*;
import net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.aivideotemplate.AiVideoTemplateDO;
import net.hzxzkj.cxlaike.framework.common.pojo.PageResult;
import net.hzxzkj.cxlaike.module.cxlaike.service.aivideotemplate.dto.VideoParamDTO;

/**
 * ai视频模板 Service 接口
 *
 * @author 宵征源码
 */
public interface AiVideoTemplateService {

    /**
     * 创建ai视频模板
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createAiVideoTemplate(@Valid AiVideoTemplateCreateReqVO createReqVO);

    /**
     * 更新ai视频模板
     *
     * @param updateReqVO 更新信息
     */
    void updateAiVideoTemplate(@Valid AiVideoTemplateUpdateReqVO updateReqVO);

    /**
     * 删除ai视频模板
     *
     * @param id 编号
     */
    void deleteAiVideoTemplate(Long id);

    /**
     * 获得ai视频模板
     *
     * @param id 编号
     * @return ai视频模板
     */
    AiVideoTemplateDO getAiVideoTemplate(Long id);

    /**
     * 获得ai视频模板列表
     *
     * @param ids 编号
     * @return ai视频模板列表
     */
    List<AiVideoTemplateDO> getAiVideoTemplateList(Collection<Long> ids);

    /**
     * 获得ai视频模板分页
     *
     * @param pageReqVO 分页查询
     * @return ai视频模板分页
     */
    PageResult<AiVideoTemplateDO> getAiVideoTemplatePage(AiVideoTemplatePageReqVO pageReqVO);

    /**
     * 获得ai视频模板列表, 用于 Excel 导出
     *
     * @param exportReqVO 查询条件
     * @return ai视频模板列表
     */
    List<AiVideoTemplateDO> getAiVideoTemplateList(AiVideoTemplateExportReqVO exportReqVO);


    /**
     * 获得ai视频模板
     *
     * @param type 类型
     * @return ai视频模板
     */
    VideoParamDTO getVideoParamDTOByType(Integer type);




}
