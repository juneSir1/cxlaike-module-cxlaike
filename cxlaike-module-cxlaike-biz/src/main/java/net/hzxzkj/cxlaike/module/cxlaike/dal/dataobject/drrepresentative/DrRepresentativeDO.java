package net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.drrepresentative;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import net.hzxzkj.cxlaike.framework.mybatis.core.dataobject.BaseDO;

/**
 * 达人探店代 DO
 *
 * @author 宵征源码
 */
@TableName("cxlaike_dr_representative")
@KeySequence("cxlaike_dr_representative_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DrRepresentativeDO extends BaseDO {
    /**
     * 主键
     */
    @TableId
    private Long id;
    /**
     * 平台类型 1-抖音
     */
    private Integer platformType;
    /**
     * 昵称
     */
    private String nickname;
    private String avatar;
    /**
     * 粉丝数
     */
    private String fansCount;
    /**
     * 销售额
     */
    private String sales;
    /**
     * 带货等级
     */
    private String sellGrade;
    /**
     * 内容力
     */
    private String contentGrade;
    /**
     * 信用分
     */
    private String creditScore;
    /**
     * 行业
     */
    private String profession;
    /**
     * 用户信息
     */
    private String userInfo;
    /**
     * 城市
     */
    private String city;

}
