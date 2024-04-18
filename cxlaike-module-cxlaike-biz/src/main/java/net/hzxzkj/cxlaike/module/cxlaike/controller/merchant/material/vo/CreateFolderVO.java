package net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.material.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * @author jianan.han
 * @date 2023-08-19 22:01
 * @description
 */
@Data
public class CreateFolderVO {

    @Schema(description = "文件夹名称",requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "请输入文件夹名称")
    @Pattern(regexp = "^[A-Za-z0-9_\\-+\\s.\\p{IsHan}~`!@#$%^&*()\\-+=|\\\\{}\\[\\]:;\"'<>,.?/]+$", message = "请输入中文、字母、数字")
    private String name;

    @Schema(description = "路径")
    private String path;


    @Schema(description = "素材类型 1-视频 2-音频 3-图片",requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "素材类型不能为空")
    private Integer materialType;

    @Schema(description = "用户id",hidden = true)
    private Long userId;


}
