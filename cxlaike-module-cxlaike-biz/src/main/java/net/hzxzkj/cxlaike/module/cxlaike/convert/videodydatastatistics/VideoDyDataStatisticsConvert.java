package net.hzxzkj.cxlaike.module.cxlaike.convert.videodydatastatistics;

import java.util.*;

import net.hzxzkj.cxlaike.framework.common.pojo.PageResult;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.videodydatastatistics.vo.*;
import net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.videodydatastatistics.VideoDyDataStatisticsDO;

/**
 * 视频纬度抖音数据统计 Convert
 *
 * @author 宵征源码
 */
@Mapper
public interface VideoDyDataStatisticsConvert {

    VideoDyDataStatisticsConvert INSTANCE = Mappers.getMapper(VideoDyDataStatisticsConvert.class);
//
//    VideoDyDataStatisticsDO convert(VideoDyDataStatisticsCreateReqVO bean);
//
//    VideoDyDataStatisticsDO convert(VideoDyDataStatisticsUpdateReqVO bean);
//
//    VideoDyDataStatisticsRespVO convert(VideoDyDataStatisticsDO bean);
//
//    List<VideoDyDataStatisticsRespVO> convertList(List<VideoDyDataStatisticsDO> list);
//
//    PageResult<VideoDyDataStatisticsRespVO> convertPage(PageResult<VideoDyDataStatisticsDO> page);
//
//    List<VideoDyDataStatisticsExcelVO> convertList02(List<VideoDyDataStatisticsDO> list);

    VideoDyDataStatisticsAnalyzeRespVO convert(VideoDyDataStatisticsDO bean);

    VideoDyDataStatisticsDO convert01(VideoDyDataStatisticsDO bean);

    List<VideoDyDataStatisticsAnalyzeExcelVO> convertList02(List<VideoDyDataStatisticsAnalyzeRespVO> list);

}
