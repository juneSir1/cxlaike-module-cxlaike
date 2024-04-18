package net.hzxzkj.cxlaike.module.cxlaike.utils.kuaishou;

import cn.hutool.core.util.StrUtil;
import com.github.kwai.open.KwaiOpenException;
import com.github.kwai.open.api.KwaiOpenOauthApi;
import com.github.kwai.open.api.KwaiOpenUserApi;
import com.github.kwai.open.api.KwaiOpenVideoApi;
import com.github.kwai.open.model.UserInfo;
import com.github.kwai.open.request.*;
import com.github.kwai.open.response.*;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import net.hzxzkj.cxlaike.framework.common.util.http.Http2Utils;
import net.hzxzkj.cxlaike.framework.common.util.json.JsonUtils;
import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.dypersonalletter.vo.DyPersonalLetterSendDTO;
import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.dypersonalletter.vo.DyPersonalLetterSendTextDTO;
import net.hzxzkj.cxlaike.module.cxlaike.utils.douyin.vo.*;
import net.hzxzkj.cxlaike.module.cxlaike.utils.kuaishou.config.KuaiShouProperties;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.io.File;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author tk
 * @date 2024-01-04 15:23
 * @description
 */
@Component
@Slf4j
public class KuaiShouApiUtils {

    @Resource
    private RestTemplate restTemplate;

    @Resource
    private KuaiShouProperties kuaiShouProperties;

    private static final String DEFAULT_OPENID = "00000000";

    public String getDefaultOpenId(){
        return DEFAULT_OPENID;
    }


    /**
     * 构建获取授权码url
     *
     * @return java.lang.String
     * @author hjn
     * @date 2023-08-22 10:22
     */
    public String buildUrl(String state,String redirectUrl) {

        return String.format(kuaiShouProperties.getOauthUrl()
                , kuaiShouProperties.getAppId()
                , redirectUrl
                , kuaiShouProperties.getScope()
                ,state);
    }


    public String buildQrUrl(String state,String redirectUrl){
        return null;//String.format(kuaiShouProperties.getQrUrl(),kuaiShouProperties.getAppId(),kuaiShouProperties.getScope(),redirectUrl,state);
    }
    public String buildCheckUrl(String state,String redirectUrl){
        return null;//String.format(kuaiShouProperties.getCheckUrl(),kuaiShouProperties.getAppId(),kuaiShouProperties.getScope(),redirectUrl,state);
    }



    /**
     * 获取token信息
     *
     * @param code
     * @return cn.iocoder.yudao.module.engineering.utils.yingshi.douyin.DouYinResult
     * <cn.iocoder.yudao.module.engineering.utils.yingshi.douyin.AccessTokenRespDTO>
     * @author hjn
     * @date 2023-08-17 15:11
     */
    public AccessTokenRespDTO getAccessToken(String code) throws  Exception{
        AccessTokenRequest request = new AccessTokenRequest();
        request.setCode(code);
        request.setAppSecret(kuaiShouProperties.getAppSecret());
        KwaiOpenOauthApi kwaiOpenOauthApi = KwaiOpenOauthApi.init(kuaiShouProperties.getAppId());
        AccessTokenResponse accessTokenResponse = kwaiOpenOauthApi.getAccessToken(request);
        AccessTokenRespDTO accessTokenRespDTO = new AccessTokenRespDTO();
        log.info("========accessTokenResponse info:{}======"+accessTokenResponse.toString());
        //{accessToken='ChFvYXV0aC5hY2Nlc3NUb2tlbhJgsskqLS3o3JXyIyO_4TlCiXg4hnAgjPuh4yaAZvrJ_AAL071LBdTu8PF9eJwtblaKYjkGXvmp7FCbtT3ArGr68vCCs3K4lu96tR1oqOphVefteYzK_7PbGMbZaF6Ya6YlGhI3fO2jrXtNfq7z9Xoxtovjx-YiIFMU0QdqSCLmhcdsTTnxADdIx5C-jpXLpL9eXFfqM3FxKAUwAQ', expiresIn=172800, refreshToken='ChJvYXV0aC5yZWZyZXNoVG9rZW4SoAFxDlXqn1CaWDMweVnL4qV9IsyWmCxl70t9KWiWEajwMkOTKGPZQCWPpo_iwnDEG6a-6m3QsEmjYfYrcTgE56c47tMeQ3_PXgbGq_kklxlNzTc8lVThDl7uuJQyJOJxJGQbq-vkmYQJaO2G3AsvgltPfr3mLlWpJ3eVSve4cG-Z29Rif-AIlaySOk-wBbcV2Xj1zb7cr8xOGYfdXOdNfiDeGhJhM6Gbh8VBrCGSwMifpJGyOhwiIEj-d4X5ZA1scVx0UvtYzL-GsujQt9lG9-2zF6YVTVzZKAUwAQ', openId='f1ba8bcb6e2de40c14bf572402838921', scopes=[user_base, user_info, user_video_delete, user_video_publish, user_video_info], refreshTokenExpiresIn=15552000}
        if(accessTokenResponse!=null){
            accessTokenRespDTO.setOpen_id(accessTokenResponse.getOpenId());
            accessTokenRespDTO.setAccess_token(accessTokenResponse.getAccessToken());
            accessTokenRespDTO.setExpires_in(accessTokenResponse.getExpiresIn());
            accessTokenRespDTO.setRefresh_expires_in(accessTokenResponse.getRefreshTokenExpiresIn());
            accessTokenRespDTO.setRefresh_token(accessTokenResponse.getRefreshToken());
            accessTokenRespDTO.setScope(accessTokenResponse.getScopes().toString());
            accessTokenRespDTO.setError_code((long)accessTokenResponse.getResultCode().getCode());
        }
        return accessTokenRespDTO;
    }

