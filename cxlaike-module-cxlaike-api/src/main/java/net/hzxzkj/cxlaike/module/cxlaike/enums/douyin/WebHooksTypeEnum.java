package net.hzxzkj.cxlaike.module.cxlaike.enums.douyin;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author jianan.han
 * @date 2023/12/13 下午3:16
 * @description
 */
@Getter
@AllArgsConstructor
public enum WebHooksTypeEnum {

    CREATE_VIDEO("create_video", "创建视频"),
    IM_RECEIVE_MSG("im_receive_msg", "接收私信"),
    IM_SEND_MSG("im_send_msg", "发送私信"),
    UN_AUTH("unauthorize", "取消授权"),
    item_comment_reply("item_comment_reply", "授权用户的视频被评论事件通知"),

    ;

    private final String type;

    private final String decs;

}
