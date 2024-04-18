package net.hzxzkj.cxlaike.module.cxlaike.utils.douyin.vo;

import lombok.Data;

/**
 * @author jianan.han
 * @date 2023-08-17 11:16
 * @description
 */
@Data
public class AccessTokenRespDTO {
    /**
     *接口调用凭证
     */
    private String access_token;

    /**
     *错误码描述
     */
    private String description;
    /**
     *错误码
     */
    private Long error_code;
    /**
     *access_token 接口调用凭证超时时间，单位（秒)
     */
    private Long expires_in;
    /**
     *授权用户唯一标识
     */
    private String open_id;
    /**
     *refresh_token 凭证超时时间，单位（秒)
     */
    private Long refresh_expires_in;
    /**
     *用户刷新 access_token
     */
    private String refresh_token;
    /**
     *用户授权的作用域（Scope），使用逗号（,）分隔，开放平台几乎每个接口都需要特定的 Scope。
     */
    private String scope;


}
