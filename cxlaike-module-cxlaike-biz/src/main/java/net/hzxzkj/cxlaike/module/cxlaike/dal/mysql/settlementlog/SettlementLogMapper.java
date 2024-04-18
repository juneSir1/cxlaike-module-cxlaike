package net.hzxzkj.cxlaike.module.cxlaike.dal.mysql.settlementlog;

import java.util.*;

import net.hzxzkj.cxlaike.framework.common.pojo.PageResult;
import net.hzxzkj.cxlaike.framework.mybatis.core.query.LambdaQueryWrapperX;
import net.hzxzkj.cxlaike.framework.mybatis.core.mapper.BaseMapperX;
import net.hzxzkj.cxlaike.module.cxlaike.controller.app.settlementlog.vo.SettlementLogExportReqVO;
import net.hzxzkj.cxlaike.module.cxlaike.controller.app.settlementlog.vo.SettlementLogPageReqVO;
import net.hzxzkj.cxlaike.module.cxlaike.controller.app.settlementlog.vo.SettlementLogSumRespVO;
import net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.settlementlog.SettlementLogDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 结算流水 Mapper
 *
 * @author 宵征源码
 */
@Mapper
public interface SettlementLogMapper extends BaseMapperX<SettlementLogDO> {

    default PageResult<SettlementLogDO> selectPage(SettlementLogPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<SettlementLogDO>()
                .eqIfPresent(SettlementLogDO::getUserId, reqVO.getUserId())
                .eqIfPresent(SettlementLogDO::getType, reqVO.getType())
                .betweenIfPresent(SettlementLogDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(SettlementLogDO::getId));
    }

    default List<SettlementLogDO> selectList(SettlementLogExportReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<SettlementLogDO>()
                .eqIfPresent(SettlementLogDO::getUserId, reqVO.getUserId())
                .eqIfPresent(SettlementLogDO::getType, reqVO.getType())
                .eqIfPresent(SettlementLogDO::getAmount, reqVO.getAmount())
                .eqIfPresent(SettlementLogDO::getAccount, reqVO.getAccount())
                .eqIfPresent(SettlementLogDO::getAccountType, reqVO.getAccountType())
                .betweenIfPresent(SettlementLogDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(SettlementLogDO::getId));
    }

    List<SettlementLogSumRespVO> selectSum(@Param("userId")Long userId);

}
