package net.hzxzkj.cxlaike.module.cxlaike.utils.douyin.vo;

import lombok.Data;

import java.util.List;

/**
 * @author jianan.han
 * @date 2023-08-17 11:16
 * @description
 */
@Data
public class UserFanDataRespDTO {

    /**
     * 错误码描述
     */
    private String description;
    /**
     * 近几天粉丝的数量
     */
    private List<FanDataListRespDTO> result;

    private Long error_code;


}
