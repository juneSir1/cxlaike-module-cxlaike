package net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.aivideotask.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

/**
 * 描   述:
 * 创建ai素材混剪视频任务文字设置请求类
 * @author tkk
 * @version [版本号, 2023/12/12]
 * @see [相关类/方法]
 * 创建日期: 2023/12/12
 */
@Data
public class AiMaterialClipVideoTaskContentTitleCreateReqVO extends BaseTextReqVO{

  @Schema(description = "文字标题")
  private List<String> contentTitleList;

  @Schema(description = "合成视频时取的下标", hidden = true)
  private Integer contentTitleIndex = 0;


}
