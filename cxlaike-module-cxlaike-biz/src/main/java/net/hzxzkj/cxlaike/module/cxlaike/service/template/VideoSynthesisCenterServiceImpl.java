package net.hzxzkj.cxlaike.module.cxlaike.service.template;

import cn.hutool.core.collection.CollectionUtil;
import lombok.extern.slf4j.Slf4j;
import net.hzxzkj.cxlaike.framework.ice.core.entity.timeline.Timeline;
import net.hzxzkj.cxlaike.framework.ice.core.entity.timeline.audio.AudioTrack;
import net.hzxzkj.cxlaike.framework.ice.core.entity.timeline.subtitle.SubtitleTrack;
import net.hzxzkj.cxlaike.framework.ice.core.entity.timeline.subtitle.SubtitleTrackClip;
import net.hzxzkj.cxlaike.framework.ice.core.entity.timeline.video.VideoTrack;
import net.hzxzkj.cxlaike.framework.ice.core.entity.timeline.video.VideoTrackClip;
import net.hzxzkj.cxlaike.module.cxlaike.enums.MaterialVideoDurationType;
import net.hzxzkj.cxlaike.module.cxlaike.enums.template.TemplateTypeEnum;
import net.hzxzkj.cxlaike.module.cxlaike.service.aivideoconfig.AiVideoConfigService;
import net.hzxzkj.cxlaike.module.cxlaike.service.aivideotemplate.AiVideoTemplateService;
import net.hzxzkj.cxlaike.module.cxlaike.service.aivideotemplate.dto.VideoParamDTO;
import net.hzxzkj.cxlaike.module.cxlaike.service.template.dto.VideoSynthesisDTO;
import net.hzxzkj.cxlaike.module.cxlaike.service.template.dto.VideoSynthesisDTOForMaterialClip;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
@Slf4j
public class VideoSynthesisCenterServiceImpl extends BaseVideoSynthesisServiceImpl implements VideoSynthesisService {

    @Resource
    private AiVideoConfigService aiVideoConfigService;

    @Resource
    private AiVideoTemplateService aiVideoTemplateService;

    @Override
    public AiVideoConfigService getAiVideoConfigService() {
        return aiVideoConfigService;
    }

    @Override
    public boolean matching(TemplateTypeEnum typeEnum) {
        List<TemplateTypeEnum> list = new ArrayList<>();
        list.add(TemplateTypeEnum.THREE_NONE);
        list.add(TemplateTypeEnum.THREE_DEFAULT);
        list.add(TemplateTypeEnum.THREE_TOP_RIGHT_CORNER);
        list.add(TemplateTypeEnum.FOUR_NONE);
        list.add(TemplateTypeEnum.FOUR_DEFAULT);
        list.add(TemplateTypeEnum.FOUR_TOP_RIGHT_CORNER);
        list.add(TemplateTypeEnum.FIVE_NONE);
        list.add(TemplateTypeEnum.FIVE_DEFAULT);
        list.add(TemplateTypeEnum.FIVE_TOP_RIGHT_CORNER);
        list.add(TemplateTypeEnum.SIX_NONE);
        list.add(TemplateTypeEnum.SIX_DEFAULT);
        list.add(TemplateTypeEnum.SIX_TOP_RIGHT_CORNER);
        return list.contains(typeEnum);
    }

