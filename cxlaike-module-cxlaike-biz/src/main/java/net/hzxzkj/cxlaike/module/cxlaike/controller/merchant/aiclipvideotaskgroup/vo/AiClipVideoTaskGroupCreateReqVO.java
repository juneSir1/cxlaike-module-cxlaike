package net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.aiclipvideotaskgroup.vo;

import lombok.*;
import io.swagger.v3.oas.annotations.media.Schema;
import net.hzxzkj.cxlaike.framework.common.validation.InEnum;
import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.aivideotask.vo.AiMaterialClipVideoTaskContentTitleCreateReqVO;
import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.aivideotask.vo.AiMaterialClipVideoTaskCopywritingCreateReqVO;
import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.aivideotaskmaterial.vo.AiMaterialClipVideoTaskMaterialCreateReqVO;
import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.aivideotaskmaterial.vo.AiVideoTaskMaterialBaseVO;
import net.hzxzkj.cxlaike.module.cxlaike.enums.CopywritingSourceType;

import java.util.List;

@Schema(description = "管理后台 - ai素材混剪视频任务-视频组设置创建 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class AiClipVideoTaskGroupCreateReqVO extends AiClipVideoTaskGroupBaseVO {


    @Schema(description = "内容标题")
    private AiMaterialClipVideoTaskContentTitleCreateReqVO contentTitleVO;

    @Schema(description = "字幕类型")
    @InEnum(value = CopywritingSourceType.class, message = "字幕来源类型必须在{value}")
    private Integer copywritingSourceType;

    @Schema(description = "口播文案")
    private AiMaterialClipVideoTaskCopywritingCreateReqVO copywritingVO;

    @Schema(description = "素材列表")
    private List<AiMaterialClipVideoTaskMaterialCreateReqVO> materialList;

}
