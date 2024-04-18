package net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.earnings;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import net.hzxzkj.cxlaike.framework.mybatis.core.dataobject.BaseDO;

/**
 * 代理商每日汇总 DO
 *
 * @author 宵征源码
 */
@TableName("cxlaike_earnings")
@KeySequence("cxlaike_earnings_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EarningsDO extends BaseDO {

    /**
     * 用户ID
     */
    private Long userId;
    /**
     * 任务金额，单位：分
     */
    private Long taskAmount;
    /**
     * 矩阵金额，单位：分
     */
    private Long matrixAmount;
    /**
     * 套餐金额，单位：分
     */
    private Long packageAmount;
    /**
     * 团队金额，单位：分
     */
    private Long teamAmount;
    /**
     * 统计时间
     */
    private LocalDateTime date;

}
