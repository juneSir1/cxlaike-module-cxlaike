package net.hzxzkj.cxlaike.module.cxlaike.service.aiclipvideotaskgroup;

import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.aiclipvideotaskgroup.vo.AiClipVideoTaskGroupCreateReqVO;
import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.aiclipvideotaskgroup.vo.AiClipVideoTaskGroupExportReqVO;
import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.aiclipvideotaskgroup.vo.AiClipVideoTaskGroupPageReqVO;
import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.aiclipvideotaskgroup.vo.AiClipVideoTaskGroupUpdateReqVO;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import java.util.*;

import net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.aiclipvideotaskgroup.AiClipVideoTaskGroupDO;
import net.hzxzkj.cxlaike.framework.common.pojo.PageResult;

import net.hzxzkj.cxlaike.module.cxlaike.convert.aiclipvideotaskgroup.AiClipVideoTaskGroupConvert;
import net.hzxzkj.cxlaike.module.cxlaike.dal.mysql.aiclipvideotaskgroup.AiClipVideoTaskGroupMapper;

import static net.hzxzkj.cxlaike.framework.common.exception.util.ServiceExceptionUtil.exception;
import static net.hzxzkj.cxlaike.module.merchant.enums.ErrorCodeConstants.DATA_ERROR;

/**
 * ai素材混剪视频任务-视频组设置 Service 实现类
 *
 * @author 宵征源码
 */
@Service
@Validated
public class AiClipVideoTaskGroupServiceImpl implements AiClipVideoTaskGroupService {

    @Resource
    private AiClipVideoTaskGroupMapper aiClipVideoTaskGroupMapper;

    @Override
    public Long createAiClipVideoTaskGroup(AiClipVideoTaskGroupCreateReqVO createReqVO) {
        // 插入
        AiClipVideoTaskGroupDO aiClipVideoTaskGroup = AiClipVideoTaskGroupConvert.INSTANCE.convert(createReqVO);
        aiClipVideoTaskGroupMapper.insert(aiClipVideoTaskGroup);
        // 返回
        return aiClipVideoTaskGroup.getId();
    }

    @Override
    public void updateAiClipVideoTaskGroup(AiClipVideoTaskGroupUpdateReqVO updateReqVO) {
        // 校验存在
        validateAiClipVideoTaskGroupExists(updateReqVO.getId());
        // 更新
        AiClipVideoTaskGroupDO updateObj = AiClipVideoTaskGroupConvert.INSTANCE.convert(updateReqVO);
        aiClipVideoTaskGroupMapper.updateById(updateObj);
    }

    @Override
    public void deleteAiClipVideoTaskGroup(Long id) {
        // 校验存在
        validateAiClipVideoTaskGroupExists(id);
        // 删除
        aiClipVideoTaskGroupMapper.deleteById(id);
    }

    private void validateAiClipVideoTaskGroupExists(Long id) {
        if (aiClipVideoTaskGroupMapper.selectById(id) == null) {
            throw exception(DATA_ERROR);
        }
    }

    @Override
    public AiClipVideoTaskGroupDO getAiClipVideoTaskGroup(Long id) {
        return aiClipVideoTaskGroupMapper.selectById(id);
    }

    @Override
    public List<AiClipVideoTaskGroupDO> getAiClipVideoTaskGroupList(Collection<Long> ids) {
        return aiClipVideoTaskGroupMapper.selectBatchIds(ids);
    }

    @Override
    public PageResult<AiClipVideoTaskGroupDO> getAiClipVideoTaskGroupPage(AiClipVideoTaskGroupPageReqVO pageReqVO) {
        return aiClipVideoTaskGroupMapper.selectPage(pageReqVO);
    }

    @Override
    public List<AiClipVideoTaskGroupDO> getAiClipVideoTaskGroupList(AiClipVideoTaskGroupExportReqVO exportReqVO) {
        return aiClipVideoTaskGroupMapper.selectList(exportReqVO);
    }

}
