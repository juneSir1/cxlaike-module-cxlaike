package net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.taskorder;

import lombok.*;

import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.*;
import net.hzxzkj.cxlaike.framework.mybatis.core.dataobject.TenantBaseDO;

/**
 * 达人任务订单 DO
 *
 * @author 宵征源码
 */
@TableName("cxlaike_task_order")
@KeySequence("cxlaike_task_order_seq")
// 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TaskOrderDO extends TenantBaseDO {

  /**
   * id
   */
  @TableId
  private Long id;
  /**
   * 达人id
   */
  private Long memberUserId;
  /**
   * 达人等级
   */
  private Integer memberLevel;
  /**
   * 达人获得服务费
   */
  private Long fee;
  /**
   * 任务类型 1-云探 2-实探
   */
  private Integer taskType;
  /**
   * 任务id
   */
  private Long taskId;
  /**
   * 商家扩展表id
   */
  private Long taskExtId;
  /**
   * 口播文案Id
   */
  private Long copyId;
  /**
   * 抖音openid
   */
  private String dyOpenId;
  /**
   * 支付订单编号
   */
  private Long payOrderId;
  /**
   * 发布截止时间
   */
  private LocalDateTime taskInvalidTime;
  /**
   * 商家审核截止时间
   */
  private LocalDateTime auditInvalidTime;
  /**
   * 视频url
   */
  private String videoUrl;
  /**
   * 视频发布时间
   */
  private LocalDateTime videoPublishTime;
  /**
   * 视频链接
   */
  private String videoLink;
  /**
   * 视频状态 0-生成中 2-不适宜公开 4-审核中 5-公开视频
   */
  private Integer videoStatus;
  /**
   * 状态 0.进行中 1.待审核 2.已通过 3.已驳回 4.已取消
   */
  private Integer status;
  /**
   * 结算状态 1-待结算 2-已结算
   */
  private Integer settleStatus;
  /**
   * 结算时间
   */
  private LocalDateTime settleTime;
  /**
   * 取消类型 1-个人取消 2-系统取消
   */
  private Integer cancelType;
  /**
   * 驳回原因
   */
  private String rejectReason;
  /**
   * 播放数
   */
  private Integer videoWatchCount;
  /**
   * 作品ID(加密版本)
   */
  private String videoItemId;
  /**
   * 作品ID(不加密版本)
   */
  private String videoVideoId;
  /**
   * 点赞数
   */
  private Integer upvoteCount;
  /**
   * 分享数
   */
  private Integer shareCount;
  /**
   * 下载数
   */
  private Integer downloadCount;
  /**
   * 评价数
   */
  private Integer commentCount;
  /**
   * 取消时间
   */
  private LocalDateTime cancelTime;
  /**
   * 取消原因
   */
  private String cancelReason;

}
