package net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.accountdydatastatistics.vo;

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
public class AccountDyDataStatisticsReqVO {

    @Schema(description = "时间筛选类型 1-昨日 7-近7天 30-近30天")
    private Integer dateType;

    @Schema(description = "时间段")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] statisticsTime;

    @Schema(description = "数据类型 1-分发 2-矩阵")
    @NotNull(message = "请选择数据类型")
    private Integer dataType;

    @Schema(description = "平台类型 1-抖音 2-快手")
    @NotNull(message = "请选择平台类型")
    private Integer platformType;

}
