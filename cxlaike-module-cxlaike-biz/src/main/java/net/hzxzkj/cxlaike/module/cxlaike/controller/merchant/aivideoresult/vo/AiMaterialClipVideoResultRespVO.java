package net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.aivideoresult.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import net.hzxzkj.cxlaike.framework.common.pojo.PageResult;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - ai视频生成结果 Response VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class AiMaterialClipVideoResultRespVO extends AiVideoResultBaseVO {

    @Schema(description = "无重复数量")
    private Integer noRepeatNum;

    @Schema(description = "低重复数量")
    private Integer lowRepeatNum;

    @Schema(description = "普通数量")
    private Integer highRepeatNum;

    @Schema(description = "视频总数量", example = "2")
    private Integer videoNum;

    @Schema(description = "视频已完成数量", example = "2")
    private Integer completedNum;

    @Schema(description = "视频分页列表对象", requiredMode = Schema.RequiredMode.REQUIRED)
    private PageResult<AiMaterialClipVideoResultPageRespVO> pageRespVO;

}
