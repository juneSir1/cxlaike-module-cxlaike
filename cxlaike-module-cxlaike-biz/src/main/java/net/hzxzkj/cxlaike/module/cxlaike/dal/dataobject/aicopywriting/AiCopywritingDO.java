package net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.aicopywriting;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import net.hzxzkj.cxlaike.framework.mybatis.core.dataobject.BaseDO;

/**
 * ai文案 DO
 *
 * @author 宵征源码
 */
@TableName("cxlaike_ai_copywriting")
@KeySequence("cxlaike_ai_copywriting_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AiCopywritingDO extends BaseDO {

    /**
     * id
     */
    @TableId
    private Long id;
    /**
     * 行业code
     */
    private String professionCode;
    /**
     * 行业名称
     */
    private String professionName;
    /**
     * 内容类型 1-团购推广套餐 2-产品宣传 3-门店推广 4-剧本创作
     */
    private Integer contentType;
    /**
     * 内容描述
     */
    private String contentDesc;
    /**
     * 文案关键字
     */
    private String contentKeyword;
    /**
     * 字数限制 1-80 5-150 10-250 15-350 20-其他
     */
    private Integer wordsCountRestrict;
    private Integer wordsCount;
    private Integer quantity;
    /**
     * 条数限制 1-1 5-3 10-5 15-其他
     */
    private Integer quantityRestrict;

    /**
     * 生成状态 1-生成中 2-完成 3-失败
     */
    private Integer aiStatus;
    /**
     * 消耗数量
     */
    private Long expendQuantity;
    /**
     * ai文案
     */
    private String aiCopywriting;

    private String referenceCopy;

    private Long expendGold;
}
