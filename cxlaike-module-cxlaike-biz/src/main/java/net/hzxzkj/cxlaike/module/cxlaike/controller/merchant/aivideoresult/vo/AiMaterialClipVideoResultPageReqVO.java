package net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.aivideoresult.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import net.hzxzkj.cxlaike.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

import static net.hzxzkj.cxlaike.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - ai视频生成结果分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class AiMaterialClipVideoResultPageReqVO extends PageParam {

    @Schema(description = "任务id")
    @NotNull(message = "任务id不能为空")
    private Long aiTaskId;

    @Schema(description = "视频质量（0，无重复，1，低重复，2普通）")
    private Integer videoQuality;


}