    /**
     * 刷新access_token
     *
     * @author hjn
     * @date 2023-08-17 15:11
     */
    public AccessTokenRespDTO getNewAccessToken(String refreshToken) throws  Exception{
        try {
            KwaiOpenOauthApi kwaiOpenOauthApi = KwaiOpenOauthApi.init(kuaiShouProperties.getAppId());
            RefreshTokenRequest request = new RefreshTokenRequest(refreshToken, kuaiShouProperties.getAppSecret());
            RefreshTokenResponse refreshTokenResponse = kwaiOpenOauthApi.refreshToken(request);
            AccessTokenRespDTO accessTokenRespDTO = new AccessTokenRespDTO();
            if (refreshTokenResponse != null) {
                accessTokenRespDTO.setExpires_in(refreshTokenResponse.getExpiresIn());
                accessTokenRespDTO.setAccess_token(refreshTokenResponse.getAccessToken());
                accessTokenRespDTO.setError_code((long) refreshTokenResponse.getResultCode().getCode());
                accessTokenRespDTO.setRefresh_token(refreshTokenResponse.getRefreshToken());
                accessTokenRespDTO.setRefresh_expires_in(refreshTokenResponse.getRefreshTokenExpiresIn());
            }
            return accessTokenRespDTO;
        }catch (Exception ex){
            log.error("=========getNewAccessToken====={}",ex);
        }
        return null;
    }

    /**
     * 刷新refresh_token
     *
     * @author hjn
     * @date 2023-08-17 15:11
     */
    public RefreshTokenRespDTO getNewRefreshToken(String refreshToken) throws  Exception{
        try{
            KwaiOpenOauthApi kwaiOpenOauthApi = KwaiOpenOauthApi.init(kuaiShouProperties.getAppId());
            RefreshTokenRequest request = new RefreshTokenRequest(refreshToken, kuaiShouProperties.getAppSecret());
            RefreshTokenResponse refreshTokenResponse = kwaiOpenOauthApi.refreshToken(request);
            RefreshTokenRespDTO refreshTokenRespDTO = new RefreshTokenRespDTO();
            if(refreshTokenResponse!=null){
                refreshTokenRespDTO.setExpires_in(refreshTokenResponse.getExpiresIn());
                refreshTokenRespDTO.setRefresh_token(refreshTokenResponse.getRefreshToken());
                refreshTokenRespDTO.setError_code((long)refreshTokenResponse.getResultCode().getCode());
            }
            return refreshTokenRespDTO;
        }catch (Exception ex){
            log.error("=========getNewRefreshToken====={}",ex);
        }
        return null;
    }

