package net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.aivideotask.vo;

import io.swagger.v3.oas.annotations.Parameter;
import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import net.hzxzkj.cxlaike.framework.common.pojo.PageParam;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static net.hzxzkj.cxlaike.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - ai视频任务分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class AiVideoTaskPageReqVO extends PageParam {

  @Parameter(description = "任务标题")
  private String title;

  @Parameter(description = "任务类型")
  private List<Integer> clipType;

}
