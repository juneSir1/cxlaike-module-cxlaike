package net.hzxzkj.cxlaike.module.cxlaike.utils.douyin.vo;

import lombok.Data;

/**
 * @author jianan.han
 * @date 2023/9/4 下午7:01
 * @description
 */
@Data
public class VideoListDTO {

    /**
     *视频标题
     */
    private String title;
    /**
     *视频创建时间戳
     */
    private Long create_time;
    /**
     *表示视频状态。
     *
     * 2：不适宜公开
     * 4：审核中
     * 5：公开视频
     */
    private Integer video_status;
    /**
     *视频播放页面。视频播放页可能会失效，请在观看视频前调用 /video/data/ 获取最新的播放页。
     */
    private String share_url;
    /**
     *视频封面
     */
    private String cover;
    /**
     *是否置顶
     */
    private boolean is_top;
    /**
     *表示是否审核结束。审核通过或者失败都会返回true，审核中返回false。
     */
    private boolean is_reviewed;

    private VideoStatisticsDTO statistics;
    /**
     *视频id
     */
    private String item_id;
    /**
     *视频真实 ID
     */
    private String video_id;
    /**
     *媒体类型。
     *
     * 2：图集
     * 4：视频
     */
    private Integer media_type;

}
