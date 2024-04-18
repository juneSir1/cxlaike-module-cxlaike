package net.hzxzkj.cxlaike.module.cxlaike.service.video;

import net.hzxzkj.cxlaike.framework.ice.core.entity.timeline.Timeline;
import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.aivideotask.vo.AiMaterialClipVideoTaskCreateReqVO;
import net.hzxzkj.cxlaike.module.cxlaike.service.video.dto.AiVideoClip;
import net.hzxzkj.cxlaike.module.cxlaike.service.video.dto.VideoClipBase;
import net.hzxzkj.cxlaike.module.cxlaike.service.video.dto.VideoClipOrderDTO;

/**
 * 描   述:
 *
 * @author ace
 * @version [版本号, 2023/10/11]
 * @see [相关类/方法]
 * 创建日期: 2023/10/11
 */
public interface VideoClipHandlerService {

  Integer getType();

  VideoClipOrderDTO firstClip(VideoClipBase videoClip);

  /**
   * Timeline-基础功能
   * 剪切合并-掐头去尾（处理视频）
   * @param timeline
   * @return
   */
  default String placeOrderTest(Timeline timeline) {
   return null;
  }

  /**
   * 任务查询
   * @param jobId
   * @return
   */
  default String testQuery(String jobId) {
   return null;
  }

  default VideoClipOrderDTO firstClipForMaterialClip(VideoClipBase videoClip){
   return null;
  }

  /**
   * 视频掐头去尾
   * @param timeline
   * @return
   */
  default String videoClip(Timeline timeline){
    return null;
  }

  /**
   * 获取视频掐头去尾结果
   * @param jobId
   * @return
   */
  default String getVideoClipResult(String jobId){
    return null;
  }

  String getVideoUrl(Long id);

  default VideoClipOrderDTO secondClip(VideoClipBase videoClip) throws Exception {
    return null;
  }

  /**
   * 视频转音频
   * @param timeline
   * @return
   * @throws Exception
   */
  default String videoToAudio(Timeline timeline){
    return null;
  }

  /**
   * 获取视频转音频结果
   * @param jobId
   * @return
   */
  default String getVideoToAudioResult(String jobId){
    return null;
  }

  /**
   * 音频转文字
   * @param url
   * @param startTime
   * @param duration
   * @return
   */
  default String audioToText(String url, String startTime, String duration){
    return null;
  }

  /**
   * 获取音频转文字结果
   * @param jobId
   * @return
   */
  default String getAudioToTextResult(String jobId){
    return null;
  }
}