    @Override
    public Timeline template(TemplateTypeEnum typeEnum, VideoSynthesisDTO videoSynthesisDTO) {
        VideoParamDTO videoParamDTO=aiVideoTemplateService.getVideoParamDTOByType(typeEnum.getValue());
        if(videoSynthesisDTO.getVolume()==null){
            videoSynthesisDTO.setVolume(videoParamDTO.getContentGain());
        }

        if(StringUtils.isBlank(videoSynthesisDTO.getCoverImage())){
            videoParamDTO.setTimelineIn(0F);
        }else {
            VideoTrackClip videoTrackClip = new VideoTrackClip();
            videoTrackClip.setMediaURL(videoSynthesisDTO.getCoverImage());
            videoTrackClip.setDuration(videoParamDTO.getTimelineIn());
            videoTrackClip.setType("Image");
//            videoSynthesisDTO.getVideoTrackClips().add(0,videoTrackClip);
        }

        List<String> fonts = this.getFonts();
        List<String> keywords = this.getKeywords();
        List<String> effectColorStyles = this.getEffectColorStyles();
        List<String> aaiMotionInEffects = this.getAaiMotionInEffects();
        List<String> subTypes = this.getSubTypes();

        List<VideoTrack> videoTracks = new ArrayList<>();
        List<SubtitleTrack> subtitleTracks = new ArrayList<>();
        List<AudioTrack> audioTracks = new ArrayList<>();
        VideoTrack videoTrack = new VideoTrack();
        List<String> contents =null;
        if(StringUtils.isNotBlank(videoSynthesisDTO.getContent())){
            String symbol=getSymbol();
            contents= Arrays.asList(videoSynthesisDTO.getContent().split(symbol));
        }else{
            videoTrack.setMainTrack(true);
        }
        if (StringUtils.isNotBlank(videoSynthesisDTO.getBgImage()) && videoParamDTO.getIsBgImage()) {
            VideoTrack bgVideoTarck = new VideoTrack();
            VideoTrackClip bgVideoTarckClip = new VideoTrackClip();
            bgVideoTarckClip.setType("GlobalImage");
            bgVideoTarckClip.setMediaURL(videoSynthesisDTO.getBgImage());
            bgVideoTarck.setVideoTrackClips(Arrays.asList(bgVideoTarckClip));
            videoTracks.add(bgVideoTarck);
        }
        String bgColor = null;
        if (typeEnum.getIsBgColor()) {
            List<String> colers= this.getColors();
            Collections.shuffle(colers);
            String color = colers.get(0);
            bgColor = color;
        }
        if (StringUtils.equals(videoParamDTO.getContentColor(),bgColor)) {
            bgColor = "#000000";
        }
        videoTrack.setVideoTrackClips(videoSynthesisDTO.getVideoTrackClips());
        Boolean isContinuous=true;
        switch (typeEnum.getVirtualType()) {
            case NONE:
                videoTracks.add(videoTrack);
                subtitleTracks.add(this.subtitle(videoSynthesisDTO.getTitle(), fonts, aaiMotionInEffects, effectColorStyles,videoParamDTO.getTimelineIn(),videoSynthesisDTO.getIsFlowerWord(), videoParamDTO));
                break;
            case TOP_RIGHT_CORNER:
                videoTracks.add(videoTrack);
                VideoTrack imageVideoTrack = this.imageRightCorner(videoSynthesisDTO.getBgVirtualImage(), videoParamDTO.getVirtualVideoY(),videoParamDTO.getTimelineIn(), videoParamDTO);
                if(imageVideoTrack!=null){
                    videoTracks.add(imageVideoTrack);
                }
                videoTracks.add(this.videoRightCorner(videoParamDTO.getVirtualVideoGain(), videoSynthesisDTO.getVirtualVideoURL(),  videoParamDTO.getVirtualVideoY(),videoParamDTO.getTimelineIn(), videoParamDTO));
                subtitleTracks.add(this.subtitle(videoSynthesisDTO.getTitle(), fonts, aaiMotionInEffects, effectColorStyles,videoParamDTO.getTimelineIn(),videoSynthesisDTO.getIsFlowerWord(), videoParamDTO));
                break;
            default:
                isContinuous=false;
                VideoTrack image = this.bgImage(videoSynthesisDTO.getBgVirtualImage(),  videoParamDTO.getVirtualVideoY(),videoParamDTO.getTimelineIn());
                if(image!=null){
                this.addTransition((List<VideoTrackClip>) image.getVideoTrackClips(), videoParamDTO.getVideoY(), bgColor, videoParamDTO.getBgRadius(), subTypes,true,videoParamDTO.getTimelineIn(), videoParamDTO);
                    videoTracks.add(image);
                }
                videoTracks.add(this.bgVideo(videoParamDTO.getVirtualVideoGain(), videoSynthesisDTO.getVirtualVideoURL(), videoParamDTO.getVirtualVideoY(),videoParamDTO.getTimelineIn()));
                videoTracks.add(videoTrack);
                subtitleTracks.add(this.subtitle(videoSynthesisDTO.getTitle(), fonts, aaiMotionInEffects, effectColorStyles,videoParamDTO.getTimelineIn(),videoSynthesisDTO.getIsFlowerWord(), videoParamDTO));
                break;
        }
        this.addTransition(videoSynthesisDTO.getVideoTrackClips(),  videoParamDTO.getVideoY(), bgColor, videoParamDTO.getBgRadius(), subTypes,isContinuous,videoParamDTO.getTimelineIn(), videoParamDTO);
        audioTracks.add(this.audioSubtitle(videoSynthesisDTO.getVolume(), videoSynthesisDTO.getSpeechRate(),videoSynthesisDTO.getPitchRate(),contents,videoSynthesisDTO.getVoice(), videoParamDTO.getContentColor(), aaiMotionInEffects, fonts, keywords,videoParamDTO.getTimelineIn(), videoParamDTO));
        audioTracks.add(this.audio(videoSynthesisDTO.getBgMediaURL(),videoSynthesisDTO.getBgMediaVolume(),videoParamDTO.getTimelineIn(),null,true));
        Timeline timeline = this.timeline(videoTracks, audioTracks, subtitleTracks);
        return timeline;
    }

