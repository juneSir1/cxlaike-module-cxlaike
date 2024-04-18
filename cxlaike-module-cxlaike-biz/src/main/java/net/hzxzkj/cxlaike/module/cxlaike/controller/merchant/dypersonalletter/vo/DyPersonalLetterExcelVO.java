package net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.dypersonalletter.vo;

import lombok.*;

import java.time.LocalDateTime;

import com.alibaba.excel.annotation.ExcelProperty;

/**
 * 抖音私信管理 Excel VO
 *
 * @author 宵征源码
 */
@Data
public class DyPersonalLetterExcelVO {

    @ExcelProperty("事件类型")
    private String event;

    @ExcelProperty("client_key")
    private String clientKey;

    @ExcelProperty("发送方 open_id")
    private String fromUserId;

    @ExcelProperty("接收方 open_id")
    private String toUserId;

    @ExcelProperty("会话 ID")
    private String conversationShortId;

    @ExcelProperty("消息 ID")
    private String serverMessageId;

    @ExcelProperty("会话类型（1：私聊）")
    private Integer conversationType;

    @ExcelProperty("消息创建时间")
    private LocalDateTime messageCreateTime;

    @ExcelProperty("text：文本 image：图片 emoji：表情 video：视频 retain_consult_card：留资卡片 other：其他 不同类型消息参数见下方介绍")
    private String messageType;

    @ExcelProperty("内容")
    private String text;

    @ExcelProperty("资源类型")
    private String resourceType;

    @ExcelProperty("资源宽度")
    private Integer resourceWidth;

    @ExcelProperty("资源高度")
    private Integer resourceHeight;

    @ExcelProperty("资源链接")
    private String resourceUrl;

    @ExcelProperty("加密后的视频ID")
    private String itemId;

    @ExcelProperty("卡片id")
    private String cardId;

    @ExcelProperty("1:空白态；2:完成态")
    private Integer cardStatus;

    @ExcelProperty("卡片信息")
    private String cardData;

    @ExcelProperty("区分发出应用通过发送私信消息接口发送，会显示具体的 clientkey通过端上主动发送，该字段默认为空")
    private String source;

    @ExcelProperty("场景类型1:关键词自动回复 2:欢迎语 100:其他")
    private Integer sceneType;

    @ExcelProperty("用户基本信息，包括：昵称和头像")
    private String userInfos;

    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}
