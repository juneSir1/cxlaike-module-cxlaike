package net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.matrixtasktitle;

import com.baomidou.mybatisplus.extension.handlers.FastjsonTypeHandler;
import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import net.hzxzkj.cxlaike.framework.mybatis.core.dataobject.BaseDO;
import net.hzxzkj.cxlaike.framework.mybatis.core.type.StringListTypeHandler;

/**
 * 矩阵任务标题 DO
 *
 * @author 宵征源码
 */
@TableName(value = "cxlaike_matrix_task_title", autoResultMap = true)
@KeySequence("cxlaike_matrix_task_title_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MatrixTaskTitleDO extends BaseDO {
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
     * 标题内容
     */
    private String title;
    /**
     * at 用户 openId
     */
    @TableField(typeHandler = StringListTypeHandler.class)
    private List<String> openIds;

}
