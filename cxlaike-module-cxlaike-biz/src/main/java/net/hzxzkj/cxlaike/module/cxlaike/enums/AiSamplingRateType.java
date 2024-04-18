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
 * @version [版本号, 2023/8/28]
 * @see [相关类/方法]
 * 创建日期: 2023/8/28
 */
@Getter
@AllArgsConstructor
public enum AiSamplingRateType implements IntArrayValuable {

  /**
   * 48k
   */
  KHZ_48(1, 48000),

  /**
   * 24k
   */
  KHZ_24(2, 24000),
  /**
   * 16k
   */
  KHZ_16(3, 16000);

  public static final int[] ARRAYS = Arrays.stream(values()).mapToInt(AiSamplingRateType::getType)
      .toArray();

  /**
   * 状态值
   */
  private final Integer type;
  /**
   * 状态名
   */
  private final Integer value;

  public static AiSamplingRateType valueOfType(Integer type) {
    return ArrayUtil.firstMatch(o -> o.getType().equals(type), values());
  }

  @Override
  public int[] array() {
    return ARRAYS;
  }
}
