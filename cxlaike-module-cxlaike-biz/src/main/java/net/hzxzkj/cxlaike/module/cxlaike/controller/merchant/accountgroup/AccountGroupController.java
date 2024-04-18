package net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.accountgroup;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.diboot.core.binding.Binder;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import net.hzxzkj.cxlaike.framework.common.pojo.CommonResult;
import net.hzxzkj.cxlaike.framework.common.pojo.PageResult;
import net.hzxzkj.cxlaike.framework.common.util.date.LocalDateTimeUtils;
import net.hzxzkj.cxlaike.framework.mybatis.core.controller.BaseControllerX;
import net.hzxzkj.cxlaike.framework.mybatis.core.dataobject.BaseDO;
import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.accountgroup.vo.*;
import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.accountgroup.vo.MatrixAccountListReqVO;
import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.matrixaccount.vo.MatrixAccountRespVO;
import net.hzxzkj.cxlaike.module.cxlaike.convert.dygroup.DyGroupConvert;
import net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.accountgroup.AccountGroupDO;
import net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.memberdybind.MemberDyBindDO;
import net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.memberdytoken.MemberDyTokenDO;
import net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.taskordergather.TaskOrderGatherDO;
import net.hzxzkj.cxlaike.module.cxlaike.dal.mysql.memberdytoken.MemberDyTokenMapper;
import net.hzxzkj.cxlaike.module.cxlaike.enums.SystemConstants;
import net.hzxzkj.cxlaike.module.cxlaike.service.dygroup.DyGroupService;
import net.hzxzkj.cxlaike.module.cxlaike.service.dygroupcorrelation.DyGroupCorrelationService;
import net.hzxzkj.cxlaike.module.cxlaike.service.memberdytoken.MemberDyTokenService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.*;
import java.util.stream.Collectors;

import static net.hzxzkj.cxlaike.framework.common.pojo.CommonResult.success;


@Tag(name = "商家后台 - 商户账号分组管理")
@RestController
@RequestMapping("/merchant/dy-group")
@Validated
public class AccountGroupController extends BaseControllerX<AccountGroupDO> {

    @Resource
    private DyGroupService dyGroupService;

    @Resource
    private MemberDyTokenService memberDyTokenService;

    @Resource
    private MemberDyTokenMapper memberDyTokenMapper;

    @Resource
    private DyGroupCorrelationService dyGroupCorrelationService;



    @PostMapping("/create")
    @Operation(summary = "创建商户账号分组管理")
    @PreAuthorize("@mss.hasPermission('cxlaike:matrix-dy-group:create')")
    public CommonResult<Long> createDyGroup(@Valid @RequestBody AccountGroupCreateReqVO createReqVO) {
        return success(dyGroupService.createDyGroup(createReqVO));
    }


    @GetMapping("/page")
    @Operation(summary = "获得商户账号分组管理分页")
    @PreAuthorize("@mss.hasPermission('cxlaike:matrix-dy-group:page')")
    public CommonResult<PageResult<AccountGroupRespVO>> getDyGroupPage(@Valid AccountGroupPageReqVO pageVO) {
        return success(this.getViewObjectPage(pageVO, AccountGroupRespVO.class, BaseDO.Fields.createTime + SystemConstants.DESC));
    }


