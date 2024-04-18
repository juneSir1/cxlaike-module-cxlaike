package net.hzxzkj.cxlaike.module.cxlaike.utils.wx.vo;

import lombok.Data;

/**
 * @author jianan.han
 * @date 2023/10/8 上午9:48
 * @description
 */
@Data
public class TokenRespVO {

    /**
     *  token
     */
    private String access_token;
    /**
     *  有效期
     */
    private Long expires_in;
}
