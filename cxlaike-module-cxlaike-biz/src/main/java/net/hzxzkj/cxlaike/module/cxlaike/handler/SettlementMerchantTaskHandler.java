package net.hzxzkj.cxlaike.module.cxlaike.handler;

import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import net.hzxzkj.cxlaike.module.cxlaike.service.task.TaskService;
import net.hzxzkj.cxlaike.module.notify.dto.TaskHandlerResult;
import net.hzxzkj.cxlaike.module.notify.enums.TaskHandlerEnum;
import net.hzxzkj.cxlaike.module.notify.handler.TaskHandler;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

/**
 * 描   述:
 *
 * @author ace
 * @version [版本号, 2023/9/11]
 * @see [相关类/方法]
 * 创建日期: 2023/9/11
 */
@Slf4j
@Service
public class SettlementMerchantTaskHandler implements TaskHandler {

  @Lazy
  @Resource
  private TaskService taskService;

  @Override
  public String getType() {
    return TaskHandlerEnum.SETTLEMENT_MERCHANT_TASK.getValue();
  }

  @Override
  public TaskHandlerResult invoke(String params) {
    try {
      Long taskId = Long.valueOf(params);
      taskService.settlementMerchantTask(taskId);
    } catch (Exception e) {
      e.printStackTrace();
      return new TaskHandlerResult(false, e.getMessage());
    }
    return new TaskHandlerResult(true, null);
  }
}
