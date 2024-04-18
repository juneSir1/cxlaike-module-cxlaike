package net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.contenttitlemanagement.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import javax.validation.constraints.*;

/**
 * 任务内容标题管理 Base VO，提供给添加、修改、详细的子 VO 使用
 * 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
 */
@Data
public class ContentTitleManagementBaseVO {

    @Schema(description = "任务id", requiredMode = Schema.RequiredMode.REQUIRED, example = "10586")
    @NotNull(message = "任务id不能为空")
    private Long taskId;

    @Schema(description = "视频组id", example = "14770")
    private Long videoGroupId;

    @Schema(description = "口播文案", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "口播文案不能为空")
    private String contentTitle;

}
