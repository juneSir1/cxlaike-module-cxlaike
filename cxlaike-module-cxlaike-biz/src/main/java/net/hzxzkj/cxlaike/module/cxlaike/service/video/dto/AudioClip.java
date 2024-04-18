package net.hzxzkj.cxlaike.module.cxlaike.service.video.dto;

import lombok.Data;

/**
 * 描   述:
 *
 * @author ace
 * @version [版本号, 2023/10/10]
 * @see [相关类/方法]
 * 创建日期: 2023/10/10
 */
@Data
public class AudioClip extends VideoClipBase {

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
  private Integer dubGain;

}
