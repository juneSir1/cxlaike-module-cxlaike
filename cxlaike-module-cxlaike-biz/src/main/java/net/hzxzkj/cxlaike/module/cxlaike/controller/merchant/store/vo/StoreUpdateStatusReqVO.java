package net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.store.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import net.hzxzkj.cxlaike.framework.common.enums.CommonStatusEnum;
import net.hzxzkj.cxlaike.framework.common.validation.InEnum;

import javax.validation.constraints.NotNull;

@Schema(description = "商户后台 - 门店更新状态 Request VO")
@Data
public class StoreUpdateStatusReqVO {
    @Schema(description = "门店编号",requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "门店编号不能为空")
    private Long id;

    @Schema(description = "门店状态 ",requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "门店状态不能为空")
    @InEnum(value = CommonStatusEnum.class , message = "修改状态必须是...")
    private Integer status;
}
