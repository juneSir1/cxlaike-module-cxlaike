package net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.taskmaterialcorrelation;

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

import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.taskmaterialcorrelation.vo.*;
import net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.taskmaterialcorrelation.TaskMaterialCorrelationDO;
import net.hzxzkj.cxlaike.module.cxlaike.convert.taskmaterialcorrelation.TaskMaterialCorrelationConvert;
import net.hzxzkj.cxlaike.module.cxlaike.service.taskmaterialcorrelation.TaskMaterialCorrelationService;

@Tag(name = "管理后台 - 商家任务与素材关联")
@RestController
@RequestMapping("/cxlaike/task-material-correlation")
@Validated
public class TaskMaterialCorrelationController {

    @Resource
    private TaskMaterialCorrelationService taskMaterialCorrelationService;

    @PostMapping("/create")
    @Operation(summary = "创建商家任务与素材关联")
    @PreAuthorize("@ss.hasPermission('cxlaike:task-material-correlation:create')")
    public CommonResult<Long> createTaskMaterialCorrelation(@Valid @RequestBody TaskMaterialCorrelationCreateReqVO createReqVO) {
        return success(taskMaterialCorrelationService.createTaskMaterialCorrelation(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新商家任务与素材关联")
    @PreAuthorize("@ss.hasPermission('cxlaike:task-material-correlation:update')")
    public CommonResult<Boolean> updateTaskMaterialCorrelation(@Valid @RequestBody TaskMaterialCorrelationUpdateReqVO updateReqVO) {
        taskMaterialCorrelationService.updateTaskMaterialCorrelation(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除商家任务与素材关联")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('cxlaike:task-material-correlation:delete')")
    public CommonResult<Boolean> deleteTaskMaterialCorrelation(@RequestParam("id") Long id) {
        taskMaterialCorrelationService.deleteTaskMaterialCorrelation(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得商家任务与素材关联")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('cxlaike:task-material-correlation:query')")
    public CommonResult<TaskMaterialCorrelationRespVO> getTaskMaterialCorrelation(@RequestParam("id") Long id) {
        TaskMaterialCorrelationDO taskMaterialCorrelation = taskMaterialCorrelationService.getTaskMaterialCorrelation(id);
        return success(TaskMaterialCorrelationConvert.INSTANCE.convert(taskMaterialCorrelation));
    }

    @GetMapping("/list")
    @Operation(summary = "获得商家任务与素材关联列表")
    @Parameter(name = "ids", description = "编号列表", required = true, example = "1024,2048")
    @PreAuthorize("@ss.hasPermission('cxlaike:task-material-correlation:query')")
    public CommonResult<List<TaskMaterialCorrelationRespVO>> getTaskMaterialCorrelationList(@RequestParam("ids") Collection<Long> ids) {
        List<TaskMaterialCorrelationDO> list = taskMaterialCorrelationService.getTaskMaterialCorrelationList(ids);
        return success(TaskMaterialCorrelationConvert.INSTANCE.convertList(list));
    }

    @GetMapping("/page")
    @Operation(summary = "获得商家任务与素材关联分页")
    @PreAuthorize("@ss.hasPermission('cxlaike:task-material-correlation:query')")
    public CommonResult<PageResult<TaskMaterialCorrelationRespVO>> getTaskMaterialCorrelationPage(@Valid TaskMaterialCorrelationPageReqVO pageVO) {
        PageResult<TaskMaterialCorrelationDO> pageResult = taskMaterialCorrelationService.getTaskMaterialCorrelationPage(pageVO);
        return success(TaskMaterialCorrelationConvert.INSTANCE.convertPage(pageResult));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出商家任务与素材关联 Excel")
    @PreAuthorize("@ss.hasPermission('cxlaike:task-material-correlation:export')")
    @OperateLog(type = EXPORT)
    public void exportTaskMaterialCorrelationExcel(@Valid TaskMaterialCorrelationExportReqVO exportReqVO,
              HttpServletResponse response) throws IOException {
        List<TaskMaterialCorrelationDO> list = taskMaterialCorrelationService.getTaskMaterialCorrelationList(exportReqVO);
        // 导出 Excel
        List<TaskMaterialCorrelationExcelVO> datas = TaskMaterialCorrelationConvert.INSTANCE.convertList02(list);
        ExcelUtils.write(response, "商家任务与素材关联.xls", "数据", TaskMaterialCorrelationExcelVO.class, datas);
    }

}
