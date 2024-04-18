package net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.aivideotaskcover.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.validation.constraints.*;

@Schema(description = "管理后台 - ai素材混剪视频封面图片关联更新 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class AiVideoTaskCoverUpdateReqVO extends AiVideoTaskCoverBaseVO {

    @Schema(description = "主键", requiredMode = Schema.RequiredMode.REQUIRED, example = "13419")
    @NotNull(message = "主键不能为空")
    private Long id;

}
