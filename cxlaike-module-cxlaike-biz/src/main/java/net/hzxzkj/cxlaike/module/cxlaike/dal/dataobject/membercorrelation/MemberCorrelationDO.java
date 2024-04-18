package net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.membercorrelation;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import net.hzxzkj.cxlaike.framework.mybatis.core.dataobject.BaseDO;

/**
 * 商户与达人视频账号(抖音账号)的任务关联 DO
 *
 * @author 宵征源码
 */
@TableName("merchant_member_correlation")
@KeySequence("merchant_member_correlation_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemberCorrelationDO extends BaseDO {

    /**
     * 商户的租户id
     */
    private Long merchantId;
    /**
     * 达人视频号(抖音)id
     */
    private Long dyBindId;

}
