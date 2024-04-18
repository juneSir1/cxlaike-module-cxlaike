package net.hzxzkj.cxlaike.module.cxlaike.job;

import lombok.extern.slf4j.Slf4j;
import net.hzxzkj.cxlaike.framework.quartz.core.handler.JobHandler;
import net.hzxzkj.cxlaike.framework.tenant.core.aop.TenantIgnore;
import net.hzxzkj.cxlaike.module.cxlaike.dal.redis.DyTokenRedisDAO;
import net.hzxzkj.cxlaike.module.cxlaike.enums.PlatformType;
import net.hzxzkj.cxlaike.module.cxlaike.service.memberdytoken.MemberDyTokenService;
import net.hzxzkj.cxlaike.module.cxlaike.utils.douyin.DouYinApiUtils;
import net.hzxzkj.cxlaike.module.cxlaike.utils.douyin.vo.AccessTokenRespDTO;
import net.hzxzkj.cxlaike.module.cxlaike.utils.douyin.vo.RefreshTokenRespDTO;
import net.hzxzkj.cxlaike.module.cxlaike.utils.kuaishou.KuaiShouApiUtils;
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
public class KuaishouRefreshAccessTokenJob implements JobHandler {

    @Resource
    private DyTokenRedisDAO dyTokenRedisDAO;
    @Resource
    private KuaiShouApiUtils kuaiShouApiUtils;

    @Resource
    private MemberDyTokenService memberDyTokenService;

    @Override
    @TenantIgnore
    public String execute(String param) throws Exception {
        log.info("【快手刷新token】开始++++++++++++++++++");
        LocalDateTime refreshTime = LocalDateTime.now();

        //需要刷新access_token的用户
        List<DyTokenRespDTO> refreshAccessTokenUsers = memberDyTokenService.getRefreshAccessTokenUsers(refreshTime.toLocalDate(),PlatformType.KUAISHOU.getType());
        for(DyTokenRespDTO respDTO : refreshAccessTokenUsers){
            DyTokenReqDTO reqDTO = new DyTokenReqDTO();
            reqDTO.setId(respDTO.getId());

            AccessTokenRespDTO newAccessToken = kuaiShouApiUtils.getNewAccessToken(respDTO.getDyRefreshToken());
            if(newAccessToken == null || newAccessToken.getAccess_token() == null){
                reqDTO.setDyAccreditStatus(2);//授权失效
                memberDyTokenService.updateDyToken(reqDTO);
                continue;
            }
            LocalDateTime dyExpiresTime = refreshTime.plusSeconds(newAccessToken.getExpires_in());
            reqDTO.setDyAccessToken(newAccessToken.getAccess_token());
            reqDTO.setDyExpiresTime(dyExpiresTime);
            reqDTO.setDyRefreshExpiresTime(refreshTime.plusSeconds(newAccessToken.getRefresh_expires_in()));
            reqDTO.setDyRefreshToken(newAccessToken.getRefresh_token());
            memberDyTokenService.updateDyToken(reqDTO);
        }

        log.info("【快手刷新token】结束++++++++++++++++++");
        return "";
    }

}
