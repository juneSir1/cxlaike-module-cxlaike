package net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.aivideotask.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 描   述:
 *
 * @author tkk
 * @version [版本号, 2023/12/26]
 * @see [相关类/方法]
 * 创建日期: 2023/12/26
 */
@Data
public class CoverRespVO {


  @Schema(description = "视频封面图片Id", example = "https://www.iocoder.cn")
  private Long coverId;

  @Schema(description = "视频封面图片Url", example = "https://www.iocoder.cn")
  private String coverUrl;

}
