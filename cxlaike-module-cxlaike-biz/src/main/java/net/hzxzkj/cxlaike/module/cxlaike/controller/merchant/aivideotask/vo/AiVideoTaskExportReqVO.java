package net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.aivideotask.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import net.hzxzkj.cxlaike.framework.common.pojo.PageParam;
import java.time.LocalDateTime;
import org.springframework.format.annotation.DateTimeFormat;

import static net.hzxzkj.cxlaike.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - ai视频任务 Excel 导出 Request VO，参数和 AiVideoTaskPageReqVO 是一致的")
@Data
public class AiVideoTaskExportReqVO {

    @Schema(description = "任务标题")
    private String title;

    @Schema(description = "视频状态", example = "1")
    private Integer status;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}
