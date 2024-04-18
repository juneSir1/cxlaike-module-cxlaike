package net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.aivideotaskmaterial;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import net.hzxzkj.cxlaike.framework.mybatis.core.dataobject.BaseDO;

/**
 * ai视频任务素材 DO
 *
 * @author 宵征源码
 */
@TableName("cxlaike_ai_video_task_material")
@KeySequence("cxlaike_ai_video_task_material_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AiVideoTaskMaterialDO extends BaseDO {

    /**
     * 主键
     */
    @TableId
    private Long id;
    /**
     * ai视频任务主键
     */
    private Long aiTaskId;
    /**
     * ai视频任务视频组主键
     */
    private Long videoGroupId;
    /**
     * 素材类型
     */
    private Integer type;
    /**
     * 视频组名
     */
    private String name;

    /**
     * 文件id
     */
    private Long fileId;

    /**
     * 文件名
     */
    private String fileName;
    /**
     * 合成任务订单Id
     */
    private Long videoOrderId;
    /**
     * jobId
     */
    private String jobId;
    /**
     * 素材url
     */
    private String url;
    /**
     * 视频时长(秒)
     */
    private Integer duration;
    /**
     * 序号
     */
    private Integer sort;
    /**
     * 视频状态
     */
    private Integer status;

}
