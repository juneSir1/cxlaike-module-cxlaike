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
public class UpdateFileVO {


    @Schema(description = "文件id",requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "文件id不能为空")
    private Long id;

    @Schema(description = "文件夹名称",requiredMode = Schema.RequiredMode.REQUIRED)
    private String name;

    @Schema(description = "路径")
    private String path;



}
