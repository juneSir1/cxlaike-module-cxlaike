package net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.dypersonalletter.vo;

import lombok.*;
import io.swagger.v3.oas.annotations.media.Schema;
import net.hzxzkj.cxlaike.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static net.hzxzkj.cxlaike.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 抖音私信管理分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class DyPersonalLetterPageReqVO extends PageParam {

    @Schema(description = "事件类型")
    private String event;

    @Schema(description = "client_key")
    private String clientKey;

    @Schema(description = "发送方 open_id", example = "10161")
    private String fromUserId;

    @Schema(description = "接收方 open_id", example = "11307")
    private String toUserId;

    @Schema(description = "会话 ID", example = "25698")
    private String conversationShortId;

    @Schema(description = "消息 ID", example = "9728")
    private String serverMessageId;

    @Schema(description = "会话类型（1：私聊）", example = "2")
    private Integer conversationType;

    @Schema(description = "消息创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] messageCreateTime;

    @Schema(description = "text：文本 image：图片 emoji：表情 video：视频 retain_consult_card：留资卡片 other：其他 不同类型消息参数见下方介绍", example = "2")
    private String messageType;

    @Schema(description = "内容")
    private String text;

    @Schema(description = "资源类型", example = "1")
    private String resourceType;

    @Schema(description = "资源宽度")
    private Integer resourceWidth;

    @Schema(description = "资源高度")
    private Integer resourceHeight;

    @Schema(description = "资源链接", example = "https://www.iocoder.cn")
    private String resourceUrl;

    @Schema(description = "加密后的视频ID", example = "31456")
    private String itemId;

    @Schema(description = "卡片id", example = "10781")
    private String cardId;

    @Schema(description = "1:空白态；2:完成态", example = "2")
    private Integer cardStatus;

    @Schema(description = "卡片信息")
    private String cardData;

    @Schema(description = "区分发出应用通过发送私信消息接口发送，会显示具体的 clientkey通过端上主动发送，该字段默认为空")
    private String source;

    @Schema(description = "场景类型1:关键词自动回复 2:欢迎语 100:其他", example = "2")
    private Integer sceneType;

    @Schema(description = "用户基本信息，包括：昵称和头像")
    private String userInfos;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}
