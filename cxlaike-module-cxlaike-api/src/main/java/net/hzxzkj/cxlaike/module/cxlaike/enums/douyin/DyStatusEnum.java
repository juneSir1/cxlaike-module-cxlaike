package net.hzxzkj.cxlaike.module.cxlaike.enums.douyin;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 账号状态 1-启用 2-停用
 *
 * @author 宵征源码
 */
@Getter
@AllArgsConstructor
public enum DyStatusEnum {
    WZ(0,"未知"),
    QY(1, "启用"),
    TY(2, "停用"),

    ;



    private final Integer code;

    private final String content;

    //根据code返回 enum
    public static DyStatusEnum getEnumByCode(Integer code) {
        for (DyStatusEnum enm : DyStatusEnum.values()) {
            if (enm.getCode().equals(code)) {
                return enm;
            }
        }
        return DyStatusEnum.WZ;
    }
}
