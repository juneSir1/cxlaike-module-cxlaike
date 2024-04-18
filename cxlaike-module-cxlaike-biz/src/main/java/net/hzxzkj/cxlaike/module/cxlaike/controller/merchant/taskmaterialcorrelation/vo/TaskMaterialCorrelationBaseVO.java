package net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.taskmaterialcorrelation.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import javax.validation.constraints.*;

/**
 * 商家任务与素材关联 Base VO，提供给添加、修改、详细的子 VO 使用
 * 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
 */
@Data
public class TaskMaterialCorrelationBaseVO {

    @Schema(description = "任务类型 1-实探 2-云探 3-矩阵", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotNull(message = "任务类型 1-实探 2-云探 3-矩阵不能为空")
    private Integer taskType;

    @Schema(description = "平台类型 1-抖音", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @NotNull(message = "平台类型 1-抖音不能为空")
    private Integer platformType;

    @Schema(description = "商家任务id", requiredMode = Schema.RequiredMode.REQUIRED, example = "12886")
    @NotNull(message = "商家任务id不能为空")
    private Long taskId;

    @Schema(description = "素材id", example = "647")
    private Long materialId;

    @Schema(description = "任务状态 1-未开始 2-进行中 3-已结束 4-终止", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @NotNull(message = "任务状态 1-未开始 2-进行中 3-已结束 4-终止不能为空")
    private Integer taskStatus;

}
