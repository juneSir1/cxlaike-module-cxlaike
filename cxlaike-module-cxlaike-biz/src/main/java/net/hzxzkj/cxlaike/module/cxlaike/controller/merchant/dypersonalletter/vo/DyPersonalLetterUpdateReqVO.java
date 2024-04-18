package net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.dypersonalletter.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.validation.constraints.*;

@Schema(description = "管理后台 - 抖音私信管理更新 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class DyPersonalLetterUpdateReqVO extends DyPersonalLetterBaseVO {

    @Schema(description = "id", requiredMode = Schema.RequiredMode.REQUIRED, example = "6314")
    @NotNull(message = "id不能为空")
    private Long id;

}
