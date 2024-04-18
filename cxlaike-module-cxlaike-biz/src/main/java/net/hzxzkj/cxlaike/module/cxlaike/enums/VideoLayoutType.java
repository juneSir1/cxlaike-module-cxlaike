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
public enum VideoLayoutType implements IntArrayValuable {

  /**
   * 随机
   */
  RANDOM(0, "随机"),

  /**
   * 9:16视频满屏
   */
  FULL_SCREEN(10, "9:16视频满屏"),
  /**
   * 9:16视频下移
   */
  DOWN(20, "9:16视频下移"),
  /**
   * 16:9 视频纯色填充
   */
  PURE_COLOR(30, "16:9 视频纯色填充"),

  /**
   * 16:9 视频添加背景图片
   */
  BACKGROUND_IMAGE(40, "16:9 视频添加背景图片"),
  /**
   * 16:9 视频背景模糊
   */
  BLUR(50, "16:9 视频背景模糊"),

  /**
   * 16:9视频模板(6)
   */
  TEMPLATE_SIX(60, "16:9视频模板(6)"),
  ;

  public static final int[] ARRAYS = Arrays.stream(values()).mapToInt(VideoLayoutType::getType)
      .toArray();

  /**
   * 状态值
   */
  private final Integer type;
  /**
   * X值
   */
  private final String value;


  public static VideoLayoutType valueOfType(Integer type) {
    return ArrayUtil.firstMatch(o -> o.getType().equals(type), values());
  }

  @Override
  public int[] array() {
    return ARRAYS;
  }
}
