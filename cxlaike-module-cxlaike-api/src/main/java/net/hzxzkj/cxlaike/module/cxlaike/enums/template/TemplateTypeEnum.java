package net.hzxzkj.cxlaike.module.cxlaike.enums.template;

import cn.hutool.core.util.ArrayUtil;
import lombok.AllArgsConstructor;
import lombok.Getter;
import net.hzxzkj.cxlaike.framework.common.enums.UserTypeEnum;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Getter
@AllArgsConstructor
public enum TemplateTypeEnum {
  ONE_NONE(11, "模板一(没有数字人)", 1, null, null, 10F, 130, 0F, null, 1F, false, "#ffffff", false,
      TemplateVirtualTypeEnum.NONE, null, 7),
  ONE_DEFAULT(12, "模板一(数字人混剪)", 1, null, null, 10F, 130, 0F, null, 1F, false, "#ffffff", false,
      TemplateVirtualTypeEnum.DEFAULT, 3, 7),
  ONE_TOP_RIGHT_CORNER(13, "模板一(数字人又上角)", 1, null, null, 10F, 130, 0F, null, 1F, false, "#ffffff",
      false, TemplateVirtualTypeEnum.TOP_RIGHT_CORNER, 3, 7),

  TWO_NONE(21, "模板二(没有数字人)", 1, 0.3F, null, 10F, 130, 0F, 0.3F, 1F, false, "#ffffff", false,
      TemplateVirtualTypeEnum.NONE, null, 3),
  TWO_DEFAULT(22, "模板二(数字人混剪)", 1, 0.3F, null, 10F, 130, 0F, 0.3F, 1F, false, "#ffffff", false,
      TemplateVirtualTypeEnum.DEFAULT, 3, 3),
  TWO_TOP_RIGHT_CORNER(23, "模板二(数字人又上角)", 1, 0.3F, null, 10F, 130, 0F, 0.3F, 1F, false, "#ffffff",
      false, TemplateVirtualTypeEnum.TOP_RIGHT_CORNER, 3, 3),

  THREE_NONE(31, "模板三(没有数字人)", 2, 0.33F, null, 10F, 130, 0F, 0.3F, 1F, true, "#ffffff", false,
      TemplateVirtualTypeEnum.NONE, null, 3),
  THREE_DEFAULT(32, "模板三(数字人混剪)", 2, 0.33F, null, 10F, 130, 0F, 0.3F, 1F, true, "#ffffff", false,
      TemplateVirtualTypeEnum.DEFAULT, 4, 3),
  THREE_TOP_RIGHT_CORNER(33, "模板三(数字人又上角)", 2, 0.33F, null, 10F, 130, 0F, null, 1F, true,
      "#ffffff", false, TemplateVirtualTypeEnum.TOP_RIGHT_CORNER, 3, 3),


  FOUR_NONE(41, "模板四(没有数字人)", 2, 0.33F, null, 10F, 130, 0F, 0.3F, 1F, false, "#ffffff", true,
      TemplateVirtualTypeEnum.NONE, null, 1),
  FOUR_DEFAULT(42, "模板四(数字人混剪)", 2, 0.33F, null, 10F, 130, 0F, 0.3F, 1F, false, "#ffffff", true,
      TemplateVirtualTypeEnum.DEFAULT, 3, 1),
  FOUR_TOP_RIGHT_CORNER(43, "模板四(数字人又上角)", 2, 0.33F, null, 10F, 130, 0F, null, 1F, false, "#ffffff",
      true, TemplateVirtualTypeEnum.TOP_RIGHT_CORNER, 3, 1),


  FIVE_NONE(51, "模板五(没有数字人)", 2, 0.33F, 0.1F, 10F, 130, 0F, 0.3F, 1F, false, "#ffffff", false,
      TemplateVirtualTypeEnum.NONE, null, 5),
  FIVE_DEFAULT(52, "模板五(数字人混剪)", 2, 0.33F, 0.1F, 10F, 130, 0F, 0.3F, 1F, false, "#ffffff", false,
      TemplateVirtualTypeEnum.DEFAULT, 4, 5),
  FIVE_TOP_RIGHT_CORNER(53, "模板五(数字人又上角)", 2, 0.33F, 0.1F, 10F, 130, 0F, null, 1F, false, "#ffffff",
      false, TemplateVirtualTypeEnum.TOP_RIGHT_CORNER, 3, 5),


