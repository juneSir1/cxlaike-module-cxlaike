package net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.accountgroupcorrelation;

import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;
import lombok.experimental.FieldNameConstants;
import net.hzxzkj.cxlaike.framework.mybatis.core.dataobject.BaseDO;

/**
 * 商户抖音分组关联 DO
 *
 * @author 宵征源码
 */
@TableName(value = "merchant_account_group_correlation",excludeProperty = "deleted")
@KeySequence("merchant_account_group_correlation_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldNameConstants
public class AccountGroupCorrelationDO extends BaseDO {

    /**
     * 分组id
     */
    private Long groupId;
    /**
     * 抖音绑定id
     */
    private Long dyBindId;

}
