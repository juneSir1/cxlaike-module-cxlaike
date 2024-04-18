package net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.aitriallisten.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - ai语音试听功能 Response VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class AiTrialListenRespVO extends AiTrialListenBaseVO {

    @Schema(description = "主键", requiredMode = Schema.RequiredMode.REQUIRED, example = "14636")
    private Long id;

    @Schema(description = "mediaURL", example = "https://www.iocoder.cn")
    private String mediaUrl;

    @Schema(description = "状态", example = "2")
    private Integer status;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    private LocalDateTime createTime;

}
