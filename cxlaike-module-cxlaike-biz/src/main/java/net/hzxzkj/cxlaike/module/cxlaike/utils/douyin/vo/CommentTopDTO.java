package net.hzxzkj.cxlaike.module.cxlaike.utils.douyin.vo;

import lombok.Data;

/**
 * @author jianan.han
 * @date 2023/12/18 下午5:22
 * @description
 */
@Data
public class CommentTopDTO {
    /**
     * 错误码描述
     */
    private String description;
    /**
     * 错误码
     */
    private Long error_code;

    private String avatar;
    private String nick_name;
}
