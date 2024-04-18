package net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.taskorder.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

@Schema(description = "商家后台 - 达人作品审核 Response VO")
@Data
public class TaskOrderAuditListCountRespVO implements Serializable {

    private static final long serialVersionUID = 4791422333497528855L;
    @Schema(description = "全部")
    private Long totalCount;

    @Schema(description = "待审核")
    private Long toAuditCount;

    @Schema(description = "审核通过")
    private Long auditCount;

    @Schema(description = "未通过")
    private Long unAuditCount;

}
