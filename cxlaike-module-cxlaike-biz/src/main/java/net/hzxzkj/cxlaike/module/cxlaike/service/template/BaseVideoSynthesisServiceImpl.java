package net.hzxzkj.cxlaike.module.cxlaike.service.template;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import net.hzxzkj.cxlaike.framework.ice.core.entity.timeline.Timeline;
import net.hzxzkj.cxlaike.framework.ice.core.entity.timeline.audio.*;
import net.hzxzkj.cxlaike.framework.ice.core.entity.timeline.effect.BaseEffect;
import net.hzxzkj.cxlaike.framework.ice.core.entity.timeline.effect.type.AIASREffect;
import net.hzxzkj.cxlaike.framework.ice.core.entity.timeline.effect.type.BackgroundEffect;
import net.hzxzkj.cxlaike.framework.ice.core.entity.timeline.effect.type.TransitionEffect;
import net.hzxzkj.cxlaike.framework.ice.core.entity.timeline.effect.type.VolumeEffect;
import net.hzxzkj.cxlaike.framework.ice.core.entity.timeline.subtitle.FontFace;
import net.hzxzkj.cxlaike.framework.ice.core.entity.timeline.subtitle.SubtitleTrack;
import net.hzxzkj.cxlaike.framework.ice.core.entity.timeline.subtitle.SubtitleTrackClip;
import net.hzxzkj.cxlaike.framework.ice.core.entity.timeline.subtitle.SubtitleTrackClipForMaterialClip;
import net.hzxzkj.cxlaike.framework.ice.core.entity.timeline.video.VideoTrack;
import net.hzxzkj.cxlaike.framework.ice.core.entity.timeline.video.VideoTrackClip;
import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.aivideotask.vo.AiMaterialClipVideoTaskContentTitleCreateReqVO;
import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.aivideotask.vo.AiMaterialClipVideoTaskCopywritingCreateReqVO;
import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.aivideotask.vo.FontFaceVO;
import net.hzxzkj.cxlaike.module.cxlaike.enums.aivideo.AiVideoConfigTypeEnum;
import net.hzxzkj.cxlaike.module.cxlaike.service.aivideoconfig.AiVideoConfigService;
import net.hzxzkj.cxlaike.module.cxlaike.service.aivideotemplate.dto.VideoParamDTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.ObjectUtils;

import java.util.*;

public class BaseVideoSynthesisServiceImpl {

    /**
     * 转场时长
     */
    protected static final Float tranTime = 0.8f;

    public AiVideoConfigService getAiVideoConfigService() {
        return null;
    }

    public List<String> getAaiMotionInEffects() {
        return this.getAiVideoConfigService().getAiVideoConfigList(AiVideoConfigTypeEnum.AAIMOTIONINEFFECT.getType());
    }

    public List<String> getSubTypes() {
        return this.getAiVideoConfigService().getAiVideoConfigList(AiVideoConfigTypeEnum.SUBTYPE.getType());
    }

    public List<String> getFonts() {
        return this.getAiVideoConfigService().getAiVideoConfigList(AiVideoConfigTypeEnum.FONT.getType());
    }

    public List<String> getKeywords() {
        return this.getAiVideoConfigService().getAiVideoConfigList(AiVideoConfigTypeEnum.KEYWORD.getType());
    }

    public List<String> getEffectColorStyles() {
        return this.getAiVideoConfigService().getAiVideoConfigList(AiVideoConfigTypeEnum.EFFECTCOLORSTYLE.getType());
    }

     List<String> getColors() {
        return this.getAiVideoConfigService().getAiVideoConfigList(AiVideoConfigTypeEnum.COLOR.getType());
    }

