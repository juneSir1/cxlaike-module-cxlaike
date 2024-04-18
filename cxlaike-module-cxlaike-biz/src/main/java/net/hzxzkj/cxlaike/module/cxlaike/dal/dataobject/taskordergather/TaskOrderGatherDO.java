package net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.taskordergather;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import net.hzxzkj.cxlaike.framework.mybatis.core.dataobject.BaseDO;
import net.hzxzkj.cxlaike.framework.mybatis.core.dataobject.TenantBaseDO;

/**
 * 用户视频集合 DO
 *
 * @author 宵征源码
 */
@TableName("cxlaike_task_order_gather")
@KeySequence("cxlaike_task_order_gather_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TaskOrderGatherDO extends TenantBaseDO {
    @TableId
    private Long id;
    /**
     * 类型 1-达人 2-商家
     */
    private Integer orderType;
    /**
     * 任务id
     */
    private Long taskId;
    /**
     * 订单id
     */
    private Long orderId;
    /**
     * 用户绑定id(抖音)
     */
    private Long accountBindId;
    /**
     * 外部视频id(抖音)
     */
    private String videoId;

    private String itemId;
}
