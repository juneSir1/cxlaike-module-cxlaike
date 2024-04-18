package net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.aisystemarg.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import javax.validation.constraints.*;

@Schema(description = "用户 APP - 来客系统参数更新 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class AiSystemArgUpdateReqVO extends AiSystemArgBaseVO {

    @Schema(description = "主键", requiredMode = Schema.RequiredMode.REQUIRED, example = "6283")
    @NotNull(message = "主键不能为空")
    private Long id;

}