     String getSymbol(){
        return this.getAiVideoConfigService().getAiVideoConfigList(AiVideoConfigTypeEnum.SYMBOL.getType()).toString();
    }
    public VideoTrack videoRightCorner(Float gain, String mediaURL, Float y,Float timelineIn,VideoParamDTO videoParamDTO) {
        if(StringUtils.isBlank(mediaURL)){
            return new VideoTrack();
        }
        List<VideoTrackClip> videoTrackClips = new ArrayList<>();
        VideoTrackClip videoTrackClip = new VideoTrackClip();
        videoTrackClip.setMediaURL(mediaURL);
        videoTrackClip.setY(y);
        videoTrackClip.setTimelineIn(timelineIn);

        //videoParamDTO.setVirtualVideoX(0.68F);
        //videoParamDTO.setVirtualVideoHeight(0.33F);
        //videoParamDTO.setVirtualVideoWidth(0.33F);

        videoTrackClip.setX(videoParamDTO.getVirtualVideoX());
        videoTrackClip.setHeight(videoParamDTO.getVirtualVideoHeight());
        videoTrackClip.setWidth(videoParamDTO.getVirtualVideoWidth());
        VolumeEffect volumeEffect = new VolumeEffect();
        volumeEffect.setGain(gain);
        videoTrackClip.setEffects(Arrays.asList(volumeEffect));
        videoTrackClips.add(videoTrackClip);
        VideoTrack videoTrack = new VideoTrack();
        videoTrack.setVideoTrackClips(videoTrackClips);
        return videoTrack;
    }

    public VideoTrack imageRightCorner(String mediaURL, Float y,Float timelineIn,VideoParamDTO videoParamDTO) {
        if(StringUtils.isBlank(mediaURL)){
            return null;
        }
        List<VideoTrackClip> videoTrackClips = new ArrayList<>();
        VideoTrackClip videoTrackClip = new VideoTrackClip();
        videoTrackClip.setType("GlobalImage");
        videoTrackClip.setMediaURL(mediaURL);
        videoTrackClip.setY(y);
        videoTrackClip.setTimelineIn(timelineIn);
        //videoParamDTO.setVirtualVideoX(0.68F);
        //videoParamDTO.setVirtualVideoHeight(0.33F);
        //videoParamDTO.setVirtualVideoWidth(0.33F);

        videoTrackClip.setX(videoParamDTO.getVirtualVideoX());
        videoTrackClip.setHeight(videoParamDTO.getVirtualVideoHeight());
        videoTrackClip.setWidth(videoParamDTO.getVirtualVideoWidth());
        videoTrackClips.add(videoTrackClip);
        VideoTrack videoTrack = new VideoTrack();
        videoTrack.setVideoTrackClips(videoTrackClips);
        return videoTrack;
    }

    public VideoTrack bgImage(String mediaURL, Float y,Float timelineIn) {
        if(StringUtils.isBlank(mediaURL)){
            return null;
        }
        List<VideoTrackClip> videoTrackClips = new ArrayList<>();
        VideoTrackClip videoTrackClip = new VideoTrackClip();
        videoTrackClip.setType("GlobalImage");
        videoTrackClip.setMediaURL(mediaURL);
        videoTrackClip.setY(y);
        videoTrackClip.setTimelineIn(timelineIn);
        videoTrackClips.add(videoTrackClip);
        VideoTrack videoTrack = new VideoTrack();
        videoTrack.setVideoTrackClips(videoTrackClips);
        return videoTrack;
    }





    public VideoTrack bgVideo(Float gain, String mediaURL, Float y,Float timelineIn) {
        if(StringUtils.isBlank(mediaURL)){
            return new VideoTrack();
        }
        List<VideoTrackClip> videoTrackClips = new ArrayList<>();
        VideoTrackClip videoTrackClip = new VideoTrackClip();
        videoTrackClip.setMediaURL(mediaURL);
        videoTrackClip.setY(y);
        videoTrackClip.setTimelineIn(timelineIn);
        VolumeEffect volumeEffect = new VolumeEffect();
        volumeEffect.setGain(gain);
        videoTrackClip.setEffects(Arrays.asList(volumeEffect));
        videoTrackClips.add(videoTrackClip);
        VideoTrack videoTrack = new VideoTrack();
        videoTrack.setVideoTrackClips(videoTrackClips);
        return videoTrack;
    }


