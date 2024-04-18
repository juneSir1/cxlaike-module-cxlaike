package net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.clientcase.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import net.hzxzkj.cxlaike.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static net.hzxzkj.cxlaike.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 客户案例分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ClientCasePageReqVO extends PageParam {

    @Schema(description = "标题")
    private String title;

    @Schema(description = "标签")
    private String label;

    @Schema(description = "封面url", example = "https://www.iocoder.cn")
    private String coverUrl;

    @Schema(description = "视频url", example = "https://www.iocoder.cn")
    private String videoUrl;

    @Schema(description = "时长")
    private String content;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}
