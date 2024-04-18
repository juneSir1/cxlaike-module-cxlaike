package net.hzxzkj.cxlaike.module.cxlaike.enums.matrixtask;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 任务类型 1-矩阵 2-扫码
 *
 * @author 宵征源码
 */
@Getter
@AllArgsConstructor
public enum MatrixTaskTypeEnum {
    WZ(0, "未知"),
    JZ(1, "矩阵"),
    SM(2, "扫码"),


    ;



    private final Integer code;

    private final String content;

    //根据code返回 enum
    public static MatrixTaskTypeEnum getEnumByCode(Integer code) {
        for (MatrixTaskTypeEnum enm : MatrixTaskTypeEnum.values()) {
            if (enm.getCode().equals(code)) {
                return enm;
            }
        }
        return MatrixTaskTypeEnum.WZ;
    }
}
