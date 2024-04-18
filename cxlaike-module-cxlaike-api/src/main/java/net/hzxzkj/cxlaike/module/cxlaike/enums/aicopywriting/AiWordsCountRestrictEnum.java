package net.hzxzkj.cxlaike.module.cxlaike.enums.aicopywriting;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * ai字数限制枚举
 *
 * @author 宵征源码
 */
@Getter
@AllArgsConstructor
public enum AiWordsCountRestrictEnum {

    ONE(1, 80),
    FIVE(5, 150),
    TEN(10, 250),
    FIFTEEN(15, 350),
    TWENTY(20, 0),


    ;



    private final Integer code;

    private final Integer count;


    //根据code返回 enum
    public static AiWordsCountRestrictEnum getEnumByCode(Integer code) {
        for (AiWordsCountRestrictEnum enm : AiWordsCountRestrictEnum.values()) {
            if (enm.getCode().equals(code)) {
                return enm;
            }
        }
        return AiWordsCountRestrictEnum.ONE;
    }
}
