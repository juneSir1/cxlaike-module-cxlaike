package net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.aivideotaskfontset;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import net.hzxzkj.cxlaike.framework.common.pojo.CommonResult;
import net.hzxzkj.cxlaike.framework.common.pojo.PageResult;
import net.hzxzkj.cxlaike.framework.excel.core.util.ExcelUtils;
import net.hzxzkj.cxlaike.framework.operatelog.core.annotations.OperateLog;
import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.aivideotaskfontset.vo.*;
import net.hzxzkj.cxlaike.module.cxlaike.convert.aivideotaskfontset.AiVideoTaskFontSetConvert;
import net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.aivideotaskfontset.AiVideoTaskFontSetDO;
import net.hzxzkj.cxlaike.module.cxlaike.service.aivideotaskfontset.AiVideoTaskFontSetService;
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

@Tag(name = "管理后台 - ai视频任务-文字设置")
@RestController
@RequestMapping("/cxlaike/ai-video-task-font-set")
@Validated
public class AiVideoTaskFontSetController {

    @Resource
    private AiVideoTaskFontSetService aiVideoTaskFontSetService;

    @PostMapping("/create")
    @Operation(summary = "创建ai视频任务-文字设置")
    @PreAuthorize("@ss.hasPermission('cxlaike:ai-video-task-font-set:create')")
    public CommonResult<Long> createAiVideoTaskFontSet(@Valid @RequestBody AiVideoTaskFontSetCreateReqVO createReqVO) {
        return success(aiVideoTaskFontSetService.createAiVideoTaskFontSet(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新ai视频任务-文字设置")
    @PreAuthorize("@ss.hasPermission('cxlaike:ai-video-task-font-set:update')")
    public CommonResult<Boolean> updateAiVideoTaskFontSet(@Valid @RequestBody AiVideoTaskFontSetUpdateReqVO updateReqVO) {
        aiVideoTaskFontSetService.updateAiVideoTaskFontSet(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除ai视频任务-文字设置")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('cxlaike:ai-video-task-font-set:delete')")
    public CommonResult<Boolean> deleteAiVideoTaskFontSet(@RequestParam("id") Long id) {
        aiVideoTaskFontSetService.deleteAiVideoTaskFontSet(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得ai视频任务-文字设置")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('cxlaike:ai-video-task-font-set:query')")
    public CommonResult<AiVideoTaskFontSetRespVO> getAiVideoTaskFontSet(@RequestParam("id") Long id) {
        AiVideoTaskFontSetDO aiVideoTaskFontSet = aiVideoTaskFontSetService.getAiVideoTaskFontSet(id);
        return success(AiVideoTaskFontSetConvert.INSTANCE.convert(aiVideoTaskFontSet));
    }

    @GetMapping("/list")
    @Operation(summary = "获得ai视频任务-文字设置列表")
    @Parameter(name = "ids", description = "编号列表", required = true, example = "1024,2048")
    @PreAuthorize("@ss.hasPermission('cxlaike:ai-video-task-font-set:query')")
    public CommonResult<List<AiVideoTaskFontSetRespVO>> getAiVideoTaskFontSetList(@RequestParam("ids") Collection<Long> ids) {
        List<AiVideoTaskFontSetDO> list = aiVideoTaskFontSetService.getAiVideoTaskFontSetList(ids);
        return success(AiVideoTaskFontSetConvert.INSTANCE.convertList(list));
    }

    @GetMapping("/page")
    @Operation(summary = "获得ai视频任务-文字设置分页")
    @PreAuthorize("@ss.hasPermission('cxlaike:ai-video-task-font-set:query')")
    public CommonResult<PageResult<AiVideoTaskFontSetRespVO>> getAiVideoTaskFontSetPage(@Valid AiVideoTaskFontSetPageReqVO pageVO) {
        PageResult<AiVideoTaskFontSetDO> pageResult = aiVideoTaskFontSetService.getAiVideoTaskFontSetPage(pageVO);
        return success(AiVideoTaskFontSetConvert.INSTANCE.convertPage(pageResult));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出ai视频任务-文字设置 Excel")
    @PreAuthorize("@ss.hasPermission('cxlaike:ai-video-task-font-set:export')")
    @OperateLog(type = EXPORT)
    public void exportAiVideoTaskFontSetExcel(@Valid AiVideoTaskFontSetExportReqVO exportReqVO,
              HttpServletResponse response) throws IOException {
        List<AiVideoTaskFontSetDO> list = aiVideoTaskFontSetService.getAiVideoTaskFontSetList(exportReqVO);
        // 导出 Excel
        List<AiVideoTaskFontSetExcelVO> datas = AiVideoTaskFontSetConvert.INSTANCE.convertList02(list);
        ExcelUtils.write(response, "ai视频任务-文字设置.xls", "数据", AiVideoTaskFontSetExcelVO.class, datas);
    }

}
