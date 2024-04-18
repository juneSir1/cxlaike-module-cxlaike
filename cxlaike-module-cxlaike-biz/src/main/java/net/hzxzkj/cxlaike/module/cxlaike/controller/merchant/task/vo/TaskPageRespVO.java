package net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.task.vo;

import static net.hzxzkj.cxlaike.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;
import lombok.Data;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * 描   述:
 *
 * @author ace
 * @version [版本号, 2023/9/6]
 * @see [相关类/方法]
 * 创建日期: 2023/9/6
 */
@Schema(description = "管理后台 - 商家发布任务 Response VO")
@Data
@ToString(callSuper = true)
public class TaskPageRespVO {

  @Schema(description = "任务id")
  private Long id;

  @Schema(description = "任务类型(1.实探,2.云探)")
  private Integer taskType;

  @Schema(description = "平台类型")
  private Integer platformType;

  @Schema(description = "任务标题")
  private String title;

  @Schema(description = "任务开始时间")
  @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
  private LocalDateTime startTime;

  @Schema(description = "任务结束时间")
  @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
  private LocalDateTime endTime;

  @Schema(description = "发布人数")
  private Integer publishNum;

  @Schema(description = "报名人数")
  private Integer applyNum;

  @Schema(description = "预计总费用(分)")
  private Long totalFee;

  @Schema(description = "任务状态")
  private Integer status;


}
