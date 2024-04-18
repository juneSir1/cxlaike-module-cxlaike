package net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.dycomment;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import net.hzxzkj.cxlaike.framework.mybatis.core.dataobject.BaseDO;

/**
 * 抖音评论管理 DO
 *
 * @author 宵征源码
 */
@TableName("cxlaike_dy_comment")
@KeySequence("cxlaike_dy_comment_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DyCommentDO extends BaseDO {
    /**
     * 主键
     */
    @TableId
    private Long id;
    /**
     * 评论id
     */
    private String commentId;
    /**
     * 该条评论发布者的user_id
     */
    private String commentUserId;
    /**
     * 评论的具体内容
     */
    private String content;
    /**
     * 评论创建时间
     */
    private LocalDateTime commentCreateTime;
    /**
     * 该评论的点赞数
     */
    private Integer diggCount;
    /**
     * 该评论的回复数量
     */
    private Integer replyCommentTotal;
    /**
     * 该评论是否被置顶 1-是 0-否
     */
    private Integer top;
    /**
     * 评论者头像
     */
    private String avatar;
    /**
     * 评论者昵称
     */
    private String nickName;
    /**
     * 视频链接
     */
    private String videoLink;
    /**
     * 视频id
     */
    private String itemId;
    /**
     * 所属者头像
     */
    private String ownAvatar;
    /**
     * 所属者昵称
     */
    private String ownNickName;

    private Long accountId;

    private Long tenantId;

}
