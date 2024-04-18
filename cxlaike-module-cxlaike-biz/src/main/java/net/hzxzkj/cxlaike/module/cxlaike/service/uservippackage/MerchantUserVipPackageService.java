package net.hzxzkj.cxlaike.module.cxlaike.service.uservippackage;

import java.util.List;

import net.hzxzkj.cxlaike.framework.mybatis.core.service.BaseCrudService;
import net.hzxzkj.cxlaike.module.cxlaike.api.merchantvip.dto.MerchantUserVipDTO;
import net.hzxzkj.cxlaike.module.cxlaike.api.merchantvip.dto.MerchantUserVipGoldAddDTO;
import net.hzxzkj.cxlaike.module.cxlaike.controller.app.teamearnings.vo.AgentPackageEarningsRespVO;
import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.uservippackage.vo.MerchantUserVipPackageActivateReqVO;
import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.uservippackage.vo.MerchantUserVipPackageActivateResultRespVO;
import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.uservippackage.vo.MerchantVipPackageResp;
import net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.uservippackage.UserVipPackageDO;

/**
 * 描   述:
 *
 * @author ace
 * @version [版本号, 2023/10/31]
 * @see [相关类/方法]
 * 创建日期: 2023/10/31
 */
public interface MerchantUserVipPackageService extends BaseCrudService<UserVipPackageDO> {

  Long activate(MerchantUserVipPackageActivateReqVO activateReqVO);

  MerchantUserVipPackageActivateResultRespVO getActivateResult(Long payOrderId, Long loginUserId);

  void vipInit(Long merchantId);

  /**
   * 更新商家vip套餐订单状态
   *
   * @param id
   * @param payOrderId
   */
  void updateMerchantUserOrderPaid(Long id, Long payOrderId);

  void merchantVipExpire(Long userVipPackageId);

  MerchantUserVipDTO getMerchantUserVip(Long userId);

  List<MerchantVipPackageResp> getSysList();

  AgentPackageEarningsRespVO getPackageEarningsDTOByUserId(Long loginUserId);

  void merchantVipGoldCoinAdd(MerchantUserVipGoldAddDTO merchantUserVipGoldAddDTO);

}
