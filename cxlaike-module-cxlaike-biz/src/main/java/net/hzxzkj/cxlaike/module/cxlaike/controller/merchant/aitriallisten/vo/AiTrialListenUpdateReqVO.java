package net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.aitriallisten.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import javax.validation.constraints.*;

@Schema(description = "管理后台 - ai语音试听功能更新 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class AiTrialListenUpdateReqVO extends AiTrialListenBaseVO {

    @Schema(description = "主键", requiredMode = Schema.RequiredMode.REQUIRED, example = "14636")
    @NotNull(message = "主键不能为空")
    private Long id;

}
