package net.hzxzkj.cxlaike.module.cxlaike.service.videodydatastatistics;

import java.time.LocalDate;
import java.util.*;
import javax.validation.*;

import com.diboot.core.service.BaseService;
import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.taskdydatastatistics.vo.TaskDyDataStatisticsAnalyzeRespVO;
import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.videodydatastatistics.vo.*;
import net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.videodydatastatistics.VideoDyDataStatisticsDO;
import net.hzxzkj.cxlaike.framework.common.pojo.PageResult;

/**
 * 视频纬度抖音数据统计 Service 接口
 *
 * @author 宵征源码
 */
public interface VideoDyDataStatisticsService extends BaseService<VideoDyDataStatisticsDO> {

//    /**
//     * 创建视频纬度抖音数据统计
//     *
//     * @param createReqVO 创建信息
//     * @return 编号
//     */
//    Long createVideoDyDataStatistics(@Valid VideoDyDataStatisticsCreateReqVO createReqVO);
//
//    /**
//     * 更新视频纬度抖音数据统计
//     *
//     * @param updateReqVO 更新信息
//     */
//    void updateVideoDyDataStatistics(@Valid VideoDyDataStatisticsUpdateReqVO updateReqVO);
//
//    /**
//     * 删除视频纬度抖音数据统计
//     *
//     * @param id 编号
//     */
//    void deleteVideoDyDataStatistics(Long id);
//
//    /**
//     * 获得视频纬度抖音数据统计
//     *
//     * @param id 编号
//     * @return 视频纬度抖音数据统计
//     */
//    VideoDyDataStatisticsDO getVideoDyDataStatistics(Long id);
//
//    /**
//     * 获得视频纬度抖音数据统计列表
//     *
//     * @param ids 编号
//     * @return 视频纬度抖音数据统计列表
//     */
//    List<VideoDyDataStatisticsDO> getVideoDyDataStatisticsList(Collection<Long> ids);
//
//    /**
//     * 获得视频纬度抖音数据统计分页
//     *
//     * @param pageReqVO 分页查询
//     * @return 视频纬度抖音数据统计分页
//     */
//    PageResult<VideoDyDataStatisticsDO> getVideoDyDataStatisticsPage(VideoDyDataStatisticsPageReqVO pageReqVO);
//
//    /**
//     * 获得视频纬度抖音数据统计列表, 用于 Excel 导出
//     *
//     * @param exportReqVO 查询条件
//     * @return 视频纬度抖音数据统计列表
//     */
//    List<VideoDyDataStatisticsDO> getVideoDyDataStatisticsList(VideoDyDataStatisticsExportReqVO exportReqVO);

    VideoDyDataStatisticsRespVO getVideoDyDataStatisticsByDate(VideoDyDataStatisticsReqVO reqVO);

    List<VideoDyDataStatisticsAnalyzeRespVO> getStatisticsForAnalyze(Integer dateType, Integer platformType, Integer orderBy, Boolean isAsc);

    /**
     * 分页查询账号分析统计数据
     * @param videoDyDataStatisticsAnalyzePageReqVO
     * @return
     */
    PageResult<VideoDyDataStatisticsAnalyzeRespVO> getStatisticsForPage(VideoDyDataStatisticsAnalyzePageReqVO videoDyDataStatisticsAnalyzePageReqVO);
    /**
     * 获得前提数据统计数据
     *
     * @param orderId 订单id
     * @param taskId 任务id
     * @param type 类型 1-达人 2-商户
     * @return VideoDyDataStatisticsDO
     */
    VideoDyDataStatisticsDO getBeforeDataByOrderId(Long orderId, Long taskId, Integer type, LocalDate statisticsTime);
    VideoDyDataStatisticsDO getYesterdayDataByOrderId(Long orderId, Long taskId, Integer type, LocalDate statisticsTime);

    List<VideoDyDataStatisticsDO> getBeforeDataByBindId(Long bindId, LocalDate statisticsTime);

    List<VideoDyDataStatisticsDO> getBeforeData(LocalDate statisticsTime, Integer platformType);

}
