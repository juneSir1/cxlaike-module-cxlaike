package net.hzxzkj.cxlaike.module.cxlaike.utils.douyin.vo;

import lombok.Data;

/**
 * @author jianan.han
 * @date 2023-08-17 11:16
 * @description
 */
@Data
public class FanDataListRespDTO {

    /**
     * 日期
     */
    private String date;
    /**
     * 每天新粉丝数
     */
    private Integer new_fans;
    /**
     * 每日总粉丝数
     */
    private Integer total_fans;

}
