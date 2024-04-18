package net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.aivideotask.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.ToString;

@Schema(description = "管理后台 - ai视频任务预览 Response VO")
@Data
@ToString(callSuper = true)
public class AiVideoTaskPreviewRespVO {

  @Schema(description = "主键")
  private Long id;

  @Schema(description = "预估数量")
  private Integer preNum;

}
