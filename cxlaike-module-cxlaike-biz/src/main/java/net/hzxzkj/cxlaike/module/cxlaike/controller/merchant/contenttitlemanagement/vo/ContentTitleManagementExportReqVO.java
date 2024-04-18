package net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.contenttitlemanagement.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import net.hzxzkj.cxlaike.framework.common.pojo.PageParam;
import java.time.LocalDateTime;
import org.springframework.format.annotation.DateTimeFormat;

import static net.hzxzkj.cxlaike.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 任务内容标题管理 Excel 导出 Request VO，参数和 ContentTitleManagementPageReqVO 是一致的")
@Data
public class ContentTitleManagementExportReqVO {

    @Schema(description = "任务id", example = "10586")
    private Long taskId;

    @Schema(description = "视频组id", example = "14770")
    private Long videoGroupId;

    @Schema(description = "口播文案")
    private String contentTitle;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}
