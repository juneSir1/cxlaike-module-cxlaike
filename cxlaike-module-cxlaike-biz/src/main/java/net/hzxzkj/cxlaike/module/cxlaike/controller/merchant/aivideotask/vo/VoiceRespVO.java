package net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.aivideotask.vo;

import io.swagger.v3.oas.annotations.media.Schema;
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
public class VoiceRespVO {

  @Schema(description = "主键", requiredMode = Schema.RequiredMode.REQUIRED, example = "13516")
  private Long id;
  @Schema(description = "配音员名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "13516")
  private String name;
}
