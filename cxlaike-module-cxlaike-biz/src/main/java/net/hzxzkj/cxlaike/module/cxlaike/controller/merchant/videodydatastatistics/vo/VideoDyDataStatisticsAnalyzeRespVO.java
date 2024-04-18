package net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.videodydatastatistics.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import com.diboot.core.binding.annotation.BindField;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.matrixtask.MatrixTaskDO;
import net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.memberdybind.MemberDyBindDO;
import net.hzxzkj.cxlaike.module.pay.enums.EntityCondtionConstants;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

import static net.hzxzkj.cxlaike.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;


@Data
public class VideoDyDataStatisticsAnalyzeRespVO {

    /**
     * 抖音绑定id
     */
    private Long dyBindId;

    @Schema(description = "昵称")
    @BindField(entity = MemberDyBindDO.class,field = MemberDyBindDO.Fields.dyNickname,condition = EntityCondtionConstants.ACCOUNT_DATA_STATISTICS_REQ_BIND_ID)
    private String dyNickname;

    @Schema(description = "头像")
    @BindField(entity = MemberDyBindDO.class,field = MemberDyBindDO.Fields.dyAvatar,condition = EntityCondtionConstants.ACCOUNT_DATA_STATISTICS_REQ_BIND_ID)
    private String dyAvatar;

    @Schema(description = "视频链接")
    private String videoLink;

    @Schema(description = "播放量增量")
    private Integer playCountIncrement;

    @Schema(description = "点赞数增量")
    private Integer diggCountIncrement;

    @Schema(description = "评论数增量")
    private Integer commentCountIncrement;

    @Schema(description = "转发数(分享)增量")
    private Integer shareCountIncrement;

    @Schema(description = "收藏数增量")
    private Integer collectCountIncrement;

    @Schema(description = "播放量", example = "5862")
    private Integer playCount;

    @Schema(description = "30天平均播放时长")
    private Double playDuration;

    @Schema(description = "poi名称")
    private String poiName;

    /**
     * 抖音绑定id
     */
    private Long orderId;

    @Schema(description = "发布时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime publishTime;


}
