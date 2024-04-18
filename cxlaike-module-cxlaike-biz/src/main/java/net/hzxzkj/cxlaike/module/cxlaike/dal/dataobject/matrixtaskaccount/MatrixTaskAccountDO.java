package net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.matrixtaskaccount;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import net.hzxzkj.cxlaike.framework.mybatis.core.dataobject.BaseDO;

/**
 * 矩阵任务账号 DO
 *
 * @author 宵征源码
 */
@TableName("cxlaike_matrix_task_account")
@KeySequence("cxlaike_matrix_task_account_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MatrixTaskAccountDO extends BaseDO {
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
     * 账号id
     */
    private Long accountBindId;

}
