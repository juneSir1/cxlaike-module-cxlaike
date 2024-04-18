package net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.aivideotask.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import java.util.Set;
import lombok.*;
import java.time.LocalDateTime;
import net.hzxzkj.cxlaike.framework.common.validation.InEnum;
import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.aivideoresult.vo.AiVideoResultRespVO;
import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.aivideotaskmaterial.vo.AiVideoTaskMaterialBaseVO;
import net.hzxzkj.cxlaike.module.cxlaike.enums.AiAspectRatioType;
import net.hzxzkj.cxlaike.module.cxlaike.enums.AiClipType;
import net.hzxzkj.cxlaike.module.cxlaike.enums.AiSamplingRateType;
import net.hzxzkj.cxlaike.module.cxlaike.enums.AiSpeechRateAndPitchRateType;
import net.hzxzkj.cxlaike.module.cxlaike.enums.MetaHumanLayoutType;
import net.hzxzkj.cxlaike.module.cxlaike.enums.SubtitleType;
import net.hzxzkj.cxlaike.module.cxlaike.enums.VideoLayoutType;

@Schema(description = "管理后台 - ai视频任务 Response VO")
@Data
@ToString(callSuper = true)
public class AiVideoTaskRespVO {

  @Schema(description = "主键", requiredMode = Schema.RequiredMode.REQUIRED, example = "13516")
  private Long id;

  @Schema(description = "任务标题")
  private String title;

  @Schema(description = "内容标题")
  private String contentTitle;

  @Schema(description = "视频剪辑类型(1.ai混剪,2.数字人混剪,3.纯数字人口播,4.试听音频,5.ai素材混剪)", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
  @InEnum(value = AiClipType.class, message = "视频剪辑类型必须在 {value}")
  private Integer clipType;

  @Schema(description = "是否有口播文案")
  private Boolean hasCopywriting;

  @Schema(description = "口播文案")
  private List<String> copywritingList;

  @Schema(description = "视频时长")
  private Integer videoDuration;

  @Schema(description = "素材列表")
  List<AiVideoTaskMaterialBaseVO> materialList;

  @Schema(description = "数字人信息")
  private MetaHumanRespVO metaHuman;

  @Schema(description = "配音员List")
  private List<VoiceRespVO> dubList;

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
  private Integer dubSamplingRate;

  @Schema(description = "选择字幕类型")
  @InEnum(value = SubtitleType.class, message = "字幕类型必须在 {value}")
  private Integer subtitleType;

  @Schema(description = "背景音乐List", example = "https://www.iocoder.cn")
  private List<MusicRespVO> pipedMusicList;

  @Schema(description = "背景音乐音量,0.为静音,1.正常音量,大于1为放大音量")
  private Float pipedMusicVolume;

  @Schema(description = "剪辑生成数量")
  private Integer videoNum;

  @Schema(description = "画面比例类型", example = "1")
  @InEnum(value = AiAspectRatioType.class, message = "画面比例类型必须在 {value}")
  private Integer aspectRatioType;

  @Schema(description = "视频布局类型", example = "1")
  @InEnum(value = VideoLayoutType.class, message = "视频布局类型必须在 {value}")
  private Integer videoLayoutType;

  @Schema(description = "数字人位置类型", example = "1")
  @InEnum(value = MetaHumanLayoutType.class, message = "数字人位置类型必须在 {value}")
  private Integer metaHumanLayoutType;

  @Schema(description = "视频背景图片Id", example = "true")
  private Long backgroundImageId;

  @Schema(description = "视频背景图片Url", example = "true")
  private String backgroundImageUrl;

  @Schema(description = "视频封面图片Id", example = "https://www.iocoder.cn")
  private Long coverId;

  @Schema(description = "视频封面图片Url", example = "https://www.iocoder.cn")
  private String coverUrl;

  @Schema(description = "视频状态", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
  private Integer status;

  @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
  private LocalDateTime createTime;

  @Schema(description = "生成结果")
  private List<AiVideoResultRespVO> resultList;

}
