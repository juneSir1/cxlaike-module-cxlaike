package net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.taskdydatastatistics.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDate;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import javax.validation.constraints.*;

/**
 * 任务纬度抖音数据统计 Base VO，提供给添加、修改、详细的子 VO 使用
 * 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
 */
@Data
public class TaskDyDataStatisticsBaseVO {

    @Schema(description = "任务id", requiredMode = Schema.RequiredMode.REQUIRED, example = "30232")
    @NotNull(message = "任务id不能为空")
    private Long taskId;

    @Schema(description = "统计日期")
    private LocalDate statisticsTime;

    @Schema(description = "发布数增量(相比于前一条)")
    private Integer publishCountIncrement;

    @Schema(description = "发布数", requiredMode = Schema.RequiredMode.REQUIRED, example = "15267")
    @NotNull(message = "发布数不能为空")
    private Integer publishCount;

    @Schema(description = "播放量增量(相比于前一条)")
    private Integer playCountIncrement;

    @Schema(description = "播放量", requiredMode = Schema.RequiredMode.REQUIRED, example = "2738")
    @NotNull(message = "播放量不能为空")
    private Integer playCount;

    @Schema(description = "点赞数增量(相比于前一条)")
    private Integer diggCountIncrement;

    @Schema(description = "点赞数", requiredMode = Schema.RequiredMode.REQUIRED, example = "10210")
    @NotNull(message = "点赞数不能为空")
    private Integer diggCount;

    @Schema(description = "评论数增量(相比于前一条)")
    private Integer commentCountIncrement;

    @Schema(description = "评论数", requiredMode = Schema.RequiredMode.REQUIRED, example = "6041")
    @NotNull(message = "评论数不能为空")
    private Integer commentCount;

    @Schema(description = "转发数(分享)增量(相比于前一条)")
    private Integer shareCountIncrement;

    @Schema(description = "转发数(分享)", requiredMode = Schema.RequiredMode.REQUIRED, example = "13164")
    @NotNull(message = "转发数(分享)不能为空")
    private Integer shareCount;

    @Schema(description = "收藏数增量(相比于前一条)")
    private Integer collectCountIncrement;

    @Schema(description = "收藏数", requiredMode = Schema.RequiredMode.REQUIRED, example = "15683")
    @NotNull(message = "收藏数不能为空")
    private Integer collectCount;

}
