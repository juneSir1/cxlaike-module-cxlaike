package net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.task.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import javax.validation.constraints.*;
import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.taskext.vo.TaskExtBaseVO;
import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.taskmaterial.vo.TaskMaterialVO;

@Schema(description = "管理后台 - 商家发布任务更新 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class TaskUpdateReqVO extends TaskBaseVO {

  @Schema(description = "主键", requiredMode = Schema.RequiredMode.REQUIRED, example = "10167")
  @NotNull(message = "主键不能为空")
  private Long id;

  @Schema(description = "任务素材列表 除了真人出镜别的任务都需要上传素材")
  private List<TaskMaterialVO> materialList;

  @Schema(description = "口播文案")
  @NotNull(message = "口播文案不能为空")
  @Size(min = 1, message = "口播文案至少需要 {min} 个")
  private List<String> copywritingList;

  @Schema(description = "任务扩展列表")
  @NotNull(message = "任务扩展列表不能为空")
  @Size(min = 1, message = "任务扩展列表至少需要 {min} 个等级")
  private List<TaskExtBaseVO> taskExtList;

}
