package net.hzxzkj.cxlaike.module.cxlaike.service.template;

import com.alibaba.druid.util.StringUtils;
import java.util.Collections;
import java.util.List;
import javax.annotation.Resource;
import net.hzxzkj.cxlaike.framework.ice.core.entity.timeline.Timeline;
import net.hzxzkj.cxlaike.framework.ice.core.entity.timeline.audio.AudioTrack;
import net.hzxzkj.cxlaike.framework.ice.core.entity.timeline.audio.AudioTrackClip;
import net.hzxzkj.cxlaike.framework.ice.core.entity.timeline.effect.BaseEffect;
import net.hzxzkj.cxlaike.framework.ice.core.entity.timeline.effect.type.AIASREffect;
import net.hzxzkj.cxlaike.framework.ice.core.entity.timeline.effect.type.VolumeEffect;
import net.hzxzkj.cxlaike.framework.ice.core.entity.timeline.subtitle.FontFace;
import net.hzxzkj.cxlaike.framework.ice.core.entity.timeline.subtitle.SubtitleTrack;
import net.hzxzkj.cxlaike.framework.ice.core.entity.timeline.subtitle.SubtitleTrackClip;
import net.hzxzkj.cxlaike.framework.ice.core.entity.timeline.video.VideoTrack;
import net.hzxzkj.cxlaike.framework.ice.core.entity.timeline.video.VideoTrackClip;
import net.hzxzkj.cxlaike.framework.ice.core.entity.timeline.video.VideoTrackClipAi;
import net.hzxzkj.cxlaike.module.cxlaike.enums.AiClipType;
import net.hzxzkj.cxlaike.module.cxlaike.enums.aivideo.AiVideoConfigTypeEnum;
import net.hzxzkj.cxlaike.module.cxlaike.service.aivideoconfig.AiVideoConfigService;
import net.hzxzkj.cxlaike.module.cxlaike.service.template.dto.VideoSynthesisMetaHumanDTO;
import org.apache.commons.compress.utils.Lists;
import org.springframework.stereotype.Service;

/**
 * 描   述:
 *
 * @author ace
 * @version [版本号, 2023/10/12]
 * @see [相关类/方法]
 * 创建日期: 2023/10/12
 */
@Service
public class VideoSynthesisMetaHumanServiceImpl implements VideoSynthesisMetaHumanService {


  @Resource
  private AiVideoConfigService aiVideoConfigService;

