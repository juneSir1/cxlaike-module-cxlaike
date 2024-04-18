package net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.task.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 描   述:
 *
 * @author ace
 * @version [版本号, 2023/9/21]
 * @see [相关类/方法]
 * 创建日期: 2023/9/21
 */
@Data
public class ShopRespVO {

  @Schema(description = "主键", requiredMode = Schema.RequiredMode.REQUIRED, example = "10167")
  private Long id;
  @Schema(description = "商家名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "10167")
  private String name;
}
