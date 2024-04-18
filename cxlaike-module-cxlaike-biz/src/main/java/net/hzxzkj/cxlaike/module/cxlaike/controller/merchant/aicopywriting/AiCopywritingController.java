package net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.aicopywriting;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import net.hzxzkj.cxlaike.framework.common.pojo.CommonResult;
import net.hzxzkj.cxlaike.framework.common.pojo.PageResult;
import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.aicopywriting.vo.AiCopywritingCreateReqVO;
import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.aicopywriting.vo.AiCopywritingPageReqVO;
import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.aicopywriting.vo.AiCopywritingRespVO;
import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.aicopywriting.vo.AiOptimizeCopywritingVO;
import net.hzxzkj.cxlaike.module.cxlaike.convert.aicopywriting.AiCopywritingConvert;
import net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.aicopywriting.AiCopywritingDO;
import net.hzxzkj.cxlaike.module.cxlaike.service.aicopywriting.AiCopywritingService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

import static net.hzxzkj.cxlaike.framework.common.pojo.CommonResult.success;

@Tag(name = "商家后台 - ai文案")
@RestController
@RequestMapping("/cxlaike/ai-copywriting")
@Validated
public class AiCopywritingController {

    @Resource
    private AiCopywritingService aiCopywritingService;

    @PostMapping("/create")
    @Operation(summary = "创建ai文案")
    @PreAuthorize("@mss.hasPermission('cxlaike:ai-copywriting')")

    public CommonResult<String> createAiCopywriting(@Valid @RequestBody AiCopywritingCreateReqVO createReqVO) {
        return success(aiCopywritingService.createAiCopywriting(createReqVO));
    }

//    @PutMapping("/update")
//    @Operation(summary = "更新ai文案")
//    @PreAuthorize("@ss.hasPermission('cxlaike:ai-copywriting:update')")
//    public CommonResult<Boolean> updateAiCopywriting(@Valid @RequestBody AiCopywritingUpdateReqVO updateReqVO) {
//        aiCopywritingService.updateAiCopywriting(updateReqVO);
//        return success(true);
//    }
//
//    @DeleteMapping("/delete")
//    @Operation(summary = "删除ai文案")
//    @Parameter(name = "id", description = "编号", required = true)
//    @PreAuthorize("@ss.hasPermission('cxlaike:ai-copywriting:delete')")
//    public CommonResult<Boolean> deleteAiCopywriting(@RequestParam("id") Long id) {
//        aiCopywritingService.deleteAiCopywriting(id);
//        return success(true);
//    }

    @GetMapping("/get")
    @Operation(summary = "获得ai文案")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@mss.hasPermission('cxlaike:ai-copywriting')")
    public CommonResult<AiCopywritingRespVO> getAiCopywriting(@RequestParam("id") Long id) {
        return success(aiCopywritingService.getAiCopywriting(id));
    }

    @GetMapping("/page")
    @Operation(summary = "获得ai文案分页")
    @PreAuthorize("@mss.hasPermission('cxlaike:ai-copywriting')")
    public CommonResult<PageResult<AiCopywritingRespVO>> getAiCopywritingPage(@Valid AiCopywritingPageReqVO pageVO) {
        PageResult<AiCopywritingDO> pageResult = aiCopywritingService.getAiCopywritingPage(pageVO);
        return success(AiCopywritingConvert.INSTANCE.convertPage(pageResult));
    }



    @GetMapping("/list")
    @Operation(summary = "获得ai文案列表")
    @PreAuthorize("@mss.hasPermission('cxlaike:ai-copywriting')")

    public CommonResult<List<AiCopywritingRespVO>> getAiCopywritingPage() {
        return success(aiCopywritingService.getAiCopywritingList());
    }


    @PostMapping("/optimizeCopywriting")
    @Operation(summary = "优化文案")
    @PreAuthorize("@mss.hasPermission('cxlaike:ai-copywriting')")

    public CommonResult<String> optimizeCopywriting(@Valid @RequestBody AiOptimizeCopywritingVO createReqVO) {
        return success(aiCopywritingService.optimizeCopywriting(createReqVO.getContent(),createReqVO.getCode()));
    }

}
