package net.hzxzkj.cxlaike.module.cxlaike.enums.taskorder;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 任务状态 0.进行中 1.待审核 2.已通过 3.已驳回 4.已取消
 *
 * @author 宵征源码
 */
@Getter
@AllArgsConstructor
public enum OrderStatusEnum {

    UNDERWAY(0, "进行中"),
    TO_BE_REVIEWED(1, "待审核"),
    PASS(2, "已通过"),
    TURN_DOWN(3, "已驳回"),
    CANCEL(4, "已取消"),
    DELETE(5, "已删除"),

    ;



    private final Integer code;

    private final String name;
    //根据code返回 enum
    public static OrderStatusEnum getEnumByCode(Integer code) {
        for (OrderStatusEnum enm : OrderStatusEnum.values()) {
            if (enm.getCode().equals(code)) {
                return enm;
            }
        }
        return OrderStatusEnum.UNDERWAY;
    }

}
