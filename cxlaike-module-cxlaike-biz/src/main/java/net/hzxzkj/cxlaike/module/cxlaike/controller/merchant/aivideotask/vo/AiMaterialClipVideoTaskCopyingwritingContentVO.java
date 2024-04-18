package net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.aivideotask.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * 描   述:
 *
 * @author tkk
 * @version [版本号, 2023/12/14]
 * @see [相关类/方法]
 * 创建日期: 2023/12/14
 */
@Data
public class AiMaterialClipVideoTaskCopyingwritingContentVO {

  /**
   * 字幕文案
   */
  @Schema(description = "字幕文案")
  @NotNull(message = "字幕文案不能为空")
  private String copywriting;

  /**
   * 文件id
   */
  @Schema(description = "文件id,自配音必传")
  private String fileId;

  /**
   * 文件名
   */
  @Schema(description = "文件名,自配音必传")
  private String fileName;

  /**
   * 音频url
   */
  @Schema(description = "音频url,自配音必传")
  private String url;

  /**
   * 音频时长
   */
  @Schema(description = "音频时长,自配音必传")
  private Float duration;

}
