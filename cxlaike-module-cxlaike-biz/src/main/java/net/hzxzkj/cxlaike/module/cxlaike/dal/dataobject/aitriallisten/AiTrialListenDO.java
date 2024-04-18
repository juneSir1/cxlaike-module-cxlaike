package net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.aitriallisten;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import net.hzxzkj.cxlaike.framework.mybatis.core.dataobject.BaseDO;

/**
 * ai语音试听功能 DO
 *
 * @author 宵征源码
 */
@TableName("cxlaike_ai_trial_listen")
@KeySequence("cxlaike_ai_trial_listen_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AiTrialListenDO extends BaseDO {

    /**
     * 主键
     */
    @TableId
    private Long id;
    /**
     * 用户Id
     */
    private Long userId;
    /**
     * 口播文案
     */
    private String copywriting;
    /**
     * 配音人
     */
    private String dubCode;
    /**
     * 配音语速
     */
    private Integer dubSpeechRate;
    /**
     * 配音语调
     */
    private Integer dubPitchRate;
    /**
     * 配音音量
     */
    private Integer dubGain;
    /**
     * 配音采样率
     */
    private Integer dubSamplingRate;
    /**
     * 视频生成Id
     */
    private Long videoOrderId;
    /**
     * 状态
     */
    private Integer status;

}
