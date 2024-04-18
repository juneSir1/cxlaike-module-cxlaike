package net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.videodydatastatistics;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import net.hzxzkj.cxlaike.framework.common.pojo.CommonResult;
import net.hzxzkj.cxlaike.framework.common.pojo.PageResult;
import net.hzxzkj.cxlaike.framework.excel.core.util.ExcelUtils;
import net.hzxzkj.cxlaike.framework.operatelog.core.annotations.OperateLog;
import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.matrixtaskstore.vo.MatrixTaskStoreExcelVO;
import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.videodydatastatistics.vo.*;
import net.hzxzkj.cxlaike.module.cxlaike.convert.videodydatastatistics.VideoDyDataStatisticsConvert;
import net.hzxzkj.cxlaike.module.cxlaike.service.videodydatastatistics.VideoDyDataStatisticsService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

import static net.hzxzkj.cxlaike.framework.common.pojo.CommonResult.success;
import static net.hzxzkj.cxlaike.framework.operatelog.core.enums.OperateTypeEnum.EXPORT;

@Tag(name = "商家后台 - 视频纬度抖音数据统计")
@RestController
@RequestMapping("/cxlaike/video-dy-data-statistics")
@Validated
public class VideoDyDataStatisticsController {

    @Resource
    private VideoDyDataStatisticsService videoDyDataStatisticsService;


    @GetMapping("/getVideoDyDataStatisticsByDate")
    @Operation(summary = "核心指标数据(新增播放、新增点赞、新增评论、新增转发、新增收藏)")
    @PreAuthorize("@mss.hasPermission('cxlaike:video-dy-data-statistics:query')")
    public CommonResult<VideoDyDataStatisticsRespVO> getVideoDyDataStatisticsList(VideoDyDataStatisticsReqVO reqVO) {
        return success(videoDyDataStatisticsService.getVideoDyDataStatisticsByDate(reqVO));
    }


    @GetMapping("/getStatisticsForAnalyze")
    @Operation(summary = "视频数据分析")
    @PreAuthorize("@mss.hasPermission('cxlaike:video-dy-data-statistics:query')")
    @Parameters({
            @Parameter(name = "dateType", description = "时间筛选 1-昨天 7-近7天 30-近30天 60-近60天", example = "1"),
            @Parameter(name = "orderBy", description = "排序字段 1-新增内容发布数 2-新增播放 3-总粉丝数 4-总播放量 5-总发布数", example = "1"),
            @Parameter(name = "isAsc", description = "是否升序 是-true 否-false", example = "true")
    })
    public CommonResult<List<VideoDyDataStatisticsAnalyzeRespVO>> getStatisticsForAnalyze(Integer dateType, Integer platformType, Integer orderBy, Boolean isAsc) {
        return success(videoDyDataStatisticsService.getStatisticsForAnalyze(dateType, platformType, orderBy,isAsc));
    }

    @GetMapping("/getStatisticsForPage")
    @Operation(summary = "视频数据分析-分页")
    @PreAuthorize("@mss.hasPermission('cxlaike:video-dy-data-statistics:query')")
    public CommonResult<PageResult<VideoDyDataStatisticsAnalyzeRespVO>> getStatisticsForPage(VideoDyDataStatisticsAnalyzePageReqVO videoDyDataStatisticsAnalyzePageReqVO) {
        return success(videoDyDataStatisticsService.getStatisticsForPage(videoDyDataStatisticsAnalyzePageReqVO));
    }

    @GetMapping("/getStatisticsForDownload")
    @Operation(summary = "视频数据分析-分页")
    @OperateLog(type = EXPORT)
    @PreAuthorize("@mss.hasPermission('cxlaike:video-dy-data-statistics:query')")
    public void getStatisticsForDownload(VideoDyDataStatisticsAnalyzePageReqVO videoDyDataStatisticsAnalyzePageReqVO,
                                         HttpServletResponse response) throws Exception{
        videoDyDataStatisticsAnalyzePageReqVO.setPageNo(1);
        videoDyDataStatisticsAnalyzePageReqVO.setPageSize(1000);
        PageResult<VideoDyDataStatisticsAnalyzeRespVO> page = videoDyDataStatisticsService.getStatisticsForPage(videoDyDataStatisticsAnalyzePageReqVO);
        List<VideoDyDataStatisticsAnalyzeRespVO> list = page.getList();
        List<VideoDyDataStatisticsAnalyzeExcelVO> excelList = VideoDyDataStatisticsConvert.INSTANCE.convertList02(list);
        ExcelUtils.write(response, "视频数据分析.xls", "数据", VideoDyDataStatisticsAnalyzeExcelVO.class, excelList);
    }




}
