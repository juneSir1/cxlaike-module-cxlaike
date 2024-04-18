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
public enum ActiveTaskType {

  /**
   * 探店任务
   */
  SHOPPING(1, "探店任务"),
  /**
   * 剪辑任务
   */
  CLIP(2, "剪辑任务"),
  /**
   * ai素材剪辑任务
   */
  AI_MATERIAL_CLIP(3, "ai素材剪辑任务"),
  ;



  private final Integer type;

  private final String value;

}
