package net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.store.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotNull;

@Schema(description = "商户后台 - 门店更新 Request VO")
@Data
@ToString(callSuper = true)
public class StoreUpdateReqVO {

    @Schema(description = "id", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "id不能为空")
    private Long id;

    @Schema(description = "经度", requiredMode = Schema.RequiredMode.REQUIRED)
    private String longitude;

    @Schema(description = "纬度", requiredMode = Schema.RequiredMode.REQUIRED)
    private String latitude;

    @Schema(description = "门店名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "门店名称不能为空")
    private String name;
    @Schema(description = "poiid", requiredMode = Schema.RequiredMode.REQUIRED)
    private String poiId;

    @Schema(description = "省", requiredMode = Schema.RequiredMode.REQUIRED)
    private String provinceName;

    @Schema(description = "市", requiredMode = Schema.RequiredMode.REQUIRED)
    private String cityName;

    @Schema(description = "区", requiredMode = Schema.RequiredMode.REQUIRED)
    private String countyName;

    @Schema(description = "详细地址", requiredMode = Schema.RequiredMode.REQUIRED)
    private String address;

    @Schema(description = "市code")
    private String cityCode;

    @Schema(description = "区code")
    private String countryCode;

}
