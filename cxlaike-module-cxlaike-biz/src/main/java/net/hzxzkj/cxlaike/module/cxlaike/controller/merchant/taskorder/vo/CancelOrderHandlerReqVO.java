package net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.taskorder.vo;

import lombok.Data;

/**
 * @author jianan.han
 * @date 2023/10/9 上午10:30
 * @description
 */
@Data
public class CancelOrderHandlerReqVO {

    private Long id;

    private Integer cancelType;

    private Long memberId;
}
