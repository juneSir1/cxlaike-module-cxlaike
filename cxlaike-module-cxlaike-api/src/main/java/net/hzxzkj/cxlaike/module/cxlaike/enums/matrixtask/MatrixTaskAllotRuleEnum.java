package net.hzxzkj.cxlaike.module.cxlaike.enums.matrixtask;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 分配规则 1-按账号固定分配 2-平均分配
 *
 * @author 宵征源码
 */
@Getter
@AllArgsConstructor
public enum MatrixTaskAllotRuleEnum {
    WZ(0, "未知"),
    IMMOBILIZATION(1, "按账号固定分配"),
    AVERAGE(2, "平均分配"),


    ;



    private final Integer code;

    private final String content;

    //根据code返回 enum
    public static MatrixTaskAllotRuleEnum getEnumByCode(Integer code) {
        for (MatrixTaskAllotRuleEnum enm : MatrixTaskAllotRuleEnum.values()) {
            if (enm.getCode().equals(code)) {
                return enm;
            }
        }
        return MatrixTaskAllotRuleEnum.WZ;
    }
}
