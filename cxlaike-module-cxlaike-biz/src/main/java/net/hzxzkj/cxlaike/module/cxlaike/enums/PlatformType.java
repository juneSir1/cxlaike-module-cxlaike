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
 * @version [版本号, 2023/8/30]
 * @see [相关类/方法]
 * 创建日期: 2023/8/30
 */
@Getter
@AllArgsConstructor
public enum PlatformType  implements IntArrayValuable {

  TIKTOK(1,"抖音"),
  KUAISHOU(2,"快手");


  public static final int[] ARRAYS = Arrays.stream(values())
      .mapToInt(PlatformType::getType)
      .toArray();

  /**
   * 状态值
   */
  private final Integer type;
  /**
   * 状态名
   */
  private final String value;

  public static PlatformType valueOfType(Integer type) {
    return ArrayUtil.firstMatch(o -> o.getType().equals(type), values());
  }

  @Override
  public int[] array() {
    return ARRAYS;
  }
}
