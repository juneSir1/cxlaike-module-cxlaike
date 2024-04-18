package net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.matrixaccount;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import net.hzxzkj.cxlaike.framework.common.pojo.CommonResult;
import net.hzxzkj.cxlaike.framework.common.pojo.PageResult;
import net.hzxzkj.cxlaike.framework.mybatis.core.controller.BaseControllerX;
import net.hzxzkj.cxlaike.framework.mybatis.core.dataobject.BaseDO;
import net.hzxzkj.cxlaike.module.cxlaike.controller.app.douyin.vo.UserDyBindRespVO;
import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.accountgroup.vo.AccountGroupRespVO;
import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.accountgroup.vo.AccountGroupUpdateReqVO;
import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.matrixaccount.vo.*;
import net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.accountgroup.AccountGroupDO;
import net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.memberdybind.MemberDyBindDO;
import net.hzxzkj.cxlaike.module.cxlaike.enums.SystemConstants;
import net.hzxzkj.cxlaike.module.cxlaike.enums.matrixaccount.MatrixAccountOrderByEnum;
import net.hzxzkj.cxlaike.module.cxlaike.service.douyin.DouYinService;
import net.hzxzkj.cxlaike.module.cxlaike.service.memberdybind.MemberDyBindService;
import net.hzxzkj.cxlaike.module.cxlaike.utils.douyin.config.DouYinProperties;
import net.hzxzkj.cxlaike.module.cxlaike.utils.douyin.vo.PoiPageRespDTO;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

import static net.hzxzkj.cxlaike.framework.common.pojo.CommonResult.success;
import static net.hzxzkj.cxlaike.framework.security.core.util.SecurityFrameworkUtils.getLoginUserId;

@Tag(name = "商户 - 矩阵账号管理")
@RestController
@RequestMapping("/cxlaike/matrix-account")
@Validated
public class MatrixAccountController  extends BaseControllerX<MemberDyBindDO> {


    @Resource
    private MemberDyBindService memberDyBindService;


    @GetMapping("/getPageListForMerchant")
    @Operation(summary = "分发账号列表")
    @PreAuthorize("@mss.hasPermission('cxlaike:matrix-account:query')")
    public CommonResult<PageResult<MatrixAccountRespVO>> getPageListForMerchant(MatrixAccountPageReqVO reqVO,@RequestParam("orderBy")Integer orderBy,@RequestParam("isAsc")Boolean isAsc) {

        String sc = isAsc ? ":ASC" : ":DESC";
        String orderBy1 = MatrixAccountOrderByEnum.getEnumByCode(orderBy).getValue();
//        return success(this.getViewObjectPage(reqVO, MatrixAccountRespVO.class, BaseDO.Fields.createTime + SystemConstants.DESC));
        return success(this.getViewObjectPage(reqVO, MatrixAccountRespVO.class, orderBy1 + sc));
    }


    @DeleteMapping("/delete")
    @Operation(summary = "删除账号")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@mss.hasPermission('cxlaike:matrix-account:delete')")
    public CommonResult<Boolean> deleteDyGroup(@RequestParam("id") Long id) {
        memberDyBindService.deleteById(id);
        return success(true);
    }

    @PutMapping("/updateStatus")
    @Operation(summary = "修改状态")
    @PreAuthorize("@mss.hasPermission('cxlaike:matrix-account:update')")
    public CommonResult<Boolean> updateStatus(@RequestBody @Valid MatrixAccountUpdateStatusReqVO reqVO) {
        memberDyBindService.updateStatus(reqVO);
        return success(true);
    }

    @PutMapping("/updateGroup")
    @Operation(summary = "修改分组")
    @PreAuthorize("@mss.hasPermission('cxlaike:matrix-account:update')")
    public CommonResult<Boolean> updateGroup(@RequestBody @Valid MatrixAccountUpdateGroupReqVO reqVO) {
        memberDyBindService.updateGroup(reqVO);
        return success(true);
    }

    @PutMapping("/updateAddress")
    @Operation(summary = "修改poi地址")
    @PreAuthorize("@mss.hasPermission('cxlaike:matrix-account:update')")
    public CommonResult<Boolean> updateAddress(@RequestBody @Valid MatrixAccountUpdateAddressReqVO reqVO) {
        memberDyBindService.updateAddress(reqVO);
        return success(true);
    }


}
