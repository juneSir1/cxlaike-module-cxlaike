package net.hzxzkj.cxlaike.module.cxlaike.service.video.dto;

import lombok.Data;
import net.hzxzkj.cxlaike.framework.ice.core.entity.timeline.audio.BaseAudioTrackClip;
import net.hzxzkj.cxlaike.framework.ice.core.entity.timeline.subtitle.SubtitleTrackClip;
import net.hzxzkj.cxlaike.framework.ice.core.entity.timeline.subtitle.SubtitleTrackClipForMaterialClip;
import net.hzxzkj.cxlaike.framework.ice.core.entity.timeline.video.VideoTrackClip;
import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.aivideotask.vo.AiMaterialClipVideoTaskContentTitleCreateReqVO;
import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.aivideotask.vo.AiMaterialClipVideoTaskCopywritingCreateReqVO;
import net.hzxzkj.cxlaike.module.cxlaike.enums.template.TemplateTypeEnum;

import java.util.List;

/**
 * 描   述: ai素材混剪
 *
 * @author ace
 * @version [版本号, 2023/10/8]
 * @see [相关类/方法]
 * 创建日期: 2023/10/8
 */
@Data
public class AiMaterialVideoClip extends VideoClipBase {

  /**
   * 模板类型
   */
  private TemplateTypeEnum templateTypeEnum;

  /**
   * 订单类型
   */
  private Integer orderType;

  /**
   * 封面图
   */
  private String coverImage;

  /**
   * 背景图片
   */
  private String bgImage;

  /**
   * 视频轨道
   */
  private List<VideoTrackClip> videoTrackClips;

  /**
   * 音频轨道
   */
  private List<BaseAudioTrackClip> audioTrackClips;

  /**
   * 字幕轨道
   */
  private List<SubtitleTrackClipForMaterialClip> subtitleTrackClipList;

  /**
   * 内容标题轨道
   */
  private List<SubtitleTrackClipForMaterialClip> contentTrackClipList;

  /**
   * 口播文案
   */
  private String copywriting;

  /**
   * 字幕来源类型
   */
//  private Integer copywritingSourceType;

  /**
   * 口播文案的设置
   */
  private AiMaterialClipVideoTaskCopywritingCreateReqVO copywritingVO;
  /**
   * 配音人
   */
  private String dubCode;
  /**
   * 配音语速
   */
  private Integer dubSpeechRate;
  /**
   * 配音语调
   */
  private Integer dubPitchRate;
  /**
   * 配音音量
   */
  private Float dubGain;

  /**
   * 背景音乐
   */
  private String pipedMusicUrl;

  /**
   * 背景音乐音量
   */
  private Float pipedMusicVolume;

  /**
   * 展示标题内容
   */
  private String contentTitle;

  /**
   * 展示标题内容的设置
   */
  private AiMaterialClipVideoTaskContentTitleCreateReqVO contentTitleVO;

  /**
   * 是否花字
   */
  private Boolean isFlowerWord = false;
  /**
   * 是否全局字幕文案
   */
  private Boolean isGlobalSubtitleDubbing = false;

  private Integer groupVideoType;

}