    /**
     * 获取client_token信息
     *
     * @return cn.iocoder.yudao.module.engineering.utils.yingshi.douyin.DouYinResult
     * <cn.iocoder.yudao.module.engineering.utils.yingshi.douyin.AccessTokenRespDTO>
     * @author hjn
     * @date 2023-08-17 15:11
     */
    public AccessTokenRespDTO getClientToken() {
        // 1.1 构建请求头
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        // 1.2 构建请求参数
        Map<String, Object> body = new HashMap<>();
        body.put("grant_type", "client_credential");
        body.put("client_key", kuaiShouProperties.getAppId());
        body.put("client_secret", kuaiShouProperties.getAppSecret());

        return null;
        // 2. 执行请求
//        ResponseEntity<CreateVideoRespDTO> exchange = restTemplate.exchange(
//                kuaiShouProperties.getCreateVideoUrl() + "?open_id=" + openId,
//                HttpMethod.POST,
//                new HttpEntity<>(body, headers),
//                new ParameterizedTypeReference<CreateVideoRespDTO>() {
//                }); // 解决泛型丢失
//        Assert.isTrue(exchange.getStatusCode().is2xxSuccessful(), "响应必须是 200 成功");
//        return exchange.getBody();
    }

    /**
     * 获取用户信息
     *
     * @param accessToken
     * @param openId
     * @return cn.iocoder.yudao.module.engineering.utils.yingshi.douyin.DouYinResult
     * <cn.iocoder.yudao.module.engineering.utils.yingshi.douyin.UserInfoRespDTO>
     * @author hjn
     * @date 2023-08-17 15:11
     */
    public UserInfoRespDTO getUserInfo(String accessToken, String openId) throws Exception{

        KwaiOpenUserApi kwaiOpenUserApi = KwaiOpenUserApi.init(kuaiShouProperties.getAppId());
        UserInfoRequest request = new UserInfoRequest();
        request.setAccessToken(accessToken);
        UserInfoResponse userInfoResponse = kwaiOpenUserApi.getUserInfo(request);
        UserInfoRespDTO userInfoRespDTO = new UserInfoRespDTO();
        log.info("========userInfoResponse info:{}======"+userInfoResponse.toString());
        if(userInfoResponse!=null){
            UserInfo userInfo = userInfoResponse.getUserInfo();
            userInfoRespDTO.setAvatar(userInfo.getHead());
            userInfoRespDTO.setNickname(userInfo.getName());
            userInfoRespDTO.setFan(userInfo.getFan());
            userInfoRespDTO.setFollow(userInfo.getFollow());
            userInfoRespDTO.setError_code((long)userInfoResponse.getResultCode().getCode());
        }
        return userInfoRespDTO;
    }


    /**
     * 上传视频
     *
     * @param accessToken
     * @return cn.iocoder.yudao.module.engineering.utils.yingshi.douyin.DouYinResult
     * <cn.iocoder.yudao.module.engineering.utils.yingshi.douyin.UserInfoRespDTO>
     * @author hjn
     * @date 2023-08-17 15:11
     */
    public StartUploadResponse startUpload(String accessToken) throws KwaiOpenException {
//        // 1.1 构建请求头
//        HttpHeaders headers = new HttpHeaders();
////        headers.add("content-type","multipart/form-data");
//        // 1.2 构建请求参数
//        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        KwaiOpenVideoApi videoApi = KwaiOpenVideoApi.init(kuaiShouProperties.getAppId());
        StartUploadRequest request = new StartUploadRequest();
        request.setAccessToken(accessToken);
        return videoApi.startUpload(request);
        // 2. 执行请求
//        ResponseEntity<KuaiShouStartUploadVideoRespDTO> exchange = restTemplate.exchange(
//                String.format(kuaiShouProperties.getUserVideoPublishStartUrl(), kuaiShouProperties.getAppId(), accessToken),
//                HttpMethod.POST,
//                new HttpEntity<>(body, headers),
//                new ParameterizedTypeReference<KuaiShouStartUploadVideoRespDTO>() {
//                }); // 解决泛型丢失
//        Assert.isTrue(exchange.getStatusCode().is2xxSuccessful(), "响应必须是 200 成功");
//        return exchange.getBody();
    }


