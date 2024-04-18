package net.hzxzkj.cxlaike.module.cxlaike.dal.mysql.taskmaterial;

import net.hzxzkj.cxlaike.framework.common.pojo.PageResult;
import net.hzxzkj.cxlaike.framework.mybatis.core.mapper.BaseMapperX;
import net.hzxzkj.cxlaike.framework.mybatis.core.query.LambdaQueryWrapperX;
import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.taskmaterial.vo.TaskMaterialPageReqVO;
import net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.taskmaterial.TaskMaterialDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 商家任务素材 Mapper
 *
 * @author 宵征源码
 */
@Mapper
public interface TaskMaterialMapper extends BaseMapperX<TaskMaterialDO> {

    default PageResult<TaskMaterialDO> selectPage(TaskMaterialPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<TaskMaterialDO>()
                .eqIfPresent(TaskMaterialDO::getTaskId, reqVO.getTaskId())
                .eqIfPresent(TaskMaterialDO::getType, reqVO.getType())
                .likeIfPresent(TaskMaterialDO::getFolderName, reqVO.getFolderName())
                .eqIfPresent(TaskMaterialDO::getJobId, reqVO.getJobId())
                .eqIfPresent(TaskMaterialDO::getMaterialUrl, reqVO.getMaterialUrl())
                .eqIfPresent(TaskMaterialDO::getStatus, reqVO.getStatus())
                .eqIfPresent(TaskMaterialDO::getSort, reqVO.getSort())
                .betweenIfPresent(TaskMaterialDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(TaskMaterialDO::getId));
    }

    List<TaskMaterialDO> selectByDownload(@Param("taskId")Long taskId,@Param("userId")Long userId);

}
