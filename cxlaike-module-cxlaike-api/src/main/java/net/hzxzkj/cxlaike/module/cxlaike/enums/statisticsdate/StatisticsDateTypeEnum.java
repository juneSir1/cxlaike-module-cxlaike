package net.hzxzkj.cxlaike.module.cxlaike.enums.statisticsdate;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 时间筛选 1-昨天 7-近7天 30-近30天 60-近60天
 *
 * @author 宵征源码
 */
@Getter
@AllArgsConstructor
public enum StatisticsDateTypeEnum {
    WZ(0, "未知"),
    YESTERDAY(1, "昨天"),
    NSEVEND(7, "近7天"),
    NTHIRTYD(30, "近30天"),
    NSIXTYD(60, "近60天"),


    ;



    private final Integer code;

    private final String content;

    //根据code返回 enum
    public static StatisticsDateTypeEnum getEnumByCode(Integer code) {
        for (StatisticsDateTypeEnum enm : StatisticsDateTypeEnum.values()) {
            if (enm.getCode().equals(code)) {
                return enm;
            }
        }
        return StatisticsDateTypeEnum.WZ;
    }
}
