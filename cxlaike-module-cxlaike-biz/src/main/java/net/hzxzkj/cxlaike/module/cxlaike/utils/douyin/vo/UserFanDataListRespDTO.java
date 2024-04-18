package net.hzxzkj.cxlaike.module.cxlaike.utils.douyin.vo;

import lombok.Data;
import lombok.ToString;

import java.util.List;

/**
 * @author jianan.han
 * @date 2023-08-17 11:16
 * @description
 */
@Data
@ToString(callSuper = true)
public class UserFanDataListRespDTO {

    private UserFanDataRespDTO data;

    private ExtraDTO extra;

}
