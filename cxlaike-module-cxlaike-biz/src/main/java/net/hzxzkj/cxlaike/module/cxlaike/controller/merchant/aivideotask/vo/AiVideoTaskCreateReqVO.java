package net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.aivideotask.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.aivideotaskmaterial.vo.AiVideoTaskMaterialBaseVO;

@Schema(description = "管理后台 - ai视频任务创建 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class AiVideoTaskCreateReqVO extends AiVideoTaskBaseVO {

  @Schema(description = "素材列表")
  List<AiVideoTaskMaterialBaseVO> materialList;

  @Schema(description = "口播文案")
  @NotNull(message = "口播文案不能为空")
  @Size(min = 1)
  private List<String> copywritingList;

  @Schema(description = "用户id",hidden = true)
  private Long userId;
}
