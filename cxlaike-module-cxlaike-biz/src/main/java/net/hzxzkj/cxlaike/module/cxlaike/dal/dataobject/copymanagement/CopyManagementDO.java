package net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.copymanagement;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import net.hzxzkj.cxlaike.framework.mybatis.core.dataobject.BaseDO;

/**
 * 任务口播文案管理 DO
 *
 * @author 宵征源码
 */
@TableName("cxlaike_copy_management")
@KeySequence("cxlaike_copy_management_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CopyManagementDO extends BaseDO {

    /**
     * 主键
     */
    @TableId
    private Long id;
    /**
     * 任务类型(1.ai视频生成,2.探店任务)
     */
    private Integer type;
    /**
     * 任务id
     */
    private Long taskId;
    /**
     * 任务视频组id
     */
    private Long videoGroupId;
    /**
     * 口播文案
     */
    private String copywriting;
    /**
     * 口播文案文件id
     */
    private String fileId;
    /**
     * 口播文案文件名
     */
    private String fileName;
    /**
     * 口播文案文件url
     */
    private String url;
    /**
     * 口播文案文件时长
     */
    private Integer duration;

}
