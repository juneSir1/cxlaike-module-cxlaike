package net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.profession.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.time.LocalDateTime;
import java.util.List;

@Schema(description = "商家后台 - 行业 Response VO")
@Data
@ToString(callSuper = true)
public class ProfessionRespVO {

    @Schema(description = "code")
    private String code;

    @Schema(description = "名称")
    private String name;

    @Schema(description = "下级行业")
    private List<ProfessionRespVO> children;
}
