package net.hzxzkj.cxlaike.module.cxlaike.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 描   述:
 *
 * @author ace
 * @version [版本号, 2023/9/12]
 * @see [相关类/方法]
 * 创建日期: 2023/9/12
 */
@Getter
@AllArgsConstructor
public enum TaskApplyStatus {

  /**
   * 1-报名未支付
   */
  APPLY_NOT_PAY(1, "报名未支付"),
  /**
   * 2-已支付
   */
  APPLY_PAY(2, "已支付"),
  /**
   * 3-已退款
   */
  APPLY_REFUND(3, "已退款"),
  /**
   * 4-已失效
   */
  APPLY_INVALID(4, "已失效"),
  ;

  private final Integer status;

  private final String value;
}
