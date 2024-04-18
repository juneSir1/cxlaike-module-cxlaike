package net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.profession;

import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;
import net.hzxzkj.cxlaike.framework.mybatis.core.dataobject.BaseDO;

/**
 * 行业 DO
 *
 * @author 宵征源码
 */
@TableName("cxlaike_profession")
@KeySequence("cxlaike_profession_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProfessionDO extends BaseDO {

    /**
     * id
     */
    @TableId
    private Long id;
    /**
     * 行业code
     */
    private String code;
    /**
     * 层级
     */
    private Integer level;
    /**
     * 上级code
     */
    private String upCode;
    /**
     * 名称
     */
    private String name;

}
