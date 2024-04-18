package net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.aivideoresult;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import net.hzxzkj.cxlaike.framework.mybatis.core.dataobject.BaseDO;

/**
 * ai视频生成结果 DO
 *
 * @author 宵征源码
 */
@TableName("cxlaike_ai_video_result")
@KeySequence("cxlaike_ai_video_result_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AiVideoResultDO extends BaseDO {

    /**
     * 主键
     */
    @TableId
    private Long id;
    /**
     * ai视频生成id
     */
    private Long aiTaskId;
    /**
     * jobId
     */
    private String jobId;
    /**
     * mediaURL
     */
    private String mediaUrl;

    /**
     * 合成任务订单Id
     */
    private Long videoOrderId;

    /**
     * 时长
     */
    private Integer duration;

    /**
     * 视频质量（0，无重复，1，低重复，2普通）
     */
    private Integer videoQuality;

    /**
     * 是否模板
     */
    private Boolean isTemplate;
    /**
     * 状态
     */
    private Integer status;

}
