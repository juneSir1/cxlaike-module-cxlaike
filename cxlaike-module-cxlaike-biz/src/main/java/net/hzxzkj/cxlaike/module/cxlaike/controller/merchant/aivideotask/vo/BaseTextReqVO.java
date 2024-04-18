package net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.aivideotask.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import net.hzxzkj.cxlaike.framework.ice.core.entity.timeline.subtitle.FontFace;

/**
 * 描   述:
 * ai素材混剪视频文字设置请求基础类
 * @author tkk
 * @version [版本号, 2023/12/13]
 * @see [相关类/方法]
 * 创建日期: 2023/12/13
 */
@Data
public class BaseTextReqVO {

  @Schema(description = "花字设置")
//  @InEnum(value = SubtitleType.class, message = "字幕类型必须在 {value}")
  private String EffectColorStyle;

  /**
   * 否
   * 横幅文字左上角距离输出视频左上角的横向距离。
   * 注：支持百分比和像素两种形式。当取值为[0～0.9999]时，表示相对输出视频宽的占比。
   * 当取值为>=2的整数时，表示绝对像素。默认为0。该坐标会按照素材尺寸和成片尺寸进行缩放。
   */
  @Schema(description = "(比例缩放)文字距左上角横向距离,支持百分比和像素两种形式,当取值为[0～0.9999]时，表示相对输出视频宽的占比,当取值为>=2的整数时，表示绝对像素")
  private Float X;

  /**
   * 否
   * 横幅文字左上角距离输出视频左上角的纵向距离。
   * 注：支持百分比和像素两种形式。当取值为[0～0.9999]时，表示相对输出视频高的占比。
   * 当取值为>=2的整数时，表示绝对像素。默认为0。该坐标会按照素材尺寸和成片尺寸进行缩放。
   */
  @Schema(description = "(比例缩放)文字距左上角纵向距离,支持百分比和像素两种形式,当取值为[0～0.9999]时，表示相对输出视频宽的占比,当取值为>=2的整数时，表示绝对像素")
  private Float Y;

  /**
   * 否
   * 横幅文字左上角距离输出视频左上角的横向距离。
   * 注：支持百分比和像素两种形式。当取值为[0～0.9999]时，表示相对输出视频宽的占比。
   * 当取值为>=2的整数时，表示绝对像素。默认为0。该坐标不会按照素材尺寸和成片尺寸进行缩放。
   */
  @Schema(description = "(不会比例缩放)文字距左上角横向距离,支持百分比和像素两种形式,当取值为[0～0.9999]时，表示相对输出视频宽的占比,当取值为>=2的整数时，表示绝对像素")
  private Float FixedX;

  /**
   * 否
   * 横幅文字左上角距离输出视频左上角的纵向距离。
   * 注：支持百分比和像素两种形式。
   * 当取值为[0～0.9999]时，表示相对输出视频宽的占比。
   * 当取值为>=2的整数时，表示绝对像素。默认为0。
   * 该坐标不会按照素材尺寸和成片尺寸进行缩放。
   */
  @Schema(description = "(不会比例缩放)文字距左上角纵向距离,支持百分比和像素两种形式,当取值为[0～0.9999]时，表示相对输出视频宽的占比,当取值为>=2的整数时，表示绝对像素")
  private Float FixedY;

  /**
   * 否
   * 横幅文字的字体。
   * 具体支持的字体参见字体列表。默认为SimSun字体。
   */
  @Schema(description = "字体")
  private String Font;

  /**
   * 是
   * 横幅文字的字号。
   * 该字号会根据素材尺寸和成片尺寸进行缩放。
   * 注：FontSize 与 FixedFontSize 仅能填写一个。
   * 如果均填写，仅有 FontSize 生效。
   */
  @Schema(description = "(比例缩放)字号")
  private Integer FontSize;

  /**
   * 是
   * 横幅文字的字号。
   * 该字号不会根据素材尺寸和成片尺寸进行缩放。
   * 注：FontSize 与 FixedFontSize 仅能填写一个。
   * 如果均填写，仅有 FontSize 生效。
   */
  @Schema(description = "(不会比例缩放)字号,fontSize有值则当前字段失效")
  private Integer FixedFontSize;

  /**
   * 否
   * 横幅文字的颜色，格式为#后跟16进制值。例如：#ffffff。
   */
  @Schema(description = "文字颜色")
  private String FontColor;

  /**
   * 否
   * 横幅文字的字体外观。
   */
  @Schema(description = "字体外观")
  private FontFaceVO FontFace;

  /**
   * 否
   * 横幅文字逆时针旋转角度。单位：度，默认为0。
   * 字幕行预先旋转过的角度，取值范围：[0,360]
   */
  @Schema(description = "文字逆时针旋转角度")
  private Float Angle;

  /**
   * 否
   * 横幅文字描边宽度。单位：像素值，默认为0。
   * 花字描边宽度的像素值，默认为0。推荐缩放比为2∶25，例如，字体大小设置为25号，则描边宽度设置为2
   */
  @Schema(description = "文字描边宽度")
  private Integer Outline;

  /**
   * 否
   * 横幅文字描边颜色，格式为#后跟16进制值。例如：#ffffff。
   */
  @Schema(description = "描边颜色，使用6位十六进制RGB值表示，格式为#xxyyzz")
  private String OutlineColour;

  /**
   * 否
   * 横幅文字投下阴影的深度，单位：像素值，默认为0。
   * 花字阴影深度的像素值，默认为0。推荐缩放比为2∶25，例如，字体大小设置为25号，则阴影深度设置为2
   */
  @Schema(description = "阴影深度的像素值")
  private Integer Shadow;

  /**
   * 否
   * 横幅文字阴影颜色，格式为#后跟16进制值。例如：#ffffff。
   */
  @Schema(description = "阴影颜色，使用6位十六进制RGB值表示，格式为#xxyyzz")
  private String BackColour;

  /**
   * 否
   * 横幅文字定位对齐方式，默认为TopLeft，支持设置：
   * TopLeft：视频左上角
   * TopCenter：视频竖直中轴线上侧
   * TopRight：视频右上角
   * CenterLeft：视频水平中轴线左侧
   * CenterCenter：视频中心位置
   * CenterRight：视频水平中轴线右侧
   * BottomLeft：视频左下角
   * BottomCenter：视频竖直中轴线下侧
   * BottomRight：视频右下角
   * 字幕对齐方式。如果设置此参数，字幕的X、Y坐标表示margin
   */
  @Schema(description = "文字定位对齐方式")
  private String Alignment;

  /**
   * 否
   * 横幅文字字间距。单位：像素值，默认为0。
   */
  @Schema(description = "文字字间距。单位：像素值")
  private Integer Spacing;

  /**
   * 否
   * 字幕文本框宽度，当设置AdaptMode时生效。将按照该值设置文本框宽度进行自动换行或缩放。不填写时，会按照视频宽度进行自动换行或缩放。单位：像素值。
   */
  private Integer TextWidth;

  /**
   * 是否随机0否 1是
   */
  private Boolean effectColorSwitch;

}
