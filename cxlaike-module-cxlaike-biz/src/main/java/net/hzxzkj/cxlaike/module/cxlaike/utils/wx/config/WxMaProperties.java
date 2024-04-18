package net.hzxzkj.cxlaike.module.cxlaike.utils.wx.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;


@Data
@ConfigurationProperties(prefix = "wx.miniapp")
@Validated
public class WxMaProperties {

    /**
     * 设置微信小程序的appid.
     */
    private String appid;

    /**
     * 设置微信小程序的Secret.
     */
    private String secret;

    private String envVersion;

}
