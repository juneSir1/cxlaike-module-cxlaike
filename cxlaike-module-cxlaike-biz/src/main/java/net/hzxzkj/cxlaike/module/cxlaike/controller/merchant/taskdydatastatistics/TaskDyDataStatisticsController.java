package net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.taskdydatastatistics;

import io.swagger.v3.oas.annotations.Parameters;
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

import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.taskdydatastatistics.vo.*;
import net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.taskdydatastatistics.TaskDyDataStatisticsDO;
import net.hzxzkj.cxlaike.module.cxlaike.convert.taskdydatastatistics.TaskDyDataStatisticsConvert;
import net.hzxzkj.cxlaike.module.cxlaike.service.taskdydatastatistics.TaskDyDataStatisticsService;

@Tag(name = "管理后台 - 任务纬度抖音数据统计")
@RestController
@RequestMapping("/cxlaike/task-dy-data-statistics")
@Validated
public class TaskDyDataStatisticsController {

    @Resource
    private TaskDyDataStatisticsService taskDyDataStatisticsService;

//    @PostMapping("/create")
//    @Operation(summary = "创建任务纬度抖音数据统计")
//    @PreAuthorize("@ss.hasPermission('cxlaike:task-dy-data-statistics:create')")
//    public CommonResult<Long> createTaskDyDataStatistics(@Valid @RequestBody TaskDyDataStatisticsCreateReqVO createReqVO) {
//        return success(taskDyDataStatisticsService.createTaskDyDataStatistics(createReqVO));
//    }
//
//    @PutMapping("/update")
//    @Operation(summary = "更新任务纬度抖音数据统计")
//    @PreAuthorize("@ss.hasPermission('cxlaike:task-dy-data-statistics:update')")
//    public CommonResult<Boolean> updateTaskDyDataStatistics(@Valid @RequestBody TaskDyDataStatisticsUpdateReqVO updateReqVO) {
//        taskDyDataStatisticsService.updateTaskDyDataStatistics(updateReqVO);
//        return success(true);
//    }
//
//    @DeleteMapping("/delete")
//    @Operation(summary = "删除任务纬度抖音数据统计")
//    @Parameter(name = "id", description = "编号", required = true)
//    @PreAuthorize("@ss.hasPermission('cxlaike:task-dy-data-statistics:delete')")
//    public CommonResult<Boolean> deleteTaskDyDataStatistics(@RequestParam("id") Long id) {
//        taskDyDataStatisticsService.deleteTaskDyDataStatistics(id);
//        return success(true);
//    }
//
//    @GetMapping("/get")
//    @Operation(summary = "获得任务纬度抖音数据统计")
//    @Parameter(name = "id", description = "编号", required = true, example = "1024")
//    @PreAuthorize("@ss.hasPermission('cxlaike:task-dy-data-statistics:query')")
//    public CommonResult<TaskDyDataStatisticsRespVO> getTaskDyDataStatistics(@RequestParam("id") Long id) {
//        TaskDyDataStatisticsDO taskDyDataStatistics = taskDyDataStatisticsService.getTaskDyDataStatistics(id);
//        return success(TaskDyDataStatisticsConvert.INSTANCE.convert(taskDyDataStatistics));
//    }
//
//    @GetMapping("/list")
//    @Operation(summary = "获得任务纬度抖音数据统计列表")
//    @Parameter(name = "ids", description = "编号列表", required = true, example = "1024,2048")
//    @PreAuthorize("@ss.hasPermission('cxlaike:task-dy-data-statistics:query')")
//    public CommonResult<List<TaskDyDataStatisticsRespVO>> getTaskDyDataStatisticsList(@RequestParam("ids") Collection<Long> ids) {
//        List<TaskDyDataStatisticsDO> list = taskDyDataStatisticsService.getTaskDyDataStatisticsList(ids);
//        return success(TaskDyDataStatisticsConvert.INSTANCE.convertList(list));
//    }
//
//    @GetMapping("/page")
//    @Operation(summary = "获得任务纬度抖音数据统计分页")
//    @PreAuthorize("@ss.hasPermission('cxlaike:task-dy-data-statistics:query')")
//    public CommonResult<PageResult<TaskDyDataStatisticsRespVO>> getTaskDyDataStatisticsPage(@Valid TaskDyDataStatisticsPageReqVO pageVO) {
//        PageResult<TaskDyDataStatisticsDO> pageResult = taskDyDataStatisticsService.getTaskDyDataStatisticsPage(pageVO);
//        return success(TaskDyDataStatisticsConvert.INSTANCE.convertPage(pageResult));
//    }
//
//    @GetMapping("/export-excel")
//    @Operation(summary = "导出任务纬度抖音数据统计 Excel")
//    @PreAuthorize("@ss.hasPermission('cxlaike:task-dy-data-statistics:export')")
//    @OperateLog(type = EXPORT)
//    public void exportTaskDyDataStatisticsExcel(@Valid TaskDyDataStatisticsExportReqVO exportReqVO,
//              HttpServletResponse response) throws IOException {
//        List<TaskDyDataStatisticsDO> list = taskDyDataStatisticsService.getTaskDyDataStatisticsList(exportReqVO);
//        // 导出 Excel
//        List<TaskDyDataStatisticsExcelVO> datas = TaskDyDataStatisticsConvert.INSTANCE.convertList02(list);
//        ExcelUtils.write(response, "任务纬度抖音数据统计.xls", "数据", TaskDyDataStatisticsExcelVO.class, datas);
//    }

    @GetMapping("/getStatisticsForAnalyze")
    @Operation(summary = "任务数据分析")
    @PreAuthorize("@mss.hasPermission('cxlaike:task-dy-data-statistics:query')")
    @Parameters({
            @Parameter(name = "dateType", description = "时间筛选 1-昨天 7-近7天 30-近30天 60-近60天", example = "1"),
            @Parameter(name = "orderBy", description = "排序字段 1-新增内容发布数 2-新增播放 3-总粉丝数 4-总播放量 5-总发布数", example = "1"),
            @Parameter(name = "platformType", description = "平台类型 1-抖音 2-快手", example = "1"),
            @Parameter(name = "isAsc", description = "是否升序 是-true 否-false", example = "true")
    })
    public CommonResult<List<TaskDyDataStatisticsAnalyzeRespVO>> getStatisticsForAnalyze(Integer dateType,Integer platformType,Integer orderBy,Boolean isAsc) {
        return success(taskDyDataStatisticsService.getStatisticsForAnalyze(dateType,platformType, orderBy,isAsc));
    }

}
