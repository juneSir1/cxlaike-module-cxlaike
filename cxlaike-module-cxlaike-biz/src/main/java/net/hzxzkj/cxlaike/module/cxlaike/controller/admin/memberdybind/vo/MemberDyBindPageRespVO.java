package net.hzxzkj.cxlaike.module.cxlaike.controller.admin.memberdybind.vo;

import lombok.Data;
import lombok.ToString;

import java.time.LocalDateTime;


@Data
@ToString(callSuper = true)
public class MemberDyBindPageRespVO {

    private Long id;

    private String dyUserId;

    private String dyNickname;

    private String mobile;

    private String dyHomePage;

    private String dyGradeUrl;

    private Integer dyGrade;

    private LocalDateTime dyAccreditTime;

    private Integer dyFansCount;
    private Integer dyGiveLikeCount;
    private Integer dyWorksCount;

    private Integer dyAuditStatus;

    private Integer dyAccreditStatus;
}

