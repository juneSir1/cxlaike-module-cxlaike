package net.hzxzkj.cxlaike.module.cxlaike.api.memberdybind.dto;

import lombok.Data;

/**
 * @author jianan.han
 * @date 2023-08-30 17:23
 * @description
 */
@Data
public class AppUserDyInfoRespVO {

    private Long id;

    private String dyAvatar;

    private String dyNickname;

    private Integer dyGrade;

    private Integer dyAccreditStatus;

    /**
     * 抖音绑定审核状态 1-待审核 2-通过 3-拒绝
     */
    private Integer dyAuditStatus;

    private String dyOpenId;

    private String dyUserId;

    private Integer status;


}
