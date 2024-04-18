package net.hzxzkj.cxlaike.module.cxlaike.service.template;

import net.hzxzkj.cxlaike.framework.mybatis.core.query.LambdaQueryWrapperX;
import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.aivideotask.vo.AiVideoTaskRespVO;
import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.template.vo.TemplateCreateReqVO;
import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.template.vo.TemplateExportReqVO;
import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.template.vo.TemplatePageReqVO;
import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.template.vo.TemplateUpdateReqVO;
import net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.aivideoresult.AiVideoResultDO;
import net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.aivideotask.AiVideoTaskDO;
import net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.aivideotemplate.AiVideoTemplateDO;
import net.hzxzkj.cxlaike.module.cxlaike.dal.mysql.aivideoresult.AiVideoResultMapper;
import net.hzxzkj.cxlaike.module.cxlaike.dal.mysql.aivideotask.AiVideoTaskMapper;
import net.hzxzkj.cxlaike.module.cxlaike.service.aivideotask.AiVideoTaskService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import java.util.*;

import net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.template.TemplateDO;
import net.hzxzkj.cxlaike.framework.common.pojo.PageResult;

import net.hzxzkj.cxlaike.module.cxlaike.convert.template.TemplateConvert;
import net.hzxzkj.cxlaike.module.cxlaike.dal.mysql.template.TemplateMapper;

import static net.hzxzkj.cxlaike.framework.common.exception.util.ServiceExceptionUtil.exception;
import static net.hzxzkj.cxlaike.module.merchant.enums.ErrorCodeConstants.DATA_ERROR;

/**
 * ai素材混剪视频模板 Service 实现类
 *
 * @author 宵征源码
 */
@Service
@Validated
public class TemplateServiceImpl implements TemplateService {

    @Resource
    private TemplateMapper templateMapper;

    @Resource
    private AiVideoTaskMapper aiVideoTaskMapper;

    @Resource
    private AiVideoResultMapper aiVideoResultMapper;

    @Override
    public Long createTemplate(TemplateCreateReqVO createReqVO) {
        // 插入
        TemplateDO template = TemplateConvert.INSTANCE.convert(createReqVO);
        templateMapper.insert(template);
        // 返回
        return template.getId();
    }

    @Override
    public void updateTemplate(TemplateUpdateReqVO updateReqVO) {
        // 校验存在
        TemplateDO template = templateMapper.selectOne(new LambdaQueryWrapperX<TemplateDO>()
                .eq(TemplateDO::getAiTaskId,updateReqVO.getAiTaskId()));
        if(template == null){
            throw exception(DATA_ERROR);
        }
        // 查询视频结果和视频任务值
        AiVideoResultDO aiVideoResultDO = aiVideoResultMapper.selectOne(new LambdaQueryWrapperX<AiVideoResultDO>()
                .eq(AiVideoResultDO::getId,updateReqVO.getVideoId()));
        template.setVideoId(updateReqVO.getVideoId());
        template.setVideoUrl(aiVideoResultDO.getMediaUrl());
        template.setDuration(aiVideoResultDO.getDuration());

        AiVideoTaskDO aiVideoTaskDO = aiVideoTaskMapper.selectOne(new LambdaQueryWrapperX<AiVideoTaskDO>()
                .eq(AiVideoTaskDO::getId,updateReqVO.getAiTaskId()));
        template.setAspectRatioType(aiVideoTaskDO.getAspectRatioType());
        templateMapper.updateById(template);
    }

    @Override
    public void deleteTemplate(Long id) {
        // 校验存在
        validateTemplateExists(id);
        // 删除
        templateMapper.deleteById(id);
    }

    private void validateTemplateExists(Long id) {
        if (templateMapper.selectById(id) == null) {
            throw exception(DATA_ERROR);
        }
    }

    @Override
    public TemplateDO getTemplate(Long id) {
        return templateMapper.selectById(id);
    }

    @Override
    public PageResult<TemplateDO> getTemplatePage(TemplatePageReqVO pageReqVO) {
        return templateMapper.selectPage(pageReqVO);
    }

    @Override
    public void updateTemplateByAiTaskId(TemplateDO updateDO) {
        templateMapper.update(updateDO, new LambdaQueryWrapperX<TemplateDO>()
                .eq(TemplateDO::getAiTaskId,updateDO.getAiTaskId()));
    }

}
