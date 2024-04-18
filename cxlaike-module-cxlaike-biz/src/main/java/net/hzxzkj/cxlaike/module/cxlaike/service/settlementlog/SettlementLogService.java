package net.hzxzkj.cxlaike.module.cxlaike.service.settlementlog;

import java.util.*;
import javax.validation.*;

import net.hzxzkj.cxlaike.module.cxlaike.controller.app.settlementlog.vo.*;
import net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.settlementlog.SettlementLogDO;
import net.hzxzkj.cxlaike.framework.common.pojo.PageResult;

/**
 * 结算流水 Service 接口
 *
 * @author 宵征源码
 */
public interface SettlementLogService {

    /**
     * 创建结算流水
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createSettlementLog(@Valid SettlementLogCreateReqVO createReqVO);

    /**
     * 更新结算流水
     *
     * @param updateReqVO 更新信息
     */
    void updateSettlementLog(@Valid SettlementLogUpdateReqVO updateReqVO);

    /**
     * 删除结算流水
     *
     * @param id 编号
     */
    void deleteSettlementLog(Long id);

    /**
     * 获得结算流水
     *
     * @param id 编号
     * @return 结算流水
     */
    SettlementLogDO getSettlementLog(Long id);

    /**
     * 获得结算流水列表
     *
     * @param ids 编号
     * @return 结算流水列表
     */
    List<SettlementLogDO> getSettlementLogList(Collection<Long> ids);

    /**
     * 获得结算流水分页
     *
     * @param pageReqVO 分页查询
     * @return 结算流水分页
     */
    PageResult<SettlementLogDO> getSettlementLogPage(SettlementLogPageReqVO pageReqVO);

    /**
     * 获得结算流水列表, 用于 Excel 导出
     *
     * @param exportReqVO 查询条件
     * @return 结算流水列表
     */
    List<SettlementLogDO> getSettlementLogList(SettlementLogExportReqVO exportReqVO);

    /**
     * 获取每个类型的总和
     */
    List<SettlementLogSumRespVO> getSettlementLogSum(Long userId);

}
