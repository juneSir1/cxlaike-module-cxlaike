package net.hzxzkj.cxlaike.module.cxlaike.service.aivideotaskcover;

import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.aivideotaskcover.vo.AiVideoTaskCoverCreateReqVO;
import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.aivideotaskcover.vo.AiVideoTaskCoverExportReqVO;
import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.aivideotaskcover.vo.AiVideoTaskCoverPageReqVO;
import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.aivideotaskcover.vo.AiVideoTaskCoverUpdateReqVO;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import java.util.*;

import net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.aivideotaskcover.AiVideoTaskCoverDO;
import net.hzxzkj.cxlaike.framework.common.pojo.PageResult;

import net.hzxzkj.cxlaike.module.cxlaike.convert.aivideotaskcover.AiVideoTaskCoverConvert;
import net.hzxzkj.cxlaike.module.cxlaike.dal.mysql.aivideotaskcover.AiVideoTaskCoverMapper;

import static net.hzxzkj.cxlaike.framework.common.exception.util.ServiceExceptionUtil.exception;
import static net.hzxzkj.cxlaike.module.cxlaike.enums.ErrorCodeConstants.DATA_ERROR;

/**
 * ai素材混剪视频封面图片关联 Service 实现类
 *
 * @author 宵征源码
 */
@Service
@Validated
public class AiVideoTaskCoverServiceImpl implements AiVideoTaskCoverService {

    @Resource
    private AiVideoTaskCoverMapper aiVideoTaskCoverMapper;

    @Override
    public Long createAiVideoTaskCover(AiVideoTaskCoverCreateReqVO createReqVO) {
        // 插入
        AiVideoTaskCoverDO aiVideoTaskCover = AiVideoTaskCoverConvert.INSTANCE.convert(createReqVO);
        aiVideoTaskCoverMapper.insert(aiVideoTaskCover);
        // 返回
        return aiVideoTaskCover.getId();
    }

    @Override
    public void updateAiVideoTaskCover(AiVideoTaskCoverUpdateReqVO updateReqVO) {
        // 校验存在
        validateAiVideoTaskCoverExists(updateReqVO.getId());
        // 更新
        AiVideoTaskCoverDO updateObj = AiVideoTaskCoverConvert.INSTANCE.convert(updateReqVO);
        aiVideoTaskCoverMapper.updateById(updateObj);
    }

    @Override
    public void deleteAiVideoTaskCover(Long id) {
        // 校验存在
        validateAiVideoTaskCoverExists(id);
        // 删除
        aiVideoTaskCoverMapper.deleteById(id);
    }

    private void validateAiVideoTaskCoverExists(Long id) {
        if (aiVideoTaskCoverMapper.selectById(id) == null) {
            throw exception(DATA_ERROR);
        }
    }

    @Override
    public AiVideoTaskCoverDO getAiVideoTaskCover(Long id) {
        return aiVideoTaskCoverMapper.selectById(id);
    }

    @Override
    public List<AiVideoTaskCoverDO> getAiVideoTaskCoverList(Collection<Long> ids) {
        return aiVideoTaskCoverMapper.selectBatchIds(ids);
    }

    @Override
    public PageResult<AiVideoTaskCoverDO> getAiVideoTaskCoverPage(AiVideoTaskCoverPageReqVO pageReqVO) {
        return aiVideoTaskCoverMapper.selectPage(pageReqVO);
    }

    @Override
    public List<AiVideoTaskCoverDO> getAiVideoTaskCoverList(AiVideoTaskCoverExportReqVO exportReqVO) {
        return aiVideoTaskCoverMapper.selectList(exportReqVO);
    }

}
