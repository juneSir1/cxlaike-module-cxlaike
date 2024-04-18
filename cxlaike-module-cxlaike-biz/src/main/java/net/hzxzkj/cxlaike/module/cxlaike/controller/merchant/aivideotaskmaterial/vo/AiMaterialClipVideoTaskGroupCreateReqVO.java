package net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.aivideotaskmaterial.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import net.hzxzkj.cxlaike.framework.common.validation.InEnum;
import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.aivideotask.vo.AiMaterialClipVideoTaskContentTitleCreateReqVO;
import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.aivideotask.vo.AiMaterialClipVideoTaskCopywritingCreateReqVO;
import net.hzxzkj.cxlaike.module.cxlaike.enums.MaterialVideoDurationType;

import java.util.List;

@Schema(description = "商户后台 - ai素材混剪视频任务素材创建 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class AiMaterialClipVideoTaskGroupCreateReqVO extends AiVideoTaskMaterialBaseVO {

    @Schema(description = "视频时长")
    private Integer videoDuration;

    @Schema(description = "视频数量")
    private Integer videoNum;

    @Schema(description = "视频原声")
    private Boolean original;

    @Schema(description = "内容标题")
    private List<AiMaterialClipVideoTaskContentTitleCreateReqVO> contentTitleList;

    @Schema(description = "口播文案")
    private List<AiMaterialClipVideoTaskCopywritingCreateReqVO> copywritingList;

    @Schema(description = "时长设置类型")
    @InEnum(value = MaterialVideoDurationType.class, message = "时长设置类型必须在{value}")
    private Integer durationType;

    @Schema(description = "时长")
    private Integer duration;

}
