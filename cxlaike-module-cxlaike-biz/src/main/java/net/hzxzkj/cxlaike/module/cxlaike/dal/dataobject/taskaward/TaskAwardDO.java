package net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.taskaward;

import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import net.hzxzkj.cxlaike.framework.mybatis.core.dataobject.BaseDO;

/**
 * 任务完成奖励 DO
 *
 * @author 宵征源码
 */
@TableName("cxlaike_task_award")
@KeySequence("cxlaike_task_award_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TaskAwardDO extends BaseDO {

    /**
     * 主键
     */
    @TableId
    private Long id;
    /**
     * 任务类型(1.邀请,2.达人一级奖励,3.达人二级奖励,4.商户一级奖励)
     */
    private Integer type;
    /**
     * 用户id
     */
    private Long userId;
    /**
     * 任务id
     */
    private Long cxlaikeTaskId;
    /**
     * 奖励金额
     */
    private BigDecimal amount;
    /**
     * 可奖励总额
     */
    private BigDecimal totalAmount;
    /**
     * 状态(0.进行中,1.成功,2.失败)
     */
    private Integer status;

}
