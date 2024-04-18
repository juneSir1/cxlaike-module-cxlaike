package net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.region;

import lombok.*;

import com.baomidou.mybatisplus.annotation.*;
import net.hzxzkj.cxlaike.framework.mybatis.core.dataobject.BaseDO;




/**
 * 地区 DO
 *
 * @author 宵征源码
 */
@TableName("cxlaike_region")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegionDO extends BaseDO {

    /**
     * 主键id
     */
    @TableId
    private Long id;
    /**
     * 父级id
     */
    private Integer pid;
    /**
     * 区划编码
     */
    private Long code;
    /**
     * 区划名称
     */
    private String name;
    /**
     * 级次id 0:省/自治区/直辖市 1:市级 2:县级
     */
    private Boolean level;


}
