package net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.taskmaterialdownload;

import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;
import net.hzxzkj.cxlaike.framework.mybatis.core.dataobject.BaseDO;

/**
 * 商家任务素材 DO
 *
 * @author 宵征源码
 */
@TableName("cxlaike_task_material_download")
@KeySequence("cxlaike_task_material_download_seq")
// 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TaskMaterialDownloadDO extends BaseDO {

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
   * 任务素材id
   */
  private Long taskMaterialId;

}
