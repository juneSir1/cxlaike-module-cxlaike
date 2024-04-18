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
 * @version [版本号, 2023/9/5]
 * @see [相关类/方法]
 * 创建日期: 2023/9/5
 */
@Getter
@AllArgsConstructor
public enum SysTakeLevelType implements IntArrayValuable {

  /**
   * 云探 - ai剪辑
   */
  YUN_AI_CLIP(1, "ai剪辑"),
  /**
   * 云探 - 数字人混剪
   */
  YUN_DIGITAL_MIX_CLIP(2, "数字人混剪"),
  /**
   * 实探 - 现场拍摄
   */
  ENTITY_SHOOTING(3, "现场拍摄"),
  /**
   * 实探 - 真人出镜
   */
  ENTITY_REAL_SHOOTING(4, "真人出镜");

  private final Integer type;

  private final String value;

  public static final int[] ARRAYS = Arrays.stream(values())
      .mapToInt(SysTakeLevelType::getType)
      .toArray();


  public static SysTakeLevelType valueOfType(Integer type) {
    return ArrayUtil.firstMatch(o -> o.getType().equals(type), values());
  }

  @Override
  public int[] array() {
    return ARRAYS;
  }
}
