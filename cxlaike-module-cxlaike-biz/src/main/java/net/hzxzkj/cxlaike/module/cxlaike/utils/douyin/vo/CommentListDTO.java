package net.hzxzkj.cxlaike.module.cxlaike.utils.douyin.vo;

import lombok.Data;

import java.util.List;

/**
 * @author jianan.han
 * @date 2023/10/27 下午3:36
 * @description
 */
@Data
public class CommentListDTO {
    /**
     * 错误码描述
     */
    private String description;
    /**
     * 错误码
     */
    private Long error_code;
    /**
     * 用于下一页请求的cursor
     */
    private Long cursor;
    /**
     * 用于判断是否还有下一页
     */
    private Boolean has_more;
    /**
     * 评论列表
     */
    private List<CommentList> list;


}
