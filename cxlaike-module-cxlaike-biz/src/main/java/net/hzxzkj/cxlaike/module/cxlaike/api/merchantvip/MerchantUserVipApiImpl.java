package net.hzxzkj.cxlaike.module.cxlaike.api.merchantvip;

import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import net.hzxzkj.cxlaike.module.cxlaike.api.merchantvip.dto.MerchantUserVipDTO;
import net.hzxzkj.cxlaike.module.cxlaike.service.uservippackage.MerchantUserVipPackageService;
import org.springframework.stereotype.Service;

/**
 * 描   述:
 *
 * @author ace
 * @version [版本号, 2023/11/1]
 * @see [相关类/方法]
 * 创建日期: 2023/11/1
 */
@Slf4j
@Service
public class MerchantUserVipApiImpl implements MerchantUserVipApi {

  @Resource
  private MerchantUserVipPackageService merchantUserVipPackageService;

  @Override
  public MerchantUserVipDTO getMerchantUserVip(Long userId) {
    return merchantUserVipPackageService.getMerchantUserVip(userId);
  }

  @Override
  public void vipInit(Long merchantId) {
    merchantUserVipPackageService.vipInit(merchantId);
  }
}
