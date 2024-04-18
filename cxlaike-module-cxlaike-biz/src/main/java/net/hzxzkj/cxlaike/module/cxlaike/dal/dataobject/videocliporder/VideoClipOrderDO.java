package net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.videocliporder;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import net.hzxzkj.cxlaike.framework.mybatis.core.dataobject.BaseDO;

/**
 * 视频剪辑订单 DO
 *
 * @author 宵征源码
 */
@TableName("cxlaike_video_clip_order")
@KeySequence("cxlaike_video_clip_order_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VideoClipOrderDO extends BaseDO {

    /**
     * 主键
     */
    @TableId
    private Long id;
    /**
     * 订单类型
     */
    private Integer orderType;
    /**
     * 剪辑任务类型
     */
    private Integer taskType;
    /**
     * 视频类型
     */
    private Integer videoType;
    /**
     * 素材id
     */
    private Long materialId;
    /**
     * jobId
     */
    private String jobId;
    /**
     * 访问地址
     */
    private String mediaUrl;
    /**
     * 时长
     */
    private Integer duration;
    /**
     * 任务状态
     */
    private Integer status;
    /**
     * 回参
     */
    private String response;

}
