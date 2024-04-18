package net.hzxzkj.cxlaike.module.cxlaike.controller.admin.aivideotemplate.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import net.hzxzkj.cxlaike.framework.common.pojo.PageParam;
import java.time.LocalDateTime;
import org.springframework.format.annotation.DateTimeFormat;

import static net.hzxzkj.cxlaike.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - ai视频模板 Excel 导出 Request VO，参数和 AiVideoTemplatePageReqVO 是一致的")
@Data
public class AiVideoTemplateExportReqVO {

    @Schema(description = "类型", example = "2")
    private Integer type;

    @Schema(description = "内容值")
    private String value;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}
