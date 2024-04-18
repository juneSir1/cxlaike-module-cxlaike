package net.hzxzkj.cxlaike.module.cxlaike.enums.taskorder;

import lombok.AllArgsConstructor;
import lombok.Getter;
import net.hzxzkj.cxlaike.framework.common.core.IntArrayValuable;

import java.util.Arrays;

/**
 * 视频状态 视频状态 0-生成中 2-不适宜公开 4-审核中 5-公开视频
 *
 * @author 宵征源码
 */
@Getter
@AllArgsConstructor
public enum VideoStatusEnum implements IntArrayValuable {

    GENERATE(0, "生成中"),
    UNDER_REVIEW(4, "审核中"),
    PUBLICITY(5, "公开"),
    UN_PUBLICITY(2, "未公开"),

    ;
    public static final int[] ARRAYS = Arrays.stream(values())
            .mapToInt(VideoStatusEnum::getCode)
            .toArray();


    private final Integer code;

    private final String name;


    @Override
    public int[] array() {
        return ARRAYS;
    }
}