    public AudioTrack audioSubtitle(Float gain,Integer speechRate,Integer pitchRate,List<String> contents, String voice, String color, List<String> aaiMotionInEffects, List<String> fonts, List<String> keywords,Float timelineIn,VideoParamDTO videoParamDTO) {
        AudioTrack audioTrack = new AudioTrack();
        if(contents==null || contents.size()==0){
            return audioTrack;
        }
        Collections.shuffle(aaiMotionInEffects);
        Collections.shuffle(fonts);
        int aaiMotionInEffectsCount = 0;
        List<AudioTrackClipAi> audioTrackClipAis = new ArrayList<>();
        for (int i = 0; i < contents.size(); i++) {
            String content = contents.get(i);
            AudioTrackClipAi audioTrackClipAi = new AudioTrackClipAi();
            if(i==0){
                audioTrackClipAi.setTimelineIn(timelineIn);
            }
            audioTrackClipAi.setContent(content);
            audioTrackClipAi.setVoice(voice);
            audioTrackClipAi.setSpeechRate(speechRate);
            audioTrackClipAi.setPitchRate(pitchRate);
            VolumeEffect volumeEffect = new VolumeEffect();
            volumeEffect.setGain(gain);
            AIASREffect aiasrEffect = new AIASREffect();
            FontFace fontFace = new FontFace();
            fontFace.setBold(true);
            aiasrEffect.setFontFace(fontFace);
            aiasrEffect.setX(videoParamDTO.getContentX());
            aiasrEffect.setY(videoParamDTO.getContentY());
            aiasrEffect.setAlignment(videoParamDTO.getContentAlignment());
            aiasrEffect.setAdaptMode("AutoWrap");
            aiasrEffect.setFont(fonts.get(0));
            aiasrEffect.setFontSize(videoParamDTO.getContentFontSize());
            aiasrEffect.setFontColor(color);
            List<BaseEffect> effects = new ArrayList<>();
            boolean anyMatch = keywords.stream().anyMatch(content::contains);
            if (anyMatch) {
                aiasrEffect.setBackColour(videoParamDTO.getContentBackColour());
                aiasrEffect.setOutlineColour(videoParamDTO.getContentOutlineColour());
                aiasrEffect.setOutline(videoParamDTO.getContentOutline());
                aiasrEffect.setAaiMotionInEffect(aaiMotionInEffects.get(aaiMotionInEffectsCount % aaiMotionInEffects.size()));
                aiasrEffect.setAaiMotionIn(videoParamDTO.getContentAaiMotionIn());
                aiasrEffect.setEffectColorStyle(videoParamDTO.getContentEffectColorStyle());
            }
            effects.add(volumeEffect);
            effects.add(aiasrEffect);
            audioTrackClipAi.setEffects(effects);
            audioTrackClipAis.add(audioTrackClipAi);
        }
        audioTrack.setAudioTrackClips(audioTrackClipAis);
        audioTrack.setMainTrack(true);
        return audioTrack;
    }