    /**
     * 上传视频
     *
     * @param
     * @return cn.iocoder.yudao.module.engineering.utils.yingshi.douyin.DouYinResult
     * <cn.iocoder.yudao.module.engineering.utils.yingshi.douyin.UserInfoRespDTO>
     * @author hjn
     * @date 2023-08-17 15:11
     */
    public UploadFileResponse uploadVideo(String uploadToken, String endpoint, byte[] file) throws KwaiOpenException {
        // 1.1 构建请求头
//        HttpHeaders headers = new HttpHeaders();
//        headers.add("Content-Type","video/mp4");
        // 1.2 构建请求参数
//        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();

        // 2. 执行请求
        KwaiOpenVideoApi videoApi = KwaiOpenVideoApi.init(kuaiShouProperties.getAppId());
        UploadFileRequest request = new UploadFileRequest();
        request.setEndPoint(endpoint);
        request.setUploadToken(uploadToken);
        request.setFileData(file);
        return videoApi.uploadFile(request);

//        ResponseEntity<KuaiShouUploadBaseDTO> exchange = restTemplate.exchange(
//                String.format(kuaiShouProperties.getUploadBinaryUrl(), endpoint, uploadToken),
//                HttpMethod.POST,
//                new HttpEntity<>(file, headers),
//                new ParameterizedTypeReference<KuaiShouUploadBaseDTO>() {
//                }); // 解决泛型丢失
//        Assert.isTrue(exchange.getStatusCode().is2xxSuccessful(), "响应必须是 200 成功");
//        return exchange.getBody();
    }


    /**
     * 分片上传视频
     *
     * @param
     * @return cn.iocoder.yudao.module.engineering.utils.yingshi.douyin.DouYinResult
     * <cn.iocoder.yudao.module.engineering.utils.yingshi.douyin.UserInfoRespDTO>
     * @author hjn
     * @date 2023-08-17 15:11
     */
    public UploadFragmentResponse uploadFragment(String uploadToken, String endpoint, byte[] file,Integer partNumber) throws KwaiOpenException {
        // 1.1 构建请求头
//        HttpHeaders headers = new HttpHeaders();
//        headers.add("content-type","multipart/form-data");
//        headers.add("access-token", accessToken);
        // 1.2 构建请求参数
//        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
//        body.add("video", new FileSystemResource(file));
        KwaiOpenVideoApi videoApi = KwaiOpenVideoApi.init(kuaiShouProperties.getAppId());
        UploadFragmentRequest request = new UploadFragmentRequest();
        request.setEndPoint(endpoint);
        request.setUploadToken(uploadToken);
        request.setFragmentId(partNumber);
        request.setFileData(file);
        return videoApi.uploadFileFragment(request);
        // 2. 执行请求
//        ResponseEntity<KuaiShouFragmentUploadVideoRespDTO> exchange = restTemplate.exchange(
//                String.format(kuaiShouProperties.getUploadFragmentUrl(), endpoint, uploadToken, partNumber),
//                HttpMethod.POST,
//                new HttpEntity<byte[]>(file, headers),
//                new ParameterizedTypeReference<KuaiShouFragmentUploadVideoRespDTO>() {
//                }); // 解决泛型丢失
//        Assert.isTrue(exchange.getStatusCode().is2xxSuccessful(), "响应必须是 200 成功");
//        return exchange.getBody();
    }

