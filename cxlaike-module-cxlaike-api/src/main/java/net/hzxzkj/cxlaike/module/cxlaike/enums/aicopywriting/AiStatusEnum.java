package net.hzxzkj.cxlaike.module.cxlaike.enums.aicopywriting;

import lombok.AllArgsConstructor;
import lombok.Getter;
import net.hzxzkj.cxlaike.framework.common.core.IntArrayValuable;

import java.util.Arrays;

/**
 * 通用状态枚举
 *
 * @author 宵征源码
 */
@Getter
@AllArgsConstructor
public enum AiStatusEnum {

    UNDER_WAY(1, "生成中"),
    COMPLETE(2, "完成"),
    FAIL(3, "失败"),

    ;


    /**
     * 状态值
     */
    private final Integer status;
    /**
     * 状态名
     */
    private final String name;

}
