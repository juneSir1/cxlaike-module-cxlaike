package net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.aivideotaskcover;

import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.aivideotaskcover.vo.*;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Operation;

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

import net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.aivideotaskcover.AiVideoTaskCoverDO;
import net.hzxzkj.cxlaike.module.cxlaike.convert.aivideotaskcover.AiVideoTaskCoverConvert;
import net.hzxzkj.cxlaike.module.cxlaike.service.aivideotaskcover.AiVideoTaskCoverService;

@Tag(name = "管理后台 - ai素材混剪视频封面图片关联")
@RestController
@RequestMapping("/cxlaike/ai-video-task-cover")
@Validated
public class AiVideoTaskCoverController {

    @Resource
    private AiVideoTaskCoverService aiVideoTaskCoverService;

    @PostMapping("/create")
    @Operation(summary = "创建ai素材混剪视频封面图片关联")
    @PreAuthorize("@ss.hasPermission('cxlaike:ai-video-task-cover:create')")
    public CommonResult<Long> createAiVideoTaskCover(@Valid @RequestBody AiVideoTaskCoverCreateReqVO createReqVO) {
        return success(aiVideoTaskCoverService.createAiVideoTaskCover(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新ai素材混剪视频封面图片关联")
    @PreAuthorize("@ss.hasPermission('cxlaike:ai-video-task-cover:update')")
    public CommonResult<Boolean> updateAiVideoTaskCover(@Valid @RequestBody AiVideoTaskCoverUpdateReqVO updateReqVO) {
        aiVideoTaskCoverService.updateAiVideoTaskCover(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除ai素材混剪视频封面图片关联")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('cxlaike:ai-video-task-cover:delete')")
    public CommonResult<Boolean> deleteAiVideoTaskCover(@RequestParam("id") Long id) {
        aiVideoTaskCoverService.deleteAiVideoTaskCover(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得ai素材混剪视频封面图片关联")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('cxlaike:ai-video-task-cover:query')")
    public CommonResult<AiVideoTaskCoverRespVO> getAiVideoTaskCover(@RequestParam("id") Long id) {
        AiVideoTaskCoverDO aiVideoTaskCover = aiVideoTaskCoverService.getAiVideoTaskCover(id);
        return success(AiVideoTaskCoverConvert.INSTANCE.convert(aiVideoTaskCover));
    }

    @GetMapping("/list")
    @Operation(summary = "获得ai素材混剪视频封面图片关联列表")
    @Parameter(name = "ids", description = "编号列表", required = true, example = "1024,2048")
    @PreAuthorize("@ss.hasPermission('cxlaike:ai-video-task-cover:query')")
    public CommonResult<List<AiVideoTaskCoverRespVO>> getAiVideoTaskCoverList(@RequestParam("ids") Collection<Long> ids) {
        List<AiVideoTaskCoverDO> list = aiVideoTaskCoverService.getAiVideoTaskCoverList(ids);
        return success(AiVideoTaskCoverConvert.INSTANCE.convertList(list));
    }

    @GetMapping("/page")
    @Operation(summary = "获得ai素材混剪视频封面图片关联分页")
    @PreAuthorize("@ss.hasPermission('cxlaike:ai-video-task-cover:query')")
    public CommonResult<PageResult<AiVideoTaskCoverRespVO>> getAiVideoTaskCoverPage(@Valid AiVideoTaskCoverPageReqVO pageVO) {
        PageResult<AiVideoTaskCoverDO> pageResult = aiVideoTaskCoverService.getAiVideoTaskCoverPage(pageVO);
        return success(AiVideoTaskCoverConvert.INSTANCE.convertPage(pageResult));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出ai素材混剪视频封面图片关联 Excel")
    @PreAuthorize("@ss.hasPermission('cxlaike:ai-video-task-cover:export')")
    @OperateLog(type = EXPORT)
    public void exportAiVideoTaskCoverExcel(@Valid AiVideoTaskCoverExportReqVO exportReqVO,
              HttpServletResponse response) throws IOException {
        List<AiVideoTaskCoverDO> list = aiVideoTaskCoverService.getAiVideoTaskCoverList(exportReqVO);
        // 导出 Excel
        List<AiVideoTaskCoverExcelVO> datas = AiVideoTaskCoverConvert.INSTANCE.convertList02(list);
        ExcelUtils.write(response, "ai素材混剪视频封面图片关联.xls", "数据", AiVideoTaskCoverExcelVO.class, datas);
    }

}
