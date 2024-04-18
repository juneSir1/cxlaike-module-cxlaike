package net.hzxzkj.cxlaike.module.cxlaike.utils.douyin.vo;

import lombok.Data;

/**
 * @author jianan.han
 * @date 2023/9/4 下午7:05
 * @description
 */
@Data
public class VideoStatisticsDTO {
    /**
     *点赞数
     */
    private Integer digg_count;
    /**
     *下载数
     */
    private Integer download_count;
    /**
     *播放数，只有作者本人可见。公开视频设为私密后，播放数也会返回 0
     */
    private Integer play_count;
    /**
     *分享数
     */
    private Integer share_count;
    /**
     *转发数。
     *
     * 目前转发功能已下线，仅旧版本有转发功能入口，所以转发数字段可以忽略；同时，客户端分享按钮中的几乎所有操作，都会增加分享数，字段可以使用。
     */
    private Integer forward_count;
    /**
     *评论数
     */
    private Integer comment_count;

}
