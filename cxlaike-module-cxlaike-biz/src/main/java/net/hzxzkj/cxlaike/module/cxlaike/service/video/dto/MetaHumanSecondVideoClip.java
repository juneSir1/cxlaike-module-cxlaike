package net.hzxzkj.cxlaike.module.cxlaike.service.video.dto;

import java.util.List;
import lombok.Data;
import net.hzxzkj.cxlaike.framework.ice.core.entity.timeline.video.VideoTrackClip;
import net.hzxzkj.cxlaike.module.cxlaike.enums.template.TemplateTypeEnum;

/**
 * 描   述:
 *
 * @author ace
 * @version [版本号, 2023/10/14]
 * @see [相关类/方法]
 * 创建日期: 2023/10/14
 */
@Data
public class MetaHumanSecondVideoClip extends VideoClipBase {

  /**
   * 模板类型
   */
  private TemplateTypeEnum templateTypeEnum;

  /**
   * 订单类型
   */
  private Integer orderType;
  /**
   * 数字人素材视频url
   */
  private String virtualVideoURL;
  /**
   * 封面图
   */
  private String coverImage;
  /**
   * 视频轨道
   */
  private List<VideoTrackClip> videoTrackClips;
  /**
   * 口播文案
   */
  private String content;
  /**
   * 配音员code
   */
  private String voice;
  /**
   * 背景音乐
   */
  private String bgMediaURL;
  /**
   * 背景音乐音量
   */
  private Float bgMediaVolume;
  /**
   * 展示标题内容
   */
  private String title;
  /**
   * 背景图片
   */
  private String bgImage;
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
   * 是否花字
   */
  private Boolean isFlowerWord;


}
