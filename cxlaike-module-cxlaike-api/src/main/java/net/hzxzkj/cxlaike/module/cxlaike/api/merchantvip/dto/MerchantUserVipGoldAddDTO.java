package net.hzxzkj.cxlaike.module.cxlaike.api.merchantvip.dto;

import lombok.Data;

/**
 * 描   述:
 *
 * @author ace
 * @version [版本号, 2023/11/6]
 * @see [相关类/方法]
 * 创建日期: 2023/11/6
 */
@Data
public class MerchantUserVipGoldAddDTO {

  private Long merchantId;

  private Long number;

  private Long userVipPackageId;

}
