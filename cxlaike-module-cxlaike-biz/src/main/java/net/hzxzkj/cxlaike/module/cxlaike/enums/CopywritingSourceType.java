package net.hzxzkj.cxlaike.module.cxlaike.enums;

import cn.hutool.core.util.ArrayUtil;
import lombok.AllArgsConstructor;
import lombok.Getter;
import net.hzxzkj.cxlaike.framework.common.core.IntArrayValuable;

import java.util.Arrays;

/**
 * 描   述: 字幕文案来源类型
 *
 * @author tkk
 * @version [版本号, 2023/12/14]
 * @see [相关类/方法]
 * 创建日期: 2023/12/14
 */
@Getter
@AllArgsConstructor
public enum CopywritingSourceType implements IntArrayValuable {

  /**
   * 手工添加
   */
  HAND(1, "手工添加"),
  /**
   * 自配音
   */
  SOUND_DISTRIBUTION(2, "自配音");


  private final Integer type;

  private final String value;

  public static final int[] ARRAYS = Arrays.stream(values())
      .mapToInt(CopywritingSourceType::getType)
      .toArray();


  public static CopywritingSourceType valueOfType(Integer type) {
    return ArrayUtil.firstMatch(o -> o.getType().equals(type), values());
  }

  @Override
  public int[] array() {
    return ARRAYS;
  }
}
