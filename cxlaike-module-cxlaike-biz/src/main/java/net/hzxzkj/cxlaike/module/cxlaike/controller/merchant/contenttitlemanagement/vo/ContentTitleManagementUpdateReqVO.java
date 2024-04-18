package net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.contenttitlemanagement.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.validation.constraints.*;

@Schema(description = "管理后台 - 任务内容标题管理更新 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ContentTitleManagementUpdateReqVO extends ContentTitleManagementBaseVO {

    @Schema(description = "主键", requiredMode = Schema.RequiredMode.REQUIRED, example = "17003")
    @NotNull(message = "主键不能为空")
    private Long id;

}
