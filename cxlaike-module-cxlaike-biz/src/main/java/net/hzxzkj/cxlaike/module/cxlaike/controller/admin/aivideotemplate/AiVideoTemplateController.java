package net.hzxzkj.cxlaike.module.cxlaike.controller.admin.aivideotemplate;

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

import net.hzxzkj.cxlaike.module.cxlaike.controller.admin.aivideotemplate.vo.*;
import net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.aivideotemplate.AiVideoTemplateDO;
import net.hzxzkj.cxlaike.module.cxlaike.convert.aivideotemplate.AiVideoTemplateConvert;
import net.hzxzkj.cxlaike.module.cxlaike.service.aivideotemplate.AiVideoTemplateService;

@Tag(name = "管理后台 - ai视频模板")
@RestController
@RequestMapping("/cxlaike/ai-video-template")
@Validated
public class AiVideoTemplateController {

    @Resource
    private AiVideoTemplateService aiVideoTemplateService;

    @PostMapping("/create")
    @Operation(summary = "创建ai视频模板")
    @PreAuthorize("@ss.hasPermission('cxlaike:ai-video-template:create')")
    public CommonResult<Long> createAiVideoTemplate(@Valid @RequestBody AiVideoTemplateCreateReqVO createReqVO) {
        return success(aiVideoTemplateService.createAiVideoTemplate(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新ai视频模板")
    @PreAuthorize("@ss.hasPermission('cxlaike:ai-video-template:update')")
    public CommonResult<Boolean> updateAiVideoTemplate(@Valid @RequestBody AiVideoTemplateUpdateReqVO updateReqVO) {
        aiVideoTemplateService.updateAiVideoTemplate(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除ai视频模板")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('cxlaike:ai-video-template:delete')")
    public CommonResult<Boolean> deleteAiVideoTemplate(@RequestParam("id") Long id) {
        aiVideoTemplateService.deleteAiVideoTemplate(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得ai视频模板")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('cxlaike:ai-video-template:query')")
    public CommonResult<AiVideoTemplateRespVO> getAiVideoTemplate(@RequestParam("id") Long id) {
        AiVideoTemplateDO aiVideoTemplate = aiVideoTemplateService.getAiVideoTemplate(id);
        return success(AiVideoTemplateConvert.INSTANCE.convert(aiVideoTemplate));
    }

    @GetMapping("/list")
    @Operation(summary = "获得ai视频模板列表")
    @Parameter(name = "ids", description = "编号列表", required = true, example = "1024,2048")
    @PreAuthorize("@ss.hasPermission('cxlaike:ai-video-template:query')")
    public CommonResult<List<AiVideoTemplateRespVO>> getAiVideoTemplateList(@RequestParam("ids") Collection<Long> ids) {
        List<AiVideoTemplateDO> list = aiVideoTemplateService.getAiVideoTemplateList(ids);
        return success(AiVideoTemplateConvert.INSTANCE.convertList(list));
    }

    @GetMapping("/page")
    @Operation(summary = "获得ai视频模板分页")
    @PreAuthorize("@ss.hasPermission('cxlaike:ai-video-template:query')")
    public CommonResult<PageResult<AiVideoTemplateRespVO>> getAiVideoTemplatePage(@Valid AiVideoTemplatePageReqVO pageVO) {
        PageResult<AiVideoTemplateDO> pageResult = aiVideoTemplateService.getAiVideoTemplatePage(pageVO);
        return success(AiVideoTemplateConvert.INSTANCE.convertPage(pageResult));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出ai视频模板 Excel")
    @PreAuthorize("@ss.hasPermission('cxlaike:ai-video-template:export')")
    @OperateLog(type = EXPORT)
    public void exportAiVideoTemplateExcel(@Valid AiVideoTemplateExportReqVO exportReqVO,
              HttpServletResponse response) throws IOException {
        List<AiVideoTemplateDO> list = aiVideoTemplateService.getAiVideoTemplateList(exportReqVO);
        // 导出 Excel
        List<AiVideoTemplateExcelVO> datas = AiVideoTemplateConvert.INSTANCE.convertList02(list);
        ExcelUtils.write(response, "ai视频模板.xls", "数据", AiVideoTemplateExcelVO.class, datas);
    }

}
