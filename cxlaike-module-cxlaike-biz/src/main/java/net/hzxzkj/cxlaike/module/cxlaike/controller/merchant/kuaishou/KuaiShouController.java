package net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.kuaishou;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import net.hzxzkj.cxlaike.framework.common.pojo.CommonResult;
import net.hzxzkj.cxlaike.framework.common.util.json.JsonUtils;
import net.hzxzkj.cxlaike.module.cxlaike.controller.app.douyin.vo.UserDyBindRespVO;
import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.kuaishou.vo.AccreditReqVO;
import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.kuaishou.vo.DealWebhooksReqVO;
import net.hzxzkj.cxlaike.module.cxlaike.service.kuaishou.KuaiShouService;
import net.hzxzkj.cxlaike.module.cxlaike.utils.kuaishou.config.KuaiShouProperties;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;
import java.util.Map;

import static net.hzxzkj.cxlaike.framework.common.pojo.CommonResult.success;
import static net.hzxzkj.cxlaike.framework.tenant.core.context.TenantContextHolder.getTenantId;

@Tag(name = "商户 - 快手")
@RestController
@RequestMapping("/cxlaike/kuai-shou")
@Validated
@Slf4j
public class KuaiShouController {

    @Resource
    private KuaiShouService kuaiShouService;
    @Resource
    private KuaiShouProperties kuaiShouProperties;
    private static final String SUCCESS = "成功";

    @GetMapping("/getAccreditUrl")
    @Operation(summary = "获得用户快手授权url")
    @PreAuthorize("@mss.hasPermission('cxlaike:kuai-shou:accredit')")
    public CommonResult<UserDyBindRespVO> getAccreditUrl(AccreditReqVO reqVO) {
        return success(kuaiShouService.buildAccreditUrl("",reqVO.getUserType(),getTenantId(),reqVO.getGroupIds()
                ,reqVO.getPoiId(),reqVO.getPoiName(),reqVO.getValidity(),kuaiShouProperties.getMerchantRedirectUrl()
                ,reqVO.getProvinceCode(),reqVO.getProvinceName(),reqVO.getCityCode(),reqVO.getCityName(),reqVO.getCountyCode()
                ,reqVO.getCountyName(),reqVO.getAddress(),reqVO.getLocation()));
    }


    @SneakyThrows
    @GetMapping("/getUserToken")
    @Operation(summary = "获取快手授权")
    //@PreAuthorize("@mss.hasPermission('cxlaike:dou-yin:accredit')")
    public CommonResult<Boolean> getUserToken(String code,String state, HttpServletResponse resp) {
        kuaiShouService.getUserToken(code,state,resp);
        resp.sendRedirect(String.format(kuaiShouProperties.getRedirectUrl(),"success", URLEncoder.encode(SUCCESS, "UTF-8")));
        return success(true);
    }

    @GetMapping("/getSignature")
    @Operation(summary = "获取签名")
    public CommonResult<String> getSignature(@RequestParam(value = "nonceStr")String nonceStr,
                                             @RequestParam(value = "timestamp")String timestamp,
                                             @RequestParam(value = "url")String url) {
        return success(kuaiShouService.getSignature(nonceStr,timestamp,url));
    }

    @GetMapping("/getAccreditUrlByState")
    @Operation(summary = "获得授权url(外部页面)")
    public CommonResult<String> getAccreditUrlByState(@RequestParam(value = "state")String state) {
        return success(kuaiShouService.getAccreditUrlByState(state));
    }

    @GetMapping("/getUserTokenWithOutRedirect")
    @Operation(summary = "获取快手授权(不带重定向)")
    public CommonResult<Boolean> getUserTokenWithOutRedirect(String code,String state) {
        try {
            kuaiShouService.getUserTokenWithOutRedirect(code, state);
        }catch (Exception ex){
            log.error("=========getUserTokenWithOutRedirect:{}",ex);
        }
        return success(true);
    }

    @GetMapping("/getAccreditUrlForScan")
    @Operation(summary = "获得授权url(外部页面-扫码)")
    public CommonResult<String> getAccreditUrlForScan(@RequestParam(value = "taskId")Long taskId) {
        return success(kuaiShouService.getAccreditUrlForScan(taskId));
    }

    @PostMapping("/dealWebhooks")
    @Operation(summary = "快手回调事件")
    @ResponseBody
    public Map<String, Object> dealWebhooks(@RequestBody DealWebhooksReqVO reqVO) {
        log.info("快手回调事件==============:{}", JsonUtils.toJsonString(reqVO));
        kuaiShouService.dealWebhooks(reqVO);
        return reqVO.getContent();
    }

    @GetMapping("/refreshAccessToken")
    @Operation(summary = "刷新快手令牌")
    @PreAuthorize("@mss.hasPermission('cxlaike:kuai-shou:accredit')")
    public CommonResult<String> refreshAccessToken(@RequestParam(value = "id",required = false)Long id) {
        return success(kuaiShouService.refreshAccessToken(id));
    }

    @GetMapping("/refreshVideoData")
    @Operation(summary = "刷新快手视频数据")
    @PreAuthorize("@mss.hasPermission('cxlaike:kuai-shou:accredit')")
    public void refreshVideoData(@RequestParam(value = "videoId",required = false)String videoId,@RequestParam(value = "tenantId",required = false)Long tenantId
            ,@RequestParam(value = "isAccountOnly",required = false)Integer isAccountOnly) {
        kuaiShouService.refreshVideoData(videoId,tenantId,isAccountOnly);
    }


}
