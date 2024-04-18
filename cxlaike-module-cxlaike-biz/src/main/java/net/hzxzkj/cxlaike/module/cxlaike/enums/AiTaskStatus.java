package net.hzxzkj.cxlaike.module.cxlaike.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 描   述:
 *
 * @author ace
 * @version [版本号, 2023/9/19]
 * @see [相关类/方法]
 * 创建日期: 2023/9/19
 */
@Getter
@AllArgsConstructor
public enum AiTaskStatus {
  /**
   * 完成
   */
  COMPLETE(0, "完成"),
  /**
   * 生成中
   */
  GENERATING(1, "生成中");

  /**
   * 状态值
   */
  private final Integer status;
  /**
   * 状态名
   */
  private final String value;
}
