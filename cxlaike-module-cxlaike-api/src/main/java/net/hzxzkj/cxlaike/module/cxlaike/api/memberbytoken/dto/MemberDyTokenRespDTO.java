package net.hzxzkj.cxlaike.module.cxlaike.api.memberbytoken.dto;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author jianan.han
 * @date 2023/10/20 上午10:53
 * @description
 */
@Data
public class MemberDyTokenRespDTO {

    /**
     * id
     */
    private Long id;
    /**
     * 抖音凭证
     */
    private String dyAccessToken;
    /**
     * access_token 接口调用凭证超时时间
     */
    private LocalDateTime dyExpiresTime;
    /**
     * 抖音用户刷新access_token
     */
    private String dyRefreshToken;
    /**
     * refresh_token 凭证超时时间
     */
    private LocalDateTime dyRefreshExpiresTime;
    /**
     * 抖音授权状态 1-已授权 2-授权失效
     */
    private Integer dyAccreditStatus;
    /**
     * 抖音授权时间
     */
    private LocalDateTime dyAccreditTime;
    /**
     * 短信提醒时间
     */
    private LocalDateTime smsRemindTime;
}
