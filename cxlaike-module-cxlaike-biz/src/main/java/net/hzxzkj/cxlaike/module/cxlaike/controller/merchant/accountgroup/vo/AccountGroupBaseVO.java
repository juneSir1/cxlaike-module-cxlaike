package net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.accountgroup.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * 商户抖音分组管理 Base VO，提供给添加、修改、详细的子 VO 使用
 * 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
 */
@Data
public class AccountGroupBaseVO {

    @Schema(description = "分组名称", example = "赵六")
    @NotBlank(message = "分组名不能为空")
    @Size(max = 30,message = "字数过长")
    private String name;


    @Schema(description = "所属平台", example = "1抖音 2快手")
    @NotNull(message = "平台不能为空")
    private Integer platformType;

}
