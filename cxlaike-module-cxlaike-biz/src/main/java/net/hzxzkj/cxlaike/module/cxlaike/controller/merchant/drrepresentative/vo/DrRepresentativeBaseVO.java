package net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.drrepresentative.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import javax.validation.constraints.*;

/**
 * 达人探店代 Base VO，提供给添加、修改、详细的子 VO 使用
 * 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
 */
@Data
public class DrRepresentativeBaseVO {

    @Schema(description = "平台类型 1-抖音", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @NotNull(message = "平台类型 1-抖音不能为空")
    private Integer platformType;

    @Schema(description = "昵称", example = "宵征")
    private String nickname;

    @Schema(description = "头像")
    private String avatar;

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

}