    public AudioTrack audioSubtitleForMaterialClip(Float gain, Integer speechRate, Integer pitchRate, List<BaseAudioTrackClip> baseAudioTrackClipList, String voice,  List<String> aaiMotionInEffects, List<String> fonts,Boolean isGlobalCopyWrite) {
        AudioTrack audioTrack = new AudioTrack();
        if(CollectionUtil.isEmpty(baseAudioTrackClipList)){
            return audioTrack;
        }
        if(isGlobalCopyWrite){
            audioTrack.setMainTrack(true);
        }
        Collections.shuffle(aaiMotionInEffects);
        Collections.shuffle(fonts);
        List<BaseAudioTrackClip> audioTrackClips = new ArrayList<>();
        for (int i = 0; i < baseAudioTrackClipList.size(); i++) {
            BaseAudioTrackClip baseAudioTrackClip = baseAudioTrackClipList.get(i);
            if(baseAudioTrackClip instanceof AudioTrackClipAiForMaterialClip){
                AudioTrackClipAiForMaterialClip audioTrackClipAi = (AudioTrackClipAiForMaterialClip) baseAudioTrackClip;
                audioTrackClipAi.setVoice(voice);
                audioTrackClipAi.setSpeechRate(speechRate);
                audioTrackClipAi.setPitchRate(pitchRate);
                VolumeEffect volumeEffect = new VolumeEffect();
                volumeEffect.setGain(gain);
                List<BaseEffect> effects = audioTrackClipAi.getEffects();
                if(effects==null){
                    audioTrackClipAi.setEffects(new ArrayList<>());
                }
                audioTrackClipAi.getEffects().add(volumeEffect);
                audioTrackClips.add(audioTrackClipAi);
            }

            if(baseAudioTrackClip instanceof AudioTrackClip){
                AudioTrackClip audioTrackClip = (AudioTrackClip) baseAudioTrackClip;
                VolumeEffect volumeEffect = new VolumeEffect();
                volumeEffect.setGain(gain);
                List<BaseEffect> effects = audioTrackClip.getEffects();
                if(effects==null){
                    audioTrackClip.setEffects(new ArrayList<>());
                }
                effects.add(volumeEffect);
                audioTrackClip.getEffects().add(volumeEffect);
                audioTrackClips.add(audioTrackClip);
            }

        }
        audioTrack.setAudioTrackClips(audioTrackClips);
        return audioTrack;
    }


    /**
     * 背景音乐的设置
     * @param mediaURL 媒资地址
     * @param gain 字段表示音量增益，值为0表示静音，1：原始音量；（0，1）：小于原始音量，值越小表示音量增益越小；>1：表示大于原始音量，值越大表示音量增益越大。
     * @param timelineIn 为5，表明该音频在输出视频的第5秒开始叠加。
     * @param timelineOut 未设置，则默认为整段音频都进行混流。如果音频的尾部超过了视频轨的总时长，则将对音频进行截断，播放持续到视频轨结束。
     * @param loopMode 是否循环播放
     * @return
     */
    public AudioTrack audio(String mediaURL, Float gain,Float timelineIn,Float timelineOut,boolean loopMode) {
        if(StringUtils.isBlank(mediaURL)){
            return new AudioTrack();
        }
        AudioTrack bgAudioTrack = new AudioTrack();
        AudioTrackClip bgAudioTrackClip = new AudioTrackClip();
        bgAudioTrackClip.setMediaURL(mediaURL);
        bgAudioTrackClip.setTimelineIn(timelineIn);
        if(timelineOut!=null) {
            bgAudioTrackClip.setTimelineOut(timelineOut);
        }
        bgAudioTrackClip.setLoopMode(loopMode);
        VolumeEffect bgVolumeEffect = new VolumeEffect();
        if (gain == null) {
            gain = 0.5F;
        }
        bgVolumeEffect.setGain(gain);
        bgAudioTrackClip.setEffects(Arrays.asList(bgVolumeEffect));
        bgAudioTrack.setAudioTrackClips(Arrays.asList(bgAudioTrackClip));
        return bgAudioTrack;
    }


