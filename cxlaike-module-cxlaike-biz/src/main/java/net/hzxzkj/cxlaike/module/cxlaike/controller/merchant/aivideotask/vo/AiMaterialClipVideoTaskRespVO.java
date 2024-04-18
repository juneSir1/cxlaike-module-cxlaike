package net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.aivideotask.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import net.hzxzkj.cxlaike.framework.common.validation.InEnum;
import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.aiclipvideotaskgroup.vo.AiClipVideoTaskGroupCreateReqVO;
import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.aivideoresult.vo.AiMaterialClipVideoResultRespVO;
import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.aivideoresult.vo.AiMaterialClipVideoResultVO;
import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.aivideoresult.vo.AiVideoResultRespVO;
import net.hzxzkj.cxlaike.module.cxlaike.enums.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

/**
 * 描   述:
 * 创建ai素材混剪视频任务返回类
 * @author tkk
 * @version [版本号, 2023/12/12]
 * @see [相关类/方法]
 * 创建日期: 2023/12/12
 */
@Data
public class AiMaterialClipVideoTaskRespVO {

  private Integer id;

  /**
   * 用户Id
   */
  private Long userId;

  @Schema(description = "视频时长类型")
  @InEnum(value = VideoDurationType.class, message = "视频时长类型必须在 {value}")
  private Integer videoDurationType;

  @Schema(description = "视频时长")
  private Integer videoDuration;

  @Schema(description = "任务标题")
  private String title;

  @Schema(description = "内容标题")
  private AiMaterialClipVideoTaskContentTitleCreateReqVO contentTitleVO;

  @Schema(description = "字幕类型")
  @InEnum(value = CopywritingSourceType.class, message = "字幕来源类型必须在{value}")
  private Integer copywritingSourceType;

  @Schema(description = "口播文案")
  private AiMaterialClipVideoTaskCopywritingCreateReqVO copywritingVO;

  @Schema(description = "视频组设置")
  @Size(min = 1)
  private List<AiClipVideoTaskGroupCreateReqVO> videoGroupList;

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

  @Schema(description = "背景音乐List")
  private List<MusicRespVO> pipedMusicList;

  @Schema(description = "背景音乐音量,0.为静音,1.正常音量,大于1为放大音量")
  private Float pipedMusicVolume;

  @Schema(description = "剪辑生成数量")
  private Integer videoNum;

//  @Schema(description = "选择字幕类型")
//  @InEnum(value = SubtitleType.class, message = "字幕类型必须在 {value}")
//  private Integer subtitleType;


  @Schema(description = "画面比例类型", example = "1")
  @InEnum(value = AiAspectRatioType.class, message = "画面比例类型必须在 {value}")
  private Integer aspectRatioType;

  @Schema(description = "视频布局类型", example = "1")
  @InEnum(value = VideoLayoutType.class, message = "视频布局类型必须在 {value}")
  private Integer videoLayoutType;

  @Schema(description = "视频背景图片Id", example = "true")
  private Long backgroundImageId;

  @Schema(description = "视频背景图片Url", example = "true")
  private String backgroundImageUrl;

  @Schema(description = "视频封面选择类型")
  @InEnum(value = CoverSelectType.class, message = "视频封面选择类型必须在 {value}")
  private Integer coverType;

  @Schema(description = "视频封面图片List")
  private List<CoverRespVO> coverList;

  @Schema(description = "视频状态", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
  private Integer status;

  @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
  private LocalDateTime createTime;

  @Schema(description = "生成结果")
  private List<AiMaterialClipVideoResultVO> resultList;

}
