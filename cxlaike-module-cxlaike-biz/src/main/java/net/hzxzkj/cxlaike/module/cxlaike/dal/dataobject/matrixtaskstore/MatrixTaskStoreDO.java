package net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.matrixtaskstore;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import net.hzxzkj.cxlaike.framework.mybatis.core.dataobject.BaseDO;

/**
 * 矩阵任务门店 DO
 *
 * @author 宵征源码
 */
@TableName("cxlaike_matrix_task_store")
@KeySequence("cxlaike_matrix_task_store_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MatrixTaskStoreDO extends BaseDO {
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
     * 门店id
     */
    private Long storeId;

}
