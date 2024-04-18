package net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.taskordergather.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import net.hzxzkj.cxlaike.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static net.hzxzkj.cxlaike.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 用户视频集合分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class TaskOrderGatherPageReqVO extends PageParam {

    @Schema(description = "类型 1-达人 2-商家", example = "1")
    private Integer orderType;

    @Schema(description = "任务id", example = "15438")
    private Long taskId;

    @Schema(description = "封面id(素材id)", example = "19245")
    private Long orderId;

    @Schema(description = "用户绑定id(抖音)", example = "22815")
    private Long accountBindId;

    @Schema(description = "外部视频id(抖音)", example = "11409")
    private String videoId;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}
