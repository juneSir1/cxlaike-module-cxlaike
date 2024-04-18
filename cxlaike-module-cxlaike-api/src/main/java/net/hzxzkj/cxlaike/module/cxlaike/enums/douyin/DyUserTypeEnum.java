package net.hzxzkj.cxlaike.module.cxlaike.enums.douyin;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 用户类型 1-达人 2-商户
 *
 * @author 宵征源码
 */
@Getter
@AllArgsConstructor
public enum DyUserTypeEnum {

    DR(1, "达人"),
    SJ(2, "商家"),
    WB(3,"外部"),

    ;



    private final Integer code;

    private final String content;

    //根据code返回 enum
    public static DyUserTypeEnum getEnumByCode(Integer code) {
        for (DyUserTypeEnum enm : DyUserTypeEnum.values()) {
            if (enm.getCode().equals(code)) {
                return enm;
            }
        }
        return DyUserTypeEnum.DR;
    }
}
