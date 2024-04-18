package net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.videodydatastatistics.vo;

import com.diboot.core.binding.annotation.BindField;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import net.hzxzkj.cxlaike.framework.common.pojo.PageParam;
import net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.memberdybind.MemberDyBindDO;
import net.hzxzkj.cxlaike.module.pay.enums.EntityCondtionConstants;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

import static net.hzxzkj.cxlaike.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;


@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class VideoDyDataStatisticsAnalyzePageReqVO extends PageParam {


    @Schema(description = "时间筛选类型 1-昨日 7-近7天 30-近30天")
    private Integer dateType;

    @Schema(description = "平台类型 1抖音  2快手")
    @NotNull(message = "未选择平台")
    private Integer platformType;

    @Schema(description = "排序字段 1-新增内容发布数 2-新增播放 3-总粉丝数 4-总播放量 5-总发布数")
    private Integer orderBy;

    @Schema(description = "是否升序 是-true 否-false")
    private Boolean isAsc;

    @Schema(description = "抖音账户ID")
    private Long dyBindId;

    @Schema(description = "分组ID")
    private Long groupId;

    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] statisticsTime;




}
