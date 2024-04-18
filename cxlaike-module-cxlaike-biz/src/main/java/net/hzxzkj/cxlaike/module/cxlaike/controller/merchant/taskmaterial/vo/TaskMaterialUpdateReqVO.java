package net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.taskmaterial.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import javax.validation.constraints.*;

@Schema(description = "管理后台 - 商家任务素材更新 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class TaskMaterialUpdateReqVO extends TaskMaterialBaseVO {

    @Schema(description = "主键", requiredMode = Schema.RequiredMode.REQUIRED, example = "15781")
    @NotNull(message = "主键不能为空")
    private Long id;

}
