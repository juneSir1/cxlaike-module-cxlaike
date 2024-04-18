package net.hzxzkj.cxlaike.module.cxlaike.service.video;

import static net.hzxzkj.cxlaike.framework.common.exception.util.ServiceExceptionUtil.exception;
import static net.hzxzkj.cxlaike.module.cxlaike.enums.ErrorCodeConstants.TASK_CREATE_AI_VIDEO_ERROR;

import cn.hutool.json.JSONUtil;
import com.aliyun.ice20201109.Client;
import com.aliyun.ice20201109.models.*;
import com.aliyun.teaopenapi.models.Config;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;

import java.util.List;
import java.util.stream.Collectors;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import net.hzxzkj.cxlaike.framework.common.util.json.JsonUtils;
import net.hzxzkj.cxlaike.framework.ice.core.client.IceService;
import net.hzxzkj.cxlaike.framework.ice.core.entity.outputmediaconfig.OutputMediaConfig;
import net.hzxzkj.cxlaike.framework.ice.core.entity.timeline.Timeline;
import net.hzxzkj.cxlaike.framework.ice.core.property.IceProperties;
import net.hzxzkj.cxlaike.framework.tenant.core.context.TenantContextHolder;
import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.aivideotask.vo.AiMaterialClipVideoTaskCreateReqVO;
import net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.videocliporder.VideoClipOrderDO;
import net.hzxzkj.cxlaike.module.cxlaike.dal.mysql.videocliporder.VideoClipOrderMapper;
import net.hzxzkj.cxlaike.module.cxlaike.enums.AiClipType;
import net.hzxzkj.cxlaike.module.cxlaike.enums.VideoClipOrderStatus;
import net.hzxzkj.cxlaike.module.cxlaike.enums.VideoDurationType;
import net.hzxzkj.cxlaike.module.cxlaike.enums.VideoType;
import net.hzxzkj.cxlaike.module.cxlaike.enums.template.TemplateTypeEnum;
import net.hzxzkj.cxlaike.module.cxlaike.service.template.VideoSynthesisService;
import net.hzxzkj.cxlaike.module.cxlaike.service.template.dto.VideoSynthesisDTO;
import net.hzxzkj.cxlaike.module.cxlaike.service.template.dto.VideoSynthesisDTOForMaterialClip;
import net.hzxzkj.cxlaike.module.cxlaike.service.video.dto.AiMaterialVideoClip;
import net.hzxzkj.cxlaike.module.cxlaike.service.video.dto.AiVideoClip;
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
@Service("aiVideoClipService")
public class AiVideoClipServiceImpl implements VideoClipHandlerService {

  @Resource
  private VideoClipOrderMapper videoClipOrderMapper;

  @Resource
  private IceService iceService;

  @Resource
  private IceProperties iceProperties;

  @Resource
  private List<VideoSynthesisService> videoSynthesisServices;
  /**
   * 数字人口播文件夹
   */
  private final static String AI_CLIP_ONLY = "/ai_clip/";

  /**
   * 文件尾缀
   */
  private final static String FILE_SUFFIX = ".mp4";

  /**
   * 文件尾缀
   */
  private final static String FILE_SUFFIX_MP3 = ".mp3";

  private final static String SUCCESS_CODE = "Success";

  private final static String FINISHED_CODE = "Finished";

  @Override
  public Integer getType() {
    return AiClipType.AI_CLIP.getType();
  }

