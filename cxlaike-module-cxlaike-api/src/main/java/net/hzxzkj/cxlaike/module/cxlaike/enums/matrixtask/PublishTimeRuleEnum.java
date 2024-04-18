package net.hzxzkj.cxlaike.module.cxlaike.enums.matrixtask;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 发布时间规则 1-立即发布 2-定时发布
 *
 * @author 宵征源码
 */
@Getter
@AllArgsConstructor
public enum PublishTimeRuleEnum {
    WZ(0, "未知"),
    IMMEDIATELY(1, "立即发布"),
    TIMING(2, "定时发布"),


    ;



    private final Integer code;

    private final String content;

    //根据code返回 enum
    public static PublishTimeRuleEnum getEnumByCode(Integer code) {
        for (PublishTimeRuleEnum enm : PublishTimeRuleEnum.values()) {
            if (enm.getCode().equals(code)) {
                return enm;
            }
        }
        return PublishTimeRuleEnum.WZ;
    }
}
