package net.hzxzkj.cxlaike.module.cxlaike.enums.matrixtask;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 定位类型 1-不需要 2-使用账号默认定位 3-统一定位
 *
 * @author 宵征源码
 */
@Getter
@AllArgsConstructor
public enum MatrixTaskLocationTypeEnum {
    WZ(0, "未知"),
    BXY(1, "不需要"),
    ZH(2, "使用账号默认定位"),
    TY(3, "统一定位"),
    MD(4,"门店位置"),


    ;



    private final Integer code;

    private final String content;

    //根据code返回 enum
    public static MatrixTaskLocationTypeEnum getEnumByCode(Integer code) {
        for (MatrixTaskLocationTypeEnum enm : MatrixTaskLocationTypeEnum.values()) {
            if (enm.getCode().equals(code)) {
                return enm;
            }
        }
        return MatrixTaskLocationTypeEnum.WZ;
    }
}
