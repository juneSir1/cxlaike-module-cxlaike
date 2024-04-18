package net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.aivideotaskmaterial.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import javax.validation.constraints.*;

@Schema(description = "管理后台 - ai视频任务素材更新 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class AiVideoTaskMaterialUpdateReqVO extends AiVideoTaskMaterialBaseVO {

    @Schema(description = "主键", requiredMode = Schema.RequiredMode.REQUIRED, example = "22443")
    @NotNull(message = "主键不能为空")
    private Long id;

}
