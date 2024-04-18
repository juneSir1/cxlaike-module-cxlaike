package net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.region.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Schema(description = "管理后台 - 地区 Response VO")
@Data
public class RegionTwoRespVO {

    @Schema(description = "主键id", requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer id;

    @Schema(description = "区县名称")
    private String name;

    @Schema(description = "区县List")
    List<RegionThreeRespVO> children;
}
