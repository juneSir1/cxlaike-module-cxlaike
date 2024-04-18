package net.hzxzkj.cxlaike.module.cxlaike.controller.admin.memberdybind;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import net.hzxzkj.cxlaike.framework.common.pojo.CommonResult;
import net.hzxzkj.cxlaike.framework.common.pojo.PageResult;
import net.hzxzkj.cxlaike.framework.security.core.annotations.PreAuthenticated;
import net.hzxzkj.cxlaike.module.cxlaike.controller.admin.memberdybind.vo.MemberDyBindAuditReqVO;
import net.hzxzkj.cxlaike.module.cxlaike.controller.admin.memberdybind.vo.MemberDyBindPageReqVO;
import net.hzxzkj.cxlaike.module.cxlaike.controller.admin.memberdybind.vo.MemberDyBindPageRespVO;
import net.hzxzkj.cxlaike.module.cxlaike.controller.admin.memberdybind.vo.MemberDyBindUpdateReqVO;
import net.hzxzkj.cxlaike.module.cxlaike.service.memberdybind.MemberDyBindService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

import static net.hzxzkj.cxlaike.framework.common.pojo.CommonResult.success;

@Tag(name = "管理后台 - 达人审核")
@RestController
@RequestMapping("/cxlaike/member_dy_bind")
@Validated
public class MemberDyBindController {

    @Resource
    private MemberDyBindService memberDyBindService;

    @GetMapping("/page")
    @Operation(summary = "获得达人审核列表(分页)")
    //@PreAuthorize("@ss.hasPermission('cxlaike:member_dy_bind:page')")
    public CommonResult<PageResult<MemberDyBindPageRespVO>> getListForAudit(@Valid MemberDyBindPageReqVO pageVO) {
        return success(memberDyBindService.getListForAudit(pageVO));
    }


    @PutMapping("/update")
    @Operation(summary = "更新数据")
    @PreAuthenticated
    //@PreAuthorize("@ss.hasPermission('cxlaike:member_dy_bind:update')")
    public CommonResult<Boolean> update(@Valid @RequestBody MemberDyBindUpdateReqVO updateReqVO) {
        memberDyBindService.update(updateReqVO.getId(),updateReqVO.getUserId(),updateReqVO.getFansCount()
                ,updateReqVO.getGiveLikeCount(),updateReqVO.getWorksCount()
                ,updateReqVO.getProvinceCode(),updateReqVO.getProvinceName(),updateReqVO.getCityCode(),updateReqVO.getCityName()
                ,updateReqVO.getCountyCode(),updateReqVO.getCountyName());
        return success(true);
    }

    @PutMapping("/audit")
    @Operation(summary = "审核")
    //@PreAuthorize("@ss.hasPermission('cxlaike:member_dy_bind:audit')")
    public CommonResult<Boolean> audit(@Valid @RequestBody MemberDyBindAuditReqVO updateReqVO) {
        memberDyBindService.audit(updateReqVO.getId(),updateReqVO.getAuditStatus(),updateReqVO.getReason(),updateReqVO.getUserId(),updateReqVO.getFansCount()
                ,updateReqVO.getGiveLikeCount(),updateReqVO.getWorksCount()
                ,updateReqVO.getProvinceCode(),updateReqVO.getProvinceName(),updateReqVO.getCityCode(),updateReqVO.getCityName()
                ,updateReqVO.getCountyCode(),updateReqVO.getCountyName());
        return success(true);
    }

}
