package net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.taskmaterialcorrelation.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import net.hzxzkj.cxlaike.framework.common.pojo.PageParam;
import java.time.LocalDateTime;
import org.springframework.format.annotation.DateTimeFormat;

import static net.hzxzkj.cxlaike.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 商家任务与素材关联 Excel 导出 Request VO，参数和 TaskMaterialCorrelationPageReqVO 是一致的")
@Data
public class TaskMaterialCorrelationExportReqVO {

    @Schema(description = "任务类型 1-实探 2-云探 3-矩阵", example = "1")
    private Integer taskType;

    @Schema(description = "平台类型 1-抖音", example = "2")
    private Integer platformType;

    @Schema(description = "商家任务id", example = "12886")
    private Long taskId;

    @Schema(description = "素材id", example = "647")
    private Long materialId;

    @Schema(description = "任务状态 1-未开始 2-进行中 3-已结束 4-终止", example = "2")
    private Integer taskStatus;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}
