package net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.packageorder;

import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import net.hzxzkj.cxlaike.framework.mybatis.core.dataobject.TenantBaseDO;

/**
 * 套餐订单 DO
 *
 * @author 宵征源码
 */
@TableName("merchant_package_order")
@KeySequence("merchant_package_order_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PackageOrderDO extends TenantBaseDO {

    /**
     * 主键
     */
    @TableId
    private Long id;

    /**
     * 套餐id
     */
    private Long merchantPackageId;
    /**
     * 价格，单位：分
     */
    private Long price;
    /**
     * 是否已支付：[0:未支付 1:已经支付过]
     */
    private Integer payStatus;
    /**
     * 支付订单编号
     */
    private Long payOrderId;
    /**
     * 支付成功的支付渠道
     */
    private String payChannelCode;
    /**
     * 订单支付时间
     */
    private LocalDateTime payTime;
    /**
     * 退款订单编号
     */
    private Long payRefundId;
    /**
     * 退款金额，单位：分
     */
    private Integer refundPrice;
    /**
     * 退款时间
     */
    private LocalDateTime refundTime;

    /**
     * 失效时间
     */
    private LocalDateTime invalidTime;

}
