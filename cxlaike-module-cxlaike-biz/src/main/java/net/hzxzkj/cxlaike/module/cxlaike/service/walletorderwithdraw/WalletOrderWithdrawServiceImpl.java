package net.hzxzkj.cxlaike.module.cxlaike.service.walletorderwithdraw;

import static net.hzxzkj.cxlaike.framework.common.exception.util.ServiceExceptionUtil.exception;
import static net.hzxzkj.cxlaike.module.cxlaike.enums.ErrorCodeConstants.DATA_ERROR;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import java.time.LocalDateTime;
import java.util.*;
import javax.annotation.Resource;

import cn.hutool.json.JSON;
import com.github.binarywang.wxpay.bean.transfer.TransferBatchesRequest;
import com.github.binarywang.wxpay.bean.transfer.TransferBatchesResult;
import com.github.binarywang.wxpay.config.WxPayConfig;
import com.github.binarywang.wxpay.constant.WxPayConstants;
import com.github.binarywang.wxpay.exception.WxPayException;
import com.github.binarywang.wxpay.service.TransferService;
import com.github.binarywang.wxpay.service.WxPayService;
import com.github.binarywang.wxpay.service.impl.TransferServiceImpl;
import com.github.binarywang.wxpay.service.impl.WxPayServiceImpl;
import lombok.extern.slf4j.Slf4j;
import net.hzxzkj.cxlaike.framework.common.enums.UserTypeEnum;
import net.hzxzkj.cxlaike.framework.common.exception.ErrorCode;
import net.hzxzkj.cxlaike.framework.common.pojo.PageResult;
import net.hzxzkj.cxlaike.framework.common.util.json.JsonUtils;
import net.hzxzkj.cxlaike.framework.mybatis.core.query.LambdaQueryWrapperX;
import net.hzxzkj.cxlaike.framework.security.core.util.SecurityFrameworkUtils;
import net.hzxzkj.cxlaike.module.cxlaike.controller.admin.walletorderwithdraw.vo.TransferDetailReqVO;
import net.hzxzkj.cxlaike.module.cxlaike.controller.admin.walletorderwithdraw.vo.WalletOrderWithdrawAuditReqVO;
import net.hzxzkj.cxlaike.module.cxlaike.controller.admin.walletorderwithdraw.vo.WalletOrderWithdrawPageReqVO;
import net.hzxzkj.cxlaike.module.cxlaike.controller.app.walletorderwithdraw.vo.AppWalletOrderWithdrawCreateReqVO;
import net.hzxzkj.cxlaike.module.cxlaike.controller.app.walletorderwithdraw.vo.AppWalletOrderWithdrawPageReqVO;
import net.hzxzkj.cxlaike.module.cxlaike.convert.walletorderwithdraw.WalletOrderWithdrawConvert;
import net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.transferaccounts.TransferAccountsDO;
import net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.walletorderwithdraw.WalletOrderWithdrawDO;
import net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.walletorderwithdrawrecord.WalletOrderWithdrawRecordDO;
import net.hzxzkj.cxlaike.module.cxlaike.dal.mysql.walletorderwithdraw.WalletOrderWithdrawMapper;
import net.hzxzkj.cxlaike.module.cxlaike.dal.mysql.walletorderwithdrawrecord.WalletOrderWithdrawRecordMapper;
import net.hzxzkj.cxlaike.module.cxlaike.enums.wallet.WalletOrderUserTypeEnum;
import net.hzxzkj.cxlaike.module.cxlaike.enums.wallet.WalletType;
import net.hzxzkj.cxlaike.module.cxlaike.enums.wallet.WithdrawOrderAuditStatus;
import net.hzxzkj.cxlaike.module.cxlaike.enums.wallet.WithdrawOrderStatus;
import net.hzxzkj.cxlaike.module.cxlaike.service.transferaccounts.TransferAccountsService;
import net.hzxzkj.cxlaike.module.cxlaike.utils.NumbersUtil;
import net.hzxzkj.cxlaike.module.member.api.user.MemberUserApi;
import net.hzxzkj.cxlaike.module.member.api.user.dto.MemberUserExtRespDTO;
import net.hzxzkj.cxlaike.module.member.api.user.dto.MemberUserRespDTO;
import net.hzxzkj.cxlaike.module.notify.client.TaskClient;
import net.hzxzkj.cxlaike.module.notify.enums.TaskHandlerEnum;
import net.hzxzkj.cxlaike.module.pay.api.wallet.WalletApi;
import net.hzxzkj.cxlaike.module.pay.api.wallet.dto.WalletDTO;
import net.hzxzkj.cxlaike.module.pay.enums.wallet.WalletLogTypeEnum;
import net.hzxzkj.cxlaike.module.pay.enums.wallet.WalletTypeEnum;
import net.hzxzkj.cxlaike.module.system.api.sms.SmsSendApi;
import net.hzxzkj.cxlaike.module.system.api.sms.dto.send.SmsSendSingleToUserReqDTO;
import net.hzxzkj.cxlaike.module.system.enums.sms.SmsSceneEnum;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

