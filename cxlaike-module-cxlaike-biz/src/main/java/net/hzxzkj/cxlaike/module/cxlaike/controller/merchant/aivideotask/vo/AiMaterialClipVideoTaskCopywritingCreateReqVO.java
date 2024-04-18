package net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.aivideotask.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import net.hzxzkj.cxlaike.framework.common.validation.InEnum;
import net.hzxzkj.cxlaike.module.cxlaike.enums.CopywritingSourceType;

import java.util.List;

/**
 * 描   述:
 * 创建ai素材混剪视频任务字幕文案设置请求类
 * @author tkk
 * @version [版本号, 2023/12/12]
 * @see [相关类/方法]
 * 创建日期: 2023/12/12
 */
@Data
public class AiMaterialClipVideoTaskCopywritingCreateReqVO extends BaseTextReqVO{

  @Schema(description = "字幕文案内容")
  private List<AiMaterialClipVideoTaskCopyingwritingContentVO> copywritingList;


  @Schema(description = "视频合成时取的下标", hidden = true)
  private Integer copywritingIndex = 0;


}
