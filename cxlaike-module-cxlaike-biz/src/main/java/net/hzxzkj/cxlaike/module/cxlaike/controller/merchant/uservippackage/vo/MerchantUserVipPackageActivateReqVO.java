package net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.uservippackage.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 描   述:
 *
 * @author ace
 * @version [版本号, 2023/10/31]
 * @see [相关类/方法]
 * 创建日期: 2023/10/31
 */
@Data
public class MerchantUserVipPackageActivateReqVO {

  @Schema(description = "商家vip套餐Id")
  private Long merchantPackageId;

}
