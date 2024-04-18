package net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.aitriallisten.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import net.hzxzkj.cxlaike.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static net.hzxzkj.cxlaike.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - ai语音试听功能分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class AiTrialListenPageReqVO extends PageParam {

    @Schema(description = "口播文案")
    private String copywriting;

    @Schema(description = "配音人")
    private String dubCode;

    @Schema(description = "配音语速")
    private String dubSpeechRate;

    @Schema(description = "配音语调")
    private String dubPitchRate;

    @Schema(description = "配音音量")
    private String dubGain;

    @Schema(description = "配音采样率")
    private String dubSamplingRate;

    @Schema(description = "状态", example = "2")
    private Integer status;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}
