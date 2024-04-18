package net.hzxzkj.cxlaike.module.cxlaike.utils.wx;

import lombok.extern.slf4j.Slf4j;
import net.hzxzkj.cxlaike.framework.common.pojo.CommonResult;
import net.hzxzkj.cxlaike.framework.common.util.http.Http2Utils;
import net.hzxzkj.cxlaike.framework.common.util.json.JsonUtils;
import net.hzxzkj.cxlaike.module.cxlaike.utils.douyin.vo.AccessTokenRespDTO;
import net.hzxzkj.cxlaike.module.cxlaike.utils.douyin.vo.DouYinResult;
import net.hzxzkj.cxlaike.module.cxlaike.utils.wx.config.WxMaProperties;
import net.hzxzkj.cxlaike.module.cxlaike.utils.wx.vo.AppletCodeRespVO;
import net.hzxzkj.cxlaike.module.cxlaike.utils.wx.vo.TokenRespVO;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author jianan.han
 * @date 2023/10/8 上午9:40
 * @description
 */
@Component
@Slf4j
public class AppletUtil {

    @Resource
    private WxMaProperties wxMaProperties;

    private final RestTemplate restTemplate = new RestTemplate();

    private static final String TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token";

    private static final String APPLET_CODE = "https://api.weixin.qq.com/wxa/getwxacodeunlimit";

    public TokenRespVO getToken(){
        // 1.1 构建请求头
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        // 1.2 构建请求参数
        Map<String, Object> body = new HashMap<>();

        // 2. 执行请求
        ResponseEntity<TokenRespVO> exchange = restTemplate.exchange(
                TOKEN_URL + "?grant_type=client_credential&appid=" + wxMaProperties.getAppid() + "&secret=" + wxMaProperties.getSecret(),
                HttpMethod.GET,
                new HttpEntity<>(body, headers),
                new ParameterizedTypeReference<TokenRespVO>() {
                }); // 解决泛型丢失
        Assert.isTrue(exchange.getStatusCode().is2xxSuccessful(), "响应必须是 200 成功");
        return exchange.getBody();
    }


//    public AppletCodeRespVO getCode(String token,String page,String scene){
//        // 1.1 构建请求头
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.IMAGE_JPEG);
//        // 1.2 构建请求参数
//        Map<String, Object> body = new HashMap<>();
//        body.put("scene", scene);
//        //body.put("page", page);
//        body.put("env_version", wxMaProperties.getEnvVersion());
//
//        // 2. 执行请求
//        ResponseEntity<AppletCodeRespVO> exchange = restTemplate.exchange(
//                APPLET_CODE+"?access_token="+token,
//                HttpMethod.POST,
//                new HttpEntity<>(body, headers),
//                new ParameterizedTypeReference<AppletCodeRespVO>() {
//                }); // 解决泛型丢失
//        Assert.isTrue(exchange.getStatusCode().is2xxSuccessful(), "响应必须是 200 成功");
//        return exchange.getBody();
//    }

    public byte[] getCode(String token,String page,String scene){
        String url = APPLET_CODE+"?access_token="+token;

        Map<String, Object> map = new HashMap<>();
        map.put("scene", scene);
        map.put("page", page);
        map.put("env_version", wxMaProperties.getEnvVersion());
        map.put("check_path",false);
        map.put("width",240);
        log.info("url:{},map:{}",url, JsonUtils.toJsonString(map));
        return Http2Utils.sendPost2Byte(url, map);

    }


}
