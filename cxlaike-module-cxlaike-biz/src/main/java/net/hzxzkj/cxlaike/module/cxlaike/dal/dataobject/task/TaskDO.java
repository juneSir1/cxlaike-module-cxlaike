package net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.task;

import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import net.hzxzkj.cxlaike.framework.mybatis.core.type.JsonLongSetTypeHandler;
import net.hzxzkj.cxlaike.framework.mybatis.core.dataobject.TenantBaseDO;

/**
 * 商家发布任务 DO
 *
 * @author 宵征源码
 */
@TableName(value = "cxlaike_task", autoResultMap = true)
@KeySequence("cxlaike_task_seq")
// 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TaskDO extends TenantBaseDO {

  /**
   * 主键
   */
  @TableId
  private Long id;
  /**
   * 任务类型(1.实探,2.云探)
   */
  private Integer taskType;
  /**
   * 发布任务商家id
   */
  private Long merchantUserId;
  /**
   * 平台类型
   */
  private Integer platformType;
  /**
   * 任务标题
   */
  private String title;
  /**
   * 内容标题
   */
  private String contentTitle;
  /**
   * 任务开始时间
   */
  private LocalDateTime startTime;
  /**
   * 任务结束时间
   */
  private LocalDateTime endTime;
  /**
   * 团购链接
   */
  private String url;
  /**
   * 门店id集合
   */
  @TableField(typeHandler = JsonLongSetTypeHandler.class)
  private Set<Long> shopIds;
  /**
   * 任务照片
   */
  private String taskPhoto;
  /**
   * 话题要求
   */
  private String topicRequire;
  /**
   * 粉丝数量要求
   */
  private String fansNumRequire;
  /**
   * 达人地区要求
   */
  private String areaRequire;
  /**
   * 商品佣金比例
   */
  private String commissionRatio;
  /**
   * 视频剪辑类型
   */
  private Integer clipType;
  /**
   * 是否到店
   */
  private Boolean isShop;
  /**
   * 任务说明
   */
  private String description;
  /**
   * 是否需要审核
   */
  private Boolean isAudit;

  /**
   * 群二维码图片
   */
  private String groupPhoto;

  /**
   * 幂等id
   */
  private String idempotentId;

  /**
   * 平台推荐值
   */
  private Integer prValue;
  /**
   * 任务状态
   */
  private Integer status;

}
