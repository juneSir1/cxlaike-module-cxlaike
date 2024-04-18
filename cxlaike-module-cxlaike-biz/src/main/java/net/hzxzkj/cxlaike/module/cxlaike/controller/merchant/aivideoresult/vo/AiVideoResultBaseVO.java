package net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.aivideoresult.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import javax.validation.constraints.*;

/**
 * ai视频生成结果 Base VO，提供给添加、修改、详细的子 VO 使用
 * 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
 */
@Data
public class AiVideoResultBaseVO {

    @Schema(description = "ai视频生成id", requiredMode = Schema.RequiredMode.REQUIRED, example = "23128")
    @NotNull(message = "ai视频生成id不能为空")
    private Long id;

    @Schema(description = "ai视频生成任务id", requiredMode = Schema.RequiredMode.REQUIRED, example = "23128")
    private Long aiTaskId;

    @Schema(description = "视频时长", requiredMode = Schema.RequiredMode.REQUIRED, example = "23128")
    private Integer duration;

    @Schema(description = "jobId", example = "32515")
    private String jobId;

    @Schema(description = "mediaURL", example = "https://www.iocoder.cn")
    private String mediaUrl;

}
