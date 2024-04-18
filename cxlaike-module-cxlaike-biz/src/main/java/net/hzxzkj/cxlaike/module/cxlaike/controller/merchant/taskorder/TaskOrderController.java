package net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.taskorder;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import net.hzxzkj.cxlaike.framework.common.pojo.CommonResult;
import net.hzxzkj.cxlaike.framework.common.pojo.PageResult;
import net.hzxzkj.cxlaike.framework.security.core.annotations.PreAuthenticated;
import net.hzxzkj.cxlaike.framework.security.core.util.SecurityFrameworkUtils;
import net.hzxzkj.cxlaike.module.cxlaike.controller.app.taskorder.vo.TaskOrderBackFillReqVO;
import net.hzxzkj.cxlaike.module.cxlaike.controller.app.taskorder.vo.TaskOrderPageReqVO;
import net.hzxzkj.cxlaike.module.cxlaike.controller.app.taskorder.vo.TaskOrderRespVO;
import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.taskorder.vo.*;
import net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.taskorder.TaskOrderDO;
import net.hzxzkj.cxlaike.module.cxlaike.service.taskorder.TaskOrderService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

import java.time.LocalDate;

import static net.hzxzkj.cxlaike.framework.common.exception.util.ServiceExceptionUtil.exception;
import static net.hzxzkj.cxlaike.framework.common.pojo.CommonResult.success;
import static net.hzxzkj.cxlaike.module.cxlaike.enums.ErrorCodeConstants.DATA_ERROR;

@Tag(name = "商家后台 - 达人任务订单")
@RestController
@RequestMapping("/cxlaike/task-order")
@Validated
public class TaskOrderController {

    @Resource
    private TaskOrderService taskOrderService;


//    @PutMapping("/cancelOrder")
//    @Operation(summary = "取消任务")
//    @PreAuthorize("@mss.hasPermission('')")
//    public CommonResult<Boolean> cancelOrder(@RequestParam(value = "id")Long id) {
//        taskOrderService.cancelOrder(id);
//        return success(true);
//    }
//
//
//    @PutMapping("/backFillVideoLink")
//    @Operation(summary = "回填链接")
//    @PreAuthorize("@mss.hasPermission('')")
//    public CommonResult<Boolean> backFillVideoLink(@Valid @RequestBody TaskOrderBackFillReqVO reqVO) {
//        taskOrderService.backFillVideoLink(reqVO);
//        return success(true);
//    }

    @GetMapping("/pageForAudit")
    @Operation(summary = "达人作品审核列表分页")
    @PreAuthorize("@mss.hasPermission('cxlaike:task-order')")
    public CommonResult<PageResult<TaskOrderAuditListRespVO>> getListForAudit(@Valid TaskOrderAuditListReqVO pageVO) {
        PageResult<TaskOrderAuditListRespVO> page = taskOrderService.getListForAudit(pageVO);
        return success(page);
    }

    @GetMapping("/getCountForAudit")
    @Operation(summary = "达人作品审核列表统计")
    @PreAuthorize("@mss.hasPermission('cxlaike:task-order')")
    public CommonResult<TaskOrderAuditListCountRespVO> getListForAudit() {
        return success(taskOrderService.getCountForAudit());
    }



    @PutMapping("/auditOrder")
    @Operation(summary = "商家审核")
    @PreAuthorize("@mss.hasPermission('cxlaike:task-order')")
    public CommonResult<Boolean> auditOrder(@Valid @RequestBody TaskOrderAuditReqVO reqVO) {
        TaskOrderDO taskOrderDO = taskOrderService.getById(reqVO.getId());
        if (taskOrderDO == null) {
            throw exception(DATA_ERROR);
        }
        taskOrderService.auditOrder(reqVO,taskOrderDO.getMemberUserId(), LocalDate.now(),taskOrderDO);
        return success(true);
    }

    @GetMapping("/getListForDetail")
    @Operation(summary = "达人视频发布明细列表分页")
    @PreAuthorize("@mss.hasPermission('cxlaike:task-order')")
    public CommonResult<PageResult<TaskOrderDetailListRespVO>> getListForDetail(@Valid TaskOrderDetailListReqVO pageVO) {
        PageResult<TaskOrderDetailListRespVO> page = taskOrderService.getListForDetail(pageVO);
        return success(page);
    }

}
