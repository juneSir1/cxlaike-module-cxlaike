package net.hzxzkj.cxlaike.module.cxlaike.service.video;

import static net.hzxzkj.cxlaike.framework.common.exception.util.ServiceExceptionUtil.exception;
import static net.hzxzkj.cxlaike.module.cxlaike.enums.ErrorCodeConstants.TASK_CREATE_AUDITION_ERROR;

import cn.hutool.json.JSONUtil;
import com.aliyun.ice20201109.models.SubmitMediaProducingJobRequest;
import com.aliyun.ice20201109.models.SubmitMediaProducingJobResponse;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import java.util.Collections;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import net.hzxzkj.cxlaike.framework.ice.core.client.IceService;
import net.hzxzkj.cxlaike.framework.ice.core.entity.outputmediaconfig.OutputMediaConfig;
import net.hzxzkj.cxlaike.framework.ice.core.entity.timeline.Timeline;
import net.hzxzkj.cxlaike.framework.ice.core.entity.timeline.audio.AudioTrack;
import net.hzxzkj.cxlaike.framework.ice.core.entity.timeline.audio.AudioTrackClipAi;
import net.hzxzkj.cxlaike.framework.ice.core.entity.timeline.effect.type.VolumeEffect;
import net.hzxzkj.cxlaike.framework.ice.core.property.IceProperties;
import net.hzxzkj.cxlaike.framework.mybatis.core.query.LambdaQueryWrapperX;
import net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.videocliporder.VideoClipOrderDO;
import net.hzxzkj.cxlaike.module.cxlaike.dal.mysql.videocliporder.VideoClipOrderMapper;
import net.hzxzkj.cxlaike.module.cxlaike.enums.ActiveTaskType;
import net.hzxzkj.cxlaike.module.cxlaike.enums.AiClipType;
import net.hzxzkj.cxlaike.module.cxlaike.enums.VideoClipOrderStatus;
import net.hzxzkj.cxlaike.module.cxlaike.enums.VideoType;
import net.hzxzkj.cxlaike.module.cxlaike.service.video.dto.AudioClip;
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
@Service("audioClipService")
public class AudioClipServiceImpl implements VideoClipHandlerService {

  @Resource
  private IceService iceService;

  @Resource
  private IceProperties iceProperties;

  @Resource
  private VideoClipOrderMapper videoClipOrderMapper;

  /**
   * 试听文件夹名
   */
  private final static String TRIAL_LISTEN = "/trial_listen/";

  /**
   * 文件尾缀
   */
  private final static String FILE_SUFFIX = ".aac";

  @Override
  public Integer getType() {
    return AiClipType.AUDIO_CLIP.getType();
  }

  @Override
  public VideoClipOrderDTO firstClip(VideoClipBase videoClip) {
    AudioClip audioClip = (AudioClip) videoClip;
    Long userId = audioClip.getUserId();
    //试听文件地址
    String mediaUrl =
        iceProperties.getUrl() + userId + TRIAL_LISTEN + IdWorker.getIdStr() + FILE_SUFFIX;
    //调用三方接口
    String jobId = placeOrder(audioClip, mediaUrl);
    VideoClipOrderDO videoClipOrderDO = new VideoClipOrderDO();
    videoClipOrderDO.setJobId(jobId);
    videoClipOrderDO.setMediaUrl(mediaUrl);
    videoClipOrderDO.setMaterialId(audioClip.getMaterialId());
    videoClipOrderDO.setTaskType(AiClipType.AUDIO_CLIP.getType());
    videoClipOrderDO.setOrderType(ActiveTaskType.CLIP.getType());
    videoClipOrderDO.setVideoType(VideoType.AI_CLIP.getType());
    videoClipOrderDO.setStatus(VideoClipOrderStatus.PROCESSING.getStatus());
    videoClipOrderMapper.insert(videoClipOrderDO);
    return new VideoClipOrderDTO().setId(videoClipOrderDO.getId()).setJobId(jobId)
        .setMediaUrl(mediaUrl);
  }

  @Override
  public String getVideoUrl(Long id) {
    VideoClipOrderDO videoClipOrderDO = videoClipOrderMapper.selectOne(
        new LambdaQueryWrapperX<VideoClipOrderDO>().eq(VideoClipOrderDO::getMaterialId, id)
            .eq(VideoClipOrderDO::getTaskType, AiClipType.AUDIO_CLIP.getType()));
    if (videoClipOrderDO == null) {
      return null;
    }
    return videoClipOrderDO.getMediaUrl();
  }

  /**
   * 音频合成下单
   *
   * @param audioClip 参数
   * @param mediaUrl  试听文件地址
   * @return jobId
   */
  private String placeOrder(AudioClip audioClip, String mediaUrl) {
    //文字转语音
    AudioTrack audioTrack = new AudioTrack();
    AudioTrackClipAi audioTrackClipAi = new AudioTrackClipAi();
    audioTrackClipAi.setType("AI_TTS");
    audioTrackClipAi.setContent(audioClip.getCopywriting());
    audioTrackClipAi.setVoice(audioClip.getDubCode());
    audioTrackClipAi.setSpeechRate(audioClip.getDubSpeechRate());
    audioTrackClipAi.setPitchRate(audioClip.getDubPitchRate());
    VolumeEffect volumeEffect = new VolumeEffect();
    volumeEffect.setGain(audioClip.getDubGain().floatValue());
    audioTrackClipAi.setEffects(Collections.singletonList(volumeEffect));
    audioTrack.setAudioTrackClips(Collections.singletonList(audioTrackClipAi));
    audioTrack.setMainTrack(true);
    Timeline timeline = new Timeline();
    timeline.setAudioTracks(Collections.singletonList(audioTrack));

    SubmitMediaProducingJobRequest submitMediaProducingJobRequest = new SubmitMediaProducingJobRequest();
    submitMediaProducingJobRequest.setTimeline(JSONUtil.toJsonStr(timeline));

    OutputMediaConfig outputMediaConfig = new OutputMediaConfig();
    outputMediaConfig.setMediaURL(mediaUrl);
    submitMediaProducingJobRequest.setOutputMediaConfig(JSONUtil.toJsonStr(outputMediaConfig));
    SubmitMediaProducingJobResponse submitMediaProducingJobResponse;
    try {
      submitMediaProducingJobResponse = iceService
          .submitMediaProducingJob(submitMediaProducingJobRequest);
    } catch (Exception ex) {
      log.error("提交音频合成任务失败", ex);
      throw exception(TASK_CREATE_AUDITION_ERROR);
    }
    return submitMediaProducingJobResponse.body.jobId;
  }

}
