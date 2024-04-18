package net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.template.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.validation.constraints.*;

@Schema(description = "管理后台 - ai素材混剪视频模板更新 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class TemplateUpdateReqVO extends TemplateBaseVO {

    @Schema(description = "主键", requiredMode = Schema.RequiredMode.REQUIRED, example = "25116")
    @NotNull(message = "主键不能为空")
    private Long id;

}
