package net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.aitriallisten.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Data;
import net.hzxzkj.cxlaike.framework.common.validation.InEnum;
import net.hzxzkj.cxlaike.module.cxlaike.enums.AiSamplingRateType;
import net.hzxzkj.cxlaike.module.cxlaike.enums.AiSpeechRateAndPitchRateType;
import org.hibernate.validator.constraints.Length;

/**
 * ai语音试听功能 Base VO，提供给添加、修改、详细的子 VO 使用
 * 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
 */
@Data
public class AiTrialListenBaseVO {

  @Schema(description = "口播文案", requiredMode = Schema.RequiredMode.REQUIRED)
  @NotNull(message = "口播文案不能为空")
  private String copywriting;

  @Schema(description = "配音人", requiredMode = Schema.RequiredMode.REQUIRED)
  @NotNull(message = "配音人不能为空")
  private String dubCode;

  @Schema(description = "配音语速")
  @InEnum(value = AiSpeechRateAndPitchRateType.class, message = "配音语速不正确")
  @NotNull(message = "配音语速不能为空")
  private Integer dubSpeechRate;

  @Schema(description = "配音语调")
  @NotNull(message = "配音语调不能为空")
  @InEnum(value = AiSpeechRateAndPitchRateType.class, message = "配音语调不正确")
  private Integer dubPitchRate;

  @Schema(description = "配音音量,0.为静音,1.正常音量,大于1为放大音量")
  @NotNull(message = "配音音量不能为空")
  @Max(value = 10, message = "配音音量最大10")
  @Min(value = 0, message = "配音音量最小0")
  private Integer dubGain;

}