  SIX_NONE(61, "模板六(没有数字人)", 2, 0.33F, null, 10F, 130, 0F, 0.3F, 1F, true, "#ffffff", false,
      TemplateVirtualTypeEnum.NONE, null, 1),
  SIX_DEFAULT(62, "模板六(数字人混剪)", 2, 0.33F, null, 10F, 130, 0F, 0.3F, 1F, true, "#ffffff", false,
      TemplateVirtualTypeEnum.DEFAULT, 4, 1),
  SIX_TOP_RIGHT_CORNER(63, "模板六(数字人又上角)", 2, 0.33F, null, 10F, 130, 0F, null, 1F, true, "#ffffff",
      false, TemplateVirtualTypeEnum.TOP_RIGHT_CORNER, 3, 1),

  ;
  private final Integer value;

  private final String name;

  /**
   * 1横屏 2竖屏
   */
  private final Integer group;

  /**
   * 视频位置
   */
  private final Float videoY;

  /**
   * 背景模糊度
   */
  private final Float bgRadius;

  /**
   * 文案音量
   */
  private final Float contentGain;

  /**
   * 默认语速
   */
  private final Integer contentSpeechRate;
  /**
   * 数字人音量
   */
  private final Float virtualVideoGain;

  /**
   * 数字人位置
   */
  private final Float virtualVideoY;

  /**
   * 背景音量
   */
  private final Float bgGain;


  /**
   * 是否有背景颜色
   */
  private final Boolean isBgColor;

  /**
   * 文案颜色
   */
  private final String contentColor;

  /**
   * 是否有背景图片
   */
  private final Boolean isGlobalImage;

  /**
   * 模板类型
   */
  private final TemplateVirtualTypeEnum virtualType;

  /**
   * 分辨率
   */
  private Integer resolution;

  /**
   * 权重
   */
  private Integer weighting;


  public static TemplateTypeEnum valueOf(Integer value) {
    return ArrayUtil
        .firstMatch(userType -> userType.getValue().equals(value), TemplateTypeEnum.values());
  }

  public static TemplateTypeEnum getRandomEnumByWeight(TemplateVirtualTypeEnum virtualType,
      Integer group) {
    List<TemplateTypeEnum> values = Arrays.asList(ArrayUtil.filter(TemplateTypeEnum.values(),
        templateTypeEnum -> templateTypeEnum.getVirtualType().equals(virtualType)
            && templateTypeEnum.getGroup().equals(group)));
    int totalWeight = values.stream().mapToInt(TemplateTypeEnum::getWeighting).sum();
    int random = new Random().nextInt(totalWeight);
    int accumulatedWeight = 0;
    for (TemplateTypeEnum templateTypeEnum : values) {
      accumulatedWeight += templateTypeEnum.getWeighting();
      if (random < accumulatedWeight) {
        return templateTypeEnum;
      }
    }
    return values.get(0);
  }

  public static TemplateTypeEnum getMetaHumanRandomEnumByWeight(Boolean isMetaHuman) {

    List<TemplateTypeEnum> values;
    if (isMetaHuman) {
      values = Arrays.asList(ArrayUtil.filter(TemplateTypeEnum.values(),
          templateTypeEnum ->
              templateTypeEnum.getVirtualType().equals(TemplateVirtualTypeEnum.DEFAULT)
                  || templateTypeEnum.getVirtualType()
                  .equals(TemplateVirtualTypeEnum.TOP_RIGHT_CORNER)));
    } else {
      values = Arrays.asList(ArrayUtil.filter(TemplateTypeEnum.values(),
          templateTypeEnum ->
              templateTypeEnum.getVirtualType().equals(TemplateVirtualTypeEnum.NONE)));
    }

    int totalWeight = values.stream().mapToInt(TemplateTypeEnum::getWeighting).sum();
    int random = new Random().nextInt(totalWeight);
    int accumulatedWeight = 0;
    for (TemplateTypeEnum templateTypeEnum : values) {
      accumulatedWeight += templateTypeEnum.getWeighting();
      if (random < accumulatedWeight) {
        return templateTypeEnum;
      }
    }
    return values.get(0);
  }


}
