package net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.accountgroup.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotNull;

@Data
@ToString(callSuper = true)
public class AccountGroupTransferReqVO {

    @Schema(description = "转移id", requiredMode = Schema.RequiredMode.REQUIRED, example = "8007")
    @NotNull(message = "转移id不能为空")
    private Long fromId;


    @Schema(description = "目的id", requiredMode = Schema.RequiredMode.REQUIRED, example = "8007")
    @NotNull(message = "目的id不能为空")
    private Long toId;
}
