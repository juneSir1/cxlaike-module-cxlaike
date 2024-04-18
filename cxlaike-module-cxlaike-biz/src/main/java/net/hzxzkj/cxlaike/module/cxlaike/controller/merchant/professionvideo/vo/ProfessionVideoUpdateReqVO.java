package net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.professionvideo.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import javax.validation.constraints.*;

@Schema(description = "管理后台 - 精选行业视频更新 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ProfessionVideoUpdateReqVO extends ProfessionVideoBaseVO {

    @Schema(description = "id", requiredMode = Schema.RequiredMode.REQUIRED, example = "25270")
    @NotNull(message = "id不能为空")
    private Long id;

}
