package net.hzxzkj.cxlaike.module.cxlaike.service.walletorderwithdraw;

import javax.validation.Valid;
import net.hzxzkj.cxlaike.framework.common.pojo.PageResult;
import net.hzxzkj.cxlaike.module.cxlaike.controller.admin.walletorderwithdraw.vo.TransferDetailReqVO;
import net.hzxzkj.cxlaike.module.cxlaike.controller.admin.walletorderwithdraw.vo.WalletOrderWithdrawAuditReqVO;
import net.hzxzkj.cxlaike.module.cxlaike.controller.admin.walletorderwithdraw.vo.WalletOrderWithdrawPageReqVO;
import net.hzxzkj.cxlaike.module.cxlaike.controller.admin.walletorderwithdraw.vo.WalletOrderWithdrawRespVO;
import net.hzxzkj.cxlaike.module.cxlaike.controller.app.walletorderwithdraw.vo.AppWalletOrderWithdrawCreateReqVO;
import net.hzxzkj.cxlaike.module.cxlaike.controller.app.walletorderwithdraw.vo.AppWalletOrderWithdrawPageReqVO;
import net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.walletorderwithdraw.WalletOrderWithdrawDO;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * 提现流水 Service 接口
 *
 * @author 宵征源码
 */
public interface WalletOrderWithdrawService {

    /**
     * 创建提现流水
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createWalletOrderWithdraw(@Valid AppWalletOrderWithdrawCreateReqVO createReqVO);

    /**
     * 获得提现流水
     *
     * @param id 编号
     * @return 提现流水
     */
    WalletOrderWithdrawDO getWalletOrderWithdraw(Long id,Long userId);


    /**
     * 获得提现流水分页
     *
     * @param pageReqVO 分页查询
     * @return 提现流水分页
     */
    PageResult<WalletOrderWithdrawDO> getWalletOrderWithdrawPage(
        AppWalletOrderWithdrawPageReqVO pageReqVO);


    PageResult<WalletOrderWithdrawDO> getListForAudit(WalletOrderWithdrawPageReqVO reqVO);

    void audit(WalletOrderWithdrawAuditReqVO reqVO);

    void dealRemit(TransferDetailReqVO detailReqVOS);

    void sendSmsForRemit(TransferDetailReqVO detailReqVOS);
}
