package net.hzxzkj.cxlaike.module.cxlaike.dal.mysql.accountdydatastatistics;

import java.util.*;

import net.hzxzkj.cxlaike.framework.common.pojo.PageResult;
import net.hzxzkj.cxlaike.framework.mybatis.core.query.LambdaQueryWrapperX;
import net.hzxzkj.cxlaike.framework.mybatis.core.mapper.BaseMapperX;
import net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.accountdydatastatistics.AccountDyDataStatisticsDO;
import org.apache.ibatis.annotations.Mapper;
import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.accountdydatastatistics.vo.*;

/**
 * 账号纬度抖音数据统计 Mapper
 *
 * @author 宵征源码
 */
@Mapper
public interface AccountDyDataStatisticsMapper extends BaseMapperX<AccountDyDataStatisticsDO> {

//    default PageResult<AccountDyDataStatisticsDO> selectPage(AccountDyDataStatisticsPageReqVO reqVO) {
//        return selectPage(reqVO, new LambdaQueryWrapperX<AccountDyDataStatisticsDO>()
//                .eqIfPresent(AccountDyDataStatisticsDO::getDyType, reqVO.getDyType())
//                .eqIfPresent(AccountDyDataStatisticsDO::getDyId, reqVO.getDyId())
//                .betweenIfPresent(AccountDyDataStatisticsDO::getStatisticsTime, reqVO.getStatisticsTime())
//                .eqIfPresent(AccountDyDataStatisticsDO::getPublishCountIncrement, reqVO.getPublishCountIncrement())
//                .eqIfPresent(AccountDyDataStatisticsDO::getPublishCount, reqVO.getPublishCount())
//                .eqIfPresent(AccountDyDataStatisticsDO::getPlayCountIncrement, reqVO.getPlayCountIncrement())
//                .eqIfPresent(AccountDyDataStatisticsDO::getPlayCount, reqVO.getPlayCount())
//                .eqIfPresent(AccountDyDataStatisticsDO::getFansCountIncrement, reqVO.getFansCountIncrement())
//                .eqIfPresent(AccountDyDataStatisticsDO::getFansCount, reqVO.getFansCount())
//                .eqIfPresent(AccountDyDataStatisticsDO::getDiggCountIncrement, reqVO.getDiggCountIncrement())
//                .eqIfPresent(AccountDyDataStatisticsDO::getDiggCount, reqVO.getDiggCount())
//                .eqIfPresent(AccountDyDataStatisticsDO::getCommentCountIncrement, reqVO.getCommentCountIncrement())
//                .eqIfPresent(AccountDyDataStatisticsDO::getCommentCount, reqVO.getCommentCount())
//                .eqIfPresent(AccountDyDataStatisticsDO::getShareCountIncrement, reqVO.getShareCountIncrement())
//                .eqIfPresent(AccountDyDataStatisticsDO::getShareCount, reqVO.getShareCount())
//                .eqIfPresent(AccountDyDataStatisticsDO::getCollectCountIncrement, reqVO.getCollectCountIncrement())
//                .eqIfPresent(AccountDyDataStatisticsDO::getCollectCount, reqVO.getCollectCount())
//                .betweenIfPresent(AccountDyDataStatisticsDO::getCreateTime, reqVO.getCreateTime())
//                .orderByDesc(AccountDyDataStatisticsDO::getId));
//    }

//    default List<AccountDyDataStatisticsDO> selectList(AccountDyDataStatisticsExportReqVO reqVO) {
//        return selectList(new LambdaQueryWrapperX<AccountDyDataStatisticsDO>()
//                .eqIfPresent(AccountDyDataStatisticsDO::getDyType, reqVO.getDyType())
//                .eqIfPresent(AccountDyDataStatisticsDO::getDyId, reqVO.getDyId())
//                .betweenIfPresent(AccountDyDataStatisticsDO::getStatisticsTime, reqVO.getStatisticsTime())
//                .eqIfPresent(AccountDyDataStatisticsDO::getPublishCountIncrement, reqVO.getPublishCountIncrement())
//                .eqIfPresent(AccountDyDataStatisticsDO::getPublishCount, reqVO.getPublishCount())
//                .eqIfPresent(AccountDyDataStatisticsDO::getPlayCountIncrement, reqVO.getPlayCountIncrement())
//                .eqIfPresent(AccountDyDataStatisticsDO::getPlayCount, reqVO.getPlayCount())
//                .eqIfPresent(AccountDyDataStatisticsDO::getFansCountIncrement, reqVO.getFansCountIncrement())
//                .eqIfPresent(AccountDyDataStatisticsDO::getFansCount, reqVO.getFansCount())
//                .eqIfPresent(AccountDyDataStatisticsDO::getDiggCountIncrement, reqVO.getDiggCountIncrement())
//                .eqIfPresent(AccountDyDataStatisticsDO::getDiggCount, reqVO.getDiggCount())
//                .eqIfPresent(AccountDyDataStatisticsDO::getCommentCountIncrement, reqVO.getCommentCountIncrement())
//                .eqIfPresent(AccountDyDataStatisticsDO::getCommentCount, reqVO.getCommentCount())
//                .eqIfPresent(AccountDyDataStatisticsDO::getShareCountIncrement, reqVO.getShareCountIncrement())
//                .eqIfPresent(AccountDyDataStatisticsDO::getShareCount, reqVO.getShareCount())
//                .eqIfPresent(AccountDyDataStatisticsDO::getCollectCountIncrement, reqVO.getCollectCountIncrement())
//                .eqIfPresent(AccountDyDataStatisticsDO::getCollectCount, reqVO.getCollectCount())
//                .betweenIfPresent(AccountDyDataStatisticsDO::getCreateTime, reqVO.getCreateTime())
//                .orderByDesc(AccountDyDataStatisticsDO::getId));
//    }

}
