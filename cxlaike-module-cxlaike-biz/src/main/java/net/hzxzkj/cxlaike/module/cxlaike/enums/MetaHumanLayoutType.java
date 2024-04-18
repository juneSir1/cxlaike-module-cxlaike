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
 * @version [版本号, 2023/9/19]
 * @see [相关类/方法]
 * 创建日期: 2023/9/19
 */
@Getter
@AllArgsConstructor
public enum MetaHumanLayoutType implements IntArrayValuable {

  /**
   * 随机
   */
  RANDOM(0, "随机"),

  /**
   * 视频画面穿插
   */
  VIDEO_INTERLACE(1, "视频画面穿插"),
  /**
   * 固定右上角
   */
  FIXED_UPPER_RIGHT_CORNER(2, "固定右上角"),
  ;

  public static final int[] ARRAYS = Arrays.stream(values()).mapToInt(MetaHumanLayoutType::getType)
      .toArray();

  /**
   * 状态值
   */
  private final Integer type;
  /**
   * X值
   */
  private final String value;


  public static MetaHumanLayoutType valueOfType(Integer type) {
    return ArrayUtil.firstMatch(o -> o.getType().equals(type), values());
  }

  @Override
  public int[] array() {
    return ARRAYS;
  }
}
