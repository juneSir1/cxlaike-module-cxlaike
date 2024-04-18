package net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.kuaishou.vo;

import lombok.Data;

import java.util.Map;

/**
 * @author jianan.han
 * @date 2023/12/13 下午3:12
 * @description
 */
@Data
public class DealWebhooksReqVO {

    private String event;
    private String from_user_id;
    private String client_key;
    private String msg_id;
    private String to_user_id;
    private Map content;
}
