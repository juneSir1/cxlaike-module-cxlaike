package net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.aivideotaskfontset.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import javax.validation.constraints.*;

/**
 * ai视频任务-文字设置 Base VO，提供给添加、修改、详细的子 VO 使用
 * 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
 */
@Data
public class AiVideoTaskFontSetBaseVO {

    @Schema(description = "ai视频任务id", requiredMode = Schema.RequiredMode.REQUIRED, example = "1460")
    @NotNull(message = "ai视频任务id不能为空")
    private Long aiTaskId;

    @Schema(description = "内容值")
    private String value;

}
