package net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.taskmaterial.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.*;

@Schema(description = "管理后台 - 商家任务素材创建 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class TaskMaterialCreateReqVO extends TaskMaterialBaseVO {

}
