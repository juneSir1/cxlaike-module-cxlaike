package net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.uservippackage.vo;

import java.util.List;
import lombok.Data;

/**
 * 描   述:
 *
 * @author ace
 * @version [版本号, 2023/11/1]
 * @see [相关类/方法]
 * 创建日期: 2023/11/1
 */
@Data
public class MerchantVipPackageResp {

  private Long id;

  private String name;

  private Long price;

  private Long days;

  private Integer sort;

  private Boolean isTrial;

  private List<MerchantPackageDetailResp> detailList;


  @Data
  public static class MerchantPackageDetailResp {

    private String name;
    private Integer ruleType;
    private Integer value;
    private Integer sort;
  }

}