/**
 * 提现流水 Service 实现类
 *
 * @author 宵征源码
 */
@Slf4j
@Service
@Validated
public class WalletOrderWithdrawServiceImpl implements WalletOrderWithdrawService {

  @Resource
  private WalletOrderWithdrawMapper walletOrderWithdrawMapper;

  @Resource
  private MemberUserApi memberUserApi;
  @Resource
  @Lazy
  private TaskClient taskClient;
  @Resource
  private WalletApi walletApi;

  @Resource
  private TransferAccountsService transferAccountsService;
  @Resource
  private WalletOrderWithdrawRecordMapper walletOrderWithdrawRecordMapper;

  private WxPayService client;

  private TransferService transferClient;
  @Resource
  private NumbersUtil numbersUtil;
  @Resource
  private SmsSendApi smsSendApi;

  @Override
  @Transactional(rollbackFor = Exception.class)
  public Long createWalletOrderWithdraw(AppWalletOrderWithdrawCreateReqVO createReqVO) {
    // 插入
    Long memberId = createReqVO.getUserId();
    Integer walletType = createReqVO.getWalletType();

    if (!WalletType.WECHAT.getCode().equals(walletType)) {
      throw exception(new ErrorCode(400, "暂不支持当前提现方式!"));
    }

    MemberUserExtRespDTO userExt = memberUserApi.getUserExtById(memberId);
    MemberUserRespDTO userRespDTO = memberUserApi.getUser(memberId);
    if (userRespDTO == null) {
      throw exception(new ErrorCode(400, "用户不存在!"));
    }
    if (userExt == null) {
      throw exception(new ErrorCode(400, "用户不存在!"));
    }
    String wxOpenId = userExt.getWxOpenId();
    if (StringUtils.isEmpty(wxOpenId)) {
      throw exception(new ErrorCode(400, "请先绑定微信!"));
    }
    Long amount = createReqVO.getAmount();

    if (amount == null || amount <= 0) {
      throw exception(new ErrorCode(400, "提现金额不正确!"));
    }
    WalletDTO walletDTO = walletApi.get(memberId, WalletTypeEnum.MEMBER);

    if (walletDTO == null || walletDTO.getBalance() < amount) {
      log.error("当前钱包余额不足或钱包不存在!");
      throw exception(new ErrorCode(400, "账户金额不足!"));
    }

    LocalDateTime now = LocalDateTime.now();

    WalletOrderWithdrawDO walletOrderWithdraw = new WalletOrderWithdrawDO();
    walletOrderWithdraw.setUserId(memberId);
    walletOrderWithdraw.setMobile(userRespDTO.getMobile());
    walletOrderWithdraw.setUserType(WalletTypeEnum.MEMBER.getType());
    walletOrderWithdraw.setWithdrawType(walletType);
    walletOrderWithdraw.setOpenId(wxOpenId);
    walletOrderWithdraw.setApplyTime(now);
    walletOrderWithdraw.setAuditStatus(WithdrawOrderAuditStatus.WAIT_AUDIT.getCode());
    walletOrderWithdraw.setStatus(WithdrawOrderStatus.PROCESSING.getCode());
    walletOrderWithdraw.setAmount(amount);

    walletOrderWithdrawMapper.insert(walletOrderWithdraw);

    //冻结任务金额
    walletApi.freeze(memberId, amount, WalletTypeEnum.MEMBER,
        WalletLogTypeEnum.WITHDRAW_MEMBER, walletOrderWithdraw.getId(), "",
        WalletLogTypeEnum.WITHDRAW_MEMBER.getName());

    // 返回
    return walletOrderWithdraw.getId();
  }


  private void validateWalletOrderWithdrawExists(Long id) {
    if (walletOrderWithdrawMapper.selectById(id) == null) {
//            throw exception(WALLET_ORDER_WITHDRAW_NOT_EXISTS);
    }
  }

