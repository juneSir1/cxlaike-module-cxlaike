package net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.accountgroup.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @author jianan.han
 * @date 2023/10/23 下午4:30
 * @description
 */
@Data
public class MatrixAccountListReqVO {

    @Schema(description = "分组id")
    private Long id;

    @Schema(description = "昵称")
    private String dyNickname;

    @Schema(description = "抖音授权状态 1-已授权 2-授权失效")
    private Integer dyAccreditStatus;

    @Schema(description = "平台1抖音 2快手")
    private Integer platformType;

}
