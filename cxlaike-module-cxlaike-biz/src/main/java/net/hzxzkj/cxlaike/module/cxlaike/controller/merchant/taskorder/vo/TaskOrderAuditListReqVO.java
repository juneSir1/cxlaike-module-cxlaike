package net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.taskorder.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import net.hzxzkj.cxlaike.framework.common.pojo.PageParam;
import net.hzxzkj.cxlaike.framework.common.validation.InEnum;
import net.hzxzkj.cxlaike.module.cxlaike.enums.TaskType;

@Schema(description = "商家后台 - 达人作品审核 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class TaskOrderAuditListReqVO extends PageParam {

    @Schema(description = "任务标题", requiredMode = Schema.RequiredMode.REQUIRED)
    private String title;

    @Schema(description = "发布平台", requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer platformType;

    @Schema(description = "任务类型(1.实探,2.云探)", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @InEnum(value = TaskType.class)
    private Integer taskType;

    @Schema(description = "状态 1.待审核 2.已通过 3.已驳回 ", requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer status;

    @Schema(description = "商家id", hidden = true)
    private Long merchantId;



}
