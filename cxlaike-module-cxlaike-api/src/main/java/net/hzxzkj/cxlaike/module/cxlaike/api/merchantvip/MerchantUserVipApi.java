package net.hzxzkj.cxlaike.module.cxlaike.api.merchantvip;

import net.hzxzkj.cxlaike.module.cxlaike.api.merchantvip.dto.MerchantUserVipDTO;

/**
 * 描   述:
 *
 * @author ace
 * @version [版本号, 2023/11/1]
 * @see [相关类/方法]
 * 创建日期: 2023/11/1
 */
public interface MerchantUserVipApi {

  MerchantUserVipDTO getMerchantUserVip(Long userId);

  void vipInit(Long merchantId);
}
