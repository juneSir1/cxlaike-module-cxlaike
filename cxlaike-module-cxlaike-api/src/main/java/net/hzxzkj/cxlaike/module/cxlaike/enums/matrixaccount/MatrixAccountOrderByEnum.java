package net.hzxzkj.cxlaike.module.cxlaike.enums.matrixaccount;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 排序字段
 *
 * @author 宵征源码
 */
@Getter
@AllArgsConstructor
public enum MatrixAccountOrderByEnum {
    CREATE_TIME(1, "createTime","创建时间"),
    DY_ACCREDIT_TIME(2, "createTime","授权时间"),

    ;



    private final Integer code;

    private final String value;

    private final String content;

    //根据code返回 enum
    public static MatrixAccountOrderByEnum getEnumByCode(Integer code) {
        for (MatrixAccountOrderByEnum enm : MatrixAccountOrderByEnum.values()) {
            if (enm.getCode().equals(code)) {
                return enm;
            }
        }
        return MatrixAccountOrderByEnum.CREATE_TIME;
    }
}
