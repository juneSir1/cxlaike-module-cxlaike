package net.hzxzkj.cxlaike.module.cxlaike.service.aivideotemplate.dto;

import lombok.Data;
import net.hzxzkj.cxlaike.framework.ice.core.entity.timeline.video.VideoTrackClip;
import net.hzxzkj.cxlaike.module.cxlaike.enums.template.TemplateVirtualTypeEnum;

import java.util.List;

@Data
public class VideoParamDTO {

    private  String name;

    private  Integer type;


    // ========== 视频配置============

    /**
     * 视频位置
     */
    private  Float videoY;

    // ========== 文案配置============

    /**
     * 文案颜色
     */
    private  String  contentColor ;

    /**
     * 文案音量
     */
    private  Float contentGain;

    /**
     * 文案默认语速
     */
    private  Integer  contentSpeechRate;

    /**
     * 文案位置
     */
    private  Float  contentY;

    /**
     * 文案位置
     */
    private Float  contentX;

    /**
     * 文案字体大小
     */
    private Integer   contentFontSize;

    /**
     * 文案阴影颜色
     */
    private String contentBackColour;

    /**
     * 文案描边颜色
     */
    private String contentOutlineColour;

    /**
     * 文案描边宽度
     */
    private Integer contentOutline;

    /**
     * 文案入场特效时长。
     */
    private Float contentAaiMotionIn;

    /**
     * 文案文字花字样式类型
     */
    private String contentEffectColorStyle;

    /**
     * 用于设置文案定位对齐方式
     */
    private String contentAlignment;




    // ========== 标题============
    /**
     * 标题文本框宽度
     */
    private Integer subtitleTextWidth;


    /**
     * 文案位置
     */
    private  Float  subtitleY;

    /**
     * 文案位置
     */
    private Float  subtitleX;


    /**
     * 标题出现时间
     */
    private Float subtitleTimelineIn;


    /**
     *  标题阴影颜色
     */
    private String  subtitleBackColour;


    /**
     * 标题描边颜色
     */
    private String  subtitleOutlineColour;


    /**
     * 标题描边宽度
     */
    private Integer  subtitleOutline;

    /**
     * 标题文字入场特效时长
     */
    private Float   subtitleAaiMotionIn;

    /**
     * 用于设置标题定位对齐方式
     */
    private String  subtitleAlignment;

    /**
     * 标题文字的字号
     */
    private Integer subtitleFontSize;



    // ========== 背景配置============

    /**
     * 是否有背景图片
     */
    private  Boolean  isBgImage ;

    /**
     * 是否有背景颜色
     */
    private  Boolean  isBgColor ;

    /**
     *  背景模糊程度
     */
    private  Float bgRadius;

    /**
     *  背景颜色
     */
    private  String bgColor;

    /**
     * 背景音量
     */
    private   Float bgGain;

    // ========== 数字人配置============

    /**
     * 数字人模板类型
     */
    private  Integer virtualType;

    /**
     * 数字人分辨率
     * 3 竖屏 1080*1920
     * 4 横屏 1920*1080
     */
    private Integer virtualVideoResolution;

    /**
     * 数字人音量
     */
    private   Float virtualVideoGain;

    /**
     * 数字人位置
     */
    private  Float virtualVideoY;

    /**
     * 数字人位置
     */
    private Float virtualVideoX;

    /**
     * 数字人宽度
     */
    private Float virtualVideoWidth;
    /**
     * 数字人高度
     */
    private Float virtualVideoHeight;

    /**
     * 视频开始时间
     */
    private Float timelineIn=0.04F;
}