  @Override
  @Transactional(readOnly = true)
  public WalletOrderWithdrawDO getWalletOrderWithdraw(Long id,Long userId) {
    return walletOrderWithdrawMapper.selectOne(new LambdaQueryWrapperX<WalletOrderWithdrawDO>()
        .eq(WalletOrderWithdrawDO::getUserType,UserTypeEnum.MEMBER.getValue())
        .eq(WalletOrderWithdrawDO::getId, id)
        .eq(WalletOrderWithdrawDO::getUserId, userId));
  }


  @Override
  @Transactional(readOnly = true)
  public PageResult<WalletOrderWithdrawDO> getWalletOrderWithdrawPage(
      AppWalletOrderWithdrawPageReqVO pageReqVO) {
    return walletOrderWithdrawMapper.selectPage(pageReqVO);
  }

  @Override
  public PageResult<WalletOrderWithdrawDO> getListForAudit(WalletOrderWithdrawPageReqVO reqVO) {
    return walletOrderWithdrawMapper.selectPage(reqVO);
  }

  @Override
  @Transactional(rollbackFor = Exception.class)
  public void audit(WalletOrderWithdrawAuditReqVO reqVO) {

    reqVO.getIds().forEach(id -> {
      WalletOrderWithdrawDO walletOrderWithdrawDO = walletOrderWithdrawMapper.selectById(id);
      if (walletOrderWithdrawDO == null) {
        throw exception(DATA_ERROR);
      }
      if (!WithdrawOrderAuditStatus.WAIT_AUDIT.getCode()
          .equals(walletOrderWithdrawDO.getAuditStatus())) {
        throw exception(new ErrorCode(400, "该流水不处于待审核状态!"));
      }
//      WalletOrderWithdrawDO updateDO = new WalletOrderWithdrawDO();
      //1.修改流水数据
      walletOrderWithdrawDO.setAuditStatus(reqVO.getAuditStatus());
      walletOrderWithdrawDO.setReason(reqVO.getReason());
      walletOrderWithdrawMapper.updateById(walletOrderWithdrawDO);
      String wxOpenId = "";
      String userName = "";
      String wxPhone = "";
      if (UserTypeEnum.MEMBER.getValue().equals(walletOrderWithdrawDO.getUserType())) {
        MemberUserExtRespDTO userExtRespDTO = memberUserApi
            .getUserExtById(walletOrderWithdrawDO.getUserId());
        if (userExtRespDTO == null) {
          throw exception(new ErrorCode(400, "用户数据异常!"));
        }
        if (StringUtils.isBlank(userExtRespDTO.getWxOpenId())) {
          throw exception(new ErrorCode(400, "用户未绑定微信!"));
        }
        wxOpenId = userExtRespDTO.getWxOpenId();
        userName = userExtRespDTO.getIdentityName();
        wxPhone = userExtRespDTO.getWxPhone();
      }
      WalletTypeEnum walletTypeEnum = WalletTypeEnum.MEMBER;
      if (WalletOrderUserTypeEnum.MERCHANT.getValue().equals(walletOrderWithdrawDO.getUserType())) {
        walletTypeEnum = WalletTypeEnum.MERCHANT;
      }
      if (WithdrawOrderAuditStatus.AUDIT_NOT_PASS.getCode().equals(reqVO.getAuditStatus())) {
        // 2.驳回
        //解冻任务金额
        walletApi.unfreeze(walletOrderWithdrawDO.getUserId(), walletOrderWithdrawDO.getAmount(),
            walletTypeEnum,
            WalletLogTypeEnum.WITHDRAW_UNFREEZE_MEMBER, walletOrderWithdrawDO.getId(), "",
            WalletLogTypeEnum.WITHDRAW_UNFREEZE_MEMBER.getName());
      } else if (WithdrawOrderAuditStatus.AUDIT_PASS.getCode().equals(reqVO.getAuditStatus())) {
        //3.通过
        // 扣除冻结
        walletApi.subFreeze(walletOrderWithdrawDO.getUserId(), walletOrderWithdrawDO.getAmount(),
            walletTypeEnum,
            WalletLogTypeEnum.WITHDRAW_DEDUCT_MEMBER, walletOrderWithdrawDO.getId(), "");
      } else {
        throw exception(new ErrorCode(400, "操作状态错误!"));
      }
      //4. 给用户微信零钱打钱
      if (WithdrawOrderAuditStatus.AUDIT_PASS.getCode().equals(reqVO.getAuditStatus())) {
        TransferDetailReqVO detailReqVO = new TransferDetailReqVO();
        detailReqVO.setId(id);
        detailReqVO.setOutDetailNo(id.toString());
        detailReqVO.setOpenid(wxOpenId);
        detailReqVO.setTransferAmount(walletOrderWithdrawDO.getAmount().intValue());
        detailReqVO.setUserName(userName);
        detailReqVO.setMemberId(walletOrderWithdrawDO.getUserId());//目前只有app用户
        detailReqVO.setWxPhone(wxPhone);
        detailReqVO.setApplyTime(walletOrderWithdrawDO.getApplyTime());
        taskClient.createTask(TaskHandlerEnum.TRANSFER_MONEY.getValue(), null,
            JsonUtils.toJsonString(detailReqVO));
      }
    });

  }

