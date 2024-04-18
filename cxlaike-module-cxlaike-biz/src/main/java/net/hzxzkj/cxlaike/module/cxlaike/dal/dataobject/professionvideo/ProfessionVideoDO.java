package net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.professionvideo;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import net.hzxzkj.cxlaike.framework.mybatis.core.dataobject.BaseDO;

/**
 * 精选行业视频 DO
 *
 * @author 宵征源码
 */
@TableName("cxlaike_profession_video")
@KeySequence("cxlaike_profession_video_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProfessionVideoDO extends BaseDO {
    /**
     * 主键
     */
    @TableId
    private Long id;
    /**
     * 标题
     */
    private String title;
    /**
     * 播放量
     */
    private String playCount;
    /**
     * 封面url
     */
    private String coverUrl;
    /**
     * 视频url
     */
    private String videoUrl;
    /**
     * 时长
     */
    private String duration;

}
