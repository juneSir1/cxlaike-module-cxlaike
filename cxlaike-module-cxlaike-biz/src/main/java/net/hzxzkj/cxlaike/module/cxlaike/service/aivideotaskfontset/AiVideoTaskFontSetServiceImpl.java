package net.hzxzkj.cxlaike.module.cxlaike.service.aivideotaskfontset;

import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.aivideotaskfontset.vo.AiVideoTaskFontSetCreateReqVO;
import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.aivideotaskfontset.vo.AiVideoTaskFontSetExportReqVO;
import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.aivideotaskfontset.vo.AiVideoTaskFontSetPageReqVO;
import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.aivideotaskfontset.vo.AiVideoTaskFontSetUpdateReqVO;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import java.util.*;

import net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.aivideotaskfontset.AiVideoTaskFontSetDO;
import net.hzxzkj.cxlaike.framework.common.pojo.PageResult;

import net.hzxzkj.cxlaike.module.cxlaike.convert.aivideotaskfontset.AiVideoTaskFontSetConvert;
import net.hzxzkj.cxlaike.module.cxlaike.dal.mysql.aivideotaskfontset.AiVideoTaskFontSetMapper;

import static net.hzxzkj.cxlaike.framework.common.exception.util.ServiceExceptionUtil.exception;
import static net.hzxzkj.cxlaike.module.merchant.enums.ErrorCodeConstants.DATA_ERROR;

/**
 * ai视频任务-文字设置 Service 实现类
 *
 * @author 宵征源码
 */
@Service
@Validated
public class AiVideoTaskFontSetServiceImpl implements AiVideoTaskFontSetService {

    @Resource
    private AiVideoTaskFontSetMapper aiVideoTaskFontSetMapper;

    @Override
    public Long createAiVideoTaskFontSet(AiVideoTaskFontSetCreateReqVO createReqVO) {
        // 插入
        AiVideoTaskFontSetDO aiVideoTaskFontSet = AiVideoTaskFontSetConvert.INSTANCE.convert(createReqVO);
        aiVideoTaskFontSetMapper.insert(aiVideoTaskFontSet);
        // 返回
        return aiVideoTaskFontSet.getId();
    }

    @Override
    public void updateAiVideoTaskFontSet(AiVideoTaskFontSetUpdateReqVO updateReqVO) {
        // 校验存在
        validateAiVideoTaskFontSetExists(updateReqVO.getId());
        // 更新
        AiVideoTaskFontSetDO updateObj = AiVideoTaskFontSetConvert.INSTANCE.convert(updateReqVO);
        aiVideoTaskFontSetMapper.updateById(updateObj);
    }

    @Override
    public void deleteAiVideoTaskFontSet(Long id) {
        // 校验存在
        validateAiVideoTaskFontSetExists(id);
        // 删除
        aiVideoTaskFontSetMapper.deleteById(id);
    }

    private void validateAiVideoTaskFontSetExists(Long id) {
        if (aiVideoTaskFontSetMapper.selectById(id) == null) {
            throw exception(DATA_ERROR);
        }
    }

    @Override
    public AiVideoTaskFontSetDO getAiVideoTaskFontSet(Long id) {
        return aiVideoTaskFontSetMapper.selectById(id);
    }

    @Override
    public List<AiVideoTaskFontSetDO> getAiVideoTaskFontSetList(Collection<Long> ids) {
        return aiVideoTaskFontSetMapper.selectBatchIds(ids);
    }

    @Override
    public PageResult<AiVideoTaskFontSetDO> getAiVideoTaskFontSetPage(AiVideoTaskFontSetPageReqVO pageReqVO) {
        return aiVideoTaskFontSetMapper.selectPage(pageReqVO);
    }

    @Override
    public List<AiVideoTaskFontSetDO> getAiVideoTaskFontSetList(AiVideoTaskFontSetExportReqVO exportReqVO) {
        return aiVideoTaskFontSetMapper.selectList(exportReqVO);
    }

}
