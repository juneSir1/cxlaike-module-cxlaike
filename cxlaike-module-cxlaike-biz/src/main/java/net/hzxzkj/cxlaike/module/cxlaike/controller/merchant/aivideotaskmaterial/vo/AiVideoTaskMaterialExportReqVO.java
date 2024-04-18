package net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.aivideotaskmaterial.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import net.hzxzkj.cxlaike.framework.common.pojo.PageParam;

@Schema(description = "管理后台 - ai视频任务素材 Excel 导出 Request VO，参数和 AiVideoTaskMaterialPageReqVO 是一致的")
@Data
public class AiVideoTaskMaterialExportReqVO {

    @Schema(description = "ai视频任务主键", example = "29710")
    private Long aiTaskId;

}
