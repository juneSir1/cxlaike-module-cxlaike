package net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.aivideotask.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 描   述:
 * 字体设置
 * @author tkk
 * @version [版本号, 2023/12/14]
 * @see [相关类/方法]
 * 创建日期: 2023/12/14
 */
@Data
public class FontFaceVO {

  /**
   * 加粗
   */
  @Schema(description = "加粗")
  private Boolean Bold;

  /**
   * 斜体
   */
  @Schema(description = "斜体")
  private Boolean Italic;

  /**
   * 下划线
   */
  @Schema(description = "下划线")
  private Boolean Underline;

}
