package net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.taskorder.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

import static net.hzxzkj.cxlaike.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "商家后台 - 达人作品明细 Response VO")
@Data
@ToString(callSuper = true)
public class TaskOrderDetailListRespVO {

    @Schema(description = "id", requiredMode = Schema.RequiredMode.REQUIRED, example = "24833")
    private Long id;

    @Schema(description = "任务标题", requiredMode = Schema.RequiredMode.REQUIRED)
    private String title;

    @Schema(description = "抖音昵称", requiredMode = Schema.RequiredMode.REQUIRED)
    private String dyNickname;
    /**
     * 抖音用户id
     */
    @Schema(description = "抖音用户id", requiredMode = Schema.RequiredMode.REQUIRED)
    private String dyUserId;

    @Schema(description = "抖音头像", requiredMode = Schema.RequiredMode.REQUIRED)
    private String dyAvatar;

    @Schema(description = "服务费用", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long fee;

    @Schema(description = "视频发布时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime videoPublishTime;

    @Schema(description = "视频链接", requiredMode = Schema.RequiredMode.REQUIRED)
    private String videoLink;

    @Schema(description = "视频状态 0-生成中 2-不适宜公开 4-审核中 5-公开视频", requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer videoStatus;

    /**
     * 播放数
     */
    @Schema(description = "播放数", requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer videoWatchCount;
    /**
     * 点赞数
     */
    @Schema(description = "点赞数", requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer upvoteCount;
    /**
     * 分享数
     */
    @Schema(description = "分享数", requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer shareCount;
    /**
     * 下载数
     */
    @Schema(description = "下载数", requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer downloadCount;
    /**
     * 评价数
     */
    @Schema(description = "评价数", requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer commentCount;
    /**
     * 状态 0.进行中 1.待审核 2.已通过 3.已驳回
     */
    @Schema(description = "状态 1.待审核 2.已通过 3.已驳回 ", requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer status;

}
