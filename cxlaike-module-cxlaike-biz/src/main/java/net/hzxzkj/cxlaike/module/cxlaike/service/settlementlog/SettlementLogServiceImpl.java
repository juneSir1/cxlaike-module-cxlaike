package net.hzxzkj.cxlaike.module.cxlaike.service.settlementlog;

import net.hzxzkj.cxlaike.framework.common.pojo.PageResult;
import net.hzxzkj.cxlaike.module.cxlaike.controller.app.settlementlog.vo.*;
import net.hzxzkj.cxlaike.module.cxlaike.convert.settlementlog.SettlementLogConvert;
import net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.settlementlog.SettlementLogDO;
import net.hzxzkj.cxlaike.module.cxlaike.dal.mysql.settlementlog.SettlementLogMapper;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;

/**
 * 结算流水 Service 实现类
 *
 * @author 宵征源码
 */
@Service
@Validated
public class SettlementLogServiceImpl implements SettlementLogService {

    @Resource
    private SettlementLogMapper settlementLogMapper;

    @Override
    public Long createSettlementLog(SettlementLogCreateReqVO createReqVO) {
        // 插入
        SettlementLogDO settlementLog = SettlementLogConvert.INSTANCE.convert(createReqVO);
        settlementLogMapper.insert(settlementLog);
        // 返回
        return settlementLog.getId();
    }

    @Override
    public void updateSettlementLog(SettlementLogUpdateReqVO updateReqVO) {
        // 更新
        SettlementLogDO updateObj = SettlementLogConvert.INSTANCE.convert(updateReqVO);
        settlementLogMapper.updateById(updateObj);
    }

    @Override
    public void deleteSettlementLog(Long id) {

        // 删除
        settlementLogMapper.deleteById(id);
    }



    @Override
    public SettlementLogDO getSettlementLog(Long id) {
        return settlementLogMapper.selectById(id);
    }

    @Override
    public List<SettlementLogDO> getSettlementLogList(Collection<Long> ids) {
        return settlementLogMapper.selectBatchIds(ids);
    }

    @Override
    public PageResult<SettlementLogDO> getSettlementLogPage(SettlementLogPageReqVO pageReqVO) {
        return settlementLogMapper.selectPage(pageReqVO);
    }

    @Override
    public List<SettlementLogDO> getSettlementLogList(SettlementLogExportReqVO exportReqVO) {
        return settlementLogMapper.selectList(exportReqVO);
    }

    @Override
    public List<SettlementLogSumRespVO> getSettlementLogSum(Long userId) {
        return settlementLogMapper.selectSum(userId);
    }

}
