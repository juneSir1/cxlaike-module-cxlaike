package net.hzxzkj.cxlaike.module.cxlaike.handler;

import lombok.extern.slf4j.Slf4j;
import net.hzxzkj.cxlaike.framework.common.util.json.JsonUtils;
import net.hzxzkj.cxlaike.module.cxlaike.controller.admin.walletorderwithdraw.vo.TransferDetailReqVO;
import net.hzxzkj.cxlaike.module.cxlaike.service.walletorderwithdraw.WalletOrderWithdrawService;
import net.hzxzkj.cxlaike.module.notify.dto.TaskHandlerResult;
import net.hzxzkj.cxlaike.module.notify.enums.TaskHandlerEnum;
import net.hzxzkj.cxlaike.module.notify.handler.TaskHandler;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


@Slf4j
@Service
public class TransferMoneyTaskHandler implements TaskHandler {

  @Lazy
  @Resource
  private WalletOrderWithdrawService walletOrderWithdrawService;

  @Override
  public String getType() {
    return TaskHandlerEnum.TRANSFER_MONEY.getValue();
  }

  @Override
  public TaskHandlerResult invoke(String params) {

    try {
      TransferDetailReqVO detailReqVOS = JsonUtils.parseObject(params, TransferDetailReqVO.class);
      walletOrderWithdrawService.dealRemit(detailReqVOS);
    } catch (Exception e) {
      e.printStackTrace();
      return new TaskHandlerResult(false, e.getMessage());
    }
    return new TaskHandlerResult(true, null);
  }
}
