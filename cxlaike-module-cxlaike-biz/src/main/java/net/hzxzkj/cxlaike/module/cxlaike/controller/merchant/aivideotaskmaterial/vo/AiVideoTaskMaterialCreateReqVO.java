package net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.aivideotaskmaterial.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.*;

@Schema(description = "管理后台 - ai视频任务素材创建 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class AiVideoTaskMaterialCreateReqVO extends AiVideoTaskMaterialBaseVO {

    @Schema(description = "ai视频任务主键", example = "29710")
    private Long aiTaskId;

}
