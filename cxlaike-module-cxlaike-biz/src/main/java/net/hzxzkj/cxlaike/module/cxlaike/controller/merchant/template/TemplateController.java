package net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.template;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import net.hzxzkj.cxlaike.framework.common.pojo.CommonResult;
import net.hzxzkj.cxlaike.framework.common.pojo.PageResult;
import net.hzxzkj.cxlaike.framework.excel.core.util.ExcelUtils;
import net.hzxzkj.cxlaike.framework.operatelog.core.annotations.OperateLog;
import net.hzxzkj.cxlaike.framework.tenant.core.aop.TenantIgnore;
import net.hzxzkj.cxlaike.framework.tenant.core.context.TenantContextHolder;
import net.hzxzkj.cxlaike.framework.web.core.util.WebFrameworkUtils;
import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.template.vo.*;
import net.hzxzkj.cxlaike.module.cxlaike.convert.template.TemplateConvert;
import net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.template.TemplateDO;
import net.hzxzkj.cxlaike.module.cxlaike.service.template.TemplateService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.Collection;
import java.util.List;

import static net.hzxzkj.cxlaike.framework.common.pojo.CommonResult.success;
import static net.hzxzkj.cxlaike.framework.operatelog.core.enums.OperateTypeEnum.EXPORT;

@Tag(name = "商家后台 - ai素材混剪视频模板")
@RestController
@RequestMapping("/cxlaike/template")
@Validated
public class TemplateController {

    @Resource
    private TemplateService templateService;

    @PostMapping("/create")
    @Operation(summary = "创建ai素材混剪视频模板")
    @PreAuthorize("@mss.hasPermission('cxlaike:template:delete')")
    public CommonResult<Long> createTemplate(@Valid @RequestBody TemplateCreateReqVO createReqVO) {
        return success(templateService.createTemplate(createReqVO));
    }

    @PostMapping("/update")
    @Operation(summary = "更新ai素材混剪视频模板")
    @PreAuthorize("@mss.hasPermission('cxlaike:template:delete')")
    public CommonResult<Boolean> updateTemplate(@Valid @RequestBody TemplateUpdateReqVO updateReqVO) {
        templateService.updateTemplate(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除ai素材混剪视频模板")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@mss.hasPermission('cxlaike:template:delete')")
    public CommonResult<Boolean> deleteTemplate(@RequestParam("id") Long id) {
        templateService.deleteTemplate(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得ai素材混剪视频模板")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@mss.hasPermission('cxlaike:template:delete')")
    public CommonResult<TemplateRespVO> getTemplate(@RequestParam("id") Long id) {
        TemplateDO template = templateService.getTemplate(id);
        return success(TemplateConvert.INSTANCE.convert(template));
    }

    @GetMapping("/page")
    @Operation(summary = "获得ai素材混剪视频模板分页")
    @PreAuthorize("@mss.hasPermission('cxlaike:template:delete')")
    @TenantIgnore
    public CommonResult<PageResult<TemplateRespVO>> getTemplatePage(@Valid TemplatePageReqVO pageVO) {
        PageResult<TemplateDO> pageResult = templateService.getTemplatePage(pageVO);
        return success(TemplateConvert.INSTANCE.convertPage(pageResult));
    }
}
