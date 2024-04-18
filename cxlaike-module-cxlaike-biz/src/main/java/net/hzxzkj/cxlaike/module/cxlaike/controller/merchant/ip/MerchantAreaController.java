package net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.ip;

import cn.hutool.core.lang.Assert;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import net.hzxzkj.cxlaike.framework.common.pojo.CommonResult;
import net.hzxzkj.cxlaike.framework.ip.core.Area;
import net.hzxzkj.cxlaike.framework.ip.core.utils.AreaUtils;
import net.hzxzkj.cxlaike.framework.security.core.annotations.PreAuthenticated;
import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.ip.vo.AppAreaNodeRespVO;
import net.hzxzkj.cxlaike.module.cxlaike.convert.ip.AreaConvert;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static net.hzxzkj.cxlaike.framework.common.pojo.CommonResult.success;

@Tag(name = "商家后台 - 地区")
@RestController
@RequestMapping("/system/area")
@Validated
public class MerchantAreaController {

    @GetMapping("/tree")
    @Operation(summary = "获得地区树")
    @PreAuthenticated
    public CommonResult<List<AppAreaNodeRespVO>> getAreaTree() {
        Area area = AreaUtils.getArea(Area.ID_CHINA);
        Assert.notNull(area, "获取不到中国");
        return success(AreaConvert.INSTANCE.convertList(area.getChildren()));
    }

}

