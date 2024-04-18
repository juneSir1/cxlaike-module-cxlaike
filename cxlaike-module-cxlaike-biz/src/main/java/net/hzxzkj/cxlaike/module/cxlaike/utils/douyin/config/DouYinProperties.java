package net.hzxzkj.cxlaike.module.cxlaike.utils.douyin.config;

import lombok.Data;
import org.hibernate.validator.constraints.URL;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@ConfigurationProperties(prefix = "douyin")
@Validated
@Data
public class DouYinProperties {


    /**
     * 抖音获取授权码 url
     */
    @NotEmpty(message = "抖音获取授权码地址不能为空")
    @URL(message = "抖音获取授权码地址的格式必须是 URL")
    private String oauthUrl;

    @NotEmpty(message = "抖音获取授权码地址不能为空")
    @URL(message = "抖音获取授权码地址的格式必须是 URL")
    private String qrUrl;

    @NotEmpty(message = "抖音获取授权码地址不能为空")
    @URL(message = "抖音获取授权码地址的格式必须是 URL")
    private String checkUrl;
    /**
     * 获取 access_token url
     */
    @NotEmpty(message = "获取access_token地址不能为空")
    @URL(message = "获取 access_token地址的格式必须是 URL")
    private String accessTokenUrl;
    @NotEmpty(message = "获取client_token地址不能为空")
    @URL(message = "获取 client_token地址的格式必须是 URL")
    private String clientTokenUrl;

    /**
     * 获取用户公开信息 url
     */
    @NotEmpty(message = "获取用户公开信息地址不能为空")
    @URL(message = "获取用户公开信息的格式必须是 URL")
    private String userInfoUrl;

    /**
     * 获取用户粉丝数据 url
     */
    @NotEmpty(message = "获取用户粉丝数据地址不能为空")
    @URL(message = "获取用户粉丝数据的格式必须是 URL")
    private String fansDataUrl;

    /**
     * 上传视频 url
     */
    @NotEmpty(message = "上传视频地址不能为空")
    @URL(message = "上传视频的格式必须是 URL")
    private String uploadVideoUrl;

    /**
     * 上传图片 url
     */
    @NotEmpty(message = "上传图片地址不能为空")
    @URL(message = "上传图片的格式必须是 URL")
    private String uploadImageUrl;

    /**
     * 创建视频 url
     */
    @NotEmpty(message = "创建视频地址不能为空")
    @URL(message = "创建视频的格式必须是 URL")
    private String createVideoUrl;

    /**
     * 查询特定视频的视频数据 url
     */
    @NotEmpty(message = "查询特定视频的视频数据地址不能为空")
    @URL(message = "查询特定视频的视频数据的格式必须是 URL")
    private String videoDataUrl;

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

    @NotEmpty(message = "查询视频携带的地点信息地址不能为空")
    @URL(message = "查询视频携带的地点信息的格式必须是 URL")
    private String poiUrl;

    @NotEmpty(message = "appId不能为空")
    private String appId;

    /**
     * 权限范围
     */
    @NotEmpty(message = "scope不能为空")
    private String scope;

    @NotEmpty(message = "appSecret不能为空")
    private String appSecret;

    /**
     * 刷新refresh_token url
     */
    @NotEmpty(message = "刷新refresh_token地址不能为空")
    @URL(message = "刷新refresh_token的格式必须是 URL")
    private String refreshTokenUrl;

    /**
     * 刷新access_token url
     */
    @NotEmpty(message = "刷新access_token地址不能为空")
    @URL(message = "刷新access_token的格式必须是 URL")
    private String refreshAccessTokenUrl;

    /**
     * 分片初始化 url
     */
    @NotEmpty(message = "分片初始化地址不能为空")
    @URL(message = "分片初始化的格式必须是 URL")
    private String initVideoPartUploadUrl;


    /**
     * 分片上传 url
     */
    @NotEmpty(message = "分片上传地址不能为空")
    @URL(message = "分片上传的格式必须是 URL")
    private String uploadVideoPartUrl;

    /**
     * 视频基础数据 url
     */
    @NotEmpty(message = "视频基础数据url不能为空")
    @URL(message = "视频基础数据的格式必须是 URL")
    private String itemBaseUrl;

    /**
     * 分片上传完成 url
     */
    @NotEmpty(message = "分片上传完成地址不能为空")
    @URL(message = "分片上传完成的格式必须是 URL")
    private String completeVideoPartUploadUrl;

    @NotNull(message = "文件大小上限不能为空")
    private Integer fileSize;

    /**
     * 获取jsb_ticket url
     */
    @NotEmpty(message = "获取jsb_ticket地址不能为空")
    @URL(message = "获取jsb_ticket的格式必须是 URL")
    private String getTicketUrl;

    @NotEmpty(message = "获取open_ticket地址不能为空")
    @URL(message = "获取open_ticket的格式必须是 URL")
    private String getOpenTicketUrl;
    /**
     * 签名
     */
    @NotEmpty(message = "签名不能为空")
    private String signature;
    @NotEmpty(message = "分享签名不能为空")
    private String schemaSignature;

    @NotEmpty(message = "重定向地址不能为空")
    @URL(message = "重定向地址的格式必须是 URL")
    private String redirectUrl;

    @NotEmpty(message = "授权成功后的回调地址不能为空")
    @URL(message = "授权成功后的回调地址的格式必须是 URL")
    private String merchantTaskScanUrl;

    @NotEmpty(message = "分享地址不能为空")
    @URL(message = "分享地址的格式必须是 URL")
    private String shareIdUrl;

    @NotEmpty(message = "发送私信地址不能为空")
    @URL(message = "发送私信地址的格式必须是 URL")
    private String sendMsgUrl;

    @NotEmpty(message = "回复评论地址不能为空")
    @URL(message = "回复评论地址的格式必须是 URL")
    private String commentReplyUrl;

    @NotEmpty(message = "评论置顶地址不能为空")
    @URL(message = "评论置顶地址的格式必须是 URL")
    private String commentTopUrl;

    @NotEmpty(message = "评论列表地址不能为空")
    @URL(message = "评论列表地址的格式必须是 URL")
    private String commentListUrl;

    @NotEmpty(message = "热歌榜地址不能为空")
    @URL(message = "评热歌榜地址的格式必须是 URL")
    private String musicHotUrl;

    @NotEmpty(message = "飙升榜地址不能为空")
    @URL(message = "飙升榜地址的格式必须是 URL")
    private String musicUpUrl;

    @NotEmpty(message = "原创榜地址不能为空")
    @URL(message = "原创榜地址的格式必须是 URL")
    private String musicOriginalUrl;
}
