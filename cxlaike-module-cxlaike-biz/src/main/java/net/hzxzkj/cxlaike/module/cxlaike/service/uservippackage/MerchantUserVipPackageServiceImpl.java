package net.hzxzkj.cxlaike.module.cxlaike.service.uservippackage;

import static cn.hutool.core.util.ObjectUtil.notEqual;
import static net.hzxzkj.cxlaike.framework.common.exception.util.ServiceExceptionUtil.exception;
import static net.hzxzkj.cxlaike.framework.common.util.json.JsonUtils.toJsonString;
import static net.hzxzkj.cxlaike.framework.common.util.servlet.ServletUtils.getClientIP;
import static net.hzxzkj.cxlaike.module.cxlaike.enums.ErrorCodeConstants.VIP_NOT_EXISTS;
import static net.hzxzkj.cxlaike.module.cxlaike.enums.ErrorCodeConstants.VIP_NOT_OPEN;
import static net.hzxzkj.cxlaike.module.cxlaike.enums.ErrorCodeConstants.VIP_ORDER_NOT_EXISTS;
import static net.hzxzkj.cxlaike.module.pay.enums.ErrorCodeConstants.ORDER_NOT_FOUND;
import static net.hzxzkj.cxlaike.module.pay.enums.ErrorCodeConstants.WALLET_ORDER_NOT_FOUND;
import static net.hzxzkj.cxlaike.module.pay.enums.ErrorCodeConstants.WALLET_ORDER_UPDATE_PAID_FAIL_PAY_ORDER_ID_ERROR;
import static net.hzxzkj.cxlaike.module.pay.enums.ErrorCodeConstants.WALLET_ORDER_UPDATE_PAID_FAIL_PAY_ORDER_STATUS_NOT_SUCCESS;
import static net.hzxzkj.cxlaike.module.pay.enums.ErrorCodeConstants.WALLET_ORDER_UPDATE_PAID_FAIL_PAY_PRICE_NOT_MATCH;
import static net.hzxzkj.cxlaike.module.pay.enums.ErrorCodeConstants.WALLET_ORDER_UPDATE_PAID_STATUS_NOT_UNPAID;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import net.hzxzkj.cxlaike.framework.common.enums.CommonStatusEnum;
import net.hzxzkj.cxlaike.framework.common.enums.UserTypeEnum;
import net.hzxzkj.cxlaike.framework.common.util.json.JsonUtils;
import net.hzxzkj.cxlaike.framework.mybatis.core.query.LambdaQueryWrapperX;
import net.hzxzkj.cxlaike.framework.mybatis.core.service.BaseCrudServiceImpl;
import net.hzxzkj.cxlaike.module.cxlaike.api.merchantvip.dto.MerchantUserVipDTO;
import net.hzxzkj.cxlaike.module.cxlaike.api.merchantvip.dto.MerchantUserVipGoldAddDTO;
import net.hzxzkj.cxlaike.module.cxlaike.controller.app.teamearnings.vo.AgentPackageEarningsRespVO;
import net.hzxzkj.cxlaike.module.cxlaike.controller.app.teamearnings.vo.AgentPackageEarningsRespVO.PackageEarningsInfoRespVO;
import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.uservippackage.vo.MerchantUserVipPackageActivateReqVO;
import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.uservippackage.vo.MerchantUserVipPackageActivateResultRespVO;
import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.uservippackage.vo.MerchantVipPackageResp;
import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.uservippackage.vo.MerchantVipPackageResp.MerchantPackageDetailResp;
import net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.packagedetails.PackageDetailsDO;
import net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.packageorder.PackageOrderDO;
import net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.packages.PackageDO;
import net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.uservippackage.UserVipPackageDO;
import net.hzxzkj.cxlaike.module.cxlaike.dal.mysql.packagedetails.PackageDetailsMapper;
import net.hzxzkj.cxlaike.module.cxlaike.dal.mysql.packageorder.PackageOrderMapper;
import net.hzxzkj.cxlaike.module.cxlaike.dal.mysql.packages.PackageMapper;
import net.hzxzkj.cxlaike.module.cxlaike.dal.mysql.uservippackage.UserVipPackageMapper;
import net.hzxzkj.cxlaike.module.cxlaike.enums.merchantvip.MerchantVipOrderStatusEnum;
import net.hzxzkj.cxlaike.module.cxlaike.enums.merchantvip.PackageDetailType;
import net.hzxzkj.cxlaike.module.member.api.user.MemberUserApi;
import net.hzxzkj.cxlaike.module.notify.client.TaskClient;
import net.hzxzkj.cxlaike.module.notify.enums.TaskHandlerEnum;
import net.hzxzkj.cxlaike.module.pay.api.order.PayOrderApi;
import net.hzxzkj.cxlaike.module.pay.api.order.dto.PayOrderCreateReqDTO;
import net.hzxzkj.cxlaike.module.pay.api.order.dto.PayOrderRespDTO;
import net.hzxzkj.cxlaike.module.pay.api.wallet.WalletApi;
import net.hzxzkj.cxlaike.module.pay.enums.app.AppEnum;
import net.hzxzkj.cxlaike.module.pay.enums.order.PayOrderStatusEnum;
import net.hzxzkj.cxlaike.module.pay.enums.wallet.WalletLogTypeEnum;
import net.hzxzkj.cxlaike.module.pay.enums.wallet.WalletTypeEnum;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.compress.utils.Lists;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 描   述:
 *
 * @author ace
 * @version [版本号, 2023/10/31]
 * @see [相关类/方法]
 * 创建日期: 2023/10/31
 */
