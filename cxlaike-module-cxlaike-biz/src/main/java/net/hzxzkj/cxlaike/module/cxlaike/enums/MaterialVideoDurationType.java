package net.hzxzkj.cxlaike.module.cxlaike.enums;

import cn.hutool.core.util.ArrayUtil;
import lombok.AllArgsConstructor;
import lombok.Getter;
import net.hzxzkj.cxlaike.framework.common.core.IntArrayValuable;

import java.util.Arrays;

/**
 * 描   述:
 * 素材视频时长类型
 * @author tkk
 * @version [版本号, 2023/12/13]
 * @see [相关类/方法]
 * 创建日期: 2023/12/13
 */
@Getter
@AllArgsConstructor
public enum MaterialVideoDurationType implements IntArrayValuable {

  /**
   * 随音频，视频智能截取
   * 镜头时长=音频的时长，视频随音频自动截取（内置去重策略）
   */

  WITH_AUDIO(1, "随音频，视频智能截取"),

  /**
   * 固定时长，智能去重
   * 镜头时长=固定的时长，自动对视频/音频进行截取（内置去重策略）
   */
  FIXED_DURATION(2, "固定时长，智能去重"),

  /**
   * 视频原始时长
   * 保留视频的原始时长，不做改变
   */
  ORIGINAL_DURATION(3, "视频原始时长"),

  /**
   * 随音频视频自动变速
   * 以音频的时长为准，视频随音频自动变速
   */
  WITH_AUDIO_AUTOMATIC_CHANGE_SPEED(4, "随音频视频自动变速"),


  ;

  public static final int[] ARRAYS = Arrays.stream(values()).mapToInt(MaterialVideoDurationType::getType)
      .toArray();

  /**
   * 状态值
   */
  private final Integer type;
  /**
   * X值
   */
  private final String value;


  public static MaterialVideoDurationType valueOfType(Integer type) {
    return ArrayUtil.firstMatch(o -> o.getType().equals(type), values());
  }

  @Override
  public int[] array() {
    return ARRAYS;
  }
}
