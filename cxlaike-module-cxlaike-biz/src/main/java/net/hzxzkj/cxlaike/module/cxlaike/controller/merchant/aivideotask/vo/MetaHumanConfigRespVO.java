package net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.aivideotask.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.ToString;

@Schema(description = "数字人参数 resp VO")
@Data
@ToString(callSuper = true)
public class MetaHumanConfigRespVO {

  @Schema(description = "主键", requiredMode = Schema.RequiredMode.REQUIRED, example = "13516")
  private Long id;
  /**
   * 用户类型(1.达人端,2.系统,3.商家)
   */
  @Schema(description = "用户类型", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
  private Integer type;
  /**
   * 用户id
   */
  @Schema(description = "用户id", requiredMode = Schema.RequiredMode.REQUIRED, example = "13516")
  private Long userId;
  /**
   * 配音code
   */
  @Schema(description = "配音code", requiredMode = Schema.RequiredMode.REQUIRED, example = "13516")
  private String dubCode;
  /**
   * 数字人code
   */
  @Schema(description = "数字人code", requiredMode = Schema.RequiredMode.REQUIRED, example = "13516")
  private String metaHumanCode;
  /**
   * 数字人名称
   */
  @Schema(description = "数字人名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "13516")
  private String name;
  /**
   * 数字人描述
   */
  @Schema(description = "数字人描述", requiredMode = Schema.RequiredMode.REQUIRED, example = "13516")
  private String depict;
  /**
   * 数字人图片
   */
  @Schema(description = "数字人图片", requiredMode = Schema.RequiredMode.REQUIRED, example = "13516")
  private String picture;

}
