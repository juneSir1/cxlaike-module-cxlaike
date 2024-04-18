package net.hzxzkj.cxlaike.module.cxlaike.utils.openapi.dto;

import lombok.Data;


/**
 * @author jianan.han
 * @date 2023-08-23 20:31
 * @description
 */
@Data
public class Choices {
    private MessageDTO message;

    private String finish_reason;

    private Long index;
}
