package net.hzxzkj.cxlaike.module.cxlaike.service.video.dto;

import java.io.Serializable;
import lombok.Data;

/**
 * 描   述:
 *
 * @author ace
 * @version [版本号, 2023/10/8]
 * @see [相关类/方法]
 * 创建日期: 2023/10/8
 */
@Data
public abstract class VideoClipBase implements Serializable {

  private Long materialId;

  private Long userId;

}
