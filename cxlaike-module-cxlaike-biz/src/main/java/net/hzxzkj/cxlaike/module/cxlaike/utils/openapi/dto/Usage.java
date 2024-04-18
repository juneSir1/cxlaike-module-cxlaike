package net.hzxzkj.cxlaike.module.cxlaike.utils.openapi.dto;

import lombok.Data;

/**
 * @author jianan.han
 * @date 2023-08-23 20:31
 * @description
 */@Data
public class Usage {
    private Long prompt_tokens;
    private Long completion_tokens;
    private Long total_tokens;
}
