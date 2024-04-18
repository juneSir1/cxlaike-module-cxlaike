package net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.aisystemarg.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import net.hzxzkj.cxlaike.framework.common.pojo.PageParam;
import java.time.LocalDateTime;
import org.springframework.format.annotation.DateTimeFormat;

import static net.hzxzkj.cxlaike.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "用户 APP - 来客系统参数 Excel 导出 Request VO，参数和 AiSystemArgPageReqVO 是一致的")
@Data
public class AiSystemArgExportReqVO {

    @Schema(description = "系统参数类型", example = "2")
    private Integer sysType;

    @Schema(description = "具体类型", example = "2")
    private Integer type;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}
