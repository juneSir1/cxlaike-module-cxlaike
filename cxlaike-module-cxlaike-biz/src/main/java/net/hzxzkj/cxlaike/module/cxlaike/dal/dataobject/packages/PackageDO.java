package net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.packages;

import lombok.*;
import com.baomidou.mybatisplus.annotation.*;
import lombok.experimental.FieldNameConstants;
import net.hzxzkj.cxlaike.framework.mybatis.core.dataobject.BaseDO;

/**
 * 商户套餐 DO
 *
 * @author 宵征源码
 */
@TableName("merchant_package")
@KeySequence("merchant_package_seq")
// 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldNameConstants
public class PackageDO extends BaseDO {

  /**
   * 主键
   */
  @TableId
  private Long id;

  /**
   * 名称
   */
  private String name;
  /**
   * 支付金额，单位：分
   */
  private Long price;
  /**
   * 天数
   */
  private Long days;
  /**
   * 显示顺序
   */
  private Integer sort;
  /**
   * 状态（0正常 1停用）
   */
  private Integer status;

}
