package net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.aicopywriting.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import net.hzxzkj.cxlaike.framework.common.pojo.PageParam;

@Schema(description = "管理后台 - ai文案分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class AiCopywritingPageReqVO extends PageParam {


    @Schema(description = "内容类型 1-团购推广套餐 2-产品宣传 3-门店推广 4-剧本创作", example = "1")
    private Integer contentType;

    @Schema(description = "内容描述")
    private String contentDesc;

}
