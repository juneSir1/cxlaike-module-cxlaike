package net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.systakelevelconfig;

import static net.hzxzkj.cxlaike.framework.common.pojo.CommonResult.success;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import javax.annotation.Resource;
import net.hzxzkj.cxlaike.framework.common.pojo.CommonResult;
import net.hzxzkj.cxlaike.framework.common.validation.InEnum;
import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.systakelevelconfig.vo.SysTakeLevelConfigRespVO;
import net.hzxzkj.cxlaike.module.cxlaike.convert.systakelevelconfig.SysTakeLevelConfigConvert;
import net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.systakelevelconfig.SysTakeLevelConfigDO;
import net.hzxzkj.cxlaike.module.cxlaike.enums.SysTakeLevelType;
import net.hzxzkj.cxlaike.module.cxlaike.service.systakelevelconfig.SysTakeLevelConfigService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 描   述:
 *
 * @author ace
 * @version [版本号, 2023/9/14]
 * @see [相关类/方法]
 * 创建日期: 2023/9/14
 */
@Tag(name = "商户后台 - 商家任务等级奖励配置")
@RestController
@RequestMapping("/cxlaike/sys-take-level-config")
@Validated
public class SysLevelConfigController {

  @Resource
  private SysTakeLevelConfigService sysTakeLevelConfigService;

  @GetMapping("/list")
  @Operation(summary = "获得来客系统等级参数配置列表")
  @PreAuthorize("@mss.hasPermission('cxlaike:sys-take-level-config')")
  public CommonResult<List<SysTakeLevelConfigRespVO>> getSysTakeLevelConfigList(
      @InEnum(value = SysTakeLevelType.class, message = "type参数错误")
      @RequestParam(value = "type", required = false) Integer type) {
    List<SysTakeLevelConfigDO> list = sysTakeLevelConfigService.getSysTakeLevelConfigList(type);
    return success(SysTakeLevelConfigConvert.INSTANCE.convertList03(list));
  }

}