    @Override
    public Timeline templateForMaterialClip(TemplateTypeEnum typeEnum, VideoSynthesisDTOForMaterialClip videoSynthesisDTO) {
        VideoParamDTO videoParamDTO=aiVideoTemplateService.getVideoParamDTOByType(typeEnum.getValue());
        if(videoSynthesisDTO.getVolume()==null){
            videoSynthesisDTO.setVolume(videoParamDTO.getContentGain());
        }

        if(StringUtils.isBlank(videoSynthesisDTO.getCoverImage())){
            videoParamDTO.setTimelineIn(0F);
        }else {
            VideoTrackClip videoTrackClip = new VideoTrackClip();
            videoTrackClip.setMediaURL(videoSynthesisDTO.getCoverImage());
            videoTrackClip.setDuration(videoParamDTO.getTimelineIn());
            videoTrackClip.setType("Image");
//            videoSynthesisDTO.getVideoTrackClips().add(0,videoTrackClip);
        }

        List<String> fonts = this.getFonts();
        List<String> keywords = this.getKeywords();
        List<String> effectColorStyles = this.getEffectColorStyles();
        List<String> aaiMotionInEffects = this.getAaiMotionInEffects();
        List<String> subTypes = this.getSubTypes();

        List<VideoTrack> videoTracks = new ArrayList<>();
        List<SubtitleTrack> subtitleTracks = new ArrayList<>();
        List<AudioTrack> audioTracks = new ArrayList<>();
        VideoTrack videoTrack = new VideoTrack();
        if(videoSynthesisDTO.getIsGlobalSubtitleDubbing() && (videoSynthesisDTO.getGroupVideoType()==null || !videoSynthesisDTO.getGroupVideoType().equals(MaterialVideoDurationType.FIXED_DURATION.getType()))){
            log.info("==================进入了全局设置====={}",videoSynthesisDTO);
            videoTrack.setTrackExpandMode("AutoSpeed");
            videoTrack.setTrackShortenMode("AutoSpeed");
        }else{
            videoTrack.setMainTrack(true);
        }
        if (StringUtils.isNotBlank(videoSynthesisDTO.getBgImage()) && videoParamDTO.getIsBgImage()) {
            VideoTrack bgVideoTarck = new VideoTrack();
            VideoTrackClip bgVideoTarckClip = new VideoTrackClip();
            bgVideoTarckClip.setType("GlobalImage");
            bgVideoTarckClip.setMediaURL(videoSynthesisDTO.getBgImage());
            bgVideoTarck.setVideoTrackClips(Arrays.asList(bgVideoTarckClip));
            videoTracks.add(bgVideoTarck);
        }
        String bgColor = null;
        if (typeEnum.getIsBgColor()) {
            List<String> colers= this.getColors();
            Collections.shuffle(colers);
            String color = colers.get(0);
            bgColor = color;
        }
        if (StringUtils.equals(videoParamDTO.getContentColor(),bgColor)) {
            bgColor = "#000000";
        }
        videoTrack.setVideoTrackClips(videoSynthesisDTO.getVideoTrackClips());
        Boolean isContinuous=true;
        videoTracks.add(videoTrack);

        //添加字幕
        if(videoSynthesisDTO.getCopywritingVO()!=null && videoSynthesisDTO.getSubtitleTrackClipList() != null) {
            subtitleTracks.add(this.subtitleForMaterialClipCopywriting(videoSynthesisDTO.getCopywritingVO(), videoSynthesisDTO.getSubtitleTrackClipList(), fonts, aaiMotionInEffects, effectColorStyles, videoParamDTO.getTimelineIn(), videoSynthesisDTO.getIsFlowerWord(), videoParamDTO));
        }

        //添加音轨
        audioTracks.add(this.audioSubtitleForMaterialClip(videoSynthesisDTO.getVolume(), videoSynthesisDTO.getSpeechRate(),videoSynthesisDTO.getPitchRate(),videoSynthesisDTO.getAudioTrackClips(), videoSynthesisDTO.getVoice(), aaiMotionInEffects, fonts, videoSynthesisDTO.getIsGlobalSubtitleDubbing()));

        //添加转场
        this.addTransitionForMaterialClip(videoSynthesisDTO.getVideoTrackClips(), videoParamDTO.getVideoY(), bgColor, videoParamDTO.getBgRadius(), subTypes,isContinuous,videoParamDTO.getTimelineIn(), videoParamDTO);

        //如果是全局，设置timelineout时间
        Float timeLineout = null;
        if(videoSynthesisDTO.getIsGlobalSubtitleDubbing()){
            List<VideoTrackClip> list= videoSynthesisDTO.getVideoTrackClips();
            Float dur = 0.0f;
            for(VideoTrackClip clip:list){
                if(clip.getMaterialDuration()==null){
                    break;
                }
                dur += clip.getMaterialDuration();
            }
            timeLineout = dur;
        }
        //添加背景音音轨
        audioTracks.add(this.audio(videoSynthesisDTO.getBgMediaURL(),videoSynthesisDTO.getBgMediaVolume(),videoParamDTO.getTimelineIn(),timeLineout,true));

        Timeline timeline = this.timeline(videoTracks, audioTracks, subtitleTracks);
        return timeline;
    }

}
