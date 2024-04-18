package net.hzxzkj.cxlaike.module.cxlaike.enums.task;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 任务类型(1.实探,2.云探)
 *
 * @author 宵征源码
 */
@Getter
@AllArgsConstructor
public enum TaskTypeEnum {

    ST(1, "实探"),
    YT(2, "云探"),
    JZ(3, "矩阵"),
    ;



    private final Integer code;

    private final String content;

    //根据code返回 enum
    public static TaskTypeEnum getEnumByCode(Integer code) {
        for (TaskTypeEnum enm : TaskTypeEnum.values()) {
            if (enm.getCode().equals(code)) {
                return enm;
            }
        }
        return TaskTypeEnum.ST;
    }
}
