package net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.template.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import javax.validation.constraints.*;

/**
 * ai素材混剪视频模板 Base VO，提供给添加、修改、详细的子 VO 使用
 * 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
 */
@Data
public class TemplateBaseVO {

    @Schema(description = "ai视频任务id", requiredMode = Schema.RequiredMode.REQUIRED, example = "12074")
    @NotNull(message = "ai视频任务id不能为空")
    private Long aiTaskId;

    @Schema(description = "模板名称", example = "王五")
    private String tempName;

    @Schema(description = "mediaURL", example = "https://www.iocoder.cn")
    private String videoUrl;

    @Schema(description = "视频结果id", example = "28563")
    private Long videoId;

    @Schema(description = "视频时长")
    private Integer duration;

    @Schema(description = "画面比例类型", example = "1")
    private Integer aspectRatioType;

    @Schema(description = "视频段数")
    private Integer groupNum;

    @Schema(description = "是否系统模板")
    private Byte isSystem;

    @Schema(description = "模板分类")
    private Integer tempIndustry;

    @Schema(description = "是否热门模板 1是 0否")
    private Integer isHot;

}
