package net.hzxzkj.cxlaike.module.cxlaike.enums;

import cn.hutool.core.util.ArrayUtil;
import lombok.AllArgsConstructor;
import lombok.Getter;
import net.hzxzkj.cxlaike.framework.common.core.IntArrayValuable;

import java.util.Arrays;

/**
 * 描   述: 封面选择类型
 *
 * @author tkk
 * @version [版本号, 2023/12/14]
 * @see [相关类/方法]
 * 创建日期: 2023/12/14
 */
@Getter
@AllArgsConstructor
public enum FontSetFieldNameType implements IntArrayValuable {

  /**
   * ai素材混剪视频任务-全局内容标题
   */
  AI_VIDEO_TASK_CONTENT(1, "AI_VIDEO_TASK_CONTENT"),
  /**
   * ai素材混剪视频任务-全局字幕文案
   */
  AI_VIDEO_TASK_COPYINGWRITING(2, "AI_VIDEO_TASK_COPYINGWRITING"),

  /**
   * ai素材混剪视频任务-视频组内容标题
   */
  AI_VIDEO_TASK_GROUP_CONTENT(3, "AI_VIDEO_TASK_GROUP_CONTENT"),
  /**
   * ai素材混剪视频任务-视频组字幕文案
   */
  AI_VIDEO_TASK_GROUP_COPYINGWRITING(4, "AI_VIDEO_TASK_GROUP_COPYINGWRITING")

  ;


  private final Integer type;

  private final String value;

  public static final int[] ARRAYS = Arrays.stream(values())
      .mapToInt(FontSetFieldNameType::getType)
      .toArray();


  public static FontSetFieldNameType valueOfType(Integer type) {
    return ArrayUtil.firstMatch(o -> o.getType().equals(type), values());
  }

  @Override
  public int[] array() {
    return ARRAYS;
  }
}
