package net.hzxzkj.cxlaike.module.cxlaike.dal.mysql.walletorderwithdraw;

import net.hzxzkj.cxlaike.framework.common.enums.UserTypeEnum;
import net.hzxzkj.cxlaike.framework.common.pojo.PageResult;
import net.hzxzkj.cxlaike.framework.mybatis.core.mapper.BaseMapperX;
import net.hzxzkj.cxlaike.framework.mybatis.core.query.LambdaQueryWrapperX;
import net.hzxzkj.cxlaike.module.cxlaike.controller.admin.walletorderwithdraw.vo.WalletOrderWithdrawPageReqVO;
import net.hzxzkj.cxlaike.module.cxlaike.controller.app.walletorderwithdraw.vo.AppWalletOrderWithdrawPageReqVO;
import net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.walletorderwithdraw.WalletOrderWithdrawDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 提现流水 Mapper
 *
 * @author 宵征源码
 */
@Mapper
public interface WalletOrderWithdrawMapper extends BaseMapperX<WalletOrderWithdrawDO> {

    default PageResult<WalletOrderWithdrawDO> selectPage(AppWalletOrderWithdrawPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<WalletOrderWithdrawDO>()
                .eq(WalletOrderWithdrawDO::getUserType, UserTypeEnum.MEMBER.getValue())
                .eqIfPresent(WalletOrderWithdrawDO::getUserId, reqVO.getUserId())
                .eqIfPresent(WalletOrderWithdrawDO::getWithdrawType, reqVO.getWithdrawType())
                .eqIfPresent(WalletOrderWithdrawDO::getStatus, reqVO.getStatus())
                .orderByDesc(WalletOrderWithdrawDO::getId));
    }

    default PageResult<WalletOrderWithdrawDO> selectPage(WalletOrderWithdrawPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<WalletOrderWithdrawDO>()
                .betweenIfPresent(WalletOrderWithdrawDO::getApplyTime, reqVO.getApplyTime())
                .eqIfPresent(WalletOrderWithdrawDO::getAuditStatus, reqVO.getAuditStatus())
                .likeIfPresent(WalletOrderWithdrawDO::getMobile, reqVO.getMobile())
                .orderByDesc(WalletOrderWithdrawDO::getId));
    }

}
