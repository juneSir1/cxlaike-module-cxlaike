package net.hzxzkj.cxlaike.module.cxlaike.enums;

import cn.hutool.core.util.ArrayUtil;
import java.util.Arrays;
import lombok.AllArgsConstructor;
import lombok.Getter;
import net.hzxzkj.cxlaike.framework.common.core.IntArrayValuable;

/**
 * 描   述: 口播文案类型
 *
 * @author ace
 * @version [版本号, 2023/9/6]
 * @see [相关类/方法]
 * 创建日期: 2023/9/6
 */
@Getter
@AllArgsConstructor
public enum CopywritingType implements IntArrayValuable {

  /**
   * ai视频
   */
  AI_VIDEO(1, "ai视频"),
  /**
   * 探店任务
   */
  EXPLORE_TASK(2, "探店任务");


  private final Integer type;

  private final String value;

  public static final int[] ARRAYS = Arrays.stream(values())
      .mapToInt(CopywritingType::getType)
      .toArray();


  public static CopywritingType valueOfType(Integer type) {
    return ArrayUtil.firstMatch(o -> o.getType().equals(type), values());
  }

  @Override
  public int[] array() {
    return ARRAYS;
  }
}
