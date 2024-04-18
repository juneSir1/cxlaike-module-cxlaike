package net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.videodydatastatistics.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDate;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import javax.validation.constraints.*;

/**
 * 视频纬度抖音数据统计 Base VO，提供给添加、修改、详细的子 VO 使用
 * 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
 */
@Data
public class VideoDyDataStatisticsBaseVO {

    @Schema(description = "订单id", requiredMode = Schema.RequiredMode.REQUIRED, example = "30169")
    @NotNull(message = "订单id不能为空")
    private Long orderId;

    @Schema(description = "类型 1-达人 2-商户", example = "1")
    private Integer dyType;

    @Schema(description = "抖音绑定id", example = "10863")
    private Long dyBindId;

    @Schema(description = "视频链接")
    private String videoLink;

    @Schema(description = "统计日期")
    private LocalDate statisticsTime;

    @Schema(description = "播放量增量(相比于前一条)")
    private Integer playCountIncrement;

    @Schema(description = "播放量", example = "30104")
    private Integer playCount;

    @Schema(description = "点赞数增量(相比于前一条)")
    private Integer diggCountIncrement;

    @Schema(description = "点赞数", example = "8246")
    private Integer diggCount;

    @Schema(description = "评论数增量(相比于前一条)")
    private Integer commentCountIncrement;

    @Schema(description = "评论数", example = "17104")
    private Integer commentCount;

    @Schema(description = "转发数(分享)增量(相比于前一条)")
    private Integer shareCountIncrement;

    @Schema(description = "转发数(分享)", example = "4059")
    private Integer shareCount;

    @Schema(description = "收藏数增量(相比于前一条)")
    private Integer collectCountIncrement;

    @Schema(description = "收藏数", example = "24461")
    private Integer collectCount;

    @Schema(description = "30天平均播放时长")
    private Object playDuration;

}
