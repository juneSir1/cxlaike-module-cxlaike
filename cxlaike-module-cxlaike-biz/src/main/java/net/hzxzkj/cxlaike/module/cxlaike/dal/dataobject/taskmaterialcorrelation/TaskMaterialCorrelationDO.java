package net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.taskmaterialcorrelation;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import net.hzxzkj.cxlaike.framework.mybatis.core.dataobject.BaseDO;

/**
 * 商家任务与素材关联 DO
 *
 * @author 宵征源码
 */
@TableName(value = "cxlaike_task_material_correlation",excludeProperty = "deleted")
@KeySequence("cxlaike_task_material_correlation_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TaskMaterialCorrelationDO extends BaseDO {
    /**
     * 主键
     */
    @TableId
    private Long id;
    /**
     * 任务类型 1-实探 2-云探 3-矩阵
     */
    private Integer taskType;
    /**
     * 平台类型 1-抖音
     */
    private Integer platformType;
    /**
     * 商家任务id
     */
    private Long taskId;
    /**
     * 素材id
     */
    private Long materialId;
    /**
     * 任务状态 1-未开始 2-进行中 3-已结束 4-终止
     */
    private Integer taskStatus;

}
