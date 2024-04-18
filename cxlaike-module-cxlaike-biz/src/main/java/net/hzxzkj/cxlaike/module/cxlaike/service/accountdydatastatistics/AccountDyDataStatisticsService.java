package net.hzxzkj.cxlaike.module.cxlaike.service.accountdydatastatistics;

import java.time.LocalDate;
import java.util.*;
import javax.validation.*;

import com.diboot.core.service.BaseService;
import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.accountdydatastatistics.vo.*;
import net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.accountdydatastatistics.AccountDyDataStatisticsDO;
import net.hzxzkj.cxlaike.framework.common.pojo.PageResult;
import net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.memberdybind.MemberDyBindDO;

/**
 * 账号纬度抖音数据统计 Service 接口
 *
 * @author 宵征源码
 */
public interface AccountDyDataStatisticsService extends BaseService<AccountDyDataStatisticsDO> {

    /**
     * 获取粉丝数据
     * @author hjn
     * @date 2023/10/25 下午4:59
     * @param reqVO
     * @return net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.accountdydatastatistics.vo.AccountDyDataStatisticsRespVO
     */
    AccountDyDataStatisticsRespVO getStatisticsByDate(AccountDyDataStatisticsReqVO reqVO);

    List<AccountDyDataStatisticsAnalyzeRespVO> getStatisticsForAnalyze(Integer dateType,Integer platformType,Integer orderBy,Boolean isAsc);

    /**
     * 分页查询账号分析统计数据
     * @param accountDyDataStatisticsPageReqVO
     * @return
     */
    PageResult<AccountDyDataStatisticsAnalyzeRespVO> getStatisticsForPage(AccountDyDataStatisticsPageReqVO accountDyDataStatisticsPageReqVO);

    /**
     * 获取指定日期的数据
     * @author hjn
     * @date 2023/10/25 下午4:59
     * @return net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.accountdydatastatistics.vo.AccountDyDataStatisticsRespVO
     */
    AccountDyDataStatisticsDO getYesterdayDataByOrderId(Long bindId, Integer type, LocalDate statisticsTime);

    void createStatisticsDO(AccountDyDataStatisticsDO statisticsDO);


}
