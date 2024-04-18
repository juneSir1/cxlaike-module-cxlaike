package net.hzxzkj.cxlaike.module.cxlaike.utils.douyin.vo;

import lombok.Data;

/**
 * 刷新refresh_token返回类
 * @author jianan.han
 * @date 2023-08-17 11:16
 * @description
 */
@Data
public class RefreshTokenRespDTO {

    /**
     *错误码描述
     */
    private String description;
    /**
     *错误码
     */
    private Long error_code;
    /**
     *refresh_token 接口调用凭证超时时间，单位（秒)
     */
    private Long expires_in;
    /**
     *用户刷新 access_token
     */
    private String refresh_token;


}
