package net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.dypersonalletter;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import net.hzxzkj.cxlaike.framework.mybatis.core.dataobject.BaseDO;
import net.hzxzkj.cxlaike.framework.mybatis.core.dataobject.TenantBaseDO;

/**
 * 抖音私信管理 DO
 *
 * @author 宵征源码
 */
@TableName("cxlaike_dy_personal_letter")
@KeySequence("cxlaike_dy_personal_letter_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DyPersonalLetterDO extends TenantBaseDO {
    /**
     * 主键
     */
    @TableId
    private Long id;
    /**
     * 事件类型
     */
    private String event;
    /**
     * client_key
     */
    private String clientKey;
    /**
     * 发送方 open_id
     */
    private String fromUserId;
    /**
     * 接收方 open_id
     */
    private String toUserId;
    /**
     * 会话 ID
     */
    private String conversationShortId;
    /**
     * 消息 ID
     */
    private String serverMessageId;
    /**
     * 会话类型（1：私聊）
     */
    private Integer conversationType;
    /**
     * 消息创建时间
     */
    private LocalDateTime messageCreateTime;
    /**
     * text：文本 image：图片 emoji：表情 video：视频 retain_consult_card：留资卡片 other：其他 不同类型消息参数见下方介绍
     */
    private String messageType;
    /**
     * 内容
     */
    private String text;
    /**
     * 资源类型
     */
    private String resourceType;
    /**
     * 资源宽度
     */
    private Integer resourceWidth;
    /**
     * 资源高度
     */
    private Integer resourceHeight;
    /**
     * 资源链接
     */
    private String resourceUrl;
    /**
     * 加密后的视频ID
     */
    private String itemId;
    /**
     * 卡片id
     */
    private String cardId;
    /**
     * 1:空白态；2:完成态
     */
    private Integer cardStatus;
    /**
     * 卡片信息
     */
    private String cardData;
    /**
     * 区分发出应用通过发送私信消息接口发送，会显示具体的 clientkey通过端上主动发送，该字段默认为空
     */
    private String source;
    /**
     * 场景类型1:关键词自动回复 2:欢迎语 100:其他
     */
    private Integer sceneType;
    /**
     * 用户基本信息，包括：昵称和头像
     */
    private String userInfos;

}
