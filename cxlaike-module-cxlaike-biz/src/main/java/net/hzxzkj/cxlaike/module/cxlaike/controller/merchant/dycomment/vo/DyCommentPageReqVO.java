package net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.dycomment.vo;

import lombok.*;
import io.swagger.v3.oas.annotations.media.Schema;
import net.hzxzkj.cxlaike.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static net.hzxzkj.cxlaike.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 抖音评论管理分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class DyCommentPageReqVO extends PageParam {

    @Schema(description = "该评论是否被置顶 1-是 0-否")
    private Integer top;

    @Schema(description = "评论者昵称", example = "王五")
    private String nickName;


}
