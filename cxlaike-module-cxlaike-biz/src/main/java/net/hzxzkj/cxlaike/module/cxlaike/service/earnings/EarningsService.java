package net.hzxzkj.cxlaike.module.cxlaike.service.earnings;

import net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.earnings.EarningsDO;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

/**
 * 代理商每日汇总 Service 接口
 *
 * @author 宵征源码
 */
public interface EarningsService {

    /**
     * 根据userId和时间范围查询代理商每日汇总
     */
    List<EarningsDO> getEarningsByUserIdAndTimeRange(Long userId, LocalDate[] date);



}
