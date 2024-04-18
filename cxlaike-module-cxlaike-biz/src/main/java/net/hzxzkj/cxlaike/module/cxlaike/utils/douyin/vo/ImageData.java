package net.hzxzkj.cxlaike.module.cxlaike.utils.douyin.vo;

import lombok.Data;

/**
 * @author jianan.han
 * @date 2023/10/27 下午3:36
 * @description
 */
@Data
public class ImageData {
    /**
     * 错误码描述
     */
    private String description;
    /**
     * 错误码
     */
    private Long error_code;
    private ImageRespVO image;

}
