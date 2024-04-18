package net.hzxzkj.cxlaike.module.cxlaike.enums.taskorder;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 结算状态 1-待结算 2-已结算
 *
 * @author 宵征源码
 */
@Getter
@AllArgsConstructor
public enum SettleStatusEnum {
    WZ(0,"未知"),
    TO_BE_SETTLE(1, "待结算"),
    BE_SETTLE(2, "已结算"),

    ;

    private final Integer code;

    private final String name;

    //根据code返回 enum
    public static SettleStatusEnum getEnumByCode(Integer code) {
        for (SettleStatusEnum enm : SettleStatusEnum.values()) {
            if (enm.getCode().equals(code)) {
                return enm;
            }
        }
        return SettleStatusEnum.WZ;
    }
}
