package net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.dypersonalletter.vo;

import lombok.*;
import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.NotBlank;

@Schema(description = "管理后台 - 抖音私信管理创建 Request VO")
@Data
@ToString(callSuper = true)
public class DyPersonalLetterCreateReqVO {

    @Schema(description = "会话 ID", example = "9728")
    @NotBlank(message = "会话id不能为空")
    private String conversationShortId;

    @Schema(description = "消息")
    @NotBlank(message = "请输入内容")
    private String text;
}
