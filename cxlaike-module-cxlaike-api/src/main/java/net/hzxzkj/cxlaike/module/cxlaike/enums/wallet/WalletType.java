package net.hzxzkj.cxlaike.module.cxlaike.enums.wallet;

import java.util.Arrays;
import lombok.AllArgsConstructor;
import lombok.Getter;
import net.hzxzkj.cxlaike.framework.common.core.IntArrayValuable;

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
public enum WalletType implements IntArrayValuable {

  /**
   * 微信
   */
  WECHAT(1, "微信"),

  /**
   * 支付宝
   */
  ALIPAY(2, "支付宝"),

  /**
   * 银行卡
   */
  BANK_CARD(3, "银行卡"),
  ;


  public static final int[] ARRAYS = Arrays.stream(values())
      .mapToInt(WalletType::getCode)
      .toArray();


  private final Integer code;

  private final String name;


  @Override
  public int[] array() {
    return ARRAYS;
  }
}
