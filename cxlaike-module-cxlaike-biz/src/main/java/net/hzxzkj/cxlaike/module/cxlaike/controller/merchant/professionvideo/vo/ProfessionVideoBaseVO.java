package net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.professionvideo.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import javax.validation.constraints.*;

/**
 * 精选行业视频 Base VO，提供给添加、修改、详细的子 VO 使用
 * 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
 */
@Data
public class ProfessionVideoBaseVO {

    @Schema(description = "标题")
    private String title;

    @Schema(description = "播放量", example = "22881")
    private String playCount;

    @Schema(description = "封面url", example = "https://www.iocoder.cn")
    private String coverUrl;

    @Schema(description = "视频url", example = "https://www.iocoder.cn")
    private String videoUrl;

    @Schema(description = "时长")
    private String duration;

}
