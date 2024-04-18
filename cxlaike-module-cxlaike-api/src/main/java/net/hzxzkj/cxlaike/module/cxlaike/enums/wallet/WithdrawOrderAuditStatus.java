package net.hzxzkj.cxlaike.module.cxlaike.enums.wallet;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 描   述:
 *
 * @author ace
 * @version [版本号, 2023/9/14]
 * @see [相关类/方法]
 * 创建日期: 2023/9/14
 */
@Getter
@AllArgsConstructor
public enum WithdrawOrderAuditStatus {

  /**
   * 待审核
   */
  WAIT_AUDIT(1, "待审核"),

  /**
   * 审核失败
   */
  AUDIT_NOT_PASS(2, "审核不通过"),

  /**
   * 审核通过
   */
  AUDIT_PASS(3, "审核通过"),

  ;
  private final Integer code;

  private final String name;
}
