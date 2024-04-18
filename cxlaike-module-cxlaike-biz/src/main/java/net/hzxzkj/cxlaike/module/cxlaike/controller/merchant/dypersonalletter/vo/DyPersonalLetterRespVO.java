package net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.dypersonalletter.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 抖音私信管理 Response VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class DyPersonalLetterRespVO extends DyPersonalLetterBaseVO {

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    private LocalDateTime createTime;

    @Schema(description = "对方头像")
    private String fromUserAvatar;

    @Schema(description = "对方昵称")
    private String fromNickName;

    @Schema(description = "自己头像")
    private String toUserAvatar;

    @Schema(description = "自己昵称")
    private String toNickName;

}
