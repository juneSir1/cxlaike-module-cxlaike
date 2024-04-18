package net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.dypersonalletter;

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

import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.dypersonalletter.vo.*;
import net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.dypersonalletter.DyPersonalLetterDO;
import net.hzxzkj.cxlaike.module.cxlaike.convert.dypersonalletter.DyPersonalLetterConvert;
import net.hzxzkj.cxlaike.module.cxlaike.service.dypersonalletter.DyPersonalLetterService;

@Tag(name = "管理后台 - 抖音私信管理")
@RestController
@RequestMapping("/cxlaike/dy-personal-letter")
@Validated
public class DyPersonalLetterController {

    @Resource
    private DyPersonalLetterService dyPersonalLetterService;

//    @PostMapping("/create")
//    @Operation(summary = "创建抖音私信管理")
//    @PreAuthorize("@ss.hasPermission('cxlaike:dy-personal-letter:create')")
//    public CommonResult<Long> createDyPersonalLetter(@Valid @RequestBody DyPersonalLetterCreateReqVO createReqVO) {
//        return success(dyPersonalLetterService.createDyPersonalLetter(createReqVO));
//    }

//    @PutMapping("/update")
//    @Operation(summary = "更新抖音私信管理")
//    @PreAuthorize("@ss.hasPermission('cxlaike:dy-personal-letter:update')")
//    public CommonResult<Boolean> updateDyPersonalLetter(@Valid @RequestBody DyPersonalLetterUpdateReqVO updateReqVO) {
//        dyPersonalLetterService.updateDyPersonalLetter(updateReqVO);
//        return success(true);
//    }
//
//    @DeleteMapping("/delete")
//    @Operation(summary = "删除抖音私信管理")
//    @Parameter(name = "id", description = "编号", required = true)
//    @PreAuthorize("@ss.hasPermission('cxlaike:dy-personal-letter:delete')")
//    public CommonResult<Boolean> deleteDyPersonalLetter(@RequestParam("id") Long id) {
//        dyPersonalLetterService.deleteDyPersonalLetter(id);
//        return success(true);
//    }
//
//    @GetMapping("/get")
//    @Operation(summary = "获得抖音私信管理")
//    @Parameter(name = "id", description = "编号", required = true, example = "1024")
//    @PreAuthorize("@ss.hasPermission('cxlaike:dy-personal-letter:query')")
//    public CommonResult<DyPersonalLetterRespVO> getDyPersonalLetter(@RequestParam("id") Long id) {
//        DyPersonalLetterDO dyPersonalLetter = dyPersonalLetterService.getDyPersonalLetter(id);
//        return success(DyPersonalLetterConvert.INSTANCE.convert(dyPersonalLetter));
//    }

    @GetMapping("/list")
    @Operation(summary = "获得抖音私信管理列表")
    @Parameter(name = "conversationId", description = "会话id", required = true)
    @PreAuthorize("@mss.hasPermission('cxlaike:dy-personal-letter:query')")
    public CommonResult<List<DyPersonalLetterRespVO>> getDyPersonalLetterList(@RequestParam("conversationId") String conversationId) {
        return success(dyPersonalLetterService.getDyPersonalLetterList(conversationId));
    }

//    @GetMapping("/page")
//    @Operation(summary = "获得抖音私信管理分页")
//    @PreAuthorize("@ss.hasPermission('cxlaike:dy-personal-letter:query')")
//    public CommonResult<PageResult<DyPersonalLetterRespVO>> getDyPersonalLetterPage(@Valid DyPersonalLetterPageReqVO pageVO) {
//        PageResult<DyPersonalLetterDO> pageResult = dyPersonalLetterService.getDyPersonalLetterPage(pageVO);
//        return success(DyPersonalLetterConvert.INSTANCE.convertPage(pageResult));
//    }
//
//    @GetMapping("/export-excel")
//    @Operation(summary = "导出抖音私信管理 Excel")
//    @PreAuthorize("@ss.hasPermission('cxlaike:dy-personal-letter:export')")
//    @OperateLog(type = EXPORT)
//    public void exportDyPersonalLetterExcel(@Valid DyPersonalLetterExportReqVO exportReqVO,
//              HttpServletResponse response) throws IOException {
//        List<DyPersonalLetterDO> list = dyPersonalLetterService.getDyPersonalLetterList(exportReqVO);
//        // 导出 Excel
//        List<DyPersonalLetterExcelVO> datas = DyPersonalLetterConvert.INSTANCE.convertList02(list);
//        ExcelUtils.write(response, "抖音私信管理.xls", "数据", DyPersonalLetterExcelVO.class, datas);
//    }

    @PostMapping("/sendMsg")
    @Operation(summary = "发送私信")
    @PreAuthorize("@mss.hasPermission('cxlaike:dy-personal-letter:create')")
    public CommonResult<Long> createDyPersonalLetter(@Valid @RequestBody DyPersonalLetterCreateReqVO createReqVO) {
        return success(dyPersonalLetterService.createDyPersonalLetter(createReqVO));
    }


}
