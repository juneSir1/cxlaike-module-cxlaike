package net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.aicopywriting.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


/**
 * ai文案 Base VO，提供给添加、修改、详细的子 VO 使用
 * 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
 */
@Data
public class AiCopywritingBaseVO {


    @Schema(description = "行业code", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "行业code不能为空")
    private String professionCode;

    @Schema(description = "行业名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "李四")
    private String professionName;

    @Schema(description = "内容类型 1-团购推广套餐 2-产品宣传 3-门店推广 4-剧本创作", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotNull(message = "内容类型 1-团购推广套餐 2-产品宣传 3-门店推广 4-剧本创作不能为空")
    private Integer contentType;

    @Schema(description = "内容描述", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "内容描述不能为空")
    private String contentDesc;

    @Schema(description = "文案关键字", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "文案关键字不能为空")
    private String contentKeyword;

    @Schema(description = "字数限制 1-80 5-150 10-250 15-350 20-其他", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "字数限制不能为空")
    private Integer wordsCountRestrict;

    @Schema(description = "字数")
    private Integer wordsCount;

    @Schema(description = "条数限制1-1 5-3 10-5 15-其他 ", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "条数限制不能为空")
    private Integer quantityRestrict;

    @Schema(description = "条数")
    private Integer quantity;

    @Schema(description = "生成状态 1-生成中 2-完成 3-失败", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    //@NotNull(message = "生成状态 1-生成中 2-完成 3-失败不能为空")
    private Integer aiStatus;

    @Schema(description = "消耗数量", requiredMode = Schema.RequiredMode.REQUIRED)
    //@NotNull(message = "消耗数量不能为空")
    private Integer expendQuantity;

    @Schema(description = "ai文案", requiredMode = Schema.RequiredMode.REQUIRED)
    //@NotBlank(message = "ai文案不能为空")
    private String aiCopywriting;

    @Schema(description = "参考文案")
    private String referenceCopy;

    @Schema(description = "消耗金币")
    private Long expendGold;

}