  @Override
  public VideoClipOrderDTO firstClip(VideoClipBase videoClip) {
    AiVideoClip aiVideoClip = (AiVideoClip) videoClip;
    Long userId = aiVideoClip.getUserId();
    //数字人口播文件地址
    String mediaUrl =
        iceProperties.getUrl() + userId + AI_CLIP_ONLY + IdWorker.getIdStr() + FILE_SUFFIX;
    //创建任务
    String jobId = placeOrder(aiVideoClip, mediaUrl);
    VideoClipOrderDO videoClipOrderDO = new VideoClipOrderDO();
    videoClipOrderDO.setJobId(jobId);
    videoClipOrderDO.setMediaUrl(mediaUrl);
    videoClipOrderDO.setMaterialId(aiVideoClip.getMaterialId());
    videoClipOrderDO.setTaskType(AiClipType.AI_CLIP.getType());
    videoClipOrderDO.setOrderType(aiVideoClip.getOrderType());
    videoClipOrderDO.setVideoType(VideoType.AI_CLIP.getType());
    videoClipOrderDO.setStatus(VideoClipOrderStatus.PROCESSING.getStatus());
    videoClipOrderMapper.insert(videoClipOrderDO);
    return new VideoClipOrderDTO().setId(videoClipOrderDO.getId()).setJobId(jobId)
        .setMediaUrl(mediaUrl);
  }
  @Override
  public VideoClipOrderDTO firstClipForMaterialClip(VideoClipBase videoClip) {
    AiMaterialVideoClip aiVideoClip = (AiMaterialVideoClip) videoClip;
    Long userId = aiVideoClip.getUserId();

    //视频文件地址
    String mediaUrl =
        iceProperties.getUrl() + userId + AI_CLIP_ONLY + IdWorker.getIdStr() + FILE_SUFFIX;

    //创建任务
    String jobId = placeOrderForMaterialClip(aiVideoClip, mediaUrl);
    VideoClipOrderDO videoClipOrderDO = new VideoClipOrderDO();
    videoClipOrderDO.setJobId(jobId);
    videoClipOrderDO.setMediaUrl(mediaUrl);
    videoClipOrderDO.setMaterialId(aiVideoClip.getMaterialId());
    videoClipOrderDO.setTaskType(AiClipType.AI_MATERIAL_CLIP.getType());
    videoClipOrderDO.setOrderType(aiVideoClip.getOrderType());
    videoClipOrderDO.setVideoType(VideoType.AI_MATERIAL_CLIP.getType());
    videoClipOrderDO.setStatus(VideoClipOrderStatus.PROCESSING.getStatus());
    videoClipOrderMapper.insert(videoClipOrderDO);
    return new VideoClipOrderDTO().setId(videoClipOrderDO.getId()).setJobId(jobId)
        .setMediaUrl(mediaUrl);
  }

  private String placeOrder(AiVideoClip aiVideoClip, String mediaUrl) {
    TemplateTypeEnum templateTypeEnum = aiVideoClip.getTemplateTypeEnum();
    VideoSynthesisService videoSynthesisService = videoSynthesisServices.stream()
        .filter(videoSynthesis -> videoSynthesis.matching(templateTypeEnum))
        .collect(Collectors.toList()).get(0);

    VideoSynthesisDTO videoSynthesisDTO = new VideoSynthesisDTO();
    videoSynthesisDTO.setVideoTrackClips(aiVideoClip.getVideoTrackClips());
    videoSynthesisDTO.setContent(aiVideoClip.getCopywriting());
    videoSynthesisDTO.setIsFlowerWord(aiVideoClip.getIsFlowerWord());
    videoSynthesisDTO.setVoice(aiVideoClip.getDubCode());
    videoSynthesisDTO
        .setBgMediaURL(aiVideoClip.getPipedMusicUrl());
    videoSynthesisDTO.setBgMediaVolume(aiVideoClip.getPipedMusicVolume());
    videoSynthesisDTO.setTitle(aiVideoClip.getContentTitle());

    videoSynthesisDTO.setBgImage(aiVideoClip.getBgImage());
    videoSynthesisDTO.setSpeechRate(aiVideoClip.getDubSpeechRate());
    videoSynthesisDTO.setPitchRate(aiVideoClip.getDubPitchRate());
    videoSynthesisDTO.setVolume(aiVideoClip.getDubGain());
    videoSynthesisDTO.setVoice(aiVideoClip.getDubCode());

    videoSynthesisDTO.setCoverImage(aiVideoClip.getCoverImage());
    log.info("templateTypeEnum:{},videoSynthesisDTO:{}", templateTypeEnum.getValue(),
        JsonUtils.toJsonString(videoSynthesisDTO));
    Timeline timeline = videoSynthesisService
        .template(templateTypeEnum, videoSynthesisDTO);
    log.info("timeline:{}", JsonUtils.toJsonString(timeline));

    SubmitMediaProducingJobRequest submitMediaProducingJobRequest = videoSynthesisService
        .submitMediaProducingJobRequest(mediaUrl, null, timeline);
    SubmitMediaProducingJobResponse submitMediaProducingJobResponse = null;
    try {
      submitMediaProducingJobResponse = iceService
          .submitMediaProducingJob(submitMediaProducingJobRequest);
    } catch (Exception ex) {
      log.error("提交ai混剪视频任务失败", ex);
      throw exception(TASK_CREATE_AI_VIDEO_ERROR);
    }
    return submitMediaProducingJobResponse.body.jobId;
  }

