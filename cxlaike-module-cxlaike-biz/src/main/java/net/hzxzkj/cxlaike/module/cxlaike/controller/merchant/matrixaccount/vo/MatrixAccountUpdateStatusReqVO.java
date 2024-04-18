package net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.matrixaccount.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Schema(description = "商家 - 分发账号列表 Request VO")
@Data
public class MatrixAccountUpdateStatusReqVO {


    @Schema(description = "id")
    @NotNull(message = "id不能为空")
    private Long id;

    @Schema(description = "账号状态 1-启用 2-停用 ")
    @NotNull(message = "账号状态不能为空")
    private Integer status;
}
