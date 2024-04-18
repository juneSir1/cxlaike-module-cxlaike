package net.hzxzkj.cxlaike.module.cxlaike.convert.accountdydatastatistics;

import java.util.*;

import net.hzxzkj.cxlaike.framework.common.pojo.PageResult;

import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.matrixtask.vo.MatrixTaskAccountReqVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.accountdydatastatistics.vo.*;
import net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.accountdydatastatistics.AccountDyDataStatisticsDO;

/**
 * 账号纬度抖音数据统计 Convert
 *
 * @author 宵征源码
 */
@Mapper
public interface AccountDyDataStatisticsConvert {

    AccountDyDataStatisticsConvert INSTANCE = Mappers.getMapper(AccountDyDataStatisticsConvert.class);

    AccountDyDataStatisticsAnalyzeRespVO convert(AccountDyDataStatisticsDO bean);

//    AccountDyDataStatisticsDO convert(AccountDyDataStatisticsCreateReqVO bean);
//
//    AccountDyDataStatisticsDO convert(AccountDyDataStatisticsUpdateReqVO bean);
//
//    AccountDyDataStatisticsRespVO convert(AccountDyDataStatisticsDO bean);
//
//    List<AccountDyDataStatisticsRespVO> convertList(List<AccountDyDataStatisticsDO> list);
//
//    PageResult<AccountDyDataStatisticsRespVO> convertPage(PageResult<AccountDyDataStatisticsDO> page);
//
    List<AccountDyDataStatisticsExcelVO> convertList02(List<AccountDyDataStatisticsAnalyzeRespVO> list);

    List<AccountDyDataStatisticsDO> convertList(List<MatrixTaskAccountReqVO> list);

}