  /**
   * 设置timeline，提交到阿里云
   * @param aiMaterialVideoClip
   * @param mediaUrl
   * @return
   */
  public String placeOrderForMaterialClip(AiMaterialVideoClip aiMaterialVideoClip, /*Timeline timeline,*/ String mediaUrl) {
    TemplateTypeEnum templateTypeEnum = aiMaterialVideoClip.getTemplateTypeEnum();

    //匹配横、竖屏实现类
    VideoSynthesisService videoSynthesisService = videoSynthesisServices.stream()
        .filter(videoSynthesis -> videoSynthesis.matching(templateTypeEnum))
        .collect(Collectors.toList()).get(0);

    VideoSynthesisDTOForMaterialClip videoSynthesisDTO = new VideoSynthesisDTOForMaterialClip();
    videoSynthesisDTO.setVideoTrackClips(aiMaterialVideoClip.getVideoTrackClips());
    videoSynthesisDTO.setAudioTrackClips(aiMaterialVideoClip.getAudioTrackClips());
    videoSynthesisDTO.setSubtitleTrackClipList(aiMaterialVideoClip.getSubtitleTrackClipList());
    videoSynthesisDTO.setContentTrackClipList(aiMaterialVideoClip.getContentTrackClipList());
    videoSynthesisDTO.setContent(aiMaterialVideoClip.getCopywriting());
    videoSynthesisDTO.setIsFlowerWord(aiMaterialVideoClip.getIsFlowerWord());
    videoSynthesisDTO.setIsGlobalSubtitleDubbing(aiMaterialVideoClip.getIsGlobalSubtitleDubbing());
    videoSynthesisDTO.setVoice(aiMaterialVideoClip.getDubCode());
    videoSynthesisDTO
        .setBgMediaURL(aiMaterialVideoClip.getPipedMusicUrl());
    videoSynthesisDTO.setBgMediaVolume(aiMaterialVideoClip.getPipedMusicVolume());
    videoSynthesisDTO.setTitle(aiMaterialVideoClip.getContentTitle());
    videoSynthesisDTO.setContentTitleVO(aiMaterialVideoClip.getContentTitleVO());

    videoSynthesisDTO.setBgImage(aiMaterialVideoClip.getBgImage());
    videoSynthesisDTO.setSpeechRate(aiMaterialVideoClip.getDubSpeechRate());
    videoSynthesisDTO.setPitchRate(aiMaterialVideoClip.getDubPitchRate());
    videoSynthesisDTO.setVolume(aiMaterialVideoClip.getDubGain());
    videoSynthesisDTO.setVoice(aiMaterialVideoClip.getDubCode());

    videoSynthesisDTO.setCoverImage(aiMaterialVideoClip.getCoverImage());
    videoSynthesisDTO.setGroupVideoType(aiMaterialVideoClip.getGroupVideoType());

    //封装timeline，给到阿里
    Timeline timeline = videoSynthesisService
        .templateForMaterialClip(templateTypeEnum, videoSynthesisDTO);

    SubmitMediaProducingJobRequest submitMediaProducingJobRequest = videoSynthesisService
        .submitMediaProducingJobRequest(mediaUrl, null, timeline);
    SubmitMediaProducingJobResponse submitMediaProducingJobResponse = null;
    try {

      submitMediaProducingJobResponse = iceClient()
          .submitMediaProducingJob(submitMediaProducingJobRequest);
    } catch (Exception ex) {
      log.error("提交ai混剪视频任务失败", ex);
      throw exception(TASK_CREATE_AI_VIDEO_ERROR);
    }
    return submitMediaProducingJobResponse.body.jobId;
  }

