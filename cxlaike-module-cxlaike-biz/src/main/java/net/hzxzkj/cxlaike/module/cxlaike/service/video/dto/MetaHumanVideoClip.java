package net.hzxzkj.cxlaike.module.cxlaike.service.video.dto;

import lombok.Data;

/**
 * 描   述: 数字人混剪
 *
 * @author ace
 * @version [版本号, 2023/10/8]
 * @see [相关类/方法]
 * 创建日期: 2023/10/8
 */
@Data
public class MetaHumanVideoClip extends VideoClipBase {

  /**
   * 订单类型
   */
  private Integer orderType;

  /**
   * 数字人code
   */
  private String metaHumanCode;
  /**
   * 数字人背景图
   */
  private String metaHumanBackgroundUrl;
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


}
