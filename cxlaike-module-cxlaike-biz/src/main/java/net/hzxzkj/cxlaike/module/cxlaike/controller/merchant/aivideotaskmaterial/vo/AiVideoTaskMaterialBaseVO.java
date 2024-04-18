package net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.aivideotaskmaterial.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * ai视频任务素材 Base VO，提供给添加、修改、详细的子 VO 使用
 * 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
 */
@Data
public class AiVideoTaskMaterialBaseVO {

  @Schema(description = "视频组名", example = "李四")
  private String name;

  @Schema(description = "文件id")
  private Long fileId;

  @Schema(description = "文件url")
  private String fileUrl;

  @Schema(description = "序号")
  private Integer sort;

  @Schema(description = "文件长度")
  private Integer fileLength;

}
