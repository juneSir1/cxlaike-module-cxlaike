package net.hzxzkj.cxlaike.module.cxlaike.handler;

import lombok.extern.slf4j.Slf4j;
import net.hzxzkj.cxlaike.framework.common.util.json.JsonUtils;
import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.matrixtaskorder.vo.MatrixTaskOrderCancelDTO;
import net.hzxzkj.cxlaike.module.cxlaike.service.matrixtaskorder.MatrixTaskOrderService;
import net.hzxzkj.cxlaike.module.notify.dto.TaskHandlerResult;
import net.hzxzkj.cxlaike.module.notify.enums.TaskHandlerEnum;
import net.hzxzkj.cxlaike.module.notify.handler.TaskHandler;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 取消矩阵任务
 */
@Slf4j
@Service
public class MatrixTaskOrderCancelTaskHandler implements TaskHandler {

  @Resource
  private MatrixTaskOrderService taskOrderService;

  @Override
  public String getType() {
    return TaskHandlerEnum.MATRIX_TASK_ORDER_CANCEL.getValue();
  }

  @Override
  public TaskHandlerResult invoke(String params) {
    try {
      MatrixTaskOrderCancelDTO cancelDTO = JsonUtils.parseObject(params, MatrixTaskOrderCancelDTO.class);
      taskOrderService.matrixTaskOrderCancel(cancelDTO);
    } catch (Exception e) {
      e.printStackTrace();
      return new TaskHandlerResult(false, e.getMessage());
    }
    return new TaskHandlerResult(true, null);
  }
}
