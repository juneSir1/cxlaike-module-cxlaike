package net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.task.vo;

import static net.hzxzkj.cxlaike.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;
import java.util.Set;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Data;
import net.hzxzkj.cxlaike.framework.common.validation.InEnum;
import net.hzxzkj.cxlaike.module.cxlaike.enums.AiClipType;
import net.hzxzkj.cxlaike.module.cxlaike.enums.PlatformType;
import net.hzxzkj.cxlaike.module.cxlaike.enums.TaskType;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * 商家发布任务 Base VO，提供给添加、修改、详细的子 VO 使用
 * 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
 */
@Data
public class TaskBaseVO {

  @Schema(description = "任务类型(1.实探,2.云探)", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
  @NotNull(message = "任务类型(1.实探,2.云探)不能为空")
  @InEnum(value = TaskType.class)
  private Integer taskType;

  @Schema(description = "平台类型", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
  @NotNull(message = "平台类型不能为空")
  @InEnum(value = PlatformType.class)
  private Integer platformType;

  @Schema(description = "任务标题", requiredMode = Schema.RequiredMode.REQUIRED)
  @NotNull(message = "任务标题不能为空")
  private String title;

  @Schema(description = "内容标题", requiredMode = Schema.RequiredMode.REQUIRED)
  private String contentTitle;

  @Schema(description = "任务开始时间")
  @NotNull(message = "任务开始时间不能为空")
  @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
  private LocalDateTime startTime;

  @Schema(description = "任务结束时间")
  @NotNull(message = "任务结束时间不能为空")
  @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
  private LocalDateTime endTime;

  @Schema(description = "团购链接", requiredMode = Schema.RequiredMode.REQUIRED, example = "https://www.iocoder.cn")
  @NotNull(message = "团购链接不能为空")
  private String url;

  @Schema(description = "门店id集合")
  @NotNull(message = "门店不能为空")
  private Set<Long> shopIds;

  @Schema(description = "任务照片")
  @NotNull(message = "任务照片不能为空")
  private String taskPhoto;

  @Schema(description = "话题要求")
  private String topicRequire;

  @Schema(description = "粉丝数量要求")
  @Size(max = 8, message = "粉丝数量要求最大长度为千万级别")
  private String fansNumRequire;

  @Schema(description = "达人地区要求")
  @NotNull(message = "达人地区要求不能为空")
  private String areaRequire;

  @Schema(description = "商品佣金比例")
  private String commissionRatio;

  @Schema(description = "视频剪辑类型", example = "1")
  @InEnum(value = AiClipType.class)
  private Integer clipType;

  @Schema(description = "是否到店")
  private Boolean isShop;

  @Schema(description = "任务说明", example = "你说的对")
  @Size(max = 900, message = "任务说明最大长度为 {max}")
  private String description;

  @Schema(description = "群二维码照片")
  private String groupPhoto;

//  @Schema(description = "是否需要审核")
//  @NotNull(message = "是否需要审核不能为空")
//  private Boolean isAudit;


}
