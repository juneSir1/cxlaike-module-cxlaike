package net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.aivideotaskcover.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import javax.validation.constraints.*;

/**
 * ai素材混剪视频封面图片关联 Base VO，提供给添加、修改、详细的子 VO 使用
 * 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
 */
@Data
public class AiVideoTaskCoverBaseVO {

    @Schema(description = "ai素材混剪任务id", requiredMode = Schema.RequiredMode.REQUIRED, example = "16522")
    @NotNull(message = "ai素材混剪任务id不能为空")
    private Long taskId;

    @Schema(description = "封面图片文件id", requiredMode = Schema.RequiredMode.REQUIRED, example = "6703")
    @NotNull(message = "封面图片文件id不能为空")
    private Long fileId;

    @Schema(description = "封面url", requiredMode = Schema.RequiredMode.REQUIRED, example = "https://www.iocoder.cn")
    @NotNull(message = "封面url不能为空")
    private String url;

    @Schema(description = "排序", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "排序不能为空")
    private Integer sort;

}
