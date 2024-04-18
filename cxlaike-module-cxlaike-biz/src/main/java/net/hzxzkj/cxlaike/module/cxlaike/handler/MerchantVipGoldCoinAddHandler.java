package net.hzxzkj.cxlaike.module.cxlaike.handler;

import com.alibaba.fastjson.JSONObject;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import net.hzxzkj.cxlaike.framework.common.util.json.JsonUtils;
import net.hzxzkj.cxlaike.module.cxlaike.api.merchantvip.dto.MerchantUserVipGoldAddDTO;
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
 * @version [版本号, 2023/11/6]
 * @see [相关类/方法]
 * 创建日期: 2023/11/6
 */
@Slf4j
@Service
public class MerchantVipGoldCoinAddHandler implements TaskHandler {
  @Lazy
  @Resource
  private MerchantUserVipPackageService merchantUserVipPackageService;

  @Override
  public String getType() {
    return TaskHandlerEnum.MERCHANT_USER_VIP_GOLD_ADD.getValue();
  }

  @Override
  public TaskHandlerResult invoke(String params) {
    try {
      MerchantUserVipGoldAddDTO merchantUserVipGoldAddDTO = JsonUtils
          .parseObject(params, MerchantUserVipGoldAddDTO.class);
      merchantUserVipPackageService.merchantVipGoldCoinAdd(merchantUserVipGoldAddDTO);
    } catch (Exception e) {
      e.printStackTrace();
      return new TaskHandlerResult(false, e.getMessage());
    }
    return new TaskHandlerResult(true, null);
  }
}
