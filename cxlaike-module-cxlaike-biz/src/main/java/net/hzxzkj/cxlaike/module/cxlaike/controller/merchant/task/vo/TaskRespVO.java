package net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.task.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.taskext.vo.MerchantTaskExtRespVO;
import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.taskmaterial.vo.TaskMaterialVO;

@Schema(description = "管理后台 - 商家发布任务 Response VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class TaskRespVO extends TaskBaseVO {

  @Schema(description = "主键", requiredMode = Schema.RequiredMode.REQUIRED, example = "10167")
  private Long id;

  @Schema(description = "发布任务商家id", requiredMode = Schema.RequiredMode.REQUIRED, example = "4282")
  private Long merchantId;

  @Schema(description = "任务扩展列表")
  private List<MerchantTaskExtRespVO> taskExtList;

  @Schema(description = "任务素材列表 除了真人出镜别的任务都需要上传素材")
  private List<TaskMaterialVO> materialList;

  @Schema(description = "口播文案")
  private List<String> copywritingList;

  @Schema(description = "地区要求")
  private List<String[]> areaRequireList;

  @Schema(description = "商家列表")
  private List<ShopRespVO> shopList;

  @Schema(description = "任务状态", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
  private Integer status;

  @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
  private LocalDateTime createTime;

}
