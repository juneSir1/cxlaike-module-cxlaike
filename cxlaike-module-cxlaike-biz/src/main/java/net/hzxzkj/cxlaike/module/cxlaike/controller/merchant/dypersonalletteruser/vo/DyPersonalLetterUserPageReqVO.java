package net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.dypersonalletteruser.vo;

import lombok.*;
import io.swagger.v3.oas.annotations.media.Schema;
import net.hzxzkj.cxlaike.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static net.hzxzkj.cxlaike.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 抖音私信用户管理分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class DyPersonalLetterUserPageReqVO extends PageParam {


    @Schema(description = "状态 0-未沟通 1-已沟通 2-无意向 3-有意向 4-已合作", example = "2")
    private Integer communicationStatus;

    @Schema(description = "账号昵称", example = "2")
    private String nickName;

    @Schema(description = "有电话/微信", example = "2")
    private Integer contactMark;

}
