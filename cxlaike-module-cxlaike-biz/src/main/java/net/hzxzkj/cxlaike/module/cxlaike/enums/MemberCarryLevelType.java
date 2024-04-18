package net.hzxzkj.cxlaike.module.cxlaike.enums;

import cn.hutool.core.util.ArrayUtil;
import java.util.Arrays;
import lombok.AllArgsConstructor;
import lombok.Getter;
import net.hzxzkj.cxlaike.framework.common.core.IntArrayValuable;

/**
 * 描   述: 达人带货等级
 *
 * @author ace
 * @version [版本号, 2023/9/5]
 * @see [相关类/方法]
 * 创建日期: 2023/9/5
 */
@Getter
@AllArgsConstructor
public enum MemberCarryLevelType implements IntArrayValuable {

  LEVEL0(0, "lv0"),

  LEVEL1(1, "lv1"),

  LEVEL2(2, "lv2"),

  LEVEL3(3, "lv3"),

  LEVEL4(4, "lv4"),

  LEVEL5(5, "lv5"),

  LEVEL6(6, "lv6"),

  LEVEL7(7, "lv7"),
  ;

  private final Integer type;

  private final String value;

  public static final int[] ARRAYS = Arrays.stream(values())
      .mapToInt(MemberCarryLevelType::getType)
      .toArray();


  public static MemberCarryLevelType valueOfType(Integer type) {
    return ArrayUtil.firstMatch(o -> o.getType().equals(type), values());
  }

  @Override
  public int[] array() {
    return ARRAYS;
  }
}
