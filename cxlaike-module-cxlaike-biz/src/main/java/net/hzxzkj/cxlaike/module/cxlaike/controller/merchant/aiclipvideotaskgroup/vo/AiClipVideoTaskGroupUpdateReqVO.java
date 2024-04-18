package net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.aiclipvideotaskgroup.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.validation.constraints.*;

@Schema(description = "管理后台 - ai素材混剪视频任务-视频组设置更新 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class AiClipVideoTaskGroupUpdateReqVO extends AiClipVideoTaskGroupBaseVO {

    @Schema(description = "主键", requiredMode = Schema.RequiredMode.REQUIRED, example = "5455")
    @NotNull(message = "主键不能为空")
    private Long id;

}
