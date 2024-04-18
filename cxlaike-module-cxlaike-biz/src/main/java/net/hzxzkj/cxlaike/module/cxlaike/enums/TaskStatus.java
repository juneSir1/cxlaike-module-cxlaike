package net.hzxzkj.cxlaike.module.cxlaike.enums;

import cn.hutool.core.util.ArrayUtil;
import java.util.Arrays;
import lombok.AllArgsConstructor;
import lombok.Getter;
import net.hzxzkj.cxlaike.framework.common.core.IntArrayValuable;

/**
 * 描   述: 任务状态
 *
 * @author ace
 * @version [版本号, 2023/9/6]
 * @see [相关类/方法]
 * 创建日期: 2023/9/6
 */
@Getter
@AllArgsConstructor
public enum TaskStatus implements IntArrayValuable {

  /**
   * 未开始
   */
  NOT_STARTED(0, "未开始"),

  /**
   * 进行中
   */
  IN_PROGRESS(1, "进行中"),

  /**
   * 已完成
   */
  COMPLETED(2, "已完成"),

  /**
   * 已结算
   */
  SETTLED(3, "已结算"),

  /**
   * 已暂停
   */
  SUSPENDED(4, "已暂停"),

  ;


  private final Integer status;

  private final String value;

  public static final int[] ARRAYS = Arrays.stream(values())
      .mapToInt(TaskStatus::getStatus)
      .toArray();


  public static TaskStatus valueOfType(Integer type) {
    return ArrayUtil.firstMatch(o -> o.getStatus().equals(type), values());
  }

  @Override
  public int[] array() {
    return ARRAYS;
  }
}
