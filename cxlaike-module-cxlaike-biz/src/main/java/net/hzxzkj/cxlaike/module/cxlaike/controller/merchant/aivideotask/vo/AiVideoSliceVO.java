package net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.aivideotask.vo;

import lombok.Data;

/**
 * 描   述:
 *
 * @author ace
 * @version [版本号, 2023/8/25]
 * @see [相关类/方法]
 * 创建日期: 2023/8/25
 */
@Data
public class AiVideoSliceVO {

  /**
   * 素材url
   */
  private String url;

  /**
   * 切片开始时间
   */
  private Float in;

  /**
   * 切片结束时间
   */
  private Float out;

  /**
   * 切片时长
   */
  private Float duration;

}
