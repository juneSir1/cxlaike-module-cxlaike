package net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.videodydatastatistics.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;

import static net.hzxzkj.cxlaike.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Data
@ToString(callSuper = true)
public class VideoDyDataStatisticsReqVO {

    @Schema(description = "平台类型 1抖音 2快手")
    @NotNull(message = "未选择平台")
    private Integer platformType;

    @Schema(description = "时间筛选类型 1-昨日 7-近7天 30-近30天")
    private Integer dateType;

    @Schema(description = "时间段")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDate[] statisticsTime;

}
