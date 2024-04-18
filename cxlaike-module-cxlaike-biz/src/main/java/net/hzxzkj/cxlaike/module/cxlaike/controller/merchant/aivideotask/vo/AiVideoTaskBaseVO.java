package net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.aivideotask.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.Set;
import javax.validation.constraints.NotNull;
import lombok.Data;
import net.hzxzkj.cxlaike.framework.common.validation.InEnum;
import net.hzxzkj.cxlaike.module.cxlaike.enums.AiAspectRatioType;
import net.hzxzkj.cxlaike.module.cxlaike.enums.AiClipType;
import net.hzxzkj.cxlaike.module.cxlaike.enums.AiSamplingRateType;
import net.hzxzkj.cxlaike.module.cxlaike.enums.AiSpeechRateAndPitchRateType;
import net.hzxzkj.cxlaike.module.cxlaike.enums.MetaHumanLayoutType;
import net.hzxzkj.cxlaike.module.cxlaike.enums.SubtitleType;
import net.hzxzkj.cxlaike.module.cxlaike.enums.VideoLayoutType;

/**
 * ai视频任务 Base VO，提供给添加、修改、详细的子 VO 使用
 * 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
 */
@Data
public class AiVideoTaskBaseVO {

  private static final Integer VIDEO_LAYOUT_TYPE = VideoLayoutType.RANDOM.getType();
  private static final Integer META_HUMAN_LAYOUT_TYPE = MetaHumanLayoutType.RANDOM.getType();
  private static final Integer DUB_SAMPLING_RATE = AiSamplingRateType.KHZ_48.getType();

  @Schema(description = "任务标题")
  @NotNull(message = "任务标题不能为空")
  private String title;

  @Schema(description = "内容标题")
  private String contentTitle;

  @Schema(description = "视频剪辑类型(1.ai混剪,2.数字人混剪,3.纯数字人口播)", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
  @NotNull(message = "视频剪辑类型(1.ai混剪,2.数字人混剪,3.纯数字人口播)不能为空")
  @InEnum(value = AiClipType.class, message = "视频剪辑类型必须在 {value}")
  private Integer clipType;

  @Schema(description = "数字人Id")
  private Long metaHumanId;

  @Schema(description = "数字人背景图", example = "https://www.iocoder.cn")
  private Long metaHumanBackgroundId;

  @Schema(description = "配音员IdList")
  private Set<Long> dubIdList;

  @Schema(description = "配音语速")
  @InEnum(value = AiSpeechRateAndPitchRateType.class, message = "配音语速必须在 {value}")
  private Integer dubSpeechRate;

  @Schema(description = "配音语调")
  @InEnum(value = AiSpeechRateAndPitchRateType.class, message = "配音语调必须在 {value}")
  private Integer dubPitchRate;

  @Schema(description = "配音音量,0.为静音,1.正常音量,大于1为放大音量")
  private Float dubGain;

  @Schema(description = "配音采样率")
  @InEnum(value = AiSamplingRateType.class, message = "配音采样率必须在 {value}")
  private Integer dubSamplingRate = DUB_SAMPLING_RATE;

  @Schema(description = "选择字幕类型")
  @NotNull(message = "字幕类型不可为空")
  @InEnum(value = SubtitleType.class, message = "字幕类型必须在 {value}")
  private Integer subtitleType;

  @Schema(description = "背景音乐idList", example = "https://www.iocoder.cn")
  private Set<Long> pipedMusicIdList;

  @Schema(description = "剪辑生成数量")
  @NotNull(message = "剪辑生成数量不能为空")
  private Integer videoNum;

  @Schema(description = "画面比例类型", example = "1")
  @InEnum(value = AiAspectRatioType.class, message = "画面比例类型必须在 {value}")
  @NotNull(message = "画面比例类型不能为空")
  private Integer aspectRatioType;

  @Schema(description = "视频布局类型", example = "1")
  @InEnum(value = VideoLayoutType.class, message = "视频布局类型必须在 {value}")
  @NotNull(message = "视频布局类型不能为空")
  private Integer videoLayoutType = VIDEO_LAYOUT_TYPE;

  @Schema(description = "数字人位置类型", example = "1")
  @InEnum(value = MetaHumanLayoutType.class, message = "数字人位置类型必须在 {value}")
  @NotNull(message = "数字人位置类型不能为空")
  private Integer metaHumanLayoutType = VIDEO_LAYOUT_TYPE;

  @Schema(description = "视频背景图片Id", example = "true")
  @NotNull(message = "视频背景图片Id不能为空")
  private Long backgroundImageId;

  @Schema(description = "视频封面图片Id", example = "https://www.iocoder.cn")
  private Long coverId;

}
