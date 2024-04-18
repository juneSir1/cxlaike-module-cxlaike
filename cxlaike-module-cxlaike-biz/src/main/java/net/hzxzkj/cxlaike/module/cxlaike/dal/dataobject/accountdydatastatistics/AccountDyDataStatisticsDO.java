package net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.accountdydatastatistics;

import lombok.*;

import java.time.LocalDate;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import net.hzxzkj.cxlaike.framework.mybatis.core.dataobject.BaseDO;

/**
 * 账号纬度抖音数据统计 DO
 *
 * @author 宵征源码
 */
@TableName("cxlaike_account_dy_data_statistics")
@KeySequence("cxlaike_account_dy_data_statistics_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccountDyDataStatisticsDO extends BaseDO {
    /**
     * 主键
     */
    @TableId
    private Long id;
    /**
     * 平台类型
     * @see net.hzxzkj.cxlaike.module.cxlaike.enums.PlatformType
     */
    private Integer platformType;
    /**
     * 类型 1-达人 2-商户
     */
    private Integer dyType;
    /**
     * 抖音绑定id
     */
    private Long dyBindId;
    /**
     * 统计日期
     */
    private LocalDate statisticsTime;
    /**
     * 发布数增量(相比于前一条)
     */
    private Integer publishCountIncrement;
    /**
     * 发布数
     */
    private Integer publishCount;
    /**
     * 播放量增量(相比于前一条)
     */
    private Integer playCountIncrement;
    /**
     * 播放量
     */
    private Integer playCount;
    /**
     * 粉丝数增量(相比于前一条)
     */
    private Integer fansCountIncrement;
    /**
     * 粉丝数
     */
    private Integer fansCount;
    /**
     * 点赞数增量(相比于前一条)
     */
    private Integer diggCountIncrement;
    /**
     * 点赞数
     */
    private Integer diggCount;
    /**
     * 评论数增量(相比于前一条)
     */
    private Integer commentCountIncrement;
    /**
     * 评论数
     */
    private Integer commentCount;
    /**
     * 转发数(分享)增量(相比于前一条)
     */
    private Integer shareCountIncrement;
    /**
     * 转发数(分享)
     */
    private Integer shareCount;
    /**
     * 收藏数增量(相比于前一条)
     */
    private Integer collectCountIncrement;
    /**
     * 收藏数
     */
    private Integer collectCount;

}
