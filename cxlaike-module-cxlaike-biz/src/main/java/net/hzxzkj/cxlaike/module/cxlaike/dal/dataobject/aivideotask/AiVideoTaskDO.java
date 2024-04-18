package net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.aivideotask;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import net.hzxzkj.cxlaike.framework.common.validation.InEnum;
import net.hzxzkj.cxlaike.framework.mybatis.core.dataobject.BaseDO;
import net.hzxzkj.cxlaike.framework.mybatis.core.type.JsonLongSetTypeHandler;
import net.hzxzkj.cxlaike.framework.mybatis.core.type.LongListTypeHandler;
import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.aivideotask.vo.AiMaterialClipVideoTaskContentTitleCreateReqVO;
import net.hzxzkj.cxlaike.module.cxlaike.enums.CopywritingSourceType;
import net.hzxzkj.cxlaike.module.cxlaike.enums.CoverSelectType;

/**
 * ai视频任务 DO
 *
 * @author 宵征源码
 */
@TableName(value = "cxlaike_ai_video_task", autoResultMap = true)
@KeySequence("cxlaike_ai_video_task_seq")
// 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AiVideoTaskDO extends BaseDO {

  /**
   * 主键
   */
  @TableId
  private Long id;

  /**
   * 用户Id
   */
  private Long userId;
  /**
   * 任务标题
   */
  private String title;

  /**
   * 内容标题
   */
  private String contentTitle;

  /**
   * 是否有文案
   */
  private Boolean hasCopywriting;

  /**
   * 是否预览视频
   */
  private Boolean isPreview;

  /**
   * 是否模板视频
   */
  private Boolean isTemplate;

  /**
   * 字幕来源类型
   * @see CopywritingSourceType
   */
  private Integer copywritingSourceType;

  /**
   * 视频时长
   */
  private Integer videoDuration;

  /**
   * 视频时长类型(1, "随镜头组设置" 2, "全局字幕配音" 3, "固定时长")
   */
  private Integer videoDurationType;

  /**
   * 视频剪辑类型(1.ai混剪,2.数字人混剪,3.纯数字人口播,4.试听音频,5.ai素材混剪)
   */
  private Integer clipType;
  /**
   * 数字人
   */
  private Long metaHumanId;
  /**
   * 数字人背景图
   */
  private Long metaHumanBackgroundId;
  /**
   * 配音人idList
   */
  @TableField(typeHandler = JsonLongSetTypeHandler.class)
  private Set<Long> dubIdList;
  /**
   * 配音语速
   */
  private Integer dubSpeechRate;
  /**
   * 配音语调
   */
  private Integer dubPitchRate;
  /**
   * 配音音量
   */
  private Float dubGain;
  /**
   * 配音采样率
   */
  private Integer dubSamplingRate;
  /**
   * 选择字幕code
   */
  private Integer subtitleType;
  /**
   * 背景音乐url
   */
  @TableField(typeHandler = JsonLongSetTypeHandler.class)
  private Set<Long> pipedMusicIdList;

  /**
   * 背景音乐音量
   */
  private Float pipedMusicVolume;
  /**
   * 剪辑生成数量
   */
  private Integer videoNum;
  /**
   * 画面比例类型
   */
  private Integer aspectRatioType;
  /**
   * 视频布局类型
   */
  private Integer videoLayoutType;
  /**
   * 数字人位置类型
   */
  private Integer metaHumanLayoutType;
  /**
   * 视频背景图片Id
   */
  private Long backgroundImageId;
  /**
   * 视频封面id
   */
  private Long coverId;

  /**
   * 视频封面选择类型
   * @see CoverSelectType
   */
  private Integer coverType;
  /**
   * 视频封面ids
   */
  @TableField(typeHandler = LongListTypeHandler.class)
  private List<Long> coverIds;
  /**
   * 视频状态
   */
  private Integer status;
  /**
   * 消耗金币数量
   */
  private Long coinNum;

  /**
   * 租户ID
   */
  private  Long tenantId;

}
