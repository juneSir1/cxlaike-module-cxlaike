package net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.taskordergather.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import javax.validation.constraints.*;

/**
 * 用户视频集合 Base VO，提供给添加、修改、详细的子 VO 使用
 * 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
 */
@Data
public class TaskOrderGatherBaseVO {

    @Schema(description = "类型 1-达人 2-商家", example = "1")
    private Integer orderType;

    @Schema(description = "任务id", requiredMode = Schema.RequiredMode.REQUIRED, example = "15438")
    @NotNull(message = "任务id不能为空")
    private Long taskId;

    @Schema(description = "封面id(素材id)", example = "19245")
    private Long orderId;

    @Schema(description = "用户绑定id(抖音)", requiredMode = Schema.RequiredMode.REQUIRED, example = "22815")
    @NotNull(message = "用户绑定id(抖音)不能为空")
    private Long accountBindId;

    @Schema(description = "外部视频id(抖音)", example = "11409")
    private String videoId;

}
