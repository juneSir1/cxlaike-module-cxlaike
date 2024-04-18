package net.hzxzkj.cxlaike.module.cxlaike.enums.taskorder;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 任务订单取消类型 1-个人取消 2-系统取消
 *
 * @author 宵征源码
 */
@Getter
@AllArgsConstructor
public enum CancelTypeEnum {

    MEMBER(1, "人为原因取消"),
    SYSTEM(2, "系统取消"),

    ;



    private final Integer code;

    private final String content;

    //根据code返回 enum
    public static CancelTypeEnum getEnumByCode(Integer code) {
        for (CancelTypeEnum enm : CancelTypeEnum.values()) {
            if (enm.getCode().equals(code)) {
                return enm;
            }
        }
        return CancelTypeEnum.MEMBER;
    }
}
