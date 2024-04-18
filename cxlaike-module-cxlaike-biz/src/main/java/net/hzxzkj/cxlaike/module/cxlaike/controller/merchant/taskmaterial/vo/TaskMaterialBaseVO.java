package net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.taskmaterial.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import javax.validation.constraints.*;

/**
 * 商家任务素材 Base VO，提供给添加、修改、详细的子 VO 使用
 * 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
 */
@Data
public class TaskMaterialBaseVO {

    @Schema(description = "商家任务id", requiredMode = Schema.RequiredMode.REQUIRED, example = "9754")
    @NotNull(message = "商家任务id不能为空")
    private Long taskId;

    @Schema(description = "类型(1.素材,2.ai剪辑视频)", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotNull(message = "类型(1.素材,2.ai剪辑视频)不能为空")
    private Integer type;

    @Schema(description = "素材文件夹名", example = "王五")
    private String folderName;

    @Schema(description = "jobId", example = "31038")
    private String jobId;

    @Schema(description = "素材url", requiredMode = Schema.RequiredMode.REQUIRED, example = "https://www.iocoder.cn")
    @NotNull(message = "素材url不能为空")
    private String materialUrl;

    @Schema(description = "状态", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotNull(message = "状态不能为空")
    private Integer status;

    @Schema(description = "序号")
    private Integer sort;

}
