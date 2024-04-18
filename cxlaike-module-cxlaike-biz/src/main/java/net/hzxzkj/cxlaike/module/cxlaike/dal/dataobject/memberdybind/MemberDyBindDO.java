package net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.memberdybind;

import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;
import lombok.experimental.FieldNameConstants;
import net.hzxzkj.cxlaike.framework.mybatis.core.dataobject.BaseDO;
import net.hzxzkj.cxlaike.framework.mybatis.core.dataobject.TenantBaseDO;

/**
 * 达人抖音绑定 DO
 *
 * @author 宵征源码
 */
@TableName("member_dy_bind")
@KeySequence("member_dy_bind_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldNameConstants
public class MemberDyBindDO extends TenantBaseDO {

    /**
     * id
     */
    @TableId
    private Long id;

    /**
     * 用户类型 1-达人 2-商户
     */
    private Integer userType;

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

    private String poiId;

    private String poiName;

    private Integer provinceCode;
    private String provinceName;
    private Integer cityCode;
    private String cityName;
    private Integer countyCode;
    private String countyName;

    private String location;

    private String address;
    /**
     * 驳回理由
     */
    private String reason;
    /**
     * 账号状态 1-启用 2-停用
     */
    private Integer status;

    /**
     * 0抖音 1快手
     */
    private Integer platformType;


}
