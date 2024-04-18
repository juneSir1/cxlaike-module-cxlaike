package net.hzxzkj.cxlaike.module.cxlaike.enums;

import cn.hutool.core.util.ArrayUtil;
import java.util.Arrays;
import lombok.AllArgsConstructor;
import lombok.Getter;
import net.hzxzkj.cxlaike.framework.common.core.IntArrayValuable;

/**
 * 描   述:
 *
 * @author ace
 * @version [版本号, 2023/8/23]
 * @see [相关类/方法]
 * 创建日期: 2023/8/23
 */
@Getter
@AllArgsConstructor
public enum AiClipType implements IntArrayValuable {

  /**
   * 1.ai混剪
   */
  AI_CLIP(1, "ai混剪"),
  /**
   * 2.数字人混剪
   */
  DIGITAL_CLIP(2, "数字人混剪"),
  /**
   * 3.纯数字人口播
   */
  DIGITAL_CLIP_ONLY(3, "纯数字人口播"),

  /**
   * 4.试听音频
   */
  AUDIO_CLIP(4, "试听音频"),

  /**
   * 5.ai混剪
   */
  AI_MATERIAL_CLIP(5, "ai素材混剪"),


  ;
  public static final int[] ARRAYS = Arrays.stream(values()).mapToInt(AiClipType::getType)
      .toArray();

  /**
   * 状态值
   */
  private final Integer type;
  /**
   * 状态名
   */
  private final String value;

  public static AiClipType valueOfType(Integer type) {
    return ArrayUtil.firstMatch(o -> o.getType().equals(type), values());
  }

  @Override
  public int[] array() {
    return ARRAYS;
  }
}
