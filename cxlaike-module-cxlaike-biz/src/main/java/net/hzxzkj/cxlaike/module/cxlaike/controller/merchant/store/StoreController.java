package net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.store;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import net.hzxzkj.cxlaike.framework.common.pojo.CommonResult;
import net.hzxzkj.cxlaike.framework.common.pojo.PageResult;
import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.store.vo.*;
import net.hzxzkj.cxlaike.module.cxlaike.convert.store.StoreConvert;
import net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.store.StoreDO;
import net.hzxzkj.cxlaike.module.cxlaike.service.store.StoreService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

import static net.hzxzkj.cxlaike.framework.common.pojo.CommonResult.success;


@Tag(name = "商户后台 - 门店管理")
@RestController
@RequestMapping("/cxlaike/store")
@Validated
public class StoreController {

    @Resource
    private StoreService storeService;

    @PostMapping("/create")
    @Operation(summary = "创建门店")
    @PreAuthorize("@mss.hasPermission('cxlaike:store')")
    public CommonResult<Long> createStore(@Valid @RequestBody StoreCreateReqVO createReqVO) {
        return success(storeService.createStore(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新门店")
    @PreAuthorize("@mss.hasPermission('cxlaike:store')")
    public CommonResult<Boolean> updateStore(@Valid @RequestBody StoreUpdateReqVO updateReqVO) {
        storeService.updateStore(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除门店")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@mss.hasPermission('cxlaike:store')")
    public CommonResult<Boolean> deleteStore(@RequestParam("id") Long id) {
        storeService.deleteStore(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得门店")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@mss.hasPermission('cxlaike:store')")
    public CommonResult<StoreRespVO> getStore(@RequestParam("id") Long id) {
        StoreDO store = storeService.getStore(id);
        return success(StoreConvert.INSTANCE.convert(store));
    }


    @GetMapping("/page")
    @Operation(summary = "获得门店分页")
    @PreAuthorize("@mss.hasPermission('cxlaike:store')")
    public CommonResult<PageResult<StoreRespVO>> getStorePage(@Valid StorePageReqVO pageVO) {
        PageResult<StoreDO> pageResult = storeService.getStorePage(pageVO);
        return success(StoreConvert.INSTANCE.convertPage(pageResult));
    }


    @PutMapping("/update-status")
    @Operation(summary = "更新门店状态")
    @PreAuthorize("@mss.hasPermission('cxlaike:store')")
    public CommonResult<Boolean> updateStoreStatus(@Valid @RequestBody StoreUpdateStatusReqVO reqVO ) {
        storeService.updateStoreStatus(reqVO.getId(),reqVO.getStatus());
        return success(true);
    }



}
