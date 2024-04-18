package net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.dypersonalletteruser;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import net.hzxzkj.cxlaike.framework.mybatis.core.dataobject.BaseDO;

/**
 * 抖音私信用户管理 DO
 *
 * @author 宵征源码
 */
@TableName("cxlaike_dy_personal_letter_user")
@KeySequence("cxlaike_dy_personal_letter_user_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DyPersonalLetterUserDO extends BaseDO {
    /**
     * 主键
     */
    @TableId
    private Long id;
    /**
     * 用户open_id
     */
    private String openId;
    /**
     * 昵称
     */
    private String nickName;
    /**
     * 头像
     */
    private String avatar;
    /**
     * 姓名
     */
    private String realName;
    /**
     * 省code
     */
    private Integer provinceCode;
    /**
     * 省
     */
    private String provinceName;
    /**
     * 市code
     */
    private Integer cityCode;
    /**
     * 市
     */
    private String cityName;
    /**
     * 区code
     */
    private Integer countyCode;
    /**
     * 区
     */
    private String countyName;
    /**
     * 状态 0-未沟通 1-已沟通 2-无意向 3-有意向 4-已合作
     */
    private Integer communicationStatus;
    /**
     * 手机号
     */
    private String mobile;
    /**
     * 微信
     */
    private String wx;
    /**
     * 公司
     */
    private String company;
    /**
     * 备注
     */
    private String remark;

    private Long accountId;

    private String conversationId;

}
