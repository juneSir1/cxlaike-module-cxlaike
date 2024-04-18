package net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.material.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author jianan.han
 * @date 2023-08-19 22:01
 * @description
 */
@Data
public class AliStsCallbackReqVO {


    @Schema(description = "文件id",requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "文件id不能为空")
    private Long userId;

    @Schema(description = "文件路径", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "文件路径不能为空")
    private String path;

    @Schema(description = "文件名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "文件名称不能为空")
    private String name;

    @Schema(description = "素材类型 1-视频 2-音频 3-图片", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "素材类型不能为空")
    private Integer materialType;

    @Schema(description = "素材类型 1-视频 2-音频 3-图片", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "素材类型不能为空")
    private Integer fileLength;

    @Schema(description = "素材类型 1-视频 2-音频 3-图片", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "素材类型不能为空")
    private Integer fileHeight;

    @Schema(description = "素材类型 1-视频 2-音频 3-图片", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "素材类型不能为空")
    private Integer fileWidth;

    @Schema(description = "文件类型", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "文件类型不能为空")
    private String type;

    @Schema(description = "文件大小", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "文件大小不能为空")
    private Integer size;



}
