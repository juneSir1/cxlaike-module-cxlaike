package net.hzxzkj.cxlaike.module.cxlaike.utils.douyin;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import net.hzxzkj.cxlaike.framework.common.util.http.Http2Utils;
import net.hzxzkj.cxlaike.framework.common.util.json.JsonUtils;
import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.dypersonalletter.vo.DyPersonalLetterSendDTO;
import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.dypersonalletter.vo.DyPersonalLetterSendTextDTO;
import net.hzxzkj.cxlaike.module.cxlaike.utils.douyin.config.DouYinProperties;
import net.hzxzkj.cxlaike.module.cxlaike.utils.douyin.vo.*;
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
import java.text.DecimalFormat;
import java.util.*;

/**
 * @author jianan.han
 * @date 2023-08-17 10:23
 * @description
 */
@Component
@Slf4j
public class DouYinApiUtils {



    @Resource
    private RestTemplate restTemplate;

    @Resource
    private DouYinProperties douYinProperties;

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

        return String.format(douYinProperties.getOauthUrl()
                , douYinProperties.getAppId()
                , redirectUrl
                , douYinProperties.getScope()
                ,state);
    }


    public String buildQrUrl(String state,String redirectUrl){
        return String.format(douYinProperties.getQrUrl(),douYinProperties.getAppId(),douYinProperties.getScope(),redirectUrl,state);
    }
    public String buildCheckUrl(String state,String redirectUrl){
        return String.format(douYinProperties.getCheckUrl(),douYinProperties.getAppId(),douYinProperties.getScope(),redirectUrl,state);
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
    public AccessTokenRespDTO getAccessToken(String code) {
        // 1.1 构建请求头
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        // 1.2 构建请求参数
        Map<String, Object> body = new HashMap<>();
        body.put("grant_type", "authorization_code");
        body.put("client_key", douYinProperties.getAppId());
        body.put("client_secret", douYinProperties.getAppSecret());
        body.put("code", code);

        // 2. 执行请求
        ResponseEntity<DouYinResult<AccessTokenRespDTO>> exchange = restTemplate.exchange(
                douYinProperties.getAccessTokenUrl(),
                HttpMethod.POST,
                new HttpEntity<>(body, headers),
                new ParameterizedTypeReference<DouYinResult<AccessTokenRespDTO>>() {
                }); // 解决泛型丢失
        Assert.isTrue(exchange.getStatusCode().is2xxSuccessful(), "响应必须是 200 成功");
        return exchange.getBody().getData();
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
    public UserInfoRespDTO getUserInfo(String accessToken, String openId) {
        // 1.1 构建请求头
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        // 1.2 构建请求参数
        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("access_token", accessToken);
        body.add("open_id", openId);

        // 2. 执行请求
        ResponseEntity<DouYinResult<UserInfoRespDTO>> exchange = restTemplate.exchange(
                douYinProperties.getUserInfoUrl(),
                HttpMethod.POST,
                new HttpEntity<>(body, headers),
                new ParameterizedTypeReference<DouYinResult<UserInfoRespDTO>>() {
                }); // 解决泛型丢失
        Assert.isTrue(exchange.getStatusCode().is2xxSuccessful(), "响应必须是 200 成功");
        return exchange.getBody().getData();
    }

    /**
     * 获取用户粉丝信息
     *
     * @param openId
     * @return cn.iocoder.yudao.module.engineering.utils.yingshi.douyin.DouYinResult
     * <cn.iocoder.yudao.module.engineering.utils.yingshi.douyin.UserInfoRespDTO>
     * @author hjn
     * @date 2023-08-17 15:11
     */
    public UserFanDataListRespDTO getUserFanDatas(String openId,String accessToken) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("access-token", accessToken);
        // 1.2 构建请求参数
        Map<String, Object> body = new HashMap<>();
        try {
            // 2. 执行请求
            ResponseEntity<UserFanDataListRespDTO> exchange = restTemplate.exchange(
                    douYinProperties.getFansDataUrl() + "?open_id=" + openId + "&date_type=" + 30,//7表示近7天数据
                    HttpMethod.GET,
                    new HttpEntity<>(body, headers),
                    new ParameterizedTypeReference<UserFanDataListRespDTO>() {
                    }); // 解决泛型丢失
            Assert.isTrue(exchange.getStatusCode().is2xxSuccessful(), "响应必须是 200 成功");
            return exchange.getBody();
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 上传视频
     *
     * @param openId
     * @return cn.iocoder.yudao.module.engineering.utils.yingshi.douyin.DouYinResult
     * <cn.iocoder.yudao.module.engineering.utils.yingshi.douyin.UserInfoRespDTO>
     * @author hjn
     * @date 2023-08-17 15:11
     */
    public UploadVideoRespDTO uploadVideo(String accessToken, String openId, File file){
        // 1.1 构建请求头
        HttpHeaders headers = new HttpHeaders();
        headers.add("content-type","multipart/form-data");
        headers.add("access-token", accessToken);
        // 1.2 构建请求参数
        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("video", new FileSystemResource(file));

        // 2. 执行请求
        ResponseEntity<UploadVideoRespDTO> exchange = restTemplate.exchange(
                douYinProperties.getUploadVideoUrl() + "?open_id=" + openId,
                HttpMethod.POST,
                new HttpEntity<>(body, headers),
                new ParameterizedTypeReference<UploadVideoRespDTO>() {
                }); // 解决泛型丢失
        Assert.isTrue(exchange.getStatusCode().is2xxSuccessful(), "响应必须是 200 成功");
        return exchange.getBody();
    }

    /**
     * 创建视频
     *
     * @param text 视频标题
     * @return cn.iocoder.yudao.module.engineering.utils.yingshi.douyin.DouYinResult
     * <cn.iocoder.yudao.module.engineering.utils.yingshi.douyin.UserInfoRespDTO>
     * @author hjn
     * @date 2023-08-17 15:11
     */
    public CreateVideoRespDTO createVideo(String videoId, String text, String accessToken, String openId, String poiId, String imageId,
                                          List<String> openIds,String microAppId,String microAppUrl,String microAppTitle) {
        // 1.1 构建请求头
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("access-token", accessToken);
        // 1.2 构建请求参数
        Map<String, Object> body = new HashMap<>();
        body.put("video_id", videoId);
        body.put("text", text);
        if(CollUtil.isNotEmpty(openIds)){
            body.put("at_users", openIds);
        }
        if(StrUtil.isNotEmpty(microAppId)){
            body.put("micro_app_id", microAppId);
            body.put("micro_app_url", microAppUrl);
            body.put("micro_app_title", microAppTitle);
        }
        if(StrUtil.isNotEmpty(poiId)){
            body.put("poi_id", poiId);
            body.put("poi_commerce", false);//true,如果用户拥有门店推广能力，则用户发布视频所添加的地理位置默认开启门店推广
        }
        if(StrUtil.isNotEmpty(imageId)){
            body.put("custom_cover_image_url", imageId);
        }
        //body.put("cover_tsp",getRandomCoverTsp() );//获取封面截图时间

        ResponseEntity<CreateVideoRespDTO> exchange = restTemplate.exchange(
                douYinProperties.getCreateVideoUrl() + "?open_id=" + openId,
                HttpMethod.POST,
                new HttpEntity<>(body, headers),
                new ParameterizedTypeReference<CreateVideoRespDTO>() {
                }); // 解决泛型丢失
        Assert.isTrue(exchange.getStatusCode().is2xxSuccessful(), "响应必须是 200 成功");
        return exchange.getBody();
    }

    private float getRandomCoverTsp(){
        Random random = new Random();
        double randomNum = (random.nextDouble() * 14 + 1);
        DecimalFormat df = new DecimalFormat("#.0");
        return Float.parseFloat(df.format(randomNum));
    }
    /**
     * 获取视频信息
     *
     * @param openId
     * @return cn.iocoder.yudao.module.engineering.utils.yingshi.douyin.DouYinResult
     * <cn.iocoder.yudao.module.engineering.utils.yingshi.douyin.UserInfoRespDTO>
     * @author hjn
     * @date 2023-08-17 15:11
     */
    public VideoDataRespDTO getVideoData(String videoId, String openId, String accessToken) {
        // 1.1 构建请求头
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("access-token", accessToken);
        // 1.2 构建请求参数
        Map<String, Object> body = new HashMap<>();
        //body.put("item_ids", Arrays.asList(itemId));
        body.put("video_ids", Arrays.asList(videoId));

        // 2. 执行请求
        ResponseEntity<VideoDataRespDTO> exchange = restTemplate.exchange(
                douYinProperties.getVideoDataUrl() + "?open_id=" + openId,
                HttpMethod.POST,
                new HttpEntity<>(body, headers),
                new ParameterizedTypeReference<VideoDataRespDTO>() {
                }); // 解决泛型丢失
        Assert.isTrue(exchange.getStatusCode().is2xxSuccessful(), "响应必须是 200 成功");
        return exchange.getBody();
    }

    public VideoDataRespDTO getVideoDataByVideoIds(List<String> videoIds, String openId, String accessToken) {
        // 1.1 构建请求头
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("access-token", accessToken);
        // 1.2 构建请求参数
        Map<String, Object> body = new HashMap<>();
        //body.put("item_ids", Arrays.asList(itemId));
        body.put("video_ids", videoIds);

        // 2. 执行请求
        ResponseEntity<VideoDataRespDTO> exchange = restTemplate.exchange(
                douYinProperties.getVideoDataUrl() + "?open_id=" + openId,
                HttpMethod.POST,
                new HttpEntity<>(body, headers),
                new ParameterizedTypeReference<VideoDataRespDTO>() {
                }); // 解决泛型丢失
        Assert.isTrue(exchange.getStatusCode().is2xxSuccessful(), "响应必须是 200 成功");
        return exchange.getBody();
    }


    /**
     * 刷新refresh_token
     *
     * @author hjn
     * @date 2023-08-17 15:11
     */
    public RefreshTokenRespDTO getNewRefreshToken(String refreshToken) {
        try {
            // 1.1 构建请求头
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
            // 1.2 构建请求参数
            MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
            body.add("client_key", douYinProperties.getAppId());
            body.add("refresh_token", refreshToken);

            // 2. 执行请求
            ResponseEntity<DouYinResult<RefreshTokenRespDTO>> exchange = restTemplate.exchange(
                    douYinProperties.getRefreshTokenUrl(),
                    HttpMethod.POST,
                    new HttpEntity<>(body, headers),
                    new ParameterizedTypeReference<DouYinResult<RefreshTokenRespDTO>>() {
                    }); // 解决泛型丢失
            Assert.isTrue(exchange.getStatusCode().is2xxSuccessful(), "响应必须是 200 成功");
            return exchange.getBody().getData();
        }catch (Exception ex){
            log.error("getNewRefreshToken======================{}",ex);
        }
        return null;
    }

    /**
     * 刷新access_token
     *
     * @author hjn
     * @date 2023-08-17 15:11
     */
    public AccessTokenRespDTO getNewAccessToken(String refreshToken) {
        try {
            // 1.1 构建请求头
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
            // 1.2 构建请求参数
            MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
            body.add("client_key", douYinProperties.getAppId());
            body.add("grant_type", "refresh_token");
            body.add("refresh_token", refreshToken);

            // 2. 执行请求
            ResponseEntity<DouYinResult<AccessTokenRespDTO>> exchange = restTemplate.exchange(
                    douYinProperties.getRefreshAccessTokenUrl(),
                    HttpMethod.POST,
                    new HttpEntity<>(body, headers),
                    new ParameterizedTypeReference<DouYinResult<AccessTokenRespDTO>>() {
                    }); // 解决泛型丢失
            Assert.isTrue(exchange.getStatusCode().is2xxSuccessful(), "响应必须是 200 成功");
            return exchange.getBody().getData();
        }catch (Exception ex){
            log.error("getNewAccessToken=================={}",ex);
        }
        return null;
    }


//    /**
//     * 获取用户粉丝信息
//     *
//     * @param openId
//     * @return cn.iocoder.yudao.module.engineering.utils.yingshi.douyin.DouYinResult
//     * <cn.iocoder.yudao.module.engineering.utils.yingshi.douyin.UserInfoRespDTO>
//     * @author hjn
//     * @date 2023-08-17 15:11
//     */
//    public UserFanDataRespDTO getUserFanDatas(String openId) {
//        // 1.1 构建请求头
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
//        // 1.2 构建请求参数
//        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
////        body.add("open_id", openId);
//
//        // 2. 执行请求
//        ResponseEntity<DouYinResult<UserFanDataRespDTO>> exchange = restTemplate.exchange(
//                douYinProperties.getFansDataUrl() + "?open_id=" + openId,
//                HttpMethod.GET,
//                new HttpEntity<>(body, headers),
//                new ParameterizedTypeReference<DouYinResult<UserFanDataRespDTO>>() {
//                }); // 解决泛型丢失
//        Assert.isTrue(exchange.getStatusCode().is2xxSuccessful(), "响应必须是 200 成功");
//        return exchange.getBody().getData();
//    }

    /**
     * 获取用户粉丝信息
     *
     * @param url
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
     * 获取client_token信息
     *
     * @param
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
        body.put("client_key", douYinProperties.getAppId());
        body.put("client_secret", douYinProperties.getAppSecret());

        // 2. 执行请求
        ResponseEntity<DouYinResult<AccessTokenRespDTO>> exchange = restTemplate.exchange(
                douYinProperties.getClientTokenUrl(),
                HttpMethod.POST,
                new HttpEntity<>(body, headers),
                new ParameterizedTypeReference<DouYinResult<AccessTokenRespDTO>>() {
                }); // 解决泛型丢失
        Assert.isTrue(exchange.getStatusCode().is2xxSuccessful(), "响应必须是 200 成功");
        return exchange.getBody().getData();
    }


    /**
     * 查询视频携带的地点信息
     *
     * @param cursor 分页游标, 第一页请求cursor是0, response中会返回下一页请求用到的cursor, 同时response还会返回has_more来表明是否有更多的数据。
     * @param count 每页数量
     * @param keyword 查询关键字，例如美食
     * @param city 查询城市，例如上海、北京
     * @author hjn
     * @date 2023-08-17 15:11
     */
    public PoiPageRespDTO getPoiList(Integer cursor,Integer count,String keyword,String city,String accessToken) {
        // 1.1 构建请求头
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("access-token", accessToken);
        // 1.2 构建请求参数
        Map<String, Object> body = new HashMap<>();

        // 2. 执行请求
        ResponseEntity<DouYinResult<PoiPageRespDTO>> exchange = restTemplate.exchange(
                String.format(douYinProperties.getPoiUrl(),cursor,count,keyword,city),//7表示近7天数据
                HttpMethod.GET,
                new HttpEntity<>(body, headers),
                new ParameterizedTypeReference<DouYinResult<PoiPageRespDTO>>() {
                }); // 解决泛型丢失
        Assert.isTrue(exchange.getStatusCode().is2xxSuccessful(), "响应必须是 200 成功");
        return exchange.getBody().getData();
    }



    /**
     * 分片初始化
     *
     * @param accessToken token
     * @author hjn
     * @date 2023-08-17 15:11
     */
    public InitVideoRespVO initVideoPartUpload(String accessToken,String openId) {
        // 1.1 构建请求头
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("access-token", accessToken);
        // 1.2 构建请求参数
        Map<String, Object> body = new HashMap<>();

        // 2. 执行请求
        ResponseEntity<InitVideoRespVO> exchange = restTemplate.exchange(
                String.format(douYinProperties.getInitVideoPartUploadUrl(),openId),
                HttpMethod.POST,
                new HttpEntity<>(body, headers),
                new ParameterizedTypeReference<InitVideoRespVO>() {
                }); // 解决泛型丢失
        Assert.isTrue(exchange.getStatusCode().is2xxSuccessful(), "响应必须是 200 成功");
        return exchange.getBody();
    }



    /**
     * 分片上传视频
     *
     * @param openId
     * @return cn.iocoder.yudao.module.engineering.utils.yingshi.douyin.DouYinResult
     * <cn.iocoder.yudao.module.engineering.utils.yingshi.douyin.UserInfoRespDTO>
     * @author hjn
     * @date 2023-08-17 15:11
     */
    public UploadVideoRespDTO uploadVideoPart(String accessToken, String openId, File file,String uploadId,Integer partNumber) {
        // 1.1 构建请求头
        HttpHeaders headers = new HttpHeaders();
        headers.add("content-type","multipart/form-data");
        headers.add("access-token", accessToken);
        // 1.2 构建请求参数
        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("video", new FileSystemResource(file));

        // 2. 执行请求
        ResponseEntity<UploadVideoRespDTO> exchange = restTemplate.exchange(
                String.format(douYinProperties.getUploadVideoPartUrl(),uploadId,openId,partNumber),
                HttpMethod.POST,
                new HttpEntity<>(body, headers),
                new ParameterizedTypeReference<UploadVideoRespDTO>() {
                }); // 解决泛型丢失
        Assert.isTrue(exchange.getStatusCode().is2xxSuccessful(), "响应必须是 200 成功");
        return exchange.getBody();
    }

    /**
     * 分片上传完成
     *
     * @param accessToken token
     * @author hjn
     * @date 2023-08-17 15:11
     */
    public UploadVideoRespDTO completeVideoPartUpload(String accessToken,String openId,String uploadId) {
        // 1.1 构建请求头
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("access-token", accessToken);
        // 1.2 构建请求参数
        Map<String, Object> body = new HashMap<>();

        // 2. 执行请求
        ResponseEntity<UploadVideoRespDTO> exchange = restTemplate.exchange(
                String.format(douYinProperties.getCompleteVideoPartUploadUrl(),uploadId,openId),
                HttpMethod.POST,
                new HttpEntity<>(body, headers),
                new ParameterizedTypeReference<UploadVideoRespDTO>() {
                }); // 解决泛型丢失
        Assert.isTrue(exchange.getStatusCode().is2xxSuccessful(), "响应必须是 200 成功");
        return exchange.getBody();
    }

    /**
     * 上传图片
     *
     * @param openId
     * @return cn.iocoder.yudao.module.engineering.utils.yingshi.douyin.DouYinResult
     * <cn.iocoder.yudao.module.engineering.utils.yingshi.douyin.UserInfoRespDTO>
     * @author hjn
     * @date 2023-08-17 15:11
     */
    public UploadImageRespDTO uploadImage(String accessToken, String openId, File file){
        // 1.1 构建请求头
        HttpHeaders headers = new HttpHeaders();
        headers.add("content-type","multipart/form-data");
        headers.add("access-token", accessToken);
        // 1.2 构建请求参数
        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("image", new FileSystemResource(file));

        // 2. 执行请求
        ResponseEntity<UploadImageRespDTO> exchange = restTemplate.exchange(
                String.format(douYinProperties.getUploadImageUrl(),openId),
                HttpMethod.POST,
                new HttpEntity<>(body, headers),
                new ParameterizedTypeReference<UploadImageRespDTO>() {
                }); // 解决泛型丢失
        Assert.isTrue(exchange.getStatusCode().is2xxSuccessful(), "响应必须是 200 成功");
        return exchange.getBody();
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
        try {
            // 1.1 构建请求头
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.add("access-token", accessToken);
            // 1.2 构建请求参数
            Map<String, Object> body = new HashMap<>();
//        body.add("open_id", openId);

            // 2. 执行请求
            ResponseEntity<VideoBaseRespDTO> exchange = restTemplate.exchange(
                    String.format(douYinProperties.getItemBaseUrl(), openId, itemId),
                    HttpMethod.GET,
                    new HttpEntity<>(body, headers),
                    new ParameterizedTypeReference<VideoBaseRespDTO>() {
                    }); // 解决泛型丢失
            Assert.isTrue(exchange.getStatusCode().is2xxSuccessful(), "响应必须是 200 成功");
            return exchange.getBody();
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return null;
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

        // 2. 执行请求
        ResponseEntity<TicketRespDTO> exchange = restTemplate.exchange(
                douYinProperties.getGetTicketUrl(),
                HttpMethod.GET,
                new HttpEntity<>(body, headers),
                new ParameterizedTypeReference<TicketRespDTO>() {
                }); // 解决泛型丢失
        Assert.isTrue(exchange.getStatusCode().is2xxSuccessful(), "响应必须是 200 成功");
        return exchange.getBody();
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

        // 2. 执行请求
        ResponseEntity<ShareIdRespDTO> exchange = restTemplate.exchange(
                douYinProperties.getShareIdUrl(),
                HttpMethod.GET,
                new HttpEntity<>(body, headers),
                new ParameterizedTypeReference<ShareIdRespDTO>() {
                }); // 解决泛型丢失
        Assert.isTrue(exchange.getStatusCode().is2xxSuccessful(), "响应必须是 200 成功");
        return exchange.getBody();
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

        // 2. 执行请求
        ResponseEntity<TicketRespDTO> exchange = restTemplate.exchange(
                douYinProperties.getGetOpenTicketUrl(),
                HttpMethod.GET,
                new HttpEntity<>(body, headers),
                new ParameterizedTypeReference<TicketRespDTO>() {
                }); // 解决泛型丢失
        Assert.isTrue(exchange.getStatusCode().is2xxSuccessful(), "响应必须是 200 成功");
        return exchange.getBody();
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

        // 2. 执行请求
        ResponseEntity<SendMsgRespDTO> exchange = restTemplate.exchange(
                String.format(douYinProperties.getSendMsgUrl(),openId),
                HttpMethod.POST,
                new HttpEntity<>(body, headers),
                new ParameterizedTypeReference<SendMsgRespDTO>() {
                }); // 解决泛型丢失
        Assert.isTrue(exchange.getStatusCode().is2xxSuccessful(), "响应必须是 200 成功");
        return exchange.getBody();
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
        if(StringUtils.isNotEmpty(commentId)) {
            body.put("comment_id", commentId);
        }
        body.put("content", content);

        // 2. 执行请求
        ResponseEntity<CommentReplyRespDTO> exchange = restTemplate.exchange(
                String.format(douYinProperties.getCommentReplyUrl(),openId),
                HttpMethod.POST,
                new HttpEntity<>(body, headers),
                new ParameterizedTypeReference<CommentReplyRespDTO>() {
                }); // 解决泛型丢失
        Assert.isTrue(exchange.getStatusCode().is2xxSuccessful(), "响应必须是 200 成功");
        return exchange.getBody();
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
    public CommentTopRespDTO commentTop(String accessToken, String openId,String itemId,String commentId,Boolean isTop){
        // 1.1 构建请求头
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("access-token", accessToken);
        // 1.2 构建请求参数

        Map<String, Object> body = new HashMap<>();
        body.put("item_id", itemId);
        body.put("comment_id", commentId);
        body.put("top", true);

        // 2. 执行请求
        ResponseEntity<CommentTopRespDTO> exchange = restTemplate.exchange(
                String.format(douYinProperties.getCommentTopUrl(),openId),
                HttpMethod.POST,
                new HttpEntity<>(body, headers),
                new ParameterizedTypeReference<CommentTopRespDTO>() {
                }); // 解决泛型丢失
        Assert.isTrue(exchange.getStatusCode().is2xxSuccessful(), "响应必须是 200 成功");
        return exchange.getBody();
    }

    /**
     * 获取评论列表
     *
     * @param accessToken
     * @return cn.iocoder.yudao.module.engineering.utils.yingshi.douyin.DouYinResult
     * <cn.iocoder.yudao.module.engineering.utils.yingshi.douyin.UserInfoRespDTO>
     * @author hjn
     * @date 2023-08-17 15:11
     */
    @SneakyThrows
    public CommentListRespDTO getCommentList(String accessToken, String openId, String itemId, Long cursor, Integer count) {
        itemId = URLEncoder.encode(itemId,"UTF-8");
        String url = String.format(douYinProperties.getCommentListUrl(),openId,cursor,count,itemId);
        Map<String, String> headers = new HashMap<>();
        headers.put("access-token",accessToken);
        String httpResponse = Http2Utils.doGet01(url, headers);
        if(StringUtils.isNotBlank(httpResponse)){
            return JsonUtils.parseObject(httpResponse,CommentListRespDTO.class);
        }
        return null;
    }


    /**
     * 获取抖音热歌榜
     * @param clientToken
     * @return
     */
    public MusicListRespDTO getMusicHot(String clientToken){
        return getMusicCharts(clientToken, douYinProperties.getMusicHotUrl());
    }

    /**
     * 获取抖音飙升榜
     * @param clientToken
     * @return
     */
    public MusicListRespDTO getMusicUp(String clientToken){
        return getMusicCharts(clientToken, douYinProperties.getMusicUpUrl());
    }

    /**
     * 获取抖音原创榜
     * @param clientToken
     * @return
     */
    public MusicListRespDTO getMusicOriginal(String clientToken){
        return getMusicCharts(clientToken, douYinProperties.getMusicOriginalUrl());
    }

    private MusicListRespDTO getMusicCharts(String clientToken, String url){
        // 1.1 构建请求头
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("access-token", clientToken);
        // 1.2 构建请求参数
        Map<String, Object> body = new HashMap<>();
//        body.add("open_id", openId);

        // 2. 执行请求
        ResponseEntity<MusicListRespDTO> exchange = restTemplate.exchange(
                url,
                HttpMethod.GET,
                new HttpEntity<>(body, headers),
                new ParameterizedTypeReference<MusicListRespDTO>() {
                }); // 解决泛型丢失
        Assert.isTrue(exchange.getStatusCode().is2xxSuccessful(), "响应必须是 200 成功");
        return exchange.getBody();
    }
}