    /**
     * 分片上传完成
     *
     * @param uploadToken token
     * @author hjn
     * @date 2023-08-17 15:11
     */
    public UploadCompleteResponse uploadComplete(String uploadToken,String endpoint,Integer partNumber) throws KwaiOpenException {
        // 1.1 构建请求头
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON);
//        headers.add("access-token", accessToken);
        // 1.2 构建请求参数
//        Map<String, Object> body = new HashMap<>();
        KwaiOpenVideoApi videoApi = KwaiOpenVideoApi.init(kuaiShouProperties.getAppId());
        UploadCompleteRequest request = new UploadCompleteRequest();
        request.setEndPoint(endpoint);
        request.setUploadToken(uploadToken);
        request.setFragmentCount(partNumber);
        return videoApi.uploadFileFragmentComplete(request);
        // 2. 执行请求
//        ResponseEntity<KuaiShouUploadBaseDTO> exchange = restTemplate.exchange(
//                String.format(kuaiShouProperties.getUploadCompleteUrl(), endpoint, uploadToken, partNumber),
//                HttpMethod.POST,
//                new HttpEntity<>(body, headers),
//                new ParameterizedTypeReference<KuaiShouUploadBaseDTO>() {
//                }); // 解决泛型丢失
//        Assert.isTrue(exchange.getStatusCode().is2xxSuccessful(), "响应必须是 200 成功");
//        return exchange.getBody();
    }

    /**
     * 发布视频
     *
     * @param
     * @return cn.iocoder.yudao.module.engineering.utils.yingshi.douyin.DouYinResult
     * <cn.iocoder.yudao.module.engineering.utils.yingshi.douyin.UserInfoRespDTO>
     * @author hjn
     * @date 2023-08-17 15:11
     */
    public VideoPublishResponse publishVideo(String accessToken,String uploadToken,byte[] cover, String caption,  Long merchantProductId) throws KwaiOpenException {
        // 1.1 构建请求头
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
//        headers.add("Content-Type", MediaType.MULTIPART_FORM_DATA_VALUE);
        // 1.2 构建请求参数
//        MultiValueMap<String,Object> body = new LinkedMultiValueMap<>();
//        body.add("cover", cover);
//        body.add("caption", caption);
//        body.add("stereo_type", poiId);
//        if(StrUtil.isNotEmpty(merchantProductId)){
//            body.add("merchant_product_id ", merchantProductId);
//        }
        KwaiOpenVideoApi videoApi = KwaiOpenVideoApi.init(kuaiShouProperties.getAppId());
        VideoPublishRequest request = new VideoPublishRequest();
        request.setAccessToken(accessToken);
        request.setUploadToken(uploadToken);
        request.setCover(cover);
        request.setCaption(caption);
//        request.setStereoType();

        if(null != merchantProductId){
            request.setMerchantProductId(merchantProductId);
        }
        return videoApi.videoPublish(request);

        // 2. 执行请求
//        ResponseEntity<KuaiShouPublishVideoRespDTO> exchange = restTemplate.exchange(
//                String.format(kuaiShouProperties.getUserVideoPublishUrl(), kuaiShouProperties.getAppId(), accessToken, uploadToken),
//                HttpMethod.POST,
//                new HttpEntity<>(body, headers),
//                new ParameterizedTypeReference<KuaiShouPublishVideoRespDTO>() {
//                }); // 解决泛型丢失
//        Assert.isTrue(exchange.getStatusCode().is2xxSuccessful(), "响应必须是 200 成功");
//        return exchange.getBody();
    }



    /**
     * 获取视频信息
     *
     * @param
     * @return cn.iocoder.yudao.module.engineering.utils.yingshi.douyin.DouYinResult
     * <cn.iocoder.yudao.module.engineering.utils.yingshi.douyin.UserInfoRespDTO>
     * @author hjn
     * @date 2023-08-17 15:11
     */
    public VideoInfoResponse getVideoData(String videoId, String accessToken) throws KwaiOpenException {
        KwaiOpenVideoApi videoApi = KwaiOpenVideoApi.init(kuaiShouProperties.getAppId());
        VideoInfoRequest request = new VideoInfoRequest();
        request.setAccessToken(accessToken);
        request.setPhotoId(videoId);

        return videoApi.queryVideoInfo(request);
    }

    /**
     * 获取用户粉丝信息
     *
     * @return cn.iocoder.yudao.module.engineering.utils.yingshi.douyin.DouYinResult
     * <cn.iocoder.yudao.module.engineering.utils.yingshi.douyin.UserInfoRespDTO>
     * @author hjn
     * @date 2023-08-17 15:11
     */
    public String getVideoCode(String url) {
        // 1.1 构建请求头
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.TEXT_HTML);
        // 1.2 构建请求参数
        Map<String, Object> body = new HashMap<>();
