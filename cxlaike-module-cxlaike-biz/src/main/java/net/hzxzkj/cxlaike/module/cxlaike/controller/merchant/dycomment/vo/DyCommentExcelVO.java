package net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.dycomment.vo;

import lombok.*;

import java.time.LocalDateTime;

import com.alibaba.excel.annotation.ExcelProperty;

/**
 * 抖音评论管理 Excel VO
 *
 * @author 宵征源码
 */
@Data
public class DyCommentExcelVO {

    @ExcelProperty("评论id")
    private String commentId;

    @ExcelProperty("该条评论发布者的user_id")
    private String commentUserId;

    @ExcelProperty("评论的具体内容")
    private String content;

    @ExcelProperty("评论创建时间")
    private LocalDateTime commentCreateTime;

    @ExcelProperty("该评论的点赞数")
    private Integer diggCount;

    @ExcelProperty("该评论的回复数量")
    private Integer replyCommentTotal;

    @ExcelProperty("该评论是否被置顶 1-是 0-否")
    private Integer top;

    @ExcelProperty("评论者头像")
    private String avatar;

    @ExcelProperty("评论者昵称")
    private String nickName;

    @ExcelProperty("评论者昵称")
    private String videoLink;

    @ExcelProperty("所属者头像")
    private String ownAvatar;

    @ExcelProperty("所属者昵称")
    private String ownNickName;

    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}
