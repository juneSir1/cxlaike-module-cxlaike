package net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.store.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * 门店 Base VO，提供给添加、修改、详细的子 VO 使用
 * 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
 */
@Data
public class StoreBaseVO {

    @Schema(description = "门店名称", requiredMode = Schema.RequiredMode.REQUIRED)
    private String name;

    @Schema(description = "门店名称", requiredMode = Schema.RequiredMode.REQUIRED)
    private String poiId;

    @Schema(description = "省", requiredMode = Schema.RequiredMode.REQUIRED)
    private String provinceName;

    @Schema(description = "市", requiredMode = Schema.RequiredMode.REQUIRED)
    private String cityName;

    @Schema(description = "区", requiredMode = Schema.RequiredMode.REQUIRED)
    private String countyName;

    @Schema(description = "详细地址", requiredMode = Schema.RequiredMode.REQUIRED)
    private String address;

    @Schema(description = "经度", requiredMode = Schema.RequiredMode.REQUIRED)
    private String longitude;

    @Schema(description = "纬度", requiredMode = Schema.RequiredMode.REQUIRED)
    private String latitude;

    @Schema(description = "状态 0-正常  1-停用", requiredMode = Schema.RequiredMode.REQUIRED, example = "0")
    private Integer status;

    @Schema(description = "市code")
    private String cityCode;

    @Schema(description = "区code")
    private String countryCode;

}
