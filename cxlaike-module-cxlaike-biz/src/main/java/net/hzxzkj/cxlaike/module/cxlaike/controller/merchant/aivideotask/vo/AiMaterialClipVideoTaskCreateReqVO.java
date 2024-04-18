package net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.aivideotask.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import net.hzxzkj.cxlaike.framework.common.validation.InEnum;
import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.aiclipvideotaskgroup.vo.AiClipVideoTaskGroupCreateReqVO;
import net.hzxzkj.cxlaike.module.cxlaike.enums.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Set;

/**
 * 描   述:
 * 创建ai素材混剪视频任务请求类
 * @author tkk
 * @version [版本号, 2023/12/12]
 * @see [相关类/方法]
 * 创建日期: 2023/12/12
 */
@Data
public class  AiMaterialClipVideoTaskCreateReqVO {

  private static final Integer VIDEO_LAYOUT_TYPE = VideoLayoutType.RANDOM.getType();

  @Schema(description = "视频时长类型")
  @NotNull(message = "视频时长类型不能为空")
  @InEnum(value = VideoDurationType.class, message = "视频时长类型必须在 {value}")
  private Integer videoDurationType;

  @Schema(description = "视频时长")
  private Integer videoDuration;

  @Schema(description = "任务标题")
  @NotNull(message = "任务标题不能为空")
  private String title;

  @Schema(description = "内容标题")
  private AiMaterialClipVideoTaskContentTitleCreateReqVO contentTitleVO;

  @Schema(description = "字幕类型")
  @InEnum(value = CopywritingSourceType.class, message = "字幕来源类型必须在{value}")
  private Integer copywritingSourceType;

  @Schema(description = "口播文案")
  private AiMaterialClipVideoTaskCopywritingCreateReqVO copywritingVO;

  @Schema(description = "视频组设置")
  @NotNull(message = "素材列表不能为空")
  @Size(min = 1)
  private List<AiClipVideoTaskGroupCreateReqVO> videoGroupList;

  @Schema(description = "配音员IdList")
//  @NotNull(message = "配音员IdList不能为空")
//  @Size(min = 1)
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

  @Schema(description = "剪辑生成数量")
  @NotNull(message = "剪辑生成数量不能为空")
  private Integer videoNum;

//  @Schema(description = "选择字幕类型")
//  @InEnum(value = SubtitleType.class, message = "字幕类型必须在 {value}")
//  private Integer subtitleType;


  @Schema(description = "画面比例类型", example = "1")
  @InEnum(value = AiAspectRatioType.class, message = "画面比例类型必须在 {value}")
  @NotNull(message = "画面比例类型不能为空")
  private Integer aspectRatioType;

  @Schema(description = "视频布局类型", example = "1")
  @InEnum(value = VideoLayoutType.class, message = "视频布局类型必须在 {value}")
  @NotNull(message = "视频布局类型不能为空")
  private Integer videoLayoutType = VIDEO_LAYOUT_TYPE;

  @Schema(description = "视频背景图片Id", example = "true")
  private Long backgroundImageId;

  @Schema(description = "视频封面选择类型")
  @InEnum(value = CoverSelectType.class, message = "视频封面选择类型必须在 {value}")
  @NotNull(message = "视频封面选择类型不能为空")
  private Integer coverType;

  @Schema(description = "视频封面图片Id")
  private List<Long> coverIds;

  @Schema(description = "用户id", hidden = true)
  private Long userId;

  @Schema(description = "模板名称")
  private String tempName;


  @Schema(description = "模板分类")
  private Integer tempIndustry;

}
