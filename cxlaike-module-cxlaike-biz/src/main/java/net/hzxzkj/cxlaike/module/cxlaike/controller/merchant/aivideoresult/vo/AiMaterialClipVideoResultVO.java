package net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.aivideoresult.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import net.hzxzkj.cxlaike.framework.common.pojo.PageResult;

@Schema(description = "管理后台 - ai视频生成结果 Response VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class AiMaterialClipVideoResultVO extends AiVideoResultBaseVO {

    @Schema(description = "视频时长")
    private Integer duration;

}
