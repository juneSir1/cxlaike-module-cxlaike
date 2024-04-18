package net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.clientcase.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import javax.validation.constraints.*;

@Schema(description = "管理后台 - 客户案例更新 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ClientCaseUpdateReqVO extends ClientCaseBaseVO {

    @Schema(description = "id", requiredMode = Schema.RequiredMode.REQUIRED, example = "24895")
    @NotNull(message = "id不能为空")
    private Long id;

}
