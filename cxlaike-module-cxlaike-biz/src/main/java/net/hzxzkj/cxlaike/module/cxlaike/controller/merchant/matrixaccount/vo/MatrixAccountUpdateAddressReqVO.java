package net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.matrixaccount.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Schema(description = "商家 - 分发账号列表 Request VO")
@Data
public class MatrixAccountUpdateAddressReqVO {


    @Schema(description = "id")
    @NotNull(message = "id不能为空")
    private Long id;

    @Schema(description = "poiId")
    @NotBlank(message = "poiId不能为空")
    private String poiId;
    @Schema(description = "poi名称")
    @NotBlank(message = "poi名称不能为空")
    private String poiName;
    @Schema(description = "省code")
    private Integer provinceCode;
    @Schema(description = "省名称")
    private String provinceName;
    @Schema(description = "市code")
    private Integer cityCode;
    @Schema(description = "市名称")
    private String cityName;
    @Schema(description = "区code")
    private Integer countyCode;
    @Schema(description = "区名称")
    private String countyName;
    @Schema(description = "详细地址")
    private String address;
}
