package net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.aivideotask.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import java.util.Set;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Data;
import net.hzxzkj.cxlaike.framework.common.validation.InEnum;
import net.hzxzkj.cxlaike.module.cxlaike.enums.AiSpeechRateAndPitchRateType;
import net.hzxzkj.cxlaike.module.cxlaike.enums.SubtitleType;

/**
 * 描   述:
 *
 * @author ace
 * @version [版本号, 2023/10/12]
 * @see [相关类/方法]
 * 创建日期: 2023/10/12
 */
@Data
public class DigitalClipOnlyVideoCreateReqVO {

  @Schema(description = "任务标题")
  @NotNull(message = "任务标题不能为空")
  private String title;

  @Schema(description = "内容标题")
  private String contentTitle;

  @Schema(description = "口播文案")
  @NotNull(message = "口播文案不能为空")
  @Size(min = 1)
  private List<String> copywritingList;

  @Schema(description = "数字人Id")
  @NotNull(message = "数字人Id不能为空")
  private Long metaHumanId;

  @Schema(description = "数字人背景图")
  @NotNull(message = "数字人背景图不能为空")
  private Long metaHumanBackgroundId;

  @Schema(description = "配音员IdList")
  @NotNull(message = "配音员IdList不能为空")
  private Set<Long> dubIdList;

  @Schema(description = "配音语速")
  @NotNull(message = "配音语速不能为空")
  @InEnum(value = AiSpeechRateAndPitchRateType.class, message = "配音语速必须在 {value}")
  private Integer dubSpeechRate;

  @Schema(description = "配音语调")
  @NotNull(message = "配音语调不能为空")
  @InEnum(value = AiSpeechRateAndPitchRateType.class, message = "配音语调必须在 {value}")
  private Integer dubPitchRate;

  @Schema(description = "配音音量,0.为静音,1.正常音量,大于1为放大音量")
  @NotNull(message = "配音音量不能为空")
  private Float dubGain;

  @Schema(description = "背景音乐idList")
  private Set<Long> pipedMusicIdList;

  @Schema(description = "背景音乐音量,0.为静音,1.正常音量,大于1为放大音量")
  private Float pipedMusicVolume;

  @Schema(description = "视频封面图片Id")
  private Long coverId;

  @Schema(description = "选择字幕类型")
  @InEnum(value = SubtitleType.class, message = "字幕类型必须在 {value}")
  private Integer subtitleType;

  @Schema(description = "用户id",hidden = true)
  private Long userId;


}
