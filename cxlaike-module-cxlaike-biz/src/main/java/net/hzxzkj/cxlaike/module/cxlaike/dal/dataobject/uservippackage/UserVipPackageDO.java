package net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.uservippackage;

import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.FieldNameConstants;
import net.hzxzkj.cxlaike.framework.mybatis.core.dataobject.TenantBaseDO;

/**
 * 商家用户vip套餐关联 DO
 *
 * @author 宵征源码
 */
@TableName("merchant_user_vip_package")
@KeySequence("merchant_user_vip_package_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldNameConstants
public class UserVipPackageDO extends TenantBaseDO {

    /**
     * id
     */
    @TableId
    private Long id;
    /**
     * vip套餐id
     */
    private Long merchantPackageId;
    /**
     * 开始时间
     */
    private LocalDateTime startTime;
    /**
     * 结束时间
     */
    private LocalDateTime endTime;
    /**
     * vip状态
     */
    private Integer status;
    /**
     * 是否主套餐
     */
    private Boolean isMaster;

}
