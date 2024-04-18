package net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.aivideotaskmaterial.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import net.hzxzkj.cxlaike.framework.common.validation.InEnum;
import net.hzxzkj.cxlaike.module.cxlaike.enums.*;

import javax.validation.constraints.NotNull;
import java.util.List;

@Schema(description = "商户后台 - ai素材混剪视频任务素材创建 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class AiMaterialClipVideoTaskMaterialCopywritingCreateReqVO extends AiVideoTaskMaterialBaseVO {


    @Schema(description = "字幕文案")
    private String copyingwriting;

    @Schema(description = "原始文件")
    private String originalFile;

    @Schema(description = "提取的音频文件")
    private String audioFile;
}
