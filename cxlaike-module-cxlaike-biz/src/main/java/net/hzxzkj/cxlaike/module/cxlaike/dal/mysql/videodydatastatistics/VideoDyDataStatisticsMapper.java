package net.hzxzkj.cxlaike.module.cxlaike.dal.mysql.videodydatastatistics;

import java.util.*;

import net.hzxzkj.cxlaike.framework.common.pojo.PageResult;
import net.hzxzkj.cxlaike.framework.mybatis.core.query.LambdaQueryWrapperX;
import net.hzxzkj.cxlaike.framework.mybatis.core.mapper.BaseMapperX;
import net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.videodydatastatistics.VideoDyDataStatisticsDO;
import org.apache.ibatis.annotations.Mapper;
import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.videodydatastatistics.vo.*;

/**
 * 视频纬度抖音数据统计 Mapper
 *
 * @author 宵征源码
 */
@Mapper
public interface VideoDyDataStatisticsMapper extends BaseMapperX<VideoDyDataStatisticsDO> {

//    default PageResult<VideoDyDataStatisticsDO> selectPage(VideoDyDataStatisticsPageReqVO reqVO) {
//        return selectPage(reqVO, new LambdaQueryWrapperX<VideoDyDataStatisticsDO>()
//                .eqIfPresent(VideoDyDataStatisticsDO::getOrderId, reqVO.getOrderId())
//                .eqIfPresent(VideoDyDataStatisticsDO::getDyType, reqVO.getDyType())
//                .eqIfPresent(VideoDyDataStatisticsDO::getDyId, reqVO.getDyId())
//                .eqIfPresent(VideoDyDataStatisticsDO::getVideoLink, reqVO.getVideoLink())
//                .betweenIfPresent(VideoDyDataStatisticsDO::getStatisticsTime, reqVO.getStatisticsTime())
//                .eqIfPresent(VideoDyDataStatisticsDO::getPlayCountIncrement, reqVO.getPlayCountIncrement())
//                .eqIfPresent(VideoDyDataStatisticsDO::getPlayCount, reqVO.getPlayCount())
//                .eqIfPresent(VideoDyDataStatisticsDO::getDiggCountIncrement, reqVO.getDiggCountIncrement())
//                .eqIfPresent(VideoDyDataStatisticsDO::getDiggCount, reqVO.getDiggCount())
//                .eqIfPresent(VideoDyDataStatisticsDO::getCommentCountIncrement, reqVO.getCommentCountIncrement())
//                .eqIfPresent(VideoDyDataStatisticsDO::getCommentCount, reqVO.getCommentCount())
//                .eqIfPresent(VideoDyDataStatisticsDO::getShareCountIncrement, reqVO.getShareCountIncrement())
//                .eqIfPresent(VideoDyDataStatisticsDO::getShareCount, reqVO.getShareCount())
//                .eqIfPresent(VideoDyDataStatisticsDO::getCollectCountIncrement, reqVO.getCollectCountIncrement())
//                .eqIfPresent(VideoDyDataStatisticsDO::getCollectCount, reqVO.getCollectCount())
//                .eqIfPresent(VideoDyDataStatisticsDO::getPlayDuration, reqVO.getPlayDuration())
//                .betweenIfPresent(VideoDyDataStatisticsDO::getCreateTime, reqVO.getCreateTime())
//                .orderByDesc(VideoDyDataStatisticsDO::getId));
//    }
//
//    default List<VideoDyDataStatisticsDO> selectList(VideoDyDataStatisticsExportReqVO reqVO) {
//        return selectList(new LambdaQueryWrapperX<VideoDyDataStatisticsDO>()
//                .eqIfPresent(VideoDyDataStatisticsDO::getOrderId, reqVO.getOrderId())
//                .eqIfPresent(VideoDyDataStatisticsDO::getDyType, reqVO.getDyType())
//                .eqIfPresent(VideoDyDataStatisticsDO::getDyId, reqVO.getDyId())
//                .eqIfPresent(VideoDyDataStatisticsDO::getVideoLink, reqVO.getVideoLink())
//                .betweenIfPresent(VideoDyDataStatisticsDO::getStatisticsTime, reqVO.getStatisticsTime())
//                .eqIfPresent(VideoDyDataStatisticsDO::getPlayCountIncrement, reqVO.getPlayCountIncrement())
//                .eqIfPresent(VideoDyDataStatisticsDO::getPlayCount, reqVO.getPlayCount())
//                .eqIfPresent(VideoDyDataStatisticsDO::getDiggCountIncrement, reqVO.getDiggCountIncrement())
//                .eqIfPresent(VideoDyDataStatisticsDO::getDiggCount, reqVO.getDiggCount())
//                .eqIfPresent(VideoDyDataStatisticsDO::getCommentCountIncrement, reqVO.getCommentCountIncrement())
//                .eqIfPresent(VideoDyDataStatisticsDO::getCommentCount, reqVO.getCommentCount())
//                .eqIfPresent(VideoDyDataStatisticsDO::getShareCountIncrement, reqVO.getShareCountIncrement())
//                .eqIfPresent(VideoDyDataStatisticsDO::getShareCount, reqVO.getShareCount())
//                .eqIfPresent(VideoDyDataStatisticsDO::getCollectCountIncrement, reqVO.getCollectCountIncrement())
//                .eqIfPresent(VideoDyDataStatisticsDO::getCollectCount, reqVO.getCollectCount())
//                .eqIfPresent(VideoDyDataStatisticsDO::getPlayDuration, reqVO.getPlayDuration())
//                .betweenIfPresent(VideoDyDataStatisticsDO::getCreateTime, reqVO.getCreateTime())
//                .orderByDesc(VideoDyDataStatisticsDO::getId));
//    }

}