@Slf4j
@Service
public class MerchantUserVipPackageServiceImpl extends
    BaseCrudServiceImpl<UserVipPackageMapper, UserVipPackageDO> implements
    MerchantUserVipPackageService {

  @Resource
  private PackageMapper packageMapper;
  @Resource
  private PackageDetailsMapper packageDetailsMapper;
  @Resource
  private PackageOrderMapper packageOrderMapper;
  @Resource
  private PayOrderApi payOrderApi;
  @Resource
  private UserVipPackageMapper userVipPackageMapper;
  @Value("${merchant.vip.trial-package-id}")
  private Long trialPackageId;
  @Resource
  private TaskClient taskClient;
  @Resource
  private WalletApi walletApi;
  @Resource
  private MemberUserApi memberUserApi;

  @Override
  @Transactional(rollbackFor = Exception.class)
  public Long activate(MerchantUserVipPackageActivateReqVO activateReqVO) {
    Long merchantPackageId = activateReqVO.getMerchantPackageId();
    // 1. 根据 merchantPackageId 查询套餐信息
    if (trialPackageId.equals(merchantPackageId)) {
      // 试用套餐
      throw exception(VIP_NOT_OPEN);
    }
    PackageDO packageDO = packageMapper.selectById(merchantPackageId);
    if (packageDO == null) {
      throw exception(VIP_NOT_EXISTS);
    }

    if (!CommonStatusEnum.ENABLE.getStatus().equals(packageDO.getStatus())) {
      throw exception(VIP_NOT_EXISTS);
    }
    //支付失效时间
    LocalDateTime invalidTime = LocalDateTime.now().plusMinutes(3);
    //创建一个支付订单
    PackageOrderDO packageOrderDO = new PackageOrderDO();
    packageOrderDO.setInvalidTime(invalidTime);
    packageOrderDO.setPrice(packageDO.getPrice());
    packageOrderDO.setPayStatus(MerchantVipOrderStatusEnum.RUN.getType());
    packageOrderDO.setMerchantPackageId(merchantPackageId);
    packageOrderMapper.insert(packageOrderDO);
    // 2.1 创建支付单
    Long payOrderId = payOrderApi.createOrder(new PayOrderCreateReqDTO()
        .setAppId(AppEnum.MERCHANT_VIP.getType()).setUserIp(getClientIP()) // 支付应用
        .setSubject("商家会员套餐")
        .setMerchantOrderId(packageOrderDO.getId().toString()) // 业务的订单编号
        .setBody("").setPrice(packageOrderDO.getPrice().intValue()) // 价格信息
        .setExpireTime(invalidTime)); // 支付的过期时间

    packageOrderMapper
        .updateById(new PackageOrderDO().setId(packageOrderDO.getId()).setPayOrderId(payOrderId));

    return payOrderId;
  }


  @Override
  public MerchantUserVipPackageActivateResultRespVO getActivateResult(Long payOrderId,
      Long loginUserId) {

    MerchantUserVipPackageActivateResultRespVO respVO = new MerchantUserVipPackageActivateResultRespVO();
    respVO.setPayOrderId(payOrderId);

    // 1. 根据 payOrderId 查询支付订单信息

    PackageOrderDO packageOrderDO = packageOrderMapper.selectOne(
        new LambdaQueryWrapperX<PackageOrderDO>().eq(PackageOrderDO::getPayOrderId, payOrderId));

    if (packageOrderDO == null) {
      throw exception(VIP_ORDER_NOT_EXISTS);
    }
    Integer payStatus = packageOrderDO.getPayStatus();
    respVO.setStatus(payStatus);
    return respVO;
  }

  @Override
  @Transactional(rollbackFor = Exception.class)
  public void vipInit(Long merchantId) {
    //查看当前用户是否已经开通会员
    List<UserVipPackageDO> userVipPackageList = userVipPackageMapper.selectList(
        new LambdaQueryWrapperX<UserVipPackageDO>().eq(UserVipPackageDO::getTenantId, merchantId)
            .eq(UserVipPackageDO::getStatus, CommonStatusEnum.ENABLE.getStatus()));
    if (CollectionUtils.isNotEmpty(userVipPackageList)) {
      log.info("[vipInit][merchantId({}) 已经存在会员]", merchantId);
      return;
    }
    this.openMerchantVip(merchantId, trialPackageId);
  }

  @Override
  @Transactional(rollbackFor = Exception.class)
  public void updateMerchantUserOrderPaid(Long id, Long payOrderId) {
    // 1.1 校验订单是否存在
    PackageOrderDO order = packageOrderMapper.selectById(id);

    PayOrderRespDTO payOrder = validateMerchantVipOrderCanPaid(order, id, payOrderId);
    if (PayOrderStatusEnum.isClosed(payOrder.getStatus())) {
      this.isClosed(id, payOrder);
    } else if (PayOrderStatusEnum.isSuccess(payOrder.getStatus())) {
      this.isSuccess(id, payOrder, order);
    } else {
      log.error(
          "[validateMerchantVipOrderCanPaid][order({}) payOrder({}) 未支付，请进行处理！payOrder 数据是：{}]",
          id, payOrderId, toJsonString(payOrder));
      throw exception(WALLET_ORDER_UPDATE_PAID_FAIL_PAY_ORDER_STATUS_NOT_SUCCESS);
    }

  }

  @Override
  @Transactional(rollbackFor = Exception.class)
  public void merchantVipExpire(Long userVipPackageId) {
    UserVipPackageDO userVipPackageDO = userVipPackageMapper.selectById(userVipPackageId);

    if (userVipPackageDO == null || !CommonStatusEnum.ENABLE.getStatus()
        .equals(userVipPackageDO.getStatus())) {
      log.error("当前会员不存在,或当前状态不为开启状态!");
      throw exception(VIP_NOT_EXISTS);
    }

    userVipPackageMapper
        .updateById(new UserVipPackageDO().setId(userVipPackageId).setIsMaster(Boolean.FALSE)
            .setStatus(CommonStatusEnum.DISABLE.getStatus()));

    Long tenantId = userVipPackageDO.getTenantId();
    //查询当前商户是否有其他会员
    List<UserVipPackageDO> userVipPackageList = userVipPackageMapper
        .selectList(new LambdaQueryWrapperX<UserVipPackageDO>()
            .eq(UserVipPackageDO::getTenantId, tenantId)
            .eq(UserVipPackageDO::getStatus, CommonStatusEnum.ENABLE.getStatus()).orderByDesc(
                UserVipPackageDO::getCreateTime));

    if (CollectionUtils.isEmpty(userVipPackageList)) {
      log.info("当前商家没有其他会员,结束流程!商家Id:{}", tenantId);
      return;
    }

    for (UserVipPackageDO newUserVipPackage : userVipPackageList) {
      //当前的套餐查询
      PackageDO packageDO = packageMapper.selectById(newUserVipPackage.getMerchantPackageId());
      if (packageDO == null || CommonStatusEnum.DISABLE.getStatus().equals(packageDO.getStatus())) {
        log.info("当前套餐不存在!或当前套餐关闭状态,套餐id:{}", newUserVipPackage.getMerchantPackageId());
        //关闭当前套餐
        userVipPackageMapper
            .updateById(new UserVipPackageDO().setId(newUserVipPackage.getId())
                .setStatus(CommonStatusEnum.DISABLE.getStatus()).setIsMaster(Boolean.FALSE));
        continue;
      }
      //套餐开始时间
      LocalDateTime startTime = LocalDateTime.now();
      //套餐结束时间
      LocalDateTime endTime = startTime.plusDays(packageDO.getDays());
      userVipPackageMapper
          .updateById(
              new UserVipPackageDO().setId(newUserVipPackage.getId()).setStartTime(startTime)
                  .setEndTime(endTime).setIsMaster(Boolean.TRUE));
      //赠送套餐金币
      goldCoin(tenantId, newUserVipPackage.getMerchantPackageId(), newUserVipPackage.getId());
      //当前会员失效任务
      taskClient
          .createTask(TaskHandlerEnum.MERCHANT_VIP_EXPIRE.getValue(),
              endTime, userVipPackageDO.getId().toString());
      return;
    }

  }

  @Override
  public MerchantUserVipDTO getMerchantUserVip(Long userId) {

    UserVipPackageDO userVipPackageDO = userVipPackageMapper
        .selectOne(new LambdaQueryWrapperX<UserVipPackageDO>().eq(
            UserVipPackageDO::getTenantId, userId).eq(UserVipPackageDO::getIsMaster, Boolean.TRUE)
            .eq(UserVipPackageDO::getStatus, CommonStatusEnum.ENABLE.getStatus()));

    MerchantUserVipDTO merchantUserVipDTO = new MerchantUserVipDTO().setUserId(userId)
        .setIsVip(userVipPackageDO != null);

    if (userVipPackageDO != null) {
      merchantUserVipDTO.setMerchantPackageId(userVipPackageDO.getMerchantPackageId());
      merchantUserVipDTO.setExpireTime(userVipPackageDO.getEndTime());
      PackageDO packageDO = packageMapper.selectById(userVipPackageDO.getMerchantPackageId());
      if (packageDO != null) {
        merchantUserVipDTO.setMerchantPackageName(packageDO.getName());
      }
    }

    return merchantUserVipDTO;
  }

  @Override
  @Transactional(readOnly = true)
  public List<MerchantVipPackageResp> getSysList() {
    List<MerchantVipPackageResp> list = new ArrayList<>();
    List<PackageDO> packageDOList = packageMapper
        .selectList(new LambdaQueryWrapperX<PackageDO>()
            .eq(PackageDO::getStatus, CommonStatusEnum.ENABLE.getStatus()).orderByAsc(
                PackageDO::getSort));
    if (CollectionUtils.isEmpty(packageDOList)) {
      return list;
    }
    packageDOList.forEach(item -> {
      MerchantVipPackageResp resp = new MerchantVipPackageResp();
      BeanUtils.copyProperties(item, resp);
      resp.setIsTrial(trialPackageId.equals(item.getId()));

      List<MerchantPackageDetailResp> detailList = Lists.newArrayList();
      List<PackageDetailsDO> packageDetails = packageDetailsMapper
          .selectList(new LambdaQueryWrapperX<PackageDetailsDO>()
              .eq(PackageDetailsDO::getMerchantPackageId, item.getId()).orderByAsc(
                  PackageDetailsDO::getSort));
      if (CollectionUtils.isNotEmpty(packageDetails)) {
        packageDetails.forEach(detail -> {
          MerchantPackageDetailResp detailResp = new MerchantPackageDetailResp();
          BeanUtils.copyProperties(detail, detailResp);
          detailList.add(detailResp);
        });
      }
      resp.setDetailList(detailList);
      list.add(resp);
    });

    return list;
  }

  private void isSuccess(Long id, PayOrderRespDTO payOrder, PackageOrderDO order) {
    //变更支付订单为成功
    int updateCount = packageOrderMapper
        .update(new PackageOrderDO().setPayStatus(MerchantVipOrderStatusEnum.FINISH.getType())
                .setPayTime(LocalDateTime.now()).setPayChannelCode(payOrder.getChannelCode()),
            new LambdaQueryWrapperX<PackageOrderDO>().eq(PackageOrderDO::getId, id)
                .eq(PackageOrderDO::getPayStatus, MerchantVipOrderStatusEnum.RUN.getType()));
    if (updateCount == 0) {
      throw exception(WALLET_ORDER_UPDATE_PAID_STATUS_NOT_UNPAID);
    }
    //用户添加会员套餐
    this.openMerchantVip(order.getTenantId(), order.getMerchantPackageId());
  }

  private void openMerchantVip(Long merchantId, Long merchantPackageId) {

    //查看当前用户是否已经开通会员
    List<UserVipPackageDO> userVipPackageList = userVipPackageMapper.selectList(
        new LambdaQueryWrapperX<UserVipPackageDO>().eq(UserVipPackageDO::getTenantId, merchantId)
            .ne(UserVipPackageDO::getMerchantPackageId, trialPackageId)
            .eq(UserVipPackageDO::getStatus, CommonStatusEnum.ENABLE.getStatus()));

    //查询套餐信息
    PackageDO packageDO = packageMapper.selectById(merchantPackageId);
    if (packageDO == null) {
      throw exception(VIP_NOT_EXISTS);
    }
    //判断当前是否主套餐
    Boolean isMaster;
    if (CollectionUtils.isEmpty(userVipPackageList)) {
      isMaster = Boolean.TRUE;
      //变更试用套餐
      userVipPackageMapper
          .update(new UserVipPackageDO().setStatus(CommonStatusEnum.DISABLE.getStatus())
                  .setIsMaster(Boolean.FALSE),
              new LambdaQueryWrapperX<UserVipPackageDO>()
                  .eq(UserVipPackageDO::getTenantId, merchantId)
                  .eq(UserVipPackageDO::getIsMaster, Boolean.TRUE)
                  .eq(UserVipPackageDO::getStatus, CommonStatusEnum.ENABLE.getStatus())
                  .eq(UserVipPackageDO::getMerchantPackageId, trialPackageId));

    } else {
      isMaster = Boolean.FALSE;
    }

    //添加会员套餐
    LocalDateTime startTime = LocalDateTime.now();
    LocalDateTime endTime = startTime.plusDays(packageDO.getDays());
    UserVipPackageDO userVipPackageDO = new UserVipPackageDO();
    userVipPackageDO.setTenantId(merchantId);
    userVipPackageDO.setMerchantPackageId(merchantPackageId);
    userVipPackageDO.setIsMaster(isMaster);
    userVipPackageDO.setStartTime(startTime);
    userVipPackageDO.setEndTime(endTime);
    userVipPackageDO.setStatus(CommonStatusEnum.ENABLE.getStatus());
    userVipPackageMapper.insert(userVipPackageDO);

    if (isMaster) {
      //如果是主套餐,需要发放金币
      goldCoin(merchantId, merchantPackageId, userVipPackageDO.getId());
      //当前会员失效任务
      taskClient.createTask(TaskHandlerEnum.MERCHANT_VIP_EXPIRE.getValue(), endTime,
          userVipPackageDO.getId().toString());
    }
  }

  private void goldCoin(Long merchantId, Long merchantPackageId,
      Long userVipPackageId) {
    //查询套餐详情
    List<PackageDetailsDO> packageDetailsDOList = packageDetailsMapper
        .selectList(new LambdaQueryWrapperX<PackageDetailsDO>()
            .eq(PackageDetailsDO::getMerchantPackageId, merchantPackageId));

    if (CollectionUtils.isNotEmpty(packageDetailsDOList)) {
      packageDetailsDOList.stream().filter(detail -> {
        return detail.getRuleType().equals(PackageDetailType.GOLD_COIN.getType());
      }).findFirst().ifPresent(packageDetailsDO ->
          taskClient.createTask(TaskHandlerEnum.MERCHANT_USER_VIP_GOLD_ADD.getValue(), null,
              JsonUtils.toJsonString(new MerchantUserVipGoldAddDTO().setMerchantId(merchantId)
                  .setNumber(packageDetailsDO.getValue().longValue())
                  .setUserVipPackageId(userVipPackageId))));
    } else {
      log.info("当前套餐没有赠送金币,套餐Id:{}", merchantPackageId);
    }
  }

  private void isClosed(Long id, PayOrderRespDTO payOrder) {
    //关闭支付订单
    int updateCount = packageOrderMapper
        .update(new PackageOrderDO().setPayStatus(MerchantVipOrderStatusEnum.FAIL.getType())
                .setPayTime(LocalDateTime.now()).setPayChannelCode(payOrder.getChannelCode()),
            new LambdaQueryWrapperX<PackageOrderDO>().eq(PackageOrderDO::getId, id)
                .eq(PackageOrderDO::getPayStatus, MerchantVipOrderStatusEnum.RUN.getType()));
    if (updateCount == 0) {
      throw exception(WALLET_ORDER_UPDATE_PAID_STATUS_NOT_UNPAID);
    }
  }

  @Override
  @Transactional(rollbackFor = Exception.class)
  public AgentPackageEarningsRespVO getPackageEarningsDTOByUserId(Long loginUserId) {
    AgentPackageEarningsRespVO agentPackageEarningsRespVO = new AgentPackageEarningsRespVO();
    //查询当前代理商下所有的一级商家
    List<Long> userIds = memberUserApi.getListByParentUserId(loginUserId, UserTypeEnum.MERCHANT);
    AtomicReference<Long> totalAmount = new AtomicReference<>(0L);
    if (CollectionUtils.isEmpty(userIds)) {
      agentPackageEarningsRespVO.setMerchantNum(0);
      agentPackageEarningsRespVO.setTotalAmount(totalAmount.get());
      return agentPackageEarningsRespVO;
    }

    //查询当前代理商下所有的商家套餐
    List<PackageOrderDO> packageOrderDOList = packageOrderMapper
        .selectList(new LambdaQueryWrapperX<PackageOrderDO>()
            .in(PackageOrderDO::getTenantId, userIds)
            .eq(PackageOrderDO::getPayStatus, MerchantVipOrderStatusEnum.FINISH.getType()));

    //合并商家套餐详情
    Map<Long, List<PackageOrderDO>> packageOrderMap = packageOrderDOList.stream()
        .collect(Collectors.groupingBy(PackageOrderDO::getMerchantPackageId));

    List<PackageEarningsInfoRespVO> packageEarningsInfoVOList = Lists.newArrayList();

    packageOrderMap.forEach((packageId, v) -> {
      //判断当前套餐是否是试用套餐
      if (!trialPackageId.equals(packageId)) {
        //查询当前套餐的详情
        PackageDO packageDO = packageMapper.selectById(packageId);
        if (packageDO != null) {
          PackageEarningsInfoRespVO packageEarningsInfoRespVO = new PackageEarningsInfoRespVO();
          packageEarningsInfoRespVO.setPackageId(packageId);
          //设置套餐名称
          packageEarningsInfoRespVO.setPackageName(packageDO.getName());
          //设置套餐金额
          packageEarningsInfoRespVO.setPackageAmount(packageDO.getPrice());
          //设置套餐数量
          packageEarningsInfoRespVO.setPackageNum(v.size());
          totalAmount.set(totalAmount.get() + packageDO.getPrice() * v.size());
          packageEarningsInfoVOList.add(packageEarningsInfoRespVO);
        }
      }
    });
    agentPackageEarningsRespVO.setMerchantNum(userIds.size());
    agentPackageEarningsRespVO.setTotalAmount(totalAmount.get());
    agentPackageEarningsRespVO.setPackageList(packageEarningsInfoVOList);

    return agentPackageEarningsRespVO;
  }

  @Override
  @Transactional(rollbackFor = Exception.class)
  public void merchantVipGoldCoinAdd(MerchantUserVipGoldAddDTO merchantUserVipGoldAddDTO) {
    walletApi
        .transfer(merchantUserVipGoldAddDTO.getMerchantId(), merchantUserVipGoldAddDTO.getNumber(),
            null
            , WalletTypeEnum.GOLD, WalletLogTypeEnum.MERCHANT_VIP_GOLD,
            merchantUserVipGoldAddDTO.getUserVipPackageId(),
            merchantUserVipGoldAddDTO.getUserVipPackageId().toString(),
            WalletLogTypeEnum.MERCHANT_VIP_GOLD.getName());
  }


  /**
   * 校验交易订单满足被支付的条件
   * <p>
   * 1. 交易订单未支付
   * 2. 支付单已支付
   *
   * @param id         交易订单编号
   * @param payOrderId 支付订单编号
   * @return 交易订单
   */
  private PayOrderRespDTO validateMerchantVipOrderCanPaid(PackageOrderDO order, Long id,
      Long payOrderId) {

    if (order == null) {
      throw exception(WALLET_ORDER_NOT_FOUND);
    }
    // 1.2 校验订单未支付
    if (!MerchantVipOrderStatusEnum.RUN.getType().equals(order.getPayStatus())) {
      log.error("[validateMerchantVipOrderPaid][order({}) 不处于待支付状态，请进行处理！order 数据是：{}]",
          id, toJsonString(order));
      throw exception(WALLET_ORDER_UPDATE_PAID_STATUS_NOT_UNPAID);
    }
    // 1.3 校验支付订单匹配
    if (notEqual(order.getPayOrderId(), payOrderId)) { // 支付单号
      log.error("[validateMerchantVipOrderCanPaid][order({}) 支付单不匹配({})，请进行处理！order 数据是：{}]",
          id, payOrderId, toJsonString(order));
      throw exception(WALLET_ORDER_UPDATE_PAID_FAIL_PAY_ORDER_ID_ERROR);
    }

    // 2.1 校验支付单是否存在
    PayOrderRespDTO payOrder = payOrderApi.getOrder(payOrderId);
    if (payOrder == null) {
      log.error("[validateMerchantVipOrderCanPaid][order({}) payOrder({}) 不存在，请进行处理！]", id,
          payOrderId);
      throw exception(ORDER_NOT_FOUND);
    }

    // 2.3 校验支付金额一致
    if (notEqual(payOrder.getPrice(), order.getPrice().intValue())) {
      log.error(
          "[validateMerchantVipOrderCanPaid][order({}) payOrder({}) 支付金额不匹配，请进行处理！order 数据是：{}，payOrder 数据是：{}]",
          id, payOrderId, toJsonString(order), toJsonString(payOrder));
      throw exception(WALLET_ORDER_UPDATE_PAID_FAIL_PAY_PRICE_NOT_MATCH);
    }
    // 2.4 校验支付订单匹配（二次）
    if (notEqual(payOrder.getMerchantOrderId(), id.toString())) {
      log.error("[validateMerchantVipOrderCanPaid][order({}) 支付单不匹配({})，请进行处理！payOrder 数据是：{}]",
          id, payOrderId, toJsonString(payOrder));
      throw exception(WALLET_ORDER_UPDATE_PAID_FAIL_PAY_ORDER_ID_ERROR);
    }
    return payOrder;
  }
}
