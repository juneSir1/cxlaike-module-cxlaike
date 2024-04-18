package net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.taskmaterial;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import net.hzxzkj.cxlaike.framework.mybatis.core.dataobject.BaseDO;

/**
 * 商家任务素材 DO
 *
 * @author 宵征源码
 */
@TableName("cxlaike_task_material")
@KeySequence("cxlaike_task_material_seq")
// 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TaskMaterialDO extends BaseDO {

  /**
   * 主键
   */
  @TableId
  private Long id;
  /**
   * 达人Id
   */
  private Long memberUserId;
  /**
   * 商家任务id
   */
  private Long taskId;
  /**
   * 达人订单Id
   */
  private Long taskOrderId;
  /**
   * 类型(1.素材,2.ai剪辑视频, 3.数字人素材)
   */
  private Integer type;
  /**
   * 素材文件夹名
   */
  private String folderName;
  /**
   * 文件名
   */
  private String fileName;
  /**
   * 素材切片表Id
   */
  private Long sliceId;
  /**
   * 视频生成Id
   */
  private Long videoOrderId;
  /**
   * jobId
   */
  private String jobId;
  /**
   * 素材url
   */
  private String materialUrl;
  /**
   * 状态
   */
  private Integer status;
  /**
   * 序号
   */
  private Integer sort;

}
