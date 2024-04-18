package net.hzxzkj.cxlaike.module.cxlaike.dal.mysql.aivideotask;

import java.util.*;

import net.hzxzkj.cxlaike.framework.common.pojo.PageResult;
import net.hzxzkj.cxlaike.framework.mybatis.core.query.LambdaQueryWrapperX;
import net.hzxzkj.cxlaike.framework.mybatis.core.mapper.BaseMapperX;
import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.aivideotask.vo.AiVideoTaskExportReqVO;
import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.aivideotask.vo.AiVideoTaskPageReqVO;
import net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.aivideotask.AiVideoTaskDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * ai视频任务 Mapper
 *
 * @author 宵征源码
 */
@Mapper
public interface AiVideoTaskMapper extends BaseMapperX<AiVideoTaskDO> {

  default PageResult<AiVideoTaskDO> selectPage(AiVideoTaskPageReqVO reqVO) {
    return selectPage(reqVO, new LambdaQueryWrapperX<AiVideoTaskDO>()
        .likeIfPresent(AiVideoTaskDO::getTitle, reqVO.getTitle())
        .inIfPresent(AiVideoTaskDO::getClipType, reqVO.getClipType())
        .eq(AiVideoTaskDO::getIsPreview, false)
        .orderByDesc(AiVideoTaskDO::getId));
  }

  default List<AiVideoTaskDO> selectList(AiVideoTaskExportReqVO reqVO) {
    return selectList(new LambdaQueryWrapperX<AiVideoTaskDO>()
        .eqIfPresent(AiVideoTaskDO::getTitle, reqVO.getTitle())
        .eqIfPresent(AiVideoTaskDO::getStatus, reqVO.getStatus())
        .betweenIfPresent(AiVideoTaskDO::getCreateTime, reqVO.getCreateTime())
        .orderByDesc(AiVideoTaskDO::getId));
  }

  void addCoinNum(@Param("taskId") Long taskId,@Param("coinNum") Long coinNum);

  void addCoinNumAndStatus(@Param("taskId") Long taskId,@Param("coinNum") Long coinNum,@Param("status") Integer status);
}
