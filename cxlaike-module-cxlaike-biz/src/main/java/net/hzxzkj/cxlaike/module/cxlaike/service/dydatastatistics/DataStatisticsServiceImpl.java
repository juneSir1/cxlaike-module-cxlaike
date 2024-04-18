package net.hzxzkj.cxlaike.module.cxlaike.service.dydatastatistics;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import net.hzxzkj.cxlaike.framework.tenant.core.aop.TenantIgnore;
import net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.accountdydatastatistics.AccountDyDataStatisticsDO;
import net.hzxzkj.cxlaike.module.cxlaike.dal.mysql.accountdydatastatistics.AccountDyDataStatisticsMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author jianan.han
 * @date 2023/11/10 下午2:13
 * @description
 */
@Service
public class DataStatisticsServiceImpl implements DataStatisticsService{
    @Resource
    private AccountDyDataStatisticsMapper accountDyDataStatisticsMapper;

    @Override
    @TenantIgnore
    public List<AccountDyDataStatisticsDO> getListAccountDyData(List<AccountDyDataStatisticsDO> dataStatisticsAllDOS, LocalDateTime startTime, LocalDateTime endTime, Integer dataType,List<Long> bindIds) {
        dataStatisticsAllDOS = accountDyDataStatisticsMapper.selectList(new LambdaQueryWrapper<AccountDyDataStatisticsDO>()
                .between(AccountDyDataStatisticsDO::getStatisticsTime,startTime, endTime)
                .eq(AccountDyDataStatisticsDO::getDyType, dataType)
                .in(AccountDyDataStatisticsDO::getDyBindId,bindIds)
                .orderByAsc(AccountDyDataStatisticsDO::getStatisticsTime));
        return dataStatisticsAllDOS;
    }

    @Override
    @TenantIgnore
    public List<AccountDyDataStatisticsDO> getListAccountDyData(List<AccountDyDataStatisticsDO> dataStatisticsAllDOS, LocalDate statisticsTime, Integer dataType, List<Long> bindIds) {
        dataStatisticsAllDOS = accountDyDataStatisticsMapper.selectList(new LambdaQueryWrapper<AccountDyDataStatisticsDO>()
                .eq(AccountDyDataStatisticsDO::getStatisticsTime, statisticsTime)
                .in(AccountDyDataStatisticsDO::getDyBindId,bindIds)
                .eq(AccountDyDataStatisticsDO::getDyType, dataType));
        return dataStatisticsAllDOS;
    }
}
