package net.hzxzkj.cxlaike.module.cxlaike.utils.douyin.vo;

import lombok.Data;

/**
 * @author jianan.han
 * @date 2023/10/27 下午3:36
 * @description
 */
@Data
public class VideoBaseResultResp {

    /**
     * 最近30天点赞数
     */
    private Integer total_like;
    /**
     * 最近30天评论数
     */
    private Integer total_comment;
    /**
     * 最近30天分享数
     */
    private Integer total_share;
    /**
     * 最近30天平均播放时长
     */
    private Double avg_play_duration;
    /**
     * 最近30天播放次数
     */
    private Integer total_play;

}
