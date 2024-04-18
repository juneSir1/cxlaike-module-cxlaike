package net.hzxzkj.cxlaike.module.cxlaike.api.merchantvip;

import javax.annotation.Resource;
import net.hzxzkj.cxlaike.module.cxlaike.service.uservippackage.MerchantUserVipPackageService;
import org.springframework.stereotype.Service;

/**
 * 描   述:
 *
 * @author ace
 * @version [版本号, 2023/10/31]
 * @see [相关类/方法]
 * 创建日期: 2023/10/31
 */
@Service
public class MerchantUserOrderApiImpl implements MerchantUserOrderApi{

  @Resource
  private MerchantUserVipPackageService merchantUserVipPackageService;

  @Override
  public void updateMerchantUserOrderPaid(Long id, Long payOrderId) {
    merchantUserVipPackageService.updateMerchantUserOrderPaid(id, payOrderId);

  }
}