    public  SubtitleTrack leftSubtitle(String content, List<String> fonts, List<String> aaiMotionInEffects, List<String> effectColorStyles,Float timelineIn,Boolean isFlowerWord,VideoParamDTO videoParamDTO) {
        Collections.shuffle(aaiMotionInEffects);
        Collections.shuffle(effectColorStyles);
        Collections.shuffle(fonts);
        SubtitleTrackClip subtitleTrackClip = new SubtitleTrackClip();

        subtitleTrackClip.setType("Text");
        subtitleTrackClip.setContent(content);
        subtitleTrackClip.setTimelineIn(timelineIn);

        subtitleTrackClip.setTextWidth(videoParamDTO.getSubtitleTextWidth());
        subtitleTrackClip.setY(videoParamDTO.getSubtitleY());
        subtitleTrackClip.setX(videoParamDTO.getSubtitleX());

        subtitleTrackClip.setTimelineIn(videoParamDTO.getSubtitleTimelineIn());
        Collections.shuffle(aaiMotionInEffects);
        subtitleTrackClip.setAaiMotionInEffect(aaiMotionInEffects.get(0));

        subtitleTrackClip.setAaiMotionIn(videoParamDTO.getSubtitleAaiMotionIn());
        subtitleTrackClip.setAlignment(videoParamDTO.getSubtitleAlignment());
        FontFace fontFace = new FontFace();
        fontFace.setBold(true);
        subtitleTrackClip.setFontFace(fontFace);
        if(isFlowerWord){
            subtitleTrackClip.setFont(fonts.get(0));
            subtitleTrackClip.setOutline(videoParamDTO.getSubtitleOutline());
            subtitleTrackClip.setOutlineColour(videoParamDTO.getSubtitleOutlineColour());
            subtitleTrackClip.setBackColour(videoParamDTO.getSubtitleBackColour());
            subtitleTrackClip.setEffectColorStyle(effectColorStyles.get(0));
        }
        subtitleTrackClip.setFontSize(videoParamDTO.getSubtitleFontSize());
        SubtitleTrack subtitleTrack = new SubtitleTrack();
        subtitleTrack.setSubtitleTrackClips(Arrays.asList(subtitleTrackClip));
        return subtitleTrack;
    }

    public  SubtitleTrack subtitle(String content, List<String> fonts, List<String> aaiMotionInEffects, List<String> effectColorStyles,Float timelineIn,Boolean isFlowerWord, VideoParamDTO videoParamDTO) {
        if(StringUtils.isBlank(content)){
            return new SubtitleTrack();
        }
        Collections.shuffle(aaiMotionInEffects);
        Collections.shuffle(effectColorStyles);
        Collections.shuffle(fonts);
        SubtitleTrackClip subtitleTrackClip = new SubtitleTrackClip();

        subtitleTrackClip.setType("Text");
        subtitleTrackClip.setContent(content);
        subtitleTrackClip.setTimelineIn(timelineIn);
        subtitleTrackClip.setTextWidth(videoParamDTO.getSubtitleTextWidth());
        subtitleTrackClip.setY(videoParamDTO.getSubtitleY());
        subtitleTrackClip.setX(videoParamDTO.getSubtitleX());

        subtitleTrackClip.setTimelineIn(videoParamDTO.getSubtitleTimelineIn());

        Collections.shuffle(aaiMotionInEffects);
        subtitleTrackClip.setAaiMotionInEffect(aaiMotionInEffects.get(0));

        subtitleTrackClip.setAaiMotionIn(videoParamDTO.getSubtitleAaiMotionIn());
        subtitleTrackClip.setAlignment(videoParamDTO.getSubtitleAlignment());
        FontFace fontFace = new FontFace();
        fontFace.setBold(true);
        subtitleTrackClip.setFontFace(fontFace);

        if(isFlowerWord){
            subtitleTrackClip.setFont(fonts.get(0));
            subtitleTrackClip.setOutline(videoParamDTO.getSubtitleOutline());
            subtitleTrackClip.setOutlineColour(videoParamDTO.getSubtitleOutlineColour());
            subtitleTrackClip.setBackColour(videoParamDTO.getSubtitleBackColour());
            subtitleTrackClip.setEffectColorStyle(effectColorStyles.get(0));
        }

        subtitleTrackClip.setFontSize(videoParamDTO.getSubtitleFontSize());
        SubtitleTrack subtitleTrack = new SubtitleTrack();
        subtitleTrack.setSubtitleTrackClips(Arrays.asList(subtitleTrackClip));
        return subtitleTrack;
    }

