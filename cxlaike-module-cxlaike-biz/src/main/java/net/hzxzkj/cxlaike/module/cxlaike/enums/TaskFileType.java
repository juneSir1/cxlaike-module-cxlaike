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
 * @version [版本号, 2023/9/6]
 * @see [相关类/方法]
 * 创建日期: 2023/9/6
 */
@Getter
@AllArgsConstructor
public enum TaskFileType implements IntArrayValuable {
  /**
   * 文件夹
   */
  FOLDER(1, "文件夹"),

  /**
   * 视频
   */
  VIDEO(2, "视频");


  private final Integer type;

  private final String value;

  public static final int[] ARRAYS = Arrays.stream(values())
      .mapToInt(TaskFileType::getType)
      .toArray();


  public static TaskFileType valueOfType(Integer type) {
    return ArrayUtil.firstMatch(o -> o.getType().equals(type), values());
  }

  @Override
  public int[] array() {
    return ARRAYS;
  }
}
