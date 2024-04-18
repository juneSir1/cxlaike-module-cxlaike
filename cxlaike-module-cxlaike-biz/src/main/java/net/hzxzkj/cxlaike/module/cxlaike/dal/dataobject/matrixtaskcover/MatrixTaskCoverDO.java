package net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.matrixtaskcover;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import net.hzxzkj.cxlaike.framework.mybatis.core.dataobject.BaseDO;

/**
 * 矩阵任务封面 DO
 *
 * @author 宵征源码
 */
@TableName("cxlaike_matrix_task_cover")
@KeySequence("cxlaike_matrix_task_cover_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MatrixTaskCoverDO extends BaseDO {
    /**
     * 主键
     */
    @TableId
    private Long id;
    /**
     * 任务id
     */
    private Long matrixTaskId;
    /**
     * 封面url
     */
    private Long coverId;

}
