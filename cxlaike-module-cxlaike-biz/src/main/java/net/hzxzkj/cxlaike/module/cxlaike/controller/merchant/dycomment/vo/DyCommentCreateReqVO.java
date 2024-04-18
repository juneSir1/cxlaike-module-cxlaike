package net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.dycomment.vo;

import lombok.*;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "商家后台 - 抖音评论管理创建 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class DyCommentCreateReqVO extends DyCommentBaseVO {

    @Schema(description = "token主键ID", example = "13472")
    private Long accountBindId;

    @Schema(description = "视频加密ID", example = "@9VwXwP7DX9ogJ3P3b8oqSc791mbsPfiFMpxyoAqlLFEQZ/L660zdRmYqig357zEBWKIsjHTXdteTkydF8OW9/A==")
    private String itemId;

}
