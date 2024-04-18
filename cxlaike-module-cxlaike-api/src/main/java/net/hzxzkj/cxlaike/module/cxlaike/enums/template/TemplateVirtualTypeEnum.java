package net.hzxzkj.cxlaike.module.cxlaike.enums.template;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TemplateVirtualTypeEnum {

  TOP_RIGHT_CORNER(3,"右侧"),

  DEFAULT(2,"数字人穿插"),

  NONE(1,"没有"),
  ;
  private final Integer type;

  private final String name;

  public static TemplateVirtualTypeEnum getRandomEnumByWeight() {
    TemplateVirtualTypeEnum[] values = new TemplateVirtualTypeEnum[]{
        TemplateVirtualTypeEnum.DEFAULT, TemplateVirtualTypeEnum.TOP_RIGHT_CORNER};

    //随机返回一个
    return values[(int) (Math.random() * values.length)];
  }
}
