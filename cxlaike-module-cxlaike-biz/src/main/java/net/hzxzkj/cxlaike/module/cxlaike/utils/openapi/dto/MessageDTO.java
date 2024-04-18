package net.hzxzkj.cxlaike.module.cxlaike.utils.openapi.dto;

import lombok.Data;

/**
 * @author jianan.han
 * @date 2023-08-19 13:16
 * @description
 */
@Data
public class MessageDTO {
    
    /**
     * 与此消息有效负载关联的角色
     */
    private String role;

    /**
     * 内容
     */
    private String content;
}
