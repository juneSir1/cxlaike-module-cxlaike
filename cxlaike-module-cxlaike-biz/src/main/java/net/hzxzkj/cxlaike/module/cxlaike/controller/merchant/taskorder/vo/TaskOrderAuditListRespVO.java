package net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.taskorder.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.ToString;
import net.hzxzkj.cxlaike.framework.common.validation.InEnum;
import net.hzxzkj.cxlaike.module.cxlaike.enums.TaskType;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

import static net.hzxzkj.cxlaike.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "商家后台 - 达人作品审核 Response VO")
@Data
@ToString(callSuper = true)
public class TaskOrderAuditListRespVO {

    @Schema(description = "id", requiredMode = Schema.RequiredMode.REQUIRED, example = "24833")
    private Long id;

    @Schema(description = "任务标题", requiredMode = Schema.RequiredMode.REQUIRED)
    private String title;

    @Schema(description = "任务id")
    private Long taskId;

    @Schema(description = "任务类型(1.实探,2.云探)", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @InEnum(value = TaskType.class)
    private Integer taskType;
    /**
     * 抖音昵称
     */
    @Schema(description = "抖音昵称", requiredMode = Schema.RequiredMode.REQUIRED)
    private String dyNickname;
    /**
     * 抖音用户id
     */
    @Schema(description = "抖音用户id", requiredMode = Schema.RequiredMode.REQUIRED)
    private String dyUserId;

    @Schema(description = "抖音头像", requiredMode = Schema.RequiredMode.REQUIRED)
    private String dyAvatar;

    @Schema(description = "抖音带货等级", requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer dyGrade;

    @Schema(description = "商家审核截止时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime auditInvalidTime;

    /**
     * 状态 0.进行中 1.待审核 2.已通过 3.已驳回
     */
    @Schema(description = "状态 1.待审核 2.已通过 3.已驳回 ", requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer status;

    @Schema(description = "视频链接", requiredMode = Schema.RequiredMode.REQUIRED)
    private String videoLink;

    @Schema(description = "驳回理由")
    private String rejectReason;

    @Schema(description = "服务费用", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long fee;

    /**
     * 带货等级类型(0.lv0,1.lv1)
     */
    @Schema(description = "带货等级类型(0.lv0,1.lv1)")
    private Integer level;

    @Schema(description = "达人id",hidden = true)
    private Long memberUserId;

    @Schema(description = "openid",hidden = true)
    private String dyOpenId;
}
