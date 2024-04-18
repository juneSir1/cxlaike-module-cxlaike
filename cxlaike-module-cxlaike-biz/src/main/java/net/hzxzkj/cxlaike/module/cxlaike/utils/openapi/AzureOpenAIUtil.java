package net.hzxzkj.cxlaike.module.cxlaike.utils.openapi;

import lombok.extern.slf4j.Slf4j;
import net.hzxzkj.cxlaike.framework.common.pojo.CommonResult;
import net.hzxzkj.cxlaike.framework.common.util.json.JsonUtils;
import net.hzxzkj.cxlaike.module.cxlaike.utils.openapi.config.WryProperties;
import net.hzxzkj.cxlaike.module.cxlaike.utils.openapi.dto.AuthenticationDTO;
import net.hzxzkj.cxlaike.module.cxlaike.utils.openapi.dto.EmbeddingsDTO;
import net.hzxzkj.cxlaike.module.cxlaike.utils.openapi.dto.MessageDTO;
import net.hzxzkj.cxlaike.module.cxlaike.utils.openapi.dto.MessageRespDTO;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;

import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @author jianan.han
 * @date 2023-08-09 16:36
 * @description
 */
@Component
@Slf4j
public class AzureOpenAIUtil {

    //    private static final String RESOURCE_NAME = "234234";
//    private static final String DEPLOYMENT_NAME = "cxlaike";
//    private static final String API_KEY = "cd62bd11afb4426fac831bc4b39c8fea";
//    private static final String PROMPT = "completions return";
//    private static final String APIVERSION = "2023-05-15";
    private final RestTemplate restTemplate = new RestTemplate();
    @Resource
    private WryProperties wryProperties;

//    /**
//     * 身份验证
//     * @author hjn
//     * @date 2023-08-10 09:35
//     * @return cn.iocoder.yudao.framework.common.pojo.CommonResult<cn.iocoder.yudao.module.engineering.utils.yingshi.openapi.dto.AuthenticationDTO>
//     */
//    public CommonResult<AuthenticationDTO> getAccessToken(){
//        // 1.1 构建请求头
//        HttpHeaders headers = new HttpHeaders();
//        headers.add("api-key",API_KEY);
//        headers.setContentType(MediaType.APPLICATION_JSON);
//        //addClientHeader(headers);
//        // 1.2 构建请求参数
//        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
//        body.add("prompt", PROMPT);
//        body.add("max_tokens", 5);
//        //String apiVersion = LocalDateTimeUtil.format(LocalDate.now(), DateUtils.FORMAT_YEAR_MONTH_DAY);
//
//        String url = String.format("https://%s.openai.azure.com/openai/deployments/%s/completions?api-version=%s",RESOURCE_NAME,DEPLOYMENT_NAME,APIVERSION);
//
//        // 2. 执行请求
//        ResponseEntity<CommonResult<AuthenticationDTO>> exchange = restTemplate.exchange(
//                url,
//                HttpMethod.POST,
//                new HttpEntity<>(body, headers),
//                new ParameterizedTypeReference<CommonResult<AuthenticationDTO>>() {}); // 解决 CommonResult 的泛型丢失
//        Assert.isTrue(exchange.getStatusCode().is2xxSuccessful(), "响应必须是 200 成功");
//        return exchange.getBody();
//    }
//
//    /**
//     * 嵌入
//     * @author hjn
//     * @date 2023-08-10 09:35
//     * @return cn.iocoder.yudao.framework.common.pojo.CommonResult<cn.iocoder.yudao.module.engineering.utils.yingshi.openapi.dto.EmbeddingsDTO>
//     */
//    public CommonResult<EmbeddingsDTO> embeddings(){
//        // 1.1 构建请求头
//        HttpHeaders headers = new HttpHeaders();
//        headers.add("api-key",API_KEY);
//        headers.setContentType(MediaType.APPLICATION_JSON);
//        //addClientHeader(headers);
//        // 1.2 构建请求参数
//        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
//        body.add("input", "The food was delicious and the waiter...");
//
//        //String apiVersion = LocalDateTimeUtil.format(LocalDate.now(), DateUtils.FORMAT_YEAR_MONTH_DAY);
//
//        String url = String.format("https://%s.openai.azure.com/openai/deployments/%s/embeddings?api-version=%s",RESOURCE_NAME,DEPLOYMENT_NAME,APIVERSION);
//
//        // 2. 执行请求
//        ResponseEntity<CommonResult<EmbeddingsDTO>> exchange = restTemplate.exchange(
//                url,
//                HttpMethod.POST,
//                new HttpEntity<>(body, headers),
//                new ParameterizedTypeReference<CommonResult<EmbeddingsDTO>>() {}); // 解决 CommonResult 的泛型丢失
//        Assert.isTrue(exchange.getStatusCode().is2xxSuccessful(), "响应必须是 200 成功");
//        return exchange.getBody();
//    }

    /**
     * 聊天完成
     *
     * @return cn.iocoder.yudao.framework.common.pojo.CommonResult<cn.iocoder.yudao.module.engineering.utils.yingshi.openapi.dto.EmbeddingsDTO>
     * @author hjn
     * @date 2023-08-10 09:35
     */
    public MessageRespDTO completions(List<MessageDTO> messageDTOS) {
        // 1.1 构建请求头
        HttpHeaders headers = new HttpHeaders();
        headers.add("api-key", wryProperties.getApiKey());
        headers.setContentType(MediaType.APPLICATION_JSON);
        //addClientHeader(headers);
        // 1.2 构建请求参数
        Map<String, Object> body = new HashMap<>();
        body.put("messages", messageDTOS);
        body.put("temperature", 0.9);
        body.put("top_p", 1);
        body.put("frequency_penalty", 0);
        body.put("presence_penalty", 0);
        body.put("max_tokens", 8000);
        body.put("stop", null);

        //String apiVersion = LocalDateTimeUtil.format(LocalDate.now(), DateUtils.FORMAT_YEAR_MONTH_DAY);

        String url = String.format("https://%s.openai.azure.com/openai/deployments/%s/chat/completions?api-version=%s",
                wryProperties.getResourceName(), wryProperties.getDeploymentName(), wryProperties.getApiVersion());
        log.info("GTP-url:",url);
        log.info("GTP-参数:",JsonUtils.toJsonString(body));
        try {
            // 2. 执行请求
            ResponseEntity<MessageRespDTO> exchange = restTemplate.exchange(
                    url,
                    HttpMethod.POST,
                    new HttpEntity<>(body, headers),
                    new ParameterizedTypeReference<MessageRespDTO>() {
                    }); // 解决 CommonResult 的泛型丢失
            return exchange.getBody();
        }catch (Exception e){
            log.info("AI文案生成失败,e:{}",e);
            return null;
        }

    }

}
