package net.hzxzkj.cxlaike.module.cxlaike.api.memberdybind.dto;

import lombok.Data;

/**
 * @author jianan.han
 * @date 2023/10/20 上午11:32
 * @description
 */
@Data
public class MemberDyBindDTO {

    /**
     * id
     */
    private Long id;

    private Long memberId;
    /**
     * 抖音openid
     */
    private String dyOpenId;

    /**
     * 抖音昵称
     */
    private String dyNickname;
    /**
     * 抖音用户id
     */
    private String dyUserId;
    private String dyAvatar;

    /**
     * 抖音主页链接
     */
    private String dyHomePage;
    /**
     * 抖音带货等级图片地址
     */
    private String dyGradeUrl;
    /**
     * 抖音带货等级
     */
    private Integer dyGrade;
    /**
     * 抖音绑定审核状态 1-待审核 2-通过 3-拒绝
     */
    private Integer dyAuditStatus;
    /**
     * 抖音粉丝数量
     */
    private Integer dyFansCount;
    /**
     * 抖音点赞数量
     */
    private Integer dyGiveLikeCount;
    /**
     * 抖音作品数量
     */
    private Integer dyWorksCount;

    private Integer provinceCode;
    private String provinceName;
    private Integer cityCode;
    private String cityName;
    private Integer countyCode;
    private String countyName;
    /**
     * 驳回理由
     */
    private String reason;
}