  @Override
  public Timeline template(VideoSynthesisMetaHumanDTO videoSynthesisMetaHumanDTO) {

    AiClipType clipType = videoSynthesisMetaHumanDTO.getAiClipType();
    List<VideoTrack> videoTracks = Lists.newArrayList();
    List<AudioTrack> audioTracks = Lists.newArrayList();
    List<SubtitleTrack> subtitleTracks = Lists.newArrayList();
    //数字人口播
    VideoTrackClipAi videoTrackClipAi = new VideoTrackClipAi();
    VideoTrack videoTrack = new VideoTrack();
    videoTrackClipAi.setContent(videoSynthesisMetaHumanDTO.getCopywriting());
    videoTrackClipAi.setAvatarId(videoSynthesisMetaHumanDTO.getMetaHumanCode());
    videoTrackClipAi.setVoice(videoSynthesisMetaHumanDTO.getDubCode());
    videoTrackClipAi.setSpeechRate(videoSynthesisMetaHumanDTO.getSpeechRate());
    videoTrackClipAi.setPitchRate(videoSynthesisMetaHumanDTO.getPitchRate());
    Boolean isFlowerWord = videoSynthesisMetaHumanDTO.getIsFlowerWord();
    //不需要音量
    VolumeEffect volumeEffect = new VolumeEffect();
    volumeEffect.setGain(0F);
    List<String> fonts = this.getFonts();
    if (AiClipType.DIGITAL_CLIP_ONLY.equals(clipType)) {
      List<BaseEffect> effects = Lists.newArrayList();
      AIASREffect aiasrEffect1 = new AIASREffect();
      aiasrEffect1.setFont(fonts.get(0));
      aiasrEffect1.setFontSize(60);
      aiasrEffect1.setRatio(1F);
      aiasrEffect1.setSpacing(1);
      aiasrEffect1.setY(0.73f);
      aiasrEffect1.setX(0.502f);
      //口播需要音量
      volumeEffect.setGain(videoSynthesisMetaHumanDTO.getVolume());
      effects.add(volumeEffect);
      effects.add(aiasrEffect1);
      videoTrackClipAi.setEffects(effects);
      //背景音乐
      AudioTrack bgAudioTrack = new AudioTrack();
      AudioTrackClip bgAudioTrackClip = new AudioTrackClip();
      bgAudioTrackClip.setMediaURL(videoSynthesisMetaHumanDTO.getPipedMusicUrl());
      VolumeEffect bgVolumeEffect = new VolumeEffect();
      Float pipedMusicVolume = videoSynthesisMetaHumanDTO.getPipedMusicVolume();
      if (pipedMusicVolume == null) {
        pipedMusicVolume = 1F;
      }
      bgVolumeEffect.setGain(pipedMusicVolume);
      bgAudioTrackClip.setEffects(Collections.singletonList(bgVolumeEffect));
      bgAudioTrack.setAudioTrackClips(Collections.singletonList(bgAudioTrackClip));
      audioTracks.add(bgAudioTrack);
      //文案
      String contentTitle = videoSynthesisMetaHumanDTO.getContentTitle();
      if (!StringUtils.isEmpty(contentTitle)) {
        SubtitleTrackClip subtitleTrackClip = new SubtitleTrackClip();
        subtitleTrackClip.setType("Text");
        subtitleTrackClip.setContent(contentTitle);
        subtitleTrackClip.setFontSize(30);
        FontFace fontFace = new FontFace();
        fontFace.setBold(true);
        fontFace.setItalic(true);

        subtitleTrackClip.setFontFace(fontFace);
        subtitleTrackClip.setAdaptMode("AutoWrap");
        subtitleTrackClip.setY(0.1f);
        subtitleTrackClip.setSpacing(1);
        subtitleTrackClip.setAlignment("TopCenter");
        subtitleTrackClip.setAaiMotionInEffect("dissovle_in");
        subtitleTrackClip.setEffectColorStyle("abstract_2");
        subtitleTrackClip.setOutlineColour("#ffffff");
        subtitleTrackClip.setBackColour("#ffe672");
        SubtitleTrack subtitleTrack = new SubtitleTrack();
        if (isFlowerWord) {
          subtitleTrackClip.setOutline(12);
          subtitleTrackClip.setOutlineColour("#ffffff");
          subtitleTrackClip.setBackColour("#ffe672");
          subtitleTrackClip.setEffectColorStyle("CS0001-000004");
        }
        subtitleTrack.setSubtitleTrackClips(Collections.singletonList(subtitleTrackClip));
        subtitleTracks.add(subtitleTrack);
      }
    }
    videoTrack.setVideoTrackClips(Collections.singletonList(videoTrackClipAi));
    //全局图片
    VideoTrack videoTrack1 = new VideoTrack();
    VideoTrackClip videoTrackClip = new VideoTrackClip();
    videoTrackClip.setType("GlobalImage");
    videoTrackClip.setMediaURL(videoSynthesisMetaHumanDTO.getMetaHumanBackgroundUrl());
    videoTrack1.setVideoTrackClips(Collections.singletonList(videoTrackClip));
    videoTracks.add(videoTrack1);
    videoTracks.add(videoTrack);

    return timeline(videoTracks, audioTracks, subtitleTracks);
  }

  public Timeline timeline(
      List<VideoTrack> videoTracks, List<AudioTrack> audioTracks,
      List<SubtitleTrack> subtitleTracks) {
    Timeline timeline = new Timeline();
    timeline.setVideoTracks(videoTracks);
    timeline.setAudioTracks(audioTracks);
    timeline.setSubtitleTracks(subtitleTracks);
    return timeline;
  }

  public List<String> getFonts() {
    return this.aiVideoConfigService.getAiVideoConfigList(AiVideoConfigTypeEnum.FONT.getType());
  }
}
