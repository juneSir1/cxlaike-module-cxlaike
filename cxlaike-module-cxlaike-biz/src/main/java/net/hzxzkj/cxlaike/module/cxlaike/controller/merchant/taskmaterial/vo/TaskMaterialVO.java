package net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.taskmaterial.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.NotNull;
import lombok.Data;
import net.hzxzkj.cxlaike.framework.common.validation.InEnum;
import net.hzxzkj.cxlaike.module.cxlaike.enums.TaskFileType;

/**
 * 描   述:
 *
 * @author ace
 * @version [版本号, 2023/9/5]
 * @see [相关类/方法]
 * 创建日期: 2023/9/5
 */
@Data
public class TaskMaterialVO {

  @Schema(description = "视频组名称")
  @NotNull(message = "视频组名不能为空")
  private String folderName;

  @Schema(description = "素材路径")
  private String materialPath;

  @Schema(description = "文件id")
  @NotNull(message = "文件id不能为空")
  private Long fileId;

  @Schema(description = "序号")
  @NotNull(message = "序号不能为空")
  private Integer sort;
}
