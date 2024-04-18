package net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.clientcase;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import net.hzxzkj.cxlaike.framework.mybatis.core.dataobject.BaseDO;

/**
 * 客户案例 DO
 *
 * @author 宵征源码
 */
@TableName("cxlaike_client_case")
@KeySequence("cxlaike_client_case_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClientCaseDO extends BaseDO {
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
     * 标签
     */
    private String label;
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
    private String content;

}
