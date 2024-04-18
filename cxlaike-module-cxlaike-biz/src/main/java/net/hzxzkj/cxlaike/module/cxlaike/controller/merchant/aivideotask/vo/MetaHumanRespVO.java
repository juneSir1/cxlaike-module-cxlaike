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
public class MetaHumanRespVO {

  @Schema(description = "主键", requiredMode = Schema.RequiredMode.REQUIRED, example = "13516")
  private Long id;

  @Schema(description = "数字人名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "13516")
  private String name;

  @Schema(description = "数字人图片", requiredMode = Schema.RequiredMode.REQUIRED, example = "13516")
  private String picture;

  @Schema(description = "数字人背景图")
  private Long metaHumanBackgroundId;

  @Schema(description = "数字人背景图")
  private String metaHumanBackgroundUrl;


}
