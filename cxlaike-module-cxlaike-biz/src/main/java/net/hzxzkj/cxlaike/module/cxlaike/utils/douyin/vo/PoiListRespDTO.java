package net.hzxzkj.cxlaike.module.cxlaike.utils.douyin.vo;

import lombok.Data;

/**
 * @author jianan.han
 * @date 2023/10/23 上午9:48
 * @description
 */
@Data
public class PoiListRespDTO {
    /**
     *地址
     */
    private String address;
    /**
     *城市
     */
    private String city;
    /**
     *城市编码
     */
    private String city_code;
    /**
     *国家
     */
    private String country;
    /**
     *国家编码
     */
    private String country_code;
    /**
     *区域名称
     */
    private String district;
    /**
     *经纬度，格式：X,Y
     */
    private String location;
    /**
     *唯一ID
     */
    private String poi_id;
    /**
     *名称
     */
    private String poi_name;
    /**
     *省份
     */
    private String province;
}
