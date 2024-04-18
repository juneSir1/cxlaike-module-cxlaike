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
public enum AiAspectRatioType implements IntArrayValuable {

  /**
   * 横屏
   */
  HORIZONTAL(1, "横屏"),
  /**
   * 竖屏
   */
  VERTICAL(2, "竖屏");


  public static final int[] ARRAYS = Arrays.stream(values()).mapToInt(AiAspectRatioType::getType)
      .toArray();

  /**
   * 状态值
   */
  private final Integer type;
  /**
   * X值
   */
  private final String value;


  public static AiAspectRatioType valueOfType(Integer type) {
    return ArrayUtil.firstMatch(o -> o.getType().equals(type), values());
  }

  @Override
  public int[] array() {
    return ARRAYS;
  }
}
