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
public class MerchantUserVipPackageActivateResultRespVO {

  @Schema(description = "支付订单ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "2623")
  private Long payOrderId;
  @Schema(description = "是否支付", example = "0.进行中,1.成功,2.失败")
  private Integer status;

}
