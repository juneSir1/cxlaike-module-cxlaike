package net.hzxzkj.cxlaike.module.cxlaike.enums.merchantvip;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 描   述:
 *
 * @author ace
 * @version [版本号, 2023/10/31]
 * @see [相关类/方法]
 * 创建日期: 2023/10/31
 */

@Getter
@AllArgsConstructor
public enum MerchantVipOrderStatusEnum {


  RUN(0, "进行中"),
  FINISH(1, "成功"),
  FAIL(2, "失败");;

  /**
   * 类型
   */
  private final Integer type;
  /**
   * 名字
   */
  private final String name;
}


