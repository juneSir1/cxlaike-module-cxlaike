package net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.aiclipvideotaskgroup.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import net.hzxzkj.cxlaike.framework.common.pojo.PageParam;
import java.time.LocalDateTime;
import org.springframework.format.annotation.DateTimeFormat;

import static net.hzxzkj.cxlaike.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - ai素材混剪视频任务-视频组设置 Excel 导出 Request VO，参数和 AiClipVideoTaskGroupPageReqVO 是一致的")
@Data
public class AiClipVideoTaskGroupExportReqVO {

    @Schema(description = "ai视频任务主键", example = "20704")
    private Long aiTaskId;

    @Schema(description = "内容标题对象", example = "26486")
    private Long contentTitleFontId;

    @Schema(description = "内容标题对象", example = "29735")
    private Long copywritingFontId;

    @Schema(description = "视频组名", example = "王五")
    private String name;

    @Schema(description = "视频组时长(秒)")
    private Integer totalDuration;

    @Schema(description = "序号")
    private Integer sort;

    @Schema(description = "视频组数量")
    private Integer num;

    @Schema(description = "是否原声")
    private Boolean original;

    @Schema(description = "时长设置类型", example = "1")
    private Integer durationType;

    @Schema(description = "时长设置-时长")
    private Integer duration;

    @Schema(description = "视频状态", example = "1")
    private Integer status;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}
