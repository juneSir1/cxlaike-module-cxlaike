package net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.material.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Schema(description = "商家管理 - 上传文件 Request VO")
@Data
public class FileUploadTemporarilyReqVO {

    @Schema(description = "文件附件", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "文件附件不能为空")
    private MultipartFile file;

    @Schema(description = "素材类型 1-视频 2-音频 3-图片", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "素材类型不能为空")
    private Integer materialType;
}
