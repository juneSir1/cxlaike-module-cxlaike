package net.hzxzkj.cxlaike.module.cxlaike.dal.mysql.aivideotaskfontset;

import java.util.*;

import net.hzxzkj.cxlaike.framework.common.pojo.PageResult;
import net.hzxzkj.cxlaike.framework.mybatis.core.query.LambdaQueryWrapperX;
import net.hzxzkj.cxlaike.framework.mybatis.core.mapper.BaseMapperX;
import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.aivideotaskfontset.vo.AiVideoTaskFontSetExportReqVO;
import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.aivideotaskfontset.vo.AiVideoTaskFontSetPageReqVO;
import net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.aivideotaskfontset.AiVideoTaskFontSetDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * ai视频任务-文字设置 Mapper
 *
 * @author 宵征源码
 */
@Mapper
public interface AiVideoTaskFontSetMapper extends BaseMapperX<AiVideoTaskFontSetDO> {

    default PageResult<AiVideoTaskFontSetDO> selectPage(AiVideoTaskFontSetPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<AiVideoTaskFontSetDO>()
                .eqIfPresent(AiVideoTaskFontSetDO::getAiTaskId, reqVO.getAiTaskId())
                .eqIfPresent(AiVideoTaskFontSetDO::getValue, reqVO.getValue())
                .betweenIfPresent(AiVideoTaskFontSetDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(AiVideoTaskFontSetDO::getId));
    }

    default List<AiVideoTaskFontSetDO> selectList(AiVideoTaskFontSetExportReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<AiVideoTaskFontSetDO>()
                .eqIfPresent(AiVideoTaskFontSetDO::getAiTaskId, reqVO.getAiTaskId())
                .eqIfPresent(AiVideoTaskFontSetDO::getValue, reqVO.getValue())
                .betweenIfPresent(AiVideoTaskFontSetDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(AiVideoTaskFontSetDO::getId));
    }

}
