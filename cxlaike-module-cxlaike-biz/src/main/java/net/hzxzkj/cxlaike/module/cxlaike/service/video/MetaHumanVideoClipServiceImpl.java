package net.hzxzkj.cxlaike.module.cxlaike.service.video;

import static net.hzxzkj.cxlaike.framework.common.exception.util.ServiceExceptionUtil.exception;
import static net.hzxzkj.cxlaike.module.cxlaike.enums.ErrorCodeConstants.TASK_CREATE_AI_VIDEO_ERROR;

import com.aliyun.ice20201109.models.SubmitMediaProducingJobRequest;
import com.aliyun.ice20201109.models.SubmitMediaProducingJobResponse;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import java.util.List;
import java.util.stream.Collectors;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import net.hzxzkj.cxlaike.framework.common.util.json.JsonUtils;
import net.hzxzkj.cxlaike.framework.ice.core.client.IceService;
import net.hzxzkj.cxlaike.framework.ice.core.entity.timeline.Timeline;
import net.hzxzkj.cxlaike.framework.ice.core.property.IceProperties;
import net.hzxzkj.cxlaike.framework.mybatis.core.query.LambdaQueryWrapperX;
import net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.videocliporder.VideoClipOrderDO;
import net.hzxzkj.cxlaike.module.cxlaike.dal.mysql.videocliporder.VideoClipOrderMapper;
import net.hzxzkj.cxlaike.module.cxlaike.enums.ActiveTaskType;
import net.hzxzkj.cxlaike.module.cxlaike.enums.AiClipType;
import net.hzxzkj.cxlaike.module.cxlaike.enums.SubtitleType;
import net.hzxzkj.cxlaike.module.cxlaike.enums.VideoClipOrderStatus;
import net.hzxzkj.cxlaike.module.cxlaike.enums.VideoType;
import net.hzxzkj.cxlaike.module.cxlaike.enums.template.TemplateTypeEnum;
import net.hzxzkj.cxlaike.module.cxlaike.service.template.VideoSynthesisMetaHumanService;
import net.hzxzkj.cxlaike.module.cxlaike.service.template.VideoSynthesisService;
import net.hzxzkj.cxlaike.module.cxlaike.service.template.dto.VideoSynthesisDTO;
import net.hzxzkj.cxlaike.module.cxlaike.service.template.dto.VideoSynthesisMetaHumanDTO;
import net.hzxzkj.cxlaike.module.cxlaike.service.video.dto.DigitalOnlyVideoClip;
import net.hzxzkj.cxlaike.module.cxlaike.service.video.dto.MetaHumanSecondVideoClip;
import net.hzxzkj.cxlaike.module.cxlaike.service.video.dto.MetaHumanVideoClip;
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
@Service("metaHumanVideoClipService")
public class MetaHumanVideoClipServiceImpl implements VideoClipHandlerService {

  @Resource
  private IceService iceService;

  @Resource
  private IceProperties iceProperties;

  @Resource
  private VideoClipOrderMapper videoClipOrderMapper;

  @Resource
  private VideoSynthesisMetaHumanService videoSynthesisMetaHumanService;

  @Resource
  private List<VideoSynthesisService> videoSynthesisServices;

  /**
   * 数字人口播文件夹
   */
  private final static String DIGITAL_CLIP = "/digital_clip/";

  /**
   * 文件尾缀
   */
  private final static String FILE_SUFFIX = ".mp4";

  @Override
  public Integer getType() {
    return AiClipType.DIGITAL_CLIP.getType();
  }

  @Override
  public VideoClipOrderDTO firstClip(VideoClipBase videoClip) {
    MetaHumanVideoClip aiVideoClip = (MetaHumanVideoClip) videoClip;
    Long userId = aiVideoClip.getUserId();
    //数字人口播文件地址
    String mediaUrl =
        iceProperties.getUrl() + userId + DIGITAL_CLIP + IdWorker.getIdStr() + FILE_SUFFIX;
    //创建任务
    String jobId = placeOrder(aiVideoClip, mediaUrl);
    VideoClipOrderDO videoClipOrderDO = new VideoClipOrderDO();
    videoClipOrderDO.setJobId(jobId);
    videoClipOrderDO.setMediaUrl(mediaUrl);
    videoClipOrderDO.setMaterialId(aiVideoClip.getMaterialId());
    videoClipOrderDO.setOrderType(aiVideoClip.getOrderType());
    videoClipOrderDO.setTaskType(AiClipType.DIGITAL_CLIP.getType());
    videoClipOrderDO.setVideoType(VideoType.DIGITAL.getType());
    videoClipOrderDO.setStatus(VideoClipOrderStatus.PROCESSING.getStatus());
    videoClipOrderMapper.insert(videoClipOrderDO);
    return new VideoClipOrderDTO().setId(videoClipOrderDO.getId()).setJobId(jobId)
        .setMediaUrl(mediaUrl);
  }

