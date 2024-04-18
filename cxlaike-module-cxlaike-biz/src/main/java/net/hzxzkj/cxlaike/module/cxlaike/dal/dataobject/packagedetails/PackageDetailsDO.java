package net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.packagedetails;

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
 * 商户套餐细则 DO
 *
 * @author 宵征源码
 */
@TableName("merchant_package_details")
@KeySequence("merchant_package_details_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PackageDetailsDO extends BaseDO {

    /**
     * 主键
     */
    @TableId
    private Long id;

    /**
     * 套餐id
     */
    private Long merchantPackageId;
    /**
     * 名称
     */
    private String name;
    /**
     * 类型
     */
    private Integer ruleType;

    /**
     * 数量
     */
    private Integer value;

    /**
     * 排序
     */
    private Integer sort;

}
