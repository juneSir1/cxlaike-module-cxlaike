package net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.matrixtask;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import lombok.experimental.FieldNameConstants;
import net.hzxzkj.cxlaike.framework.mybatis.core.dataobject.BaseDO;
import net.hzxzkj.cxlaike.framework.mybatis.core.dataobject.TenantBaseDO;

/**
 * 矩阵任务 DO
 *
 * @author 宵征源码
 */
@TableName("cxlaike_matrix_task")
@KeySequence("cxlaike_matrix_task_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldNameConstants
public class MatrixTaskDO extends TenantBaseDO {

    /**
     * 主键
     */
    @TableId
    private Long id;

    /**
     * 任务类型 1-矩阵 2-扫码
     */
    private Integer taskType;

    /**
     * 平台类型 1-抖音
     */
    private Integer platformType;
    /**
     * 定位类型 1-不需要 2-使用账号默认定位 3-统一定位
     */
    private Integer locationType;
    /**
     * 抖音定位id
     */
    private String poiId;
    /**
     * 抖音定位名称
     */
    private String poiName;
    /**
     * 经纬度，格式：X,Y
     */
    private String location;
    /**
     * 详细地址
     */
    private String address;

    /**
     * 抖音小程序id
     */
    private String microAppId;

    /**
     * 抖音开发者在小程序中生成该页面时写的 path 地址
     */
    private String microAppUrl;

    /**
     * 抖音小程序标题
     */
    private String microAppTitle;

    /**
     * 快手需要挂载小黄车的商品ID
     */
    private Long merchantProductId ;
    /**
     * 任务名称
     */
    private String taskName;
    /**
     * 分配规则 1-按账号固定分配 2-平均分配
     */
    private Integer allotRule;
    /**
     * 分配条数
     */
    private Integer allotCount;
    /**
     * 发布时间规则 1-立即发布 2-定时发布
     */
    private Integer publishTimeRule;
    /**
     * 发布时间
     */
    private LocalDateTime publishTime;
    /**
     * 发布方式规则 1-按视频间隔发布 2-按单账号视频间隔
     */
    private Integer publishRule;
    /**
     * 时间间隔单位 1-分 2-时 3-天
     */
    private Integer intervalUnit;
    /**
     * 时间间隔值
     */
    private Integer intervalValue;
    /**
     * 任务状态 1-未开始 2-进行中 3-已结束 4-终止
     */
    private Integer status;
    /**
     * 任务执行开始时间
     */
    private Integer taskTimeStart;
    /**
     * 任务执行结束时间
     */
    private Integer taskTimeEnd;
    /**
     * 已扫码人数
     */
    private Long scanCount;

}
