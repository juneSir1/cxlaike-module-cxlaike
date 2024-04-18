package net.hzxzkj.cxlaike.module.cxlaike.utils.douyin.vo;

import lombok.Data;

import java.util.List;

/**
 * @author jianan.han
 * @date 2023/10/23 上午9:47
 * @description
 */
@Data
public class PoiPageRespDTO {

    private Integer cursor;

    private String description;

    private Integer error_code;

    private Boolean has_more;

    private List<PoiListRespDTO> pois;
}
