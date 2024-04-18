package net.hzxzkj.cxlaike.module.cxlaike.dal.mysql.aivideoresult;

import java.util.*;

import net.hzxzkj.cxlaike.framework.common.pojo.PageResult;
import net.hzxzkj.cxlaike.framework.mybatis.core.query.LambdaQueryWrapperX;
import net.hzxzkj.cxlaike.framework.mybatis.core.mapper.BaseMapperX;
import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.aivideoresult.vo.AiVideoResultExportReqVO;
import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.aivideoresult.vo.AiVideoResultPageReqVO;
import net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.aivideoresult.AiVideoResultDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * ai视频生成结果 Mapper
 *
 * @author 宵征源码
 */
@Mapper
public interface AiVideoResultMapper extends BaseMapperX<AiVideoResultDO> {

    default PageResult<AiVideoResultDO> selectPage(AiVideoResultPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<AiVideoResultDO>()
                .eqIfPresent(AiVideoResultDO::getAiTaskId, reqVO.getAiTaskId())
                .eqIfPresent(AiVideoResultDO::getMediaUrl, reqVO.getMediaUrl())
                .eqIfPresent(AiVideoResultDO::getStatus, reqVO.getStatus())
                .betweenIfPresent(AiVideoResultDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(AiVideoResultDO::getId));
    }

    default List<AiVideoResultDO> selectList(AiVideoResultExportReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<AiVideoResultDO>()
                .eqIfPresent(AiVideoResultDO::getAiTaskId, reqVO.getAiTaskId())
                .eqIfPresent(AiVideoResultDO::getMediaUrl, reqVO.getMediaUrl())
                .eqIfPresent(AiVideoResultDO::getStatus, reqVO.getStatus())
                .betweenIfPresent(AiVideoResultDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(AiVideoResultDO::getId));
    }

    default int updateByTaskId(AiVideoResultDO entity) {
        return update(entity, new LambdaQueryWrapperX<AiVideoResultDO>()
                .eq(AiVideoResultDO::getAiTaskId, entity.getAiTaskId()));
    }

}
