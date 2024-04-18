package net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.aivideoconfig;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import net.hzxzkj.cxlaike.framework.mybatis.core.dataobject.BaseDO;

/**
 * ai视频配置 DO
 *
 * @author 宵征源码
 */
@TableName("cxlaike_ai_video_config")
@KeySequence("cxlaike_ai_video_config_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AiVideoConfigDO extends BaseDO {

    /**
     * id
     */
    @TableId
    private Long id;
    /**
     * 类型(1.AaiMotionInEffect,2.SubType,3.Fonts,4.Keywords,5.EffectColorStyle,6.Colors,7.Symbol)
     */
    private Integer type;
    /**
     * 内容值
     */
    private String value;
    /**
     * 0.关闭,1.开启)
     */
    private Integer status;

}
