package net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.aivideotaskcover.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import net.hzxzkj.cxlaike.framework.common.pojo.PageParam;

@Schema(description = "管理后台 - ai素材混剪视频封面图片关联 Excel 导出 Request VO，参数和 AiVideoTaskCoverPageReqVO 是一致的")
@Data
public class AiVideoTaskCoverExportReqVO {

    @Schema(description = "ai素材混剪任务id", example = "16522")
    private Long taskId;

    @Schema(description = "封面图片文件id", example = "6703")
    private Long fileId;

    @Schema(description = "封面url", example = "https://www.iocoder.cn")
    private String url;

    @Schema(description = "排序")
    private Integer sort;

}
