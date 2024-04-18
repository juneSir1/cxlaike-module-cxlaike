package net.hzxzkj.cxlaike.module.cxlaike.enums;

import cn.hutool.core.util.ArrayUtil;
import lombok.AllArgsConstructor;
import lombok.Getter;
import net.hzxzkj.cxlaike.framework.common.core.IntArrayValuable;

import java.util.Arrays;

/**
 * 描   述:
 * 视频时长类型
 * @author tkk
 * @version [版本号, 2023/12/13]
 * @see [相关类/方法]
 * 创建日期: 2023/12/13
 */
@Getter
@AllArgsConstructor
public enum VideoDurationType implements IntArrayValuable {

  /**
   * 随镜头组设置
   */

  SET_WITH_LENS_GROUP(1, "随镜头组设置"),

  /**
   * 全局字幕配音
   */
  GLOBAL_SUBTITLE_DUBBING(2, "全局字幕配音"),

  /**
   * 固定时长
   */
  FIXED_DURATION(3, "固定时长"),


  ;

  public static final int[] ARRAYS = Arrays.stream(values()).mapToInt(VideoDurationType::getType)
      .toArray();

  /**
   * 状态值
   */
  private final Integer type;
  /**
   * X值
   */
  private final String value;


  public static VideoDurationType valueOfType(Integer type) {
    return ArrayUtil.firstMatch(o -> o.getType().equals(type), values());
  }

  @Override
  public int[] array() {
    return ARRAYS;
  }
}
