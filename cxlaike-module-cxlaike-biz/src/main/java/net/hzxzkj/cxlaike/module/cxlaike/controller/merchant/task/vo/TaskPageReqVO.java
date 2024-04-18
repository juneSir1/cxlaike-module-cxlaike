package net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.task.vo;

import io.swagger.v3.oas.annotations.Parameter;
import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import net.hzxzkj.cxlaike.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static net.hzxzkj.cxlaike.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 商家发布任务分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class TaskPageReqVO extends PageParam {

    @Parameter(description = "任务类型(1.实探,2.云探)", example = "1")
    private Integer taskType;

    @Parameter(description = "平台类型", example = "1")
    private Integer platformType;

    @Parameter(description = "任务标题or任务id")
    private String content;

    @Parameter(description = "任务发布时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] time;

    @Parameter(description = "任务状态", example = "1")
    private Integer status;

}
