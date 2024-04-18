package net.hzxzkj.cxlaike.module.cxlaike.convert.walletorderwithdraw;

import java.util.*;

import com.github.binarywang.wxpay.bean.transfer.TransferBatchesRequest;
import net.hzxzkj.cxlaike.framework.common.pojo.PageResult;

import net.hzxzkj.cxlaike.module.cxlaike.controller.admin.walletorderwithdraw.vo.TransferDetailReqVO;
import net.hzxzkj.cxlaike.module.cxlaike.controller.admin.walletorderwithdraw.vo.WalletOrderWithdrawRespVO;
import net.hzxzkj.cxlaike.module.cxlaike.controller.app.walletorderwithdraw.vo.AppWalletOrderWithdrawCreateReqVO;
import net.hzxzkj.cxlaike.module.cxlaike.controller.app.walletorderwithdraw.vo.AppWalletOrderWithdrawExcelVO;
import net.hzxzkj.cxlaike.module.cxlaike.controller.app.walletorderwithdraw.vo.AppWalletOrderWithdrawRespVO;
import net.hzxzkj.cxlaike.module.cxlaike.controller.app.walletorderwithdraw.vo.AppWalletOrderWithdrawUpdateReqVO;
import net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.walletorderwithdraw.WalletOrderWithdrawDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * 提现流水 Convert
 *
 * @author 宵征源码
 */
@Mapper
public interface WalletOrderWithdrawConvert {

    WalletOrderWithdrawConvert INSTANCE = Mappers.getMapper(WalletOrderWithdrawConvert.class);

    WalletOrderWithdrawDO convert(AppWalletOrderWithdrawCreateReqVO bean);

    WalletOrderWithdrawDO convert(AppWalletOrderWithdrawUpdateReqVO bean);

    AppWalletOrderWithdrawRespVO convert(WalletOrderWithdrawDO bean);

    List<AppWalletOrderWithdrawRespVO> convertList(List<WalletOrderWithdrawDO> list);

    PageResult<AppWalletOrderWithdrawRespVO> convertPage(PageResult<WalletOrderWithdrawDO> page);

    List<AppWalletOrderWithdrawExcelVO> convertList02(List<WalletOrderWithdrawDO> list);

    PageResult<WalletOrderWithdrawRespVO> convertPage02(PageResult<WalletOrderWithdrawDO> page);

    List<TransferBatchesRequest.TransferDetail> convertList03(List<TransferDetailReqVO> detailReqVOS);
    TransferBatchesRequest.TransferDetail convert(TransferDetailReqVO detailReqVO);
}
