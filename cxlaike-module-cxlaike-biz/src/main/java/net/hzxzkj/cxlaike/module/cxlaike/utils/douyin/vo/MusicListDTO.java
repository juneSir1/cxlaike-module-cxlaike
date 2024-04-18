package net.hzxzkj.cxlaike.module.cxlaike.utils.douyin.vo;

import lombok.Data;
import net.hzxzkj.cxlaike.module.infra.api.file.dto.MusicList;

import java.util.List;

/**
 * @author tk
 * @date 2024-01-05 11:16
 * @description
 */
@Data
public class MusicListDTO {

    /**
     * 错误码描述
     */
    private String description;
    /**
     * 错误码
     */
    private Long error_code;
    /**
     * 音乐列表
     */
    private List<MusicList> list;


}
