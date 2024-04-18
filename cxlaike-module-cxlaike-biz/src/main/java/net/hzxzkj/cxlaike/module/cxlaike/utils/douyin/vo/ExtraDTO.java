package net.hzxzkj.cxlaike.module.cxlaike.utils.douyin.vo;

import lombok.Data;

/**
 * @author jianan.han
 * @date 2023-08-17 11:16
 * @description
 */
@Data
public class ExtraDTO {

    /**
     * 错误码描述
     */
    private String description;
    /**
     * 错误码
     */
    private Long error_code;
    /**
     * 标识请求的唯一id
     */
    private String logid;
    /**
     * 毫秒级时间戳
     */
    private Long now;
    /**
     * 子错误码描述
     */
    private String sub_description;
    /**
     * 子错误码
     */
    private Long sub_error_code;


}
