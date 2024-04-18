package net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.dycomment.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import static net.hzxzkj.cxlaike.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

/**
 * 抖音评论管理 Base VO，提供给添加、修改、详细的子 VO 使用
 * 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
 */
@Data
public class DyCommentBaseVO {

    @Schema(description = "评论id", example = "13472")
    private String commentId;

    @Schema(description = "该条评论发布者的user_id", example = "29782")
    private String commentUserId;

    @Schema(description = "评论的具体内容")
    private String content;

    @Schema(description = "评论创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime commentCreateTime;

    @Schema(description = "该评论的点赞数", example = "27935")
    private Integer diggCount;

    @Schema(description = "该评论的回复数量")
    private Integer replyCommentTotal;

    @Schema(description = "该评论是否被置顶 1-是 0-否")
    private Integer top;

    @Schema(description = "评论者头像")
    private String avatar;

    @Schema(description = "评论者昵称", example = "王五")
    private String nickName;

    @Schema(description = "视频链接")
    private String videoLink;

    @Schema(description = "所属者头像")
    private String ownAvatar;

    @Schema(description = "所属者昵称", example = "赵六")
    private String ownNickName;

}
