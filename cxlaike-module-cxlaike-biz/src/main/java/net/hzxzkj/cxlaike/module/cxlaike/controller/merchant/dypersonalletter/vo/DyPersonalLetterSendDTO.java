package net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.dypersonalletter.vo;

import lombok.Data;

/**
 * 抖音私信管理 Base VO，提供给添加、修改、详细的子 VO 使用
 * 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
 */
@Data
public class DyPersonalLetterSendDTO {

    private Integer msg_type = 1;

    private DyPersonalLetterSendTextDTO text;

}
