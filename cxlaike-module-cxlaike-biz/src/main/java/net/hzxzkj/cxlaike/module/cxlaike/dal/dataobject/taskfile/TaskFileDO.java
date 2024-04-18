package net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.taskfile;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import net.hzxzkj.cxlaike.framework.mybatis.core.dataobject.BaseDO;

/**
 * 来客任务文件 DO
 *
 * @author 宵征源码
 */
@TableName("cxlaike_task_file")
@KeySequence("cxlaike_task_file_seq")
// 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TaskFileDO extends BaseDO {

  /**
   * 主键
   */
  @TableId
  private Long id;
  /**
   * 任务类型(1,ai剪辑视频,2.探店任务)
   */
  private Integer taskType;
  /**
   * 任务id
   */
  private Long taskId;
  /**
   * 视频组名
   */
  private String name;
  /**
   * 文件id
   */
  private Long fileId;
  /**
   * 序号
   */
  private Integer sort;

}
