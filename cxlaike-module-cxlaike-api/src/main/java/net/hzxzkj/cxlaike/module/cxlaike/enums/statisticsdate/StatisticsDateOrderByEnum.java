package net.hzxzkj.cxlaike.module.cxlaike.enums.statisticsdate;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 排序字段 1-新增内容发布数 2-新增播放 3-总粉丝数 4-总播放量 5-总发布数
 *
 * @author 宵征源码
 */
@Getter
@AllArgsConstructor
public enum StatisticsDateOrderByEnum {
    WZ(0, "","未知"),
    PUBLISH_COUNT_INCREMENT(1, "publish_count_increment","新增内容发布数"),
    PLAY_COUNT_INCREMENT(2, "play_count_increment","新增播放"),
    FANS_COUNT(3, "fans_count","总粉丝数"),
    PLAY_COUNT(4, "play_count","总播放量"),
    PUBLISH_COUNT(5, "publish_count","总发布数"),
    DIGG_COUNT_INCREMENT(6, "digg_count_increment","新增点赞"),
    FANS_COUNT_INCREMENT(7, "fans_count_increment","粉丝量"),


    ;



    private final Integer code;

    private final String value;

    private final String content;

    //根据code返回 enum
    public static StatisticsDateOrderByEnum getEnumByCode(Integer code) {
        for (StatisticsDateOrderByEnum enm : StatisticsDateOrderByEnum.values()) {
            if (enm.getCode().equals(code)) {
                return enm;
            }
        }
        return StatisticsDateOrderByEnum.WZ;
    }
}
