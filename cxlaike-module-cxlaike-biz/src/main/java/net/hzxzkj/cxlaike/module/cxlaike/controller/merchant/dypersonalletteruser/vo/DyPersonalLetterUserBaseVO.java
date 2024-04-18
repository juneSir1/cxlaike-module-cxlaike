package net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.dypersonalletteruser.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

/**
 * 抖音私信用户管理 Base VO，提供给添加、修改、详细的子 VO 使用
 * 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
 */
@Data
public class DyPersonalLetterUserBaseVO {

    @Schema(description = "用户open_id", example = "13497")
    private String openId;

    @Schema(description = "昵称", example = "赵六")
    private String nickName;

    @Schema(description = "头像")
    private String avatar;

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

    @Schema(description = "状态 0-未沟通 1-已沟通 2-无意向 3-有意向 4-已合作", example = "2")
    private Integer communicationStatus;

    @Schema(description = "手机号")
    private String mobile;

    @Schema(description = "微信")
    private String wx;

    @Schema(description = "公司")
    private String company;

    @Schema(description = "备注", example = "你猜")
    private String remark;

    @Schema(description = "私信用户id",hidden = true)
    private Long accountId;

    @Schema(description = "会话id")
    private String conversationId;

}
