package net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.taskordergather.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import javax.validation.constraints.*;

@Schema(description = "管理后台 - 用户视频集合更新 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class TaskOrderGatherUpdateReqVO extends TaskOrderGatherBaseVO {

    @Schema(description = "主键", requiredMode = Schema.RequiredMode.REQUIRED, example = "21163")
    @NotNull(message = "主键不能为空")
    private Long id;

}
