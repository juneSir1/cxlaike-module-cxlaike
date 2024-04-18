package net.hzxzkj.cxlaike.module.cxlaike.controller.admin.memberdybind.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import net.hzxzkj.cxlaike.framework.common.pojo.PageParam;

import java.time.LocalDateTime;


@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class MemberDyBindPageReqVO extends PageParam {

    private String dyNickname;

    private LocalDateTime accreditTimeStart;
    private LocalDateTime accreditTimeEnd;

    private String mobile;

    private Integer auditStatus;

}
