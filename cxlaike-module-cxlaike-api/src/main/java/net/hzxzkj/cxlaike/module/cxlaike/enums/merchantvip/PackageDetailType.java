package net.hzxzkj.cxlaike.module.cxlaike.enums.merchantvip;

import cn.hutool.core.util.ArrayUtil;
import java.util.Arrays;
import lombok.AllArgsConstructor;
import lombok.Getter;
import net.hzxzkj.cxlaike.framework.common.core.IntArrayValuable;

/**
 * 描   述:
 *
 * @author ace
 * @version [版本号, 2023/10/31]
 * @see [相关类/方法]
 * 创建日期: 2023/10/31
 */
@Getter
@AllArgsConstructor
public enum PackageDetailType implements IntArrayValuable {

  /**
   * 金币赠送
   */
  GOLD_COIN(1, "金币赠送"),
  ;

  public static final int[] ARRAYS = Arrays.stream(values()).mapToInt(PackageDetailType::getType)
      .toArray();

  /**
   * 状态值
   */
  private final Integer type;
  /**
   * X值
   */
  private final String value;


  public static PackageDetailType valueOfType(Integer type) {
    return ArrayUtil.firstMatch(o -> o.getType().equals(type), values());
  }

  @Override
  public int[] array() {
    return ARRAYS;
  }

}
