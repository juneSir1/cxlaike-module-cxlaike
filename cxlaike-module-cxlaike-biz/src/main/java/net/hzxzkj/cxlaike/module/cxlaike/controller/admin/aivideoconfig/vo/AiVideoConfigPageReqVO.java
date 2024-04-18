package net.hzxzkj.cxlaike.module.cxlaike.controller.admin.aivideoconfig.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import net.hzxzkj.cxlaike.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static net.hzxzkj.cxlaike.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - ai视频配置分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class AiVideoConfigPageReqVO extends PageParam {

    @Schema(description = "类型(1.AaiMotionInEffect,2.SubType,3.Fonts,4.Keywords,5.EffectColorStyle,6.Colors,7.Symbol)", example = "1")
    private Integer type;

    @Schema(description = "内容值")
    private String value;

    @Schema(description = "0.关闭,1.开启)", example = "2")
    private Integer status;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}
