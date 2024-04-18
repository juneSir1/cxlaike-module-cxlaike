package net.hzxzkj.cxlaike.module.cxlaike.service.dydatastatistics;

import net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.accountdydatastatistics.AccountDyDataStatisticsDO;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author jianan.han
 * @date 2023/11/10 下午2:13
 * @description
 */
public interface DataStatisticsService {

    List<AccountDyDataStatisticsDO> getListAccountDyData(List<AccountDyDataStatisticsDO> dataStatisticsAllDOS, LocalDateTime startTime,LocalDateTime endTime
                        ,Integer dataType,List<Long> bindIds);




    List<AccountDyDataStatisticsDO> getListAccountDyData(List<AccountDyDataStatisticsDO> dataStatisticsAllDOS, LocalDate statisticsTime,Integer dataType, List<Long> bindIds);
}
