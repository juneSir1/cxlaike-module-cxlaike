package net.hzxzkj.cxlaike.module.cxlaike.utils.douyin.vo;

import lombok.Data;

import java.util.List;

/**
 * @author jianan.han
 * @date 2023/9/4 下午7:03
 * @description
 */
@Data
public class VideoDataDTO {
    /**
     *错误码描述
     */
    private String description;
    /**
     *错误码
     */
    private Long error_code;

    private List<VideoListDTO> list;
}
