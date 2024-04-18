package net.hzxzkj.cxlaike.module.cxlaike.service.aivideotemplate;

import cn.hutool.json.JSONUtil;
import net.hzxzkj.cxlaike.framework.mybatis.core.query.LambdaQueryWrapperX;
import net.hzxzkj.cxlaike.module.cxlaike.service.aivideotemplate.dto.VideoParamDTO;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import java.util.*;
import net.hzxzkj.cxlaike.module.cxlaike.controller.admin.aivideotemplate.vo.*;
import net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.aivideotemplate.AiVideoTemplateDO;
import net.hzxzkj.cxlaike.framework.common.pojo.PageResult;

import net.hzxzkj.cxlaike.module.cxlaike.convert.aivideotemplate.AiVideoTemplateConvert;
import net.hzxzkj.cxlaike.module.cxlaike.dal.mysql.aivideotemplate.AiVideoTemplateMapper;

import static net.hzxzkj.cxlaike.framework.common.exception.util.ServiceExceptionUtil.exception;
import static net.hzxzkj.cxlaike.module.cxlaike.enums.ErrorCodeConstants.*;

/**
 * ai视频模板 Service 实现类
 *
 * @author 宵征源码
 */
@Service
@Validated
public class AiVideoTemplateServiceImpl implements AiVideoTemplateService {

    @Resource
    private AiVideoTemplateMapper aiVideoTemplateMapper;

    @Override
    public Long createAiVideoTemplate(AiVideoTemplateCreateReqVO createReqVO) {
        // 插入
        AiVideoTemplateDO aiVideoTemplate = AiVideoTemplateConvert.INSTANCE.convert(createReqVO);
        aiVideoTemplateMapper.insert(aiVideoTemplate);
        // 返回
        return aiVideoTemplate.getId();
    }

    @Override
    @CacheEvict(cacheNames = "system:video:template", key = "#updateReqVO.type")
    public void updateAiVideoTemplate(AiVideoTemplateUpdateReqVO updateReqVO) {
        // 校验存在
        validateAiVideoTemplateExists(updateReqVO.getId());
        // 更新
        AiVideoTemplateDO updateObj = AiVideoTemplateConvert.INSTANCE.convert(updateReqVO);
        aiVideoTemplateMapper.updateById(updateObj);
    }

    @Override
    public void deleteAiVideoTemplate(Long id) {
        // 校验存在
        validateAiVideoTemplateExists(id);
        // 删除
        aiVideoTemplateMapper.deleteById(id);
    }

    private void validateAiVideoTemplateExists(Long id) {
        if (aiVideoTemplateMapper.selectById(id) == null) {
            throw exception(AI_VIDEO_TEMPLATE_NOT_EXISTS);
        }
    }

    @Override
    public AiVideoTemplateDO getAiVideoTemplate(Long id) {
        return aiVideoTemplateMapper.selectById(id);
    }

    @Override
    public List<AiVideoTemplateDO> getAiVideoTemplateList(Collection<Long> ids) {
        return aiVideoTemplateMapper.selectBatchIds(ids);
    }

    @Override
    public PageResult<AiVideoTemplateDO> getAiVideoTemplatePage(AiVideoTemplatePageReqVO pageReqVO) {
        return aiVideoTemplateMapper.selectPage(pageReqVO);
    }

    @Override
    public List<AiVideoTemplateDO> getAiVideoTemplateList(AiVideoTemplateExportReqVO exportReqVO) {
        return aiVideoTemplateMapper.selectList(exportReqVO);
    }

    @Override
//    @Cacheable(cacheNames = "system:video:template", key = "#type", unless = "#result == null")
    public VideoParamDTO getVideoParamDTOByType(Integer type) {
        AiVideoTemplateDO aiVideoTemplateDO = aiVideoTemplateMapper.selectOne(new LambdaQueryWrapperX<AiVideoTemplateDO>()
                .eq(AiVideoTemplateDO::getType, type).orderByDesc(AiVideoTemplateDO::getId));
        if(aiVideoTemplateDO==null){
            return null;
        }
        VideoParamDTO videoParamDTO = JSONUtil.toBean(aiVideoTemplateDO.getValue(), VideoParamDTO.class);
        return videoParamDTO;
    }


}