    public  List<SubtitleTrackClip> subtitleForMaterialClip(List<SubtitleTrackClipForMaterialClip> subtitleTrackClips, List<String> fonts, List<String> aaiMotionInEffects, Float timelineIn, VideoParamDTO videoParamDTO) {
        if(CollectionUtil.isEmpty(subtitleTrackClips)){
            return new ArrayList<>();
        }
        List<SubtitleTrackClip> subtitleTrackClipList = new ArrayList<>();

        for (SubtitleTrackClipForMaterialClip subtitleTrackClipForMaterialClip : subtitleTrackClips) {

            SubtitleTrackClip subtitleTrackClip = Convert.convert(SubtitleTrackClip.class, subtitleTrackClipForMaterialClip);

            subtitleTrackClip.setTextWidth(videoParamDTO.getSubtitleTextWidth());
            subtitleTrackClip.setY(null != subtitleTrackClip.getY() ? subtitleTrackClip.getY() : videoParamDTO.getSubtitleY());
            subtitleTrackClip.setX(null != subtitleTrackClip.getX() ? subtitleTrackClip.getX() : videoParamDTO.getSubtitleX());

//            subtitleTrackClip.setTimelineIn(videoParamDTO.getSubtitleTimelineIn());

            Collections.shuffle(aaiMotionInEffects);
            subtitleTrackClip.setAaiMotionInEffect(aaiMotionInEffects.get(0));

            subtitleTrackClip.setAaiMotionIn(videoParamDTO.getSubtitleAaiMotionIn());
//            subtitleTrackClip.setAlignment(StrUtil.nullToDefault(subtitleTrackClip.getAlignment(), videoParamDTO.getSubtitleAlignment()));

            if(null != subtitleTrackClip.getFontFace()){
                FontFace fontFace = new FontFace();
                fontFace.setBold(true);
                subtitleTrackClip.setFontFace(fontFace);
            }

            Collections.shuffle(fonts);
            subtitleTrackClip.setFont(StrUtil.nullToDefault(subtitleTrackClip.getFont(), fonts.get(0)));
            subtitleTrackClip.setOutline(null != subtitleTrackClip.getOutline() ? subtitleTrackClip.getOutline() : videoParamDTO.getSubtitleOutline());
            subtitleTrackClip.setOutlineColour(StrUtil.nullToDefault(subtitleTrackClip.getOutlineColour(), videoParamDTO.getSubtitleOutlineColour()));
            subtitleTrackClip.setBackColour(StrUtil.nullToDefault(subtitleTrackClip.getBackColour(), videoParamDTO.getSubtitleBackColour()));

            subtitleTrackClip.setFontSize(null != subtitleTrackClip.getFontSize() ? subtitleTrackClip.getFontSize() : videoParamDTO.getSubtitleFontSize());
            subtitleTrackClipList.add(subtitleTrackClip);
        }

        return subtitleTrackClipList;
    }

