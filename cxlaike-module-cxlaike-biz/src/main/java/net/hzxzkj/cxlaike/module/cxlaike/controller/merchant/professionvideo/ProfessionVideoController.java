package net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.professionvideo;

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

import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.professionvideo.vo.*;
import net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.professionvideo.ProfessionVideoDO;
import net.hzxzkj.cxlaike.module.cxlaike.convert.professionvideo.ProfessionVideoConvert;
import net.hzxzkj.cxlaike.module.cxlaike.service.professionvideo.ProfessionVideoService;

@Tag(name = "管理后台 - 精选行业视频")
@RestController
@RequestMapping("/cxlaike/profession-video")
@Validated
public class ProfessionVideoController {

    @Resource
    private ProfessionVideoService professionVideoService;

    @GetMapping("/list")
    @Operation(summary = "获得精选行业视频列表")
    //@PreAuthorize("@mss.hasPermission('cxlaike:profession-video:query')")
    public CommonResult<List<ProfessionVideoRespVO>> getProfessionVideoList() {
        List<ProfessionVideoDO> list = professionVideoService.getProfessionVideoList();
        return success(ProfessionVideoConvert.INSTANCE.convertList(list));
    }

}
