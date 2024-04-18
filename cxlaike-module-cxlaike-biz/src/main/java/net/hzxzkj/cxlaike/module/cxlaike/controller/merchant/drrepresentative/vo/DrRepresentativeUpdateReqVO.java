package net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.drrepresentative.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import javax.validation.constraints.*;

@Schema(description = "管理后台 - 达人探店代更新 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class DrRepresentativeUpdateReqVO extends DrRepresentativeBaseVO {

    @Schema(description = "id", requiredMode = Schema.RequiredMode.REQUIRED, example = "22133")
    @NotNull(message = "id不能为空")
    private Long id;

}
