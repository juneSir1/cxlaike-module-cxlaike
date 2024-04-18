package net.hzxzkj.cxlaike.module.cxlaike.utils.douyin.vo;

import lombok.Data;

/**
 * @author jianan.han
 * @date 2023/10/27 下午3:36
 * @description
 */
@Data
public class CommentList {
    /**
     * 评论id
     */
    private String comment_id;
    /**
     * 该条评论发布者的user_id
     */
    private String comment_user_id;
    /**
     * 评论的具体内容
     */
    private String content;
    /**
     * 评论创建的秒级时间戳
     */
    private Long create_time;
    /**
     * 该评论的点赞数
     */
    private Integer digg_count;
    /**
     * 该评论的回复数量
     */
    private Integer reply_comment_total;
    /**
     * 该评论是否被置顶
     */
    private Boolean top;
    /**
     * 用户头像url
     */
    private String avatar;
    /**
     * 用户昵称
     */
    private String nick_name;


}
