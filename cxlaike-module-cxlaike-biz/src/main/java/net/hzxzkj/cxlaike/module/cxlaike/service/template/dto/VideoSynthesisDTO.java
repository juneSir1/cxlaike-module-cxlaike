package net.hzxzkj.cxlaike.module.cxlaike.service.template.dto;

import lombok.Data;
import net.hzxzkj.cxlaike.framework.ice.core.entity.timeline.audio.BaseAudioTrackClip;
import net.hzxzkj.cxlaike.framework.ice.core.entity.timeline.subtitle.SubtitleTrackClip;
import net.hzxzkj.cxlaike.framework.ice.core.entity.timeline.video.VideoTrackClip;
import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.aivideotask.vo.AiMaterialClipVideoTaskContentTitleCreateReqVO;
import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.aivideotask.vo.AiMaterialClipVideoTaskCopywritingCreateReqVO;

import java.util.List;

@Data
public class VideoSynthesisDTO {
    private String virtualVideoURL;
    private String bgVirtualImage;
    private String coverImage;
    private List<VideoTrackClip> videoTrackClips;
    private List<BaseAudioTrackClip> audioTrackClips;
    private List<SubtitleTrackClip> subtitleTrackClipList;
    private String content;
    /**
     * 口播文案来源类型
     */
//    private Integer copywritingSourceType;
    /**
     * 口播文案的设置
     */
    private AiMaterialClipVideoTaskCopywritingCreateReqVO copywritingVO;
    private String voice;
    private String bgMediaURL;
    private Float bgMediaVolume;
    private String title;

    /**
     * 展示标题内容的设置
     */
    private AiMaterialClipVideoTaskContentTitleCreateReqVO contentTitleVO;
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
    /**
     * 是否全局字幕文案
     */
    private Boolean isGlobalSubtitleDubbing;




}
