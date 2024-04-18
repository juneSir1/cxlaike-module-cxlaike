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
public enum WithdrawOrderStatus {

  /**
   * 进行中
   */
  PROCESSING(0, "进行中"),

  /**
   * 成功
   */
  SUCCESS(1, "成功"),

  /**
   * 失败
   */
  FAIL(2, "失败"),
  ;


  private final Integer code;

  private final String name;
}