  @Override
  public void dealRemit(TransferDetailReqVO detailReqVO) {
    TransferAccountsDO transferAccounts = transferAccountsService.getTransferAccounts();
    if (transferAccounts == null) {
      throw exception(new ErrorCode(400, "转账账户异常!"));
    }
    // 创建 config 配置
    WxPayConfig payConfig = new WxPayConfig();
    payConfig.setTradeType(WxPayConstants.TradeType.JSAPI);
    payConfig.setAppId(transferAccounts.getAppId());
    payConfig.setMchId(transferAccounts.getMchId());
//    payConfig.setPrivateKeyPath(FileUtils.createTempFile(transferAccounts.getPrivateKeyContent()).getPath());
//    payConfig.setPrivateCertPath(FileUtils.createTempFile(transferAccounts.getPrivateCertContent()).getPath());
    payConfig.setPrivateKeyContent(transferAccounts.getPrivateKeyContent().getBytes());
    payConfig.setPrivateCertContent(transferAccounts.getPrivateCertContent().getBytes());
    payConfig.setApiV3Key(transferAccounts.getApiV3Key());
    // 创建 client 客户端
    client = new WxPayServiceImpl();
    client.setConfig(payConfig);
    transferClient = new TransferServiceImpl(client);

    TransferBatchesRequest request = new TransferBatchesRequest();
    TransferBatchesRequest.TransferDetail transferDetail = WalletOrderWithdrawConvert.INSTANCE
        .convert(detailReqVO);
    Integer totalAmount = transferDetail.getTransferAmount();
    request.setAppid(transferAccounts.getAppId());
    //String outBatchNo = numbersUtil.getOutBatchNo();
    request.setOutBatchNo(detailReqVO.getId().toString());
    request.setTotalAmount(totalAmount);
    request.setBatchName(detailReqVO.getId().toString());
    request.setTotalNum(1);
    request.setTransferSceneId("");
    request.setBatchRemark(detailReqVO.getId().toString());
    request.setTransferDetailList(Arrays.asList(transferDetail));

    TransferBatchesResult transferBatchesResult = null;
    boolean fag = true;
    try {
      transferBatchesResult = transferClient.transferBatches(request);
    } catch (WxPayException e) {
      e.printStackTrace();
      fag = false;
    }
    WalletOrderWithdrawRecordDO recordDO = new WalletOrderWithdrawRecordDO();
    recordDO.setOutBatchNo(detailReqVO.getId().toString());
    recordDO.setInputParams(JsonUtils.toJsonString(request));
    if (transferBatchesResult != null) {
      recordDO.setInputParams(JsonUtils.toJsonString(transferBatchesResult));
    }
    walletOrderWithdrawRecordMapper.insert(recordDO);

    if (fag) {
      //发送短信
      taskClient.createTask(TaskHandlerEnum.TRANSFER_MONEY_SMS.getValue(), null,
          JsonUtils.toJsonString(detailReqVO));
    }
  }

  @Override
  public void sendSmsForRemit(TransferDetailReqVO reqVO) {
    MemberUserRespDTO memberUserRespDTO = memberUserApi.getUser(reqVO.getMemberId());
    SmsSendSingleToUserReqDTO reqDTO = new SmsSendSingleToUserReqDTO();
    reqDTO.setUserId(memberUserRespDTO.getId());
    reqDTO.setMobile(memberUserRespDTO.getMobile());
    reqDTO.setTemplateCode(SmsSceneEnum.MEMBER_WITHDRAW.getTemplateCode());
    Map<String, Object> params = new HashMap<>();
    params.put("reqtime", reqVO.getApplyTime());
    String wxPhone = "";
    if (StringUtils.isNotBlank(reqVO.getWxPhone())) {
      wxPhone = reqVO.getWxPhone().substring(7);
    }
    params.put("account", wxPhone);
    reqDTO.setTemplateParams(params);
    smsSendApi.sendSingleSmsToMember(reqDTO);

  }

}
