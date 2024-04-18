package net.hzxzkj.cxlaike.module.cxlaike.service.template.dto;

import lombok.Data;
import net.hzxzkj.cxlaike.module.cxlaike.enums.AiClipType;

/**
 * 描   述:
 *
 * @author ace
 * @version [版本号, 2023/10/12]
 * @see [相关类/方法]
 * 创建日期: 2023/10/12
 */
@Data
public class VideoSynthesisMetaHumanDTO {

  /**
   * 视频类型
   */
  private AiClipType aiClipType;

  /**
   * 口播文案
   */
  private String copywriting;
  /**
   * 数字人code
   */
  private String metaHumanCode;
  /**
   * 配音员code
   */
  private String dubCode;
  /**
   * 语速
   */
  private Integer speechRate;
  /**
   * 语调
   */
  private Integer pitchRate;
  /**
   * 音量
   */
  private Float volume;
  /**
   * 数字人背景图片
   */
  private String metaHumanBackgroundUrl;
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
