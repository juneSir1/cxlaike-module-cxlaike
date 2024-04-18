package net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.aivideotaskfontset;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import net.hzxzkj.cxlaike.framework.mybatis.core.dataobject.BaseDO;

/**
 * ai视频任务-文字设置 DO
 *
 * @author 宵征源码
 */
@TableName("cxlaike_ai_video_task_font_set")
@KeySequence("cxlaike_ai_video_task_font_set_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AiVideoTaskFontSetDO extends BaseDO {

    /**
     * id
     */
    @TableId
    private Long id;

    /**
     * ai视频任务id
     */
    private Long aiTaskId;

    /**
     * ai视频任务视频组id
     */
    private Long videoGroupId;

    /**
     * ai视频任务-字段
     */
    private String fieldName;
    /**
     * 内容值
     */
    private String value;

}
