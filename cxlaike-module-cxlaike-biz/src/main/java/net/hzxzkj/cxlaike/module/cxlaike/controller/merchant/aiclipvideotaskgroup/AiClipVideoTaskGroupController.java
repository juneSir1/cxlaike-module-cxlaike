package net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.aiclipvideotaskgroup;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import net.hzxzkj.cxlaike.framework.common.pojo.CommonResult;
import net.hzxzkj.cxlaike.framework.common.pojo.PageResult;
import net.hzxzkj.cxlaike.framework.excel.core.util.ExcelUtils;
import net.hzxzkj.cxlaike.framework.operatelog.core.annotations.OperateLog;
import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.aiclipvideotaskgroup.vo.*;
import net.hzxzkj.cxlaike.module.cxlaike.convert.aiclipvideotaskgroup.AiClipVideoTaskGroupConvert;
import net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.aiclipvideotaskgroup.AiClipVideoTaskGroupDO;
import net.hzxzkj.cxlaike.module.cxlaike.service.aiclipvideotaskgroup.AiClipVideoTaskGroupService;
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

@Tag(name = "管理后台 - ai素材混剪视频任务-视频组设置")
@RestController
@RequestMapping("/cxlaike/ai-clip-video-task-group")
@Validated
public class AiClipVideoTaskGroupController {

    @Resource
    private AiClipVideoTaskGroupService aiClipVideoTaskGroupService;

    @PostMapping("/create")
    @Operation(summary = "创建ai素材混剪视频任务-视频组设置")
    @PreAuthorize("@ss.hasPermission('cxlaike:ai-clip-video-task-group:create')")
    public CommonResult<Long> createAiClipVideoTaskGroup(@Valid @RequestBody AiClipVideoTaskGroupCreateReqVO createReqVO) {
        return success(aiClipVideoTaskGroupService.createAiClipVideoTaskGroup(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新ai素材混剪视频任务-视频组设置")
    @PreAuthorize("@ss.hasPermission('cxlaike:ai-clip-video-task-group:update')")
    public CommonResult<Boolean> updateAiClipVideoTaskGroup(@Valid @RequestBody AiClipVideoTaskGroupUpdateReqVO updateReqVO) {
        aiClipVideoTaskGroupService.updateAiClipVideoTaskGroup(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除ai素材混剪视频任务-视频组设置")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('cxlaike:ai-clip-video-task-group:delete')")
    public CommonResult<Boolean> deleteAiClipVideoTaskGroup(@RequestParam("id") Long id) {
        aiClipVideoTaskGroupService.deleteAiClipVideoTaskGroup(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得ai素材混剪视频任务-视频组设置")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('cxlaike:ai-clip-video-task-group:query')")
    public CommonResult<AiClipVideoTaskGroupRespVO> getAiClipVideoTaskGroup(@RequestParam("id") Long id) {
        AiClipVideoTaskGroupDO aiClipVideoTaskGroup = aiClipVideoTaskGroupService.getAiClipVideoTaskGroup(id);
        return success(AiClipVideoTaskGroupConvert.INSTANCE.convert(aiClipVideoTaskGroup));
    }

    @GetMapping("/list")
    @Operation(summary = "获得ai素材混剪视频任务-视频组设置列表")
    @Parameter(name = "ids", description = "编号列表", required = true, example = "1024,2048")
    @PreAuthorize("@ss.hasPermission('cxlaike:ai-clip-video-task-group:query')")
    public CommonResult<List<AiClipVideoTaskGroupRespVO>> getAiClipVideoTaskGroupList(@RequestParam("ids") Collection<Long> ids) {
        List<AiClipVideoTaskGroupDO> list = aiClipVideoTaskGroupService.getAiClipVideoTaskGroupList(ids);
        return success(AiClipVideoTaskGroupConvert.INSTANCE.convertList(list));
    }

    @GetMapping("/page")
    @Operation(summary = "获得ai素材混剪视频任务-视频组设置分页")
    @PreAuthorize("@ss.hasPermission('cxlaike:ai-clip-video-task-group:query')")
    public CommonResult<PageResult<AiClipVideoTaskGroupRespVO>> getAiClipVideoTaskGroupPage(@Valid AiClipVideoTaskGroupPageReqVO pageVO) {
        PageResult<AiClipVideoTaskGroupDO> pageResult = aiClipVideoTaskGroupService.getAiClipVideoTaskGroupPage(pageVO);
        return success(AiClipVideoTaskGroupConvert.INSTANCE.convertPage(pageResult));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出ai素材混剪视频任务-视频组设置 Excel")
    @PreAuthorize("@ss.hasPermission('cxlaike:ai-clip-video-task-group:export')")
    @OperateLog(type = EXPORT)
    public void exportAiClipVideoTaskGroupExcel(@Valid AiClipVideoTaskGroupExportReqVO exportReqVO,
              HttpServletResponse response) throws IOException {
        List<AiClipVideoTaskGroupDO> list = aiClipVideoTaskGroupService.getAiClipVideoTaskGroupList(exportReqVO);
        // 导出 Excel
        List<AiClipVideoTaskGroupExcelVO> datas = AiClipVideoTaskGroupConvert.INSTANCE.convertList02(list);
        ExcelUtils.write(response, "ai素材混剪视频任务-视频组设置.xls", "数据", AiClipVideoTaskGroupExcelVO.class, datas);
    }

}
