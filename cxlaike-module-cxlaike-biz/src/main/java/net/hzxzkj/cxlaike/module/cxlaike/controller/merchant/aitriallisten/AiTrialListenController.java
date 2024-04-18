package net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.aitriallisten;

import static net.hzxzkj.cxlaike.framework.common.pojo.CommonResult.success;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import javax.annotation.Resource;
import javax.validation.Valid;
import net.hzxzkj.cxlaike.framework.common.pojo.CommonResult;
import net.hzxzkj.cxlaike.framework.idempotent.core.annotation.Idempotent;
import net.hzxzkj.cxlaike.framework.security.core.annotations.PreAuthenticated;
import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.aitriallisten.vo.AiTrialListenCreateReqVO;
import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.aitriallisten.vo.AiTrialListenRespVO;
import net.hzxzkj.cxlaike.module.cxlaike.service.aitriallisten.AiTrialListenService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "商家后台 - ai语音试听功能")
@RestController
@RequestMapping("/cxlaike/ai-trial-listen")
@Validated
public class AiTrialListenController {

  @Resource
  private AiTrialListenService aiTrialListenService;

  @PostMapping("/create")
  @Operation(summary = "创建ai语音试听功能")
  @PreAuthorize("@mss.hasPermission('cxlaike:ai-trial-listen')")
  @Idempotent(message = "请勿重复提交")
  public CommonResult<Long> createAiTrialListen(
      @Valid @RequestBody AiTrialListenCreateReqVO createReqVO) {
    return success(aiTrialListenService.createAiTrialListen(createReqVO));
  }

  @GetMapping("/get")
  @Operation(summary = "获得ai语音试听功能")
  @Parameter(name = "id", description = "编号", required = true, example = "1024")
  @PreAuthorize("@mss.hasPermission('cxlaike:ai-trial-listen')")

  public CommonResult<AiTrialListenRespVO> getAiTrialListen(@RequestParam("id") Long id) {
    AiTrialListenRespVO aiTrialListen = aiTrialListenService.getAiTrialListen(id);
    return success(aiTrialListen);
  }
}
