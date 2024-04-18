package net.hzxzkj.cxlaike.module.cxlaike.enums;

import cn.hutool.core.util.ArrayUtil;
import lombok.AllArgsConstructor;
import lombok.Getter;
import net.hzxzkj.cxlaike.framework.common.core.IntArrayValuable;

import java.util.Arrays;

/**
 * 描   述:
 *
 * @author tkk
 * @version [版本号, 2023/12/16]
 * @see [相关类/方法]
 * 创建日期: 2023/12/16
 */
@Getter
@AllArgsConstructor
public enum RepeatRateType implements IntArrayValuable {

  /**
   * 无重复
   */
  NO(1, "无重复"),
  /**
   * 低重复
   */
  LOW(2, "低重复"),
  /**
   * 高重复
   */
  HIGH(3, "高重复"),


  ;
  public static final int[] ARRAYS = Arrays.stream(values()).mapToInt(RepeatRateType::getType)
      .toArray();

  /**
   * 状态值
   */
  private final Integer type;
  /**
   * 状态名
   */
  private final String value;

  public static RepeatRateType valueOfType(Integer type) {
    return ArrayUtil.firstMatch(o -> o.getType().equals(type), values());
  }

  @Override
  public int[] array() {
    return ARRAYS;
  }
}
