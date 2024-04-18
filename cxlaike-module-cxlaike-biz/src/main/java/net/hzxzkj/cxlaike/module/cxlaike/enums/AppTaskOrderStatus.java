package net.hzxzkj.cxlaike.module.cxlaike.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 描   述:
 *
 * @author ace
 * @version [版本号, 2023/9/22]
 * @see [相关类/方法]
 * 创建日期: 2023/9/22
 */
@Getter
@AllArgsConstructor
public enum AppTaskOrderStatus {

  /**
   * 0.进行中
   */
  UNDERWAY(0, "进行中"),

  /**
   * ,1.已结束,
   */
  FINISH(1, "已结束"),
  /**
   * 2.已暂停
   */
  PAUSE(2, "已暂停"),
  /**
   * ,5.已报名
   */
  SIGN_UP(5, "已报名"),
  ;
  private final Integer status;

  private final String value;
}
