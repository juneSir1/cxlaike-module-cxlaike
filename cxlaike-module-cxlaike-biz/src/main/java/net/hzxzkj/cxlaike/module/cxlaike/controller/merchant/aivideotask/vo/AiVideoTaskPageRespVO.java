package net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.aivideotask.vo;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;
import java.util.List;
import javax.validation.constraints.NotNull;
import lombok.Data;

/**
 * 描   述:
 *
 * @author ace
 * @version [版本号, 2023/9/20]
 * @see [相关类/方法]
 * 创建日期: 2023/9/20
 */
@Data
public class AiVideoTaskPageRespVO {

  @Schema(description = "主键")
  private Long id;
  @Schema(description = "任务标题")
  private String title;
  @Schema(description = "内容标题")
  private String contentTitle;
  @NotNull(message = "视频剪辑类型(1.ai混剪,2.数字人混剪,3.纯数字人口播,3.试听音频,3.ai素材混剪)不能为空")
  private Integer clipType;
  @Schema(description = "口播文案")
  private List<String> copywritingList;
  @Schema(description = "内容标题")
  private List<String> contentTitleList;
  @Schema(description = "数字人图片")
  private String digitalManImage;
  @Schema(description = "配音")
  private List<String> dubList;
  @Schema(description = "背景音乐")
  private List<String> backgroundMusicList;
  @Schema(description = "视频状态")
  private Integer status;
  @Schema(description = "画面比例")
  private Integer aspectRatioType;
  @Schema(description = "生成数量")
  private Integer videoNum;
  @Schema(description = "消耗金币数量")
  private Integer coinNum;

  @Schema(description = "创建时间")
  private LocalDateTime createTime;

}
