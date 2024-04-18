package net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.aicopywriting.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Schema(description = "管理后台 - ai文案创建 Request VO")
@Data
@ToString(callSuper = true)
public class AiOptimizeCopywritingVO {

    @Schema(description = "文案", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "文案不能为空")
    private String content;

    @Schema(description = "code")
    private String code;

}
