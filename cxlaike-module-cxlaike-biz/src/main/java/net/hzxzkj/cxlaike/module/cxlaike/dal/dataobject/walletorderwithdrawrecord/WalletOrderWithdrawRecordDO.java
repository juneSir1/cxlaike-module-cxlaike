package net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.walletorderwithdrawrecord;

import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;
import net.hzxzkj.cxlaike.framework.mybatis.core.dataobject.BaseDO;

import java.time.LocalDateTime;

/**
 * 提现流水记录表 DO
 *
 * @author 宵征源码
 */
@TableName("pay_wallet_order_withdraw_record")
@KeySequence("pay_wallet_order_withdraw_record_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WalletOrderWithdrawRecordDO extends BaseDO {

    /**
     * 主键
     */
    @TableId
    private Long id;
    /**
     * 流水id
     */
    private String outBatchNo;
    /**
     * 入参
     */
    private String inputParams;
    /**
     * 出参
     */
    private String outputParams;


}
