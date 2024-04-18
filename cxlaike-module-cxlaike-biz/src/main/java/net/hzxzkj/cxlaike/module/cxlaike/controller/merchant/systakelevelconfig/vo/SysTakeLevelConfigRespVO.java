package net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.systakelevelconfig.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.NotNull;
import lombok.Data;
import net.hzxzkj.cxlaike.framework.common.validation.InEnum;
import net.hzxzkj.cxlaike.module.cxlaike.enums.MemberCarryLevelType;
import net.hzxzkj.cxlaike.module.cxlaike.enums.SysTakeLevelType;

/**
 * 描   述:
 *
 * @author ace
 * @version [版本号, 2023/9/14]
 * @see [相关类/方法]
 * 创建日期: 2023/9/14
 */
@Data
public class SysTakeLevelConfigRespVO {
  @Schema(description = "类型(1.云探-ai,2.云探-数字人混剪,3.实探-现场拍摄,4.实探-真人出镜)", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
  @NotNull(message = "类型(1.云探-ai,2.云探-数字人混剪,3.实探-现场拍摄,4.实探-真人出镜)不能为空")
  private Integer type;

  @Schema(description = "等级类型", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
  @NotNull(message = "等级类型不能为空")
  private Integer levelType;

  @Schema(description = "服务费(分)", requiredMode = Schema.RequiredMode.REQUIRED)
  @NotNull(message = "服务费(分)不能为空")
  private Integer fee;

  @Schema(description = "一级分销%", requiredMode = Schema.RequiredMode.REQUIRED)
  @NotNull(message = "一级分销%不能为空")
  private Integer memberOneRatio;

  @Schema(description = "二级分销%", requiredMode = Schema.RequiredMode.REQUIRED)
  @NotNull(message = "二级分销%不能为空")
  private Integer memberTwoRatio;

  @Schema(description = "服务商%", requiredMode = Schema.RequiredMode.REQUIRED)
  @NotNull(message = "服务商%不能为空")
  private Integer merchantRatio;

  @Schema(description = "达人%", requiredMode = Schema.RequiredMode.REQUIRED)
  @NotNull(message = "达人%不能为空")
  private Integer memberRatio;

  @Schema(description = "平台%", requiredMode = Schema.RequiredMode.REQUIRED)
  @NotNull(message = "平台%不能为空")
  private Integer systemRatio;
}
