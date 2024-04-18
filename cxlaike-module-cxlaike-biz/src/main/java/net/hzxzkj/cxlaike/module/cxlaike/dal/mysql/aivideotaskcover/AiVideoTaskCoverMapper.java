package net.hzxzkj.cxlaike.module.cxlaike.dal.mysql.aivideotaskcover;

import java.util.*;

import net.hzxzkj.cxlaike.framework.common.pojo.PageResult;
import net.hzxzkj.cxlaike.framework.mybatis.core.query.LambdaQueryWrapperX;
import net.hzxzkj.cxlaike.framework.mybatis.core.mapper.BaseMapperX;
import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.aivideotaskcover.vo.AiVideoTaskCoverExportReqVO;
import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.aivideotaskcover.vo.AiVideoTaskCoverPageReqVO;
import net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.aivideotaskcover.AiVideoTaskCoverDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * ai素材混剪视频封面图片关联 Mapper
 *
 * @author 宵征源码
 */
@Mapper
public interface AiVideoTaskCoverMapper extends BaseMapperX<AiVideoTaskCoverDO> {

    default PageResult<AiVideoTaskCoverDO> selectPage(AiVideoTaskCoverPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<AiVideoTaskCoverDO>()
                .eqIfPresent(AiVideoTaskCoverDO::getTaskId, reqVO.getTaskId())
                .eqIfPresent(AiVideoTaskCoverDO::getFileId, reqVO.getFileId())
                .eqIfPresent(AiVideoTaskCoverDO::getUrl, reqVO.getUrl())
                .eqIfPresent(AiVideoTaskCoverDO::getSort, reqVO.getSort())
                .orderByDesc(AiVideoTaskCoverDO::getId));
    }

    default List<AiVideoTaskCoverDO> selectList(AiVideoTaskCoverExportReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<AiVideoTaskCoverDO>()
                .eqIfPresent(AiVideoTaskCoverDO::getTaskId, reqVO.getTaskId())
                .eqIfPresent(AiVideoTaskCoverDO::getFileId, reqVO.getFileId())
                .eqIfPresent(AiVideoTaskCoverDO::getUrl, reqVO.getUrl())
                .eqIfPresent(AiVideoTaskCoverDO::getSort, reqVO.getSort())
                .orderByDesc(AiVideoTaskCoverDO::getId));
    }

}
