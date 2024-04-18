package net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.accountdydatastatistics;

import io.swagger.v3.oas.annotations.Parameters;
import net.hzxzkj.cxlaike.framework.mybatis.core.controller.BaseControllerX;
import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.videodydatastatistics.vo.VideoDyDataStatisticsAnalyzeExcelVO;
import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.videodydatastatistics.vo.VideoDyDataStatisticsAnalyzePageReqVO;
import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.videodydatastatistics.vo.VideoDyDataStatisticsAnalyzeRespVO;
import net.hzxzkj.cxlaike.module.cxlaike.convert.videodydatastatistics.VideoDyDataStatisticsConvert;
import net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.memberdybind.MemberDyBindDO;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Operation;

import javax.validation.constraints.*;
import javax.validation.*;
import javax.servlet.http.*;
import java.util.*;
import java.io.IOException;

import net.hzxzkj.cxlaike.framework.common.pojo.PageResult;
import net.hzxzkj.cxlaike.framework.common.pojo.CommonResult;
import static net.hzxzkj.cxlaike.framework.common.pojo.CommonResult.success;

import net.hzxzkj.cxlaike.framework.excel.core.util.ExcelUtils;

import net.hzxzkj.cxlaike.framework.operatelog.core.annotations.OperateLog;
import static net.hzxzkj.cxlaike.framework.operatelog.core.enums.OperateTypeEnum.*;

import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.accountdydatastatistics.vo.*;
import net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.accountdydatastatistics.AccountDyDataStatisticsDO;
import net.hzxzkj.cxlaike.module.cxlaike.convert.accountdydatastatistics.AccountDyDataStatisticsConvert;
import net.hzxzkj.cxlaike.module.cxlaike.service.accountdydatastatistics.AccountDyDataStatisticsService;

@Tag(name = "管理后台 - 账号纬度抖音数据统计")
@RestController
@RequestMapping("/cxlaike/account-dy-data-statistics")
@Validated
public class AccountDyDataStatisticsController extends BaseControllerX<AccountDyDataStatisticsDO> {

    @Resource
    private AccountDyDataStatisticsService accountDyDataStatisticsService;

    @GetMapping("/getStatisticsByDate")
    @Operation(summary = "核心指标数据")
    @PreAuthorize("@mss.hasPermission('cxlaike:account-dy-data-statistics:query')")
    public CommonResult<AccountDyDataStatisticsRespVO> getAccountDyDataStatisticsByDate(AccountDyDataStatisticsReqVO reqVO) {
        return success(accountDyDataStatisticsService.getStatisticsByDate(reqVO));
    }

    @GetMapping("/getStatisticsForAnalyze")
    @Operation(summary = "账号数据分析")
    @PreAuthorize("@mss.hasPermission('cxlaike:account-dy-data-statistics:query')")
    @Parameters({
            @Parameter(name = "dateType", description = "时间筛选 1-昨天 7-近7天 30-近30天 60-近60天", example = "1"),
            @Parameter(name = "platformType", description = "平台类型 1-抖音 7-快手", example = "1"),
            @Parameter(name = "orderBy", description = "排序字段 1-新增内容发布数 2-新增播放 3-总粉丝数 4-总播放量 5-总发布数", example = "1"),
            @Parameter(name = "isAsc", description = "是否升序 是-true 否-false", example = "true")
    })
    public CommonResult<List<AccountDyDataStatisticsAnalyzeRespVO>> getStatisticsForAnalyze(Integer dateType,Integer platformType,Integer orderBy,Boolean isAsc) {
        return success(accountDyDataStatisticsService.getStatisticsForAnalyze(dateType, platformType, orderBy,isAsc));
    }

    @GetMapping("/getStatisticsForPage")
    @Operation(summary = "账号数据分析分页")
    @PreAuthorize("@mss.hasPermission('cxlaike:account-dy-data-statistics:query')")
    public CommonResult<PageResult<AccountDyDataStatisticsAnalyzeRespVO>> getStatisticsForPage(AccountDyDataStatisticsPageReqVO accountDyDataStatisticsPageReqVO) {
        return success(accountDyDataStatisticsService.getStatisticsForPage(accountDyDataStatisticsPageReqVO));
    }


    @GetMapping("/getStatisticsForDownload")
    @Operation(summary = "视频数据分析-分页")
    @OperateLog(type = EXPORT)
    @PreAuthorize("@mss.hasPermission('cxlaike:account-dy-data-statistics:query')")
    public void getStatisticsForDownload(AccountDyDataStatisticsPageReqVO accountDyDataStatisticsPageReqVO,
                                         HttpServletResponse response) throws Exception{
        accountDyDataStatisticsPageReqVO.setPageNo(1);//service方法里会减一
        accountDyDataStatisticsPageReqVO.setPageSize(1000);
        PageResult<AccountDyDataStatisticsAnalyzeRespVO> page = accountDyDataStatisticsService.getStatisticsForPage(accountDyDataStatisticsPageReqVO);
        List<AccountDyDataStatisticsAnalyzeRespVO> list = page.getList();
        List<AccountDyDataStatisticsExcelVO> excelList = AccountDyDataStatisticsConvert.INSTANCE.convertList02(list);
        ExcelUtils.write(response, "账号数据分析.xls", "数据", AccountDyDataStatisticsExcelVO.class, excelList);
    }

}
