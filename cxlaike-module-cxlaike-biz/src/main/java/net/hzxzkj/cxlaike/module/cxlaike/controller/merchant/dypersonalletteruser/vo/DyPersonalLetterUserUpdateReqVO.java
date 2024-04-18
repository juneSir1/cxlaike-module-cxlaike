package net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.dypersonalletteruser.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.validation.constraints.*;

@Schema(description = "管理后台 - 抖音私信用户管理更新 Request VO")
@Data
@ToString(callSuper = true)
public class DyPersonalLetterUserUpdateReqVO {

    @Schema(description = "id", requiredMode = Schema.RequiredMode.REQUIRED, example = "30266")
    @NotNull(message = "id不能为空")
    private Long id;

    @Schema(description = "状态 0-未沟通 1-已沟通 2-无意向 3-有意向 4-已合作", example = "2")
    private Integer communicationStatus;

    @Schema(description = "姓名", example = "赵六")
    private String realName;

    @Schema(description = "省code")
    private Integer provinceCode;

    @Schema(description = "省", example = "张三")
    private String provinceName;

    @Schema(description = "市code")
    private Integer cityCode;

    @Schema(description = "市", example = "赵六")
    private String cityName;

    @Schema(description = "区code")
    private Integer countyCode;

    @Schema(description = "区", example = "张三")
    private String countyName;

    @Schema(description = "手机号")
    private String mobile;

    @Schema(description = "微信")
    private String wx;

    @Schema(description = "公司")
    private String company;

    @Schema(description = "备注", example = "你猜")
    private String remark;


}
