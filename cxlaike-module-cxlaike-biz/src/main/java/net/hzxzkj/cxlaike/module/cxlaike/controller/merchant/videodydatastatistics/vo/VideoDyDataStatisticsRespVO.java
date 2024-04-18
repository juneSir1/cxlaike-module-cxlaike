package net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.videodydatastatistics.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.time.LocalDateTime;
import java.util.List;

@Schema(description = "商家 - 视频纬度抖音数据统计 Response VO")
@Data
@ToString(callSuper = true)
public class VideoDyDataStatisticsRespVO {



    @Schema(description = "播放新增量")
    private Integer playCountIncrement;

    @Schema(description = "播放每日新增量")
    private List<VideoDyDataDateRespVO> playDateIncrement;

    @Schema(description = "点赞新增量")
    private Integer diggCountIncrement;

    @Schema(description = "点赞每日新增量")
    private List<VideoDyDataDateRespVO> diggDateIncrement;

    @Schema(description = "评论新增量")
    private Integer commentCountIncrement;

    @Schema(description = "评论每日新增量")
    private List<VideoDyDataDateRespVO> commentDateIncrement;

    @Schema(description = "转发新增量")
    private Integer shareCountIncrement;

    @Schema(description = "转发每日新增量")
    private List<VideoDyDataDateRespVO> shareDateIncrement;

    @Schema(description = "收藏新增量")
    private Integer collectCountIncrement;

    @Schema(description = "收藏每日新增量")
    private List<VideoDyDataDateRespVO> collectDateIncrement;


}
