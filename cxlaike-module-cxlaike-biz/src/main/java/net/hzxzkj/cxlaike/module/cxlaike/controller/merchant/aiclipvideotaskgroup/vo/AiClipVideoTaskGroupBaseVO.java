package net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.aiclipvideotaskgroup.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import javax.validation.constraints.*;

/**
 * ai素材混剪视频任务-视频组设置 Base VO，提供给添加、修改、详细的子 VO 使用
 * 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
 */
@Data
public class AiClipVideoTaskGroupBaseVO {

//    @Schema(description = "ai视频任务主键", requiredMode = Schema.RequiredMode.REQUIRED, example = "20704", hidden = true)
//    private Long aiTaskId;
//
//    @Schema(description = "内容标题对象", example = "26486", hidden = true)
//    private Long contentTitleFontId;
//
//    @Schema(description = "内容标题对象", example = "29735", hidden = true)
//    private Long copywritingFontId;

    @Schema(description = "视频组名", example = "王五")
    private String name;

    @Schema(description = "视频组时长(秒)")
    private Integer totalDuration;

    @Schema(description = "序号", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "序号不能为空")
    private Integer sort;

    @Schema(description = "视频组数量", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "视频组数量不能为空")
    private Integer num;

    @Schema(description = "是否原声", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "是否原声不能为空")
    private Boolean original = false;

    @Schema(description = "时长设置类型", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotNull(message = "时长设置类型不能为空")
    private Integer durationType;

    @Schema(description = "时长设置-时长")
    private Integer duration;

//    @Schema(description = "视频状态", example = "1", hidden = true)
//    private Integer status;

}
