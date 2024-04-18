package net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.systakelevelconfig;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import net.hzxzkj.cxlaike.framework.mybatis.core.dataobject.BaseDO;

/**
 * 来客系统等级参数配置 DO
 *
 * @author 宵征源码
 */
@TableName("cxlaike_sys_take_level_config")
@KeySequence("cxlaike_sys_take_level_config_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SysTakeLevelConfigDO extends BaseDO {

    /**
     * 主键
     */
    @TableId
    private Long id;
    /**
     * 类型(1.云探-ai,2.云探-数字人混剪,3.实探-现场拍摄,4.实探-真人出镜)
     */
    private Integer type;
    /**
     * 等级类型
     */
    private Integer levelType;
    /**
     * 服务费(分)
     */
    private Long fee;
    /**
     * 一级分销%
     */
    private Integer memberOneRatio;
    /**
     * 二级分销%
     */
    private Integer memberTwoRatio;
    /**
     * 服务商%
     */
    private Integer merchantRatio;
    /**
     * 达人%
     */
    private Integer memberRatio;
    /**
     * 平台%
     */
    private Integer systemRatio;

}
