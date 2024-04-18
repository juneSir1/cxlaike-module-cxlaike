package net.hzxzkj.cxlaike.module.cxlaike.service.video.dto;

import lombok.Data;

/**
 * 描   述:
 *
 * @author ace
 * @version [版本号, 2023/10/12]
 * @see [相关类/方法]
 * 创建日期: 2023/10/12
 */
@Data
public class VideoClipOrderDTO {

  private Long id;

  /**
   * jobId
   */
  private String jobId;

  /**
   * 访问地址
   */
  private String mediaUrl;
}
