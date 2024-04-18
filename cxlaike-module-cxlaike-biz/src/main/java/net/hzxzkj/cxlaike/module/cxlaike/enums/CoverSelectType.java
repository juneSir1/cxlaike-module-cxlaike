package net.hzxzkj.cxlaike.module.cxlaike.enums;

import cn.hutool.core.util.ArrayUtil;
import lombok.AllArgsConstructor;
import lombok.Getter;
import net.hzxzkj.cxlaike.framework.common.core.IntArrayValuable;

import java.util.Arrays;

/**
 * 描   述: 封面选择类型
 *
 * @author tkk
 * @version [版本号, 2023/12/14]
 * @see [相关类/方法]
 * 创建日期: 2023/12/14
 */
@Getter
@AllArgsConstructor
public enum CoverSelectType implements IntArrayValuable {

  /**
   * 智能选择
   */
  SMART_CHOICE(1, "智能选择"),
  /**
   * 自定义
   */
  CUSTOMIZE(2, "自定义");


  private final Integer type;

  private final String value;

  public static final int[] ARRAYS = Arrays.stream(values())
      .mapToInt(CoverSelectType::getType)
      .toArray();


  public static CoverSelectType valueOfType(Integer type) {
    return ArrayUtil.firstMatch(o -> o.getType().equals(type), values());
  }

  @Override
  public int[] array() {
    return ARRAYS;
  }
}
