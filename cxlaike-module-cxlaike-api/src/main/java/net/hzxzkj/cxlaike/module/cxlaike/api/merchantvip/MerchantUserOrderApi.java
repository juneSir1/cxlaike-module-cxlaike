package net.hzxzkj.cxlaike.module.cxlaike.api.merchantvip;

/**
 * 描   述:
 *
 * @author ace
 * @version [版本号, 2023/10/31]
 * @see [相关类/方法]
 * 创建日期: 2023/10/31
 */
public interface MerchantUserOrderApi {

  /**
   * 更新商家vip套餐订单状态
   * @param id
   * @param payOrderId
   */
  void updateMerchantUserOrderPaid(Long id, Long payOrderId);

}
