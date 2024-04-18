package net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.region;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import net.hzxzkj.cxlaike.framework.common.pojo.CommonResult;
import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.region.vo.RegionRespVO;
import net.hzxzkj.cxlaike.module.cxlaike.service.region.RegionService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

import static net.hzxzkj.cxlaike.framework.common.pojo.CommonResult.success;


@Tag(name = "管理后台 - 地区2")
@RestController
@RequestMapping("/cxlaike/region")
@Validated
public class RegionController {

    @Resource
    private RegionService regionService;

    @GetMapping("/tree")
    @Operation(summary = "获得地区树")
    @PreAuthorize("@mss.hasPermission('cxlaike:region')")
    public CommonResult<List<RegionRespVO>> getRegionList() {
        return success(regionService.getRegionList());
    }


}
