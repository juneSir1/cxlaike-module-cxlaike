package net.hzxzkj.cxlaike.module.cxlaike.handler;

import lombok.extern.slf4j.Slf4j;
import net.hzxzkj.cxlaike.module.cxlaike.service.matrixtask.MatrixTaskService;
import net.hzxzkj.cxlaike.module.notify.dto.TaskHandlerResult;
import net.hzxzkj.cxlaike.module.notify.enums.TaskHandlerEnum;
import net.hzxzkj.cxlaike.module.notify.handler.TaskHandler;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


@Slf4j
@Service
public class UpdateMatrixTaskStatusTaskHandler implements TaskHandler {

  @Resource
  private MatrixTaskService taskService;

  @Override
  public String getType() {
    return TaskHandlerEnum.MATRIX_TASK_UPDATE_STATUS.getValue();
  }

  @Override
  public TaskHandlerResult invoke(String params) {
    try {
      Long matrixTaskId = Long.valueOf(params);
      taskService.updateStatusById(matrixTaskId);
    } catch (Exception e) {
      e.printStackTrace();
      return new TaskHandlerResult(false, e.getMessage());
    }
    return new TaskHandlerResult(true, null);
  }
}
