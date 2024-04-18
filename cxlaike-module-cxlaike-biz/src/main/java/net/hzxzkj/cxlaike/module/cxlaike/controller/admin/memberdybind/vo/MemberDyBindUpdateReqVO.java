package net.hzxzkj.cxlaike.module.cxlaike.controller.admin.memberdybind.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author jianan.han
 * @date 2023/9/12 下午3:04
 * @description
 */
@Data
public class MemberDyBindUpdateReqVO {

    @Schema(description = "id",requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "id不能为空")
    private Long id;

//    @Schema(description = "2-通过 3-拒绝",requiredMode = Schema.RequiredMode.REQUIRED)
//    @NotNull(message = "请选择通过/驳回")
//    private Integer auditStatus;

    @Schema(description = "抖音用户id",requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "抖音用户id不能为空")
    private String userId;

    @Schema(description = "粉丝数",requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "粉丝数不能为空")
    private Integer fansCount;

    @Schema(description = "点赞数",requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "点赞数不能为空")
    private Integer giveLikeCount;

    @Schema(description = "作品数",requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "作品数不能为空")
    private Integer worksCount;

    @Schema(description = "省code",requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "省code不能为空")
    private Integer provinceCode;

    @Schema(description = "省名称",requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "省名称不能为空")
    private String provinceName;

    @Schema(description = "市code",requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "市code不能为空")
    private Integer cityCode;

    @Schema(description = "市名称",requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "市名称不能为空")
    private String cityName;

    @Schema(description = "区code",requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer countyCode;

    @Schema(description = "区名称",requiredMode = Schema.RequiredMode.REQUIRED)
    private String countyName;

}
