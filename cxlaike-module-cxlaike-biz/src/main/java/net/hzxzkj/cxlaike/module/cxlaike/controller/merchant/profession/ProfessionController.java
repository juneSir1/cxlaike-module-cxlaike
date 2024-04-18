package net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.profession;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import net.hzxzkj.cxlaike.framework.common.pojo.CommonResult;
import net.hzxzkj.cxlaike.framework.security.core.annotations.PreAuthenticated;
import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.profession.vo.ProfessionRespVO;
import net.hzxzkj.cxlaike.module.cxlaike.service.profession.ProfessionService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

import static net.hzxzkj.cxlaike.framework.common.pojo.CommonResult.success;

@Tag(name = "商户后台 - 行业")
@RestController
@RequestMapping("/cxlaike/profession")
@Validated
public class ProfessionController {

    @Resource
    private ProfessionService professionService;


    @GetMapping("/list")
    @Operation(summary = "获得行业列表")
    @PreAuthenticated
    public CommonResult<List<ProfessionRespVO>> getProfessionList() {
        return success(professionService.getProfessionList());
    }

}
