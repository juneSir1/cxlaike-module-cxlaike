package net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.dypersonalletteruser.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 抖音私信用户管理 Response VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class DyPersonalLetterUserRespVO extends DyPersonalLetterUserBaseVO {

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    private LocalDateTime createTime;

    @Schema(description = "私信帐号")
    private String userNickName;

    @Schema(description = "私信帐号头像")
    private String userAvatar;

    @Schema(description = "id")
    private Long id;

    @Schema(description = "会话内容")
    private String text;

}
