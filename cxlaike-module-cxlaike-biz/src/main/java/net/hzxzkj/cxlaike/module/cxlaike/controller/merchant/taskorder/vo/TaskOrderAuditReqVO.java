package net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.taskorder.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * 商家审核订单参数
 * @author jianan.han
 * @date 2023/9/6 下午3:35
 * @description
 */
@Data
public class TaskOrderAuditReqVO {

    @Schema(description = "id", requiredMode = Schema.RequiredMode.REQUIRED, example = "24833")
    @NotNull(message = "id不能为空")
    private Long id;

    @Schema(description = "2.通过 3.驳回", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @NotNull(message = "状态不能为空")
    private Integer status;

    @Schema(description = "驳回理由")
    private String rejectReason;
}
