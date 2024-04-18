package net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.metahumanconfig;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import net.hzxzkj.cxlaike.framework.mybatis.core.dataobject.BaseDO;

/**
 * 来客数字人配置 DO
 *
 * @author 宵征源码
 */
@TableName("cxlaike_meta_human_config")
@KeySequence("cxlaike_meta_human_config_seq")
// 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MetaHumanConfigDO extends BaseDO {

  /**
   * 主键
   */
  @TableId
  private Long id;
  /**
   * 用户类型
   */
  private Integer type;
  /**
   * 用户id
   */
  private Long userId;
  /**
   * 配音code
   */
  private String dubCode;
  /**
   * 数字人code
   */
  private String metaHumanCode;
  /**
   * 数字人名称
   */
  private String name;
  /**
   * 数字人描述
   */
  private String depict;
  /**
   * 数字人图片
   */
  private String picture;

}
