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
public enum TaskMaterialFileType implements IntArrayValuable {

  /**
   * 素材
   */
  MATERIAL(1, "素材"),

  /**
   * ai剪辑视频
   */
  AI_CLIP(2, "ai剪辑视频"),

  /**
   * 数字人素材
   */
  DIGITAL(3, "数字人素材"),
  ;



  private final Integer type;

  private final String value;

  public static final int[] ARRAYS = Arrays.stream(values())
      .mapToInt(TaskMaterialFileType::getType)
      .toArray();


  public static TaskMaterialFileType valueOfType(Integer type) {
    return ArrayUtil.firstMatch(o -> o.getType().equals(type), values());
  }

  @Override
  public int[] array() {
    return ARRAYS;
  }
}
