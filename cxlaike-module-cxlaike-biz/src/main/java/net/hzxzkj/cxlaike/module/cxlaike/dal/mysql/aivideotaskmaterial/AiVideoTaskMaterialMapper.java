package net.hzxzkj.cxlaike.module.cxlaike.dal.mysql.aivideotaskmaterial;

import java.util.*;

import net.hzxzkj.cxlaike.framework.common.pojo.PageResult;
import net.hzxzkj.cxlaike.framework.mybatis.core.query.LambdaQueryWrapperX;
import net.hzxzkj.cxlaike.framework.mybatis.core.mapper.BaseMapperX;
import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.aivideotaskmaterial.vo.AiVideoTaskMaterialExportReqVO;
import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.aivideotaskmaterial.vo.AiVideoTaskMaterialPageReqVO;
import net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.aivideotaskmaterial.AiVideoTaskMaterialDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * ai视频任务素材 Mapper
 *
 * @author 宵征源码
 */
@Mapper
public interface AiVideoTaskMaterialMapper extends BaseMapperX<AiVideoTaskMaterialDO> {

    default PageResult<AiVideoTaskMaterialDO> selectPage(AiVideoTaskMaterialPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<AiVideoTaskMaterialDO>()
                .eqIfPresent(AiVideoTaskMaterialDO::getAiTaskId, reqVO.getAiTaskId())
                .orderByDesc(AiVideoTaskMaterialDO::getId));
    }

    default List<AiVideoTaskMaterialDO> selectList(AiVideoTaskMaterialExportReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<AiVideoTaskMaterialDO>()
                .eqIfPresent(AiVideoTaskMaterialDO::getAiTaskId, reqVO.getAiTaskId())
                .orderByDesc(AiVideoTaskMaterialDO::getId));
    }

    default List<AiVideoTaskMaterialDO> selectListByVideoGroupId(Long aiTaskId, Long videoGroupId) {
        return selectList(new LambdaQueryWrapperX<AiVideoTaskMaterialDO>()
                .eqIfPresent(AiVideoTaskMaterialDO::getAiTaskId, aiTaskId)
                .eqIfPresent(AiVideoTaskMaterialDO::getVideoGroupId, videoGroupId)
                .orderByAsc(AiVideoTaskMaterialDO::getSort)
                .orderByAsc(AiVideoTaskMaterialDO::getId));
    }

}
