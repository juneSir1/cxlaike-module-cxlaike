package net.hzxzkj.cxlaike.module.cxlaike.job;

import lombok.extern.slf4j.Slf4j;
import net.hzxzkj.cxlaike.framework.quartz.core.handler.JobHandler;
import net.hzxzkj.cxlaike.framework.tenant.core.aop.TenantIgnore;
import net.hzxzkj.cxlaike.framework.tenant.core.context.TenantContextHolder;
import net.hzxzkj.cxlaike.framework.tenant.core.job.TenantJob;
import net.hzxzkj.cxlaike.framework.tenant.core.util.TenantUtils;
import net.hzxzkj.cxlaike.module.cxlaike.dal.redis.DyTokenRedisDAO;
import net.hzxzkj.cxlaike.module.cxlaike.enums.PlatformType;
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

import static net.hzxzkj.cxlaike.module.cxlaike.dal.RedisKeyConstants.*;

@Component
//@TenantJob // 标记多租户
@Slf4j
public class DyRefreshAccessTokenJob implements JobHandler {


    @Resource
    private MemberUserApi memberUserApi;

    @Resource
    private DyTokenRedisDAO dyTokenRedisDAO;
    @Resource
    private DouYinApiUtils douYinApiUtils;
    @Resource
    private SmsSendApi smsSendApi;
    @Resource
    private MemberDyTokenService memberDyTokenService;

    @Override
    @TenantIgnore
    public String execute(String param) throws Exception {
        log.info("【抖音刷新token】开始++++++++++++++++++");
        LocalDateTime refreshTime = LocalDateTime.now();
        Long refreshCount = 0L;
        //需要刷新refresh_token的用户
        List<DyTokenRespDTO> refreshTokenUsers = memberDyTokenService.getRefreshTokenUsers(refreshTime.toLocalDate(), PlatformType.TIKTOK.getType());

        for(DyTokenRespDTO respDTO : refreshTokenUsers){
//            String key = String.format(REFRESH_TOKEN_USERS.getKeyTemplate(), respDTO.getMemberId());
//            refreshCount = dyTokenRedisDAO.incr(key);//刷新次数
//            if(refreshCount > 5){//抖音刷新token最多5次
//                continue;
//            }

            RefreshTokenRespDTO newRefreshToken = douYinApiUtils.getNewRefreshToken(respDTO.getDyRefreshToken());
            if(newRefreshToken == null || newRefreshToken.getError_code() != 0){
                log.error("刷新令牌失败=========refreshToken：{}======={}",respDTO.getDyRefreshToken(),newRefreshToken);
                continue;
            }
            LocalDateTime dyRefreshExpiresTime = refreshTime.plusSeconds(newRefreshToken.getExpires_in());
            DyTokenReqDTO reqDTO = new DyTokenReqDTO();
            reqDTO.setDyRefreshExpiresTime(dyRefreshExpiresTime);
            reqDTO.setId(respDTO.getId());
            reqDTO.setDyRefreshToken(newRefreshToken.getRefresh_token());
            memberDyTokenService.updateDyToken(reqDTO);

            String accessKey = String.format(ACCESS_TOKEN_USERS.getKeyTemplate(), respDTO.getMemberId());
            dyTokenRedisDAO.delete(accessKey);
        }
        //需要刷新access_token的用户
        List<DyTokenRespDTO> refreshAccessTokenUsers = memberDyTokenService.getRefreshAccessTokenUsers(refreshTime.toLocalDate(),PlatformType.TIKTOK.getType());
        for(DyTokenRespDTO respDTO : refreshAccessTokenUsers){
            DyTokenReqDTO reqDTO = new DyTokenReqDTO();
            reqDTO.setId(respDTO.getId());

            String key = String.format(REFRESH_TOKEN_USERS.getKeyTemplate(), respDTO.getMemberId());
            AccessTokenRespDTO newAccessToken = douYinApiUtils.getNewAccessToken(respDTO.getDyRefreshToken());
            if(newAccessToken == null || newAccessToken.getError_code() != 0){
                reqDTO.setDyAccreditStatus(2);//授权失效
                memberDyTokenService.updateDyToken(reqDTO);
                continue;
            }
            LocalDateTime dyExpiresTime = refreshTime.plusSeconds(newAccessToken.getExpires_in());
            reqDTO.setDyAccessToken(newAccessToken.getAccess_token());
            reqDTO.setDyExpiresTime(dyExpiresTime);
            memberDyTokenService.updateDyToken(reqDTO);
        }



        log.info("【抖音刷新token】结束++++++++++++++++++");
        return "";
    }

}
