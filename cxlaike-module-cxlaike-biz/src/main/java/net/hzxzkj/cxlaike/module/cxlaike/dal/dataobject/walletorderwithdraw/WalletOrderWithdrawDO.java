package net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.walletorderwithdraw;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import net.hzxzkj.cxlaike.framework.mybatis.core.dataobject.BaseDO;

/**
 * 提现流水 DO
 *
 * @author 宵征源码
 */
@TableName("pay_wallet_order_withdraw")
@KeySequence("pay_wallet_order_withdraw_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WalletOrderWithdrawDO extends BaseDO {

    /**
     * 主键
     */
    @TableId
    private Long id;
    /**
     * 订单类型(1.用户钱包,2.商户钱包)
     */
    private Integer userType;
    /**
     * 用户id
     */
    private Long userId;
    /**
     * 所属账号
     */
    private String mobile;
    /**
     * 微信openid
     */
    private String openId;
    /**
     * 提现类型(1.微信,2.支付宝,3.银行卡)
     */
    private Integer withdrawType;
    /**
     * 提现申请时间
     */
    private LocalDateTime applyTime;
    /**
     * 审核状态(1.待审核,2.审核失败,3.审核通过)
     */
    private Integer auditStatus;
    /**
     * 提现时间(审核通过)
     */
    private LocalDateTime payTime;
    /**
     * 金额
     */
    private Long amount;
    /**
     * 状态：0:进行中,1:已成功,2.已失败
     */
    private Integer status;
    /**
     * 失败原因
     */
    private String reason;

}
