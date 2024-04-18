package net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.taskdydatastatistics.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 任务纬度抖音数据统计 Response VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class TaskDyDataStatisticsRespVO extends TaskDyDataStatisticsBaseVO {

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    private LocalDateTime createTime;

}
