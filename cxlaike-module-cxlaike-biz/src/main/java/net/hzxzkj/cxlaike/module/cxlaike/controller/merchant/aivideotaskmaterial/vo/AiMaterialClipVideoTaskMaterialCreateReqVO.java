package net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.aivideotaskmaterial.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import net.hzxzkj.cxlaike.framework.common.validation.InEnum;
import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.aivideotask.vo.AiMaterialClipVideoTaskContentTitleCreateReqVO;
import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.aivideotask.vo.AiMaterialClipVideoTaskCopywritingCreateReqVO;
import net.hzxzkj.cxlaike.module.cxlaike.enums.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Set;

@Schema(description = "商户后台 - ai素材混剪视频任务素材创建 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class AiMaterialClipVideoTaskMaterialCreateReqVO extends AiVideoTaskMaterialBaseVO {

//    @Schema(description = "初始素材url")
//    private String sourceUrl;

    @Schema(description = "时长")
    private Integer duration;

    @Schema(description = "文件地址")
    private String url;
}
