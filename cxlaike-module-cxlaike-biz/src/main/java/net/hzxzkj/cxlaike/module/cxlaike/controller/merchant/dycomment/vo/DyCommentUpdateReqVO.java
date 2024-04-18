package net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.dycomment.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.validation.constraints.*;

@Schema(description = "管理后台 - 抖音评论管理更新 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class DyCommentUpdateReqVO extends DyCommentBaseVO {

    @Schema(description = "id", requiredMode = Schema.RequiredMode.REQUIRED, example = "30405")
    @NotNull(message = "id不能为空")
    private Long id;

}
