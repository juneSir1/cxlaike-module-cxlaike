package net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.aicopywriting.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Schema(description = "管理后台 - ai文案创建 Request VO")
@Data
@ToString(callSuper = true)
public class AiCopywritingCreateReqVO {

    @Schema(description = "行业code", requiredMode = Schema.RequiredMode.REQUIRED)
    //@NotBlank(message = "行业code不能为空")
    private String professionCode;

    @Schema(description = "行业名称")
    private String professionName;

    @Schema(description = "内容类型 1-团购推广套餐 2-产品宣传 3-门店推广 4-剧本创作不能为空 5-通用 6-内容裂变 7-生成标题 8-短视频脚本 9-字幕文案", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotNull(message = "内容类型 1-团购推广套餐 2-产品宣传 3-门店推广 4-剧本创作不能为空 5-通用 6-内容裂变 7-生成标题 8-短视频脚本 9-字幕文案")
    private Integer contentType;

    @Schema(description = "内容描述", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "内容描述不能为空")
    @Length(max = 1000,message = "输入内容最多只能1000字")
    private String contentDesc;

    @Schema(description = "文案关键字", requiredMode = Schema.RequiredMode.REQUIRED)
//    @NotBlank(message = "文案关键字不能为空")
    @Length(max = 100,message = "文案关键字最多只能100字")
    private String contentKeyword;

    @Schema(description = "字数限制 1-80 5-150 10-250 15-350 20-其他", requiredMode = Schema.RequiredMode.REQUIRED)
//    @NotNull(message = "字数限制不能为空")
    private Integer wordsCountRestrict;

    @Schema(description = "字数")
    private Integer wordsCount;

    @Schema(description = "条数限制1-1 5-3 10-5 15-其他 ", requiredMode = Schema.RequiredMode.REQUIRED)
//    @NotNull(message = "条数限制不能为空的")
    private Integer quantityRestrict;

    @Schema(description = "条数")
    @Max(value = 20,message = "最多20条")
    private Integer quantity;

    @Schema(description = "参考文案")
    @Length(max = 1000,message = "参考文案最多只能1000字")
    private String referenceCopy;

}