  public static Client iceClient() throws Exception {
    Config config = new Config();

    String url = "https://cxlaike-dev.oss-cn-hangzhou.aliyuncs.com/";
    String bucket = "cxlaike-dev";
    String regionId = "cn-hangzhou";
    String endpoint = "ice.cn-hangzhou.aliyuncs.com";
    String apiKey = "LTAI5tQEoMHakJybURtE4zid";
    String apiSecret = "MhMfgpaJ2CX0BXzXF2c9EQJmaPRQer";


    config.accessKeyId = apiKey;
    config.accessKeySecret = apiSecret;
    config.endpoint = endpoint;
    config.regionId = regionId;

    return new Client(config);
  }

  @Override
  public String videoClip(Timeline timeline) {
    //数字人口播文件地址
    String mediaUrl =
            iceProperties.getUrl() + /*TenantContextHolder.getTenantId()*/"test" + AI_CLIP_ONLY + IdWorker.getIdStr() + FILE_SUFFIX;

    log.info("mediaUrl:{}", mediaUrl);
    log.info("timeline:{}", JsonUtils.toJsonString(timeline));

    SubmitMediaProducingJobRequest submitMediaProducingJobRequest = new SubmitMediaProducingJobRequest();
    submitMediaProducingJobRequest.setTimeline(JSONUtil.toJsonStr(timeline));
    OutputMediaConfig outputMediaConfig = new OutputMediaConfig();
    outputMediaConfig.setMediaURL(mediaUrl);
//    outputMediaConfig.setWidth(1080);
//    outputMediaConfig.setHeight(1920);
    outputMediaConfig.setBitrate(null);
    submitMediaProducingJobRequest.setOutputMediaConfig(JSONUtil.toJsonStr(outputMediaConfig));

    SubmitMediaProducingJobResponse submitMediaProducingJobResponse = null;
    try {
      submitMediaProducingJobResponse = iceService
              .submitMediaProducingJob(submitMediaProducingJobRequest);
    } catch (Exception ex) {
      log.error("提交ai混剪视频任务失败", ex);
      throw exception(TASK_CREATE_AI_VIDEO_ERROR);
    }
    log.info("submitMediaProducingJobResponse:{}", JsonUtils.toJsonString(submitMediaProducingJobResponse));
    return submitMediaProducingJobResponse.body.jobId;
  }

  @Override
  public String getVideoClipResult(String jobId) {

    return getMediaProducingJob(jobId);
  }

  @Override
  public String getVideoUrl(Long id) {
    return null;
  }


