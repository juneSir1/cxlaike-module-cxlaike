package net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.drrepresentative.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import net.hzxzkj.cxlaike.framework.common.pojo.PageParam;
import java.time.LocalDateTime;
import org.springframework.format.annotation.DateTimeFormat;

import static net.hzxzkj.cxlaike.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 达人探店代 Excel 导出 Request VO，参数和 DrRepresentativePageReqVO 是一致的")
@Data
public class DrRepresentativeExportReqVO {

    @Schema(description = "平台类型 1-抖音", example = "2")
    private Integer platformType;

    @Schema(description = "昵称", example = "宵征")
    private String nickname;

    @Schema(description = "粉丝数", example = "26089")
    private String fansCount;

    @Schema(description = "销售额")
    private String sales;

    @Schema(description = "带货等级")
    private String sellGrade;

    @Schema(description = "内容力")
    private String contentGrade;

    @Schema(description = "信用分")
    private String creditScore;

    @Schema(description = "行业")
    private String profession;

    @Schema(description = "用户信息")
    private String userInfo;

    @Schema(description = "城市")
    private String city;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}
