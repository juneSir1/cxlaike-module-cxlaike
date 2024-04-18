package net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.aicopywritingrecord;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import net.hzxzkj.cxlaike.framework.mybatis.core.dataobject.BaseDO;

/**
 * ai文案记录 DO
 *
 * @author 宵征源码
 */
@TableName("cxlaike_ai_copywriting_record")
@KeySequence("cxlaike_ai_copywriting_record_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AiCopywritingRecordDO extends BaseDO {

    /**
     * id
     */
    @TableId
    private Long id;

    /**
     * 业务id
     */
    private Long bId;
    /**
     * 业务类型 1-ai生成文案 2-ai优化文案
     */
    private Integer type;

    /**
     * 返回id
     */
    private String returnId;
    /**
     * 输入文案
     */
    private String inputContent;
    /**
     * 输出文案
     */
    private String outputContent;
    /**
     * 输出字数
     */
    private Long completionTokens;
    /**
     * 输出字数
     */
    private Long promptTokens;
    /**
     * 原因
     */
    private String finishReason;

    private String returnBody;

}
