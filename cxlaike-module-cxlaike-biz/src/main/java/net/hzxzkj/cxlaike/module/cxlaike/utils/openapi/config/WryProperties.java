package net.hzxzkj.cxlaike.module.cxlaike.utils.openapi.config;

import lombok.Data;
import org.hibernate.validator.constraints.URL;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotEmpty;

@ConfigurationProperties(prefix = "wry")
@Validated
@Data
public class WryProperties {


    /**
     * 资源组名称
     */
    @NotEmpty(message = "资源组名称不能为空")
    private String resourceName;

    /**
     * 模型部署的名称
     */
    @NotEmpty(message = "模型部署的名称不能为空")
    private String deploymentName;

    /**
     * 账户api-key
     */
    @NotEmpty(message = "账户api-key不能为空")
    private String apiKey;

    /**
     * 版本号
     */
    @NotEmpty(message = "版本号不能为空")
    private String apiVersion;

    @NotEmpty(message = "生成文案描述不能为空")
    private String generateContentOne;

    @NotEmpty(message = "生成文案描述不能为空")
    private String generateContentTwo;

    @NotEmpty(message = "优化文案描述不能为空")
    private String optimizeContent;

    @NotEmpty(message = "生成文案描述不能为空")
    private String generateContentThree;

    @NotEmpty(message = "生成文案描述不能为空")
    private String generateContentFour;

    @NotEmpty(message = "生成文案描述不能为空")
    private String generateContentSeven;


}
