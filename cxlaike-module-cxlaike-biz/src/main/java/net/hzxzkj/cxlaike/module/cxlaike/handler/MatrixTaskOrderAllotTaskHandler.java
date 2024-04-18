package net.hzxzkj.cxlaike.module.cxlaike.handler;

import lombok.extern.slf4j.Slf4j;
import net.hzxzkj.cxlaike.framework.common.util.json.JsonUtils;
import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.matrixtask.vo.MatrixTaskCreateReqVO;
import net.hzxzkj.cxlaike.module.cxlaike.service.matrixtaskorder.MatrixTaskOrderService;
import net.hzxzkj.cxlaike.module.notify.dto.TaskHandlerResult;
import net.hzxzkj.cxlaike.module.notify.enums.TaskHandlerEnum;
import net.hzxzkj.cxlaike.module.notify.handler.TaskHandler;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 分配矩阵任务到各账号
 */
@Slf4j
@Service
public class MatrixTaskOrderAllotTaskHandler implements TaskHandler {

  @Resource
  private MatrixTaskOrderService matrixTaskOrderService;

  @Override
  public String getType() {
    return TaskHandlerEnum.MATRIX_TASK_ORDER_ALLOT.getValue();
  }

  @Override
  public TaskHandlerResult invoke(String params) {
    try {
      MatrixTaskCreateReqVO reqVO = JsonUtils.parseObject(params, MatrixTaskCreateReqVO.class);
      matrixTaskOrderService.allotMatrixTaskOrder(reqVO);
    } catch (Exception e) {
      e.printStackTrace();
      return new TaskHandlerResult(false, e.getMessage());
    }
    return new TaskHandlerResult(true, null);
  }
}
