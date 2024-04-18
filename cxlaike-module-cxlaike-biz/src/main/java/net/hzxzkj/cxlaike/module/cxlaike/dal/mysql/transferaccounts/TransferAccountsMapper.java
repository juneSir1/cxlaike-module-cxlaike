package net.hzxzkj.cxlaike.module.cxlaike.dal.mysql.transferaccounts;

import net.hzxzkj.cxlaike.framework.common.pojo.PageResult;
import net.hzxzkj.cxlaike.framework.mybatis.core.mapper.BaseMapperX;
import net.hzxzkj.cxlaike.framework.mybatis.core.query.LambdaQueryWrapperX;
import net.hzxzkj.cxlaike.module.cxlaike.controller.admin.transferaccounts.vo.TransferAccountsExportReqVO;
import net.hzxzkj.cxlaike.module.cxlaike.controller.admin.transferaccounts.vo.TransferAccountsPageReqVO;
import net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.transferaccounts.TransferAccountsDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 转账账户 Mapper
 *
 * @author 宵征源码
 */
@Mapper
public interface TransferAccountsMapper extends BaseMapperX<TransferAccountsDO> {

    default PageResult<TransferAccountsDO> selectPage(TransferAccountsPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<TransferAccountsDO>()
                .eqIfPresent(TransferAccountsDO::getStatus, reqVO.getStatus())
                .eqIfPresent(TransferAccountsDO::getRemark, reqVO.getRemark())
                .eqIfPresent(TransferAccountsDO::getFeeRate, reqVO.getFeeRate())
                .eqIfPresent(TransferAccountsDO::getAppId, reqVO.getAppId())
                .eqIfPresent(TransferAccountsDO::getMchId, reqVO.getMchId())
                .eqIfPresent(TransferAccountsDO::getApiV3Key, reqVO.getApiV3Key())
                .eqIfPresent(TransferAccountsDO::getPrivateKeyContent, reqVO.getPrivateKeyContent())
                .eqIfPresent(TransferAccountsDO::getPrivateCertContent, reqVO.getPrivateCertContent())
                .betweenIfPresent(TransferAccountsDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(TransferAccountsDO::getId));
    }

    default List<TransferAccountsDO> selectList(TransferAccountsExportReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<TransferAccountsDO>()
                .eqIfPresent(TransferAccountsDO::getStatus, reqVO.getStatus())
                .eqIfPresent(TransferAccountsDO::getRemark, reqVO.getRemark())
                .eqIfPresent(TransferAccountsDO::getFeeRate, reqVO.getFeeRate())
                .eqIfPresent(TransferAccountsDO::getAppId, reqVO.getAppId())
                .eqIfPresent(TransferAccountsDO::getMchId, reqVO.getMchId())
                .eqIfPresent(TransferAccountsDO::getApiV3Key, reqVO.getApiV3Key())
                .eqIfPresent(TransferAccountsDO::getPrivateKeyContent, reqVO.getPrivateKeyContent())
                .eqIfPresent(TransferAccountsDO::getPrivateCertContent, reqVO.getPrivateCertContent())
                .betweenIfPresent(TransferAccountsDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(TransferAccountsDO::getId));
    }

}
