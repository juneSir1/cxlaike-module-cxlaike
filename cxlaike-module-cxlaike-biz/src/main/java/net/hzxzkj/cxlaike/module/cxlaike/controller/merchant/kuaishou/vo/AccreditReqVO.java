package net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.kuaishou.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author jianan.han
 * @date 2023/10/24 下午4:33
 * @description
 */
@Data
public class AccreditReqVO {

    @Schema(description = "分组列表")
    private List<Long> groupIds;
    @Schema(description = "poiid")
    private String poiId;
    @Schema(description = "poi名称")
    private String poiName;
    @Schema(description = "有效期")
    @NotNull(message = "请选择有效期")
    private Integer validity;
    @Schema(description = "用户类型 1-达人 2-商户")
    private Integer userType;
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
    @Schema(description = "经纬度，格式：X,Y")
    private String location;
}
