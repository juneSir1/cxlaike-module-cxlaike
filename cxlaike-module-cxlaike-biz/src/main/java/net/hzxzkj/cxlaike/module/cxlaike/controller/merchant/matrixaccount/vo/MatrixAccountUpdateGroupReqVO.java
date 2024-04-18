package net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.matrixaccount.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

@Schema(description = "商家 - 分发账号列表 Request VO")
@Data
public class MatrixAccountUpdateGroupReqVO {


    @Schema(description = "id")
    @NotNull(message = "id不能为空")
    private Long id;

    @Schema(description = "分组id")
    @NotNull(message = "分组id不能为空")
    private List<Long> groupIds;

    @Schema(description = "platformType")
    @NotNull(message = "所属平台")
    private  Integer platformType;
}
