package net.hzxzkj.cxlaike.module.cxlaike.dal.mysql.aiclipvideotaskgroup;

import java.util.*;

import net.hzxzkj.cxlaike.framework.common.pojo.PageResult;
import net.hzxzkj.cxlaike.framework.mybatis.core.query.LambdaQueryWrapperX;
import net.hzxzkj.cxlaike.framework.mybatis.core.mapper.BaseMapperX;
import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.aiclipvideotaskgroup.vo.AiClipVideoTaskGroupExportReqVO;
import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.aiclipvideotaskgroup.vo.AiClipVideoTaskGroupPageReqVO;
import net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.aiclipvideotaskgroup.AiClipVideoTaskGroupDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * ai素材混剪视频任务-视频组设置 Mapper
 *
 * @author 宵征源码
 */
@Mapper
public interface AiClipVideoTaskGroupMapper extends BaseMapperX<AiClipVideoTaskGroupDO> {

    default PageResult<AiClipVideoTaskGroupDO> selectPage(AiClipVideoTaskGroupPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<AiClipVideoTaskGroupDO>()
                .eqIfPresent(AiClipVideoTaskGroupDO::getAiTaskId, reqVO.getAiTaskId())
                .eqIfPresent(AiClipVideoTaskGroupDO::getCopywritingSourceType, reqVO.getAiTaskId())
                .likeIfPresent(AiClipVideoTaskGroupDO::getName, reqVO.getName())
                .eqIfPresent(AiClipVideoTaskGroupDO::getTotalDuration, reqVO.getTotalDuration())
                .eqIfPresent(AiClipVideoTaskGroupDO::getSort, reqVO.getSort())
                .eqIfPresent(AiClipVideoTaskGroupDO::getNum, reqVO.getNum())
                .eqIfPresent(AiClipVideoTaskGroupDO::getOriginal, reqVO.getOriginal())
                .eqIfPresent(AiClipVideoTaskGroupDO::getDurationType, reqVO.getDurationType())
                .eqIfPresent(AiClipVideoTaskGroupDO::getDuration, reqVO.getDuration())
                .eqIfPresent(AiClipVideoTaskGroupDO::getStatus, reqVO.getStatus())
                .betweenIfPresent(AiClipVideoTaskGroupDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(AiClipVideoTaskGroupDO::getId));
    }

    default List<AiClipVideoTaskGroupDO> selectList(AiClipVideoTaskGroupExportReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<AiClipVideoTaskGroupDO>()
                .eqIfPresent(AiClipVideoTaskGroupDO::getAiTaskId, reqVO.getAiTaskId())
                .eqIfPresent(AiClipVideoTaskGroupDO::getCopywritingSourceType, reqVO.getAiTaskId())
                .likeIfPresent(AiClipVideoTaskGroupDO::getName, reqVO.getName())
                .eqIfPresent(AiClipVideoTaskGroupDO::getTotalDuration, reqVO.getTotalDuration())
                .eqIfPresent(AiClipVideoTaskGroupDO::getSort, reqVO.getSort())
                .eqIfPresent(AiClipVideoTaskGroupDO::getNum, reqVO.getNum())
                .eqIfPresent(AiClipVideoTaskGroupDO::getOriginal, reqVO.getOriginal())
                .eqIfPresent(AiClipVideoTaskGroupDO::getDurationType, reqVO.getDurationType())
                .eqIfPresent(AiClipVideoTaskGroupDO::getDuration, reqVO.getDuration())
                .eqIfPresent(AiClipVideoTaskGroupDO::getStatus, reqVO.getStatus())
                .betweenIfPresent(AiClipVideoTaskGroupDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(AiClipVideoTaskGroupDO::getId));
    }

    default List<AiClipVideoTaskGroupDO> selectListByAiTaskId(Long aiTaskId) {
        return selectList(new LambdaQueryWrapperX<AiClipVideoTaskGroupDO>()
                .eqIfPresent(AiClipVideoTaskGroupDO::getAiTaskId, aiTaskId)
                .orderByAsc(AiClipVideoTaskGroupDO::getSort));
    }

}
