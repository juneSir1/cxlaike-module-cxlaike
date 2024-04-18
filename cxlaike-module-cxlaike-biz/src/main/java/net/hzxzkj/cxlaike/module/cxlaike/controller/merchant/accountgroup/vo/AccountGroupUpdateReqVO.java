package net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.accountgroup.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.validation.constraints.NotNull;

@Schema(description = "管理后台 - 商户抖音分组管理更新 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class AccountGroupUpdateReqVO extends AccountGroupBaseVO {

    @Schema(description = "id", requiredMode = Schema.RequiredMode.REQUIRED, example = "8007")
    @NotNull(message = "id不能为空")
    private Long id;

}
