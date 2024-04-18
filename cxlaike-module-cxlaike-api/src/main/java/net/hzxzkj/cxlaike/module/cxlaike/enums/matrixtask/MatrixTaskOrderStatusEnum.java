package net.hzxzkj.cxlaike.module.cxlaike.enums.matrixtask;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 订单状态 1-分发 5-已上传 10-终止
 *
 * @author 宵征源码
 */
@Getter
@AllArgsConstructor
public enum MatrixTaskOrderStatusEnum {
    WZ(0, "未知"),
    FF(1, "分发"),
    YSC(5, "已上传"),
    ZZ(10, "终止"),
    SX(15, "失效"),


    ;



    private final Integer code;

    private final String content;

    //根据code返回 enum
    public static MatrixTaskOrderStatusEnum getEnumByCode(Integer code) {
        for (MatrixTaskOrderStatusEnum enm : MatrixTaskOrderStatusEnum.values()) {
            if (enm.getCode().equals(code)) {
                return enm;
            }
        }
        return MatrixTaskOrderStatusEnum.WZ;
    }
}