  @Override
  public String videoToAudio(Timeline timeline) {
    //视频转音频文件地址
    String mediaUrl =
//            iceProperties.getUrl() + "test" + AI_CLIP_ONLY + IdWorker.getIdStr() + FILE_SUFFIX;
            iceProperties.getUrl() + TenantContextHolder.getTenantId() + AI_CLIP_ONLY + IdWorker.getIdStr() + FILE_SUFFIX_MP3;

    log.info("mediaUrl:{}", mediaUrl);
    log.info("timeline:{}", JsonUtils.toJsonString(timeline));

    SubmitMediaProducingJobRequest submitMediaProducingJobRequest = new SubmitMediaProducingJobRequest();
    submitMediaProducingJobRequest.setTimeline(JSONUtil.toJsonStr(timeline));
    OutputMediaConfig outputMediaConfig = new OutputMediaConfig();
    outputMediaConfig.setMediaURL(mediaUrl);
//    outputMediaConfig.setWidth(1080);
//    outputMediaConfig.setHeight(1920);
    outputMediaConfig.setBitrate(null);
    submitMediaProducingJobRequest.setOutputMediaConfig(JSONUtil.toJsonStr(outputMediaConfig));

    SubmitMediaProducingJobResponse submitMediaProducingJobResponse = null;
    try {
      submitMediaProducingJobResponse = iceService
              .submitMediaProducingJob(submitMediaProducingJobRequest);
    } catch (Exception ex) {
      log.error("提交ai混剪视频任务失败", ex);
      throw exception(TASK_CREATE_AI_VIDEO_ERROR);
    }
    log.info("submitMediaProducingJobResponse:{}", JsonUtils.toJsonString(submitMediaProducingJobResponse));
    return submitMediaProducingJobResponse.body.jobId;
  }

  @Override
  public String getVideoToAudioResult(String jobId) {

    return getMediaProducingJob(jobId);
  }

  public static void main(String[] args) {
//    String jobId = "6f0efa3533554706903e1c5280fd39fd";
//    String jobId = "eebbfd265e9f498293da1ce5ba66faad";
    String jobId = "38d862ea63e947e48220020f2d882c42";
    System.out.println(getMediaProducingJob(jobId));
  }
  private static String getMediaProducingJob(String jobId) {
    GetMediaProducingJobRequest request = new GetMediaProducingJobRequest();
    request.setJobId(jobId);
    GetMediaProducingJobResponse getMediaProducingJobResponse = null;
    try {
//      getMediaProducingJobResponse = iceService
//              .getMediaProducingJob(jobId);
      getMediaProducingJobResponse = iceClient().getMediaProducingJob(request);
    } catch (Exception ex) {
      log.error("提交ai混剪视频任务失败", ex);
      throw exception(TASK_CREATE_AI_VIDEO_ERROR);
    }
    log.info("getMediaProducingJobResponse:{}", JsonUtils.toJsonString(getMediaProducingJobResponse));
    if(!SUCCESS_CODE.equals(getMediaProducingJobResponse.body.getMediaProducingJob().status)){
      return null;
    }
    return getMediaProducingJobResponse.body.getMediaProducingJob().mediaURL;
  }

  @Override
  public String audioToText(String url, String startTime, String duration) {
    return placeASRJob(url, startTime, duration);
  }

  private String placeASRJob(String url, String startTime, String duration) {
    SubmitASRJobRequest submitASRJobRequest = new SubmitASRJobRequest();
    submitASRJobRequest.setInputFile(url);
    submitASRJobRequest.setStartTime(startTime);
    submitASRJobRequest.setDuration(duration);

    SubmitASRJobResponse submitASRJobResponse = null;
    try {
      submitASRJobResponse = iceService
              .submitASRJob(submitASRJobRequest);
    } catch (Exception ex) {
      log.error("提交ai混剪视频任务失败", ex);
      throw exception(TASK_CREATE_AI_VIDEO_ERROR);
    }

    return submitASRJobResponse.body.jobId;
  }

    @Override
  public String getAudioToTextResult(String jobId) {
    GetSmartHandleJobResponse getSmartHandleJobResponse = null;
    try {
      getSmartHandleJobResponse = iceService
              .getSmartHandleJob(jobId);

      log.info("getSmartHandleJobResponse:{}", JsonUtils.toJsonString(getSmartHandleJobResponse));
    } catch (Exception ex) {
      log.error("提交ai混剪视频任务失败", ex);
      throw exception(TASK_CREATE_AI_VIDEO_ERROR);
    }

    if(!FINISHED_CODE.equals(getSmartHandleJobResponse.body.state)){
      return null;
    }
    return getSmartHandleJobResponse.body.output;
  }
}
