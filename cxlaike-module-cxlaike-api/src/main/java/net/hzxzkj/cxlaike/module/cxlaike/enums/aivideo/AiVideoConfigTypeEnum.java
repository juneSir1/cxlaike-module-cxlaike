package net.hzxzkj.cxlaike.module.cxlaike.enums.aivideo;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AiVideoConfigTypeEnum {
    AAIMOTIONINEFFECT(1),
    SUBTYPE(2),
    FONT(3),
    KEYWORD(4),
    EFFECTCOLORSTYLE(5),
    COLOR(6),
    SYMBOL(7),
    ;

    /**
     * 类型
     */
    private final Integer type;

}
