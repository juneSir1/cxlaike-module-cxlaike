package net.hzxzkj.cxlaike.module.cxlaike.api.merchantvip.dto;

import java.time.LocalDateTime;
import lombok.Data;

/**
 * 描   述:
 *
 * @author ace
 * @version [版本号, 2023/11/1]
 * @see [相关类/方法]
 * 创建日期: 2023/11/1
 */
@Data
public class MerchantUserVipDTO {

  private Boolean isVip;

  private Long userId;
  /**
   * vip到期时间
   */
  private LocalDateTime expireTime;

  private Long merchantPackageId;

  private String merchantPackageName;
}
