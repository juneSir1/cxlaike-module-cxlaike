package net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.drrepresentative;

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

import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.drrepresentative.vo.*;
import net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.drrepresentative.DrRepresentativeDO;
import net.hzxzkj.cxlaike.module.cxlaike.convert.drrepresentative.DrRepresentativeConvert;
import net.hzxzkj.cxlaike.module.cxlaike.service.drrepresentative.DrRepresentativeService;

@Tag(name = "管理后台 - 达人探店代")
@RestController
@RequestMapping("/cxlaike/dr-representative")
@Validated
public class DrRepresentativeController {

    @Resource
    private DrRepresentativeService drRepresentativeService;

//    @PostMapping("/create")
//    @Operation(summary = "创建达人探店代")
//    @PreAuthorize("@mss.hasPermission('cxlaike:dr-representative:create')")
//    public CommonResult<Long> createDrRepresentative(@Valid @RequestBody DrRepresentativeCreateReqVO createReqVO) {
//        return success(drRepresentativeService.createDrRepresentative(createReqVO));
//    }
//
//    @PutMapping("/update")
//    @Operation(summary = "更新达人探店代")
//    @PreAuthorize("@mss.hasPermission('cxlaike:dr-representative:update')")
//    public CommonResult<Boolean> updateDrRepresentative(@Valid @RequestBody DrRepresentativeUpdateReqVO updateReqVO) {
//        drRepresentativeService.updateDrRepresentative(updateReqVO);
//        return success(true);
//    }
//
//    @DeleteMapping("/delete")
//    @Operation(summary = "删除达人探店代")
//    @Parameter(name = "id", description = "编号", required = true)
//    @PreAuthorize("@mss.hasPermission('cxlaike:dr-representative:delete')")
//    public CommonResult<Boolean> deleteDrRepresentative(@RequestParam("id") Long id) {
//        drRepresentativeService.deleteDrRepresentative(id);
//        return success(true);
//    }
//
//    @GetMapping("/get")
//    @Operation(summary = "获得达人探店代")
//    @Parameter(name = "id", description = "编号", required = true, example = "1024")
//    @PreAuthorize("@mss.hasPermission('cxlaike:dr-representative:query')")
//    public CommonResult<DrRepresentativeRespVO> getDrRepresentative(@RequestParam("id") Long id) {
//        DrRepresentativeDO drRepresentative = drRepresentativeService.getDrRepresentative(id);
//        return success(DrRepresentativeConvert.INSTANCE.convert(drRepresentative));
//    }

    @GetMapping("/list")
    @Operation(summary = "获得达人探店代列表")
    //@PreAuthorize("@mss.hasPermission('cxlaike:dr-representative:query')")
    public CommonResult<List<DrRepresentativeRespVO>> getDrRepresentativeList() {
        List<DrRepresentativeDO> list = drRepresentativeService.getDrRepresentativeList();
        return success(DrRepresentativeConvert.INSTANCE.convertList(list));
    }
//
//    @GetMapping("/page")
//    @Operation(summary = "获得达人探店代分页")
//    @PreAuthorize("@mss.hasPermission('cxlaike:dr-representative:query')")
//    public CommonResult<PageResult<DrRepresentativeRespVO>> getDrRepresentativePage(@Valid DrRepresentativePageReqVO pageVO) {
//        PageResult<DrRepresentativeDO> pageResult = drRepresentativeService.getDrRepresentativePage(pageVO);
//        return success(DrRepresentativeConvert.INSTANCE.convertPage(pageResult));
//    }
//
//    @GetMapping("/export-excel")
//    @Operation(summary = "导出达人探店代 Excel")
//    @PreAuthorize("@mss.hasPermission('cxlaike:dr-representative:export')")
//    @OperateLog(type = EXPORT)
//    public void exportDrRepresentativeExcel(@Valid DrRepresentativeExportReqVO exportReqVO,
//              HttpServletResponse response) throws IOException {
//        List<DrRepresentativeDO> list = drRepresentativeService.getDrRepresentativeList(exportReqVO);
//        // 导出 Excel
//        List<DrRepresentativeExcelVO> datas = DrRepresentativeConvert.INSTANCE.convertList02(list);
//        ExcelUtils.write(response, "达人探店代.xls", "数据", DrRepresentativeExcelVO.class, datas);
//    }

}
