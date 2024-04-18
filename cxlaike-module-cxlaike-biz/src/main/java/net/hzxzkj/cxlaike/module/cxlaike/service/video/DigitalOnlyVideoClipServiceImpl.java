package net.hzxzkj.cxlaike.module.cxlaike.service.video;

import static net.hzxzkj.cxlaike.framework.common.exception.util.ServiceExceptionUtil.exception;
import static net.hzxzkj.cxlaike.module.cxlaike.enums.ErrorCodeConstants.TASK_CREATE_AI_VIDEO_ERROR;

import com.aliyun.ice20201109.models.SubmitMediaProducingJobRequest;
import com.aliyun.ice20201109.models.SubmitMediaProducingJobResponse;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import net.hzxzkj.cxlaike.framework.ice.core.client.IceService;
import net.hzxzkj.cxlaike.framework.ice.core.entity.timeline.Timeline;
import net.hzxzkj.cxlaike.framework.ice.core.property.IceProperties;
import net.hzxzkj.cxlaike.framework.mybatis.core.query.LambdaQueryWrapperX;
import net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.videocliporder.VideoClipOrderDO;
import net.hzxzkj.cxlaike.module.cxlaike.dal.mysql.videocliporder.VideoClipOrderMapper;
import net.hzxzkj.cxlaike.module.cxlaike.enums.ActiveTaskType;
import net.hzxzkj.cxlaike.module.cxlaike.enums.AiClipType;
import net.hzxzkj.cxlaike.module.cxlaike.enums.VideoClipOrderStatus;
import net.hzxzkj.cxlaike.module.cxlaike.enums.VideoType;
import net.hzxzkj.cxlaike.module.cxlaike.service.template.VideoSynthesisMetaHumanService;
import net.hzxzkj.cxlaike.module.cxlaike.service.template.dto.VideoSynthesisMetaHumanDTO;
import net.hzxzkj.cxlaike.module.cxlaike.service.video.dto.DigitalOnlyVideoClip;
import net.hzxzkj.cxlaike.module.cxlaike.service.video.dto.VideoClipBase;
import net.hzxzkj.cxlaike.module.cxlaike.service.video.dto.VideoClipOrderDTO;
import org.springframework.stereotype.Service;

/**
 * 描   述:
 *
 * @author ace
 * @version [版本号, 2023/10/8]
 * @see [相关类/方法]
 * 创建日期: 2023/10/8
 */
@Slf4j
@Service("digitalOnlyVideoClipService")
public class DigitalOnlyVideoClipServiceImpl implements VideoClipHandlerService {

  @Resource
  private IceService iceService;

  @Resource
  private IceProperties iceProperties;

  @Resource
  private VideoClipOrderMapper videoClipOrderMapper;

  /**
   * 数字人口播文件夹
   */
  private final static String DIGITAL_CLIP_ONLY = "/digital_clip_only/";

  /**
   * 文件尾缀
   */
  private final static String FILE_SUFFIX = ".mp4";

  @Resource
  private VideoSynthesisMetaHumanService videoSynthesisMetaHumanService;

  @Override
  public Integer getType() {
    return AiClipType.DIGITAL_CLIP_ONLY.getType();
  }

  @Override
  public VideoClipOrderDTO firstClip(VideoClipBase videoClip) {
    DigitalOnlyVideoClip aiVideoClip = (DigitalOnlyVideoClip) videoClip;
    Long userId = aiVideoClip.getUserId();
    //数字人口播文件地址
    String mediaUrl =
        iceProperties.getUrl() + userId + DIGITAL_CLIP_ONLY + IdWorker.getIdStr() + FILE_SUFFIX;
    //创建任务
    String jobId = placeOrder(aiVideoClip, mediaUrl);
    VideoClipOrderDO videoClipOrderDO = new VideoClipOrderDO();
    videoClipOrderDO.setJobId(jobId);
    videoClipOrderDO.setMediaUrl(mediaUrl);
    videoClipOrderDO.setMaterialId(aiVideoClip.getMaterialId());
    videoClipOrderDO.setTaskType(AiClipType.DIGITAL_CLIP_ONLY.getType());
    videoClipOrderDO.setOrderType(ActiveTaskType.CLIP.getType());
    videoClipOrderDO.setVideoType(VideoType.DIGITAL.getType());
    videoClipOrderDO.setStatus(VideoClipOrderStatus.PROCESSING.getStatus());
    videoClipOrderMapper.insert(videoClipOrderDO);
    return new VideoClipOrderDTO().setId(videoClipOrderDO.getId()).setJobId(jobId)
        .setMediaUrl(mediaUrl);
  }

  private String placeOrder(DigitalOnlyVideoClip aiVideoClip, String mediaUrl) {
    VideoSynthesisMetaHumanDTO videoSynthesisMetaHumanDTO = new VideoSynthesisMetaHumanDTO();

    videoSynthesisMetaHumanDTO.setAiClipType(AiClipType.DIGITAL_CLIP_ONLY);
    videoSynthesisMetaHumanDTO.setCopywriting(aiVideoClip.getCopywriting());
    videoSynthesisMetaHumanDTO.setMetaHumanCode(aiVideoClip.getMetaHumanCode());
    videoSynthesisMetaHumanDTO.setDubCode(aiVideoClip.getDubCode());
    videoSynthesisMetaHumanDTO.setSpeechRate(aiVideoClip.getDubSpeechRate());
    videoSynthesisMetaHumanDTO.setPitchRate(aiVideoClip.getDubPitchRate());
    videoSynthesisMetaHumanDTO.setVolume(aiVideoClip.getDubGain());
    videoSynthesisMetaHumanDTO.setMetaHumanBackgroundUrl(aiVideoClip.getMetaHumanBackgroundUrl());
    videoSynthesisMetaHumanDTO.setPipedMusicVolume(aiVideoClip.getPipedMusicVolume());
    videoSynthesisMetaHumanDTO.setPipedMusicUrl(aiVideoClip.getPipedMusicUrl());
    videoSynthesisMetaHumanDTO.setContentTitle(aiVideoClip.getContentTitle());
    videoSynthesisMetaHumanDTO.setIsFlowerWord(aiVideoClip.getIsFlowerWord());
    Timeline template = videoSynthesisMetaHumanService.template(videoSynthesisMetaHumanDTO);

    SubmitMediaProducingJobRequest submitMediaProducingJobRequest = videoSynthesisMetaHumanService
        .submitMediaProducingJobRequest(mediaUrl, null, template);
    SubmitMediaProducingJobResponse submitMediaProducingJobResponse;
    try {
      submitMediaProducingJobResponse = iceService
          .submitMediaProducingJob(submitMediaProducingJobRequest);
    } catch (Exception ex) {
      log.error("提交数字人口播视频任务失败", ex);
      throw exception(TASK_CREATE_AI_VIDEO_ERROR);
    }
    return submitMediaProducingJobResponse.body.jobId;
  }

  @Override
  public String getVideoUrl(Long id) {
    VideoClipOrderDO videoClipOrderDO = videoClipOrderMapper.selectOne(
        new LambdaQueryWrapperX<VideoClipOrderDO>().eq(VideoClipOrderDO::getMaterialId, id)
            .eq(VideoClipOrderDO::getTaskType, AiClipType.DIGITAL_CLIP_ONLY.getType()));
    if (videoClipOrderDO == null) {
      return null;
    }
    return videoClipOrderDO.getMediaUrl();
  }

}
