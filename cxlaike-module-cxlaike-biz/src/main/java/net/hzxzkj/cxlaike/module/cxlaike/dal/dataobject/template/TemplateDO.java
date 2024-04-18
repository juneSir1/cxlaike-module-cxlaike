package net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.template;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import net.hzxzkj.cxlaike.framework.mybatis.core.dataobject.BaseDO;

/**
 * ai素材混剪视频模板 DO
 *
 * @author 宵征源码
 */
@TableName("cxlaike_template")
@KeySequence("cxlaike_template_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TemplateDO extends BaseDO {

    /**
     * id
     */
    private Long id;

    /**
     * ai视频任务id
     */
    private Long aiTaskId;
    /**
     * 模板名称
     */
    private String tempName;
    /**
     * mediaURL
     */
    private String videoUrl;
    /**
     * 视频结果id
     */
    private Long videoId;
    /**
     * 视频时长
     */
    private Integer duration;
    /**
     * 画面比例类型
     */
    private Integer aspectRatioType;
    /**
     * 视频段数
     */
    private Integer groupNum;
    /**
     * 是否系统模板
     */
    private Byte isSystem;
    /**
     * 模板分类
     */
    private Integer tempIndustry;

    /**
     * 是否热门模板 1是 0否
     */
    private Integer isHot;

    /**
     * 租户ID
     */
    private Long tenantId;
}
