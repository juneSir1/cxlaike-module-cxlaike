package net.hzxzkj.cxlaike.module.cxlaike.enums.matrixtask;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 任务状态 1-未开始 2-进行中 3-已结束 4-终止
 *
 * @author 宵征源码
 */
@Getter
@AllArgsConstructor
public enum MatrixTaskStatusEnum {
    WZ(0, "未知"),
    WKS(1, "未开始"),
    JXZ(2, "进行中"),
    YJS(3, "已结束"),
    ZZ(4, "终止"),

    ;



    private final Integer code;

    private final String content;

    //根据code返回 enum
    public static MatrixTaskStatusEnum getEnumByCode(Integer code) {
        for (MatrixTaskStatusEnum enm : MatrixTaskStatusEnum.values()) {
            if (enm.getCode().equals(code)) {
                return enm;
            }
        }
        return MatrixTaskStatusEnum.WZ;
    }
}
