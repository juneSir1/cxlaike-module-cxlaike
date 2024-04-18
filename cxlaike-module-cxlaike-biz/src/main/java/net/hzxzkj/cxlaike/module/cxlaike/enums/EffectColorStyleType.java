package net.hzxzkj.cxlaike.module.cxlaike.enums;

import cn.hutool.core.util.ArrayUtil;
import lombok.AllArgsConstructor;
import lombok.Getter;
import net.hzxzkj.cxlaike.framework.common.core.IntArrayValuable;

import java.util.Arrays;

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
public enum EffectColorStyleType implements IntArrayValuable {

  /**
   * 动态花字
   */

  DYNAMIC(1, "动态花字"),

  /**
   * 常规字幕
   */
  NORMAL(2, "常规字幕");

  public static final int[] ARRAYS = Arrays.stream(values()).mapToInt(EffectColorStyleType::getType)
      .toArray();

  /**
   * 状态值
   */
  private final Integer type;
  /**
   * X值
   */
  private final String value;


  public static EffectColorStyleType valueOfType(Integer type) {
    return ArrayUtil.firstMatch(o -> o.getType().equals(type), values());
  }

  @Override
  public int[] array() {
    return ARRAYS;
  }
}
