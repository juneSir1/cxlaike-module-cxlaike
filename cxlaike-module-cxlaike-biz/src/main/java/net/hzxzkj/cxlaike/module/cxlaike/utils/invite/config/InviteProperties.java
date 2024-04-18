package net.hzxzkj.cxlaike.module.cxlaike.utils.invite.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

import java.util.Collections;
import java.util.List;

/**
 * Xss 配置属性
 *
 * @author 宵征源码
 */
@ConfigurationProperties(prefix = "cxlaike.invite")
@Validated
@Data
public class InviteProperties {


    /**
     * 需要排除的 URL，默认为空
     */
    private List<String> skipUrls = Collections.emptyList();

}
