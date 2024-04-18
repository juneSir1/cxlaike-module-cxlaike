package net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.template.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import net.hzxzkj.cxlaike.framework.common.pojo.PageParam;
import java.time.LocalDateTime;
import org.springframework.format.annotation.DateTimeFormat;

import static net.hzxzkj.cxlaike.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - ai素材混剪视频模板 Excel 导出 Request VO，参数和 TemplatePageReqVO 是一致的")
@Data
public class TemplateExportReqVO {

    @Schema(description = "ai视频任务id", example = "12074")
    private Long aiTaskId;

    @Schema(description = "模板名称", example = "王五")
    private String tempName;

    @Schema(description = "mediaURL", example = "https://www.iocoder.cn")
    private String videoUrl;

    @Schema(description = "视频结果id", example = "28563")
    private Long videoId;

    @Schema(description = "视频时长")
    private Integer duration;

    @Schema(description = "画面比例类型", example = "1")
    private Integer aspectRatioType;

    @Schema(description = "视频段数")
    private Integer groupNum;

    @Schema(description = "是否系统模板")
    private Byte isSystem;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

    @Schema(description = "模板分类")
    private Integer tempIndustry;

}
