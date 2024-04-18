package net.hzxzkj.cxlaike.module.cxlaike.utils.wx.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration(proxyBeanMethods = false)
@EnableConfigurationProperties(WxMaProperties.class)
public class WxMaConfiguration {
}
