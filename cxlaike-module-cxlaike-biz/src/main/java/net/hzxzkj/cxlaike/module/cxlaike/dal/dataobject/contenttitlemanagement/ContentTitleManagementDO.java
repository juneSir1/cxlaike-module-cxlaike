package net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.contenttitlemanagement;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import net.hzxzkj.cxlaike.framework.mybatis.core.dataobject.BaseDO;

/**
 * 任务内容标题管理 DO
 *
 * @author 宵征源码
 */
@TableName("cxlaike_content_title_management")
@KeySequence("cxlaike_content_title_management_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ContentTitleManagementDO extends BaseDO {

    /**
     * id
     */
    private Long id;

    /**
     * 任务id
     */
    private Long taskId;
    /**
     * 视频组id
     */
    private Long videoGroupId;
    /**
     * 口播文案
     */
    private String contentTitle;

}
