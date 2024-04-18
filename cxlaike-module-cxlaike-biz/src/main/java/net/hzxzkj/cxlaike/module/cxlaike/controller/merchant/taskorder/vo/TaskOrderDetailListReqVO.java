package net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.taskorder.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.ToString;
import net.hzxzkj.cxlaike.framework.common.pojo.PageParam;
import net.hzxzkj.cxlaike.framework.common.validation.InEnum;
import net.hzxzkj.cxlaike.module.cxlaike.enums.TaskType;
import net.hzxzkj.cxlaike.module.cxlaike.enums.taskorder.VideoStatusEnum;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

import static net.hzxzkj.cxlaike.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "商家后台 - 达人作品明细 Response VO")
@Data
@ToString(callSuper = true)
public class TaskOrderDetailListReqVO extends PageParam {

    @Schema(description = "任务标题", requiredMode = Schema.RequiredMode.REQUIRED)
    private String title;

    @Schema(description = "视频发布时间开始")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime videoPublishTimeStart;

    @Schema(description = "视频发布时间结束")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime videoPublishTimeEnd;

    @Schema(description = "发布平台", requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer platformType;

    @Schema(description = "视频状态 2-不适宜公开 4-审核中 5-公开视频", requiredMode = Schema.RequiredMode.REQUIRED)
    @InEnum(value = VideoStatusEnum.class)
    private Integer videoStatus;

    @Schema(description = "任务类型(1.实探,2.云探)", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @InEnum(value = TaskType.class)
    private Integer taskType;
    /**
     * 播放数
     */
    @Schema(description = "播放数开始", requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer watchCountStart;

    @Schema(description = "播放数结束", requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer watchCountEnd;
    /**
     * 点赞数
     */
    @Schema(description = "点赞数开始", requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer upvoteCountStart;

    @Schema(description = "点赞数结束", requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer upvoteCountEnd;

    @Schema(description = "商家id", hidden = true)
    private Long merchantId;

    @Schema(description = "状态 0.进行中 1.待审核 2.已通过 3.已驳回 4.已取消")
    private Integer status;


}
