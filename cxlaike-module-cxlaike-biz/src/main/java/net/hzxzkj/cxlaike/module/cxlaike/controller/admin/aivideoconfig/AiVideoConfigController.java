package net.hzxzkj.cxlaike.module.cxlaike.controller.admin.aivideoconfig;

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

import net.hzxzkj.cxlaike.module.cxlaike.controller.admin.aivideoconfig.vo.*;
import net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.aivideoconfig.AiVideoConfigDO;
import net.hzxzkj.cxlaike.module.cxlaike.convert.aivideoconfig.AiVideoConfigConvert;
import net.hzxzkj.cxlaike.module.cxlaike.service.aivideoconfig.AiVideoConfigService;

@Tag(name = "管理后台 - ai视频配置")
@RestController
@RequestMapping("/cxlaike/ai-video-config")
@Validated
public class AiVideoConfigController {

    @Resource
    private AiVideoConfigService aiVideoConfigService;

    @PostMapping("/create")
    @Operation(summary = "创建ai视频配置")
    @PreAuthorize("@ss.hasPermission('cxlaike:ai-video-config:create')")
    public CommonResult<Long> createAiVideoConfig(@Valid @RequestBody AiVideoConfigCreateReqVO createReqVO) {
        return success(aiVideoConfigService.createAiVideoConfig(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新ai视频配置")
    @PreAuthorize("@ss.hasPermission('cxlaike:ai-video-config:update')")
    public CommonResult<Boolean> updateAiVideoConfig(@Valid @RequestBody AiVideoConfigUpdateReqVO updateReqVO) {
        aiVideoConfigService.updateAiVideoConfig(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除ai视频配置")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('cxlaike:ai-video-config:delete')")
    public CommonResult<Boolean> deleteAiVideoConfig(@RequestParam("id") Long id) {
        aiVideoConfigService.deleteAiVideoConfig(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得ai视频配置")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('cxlaike:ai-video-config:query')")
    public CommonResult<AiVideoConfigRespVO> getAiVideoConfig(@RequestParam("id") Long id) {
        AiVideoConfigDO aiVideoConfig = aiVideoConfigService.getAiVideoConfig(id);
        return success(AiVideoConfigConvert.INSTANCE.convert(aiVideoConfig));
    }

    @GetMapping("/list")
    @Operation(summary = "获得ai视频配置列表")
    @Parameter(name = "ids", description = "编号列表", required = true, example = "1024,2048")
    @PreAuthorize("@ss.hasPermission('cxlaike:ai-video-config:query')")
    public CommonResult<List<AiVideoConfigRespVO>> getAiVideoConfigList(@RequestParam("ids") Collection<Long> ids) {
        List<AiVideoConfigDO> list = aiVideoConfigService.getAiVideoConfigList(ids);
        return success(AiVideoConfigConvert.INSTANCE.convertList(list));
    }

    @GetMapping("/page")
    @Operation(summary = "获得ai视频配置分页")
    @PreAuthorize("@ss.hasPermission('cxlaike:ai-video-config:query')")
    public CommonResult<PageResult<AiVideoConfigRespVO>> getAiVideoConfigPage(@Valid AiVideoConfigPageReqVO pageVO) {
        PageResult<AiVideoConfigDO> pageResult = aiVideoConfigService.getAiVideoConfigPage(pageVO);
        return success(AiVideoConfigConvert.INSTANCE.convertPage(pageResult));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出ai视频配置 Excel")
    @PreAuthorize("@ss.hasPermission('cxlaike:ai-video-config:export')")
    @OperateLog(type = EXPORT)
    public void exportAiVideoConfigExcel(@Valid AiVideoConfigExportReqVO exportReqVO,
              HttpServletResponse response) throws IOException {
        List<AiVideoConfigDO> list = aiVideoConfigService.getAiVideoConfigList(exportReqVO);
        // 导出 Excel
        List<AiVideoConfigExcelVO> datas = AiVideoConfigConvert.INSTANCE.convertList02(list);
        ExcelUtils.write(response, "ai视频配置.xls", "数据", AiVideoConfigExcelVO.class, datas);
    }

}
