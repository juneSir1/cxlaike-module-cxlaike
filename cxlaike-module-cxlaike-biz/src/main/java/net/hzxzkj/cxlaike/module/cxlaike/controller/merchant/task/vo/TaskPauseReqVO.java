package net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.task.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.taskext.vo.TaskExtBaseVO;
import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.taskmaterial.vo.TaskMaterialVO;

@Schema(description = "管理后台 - 商家发布任务暂停 Request VO")
@Data
@ToString(callSuper = true)
public class TaskPauseReqVO {

  @Schema(description = "主键", requiredMode = Schema.RequiredMode.REQUIRED, example = "10167")
  @NotNull(message = "主键不能为空")
  private Long id;

  @Schema(description = "是否暂停")
  @NotNull(message = "是否暂停不能为空")
  private Boolean isPause;


}
