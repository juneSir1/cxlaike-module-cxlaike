package net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.taskmaterialslice;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import net.hzxzkj.cxlaike.framework.mybatis.core.dataobject.BaseDO;

/**
 * 商家任务素材切片 DO
 *
 * @author 宵征源码
 */
@TableName("cxlaike_task_material_slice")
@KeySequence("cxlaike_task_material_slice_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TaskMaterialSliceDO extends BaseDO {

    /**
     * 主键
     */
    @TableId
    private Long id;
    /**
     * 任务id
     */
    private Long taskId;
    /**
     * 参数
     */
    private String param;
    /**
     * 口播文案
     */
    private String copy;
    /**
     * 使用当前切片数据的达人Id
     */
    private Long memberUserId;
    /**
     * 状态(0.未用,1.已用)
     */
    private Integer status;

}
