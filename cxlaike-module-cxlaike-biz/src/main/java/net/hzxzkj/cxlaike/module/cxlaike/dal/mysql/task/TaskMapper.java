package net.hzxzkj.cxlaike.module.cxlaike.dal.mysql.task;

import com.alibaba.druid.util.StringUtils;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import java.util.List;
import net.hzxzkj.cxlaike.framework.common.pojo.PageResult;
import net.hzxzkj.cxlaike.framework.mybatis.core.mapper.BaseMapperX;
import net.hzxzkj.cxlaike.framework.mybatis.core.query.LambdaQueryWrapperX;
import net.hzxzkj.cxlaike.module.cxlaike.controller.app.task.vo.AppTaskPageRespVO;
import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.task.vo.TaskPageReqVO;
import net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.task.TaskDO;
import net.hzxzkj.cxlaike.module.cxlaike.enums.TaskStatus;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 商家发布任务 Mapper
 *
 * @author 宵征源码
 */
@Mapper
public interface TaskMapper extends BaseMapperX<TaskDO> {

  default PageResult<TaskDO> selectPage(TaskPageReqVO reqVO) {
    LambdaQueryWrapper<TaskDO> lambdaQueryWrapper = new LambdaQueryWrapperX<TaskDO>()
        .eqIfPresent(TaskDO::getTaskType, reqVO.getTaskType())
        .eqIfPresent(TaskDO::getPlatformType, reqVO.getPlatformType());

    if (null != reqVO.getStatus()) {
      if (TaskStatus.COMPLETED.getStatus().equals(reqVO.getStatus())) {
        lambdaQueryWrapper.in(TaskDO::getStatus, TaskStatus.COMPLETED.getStatus(),
            TaskStatus.SETTLED.getStatus());
      } else {
        lambdaQueryWrapper.eq(TaskDO::getStatus, reqVO.getStatus());
      }
    }

    if (reqVO.getTime() != null && reqVO.getTime().length == 2) {
      lambdaQueryWrapper.and(
          wrapper -> wrapper.between(TaskDO::getStartTime, reqVO.getTime()[0], reqVO.getTime()[1])
              .or()
              .between(TaskDO::getEndTime, reqVO.getTime()[0], reqVO.getTime()[1]));
    }

    if (!StringUtils.isEmpty(reqVO.getContent())) {
      lambdaQueryWrapper.and(
          i -> i.like(TaskDO::getId, reqVO.getContent())
              .or()
              .like(TaskDO::getTitle, reqVO.getContent()));
    }
    lambdaQueryWrapper.orderByDesc(TaskDO::getId);
    return selectPage(reqVO, lambdaQueryWrapper);

  }


  Page<AppTaskPageRespVO> selectAppPage(
      @Param("title") String title,
      @Param("taskType") List<Integer> taskType,
      @Param("levelType") List<Integer> levelType,
      @Param("serviceFee") List<Long> serviceFee,
      @Param("fansRequire") List<Integer> fansRequire,
      @Param("area") String area,
      @Param("isDesc") Boolean isDesc,
      @Param("professionCode") String professionCode, Page<AppTaskPageRespVO> page);


  Long selectMerchantCountOfPublishTask(@Param("ids") List<Long> merchantIds);
}
