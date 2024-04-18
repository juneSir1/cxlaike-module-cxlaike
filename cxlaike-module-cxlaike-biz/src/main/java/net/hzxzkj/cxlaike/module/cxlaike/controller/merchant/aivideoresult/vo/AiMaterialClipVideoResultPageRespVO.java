package net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.aivideoresult.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - ai视频生成结果 Response VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class AiMaterialClipVideoResultPageRespVO extends AiVideoResultBaseVO {

    @Schema(description = "主键", requiredMode = Schema.RequiredMode.REQUIRED, example = "7690")
    private Long id;

    @Schema(description = "状态", example = "2")
    private Integer status;

    @Schema(description = "视频质量（0，无重复，1，低重复，2普通）", example = "2")
    private Integer videoQuality;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    private LocalDateTime createTime;

}
