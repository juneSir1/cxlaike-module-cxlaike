package net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.taskext.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 描   述:
 *
 * @author ace
 * @version [版本号, 2023/10/9]
 * @see [相关类/方法]
 * 创建日期: 2023/10/9
 */
@Data
public class MerchantTaskExtRespVO extends TaskExtBaseVO{

  @Schema(description = "总数量", requiredMode = Schema.RequiredMode.REQUIRED)
  private Integer totalNum;
}
