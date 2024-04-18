package net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.aivideotaskcover;

import lombok.*;
import java.util.*;
import com.baomidou.mybatisplus.annotation.*;
import net.hzxzkj.cxlaike.framework.mybatis.core.dataobject.BaseDO;

/**
 * ai素材混剪视频封面图片关联 DO
 *
 * @author 宵征源码
 */
@TableName("cxlaike_ai_video_task_cover")
@KeySequence("cxlaike_ai_video_task_cover_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AiVideoTaskCoverDO extends BaseDO {

    /**
     * 主键
     */
    @TableId
    private Long id;

    /**
     * ai素材混剪任务id
     */
    private Long taskId;
    /**
     * 封面图片文件id
     */
    private Long fileId;
    /**
     * 封面url
     */
    private String url;
    /**
     * 排序
     */
    private Integer sort;

}
