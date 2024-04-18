package net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.dycomment;

import net.hzxzkj.cxlaike.framework.tenant.core.context.TenantContextHolder;
import net.hzxzkj.cxlaike.framework.tenant.core.util.TenantUtils;
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

import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.dycomment.vo.*;
import net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.dycomment.DyCommentDO;
import net.hzxzkj.cxlaike.module.cxlaike.convert.dycomment.DyCommentConvert;
import net.hzxzkj.cxlaike.module.cxlaike.service.dycomment.DyCommentService;

@Tag(name = "商家后台 - 抖音评论管理")
@RestController
@RequestMapping("/cxlaike/dy-comment")
@Validated
public class DyCommentController {

    @Resource
    private DyCommentService dyCommentService;

//    @PostMapping("/create")
//    @Operation(summary = "创建抖音评论管理")
//    @PreAuthorize("@ss.hasPermission('cxlaike:dy-comment:create')")
//    public CommonResult<Long> createDyComment(@Valid @RequestBody DyCommentCreateReqVO createReqVO) {
//        return success(dyCommentService.createDyComment(createReqVO));
//    }
//
//    @PutMapping("/update")
//    @Operation(summary = "更新抖音评论管理")
//    @PreAuthorize("@ss.hasPermission('cxlaike:dy-comment:update')")
//    public CommonResult<Boolean> updateDyComment(@Valid @RequestBody DyCommentUpdateReqVO updateReqVO) {
//        dyCommentService.updateDyComment(updateReqVO);
//        return success(true);
//    }
//
//    @DeleteMapping("/delete")
//    @Operation(summary = "删除抖音评论管理")
//    @Parameter(name = "id", description = "编号", required = true)
//    @PreAuthorize("@ss.hasPermission('cxlaike:dy-comment:delete')")
//    public CommonResult<Boolean> deleteDyComment(@RequestParam("id") Long id) {
//        dyCommentService.deleteDyComment(id);
//        return success(true);
//    }
//
//    @GetMapping("/get")
//    @Operation(summary = "获得抖音评论管理")
//    @Parameter(name = "id", description = "编号", required = true, example = "1024")
//    @PreAuthorize("@ss.hasPermission('cxlaike:dy-comment:query')")
//    public CommonResult<DyCommentRespVO> getDyComment(@RequestParam("id") Long id) {
//        DyCommentDO dyComment = dyCommentService.getDyComment(id);
//        return success(DyCommentConvert.INSTANCE.convert(dyComment));
//    }
//
//    @GetMapping("/list")
//    @Operation(summary = "获得抖音评论管理列表")
//    @Parameter(name = "ids", description = "编号列表", required = true, example = "1024,2048")
//    @PreAuthorize("@ss.hasPermission('cxlaike:dy-comment:query')")
//    public CommonResult<List<DyCommentRespVO>> getDyCommentList(@RequestParam("ids") Collection<Long> ids) {
//        List<DyCommentDO> list = dyCommentService.getDyCommentList(ids);
//        return success(DyCommentConvert.INSTANCE.convertList(list));
//    }

    @GetMapping("/page")
    @Operation(summary = "获得抖音评论管理分页")
    @PreAuthorize("@mss.hasPermission('cxlaike:dy-comment:query')")
    public CommonResult<PageResult<DyCommentRespVO>> getDyCommentPage(@Valid DyCommentPageReqVO pageVO) {
        PageResult<DyCommentDO> pageResult = dyCommentService.getDyCommentPage(pageVO);
        return success(DyCommentConvert.INSTANCE.convertPage(pageResult));
    }


    @PostMapping("/commentTop")
    @Operation(summary = "置顶")
    @PreAuthorize("@mss.hasPermission('cxlaike:dy-comment:update')")
    public CommonResult<Boolean> commentTop(@Valid @RequestBody DyCommentTopReqVO topReqVO) {
        if(topReqVO.getIsTop() == null){
          topReqVO.setIsTop(true);//设置默认值，兼容之前调用
        }
        dyCommentService.commentTop(topReqVO);
        return success(true);
    }

    @PostMapping("/commentReply")
    @Operation(summary = "回复")
    @PreAuthorize("@mss.hasPermission('cxlaike:dy-comment:update')")
    public CommonResult<Boolean> commentReply(@Valid @RequestBody DyCommentReplyReqVO replyReqVO) {
        dyCommentService.commentReply(replyReqVO);
        return success(true);
    }

    /**
     * 必输入参数：accountBindId\itemId\content
     * @param createReqVO
     * @return
     */
    @PostMapping("/createDyComment")
    @Operation(summary = "创建抖音评论")
    @PreAuthorize("@mss.hasPermission('cxlaike:dy-comment:update')")
    public CommonResult<Boolean> createDyComment(@Valid @RequestBody DyCommentCreateReqVO createReqVO) {
        dyCommentService.createDyComment(createReqVO);
        return success(true);
    }

    @PostMapping("/pullCommentList")
    @Operation(summary = "手动更新")
    @PreAuthorize("@mss.hasPermission('cxlaike:dy-comment:update')")
    public CommonResult<Boolean> pullCommentList() {
        dyCommentService.pullCommentList(TenantContextHolder.getTenantId());
        return success(true);
    }

}
