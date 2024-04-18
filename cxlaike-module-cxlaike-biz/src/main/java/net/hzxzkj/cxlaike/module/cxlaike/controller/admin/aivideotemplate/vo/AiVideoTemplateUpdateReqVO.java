package net.hzxzkj.cxlaike.module.cxlaike.controller.admin.aivideotemplate.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import javax.validation.constraints.*;

@Schema(description = "管理后台 - ai视频模板更新 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class AiVideoTemplateUpdateReqVO extends AiVideoTemplateBaseVO {

    @Schema(description = "id", requiredMode = Schema.RequiredMode.REQUIRED, example = "1338")
    @NotNull(message = "id不能为空")
    private Long id;

}