    public  SubtitleTrack subtitleForMaterialClipCopywriting(AiMaterialClipVideoTaskCopywritingCreateReqVO copywritingVO, List<SubtitleTrackClipForMaterialClip> subtitleTrackClipList, List<String> fonts, List<String> aaiMotionInEffects, List<String> effectColorStyles, Float timelineIn, Boolean isFlowerWord, VideoParamDTO videoParamDTO) {
        if(CollectionUtil.isEmpty(subtitleTrackClipList)){
            return new SubtitleTrack();
        }
        Collections.shuffle(aaiMotionInEffects);
        Collections.shuffle(effectColorStyles);
        Collections.shuffle(fonts);
        Iterator<SubtitleTrackClipForMaterialClip> iterator = subtitleTrackClipList.iterator();
        while (iterator.hasNext()){
            SubtitleTrackClipForMaterialClip subtitleTrackClip = iterator.next();
            if(subtitleTrackClip.getIsDiscard()){
                iterator.remove();
                continue;
            }
            subtitleTrackClip.setTextWidth(videoParamDTO.getSubtitleTextWidth());
            subtitleTrackClip.setY(null != videoParamDTO.getSubtitleY() ? videoParamDTO.getSubtitleY() : videoParamDTO.getSubtitleY());
            subtitleTrackClip.setX(null != videoParamDTO.getSubtitleX() ? videoParamDTO.getSubtitleX() : videoParamDTO.getSubtitleX());

//            subtitleTrackClip.setTimelineIn(videoParamDTO.getSubtitleTimelineIn());

            subtitleTrackClip.setAaiMotionInEffect(aaiMotionInEffects.get(0));

            subtitleTrackClip.setAaiMotionIn(videoParamDTO.getSubtitleAaiMotionIn());
            subtitleTrackClip.setAlignment(StrUtil.nullToDefault(copywritingVO.getAlignment(), "BottomCenter"));

            FontFace fontFace = new FontFace();
            FontFaceVO fontFaceVO = copywritingVO.getFontFace();
            if(null != fontFaceVO){
                fontFace.setBold(fontFaceVO.getBold());
                fontFace.setItalic(fontFaceVO.getItalic());
                fontFace.setUnderline(fontFaceVO.getUnderline());
            }else {
                fontFace.setBold(true);
            }
            subtitleTrackClip.setFontFace(fontFace);

//        if(isFlowerWord){
            subtitleTrackClip.setFont(StrUtil.nullToDefault(copywritingVO.getFont(), fonts.get(0)));
            subtitleTrackClip.setOutline(null != copywritingVO.getOutline() ? copywritingVO.getOutline() : videoParamDTO.getSubtitleOutline());
            subtitleTrackClip.setOutlineColour(StrUtil.nullToDefault(copywritingVO.getOutlineColour(), videoParamDTO.getSubtitleOutlineColour()));
            subtitleTrackClip.setBackColour(StrUtil.nullToDefault(copywritingVO.getBackColour(), videoParamDTO.getSubtitleBackColour()));
            subtitleTrackClip.setEffectColorStyle(StrUtil.nullToDefault(copywritingVO.getEffectColorStyle(), effectColorStyles.get(0)));
//        }

            subtitleTrackClip.setFontSize(null != copywritingVO.getFontSize() ? copywritingVO.getFontSize() : videoParamDTO.getSubtitleFontSize());
//        subtitleTrackClip.setFontSize(videoParamDTO.getSubtitleFontSize());
        }
        SubtitleTrack subtitleTrack = new SubtitleTrack();
        subtitleTrack.setSubtitleTrackClips(JSONUtil.toList(JSONUtil.toJsonStr(subtitleTrackClipList), SubtitleTrackClip.class));
        return subtitleTrack;
    }

