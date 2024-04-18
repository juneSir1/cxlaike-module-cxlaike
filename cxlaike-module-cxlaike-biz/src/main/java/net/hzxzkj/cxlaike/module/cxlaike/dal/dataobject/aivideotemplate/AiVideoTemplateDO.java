package net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.aivideotemplate;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import net.hzxzkj.cxlaike.framework.mybatis.core.dataobject.BaseDO;

/**
 * ai视频模板 DO
 *
 * @author 宵征源码
 */
@TableName("cxlaike_ai_video_template")
@KeySequence("cxlaike_ai_video_template_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AiVideoTemplateDO extends BaseDO {

    /**
     * id
     */
    @TableId
    private Long id;
    /**
     * 类型
     */
    private Integer type;
    /**
     * 内容值
     */
    private String value;

}
