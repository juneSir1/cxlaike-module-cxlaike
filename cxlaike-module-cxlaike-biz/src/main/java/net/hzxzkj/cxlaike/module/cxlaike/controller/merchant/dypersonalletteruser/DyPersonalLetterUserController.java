package net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.dypersonalletteruser;

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

import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.dypersonalletteruser.vo.*;
import net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.dypersonalletteruser.DyPersonalLetterUserDO;
import net.hzxzkj.cxlaike.module.cxlaike.convert.dypersonalletteruser.DyPersonalLetterUserConvert;
import net.hzxzkj.cxlaike.module.cxlaike.service.dypersonalletteruser.DyPersonalLetterUserService;

@Tag(name = "管理后台 - 抖音私信用户管理")
@RestController
@RequestMapping("/cxlaike/dy-personal-letter-user")
@Validated
public class DyPersonalLetterUserController {

    @Resource
    private DyPersonalLetterUserService dyPersonalLetterUserService;

//    @PostMapping("/create")
//    @Operation(summary = "创建抖音私信用户管理")
//    @PreAuthorize("@ss.hasPermission('cxlaike:dy-personal-letter-user:create')")
//    public CommonResult<Long> createDyPersonalLetterUser(@Valid @RequestBody DyPersonalLetterUserCreateReqVO createReqVO) {
//        return success(dyPersonalLetterUserService.createDyPersonalLetterUser(createReqVO));
//    }
//
    @PutMapping("/update")
    @Operation(summary = "更新抖音私信用户管理")
    @PreAuthorize("@mss.hasPermission('cxlaike:dy-personal-letter-user:update')")
    public CommonResult<Boolean> updateDyPersonalLetterUser(@Valid @RequestBody DyPersonalLetterUserUpdateReqVO updateReqVO) {
        dyPersonalLetterUserService.updateDyPersonalLetterUser(updateReqVO);
        return success(true);
    }
//
//    @DeleteMapping("/delete")
//    @Operation(summary = "删除抖音私信用户管理")
//    @Parameter(name = "id", description = "编号", required = true)
//    @PreAuthorize("@ss.hasPermission('cxlaike:dy-personal-letter-user:delete')")
//    public CommonResult<Boolean> deleteDyPersonalLetterUser(@RequestParam("id") Long id) {
//        dyPersonalLetterUserService.deleteDyPersonalLetterUser(id);
//        return success(true);
//    }

    @GetMapping("/get")
    @Operation(summary = "获得抖音私信用户管理")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@mss.hasPermission('cxlaike:dy-personal-letter-user:query')")
    public CommonResult<DyPersonalLetterUserRespVO> getDyPersonalLetterUser(@RequestParam("id") Long id) {
        DyPersonalLetterUserDO dyPersonalLetterUser = dyPersonalLetterUserService.getDyPersonalLetterUser(id);
        return success(DyPersonalLetterUserConvert.INSTANCE.convert(dyPersonalLetterUser));
    }

    @GetMapping("/list")
    @Operation(summary = "获得抖音私信用户管理列表")
    @PreAuthorize("@mss.hasPermission('cxlaike:dy-personal-letter-user:query')")
    public CommonResult<List<DyPersonalLetterUserRespVO>> getDyPersonalLetterUserList() {
        return success(dyPersonalLetterUserService.getDyPersonalLetterUserList());
    }



    @GetMapping("/page")
    @Operation(summary = "获得抖音私信用户管理分页")
    @PreAuthorize("@mss.hasPermission('cxlaike:dy-personal-letter-user:query')")
    public CommonResult<PageResult<DyPersonalLetterUserRespVO>> getDyPersonalLetterUserPage(@Valid DyPersonalLetterUserPageReqVO pageVO) {
        return success(dyPersonalLetterUserService.getDyPersonalLetterUserPage(pageVO));
    }

//    @GetMapping("/export-excel")
//    @Operation(summary = "导出抖音私信用户管理 Excel")
//    @PreAuthorize("@ss.hasPermission('cxlaike:dy-personal-letter-user:export')")
//    @OperateLog(type = EXPORT)
//    public void exportDyPersonalLetterUserExcel(@Valid DyPersonalLetterUserExportReqVO exportReqVO,
//              HttpServletResponse response) throws IOException {
//        List<DyPersonalLetterUserDO> list = dyPersonalLetterUserService.getDyPersonalLetterUserList(exportReqVO);
//        // 导出 Excel
//        List<DyPersonalLetterUserExcelVO> datas = DyPersonalLetterUserConvert.INSTANCE.convertList02(list);
//        ExcelUtils.write(response, "抖音私信用户管理.xls", "数据", DyPersonalLetterUserExcelVO.class, datas);
//    }

}