    public void addTransition(List<VideoTrackClip> videoTrackClips, Float y, String color, Float radius, List<String> subTypes, Boolean isContinuous,Float timelineIn,VideoParamDTO videoParamDTO) {
        Collections.shuffle(subTypes);
        int subTypeCount = 0;
        Float count = 0F;
        //videoParamDTO.setBgGain(0F);
        //videoParamDTO.setIsBgColor(StringUtils.isNotBlank(color));
        //videoParamDTO.setBgRadius(radius);
        for (int i = 0; i < videoTrackClips.size(); i++) {
            VideoTrackClip videoTrackClip = videoTrackClips.get(i);
            videoTrackClip.setY(y);
            List<BaseEffect> effects = new ArrayList<>();
            VolumeEffect volumeEffect = new VolumeEffect();
            volumeEffect.setGain(0F);
            effects.add(volumeEffect);
            if (videoParamDTO.getBgRadius() != null) {
                BackgroundEffect backgroundEffect = new BackgroundEffect();
                backgroundEffect.setSubType("Blur");
                backgroundEffect.setRadius(videoParamDTO.getBgRadius());
                effects.add(backgroundEffect);
            }
            if (videoParamDTO.getIsBgColor()) {
                BackgroundEffect backgroundEffect = new BackgroundEffect();
                backgroundEffect.setSubType("Color");
                backgroundEffect.setColor(color);
                effects.add(backgroundEffect);
            }
            if(!isContinuous){
                if ((i + 1) % 2 == 0) {
                    count = count + tranTime;
                }
                if(videoTrackClip.getOut()!=null){
                    videoTrackClip.setTimelineIn((i + count) * videoTrackClip.getOut()+timelineIn);
                    videoTrackClip.setTimelineOut((i + count + 1) * videoTrackClip.getOut()+timelineIn);
                }
            }
            if (i % 2 == 1) {
                TransitionEffect transitionEffect = new TransitionEffect();
                transitionEffect.setSubType(subTypes.get(subTypeCount % subTypes.size()));
                transitionEffect.setDuration(1F);
                effects.add(transitionEffect);
                subTypeCount++;
            }
            videoTrackClip.setEffects(effects);
        }
    }

    public void addTransitionForMaterialClip(List<VideoTrackClip> videoTrackClips, Float y, String color, Float radius, List<String> subTypes, Boolean isContinuous,Float timelineIn,VideoParamDTO videoParamDTO) {
        Collections.shuffle(subTypes);
        int subTypeCount = 0;
        Float count = 0F;
        for (int i = 0; i < videoTrackClips.size(); i++) {
            VideoTrackClip videoTrackClip = videoTrackClips.get(i);
            videoTrackClip.setY(y);
            List<BaseEffect> effects = new ArrayList<>();
//            VolumeEffect volumeEffect = new VolumeEffect();
//            volumeEffect.setGain(0F);
//            effects.add(volumeEffect);
            if (videoParamDTO.getBgRadius() != null) {
                BackgroundEffect backgroundEffect = new BackgroundEffect();
                backgroundEffect.setSubType("Blur");
                backgroundEffect.setRadius(videoParamDTO.getBgRadius());
                effects.add(backgroundEffect);
            }
            if (videoParamDTO.getIsBgColor()) {
                BackgroundEffect backgroundEffect = new BackgroundEffect();
                backgroundEffect.setSubType("Color");
                backgroundEffect.setColor(color);
                effects.add(backgroundEffect);
            }
            if(!isContinuous){
                if ((i + 1) % 2 == 0) {
                    count = count + tranTime;
                }
                if(videoTrackClip.getOut()!=null){
                    videoTrackClip.setTimelineIn((i + count) * videoTrackClip.getOut()+timelineIn);
                    videoTrackClip.setTimelineOut((i + count + 1) * videoTrackClip.getOut()+timelineIn);
                }
            }
//            if (i % 2 == 1) {
            if (i > 0) {
                TransitionEffect transitionEffect = new TransitionEffect();
                transitionEffect.setSubType(subTypes.get(subTypeCount % subTypes.size()));
                transitionEffect.setDuration(tranTime);
                effects.add(transitionEffect);
                subTypeCount++;
            }
            List<BaseEffect> effects1 = videoTrackClip.getEffects();
            if(effects1==null){
                videoTrackClip.setEffects(new ArrayList<>());
            }
            videoTrackClip.getEffects().addAll(effects);
        }
    }

    public Timeline timeline(List<VideoTrack> videoTracks, List<AudioTrack> audioTracks, List<SubtitleTrack> subtitleTracks) {
        Timeline timeline = new Timeline();
        timeline.setVideoTracks(videoTracks);
        timeline.setAudioTracks(audioTracks);
        timeline.setSubtitleTracks(subtitleTracks);
        return timeline;
    }


}
