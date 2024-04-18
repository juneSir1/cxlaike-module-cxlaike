package net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.aivideotaskfontset.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.validation.constraints.*;

@Schema(description = "管理后台 - ai视频任务-文字设置更新 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class AiVideoTaskFontSetUpdateReqVO extends AiVideoTaskFontSetBaseVO {

    @Schema(description = "id", requiredMode = Schema.RequiredMode.REQUIRED, example = "3834")
    @NotNull(message = "id不能为空")
    private Long id;

}
