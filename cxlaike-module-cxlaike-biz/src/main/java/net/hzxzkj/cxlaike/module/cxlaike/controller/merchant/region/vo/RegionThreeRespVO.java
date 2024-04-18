package net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.region.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "管理后台 - 地区 Response VO")
@Data
public class RegionThreeRespVO {

    @Schema(description = "主键id", requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer id;

    @Schema(description = "省名称")
    private String name;


}
