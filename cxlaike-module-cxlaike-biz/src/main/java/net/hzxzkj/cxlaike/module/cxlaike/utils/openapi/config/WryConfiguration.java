package net.hzxzkj.cxlaike.module.cxlaike.utils.openapi.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration(proxyBeanMethods = false)
@EnableConfigurationProperties(WryProperties.class)
public class WryConfiguration {
}
