package net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.settlementlog;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import net.hzxzkj.cxlaike.framework.mybatis.core.dataobject.BaseDO;

/**
 * 结算流水 DO
 *
 * @author 宵征源码
 */
@TableName("cxlaike_settlement_log")
@KeySequence("cxlaike_settlement_log_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SettlementLogDO extends BaseDO {

    /**
     * id
     */
    @TableId
    private Long id;

    /**
     * 用户ID
     */
    private Long userId;
    /**
     * 类型
     */
    private Integer type;
    /**
     * 金额，单位：分
     */
    private Long amount;
    /**
     * 账号
     */
    private String account;
    /**
     * 账号类型
     */
    private Integer accountType;

}
