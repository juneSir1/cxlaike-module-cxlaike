package net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.taskapply;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import net.hzxzkj.cxlaike.framework.mybatis.core.dataobject.BaseDO;

/**
 * 达人报名任务 DO
 *
 * @author 宵征源码
 */
@TableName("cxlaike_task_apply")
@KeySequence("cxlaike_task_apply_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TaskApplyDO extends BaseDO {

    /**
     * id
     */
    @TableId
    private Long id;
    /**
     * 达人id
     */
    private Long memberId;
    /**
     * 任务id
     */
    private Long taskId;
    /**
     * 任务扩展Id
     */
    private Long taskExtId;
    /**
     * 抖音openId
     */
    private String openId;
    /**
     * 钱包订单id
     */
    private Long walletOrderId;
    /**
     * 支付订单Id
     */
    private Long payOrderId;
    /**
     * 报名费(分)
     */
    private Long entryFee;
    /**
     * 报名状态 1-报名未支付 2-已支付 3-已退款 4-已失效
     */
    private Integer applyStatus;
    /**
     * 报名失效时间
     */
    private LocalDateTime applyInvalidTime;

}