    @PutMapping("/update")
    @Operation(summary = "更新商户账号分组管理")
    @PreAuthorize("@mss.hasPermission('cxlaike:matrix-dy-group:update')")
    public CommonResult<Boolean> updateDyGroup(@Valid @RequestBody AccountGroupUpdateReqVO updateReqVO) {
        dyGroupService.updateDyGroup(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除商户账号分组管理")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@mss.hasPermission('cxlaike:matrix-dy-group:delete')")
    public CommonResult<Boolean> deleteDyGroup(@RequestParam("id") Long id) {
        dyGroupService.deleteDyGroup(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得商户账号分组管理")
    @PreAuthorize("@mss.hasPermission('cxlaike:matrix-dy-group:get')")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    public CommonResult<AccountGroupRespVO> getDyGroup(@RequestParam("id") Long id) {
        AccountGroupDO dyGroup = dyGroupService.getDyGroup(id);
        return success(DyGroupConvert.INSTANCE.convert(dyGroup));
    }

    @GetMapping("/list")
    @Operation(summary = "获得商户抖音分组管理列表")
    @PreAuthorize("@mss.hasPermission('cxlaike:matrix-dy-group:list')")
    @Parameter(name = "platformType", description = "平台,1抖音，2快手", example = "1")
    public CommonResult<List<AccountGroupRespVO>> getDyGroupList(@RequestParam("platformType") Integer platform) {
        List<AccountGroupDO> list = dyGroupService.getDyGroupList(platform);
        List<AccountGroupRespVO> resultList = Binder.convertAndBindRelations(list, AccountGroupRespVO.class);
        for(AccountGroupRespVO vo:resultList){
            List<MemberDyBindDO> mList = vo.getDyBindDOS();
            if(CollectionUtil.isEmpty(mList)){
                continue;
            }
            Map<Long,MemberDyBindDO> bindMap = new HashMap<>();
            for(MemberDyBindDO bindDO:mList){
                bindMap.put(bindDO.getId(),bindDO);
            }
            List<Long> accountIds = mList.stream().map(MemberDyBindDO::getId).collect(Collectors.toList());
            List<MemberDyTokenDO> tokenList = memberDyTokenMapper.selectList(new LambdaQueryWrapper<MemberDyTokenDO>()
                    .in(MemberDyTokenDO::getId, accountIds));
            List<MemberDyBindDO> newMList = new ArrayList<>();
            for(MemberDyTokenDO tokenDO:tokenList){
                if(tokenDO.getDyAccreditStatus().equals(1)){
                    newMList.add(bindMap.get(tokenDO.getId()));
                }
            }
            vo.setAccountCount(newMList.size());
            vo.setDyBindDOS(newMList);
        }
        return success(resultList);
    }


    @PutMapping("/transfer")
    @Operation(summary = "转移")
    @PreAuthorize("@mss.hasPermission('cxlaike:matrix-dy-group:transfer')")
    public CommonResult<Boolean> transfer(@Valid @RequestBody AccountGroupTransferReqVO updateReqVO) {
        dyGroupService.transferDyGroup(updateReqVO);
        return success(true);
    }

    @GetMapping("/getAccountByGroup")
    @Operation(summary = "根据分组查询账号")
    @PreAuthorize("@mss.hasPermission('cxlaike:matrix-dy-group:query')")
    public CommonResult<List<MatrixAccountListRespVO>> getAccountByGroup(MatrixAccountListReqVO reqVO) {
        List<MatrixAccountListRespVO> list = dyGroupService.getAccountByGroup(reqVO);
        if(CollectionUtil.isEmpty(list)){
            return success(new ArrayList<>());
        }
        List<MatrixAccountListRespVO> newMList = new ArrayList<>();
        Map<Long,MatrixAccountListRespVO> bindMap = new HashMap<>();
        for(MatrixAccountListRespVO bindDO:list){
            bindMap.put(bindDO.getAccountId(),bindDO);
        }
        List<Long> accountIds = list.stream().map(MatrixAccountListRespVO::getAccountId).collect(Collectors.toList());
        List<MemberDyTokenDO> tokenList = memberDyTokenMapper.selectList(new LambdaQueryWrapper<MemberDyTokenDO>()
                .in(MemberDyTokenDO::getId, accountIds));
        for(MemberDyTokenDO tokenDO:tokenList){
            if(tokenDO.getDyAccreditStatus().equals(1)){
                newMList.add(bindMap.get(tokenDO.getId()));
            }
        }

        return success(newMList);
    }
}
