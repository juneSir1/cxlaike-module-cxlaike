package net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.videodydatastatistics.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDate;

import static net.hzxzkj.cxlaike.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY;
import static net.hzxzkj.cxlaike.framework.common.util.date.DateUtils.TIME_ZONE_DEFAULT;

@Data
@ToString(callSuper = true)
public class VideoDyDataDateRespVO {

    @Schema(description = "新增量")
    private Integer countIncrement;

    @Schema(description = "日期")
    @JsonFormat(pattern = FORMAT_YEAR_MONTH_DAY, timezone = TIME_ZONE_DEFAULT)
    private LocalDate statisticsTime;

}
