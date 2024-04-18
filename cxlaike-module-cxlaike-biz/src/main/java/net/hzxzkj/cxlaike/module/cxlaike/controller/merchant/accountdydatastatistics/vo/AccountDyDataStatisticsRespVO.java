package net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.accountdydatastatistics.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.List;

@Data
@ToString(callSuper = true)
public class AccountDyDataStatisticsRespVO {

    @Schema(description = "粉丝新增量")
    private Integer countIncrement;

    @Schema(description = "粉丝每日新增量")
    private List<AccountDyDataDateRespVO> fanDateIncrement;



    @Schema(description = "分发视频")
    private Integer publishCount;

    @Schema(description = "分发视频每日量")
    private List<AccountDyDataDateRespVO> publishCountDate;

    @Schema(description = "播放新增量")
    private Integer playCountIncrement;

    @Schema(description = "播放每日新增量")
    private List<AccountDyDataDateRespVO> playDateIncrement;

    @Schema(description = "点赞新增量")
    private Integer diggCountIncrement;

    @Schema(description = "点赞每日新增量")
    private List<AccountDyDataDateRespVO> diggDateIncrement;

    @Schema(description = "评论新增量")
    private Integer commentCountIncrement;

    @Schema(description = "评论每日新增量")
    private List<AccountDyDataDateRespVO> commentDateIncrement;

    @Schema(description = "转发新增量")
    private Integer shareCountIncrement;

    @Schema(description = "转发每日新增量")
    private List<AccountDyDataDateRespVO> shareDateIncrement;




}
