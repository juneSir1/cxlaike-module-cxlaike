package net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.template.vo;

import lombok.*;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "管理后台 - ai素材混剪视频模板创建 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class TemplateCreateReqVO extends TemplateBaseVO {


    @Schema(description = "用户id", hidden = true)
    private Long userId;
}
