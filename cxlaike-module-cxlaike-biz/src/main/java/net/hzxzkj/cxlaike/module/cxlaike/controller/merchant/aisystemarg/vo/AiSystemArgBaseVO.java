package net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.aisystemarg.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import javax.validation.constraints.*;
import net.hzxzkj.cxlaike.framework.common.validation.InEnum;
import net.hzxzkj.cxlaike.module.cxlaike.enums.AiSystemArgType;

/**
 * 来客系统参数 Base VO，提供给添加、修改、详细的子 VO 使用
 * 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
 */
@Data
public class AiSystemArgBaseVO {

  @Schema(description = "系统参数类型", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
  @NotNull(message = "系统参数类型不能为空")
  @InEnum(value = AiSystemArgType.class, message = "系统参数类型必须在 {value}")
  private Integer sysType;

  @Schema(description = "具体类型", example = "2")
  private Integer type;

  @Schema(description = "名字", example = "王五")
  private String name;

  @Schema(description = "描述")
  private String depict;

  @Schema(description = "编号")
  private String code;

  @Schema(description = "图片")
  private String picture;

  @Schema(description = "url", example = "https://www.iocoder.cn")
  private String url;

}
