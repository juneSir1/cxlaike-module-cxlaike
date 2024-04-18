package net.hzxzkj.cxlaike.module.cxlaike.job;

import lombok.extern.slf4j.Slf4j;
import net.hzxzkj.cxlaike.framework.quartz.core.handler.JobHandler;
import net.hzxzkj.cxlaike.framework.tenant.core.aop.TenantIgnore;
import net.hzxzkj.cxlaike.module.cxlaike.dal.redis.DyTokenRedisDAO;
import net.hzxzkj.cxlaike.module.cxlaike.service.douyin.DouYinService;
import net.hzxzkj.cxlaike.module.cxlaike.service.memberdytoken.MemberDyTokenService;
import net.hzxzkj.cxlaike.module.cxlaike.utils.douyin.DouYinApiUtils;
import net.hzxzkj.cxlaike.module.cxlaike.utils.douyin.vo.AccessTokenRespDTO;
import net.hzxzkj.cxlaike.module.cxlaike.utils.douyin.vo.RefreshTokenRespDTO;
import net.hzxzkj.cxlaike.module.member.api.user.MemberUserApi;
import net.hzxzkj.cxlaike.module.member.api.user.dto.DyTokenReqDTO;
import net.hzxzkj.cxlaike.module.member.api.user.dto.DyTokenRespDTO;
import net.hzxzkj.cxlaike.module.member.api.user.dto.MemberUserRespDTO;
import net.hzxzkj.cxlaike.module.system.api.sms.SmsSendApi;
import net.hzxzkj.cxlaike.module.system.api.sms.dto.send.SmsSendSingleToUserReqDTO;
import net.hzxzkj.cxlaike.module.system.enums.sms.SmsSceneEnum;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static net.hzxzkj.cxlaike.module.cxlaike.dal.RedisKeyConstants.ACCESS_TOKEN_USERS;
import static net.hzxzkj.cxlaike.module.cxlaike.dal.RedisKeyConstants.REFRESH_TOKEN_USERS;

@Component
//@TenantJob // 标记多租户
@Slf4j
public class DyMusicRefreshJob implements JobHandler {


    @Resource
    private DyTokenRedisDAO dyTokenRedisDAO;
    @Resource
    private DouYinService douYinService;
    @Resource
    private DouYinApiUtils douYinApiUtils;
    @Resource
    private SmsSendApi smsSendApi;
    @Resource
    private MemberDyTokenService memberDyTokenService;

    @Override
    @TenantIgnore
    public String execute(String param) throws Exception {
        log.info("【抖音刷新音乐榜】开始++++++++++++++++++");
        douYinService.getMusicCharts();
        log.info("【抖音刷新token】结束++++++++++++++++++");
        return "";
    }

}
