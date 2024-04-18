package net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.region.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Schema(description = "管理后台 - 地区 Response VO")
@Data
public class RegionRespVO  {

    @Schema(description = "主键id", requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer id;

    @Schema(description = "市名称")
    private String name;

    @Schema(description = "父级id",accessMode = Schema.AccessMode.READ_ONLY)
    private Integer pid;

    @Schema(description = "市List")
    List<RegionTwoRespVO> children;

}
