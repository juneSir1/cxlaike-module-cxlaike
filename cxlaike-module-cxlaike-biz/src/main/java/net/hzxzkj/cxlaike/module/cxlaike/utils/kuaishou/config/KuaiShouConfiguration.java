package net.hzxzkj.cxlaike.module.cxlaike.utils.kuaishou.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration(proxyBeanMethods = false)
@EnableConfigurationProperties(KuaiShouProperties.class)
public class KuaiShouConfiguration {
}
