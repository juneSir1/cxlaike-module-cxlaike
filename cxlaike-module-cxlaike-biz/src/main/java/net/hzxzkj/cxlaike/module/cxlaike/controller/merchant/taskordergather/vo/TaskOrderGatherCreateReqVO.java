package net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.taskordergather.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.*;

@Schema(description = "商家后台 - 用户视频集合创建 Request VO")
@Data
@ToString(callSuper = true)
public class TaskOrderGatherCreateReqVO {

    @Schema(description = "类型 1-达人 2-商家", example = "1")
    private Integer orderType;

    @Schema(description = "任务id", requiredMode = Schema.RequiredMode.REQUIRED, example = "15438")
    private Long taskId;

    @Schema(description = "封面id(素材id)", example = "19245")
    private Long orderId;

    @Schema(description = "用户绑定id(抖音)", requiredMode = Schema.RequiredMode.REQUIRED, example = "22815")
    private Long accountBindId;

    @Schema(description = "外部视频id(抖音)", example = "11409")
    private String videoId;

    @Schema(description = "外部视频id(抖音)", example = "11409")
    private String itemId;


}
