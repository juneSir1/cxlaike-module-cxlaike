package net.hzxzkj.cxlaike.module.cxlaike.enums;

import cn.hutool.core.util.ArrayUtil;
import java.util.Arrays;
import lombok.AllArgsConstructor;
import lombok.Getter;
import net.hzxzkj.cxlaike.framework.common.core.IntArrayValuable;

/**
 * 描   述: ai配音类型
 *
 * @author ace
 * @version [版本号, 2023/8/28]
 * @see [相关类/方法]
 * 创建日期: 2023/8/28
 */
@Getter
@AllArgsConstructor
public enum AiDubType implements IntArrayValuable {
  /**
   * 超高清(推荐)
   */
  SUPER_HD(1, "超高清(推荐)"),
  /**
   * 客服
   */
  CUSTOMER_SERVICE(2, "客服"),
  /**
   * 通用
   */
  GENERAL(3, "通用"),
  /**
   * 直播
   */
  LIVE(4, "直播"),
  /**
   * 童声
   */
  CHILD(5, "童声"),
  /**
   * 方言
   */
  DIALECT(6, "方言"),
  /**
   * 多语言
   */
  MULTI_LANGUAGE(7, "多语言"),
  /**
   * 英文
   */
  ENGLISH(8, "英文"),
  ;


  public static final int[] ARRAYS = Arrays.stream(values())
      .mapToInt(AiDubType::getType)
      .toArray();

  /**
   * 状态值
   */
  private final Integer type;
  /**
   * 状态名
   */
  private final String value;

  public static AiDubType valueOfType(Integer type) {
    return ArrayUtil.firstMatch(o -> o.getType().equals(type), values());
  }

  @Override
  public int[] array() {
    return ARRAYS;
  }
}