//        body.add("open_id", openId);

        // 2. 执行请求
        ResponseEntity<DouYinResult<String>> exchange = restTemplate.exchange(
                url,//7表示近7天数据
                HttpMethod.GET,
                new HttpEntity<>(body, headers),
                new ParameterizedTypeReference<DouYinResult<String>>() {
                }); // 解决泛型丢失
        Assert.isTrue(exchange.getStatusCode().is2xxSuccessful(), "响应必须是 200 成功");
        return exchange.getBody().getData();
    }

    /**
     * item
     *
     * @param openId
     * @return cn.iocoder.yudao.module.engineering.utils.yingshi.douyin.DouYinResult
     * <cn.iocoder.yudao.module.engineering.utils.yingshi.douyin.UserInfoRespDTO>
     * @author hjn
     * @date 2023-08-17 15:11
     */
    public VideoBaseRespDTO getVideoBaseDatas(String accessToken,String openId,String itemId) {
        // 1.1 构建请求头
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("access-token", accessToken);
        // 1.2 构建请求参数
        Map<String, Object> body = new HashMap<>();
//        body.add("open_id", openId);

        return null;
        // 2. 执行请求
//        ResponseEntity<CreateVideoRespDTO> exchange = restTemplate.exchange(
//                kuaiShouProperties.getCreateVideoUrl() + "?open_id=" + openId,
//                HttpMethod.POST,
//                new HttpEntity<>(body, headers),
//                new ParameterizedTypeReference<CreateVideoRespDTO>() {
//                }); // 解决泛型丢失
//        Assert.isTrue(exchange.getStatusCode().is2xxSuccessful(), "响应必须是 200 成功");
//        return exchange.getBody();
    }


    /**
     * 获取jsb_ticket
     *
     * @param accessToken
     * @return cn.iocoder.yudao.module.engineering.utils.yingshi.douyin.DouYinResult
     * <cn.iocoder.yudao.module.engineering.utils.yingshi.douyin.UserInfoRespDTO>
     * @author hjn
     * @date 2023-08-17 15:11
     */
    public TicketRespDTO getTicket(String accessToken) {
        // 1.1 构建请求头
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("access-token", accessToken);
        // 1.2 构建请求参数
        Map<String, Object> body = new HashMap<>();
//        body.add("open_id", openId);

        return null;
        // 2. 执行请求
//        ResponseEntity<CreateVideoRespDTO> exchange = restTemplate.exchange(
//                kuaiShouProperties.getCreateVideoUrl() + "?open_id=" + openId,
//                HttpMethod.POST,
//                new HttpEntity<>(body, headers),
//                new ParameterizedTypeReference<CreateVideoRespDTO>() {
//                }); // 解决泛型丢失
//        Assert.isTrue(exchange.getStatusCode().is2xxSuccessful(), "响应必须是 200 成功");
//        return exchange.getBody();
    }


    /**
     * 获取share_id
     *
     * @param accessToken
     * @return cn.iocoder.yudao.module.engineering.utils.yingshi.douyin.DouYinResult
     * <cn.iocoder.yudao.module.engineering.utils.yingshi.douyin.UserInfoRespDTO>
     * @author hjn
     * @date 2023-08-17 15:11
     */
    public ShareIdRespDTO getShareId(String accessToken) {
        // 1.1 构建请求头
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("access-token", accessToken);
        // 1.2 构建请求参数
        Map<String, Object> body = new HashMap<>();
//        body.add("open_id", openId);

        return null;
        // 2. 执行请求
//        ResponseEntity<CreateVideoRespDTO> exchange = restTemplate.exchange(
//                kuaiShouProperties.getCreateVideoUrl() + "?open_id=" + openId,
//                HttpMethod.POST,
//                new HttpEntity<>(body, headers),
//                new ParameterizedTypeReference<CreateVideoRespDTO>() {
//                }); // 解决泛型丢失
//        Assert.isTrue(exchange.getStatusCode().is2xxSuccessful(), "响应必须是 200 成功");
//        return exchange.getBody();
    }

    /**
     * 获取jsb_ticket
     *
     * @param accessToken
     * @return cn.iocoder.yudao.module.engineering.utils.yingshi.douyin.DouYinResult
     * <cn.iocoder.yudao.module.engineering.utils.yingshi.douyin.UserInfoRespDTO>
     * @author hjn
     * @date 2023-08-17 15:11
     */
    public TicketRespDTO getOpenTicket(String accessToken) {
        // 1.1 构建请求头
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("access-token", accessToken);
        // 1.2 构建请求参数
        Map<String, Object> body = new HashMap<>();
//        body.add("open_id", openId);

        return null;
        // 2. 执行请求
//        ResponseEntity<CreateVideoRespDTO> exchange = restTemplate.exchange(
//                kuaiShouProperties.getCreateVideoUrl() + "?open_id=" + openId,
//                HttpMethod.POST,
//                new HttpEntity<>(body, headers),
//                new ParameterizedTypeReference<CreateVideoRespDTO>() {
//                }); // 解决泛型丢失
//        Assert.isTrue(exchange.getStatusCode().is2xxSuccessful(), "响应必须是 200 成功");
//        return exchange.getBody();
    }

    /**
     * 发送私信
     *
     * @param openId
     * @return cn.iocoder.yudao.module.engineering.utils.yingshi.douyin.DouYinResult
     * <cn.iocoder.yudao.module.engineering.utils.yingshi.douyin.UserInfoRespDTO>
     * @author hjn
     * @date 2023-08-17 15:11
     */
    public SendMsgRespDTO sendMsg(String accessToken, String openId,String msgId,String conversationId,String toUserId,String textStr){
        // 1.1 构建请求头
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("access-token", accessToken);
        // 1.2 构建请求参数

        DyPersonalLetterSendDTO sendDTO = new DyPersonalLetterSendDTO();
        DyPersonalLetterSendTextDTO textDTO = new DyPersonalLetterSendTextDTO();
        textDTO.setText(textStr);
        sendDTO.setText(textDTO);
        Map<String, Object> body = new HashMap<>();
        body.put("msg_id", msgId);
        body.put("conversation_id", conversationId);
        body.put("to_user_id", toUserId);
        body.put("content", sendDTO);
        body.put("scene", "im_replay_msg");
        System.out.println(JsonUtils.toJsonString(sendDTO));
        System.out.println(JsonUtils.toJsonString(body));

        return null;
        // 2. 执行请求
//        ResponseEntity<CreateVideoRespDTO> exchange = restTemplate.exchange(
//                kuaiShouProperties.getCreateVideoUrl() + "?open_id=" + openId,
//                HttpMethod.POST,
//                new HttpEntity<>(body, headers),
//                new ParameterizedTypeReference<CreateVideoRespDTO>() {
//                }); // 解决泛型丢失
//        Assert.isTrue(exchange.getStatusCode().is2xxSuccessful(), "响应必须是 200 成功");
//        return exchange.getBody();
    }


    /**
     * 回复视频评论
     *
     * @param openId
     * @return cn.iocoder.yudao.module.engineering.utils.yingshi.douyin.DouYinResult
     * <cn.iocoder.yudao.module.engineering.utils.yingshi.douyin.UserInfoRespDTO>
     * @author hjn
     * @date 2023-08-17 15:11
     */
    public CommentReplyRespDTO commentReply(String accessToken, String openId,String itemId,String commentId,String content){
        // 1.1 构建请求头
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("access-token", accessToken);
        // 1.2 构建请求参数

        Map<String, Object> body = new HashMap<>();
        body.put("item_id", itemId);
        body.put("comment_id", commentId);
        body.put("content", content);

        return null;
        // 2. 执行请求
//        ResponseEntity<CreateVideoRespDTO> exchange = restTemplate.exchange(
//                kuaiShouProperties.getCreateVideoUrl() + "?open_id=" + openId,
//                HttpMethod.POST,
//                new HttpEntity<>(body, headers),
//                new ParameterizedTypeReference<CreateVideoRespDTO>() {
//                }); // 解决泛型丢失
//        Assert.isTrue(exchange.getStatusCode().is2xxSuccessful(), "响应必须是 200 成功");
//        return exchange.getBody();
    }

    /**
     * 置顶评论
     *
     * @param openId
     * @return cn.iocoder.yudao.module.engineering.utils.yingshi.douyin.DouYinResult
     * <cn.iocoder.yudao.module.engineering.utils.yingshi.douyin.UserInfoRespDTO>
     * @author hjn
     * @date 2023-08-17 15:11
     */
    public CommentTopRespDTO commentTop(String accessToken, String openId,String itemId,String commentId){
        // 1.1 构建请求头
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("access-token", accessToken);
        // 1.2 构建请求参数

        Map<String, Object> body = new HashMap<>();
        body.put("item_id", itemId);
        body.put("comment_id", commentId);
        body.put("top", true);

        return null;
        // 2. 执行请求
//        ResponseEntity<CreateVideoRespDTO> exchange = restTemplate.exchange(
//                kuaiShouProperties.getCreateVideoUrl() + "?open_id=" + openId,
//                HttpMethod.POST,
//                new HttpEntity<>(body, headers),
//                new ParameterizedTypeReference<CreateVideoRespDTO>() {
//                }); // 解决泛型丢失
//        Assert.isTrue(exchange.getStatusCode().is2xxSuccessful(), "响应必须是 200 成功");
//        return exchange.getBody();
    }


//    }
}
