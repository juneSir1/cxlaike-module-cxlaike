package net.hzxzkj.cxlaike.module.cxlaike.enums.matrixtask;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 时间间隔单位 1-分 2-时 3-天
 *
 * @author 宵征源码
 */
@Getter
@AllArgsConstructor
public enum MatrixTaskIntervalUnitEnum {
    WZ(0, "未知"),
    MINUTE(1, "分"),
    HOUR(2, "时"),
    DAY(3, "天"),


    ;



    private final Integer code;

    private final String content;

    //根据code返回 enum
    public static MatrixTaskIntervalUnitEnum getEnumByCode(Integer code) {
        for (MatrixTaskIntervalUnitEnum enm : MatrixTaskIntervalUnitEnum.values()) {
            if (enm.getCode().equals(code)) {
                return enm;
            }
        }
        return MatrixTaskIntervalUnitEnum.WZ;
    }
}
