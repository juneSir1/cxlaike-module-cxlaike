package net.hzxzkj.cxlaike.module.cxlaike.handler;

import lombok.extern.slf4j.Slf4j;
import net.hzxzkj.cxlaike.framework.common.util.json.JsonUtils;
import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.matrixtask.vo.MatrixTaskCreateReqVO;
import net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.matrixtaskorder.MatrixTaskOrderDO;
import net.hzxzkj.cxlaike.module.cxlaike.service.matrixtaskorder.MatrixTaskOrderService;
import net.hzxzkj.cxlaike.module.notify.dto.TaskHandlerResult;
import net.hzxzkj.cxlaike.module.notify.enums.TaskHandlerEnum;
import net.hzxzkj.cxlaike.module.notify.handler.TaskHandler;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 创建矩阵任务
 */
@Slf4j
@Service
public class MatrixTaskOrderCreateTaskHandler implements TaskHandler {

  @Resource
  private MatrixTaskOrderService matrixTaskOrderService;

  @Override
  public String getType() {
    return TaskHandlerEnum.MATRIX_TASK_ORDER_PUBLISH.getValue();
  }

  @Override
  public TaskHandlerResult invoke(String params) {
    try {
      matrixTaskOrderService.createMatrixTaskOrder(Long.valueOf(params));
    } catch (Exception e) {
      e.printStackTrace();
      return new TaskHandlerResult(false, e.getMessage());
    }
    return new TaskHandlerResult(true, null);
  }
}
