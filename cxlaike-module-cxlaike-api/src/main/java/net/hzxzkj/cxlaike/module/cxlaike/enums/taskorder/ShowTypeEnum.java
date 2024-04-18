package net.hzxzkj.cxlaike.module.cxlaike.enums.taskorder;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 任务订单小状态展示类型 1-发布剩余时间 2-视频正在生成中 3-商家剩余审核时间 4-待结算 5-已结算 6-已取消
 *
 * @author 宵征源码
 */
@Getter
@AllArgsConstructor
public enum ShowTypeEnum {

    FBSYSJ(1, "发布剩余时间"),
    SPSCZ(2, "视频正在生成中"),
    SJSYSHSJ(3, "商家剩余审核时间"),
    DJS(4, "待结算"),
    YJS(5, "已结算"),
    YQX(6, "已取消"),




    ;



    private final Integer code;

    private final String name;


}
