package net.hzxzkj.cxlaike.module.cxlaike.handler;

import javax.annotation.Resource;

import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import net.hzxzkj.cxlaike.module.cxlaike.service.taskorder.TaskOrderService;
import net.hzxzkj.cxlaike.module.notify.dto.TaskHandlerResult;
import net.hzxzkj.cxlaike.module.notify.enums.TaskHandlerEnum;
import net.hzxzkj.cxlaike.module.notify.handler.TaskHandler;
import net.hzxzkj.cxlaike.module.pay.api.walletorder.dto.UpMemberCashOrderDTO;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

/**
 * 描   述:
 *
 * @author ace
 * @version [版本号, 2023/9/12]
 * @see [相关类/方法]
 * 创建日期: 2023/9/12
 */
@Slf4j
@Service
public class UpMemberCashOrderTaskHandler implements TaskHandler {

  @Lazy
  @Resource
  private TaskOrderService taskOrderService;

  @Override
  public String getType() {
    return TaskHandlerEnum.UP_MEMBER_CASH_ORDER.getValue();
  }

  @Override
  public TaskHandlerResult invoke(String params) {

    try {
      UpMemberCashOrderDTO order = JSONUtil.toBean(params, UpMemberCashOrderDTO.class);
      taskOrderService.processOrder(order,LocalDate.now());
    } catch (Exception e) {
      e.printStackTrace();
      return new TaskHandlerResult(false, e.getMessage());
    }
    return new TaskHandlerResult(true, null);
  }
}
