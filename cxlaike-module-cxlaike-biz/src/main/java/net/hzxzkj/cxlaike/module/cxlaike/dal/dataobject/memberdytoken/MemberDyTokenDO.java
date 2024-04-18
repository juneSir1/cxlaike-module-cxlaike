package net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.memberdytoken;

import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;
import lombok.experimental.FieldNameConstants;
import net.hzxzkj.cxlaike.framework.mybatis.core.dataobject.BaseDO;
import net.hzxzkj.cxlaike.framework.mybatis.core.dataobject.TenantBaseDO;

import java.time.LocalDateTime;

/**
 * 达人抖音token DO
 *
 * @author 宵征源码
 */
@TableName("member_dy_token")
@KeySequence("member_dy_token_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldNameConstants
public class MemberDyTokenDO extends TenantBaseDO {

    /**
     * id
     */
    @TableId
    private Long id;
    /**
     * 抖音凭证
     */
    private String dyAccessToken;
    /**
     * access_token 接口调用凭证超时时间
     */
    private LocalDateTime dyExpiresTime;
    /**
     * 抖音用户刷新access_token
     */
    private String dyRefreshToken;
    /**
     * refresh_token 凭证超时时间
     */
    private LocalDateTime dyRefreshExpiresTime;
    /**
     * 抖音授权状态 1-已授权 2-授权失效
     */
    private Integer dyAccreditStatus;
    /**
     * 抖音授权时间
     */
    private LocalDateTime dyAccreditTime;
    /**
     * 短信提醒时间
     */
    private LocalDateTime smsRemindTime;

    /**
     * 平台1抖音 2快手
     */
    private Integer platformType;



}
