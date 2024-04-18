package net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.taskext;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import net.hzxzkj.cxlaike.framework.mybatis.core.dataobject.BaseDO;

/**
 * 商家任务扩展 DO
 *
 * @author 宵征源码
 */
@TableName("cxlaike_task_ext")
@KeySequence("cxlaike_task_ext_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TaskExtDO extends BaseDO {

    /**
     * 主键
     */
    @TableId
    private Long id;
    /**
     * 商家任务id
     */
    private Long taskId;
    /**
     * 带货等级类型(0.lv0,1.lv1)
     */
    private Integer level;
    /**
     * 剩余数量
     */
    private Integer surplusNum;
    /**
     * 总数量
     */
    private Integer totalNum;
    /**
     * 服务费用(分)
     */
    private Long fee;
    /**
     * 总服务费用(分)
     */
    private Long totalFee;
    /**
     * 达人服务费 (分) = fee * memberRatio / 100
     */
    private Long memberFee;
    /**
     * 一级分销%
     */
    private Integer memberOneRatio;
    /**
     * 二级分销%
     */
    private Integer memberTwoRatio;
    /**
     * 服务商%
     */
    private Integer merchantRatio;
    /**
     * 达人%
     */
    private Integer memberRatio;
    /**
     * 平台%
     */
    private Integer systemRatio;

}
