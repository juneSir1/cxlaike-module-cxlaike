package net.hzxzkj.cxlaike.module.cxlaike.dal.mysql.aitriallisten;

import java.util.*;

import net.hzxzkj.cxlaike.framework.common.pojo.PageResult;
import net.hzxzkj.cxlaike.framework.mybatis.core.query.LambdaQueryWrapperX;
import net.hzxzkj.cxlaike.framework.mybatis.core.mapper.BaseMapperX;
import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.aitriallisten.vo.AiTrialListenExportReqVO;
import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.aitriallisten.vo.AiTrialListenPageReqVO;
import net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.aitriallisten.AiTrialListenDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * ai语音试听功能 Mapper
 *
 * @author 宵征源码
 */
@Mapper
public interface AiTrialListenMapper extends BaseMapperX<AiTrialListenDO> {

    default PageResult<AiTrialListenDO> selectPage(AiTrialListenPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<AiTrialListenDO>()
                .eqIfPresent(AiTrialListenDO::getCopywriting, reqVO.getCopywriting())
                .eqIfPresent(AiTrialListenDO::getDubCode, reqVO.getDubCode())
                .eqIfPresent(AiTrialListenDO::getDubSpeechRate, reqVO.getDubSpeechRate())
                .eqIfPresent(AiTrialListenDO::getDubPitchRate, reqVO.getDubPitchRate())
                .eqIfPresent(AiTrialListenDO::getDubGain, reqVO.getDubGain())
                .eqIfPresent(AiTrialListenDO::getDubSamplingRate, reqVO.getDubSamplingRate())
                .eqIfPresent(AiTrialListenDO::getStatus, reqVO.getStatus())
                .betweenIfPresent(AiTrialListenDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(AiTrialListenDO::getId));
    }

    default List<AiTrialListenDO> selectList(AiTrialListenExportReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<AiTrialListenDO>()
                .eqIfPresent(AiTrialListenDO::getCopywriting, reqVO.getCopywriting())
                .eqIfPresent(AiTrialListenDO::getDubCode, reqVO.getDubCode())
                .eqIfPresent(AiTrialListenDO::getDubSpeechRate, reqVO.getDubSpeechRate())
                .eqIfPresent(AiTrialListenDO::getDubPitchRate, reqVO.getDubPitchRate())
                .eqIfPresent(AiTrialListenDO::getDubGain, reqVO.getDubGain())
                .eqIfPresent(AiTrialListenDO::getDubSamplingRate, reqVO.getDubSamplingRate())
                .eqIfPresent(AiTrialListenDO::getStatus, reqVO.getStatus())
                .betweenIfPresent(AiTrialListenDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(AiTrialListenDO::getId));
    }

}
