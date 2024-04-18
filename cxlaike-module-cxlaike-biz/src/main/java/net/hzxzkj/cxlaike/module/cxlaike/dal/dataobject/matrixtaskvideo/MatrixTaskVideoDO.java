package net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.matrixtaskvideo;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import net.hzxzkj.cxlaike.framework.mybatis.core.dataobject.BaseDO;

/**
 * 矩阵任务视频 DO
 *
 * @author 宵征源码
 */
@TableName("cxlaike_matrix_task_video")
@KeySequence("cxlaike_matrix_task_video_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MatrixTaskVideoDO extends BaseDO {
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
     * 视频id(素材id)
     */
    private Long videoId;
    /**
     * 扫码发布是否被使用 1-是 0-否
     */
    private Integer isUsed;
    /**
     * 素材类型
     * @see net.hzxzkj.cxlaike.module.infra.enums.MaterialTypeEnum
     */
    private Integer materialType;

}
