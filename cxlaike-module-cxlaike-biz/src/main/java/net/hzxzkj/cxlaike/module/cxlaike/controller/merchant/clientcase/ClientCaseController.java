package net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.clientcase;

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

import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.clientcase.vo.*;
import net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.clientcase.ClientCaseDO;
import net.hzxzkj.cxlaike.module.cxlaike.convert.clientcase.ClientCaseConvert;
import net.hzxzkj.cxlaike.module.cxlaike.service.clientcase.ClientCaseService;

@Tag(name = "管理后台 - 客户案例")
@RestController
@RequestMapping("/cxlaike/client-case")
@Validated
public class ClientCaseController {

    @Resource
    private ClientCaseService clientCaseService;

//    @PostMapping("/create")
//    @Operation(summary = "创建客户案例")
//    @PreAuthorize("@mss.hasPermission('cxlaike:client-case:create')")
//    public CommonResult<Long> createClientCase(@Valid @RequestBody ClientCaseCreateReqVO createReqVO) {
//        return success(clientCaseService.createClientCase(createReqVO));
//    }
//
//    @PutMapping("/update")
//    @Operation(summary = "更新客户案例")
//    @PreAuthorize("@mss.hasPermission('cxlaike:client-case:update')")
//    public CommonResult<Boolean> updateClientCase(@Valid @RequestBody ClientCaseUpdateReqVO updateReqVO) {
//        clientCaseService.updateClientCase(updateReqVO);
//        return success(true);
//    }
//
//    @DeleteMapping("/delete")
//    @Operation(summary = "删除客户案例")
//    @Parameter(name = "id", description = "编号", required = true)
//    @PreAuthorize("@mss.hasPermission('cxlaike:client-case:delete')")
//    public CommonResult<Boolean> deleteClientCase(@RequestParam("id") Long id) {
//        clientCaseService.deleteClientCase(id);
//        return success(true);
//    }
//
//    @GetMapping("/get")
//    @Operation(summary = "获得客户案例")
//    @Parameter(name = "id", description = "编号", required = true, example = "1024")
//    @PreAuthorize("@mss.hasPermission('cxlaike:client-case:query')")
//    public CommonResult<ClientCaseRespVO> getClientCase(@RequestParam("id") Long id) {
//        ClientCaseDO clientCase = clientCaseService.getClientCase(id);
//        return success(ClientCaseConvert.INSTANCE.convert(clientCase));
//    }

    @GetMapping("/list")
    @Operation(summary = "获得客户案例列表")
    @PreAuthorize("@mss.hasPermission('cxlaike:client-case:query')")
    public CommonResult<List<ClientCaseRespVO>> getClientCaseList() {
        List<ClientCaseDO> list = clientCaseService.getClientCaseList();
        return success(ClientCaseConvert.INSTANCE.convertList(list));
    }

//    @GetMapping("/page")
//    @Operation(summary = "获得客户案例分页")
//    @PreAuthorize("@mss.hasPermission('cxlaike:client-case:query')")
//    public CommonResult<PageResult<ClientCaseRespVO>> getClientCasePage(@Valid ClientCasePageReqVO pageVO) {
//        PageResult<ClientCaseDO> pageResult = clientCaseService.getClientCasePage(pageVO);
//        return success(ClientCaseConvert.INSTANCE.convertPage(pageResult));
//    }
//
//    @GetMapping("/export-excel")
//    @Operation(summary = "导出客户案例 Excel")
//    @PreAuthorize("@mss.hasPermission('cxlaike:client-case:export')")
//    @OperateLog(type = EXPORT)
//    public void exportClientCaseExcel(@Valid ClientCaseExportReqVO exportReqVO,
//              HttpServletResponse response) throws IOException {
//        List<ClientCaseDO> list = clientCaseService.getClientCaseList(exportReqVO);
//        // 导出 Excel
//        List<ClientCaseExcelVO> datas = ClientCaseConvert.INSTANCE.convertList02(list);
//        ExcelUtils.write(response, "客户案例.xls", "数据", ClientCaseExcelVO.class, datas);
//    }

}
