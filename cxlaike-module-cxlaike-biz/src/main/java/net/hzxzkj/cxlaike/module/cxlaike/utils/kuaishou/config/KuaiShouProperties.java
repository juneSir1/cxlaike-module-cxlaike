package net.hzxzkj.cxlaike.module.cxlaike.utils.kuaishou.config;

import lombok.Data;
import org.hibernate.validator.constraints.URL;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@ConfigurationProperties(prefix = "kuaishou")
@Validated
@Data
public class KuaiShouProperties {



    @NotEmpty(message = "appId不能为空")
    private String appId;

    /**
     * 权限范围
     */
    @NotEmpty(message = "scope不能为空")
    private String scope;

    @NotEmpty(message = "appSecret不能为空")
    private String appSecret;

//    /**
//     * 签名
//     */
//    @NotEmpty(message = "签名不能为空")
//    private String signature;
    /**
     * 快手获取授权码 url
     */
    @NotEmpty(message = "快手获取授权码地址不能为空")
    @URL(message = "快手获取授权码地址的格式必须是 URL")
    private String oauthUrl;

    /**
     * 获取 access_token url
     */
    @NotEmpty(message = "获取access_token地址不能为空")
    @URL(message = "获取 access_token地址的格式必须是 URL")
    private String accessTokenUrl;

    /**
     * 刷新 access_token url
     */
    @NotEmpty(message = "刷新access_token地址不能为空")
    @URL(message = "刷新 access_token地址的格式必须是 URL")
    private String refreshAccessTokenUrl;

    /**
     * 获取用户公开信息 url
     */
    @NotEmpty(message = "获取用户公开信息地址不能为空")
    @URL(message = "获取用户公开信息的格式必须是 URL")
    private String getUserInfoUrl;

    /**
     * 获取用户手机 url
     */
    @NotEmpty(message = "获取用户手机不能为空")
    @URL(message = "获取用户手机必须是 URL")
    private String getUserPhoneUrl;

    /**
     * 视频开始上传 url
     */
    @NotEmpty(message = "视频开始上传地址不能为空")
    @URL(message = "视频开始上传地址必须是 URL")
    private String userVideoPublishStartUrl;

    /**
     * A. 直接上传，适合小于10M的文件
     * A.1 支持二进制body上传
     */
    @NotEmpty(message = "分片上传地址不能为空")
    @URL(message = "分片上传的格式必须是 URL")
    private String uploadBinaryUrl;

    /**
     * A. 直接上传，适合小于10M的文件
     * A.2 Multipart Form Data两种方式上传
     */
    @NotEmpty(message = "分片上传地址不能为空")
    @URL(message = "分片上传的格式必须是 URL")
    private String uploadMultipartUrl;

    /**
     * B. 分片上传
     * B.1 上传分片
     */
    @NotEmpty(message = "分片上传-上传分片地址不能为空")
    @URL(message = "分片上传-上传分片的格式必须是 URL")
    private String uploadFragmentUrl;

    /**
     * B. 分片上传
     * B.2 断点续传 非必要的流程,在上传进程意外中断的情况下,可以使用此接口查询已经上传的分片,从失败的分片重新上传
     */
    @NotEmpty(message = "分片上传-断点续传地址不能为空")
    @URL(message = "分片上传-断点续传的格式必须是 URL")
    private String uploadResumeUrl;

    /**
     * B. 分片上传
     * B.3 完成分片上传
     */
    @NotEmpty(message = "分片上传完成地址不能为空")
    @URL(message = "分片上传完成的格式必须是 URL")
    private String uploadCompleteUrl;

    /**
     * 视频发布 url
     */
    @NotEmpty(message = "视频发布地址不能为空")
    @URL(message = "视频发布格式必须是 URL")
    private String userVideoPublishUrl;


    @NotNull(message = "文件大小上限不能为空")
    private Integer fileSize;

    /**
     * 授权成功后的回调地址，必须以 http/https 开头，存在（#）等特殊符号时请对回调地址做 urlEncode 处理。
     *
     * 地址必须对应申请应用时填写的重定向 URL，仅校验（#）前的内容。如不清楚请联系应用申请人。
     * 例：
     * 配置的重定向 uri 为https://aa.bb.cc/dd/，
     * 授权时传的 redirect_uri 为 https://aa.bb.cc/dd/#xxxx 则授权时会截取 redirect_uri 中 # 号前的 https://aa.bb.cc/dd/ 进行校验，判断是否存在配置中。
     * 注意：redirect_uri 中不支持携带自定义 query 参数
     */
    @NotEmpty(message = "授权成功后的回调地址不能为空")
    @URL(message = "授权成功后的回调地址的格式必须是 URL")
    private String appRedirectUrl;

    @NotEmpty(message = "授权成功后的回调地址不能为空")
    @URL(message = "授权成功后的回调地址的格式必须是 URL")
    private String merchantRedirectUrl;

    @NotEmpty(message = "授权成功后的回调地址不能为空")
    @URL(message = "授权成功后的回调地址的格式必须是 URL")
    private String redirectUrl;

}
