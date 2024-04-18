package net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.taskmaterial.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import net.hzxzkj.cxlaike.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static net.hzxzkj.cxlaike.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 商家任务素材分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class TaskMaterialPageReqVO extends PageParam {

    @Schema(description = "商家任务id", example = "9754")
    private Long taskId;

    @Schema(description = "类型(1.素材,2.ai剪辑视频)", example = "1")
    private Integer type;

    @Schema(description = "素材文件夹名", example = "王五")
    private String folderName;

    @Schema(description = "jobId", example = "31038")
    private String jobId;

    @Schema(description = "素材url", example = "https://www.iocoder.cn")
    private String materialUrl;

    @Schema(description = "状态", example = "1")
    private Integer status;

    @Schema(description = "序号")
    private Integer sort;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}
