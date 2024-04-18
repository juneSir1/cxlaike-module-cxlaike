package net.hzxzkj.cxlaike.module.cxlaike.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 描   述:
 *
 * @author ace
 * @version [版本号, 2023/10/13]
 * @see [相关类/方法]
 * 创建日期: 2023/10/13
 */
@Getter
@AllArgsConstructor
public enum VideoType {

  /**
   * ai剪辑视频
   */
  AI_CLIP(1, "ai剪辑视频"),

  /**
   * 数字人素材
   */
  DIGITAL(2, "数字人素材"),

  /**
   * ai素材混剪
   */
  AI_MATERIAL_CLIP(2, "数字人素材"),


  ;

  private final Integer type;

  private final String value;

}
