package net.hzxzkj.cxlaike.module.cxlaike.handler;

import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import net.hzxzkj.cxlaike.module.cxlaike.service.uservippackage.MerchantUserVipPackageService;
import net.hzxzkj.cxlaike.module.notify.dto.TaskHandlerResult;
import net.hzxzkj.cxlaike.module.notify.enums.TaskHandlerEnum;
import net.hzxzkj.cxlaike.module.notify.handler.TaskHandler;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

/**
 * 描   述:
 *
 * @author ace
 * @version [版本号, 2023/10/31]
 * @see [相关类/方法]
 * 创建日期: 2023/10/31
 */
@Slf4j
@Service
public class MerchantVipExpireTaskHandler implements TaskHandler {
  @Lazy
  @Resource
  private MerchantUserVipPackageService merchantUserVipPackageService;
  @Override
  public String getType() {
    return TaskHandlerEnum.MERCHANT_VIP_EXPIRE.getValue();
  }

  @Override
  public TaskHandlerResult invoke(String params) {
    try {
      Long userVipPackageId = Long.valueOf(params);
      merchantUserVipPackageService.merchantVipExpire(userVipPackageId);
    } catch (Exception e) {
      e.printStackTrace();
      return new TaskHandlerResult(false, e.getMessage());
    }
    return new TaskHandlerResult(true, null);
  }
}
