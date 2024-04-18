package net.hzxzkj.cxlaike.module.cxlaike.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 描   述:
 *
 * @author ace
 * @version [版本号, 2023/9/11]
 * @see [相关类/方法]
 * 创建日期: 2023/9/11
 */
@Getter
@AllArgsConstructor
public enum TaskMaterialSliceStatus {
  /**
   * 未用
   */
  UNUSED(0, "未用"),

  /**
   * 已用
   */
  USED(1, "已用"),

  /**
   * 数字人已用
   */
  AI_USED(2, "数字人已用"),
  ;

  private final Integer status;

  private final String value;


}
