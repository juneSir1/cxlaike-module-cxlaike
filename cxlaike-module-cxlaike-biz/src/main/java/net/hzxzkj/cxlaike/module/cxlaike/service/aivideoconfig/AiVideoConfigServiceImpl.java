package net.hzxzkj.cxlaike.module.cxlaike.service.aivideoconfig;

import net.hzxzkj.cxlaike.framework.common.enums.CommonBooleanEnum;
import net.hzxzkj.cxlaike.framework.common.pojo.PageResult;
import net.hzxzkj.cxlaike.framework.mybatis.core.query.LambdaQueryWrapperX;
import net.hzxzkj.cxlaike.module.cxlaike.controller.admin.aivideoconfig.vo.AiVideoConfigCreateReqVO;
import net.hzxzkj.cxlaike.module.cxlaike.controller.admin.aivideoconfig.vo.AiVideoConfigExportReqVO;
import net.hzxzkj.cxlaike.module.cxlaike.controller.admin.aivideoconfig.vo.AiVideoConfigPageReqVO;
import net.hzxzkj.cxlaike.module.cxlaike.controller.admin.aivideoconfig.vo.AiVideoConfigUpdateReqVO;
import net.hzxzkj.cxlaike.module.cxlaike.convert.aivideoconfig.AiVideoConfigConvert;
import net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.aivideoconfig.AiVideoConfigDO;
import net.hzxzkj.cxlaike.module.cxlaike.dal.mysql.aivideoconfig.AiVideoConfigMapper;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static net.hzxzkj.cxlaike.framework.common.exception.util.ServiceExceptionUtil.exception;
import static net.hzxzkj.cxlaike.module.cxlaike.enums.ErrorCodeConstants.AI_VIDEO_CONFIG_NOT_EXISTS;

/**
 * ai视频配置 Service 实现类
 *
 * @author 宵征源码
 */
@Service
@Validated
public class AiVideoConfigServiceImpl implements AiVideoConfigService {

    @Resource
    private AiVideoConfigMapper aiVideoConfigMapper;

    @Override
    @CacheEvict(cacheNames = "ai:video:config", key = "#createReqVO.type")
    public Long createAiVideoConfig(AiVideoConfigCreateReqVO createReqVO) {
        // 插入
        AiVideoConfigDO aiVideoConfig = AiVideoConfigConvert.INSTANCE.convert(createReqVO);
        aiVideoConfigMapper.insert(aiVideoConfig);
        // 返回
        return aiVideoConfig.getId();
    }

    @Override
    @CacheEvict(cacheNames = "ai:video:config", key = "#updateReqVO.type")
    public void updateAiVideoConfig(AiVideoConfigUpdateReqVO updateReqVO) {
        // 校验存在
        validateAiVideoConfigExists(updateReqVO.getId());
        // 更新
        AiVideoConfigDO updateObj = AiVideoConfigConvert.INSTANCE.convert(updateReqVO);
        aiVideoConfigMapper.updateById(updateObj);
    }

    @Override
    public void deleteAiVideoConfig(Long id) {
        // 校验存在
        validateAiVideoConfigExists(id);
        // 删除
        aiVideoConfigMapper.deleteById(id);
    }

    private void validateAiVideoConfigExists(Long id) {
        if (aiVideoConfigMapper.selectById(id) == null) {
            throw exception(AI_VIDEO_CONFIG_NOT_EXISTS);
        }
    }

    @Override
    public AiVideoConfigDO getAiVideoConfig(Long id) {
        return aiVideoConfigMapper.selectById(id);
    }

    @Override
    public List<AiVideoConfigDO> getAiVideoConfigList(Collection<Long> ids) {
        return aiVideoConfigMapper.selectBatchIds(ids);
    }

    @Override
    public PageResult<AiVideoConfigDO> getAiVideoConfigPage(AiVideoConfigPageReqVO pageReqVO) {
        return aiVideoConfigMapper.selectPage(pageReqVO);
    }

    @Override
    public List<AiVideoConfigDO> getAiVideoConfigList(AiVideoConfigExportReqVO exportReqVO) {
        return aiVideoConfigMapper.selectList(exportReqVO);
    }

    @Override
//    @Cacheable(cacheNames = "ai:video:config", key = "#type")
    public List<String> getAiVideoConfigList(Integer type) {
        return aiVideoConfigMapper.selectList(new LambdaQueryWrapperX<AiVideoConfigDO>().select(AiVideoConfigDO::getValue).eq(AiVideoConfigDO::getType, type).eq(AiVideoConfigDO::getStatus, CommonBooleanEnum.YES.getType())).stream().map(AiVideoConfigDO::getValue).collect(Collectors.toList());
    }

}
