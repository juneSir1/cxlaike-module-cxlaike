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
 * @version [版本号, 2023/10/10]
 * @see [相关类/方法]
 * 创建日期: 2023/10/10
 */
@Getter
@AllArgsConstructor
public enum VideoClipOrderStatus implements IntArrayValuable {

  /**
   * 处理中
   */
  PROCESSING(10, "处理中"),

  /**
   * 处理完成
   */
  FINISH(20, "处理完成"),
  /**
   * 处理失败
   */
  FAIL(30, "处理失败"),
  ;


  public static final int[] ARRAYS = Arrays.stream(values())
      .mapToInt(VideoClipOrderStatus::getStatus)
      .toArray();

  /**
   * 状态值
   */
  private final Integer status;
  /**
   * 状态名
   */
  private final String value;

  public static VideoClipOrderStatus valueOfType(Integer type) {
    return ArrayUtil.firstMatch(o -> o.getStatus().equals(type), values());
  }

  @Override
  public int[] array() {
    return ARRAYS;
  }
}