  private String placeOrder(MetaHumanVideoClip videoClip, String mediaUrl) {

    VideoSynthesisMetaHumanDTO videoSynthesisMetaHumanDTO = new VideoSynthesisMetaHumanDTO();
    videoSynthesisMetaHumanDTO.setAiClipType(AiClipType.DIGITAL_CLIP);
    videoSynthesisMetaHumanDTO.setCopywriting(videoClip.getCopywriting());
    videoSynthesisMetaHumanDTO.setMetaHumanCode(videoClip.getMetaHumanCode());
    videoSynthesisMetaHumanDTO.setDubCode(videoClip.getDubCode());
    videoSynthesisMetaHumanDTO.setSpeechRate(videoClip.getDubSpeechRate());
    videoSynthesisMetaHumanDTO.setPitchRate(videoClip.getDubPitchRate());
    videoSynthesisMetaHumanDTO.setMetaHumanBackgroundUrl(videoClip.getMetaHumanBackgroundUrl());

    Timeline template = videoSynthesisMetaHumanService.template(videoSynthesisMetaHumanDTO);

    SubmitMediaProducingJobRequest submitMediaProducingJobRequest = videoSynthesisMetaHumanService
        .submitMediaProducingJobRequest(mediaUrl, null, template);
    SubmitMediaProducingJobResponse submitMediaProducingJobResponse;
    try {
      submitMediaProducingJobResponse = iceService
          .submitMediaProducingJob(submitMediaProducingJobRequest);
    } catch (Exception ex) {
      log.error("提交数字人生成视频任务失败", ex);
      throw exception(TASK_CREATE_AI_VIDEO_ERROR);
    }
    return submitMediaProducingJobResponse.body.jobId;
  }

  @Override
  public String getVideoUrl(Long id) {
    VideoClipOrderDO videoClipOrderDO = videoClipOrderMapper.selectOne(
        new LambdaQueryWrapperX<VideoClipOrderDO>().eq(VideoClipOrderDO::getMaterialId, id)
            .eq(VideoClipOrderDO::getTaskType, AiClipType.DIGITAL_CLIP.getType()));
    if (videoClipOrderDO == null) {
      return null;
    }
    return videoClipOrderDO.getMediaUrl();
  }


  @Override
  public VideoClipOrderDTO secondClip(VideoClipBase videoClip) throws Exception {
    MetaHumanSecondVideoClip aiVideoClip = (MetaHumanSecondVideoClip) videoClip;
    Long userId = aiVideoClip.getUserId();
    //数字人口播文件地址
    String mediaUrl =
        iceProperties.getUrl() + userId + DIGITAL_CLIP + IdWorker.getIdStr() + FILE_SUFFIX;
    //创建任务
    String jobId = placeSecondClipOrder(aiVideoClip, mediaUrl);
    VideoClipOrderDO videoClipOrderDO = new VideoClipOrderDO();
    videoClipOrderDO.setJobId(jobId);
    videoClipOrderDO.setMediaUrl(mediaUrl);
    videoClipOrderDO.setMaterialId(aiVideoClip.getMaterialId());
    videoClipOrderDO.setOrderType(aiVideoClip.getOrderType());
    videoClipOrderDO.setTaskType(AiClipType.DIGITAL_CLIP.getType());
    videoClipOrderDO.setVideoType(VideoType.AI_CLIP.getType());
    videoClipOrderDO.setStatus(VideoClipOrderStatus.PROCESSING.getStatus());
    videoClipOrderMapper.insert(videoClipOrderDO);
    return new VideoClipOrderDTO().setId(videoClipOrderDO.getId()).setJobId(jobId)
        .setMediaUrl(mediaUrl);
  }

  private String placeSecondClipOrder(MetaHumanSecondVideoClip aiVideoClip, String mediaUrl)
      throws Exception {
    TemplateTypeEnum templateTypeEnum = aiVideoClip.getTemplateTypeEnum();
    VideoSynthesisService videoSynthesisService = videoSynthesisServices.stream()
        .filter(videoSynthesis -> videoSynthesis.matching(templateTypeEnum))
        .collect(Collectors.toList()).get(0);

    VideoSynthesisDTO videoSynthesisDTO = new VideoSynthesisDTO();
    videoSynthesisDTO.setVirtualVideoURL(aiVideoClip.getVirtualVideoURL());
    videoSynthesisDTO.setVideoTrackClips(aiVideoClip.getVideoTrackClips());
    videoSynthesisDTO.setContent(aiVideoClip.getContent());
    videoSynthesisDTO.setIsFlowerWord(aiVideoClip.getIsFlowerWord());
    videoSynthesisDTO.setBgMediaURL(aiVideoClip.getBgMediaURL());
    videoSynthesisDTO.setBgMediaVolume(aiVideoClip.getBgMediaVolume());
    videoSynthesisDTO.setTitle(aiVideoClip.getTitle());

    videoSynthesisDTO.setBgImage(aiVideoClip.getBgImage());
    videoSynthesisDTO.setSpeechRate(aiVideoClip.getSpeechRate());
    videoSynthesisDTO.setPitchRate(aiVideoClip.getPitchRate());
    videoSynthesisDTO.setVolume(aiVideoClip.getVolume());
    videoSynthesisDTO.setVoice(aiVideoClip.getVoice());

    videoSynthesisDTO.setCoverImage(aiVideoClip.getCoverImage());
    log.info("templateTypeEnum:{},videoSynthesisDTO:{}", templateTypeEnum.getValue(),
        JsonUtils.toJsonString(videoSynthesisDTO));
    Timeline timeline = videoSynthesisService
        .template(templateTypeEnum, videoSynthesisDTO);
    log.info("timeline:{}", JsonUtils.toJsonString(timeline));

    SubmitMediaProducingJobRequest submitMediaProducingJobRequest = videoSynthesisService
        .submitMediaProducingJobRequest(mediaUrl, null, timeline);
    SubmitMediaProducingJobResponse submitMediaProducingJobResponse = iceService
        .submitMediaProducingJob(submitMediaProducingJobRequest);
    return submitMediaProducingJobResponse.body.jobId;
  }

}
