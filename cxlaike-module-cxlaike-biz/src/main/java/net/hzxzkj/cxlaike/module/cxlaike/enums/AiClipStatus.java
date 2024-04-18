package net.hzxzkj.cxlaike.module.cxlaike.enums;

import java.util.Arrays;
import lombok.AllArgsConstructor;
import lombok.Getter;
import net.hzxzkj.cxlaike.framework.common.core.IntArrayValuable;

/**
 * 描   述:
 *
 * @author ace
 * @version [版本号, 2023/8/22]
 * @see [相关类/方法]
 * 创建日期: 2023/8/22
 */
@Getter
@AllArgsConstructor
public enum AiClipStatus implements IntArrayValuable {

  /**
   * 成功
   */
  SUCCESS(0, "成功"),
  /**
   * 生成中
   */
  GENERATING(1, "生成中"),
  /**
   * 失败
   */
  FAILURE(2, "失败"),
  /**
   * 部分失败
   */
  PART_FAILURE(3, "部分失败"),

  /**
   * 素材生成中
   */
  MATERIAL_GENERATING(4, "素材生成中"),
  ;


  public static final int[] ARRAYS = Arrays.stream(values()).mapToInt(AiClipStatus::getStatus)
      .toArray();

  /**
   * 状态值
   */
  private final Integer status;
  /**
   * 状态名
   */
  private final String name;


  @Override
  public int[] array() {
    return ARRAYS;
  }

}
