package net.hzxzkj.cxlaike.module.cxlaike.service.task;

import java.util.List;
import javax.validation.Valid;
import net.hzxzkj.cxlaike.framework.common.pojo.PageResult;
import net.hzxzkj.cxlaike.module.cxlaike.controller.app.task.vo.AppPOIRespVO;
import net.hzxzkj.cxlaike.module.cxlaike.controller.app.task.vo.AppTaskPageReqVO;
import net.hzxzkj.cxlaike.module.cxlaike.controller.app.task.vo.AppTaskPageRespVO;
import net.hzxzkj.cxlaike.module.cxlaike.controller.app.task.vo.AppTaskRespVO;
import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.task.vo.TaskCreateReqVO;
import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.task.vo.TaskPageReqVO;
import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.task.vo.TaskPageRespVO;
import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.task.vo.TaskPauseReqVO;
import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.task.vo.TaskRespVO;
import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.task.vo.TaskUpdateReqVO;
import net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.task.TaskDO;
import net.hzxzkj.cxlaike.module.cxlaike.enums.SwitchType;
import net.hzxzkj.cxlaike.module.cxlaike.enums.TaskStatus;

/**
 * 商家发布任务 Service 接口
 *
 * @author 宵征源码
 */
public interface TaskService {

  /**
   * 创建商家发布任务
   *
   * @param createReqVO 创建信息
   * @return 编号
   */
  Long createTask(@Valid TaskCreateReqVO createReqVO);

  /**
   * 更新商家发布任务
   *
   * @param updateReqVO 更新信息
   */
  void updateTask(@Valid TaskUpdateReqVO updateReqVO);

  /**
   * 删除商家发布任务
   *
   * @param id 编号
   */
  void deleteTask(Long id);

  /**
   * 获得商家发布任务
   *
   * @param id 编号
   * @return 商家发布任务
   */
  TaskRespVO getTask(Long id);


  /**
   * 获得商家发布任务分页
   *
   * @param pageReqVO 分页查询
   * @return 商家发布任务分页
   */
  PageResult<TaskPageRespVO> getTaskPage(TaskPageReqVO pageReqVO);

  /**
   * 更新任务状态
   *
   * @param id 编号
   */
  void updateTaskStatus(Long id, SwitchType switchType);

  PageResult<AppTaskPageRespVO> getTaskAppPage(AppTaskPageReqVO pageVO);

  AppTaskRespVO getAppTask(Long id);

  /**
   * 结算商家任务
   *
   * @param taskId
   */
  void settlementMerchantTask(Long taskId);

  List<AppPOIRespVO> getPOIList(Long taskId);

  void pauseTask(TaskPauseReqVO taskPauseReqVO);

  Long getMerchantCountOfPublishTask(List<Long> merchantIds);

  List<TaskDO> getTaskListByMerchantIds(List<Long> merchantIds);
}
