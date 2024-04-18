package net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.aisystemarg;

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
@TableName("cxlaike_ai_system_arg")
@KeySequence("cxlaike_ai_system_arg_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AiSystemArgDO extends BaseDO {

    /**
     * 主键
     */
    @TableId
    private Long id;

    /**
     * 用户id
     */
    private Long userId;
    /**
     * 系统参数类型
     */
    private Integer sysType;
    /**
     * 具体类型
     */
    private Integer type;
    /**
     * 名字
     */
    private String name;
    /**
     * 描述
     */
    private String depict;
    /**
     * 编号
     */
    private String code;

    /**
     * 图片
     */
    private String picture;
    /**
     * url
     */
    private String url;

}
