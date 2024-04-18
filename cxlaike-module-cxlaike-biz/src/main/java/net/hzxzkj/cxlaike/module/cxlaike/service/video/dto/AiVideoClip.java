package net.hzxzkj.cxlaike.module.cxlaike.service.video.dto;

import java.util.List;
import lombok.Data;
import net.hzxzkj.cxlaike.framework.ice.core.entity.timeline.video.VideoTrackClip;
import net.hzxzkj.cxlaike.module.cxlaike.enums.template.TemplateTypeEnum;

/**
 * 描   述: 混剪
 *
 * @author ace
 * @version [版本号, 2023/10/8]
 * @see [相关类/方法]
 * 创建日期: 2023/10/8
 */
@Data
public class AiVideoClip extends VideoClipBase {

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
   * 口播文案
   */
  private String copywriting;
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
   * 是否花字
   */
  private Boolean isFlowerWord;

}
