package net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.aiclipvideotaskgroup;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import net.hzxzkj.cxlaike.framework.mybatis.core.dataobject.BaseDO;

/**
 * ai素材混剪视频任务-视频组设置 DO
 *
 * @author 宵征源码
 */
@TableName("cxlaike_ai_clip_video_task_group")
@KeySequence("cxlaike_ai_clip_video_task_group_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AiClipVideoTaskGroupDO extends BaseDO {

    /**
     * ai视频任务主键
     */
    @TableId
    private Long id;
    /**
     * ai视频任务主键
     */
    private Long aiTaskId;
    /**
     * 字幕来源类型
     */
    private Integer copywritingSourceType;
    /**
     * 视频组名
     */
    private String name;
    /**
     * 视频组时长(秒)
     */
    private Integer totalDuration;
    /**
     * 序号
     */
    private Integer sort;
    /**
     * 视频组数量
     */
    private Integer num;
    /**
     * 是否原声
     */
    private Boolean original;
    /**
     * 时长设置类型
     */
    private Integer durationType;
    /**
     * 时长设置-时长
     */
    private Integer duration;
    /**
     * 视频状态
     */
    private Integer status;

}
