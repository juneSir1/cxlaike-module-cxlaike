package net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.videoslice;

import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import net.hzxzkj.cxlaike.framework.mybatis.core.dataobject.BaseDO;

/**
 * 描   述:
 *
 * @author ace
 * @version [版本号, 2023/10/14]
 * @see [相关类/方法]
 * 创建日期: 2023/10/14
 */
@TableName("cxlaike_video_slice")
@KeySequence("cxlaike_video_slice_seq")
// 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VideoSliceDO extends BaseDO {

  /**
   * 主键
   */
  @TableId
  private Long id;
  /**
   * 任务类型
   */
  private Integer taskType;
  /**
   * 任务id
   */
  private Long taskId;
  /**
   * 素材id
   */
  private Long materialId;
  /**
   * 视频切片详情
   */
  private String slice;
  /**
   * 切片时长
   */
  private Float sliceDuration;
  /**
   * 使用次数
   */
  private Integer num;

}
