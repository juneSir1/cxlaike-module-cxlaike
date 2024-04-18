package net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.contenttitlemanagement;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import net.hzxzkj.cxlaike.framework.common.pojo.CommonResult;
import net.hzxzkj.cxlaike.framework.common.pojo.PageResult;
import net.hzxzkj.cxlaike.framework.excel.core.util.ExcelUtils;
import net.hzxzkj.cxlaike.framework.operatelog.core.annotations.OperateLog;
import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.contenttitlemanagement.vo.*;
import net.hzxzkj.cxlaike.module.cxlaike.convert.contenttitlemanagement.ContentTitleManagementConvert;
import net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.contenttitlemanagement.ContentTitleManagementDO;
import net.hzxzkj.cxlaike.module.cxlaike.service.contenttitlemanagement.ContentTitleManagementService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.Collection;
import java.util.List;

import static net.hzxzkj.cxlaike.framework.common.pojo.CommonResult.success;
import static net.hzxzkj.cxlaike.framework.operatelog.core.enums.OperateTypeEnum.EXPORT;

@Tag(name = "管理后台 - 任务内容标题管理")
@RestController
@RequestMapping("/cxlaike/content-title-management")
@Validated
public class ContentTitleManagementController {

    @Resource
    private ContentTitleManagementService contentTitleManagementService;

    @PostMapping("/create")
    @Operation(summary = "创建任务内容标题管理")
    @PreAuthorize("@ss.hasPermission('cxlaike:content-title-management:create')")
    public CommonResult<Long> createContentTitleManagement(@Valid @RequestBody ContentTitleManagementCreateReqVO createReqVO) {
        return success(contentTitleManagementService.createContentTitleManagement(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新任务内容标题管理")
    @PreAuthorize("@ss.hasPermission('cxlaike:content-title-management:update')")
    public CommonResult<Boolean> updateContentTitleManagement(@Valid @RequestBody ContentTitleManagementUpdateReqVO updateReqVO) {
        contentTitleManagementService.updateContentTitleManagement(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除任务内容标题管理")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('cxlaike:content-title-management:delete')")
    public CommonResult<Boolean> deleteContentTitleManagement(@RequestParam("id") Long id) {
        contentTitleManagementService.deleteContentTitleManagement(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得任务内容标题管理")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('cxlaike:content-title-management:query')")
    public CommonResult<ContentTitleManagementRespVO> getContentTitleManagement(@RequestParam("id") Long id) {
        ContentTitleManagementDO contentTitleManagement = contentTitleManagementService.getContentTitleManagement(id);
        return success(ContentTitleManagementConvert.INSTANCE.convert(contentTitleManagement));
    }

    @GetMapping("/list")
    @Operation(summary = "获得任务内容标题管理列表")
    @Parameter(name = "ids", description = "编号列表", required = true, example = "1024,2048")
    @PreAuthorize("@ss.hasPermission('cxlaike:content-title-management:query')")
    public CommonResult<List<ContentTitleManagementRespVO>> getContentTitleManagementList(@RequestParam("ids") Collection<Long> ids) {
        List<ContentTitleManagementDO> list = contentTitleManagementService.getContentTitleManagementList(ids);
        return success(ContentTitleManagementConvert.INSTANCE.convertList(list));
    }

    @GetMapping("/page")
    @Operation(summary = "获得任务内容标题管理分页")
    @PreAuthorize("@ss.hasPermission('cxlaike:content-title-management:query')")
    public CommonResult<PageResult<ContentTitleManagementRespVO>> getContentTitleManagementPage(@Valid ContentTitleManagementPageReqVO pageVO) {
        PageResult<ContentTitleManagementDO> pageResult = contentTitleManagementService.getContentTitleManagementPage(pageVO);
        return success(ContentTitleManagementConvert.INSTANCE.convertPage(pageResult));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出任务内容标题管理 Excel")
    @PreAuthorize("@ss.hasPermission('cxlaike:content-title-management:export')")
    @OperateLog(type = EXPORT)
    public void exportContentTitleManagementExcel(@Valid ContentTitleManagementExportReqVO exportReqVO,
              HttpServletResponse response) throws IOException {
        List<ContentTitleManagementDO> list = contentTitleManagementService.getContentTitleManagementList(exportReqVO);
        // 导出 Excel
        List<ContentTitleManagementExcelVO> datas = ContentTitleManagementConvert.INSTANCE.convertList02(list);
        ExcelUtils.write(response, "任务内容标题管理.xls", "数据", ContentTitleManagementExcelVO.class, datas);
    }

}
