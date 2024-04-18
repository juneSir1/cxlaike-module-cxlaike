package net.hzxzkj.cxlaike.module.cxlaike.enums.aicopywriting;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * ai条件限制枚举
 *
 * @author 宵征源码
 */
@Getter
@AllArgsConstructor
public enum AiQuantityRestrictEnum {

    ONE(1, 1),
    FIVE(5, 3),
    TEN(10, 5),
    FIFTEEN(15, 0),

    ;



    private final Integer code;

    private final Integer count;

    //根据code返回 enum
    public static AiQuantityRestrictEnum getEnumByCode(Integer code) {
        for (AiQuantityRestrictEnum enm : AiQuantityRestrictEnum.values()) {
            if (enm.getCode().equals(code)) {
                return enm;
            }
        }
        return AiQuantityRestrictEnum.ONE;
    }
}
