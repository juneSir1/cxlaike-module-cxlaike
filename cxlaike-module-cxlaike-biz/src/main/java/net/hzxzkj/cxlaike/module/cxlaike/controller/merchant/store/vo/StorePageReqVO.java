package net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.store.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import net.hzxzkj.cxlaike.framework.common.pojo.PageParam;

import javax.validation.constraints.NotNull;


@Schema(description = "商户后台 - 门店分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class StorePageReqVO extends PageParam {

    @Schema(description = "门店名称")
    private String name;

    @Schema(description = "详细地址")
    private String address;

    @Schema(description = "状态 0-正常  1-停用")
    private Integer status;
}
