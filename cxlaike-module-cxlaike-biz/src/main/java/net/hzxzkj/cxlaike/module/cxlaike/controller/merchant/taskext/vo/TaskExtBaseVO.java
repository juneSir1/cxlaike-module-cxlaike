package net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.taskext.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import lombok.Data;
import net.hzxzkj.cxlaike.framework.common.validation.InEnum;
import net.hzxzkj.cxlaike.module.cxlaike.enums.MemberCarryLevelType;

/**
 * 商家任务扩展 Base VO，提供给添加、修改、详细的子 VO 使用
 * 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
 */
@Data
public class TaskExtBaseVO {

  @Schema(description = "带货等级类型(0.lv0,1.lv1)", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
  @NotNull(message = "带货等级类型(0.lv0,1.lv1)不能为空")
  @InEnum(value = MemberCarryLevelType.class, message = "带货等级类型必须在 {value}")
  private Integer level;

  @Schema(description = "剩余数量", requiredMode = Schema.RequiredMode.REQUIRED)
  @NotNull(message = "剩余数量不能为空")
  @Min(value = 1, message = "最少数量为 {min} 之间")
  private Integer number;

}
