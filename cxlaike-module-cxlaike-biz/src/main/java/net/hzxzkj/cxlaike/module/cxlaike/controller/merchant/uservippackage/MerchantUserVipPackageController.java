package net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.uservippackage;

import static net.hzxzkj.cxlaike.framework.common.pojo.CommonResult.success;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import javax.annotation.Resource;
import javax.validation.Valid;
import net.hzxzkj.cxlaike.framework.common.pojo.CommonResult;
import net.hzxzkj.cxlaike.framework.idempotent.core.annotation.Idempotent;
import net.hzxzkj.cxlaike.framework.security.core.annotations.PreAuthenticated;
import net.hzxzkj.cxlaike.framework.tenant.core.context.TenantContextHolder;
import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.uservippackage.vo.MerchantUserVipPackageActivateReqVO;
import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.uservippackage.vo.MerchantUserVipPackageActivateResultRespVO;
import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.uservippackage.vo.MerchantVipPackageResp;
import net.hzxzkj.cxlaike.module.cxlaike.service.uservippackage.MerchantUserVipPackageService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 描   述:
 *
 * @author ace
 * @version [版本号, 2023/10/31]
 * @see [相关类/方法]
 * 创建日期: 2023/10/31
 */

@Tag(name = "商家后台 - vip套餐")
@RestController
@RequestMapping("/cxlaike/vip-package")
@Validated
public class MerchantUserVipPackageController {

  @Resource
  private MerchantUserVipPackageService merchantUserVipPackageService;

  @PostMapping("/activate")
  @Operation(summary = "激活套餐")
  @Idempotent(message = "请勿重复提交")
  public CommonResult<Long> createTask(
      @Valid @RequestBody MerchantUserVipPackageActivateReqVO activateReqVO) {
    Long payOrderId = merchantUserVipPackageService.activate(activateReqVO);
    return success(payOrderId);
  }


  @GetMapping("/getActivateResult")
  @Operation(summary = "获得激活套餐支付结果")
  @PreAuthenticated
  public CommonResult<MerchantUserVipPackageActivateResultRespVO> getTaskOrderPaymentResult(
      @RequestParam(value = "payOrderId") Long payOrderId) {
    MerchantUserVipPackageActivateResultRespVO resp = merchantUserVipPackageService
        .getActivateResult(payOrderId,
            TenantContextHolder.getTenantId());
    return success(resp);
  }


  @GetMapping("/list")
  @Operation(summary = "获取套餐列表")
  @PreAuthenticated
  public CommonResult<List<MerchantVipPackageResp>> getSysList() {
    List<MerchantVipPackageResp> list = merchantUserVipPackageService.getSysList();
    return success(list);
  }


}
