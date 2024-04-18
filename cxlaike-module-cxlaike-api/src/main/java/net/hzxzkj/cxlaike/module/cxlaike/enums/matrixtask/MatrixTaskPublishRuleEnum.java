package net.hzxzkj.cxlaike.module.cxlaike.enums.matrixtask;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 发布方式规则 1-按视频间隔发布 2-按单账号视频间隔
 *
 * @author 宵征源码
 */
@Getter
@AllArgsConstructor
public enum MatrixTaskPublishRuleEnum {
    WZ(0, "未知"),
    SP(1, "按视频间隔发布"),
    ZH(2, "按单账号视频间隔"),


    ;



    private final Integer code;

    private final String content;

    //根据code返回 enum
    public static MatrixTaskPublishRuleEnum getEnumByCode(Integer code) {
        for (MatrixTaskPublishRuleEnum enm : MatrixTaskPublishRuleEnum.values()) {
            if (enm.getCode().equals(code)) {
                return enm;
            }
        }
        return MatrixTaskPublishRuleEnum.WZ;
    }
}
