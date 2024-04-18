package net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.systemarg;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import net.hzxzkj.cxlaike.framework.mybatis.core.dataobject.BaseDO;

/**
 * 来客系统参数 DO
 *
 * @author 宵征源码
 */
@TableName("cxlaike_system_arg")
@KeySequence("cxlaike_system_arg_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SystemArgDO extends BaseDO {

    /**
     * 主键
     */
    @TableId
    private Long id;
    /**
     * 云探达人每日接单数量
     */
    private Integer yunDayOrderNum;
    /**
     * 实探达人每日接单数量
     */
    private Integer entityDayOrderNum;
    /**
     * 云探同一任务天数
     */
    private Integer yunDayNum;
    /**
     * 实探同一任务天数
     */
    private Integer entityDayNum;
    /**
     * 云探可接单数量
     */
    private Integer yunOrderNum;
    /**
     * 实探可接单数量
     */
    private Integer entityOrderNum;
    /**
     * 云探ai混剪报名费(分)
     */
    private Long yunAiClipFee;
    /**
     * 云探数字人混剪报名费(分)
     */
    private Long yunDigitalClipFee;
    /**
     * 实探真人出镜报名费(分)
     */
    private Long entityOnCamera;
    /**
     * 实探现场拍摄报名费(分)
     */
    private Long entityNotOnCamera;
    /**
     * 热门推荐城市
     */
    private String popularCities;
    /**
     * 任务详情页注意事项
     */
    private String detail;

}
