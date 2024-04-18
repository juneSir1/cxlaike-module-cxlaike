package net.hzxzkj.cxlaike.module.cxlaike.controller.admin.callback;

import static net.hzxzkj.cxlaike.framework.common.pojo.CommonResult.success;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import javax.annotation.Resource;
import javax.annotation.security.PermitAll;
import javax.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import net.hzxzkj.cxlaike.framework.common.pojo.CommonResult;
import net.hzxzkj.cxlaike.module.cxlaike.controller.admin.callback.vo.ProduceMediaCompleteVO;
import net.hzxzkj.cxlaike.module.cxlaike.service.callback.CallbackService;
import net.hzxzkj.cxlaike.module.cxlaike.service.douyin.DouYinService;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 描   述:
 *
 * @author ace
 * @version [版本号, 2023/9/19]
 * @see [相关类/方法]
 * 创建日期: 2023/9/19
 */
@Slf4j
@Tag(name = "管理后台 - 回调")
@RestController
@RequestMapping("/cxlaike/callback")
@Validated
public class CallbackController {

  @Resource
  private CallbackService callbackService;
  @Resource
  private DouYinService douYinService;
//  @Resource
//  private HttpServletRequest httpRequest;


  @PostMapping(value = "/ai_video", consumes = MediaType.APPLICATION_JSON_VALUE)
  @Operation(summary = "媒体剪辑回调")
  @PermitAll
  public CommonResult<String> aiVideoCallback(@RequestBody ProduceMediaCompleteVO complete) {
//    String body = printPostBody(httpRequest);
    log.info("ai视频生成回调:{}", complete);
    callbackService.aiVideoCallback(complete);
    return success("success");
  }

  public static String printPostBody(HttpServletRequest request) {
    try {
      BufferedReader reader = new BufferedReader(new InputStreamReader(request.getInputStream()));
      String line;
      StringBuilder body = new StringBuilder();
      while ((line = reader.readLine()) != null) {
        body.append(line);
      }
      reader.close();
      return body.toString();
    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }

  @PostMapping("/digital_human_video")
  @Operation(summary = "创建ai视频配置")
  public CommonResult<String> digitalHumanCallback() {
//    callbackService.aiVideoCallback(complete);
    return success("success");
  }

//  @GetMapping("/getUserToken")
//  @Operation(summary = "获取抖音授权")
//  @PermitAll
//  public CommonResult<Boolean> getUserToken(String code, String state) {
//    douYinService.getUserToken(code, state);
//    return success(true);
//  }
}
