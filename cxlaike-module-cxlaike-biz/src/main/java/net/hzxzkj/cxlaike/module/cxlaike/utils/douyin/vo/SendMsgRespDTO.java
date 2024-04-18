package net.hzxzkj.cxlaike.module.cxlaike.utils.douyin.vo;

import lombok.Data;

/**
 * @author jianan.han
 * @date 2023-08-17 11:16
 * @description
 */
@Data
public class SendMsgRespDTO {


    private SendMsgDTO data;
    private ExtraDTO extra;

    private String msg_id;
    private String[] msg_list;


}
