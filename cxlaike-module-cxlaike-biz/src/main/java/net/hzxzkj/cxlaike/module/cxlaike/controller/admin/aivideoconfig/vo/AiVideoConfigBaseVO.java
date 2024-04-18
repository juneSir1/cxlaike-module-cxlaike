package net.hzxzkj.cxlaike.module.cxlaike.controller.admin.aivideoconfig.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import javax.validation.constraints.*;

/**
 * ai视频配置 Base VO，提供给添加、修改、详细的子 VO 使用
 * 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
 */
@Data
public class AiVideoConfigBaseVO {

    @Schema(description = "类型(1.AaiMotionInEffect,2.SubType,3.Fonts,4.Keywords,5.EffectColorStyle,6.Colors,7.Symbol)", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotNull(message = "类型(1.AaiMotionInEffect,2.SubType,3.Fonts,4.Keywords,5.EffectColorStyle,6.Colors,7.Symbol)不能为空")
    private Integer type;

    @Schema(description = "内容值")
    private String value;

    @Schema(description = "0.关闭,1.开启)", example = "2")
    private Integer status;

}
