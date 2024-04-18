package net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.professionvideo.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import net.hzxzkj.cxlaike.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static net.hzxzkj.cxlaike.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 精选行业视频分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ProfessionVideoPageReqVO extends PageParam {

    @Schema(description = "标题")
    private String title;

    @Schema(description = "播放量", example = "22881")
    private String playCount;

    @Schema(description = "封面url", example = "https://www.iocoder.cn")
    private String coverUrl;

    @Schema(description = "视频url", example = "https://www.iocoder.cn")
    private String videoUrl;

    @Schema(description = "时长")
    private String duration;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}
