package net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.aisystemarg;

import static net.hzxzkj.cxlaike.framework.common.pojo.CommonResult.success;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import javax.annotation.Resource;
import net.hzxzkj.cxlaike.framework.common.pojo.CommonResult;
import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.aisystemarg.vo.AiSystemArgRespVO;
import net.hzxzkj.cxlaike.module.cxlaike.convert.aisystemarg.AiSystemArgConvert;
import net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.aisystemarg.AiSystemArgDO;
import net.hzxzkj.cxlaike.module.cxlaike.service.aisystemarg.AiSystemArgService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "商家后台 - 来客系统参数")
@RestController
@RequestMapping("/cxlaike/ai-system-arg")
@Validated
public class AiSystemArgController {

  @Resource
  private AiSystemArgService aiSystemArgService;

  @GetMapping("/list")
  @Operation(summary = "获得来客系统参数列表")
  @Parameter(name = "sysType", description = "系统参数类型")
  @PreAuthorize("@mss.hasPermission('cxlaike:ai-system-arg')")
  public CommonResult<List<AiSystemArgRespVO>> getAiSystemArgList(
      @RequestParam(value = "sysType", required = false) Integer sysType) {
    List<AiSystemArgDO> list = aiSystemArgService.getAiSystemArgList(sysType);
    return success(AiSystemArgConvert.INSTANCE.convertList(list));
  }

}
