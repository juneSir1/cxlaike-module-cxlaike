package net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.accountdydatastatistics.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDate;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import javax.validation.constraints.*;

/**
 * 账号纬度抖音数据统计 Base VO，提供给添加、修改、详细的子 VO 使用
 * 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
 */
@Data
public class AccountDyDataStatisticsBaseVO {

    @Schema(description = "类型 1-达人 2-商户", example = "2")
    private Integer dyType;

    @Schema(description = "抖音绑定id", example = "32511")
    private Long dyBindId;

    @Schema(description = "统计日期")
    private LocalDate statisticsTime;

    @Schema(description = "发布数增量(相比于前一条)")
    private Integer publishCountIncrement;

    @Schema(description = "发布数", example = "17991")
    private Integer publishCount;

    @Schema(description = "播放量增量(相比于前一条)")
    private Integer playCountIncrement;

    @Schema(description = "播放量", example = "5862")
    private Integer playCount;

    @Schema(description = "粉丝数增量(相比于前一条)")
    private Integer fansCountIncrement;

    @Schema(description = "粉丝数", example = "6019")
    private Integer fansCount;

    @Schema(description = "点赞数增量(相比于前一条)")
    private Integer diggCountIncrement;

    @Schema(description = "点赞数", example = "1333")
    private Integer diggCount;

    @Schema(description = "评论数增量(相比于前一条)")
    private Integer commentCountIncrement;

    @Schema(description = "评论数", example = "13978")
    private Integer commentCount;

    @Schema(description = "转发数(分享)增量(相比于前一条)")
    private Integer shareCountIncrement;

    @Schema(description = "转发数(分享)", example = "13024")
    private Integer shareCount;

    @Schema(description = "收藏数增量(相比于前一条)")
    private Integer collectCountIncrement;

    @Schema(description = "收藏数", example = "13889")
    private Integer collectCount;

}
