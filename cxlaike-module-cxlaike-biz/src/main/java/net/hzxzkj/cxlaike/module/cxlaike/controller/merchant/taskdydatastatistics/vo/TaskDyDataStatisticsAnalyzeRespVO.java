package net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.taskdydatastatistics.vo;

import com.diboot.core.binding.annotation.BindField;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.matrixtask.MatrixTaskDO;
import net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.memberdybind.MemberDyBindDO;
import net.hzxzkj.cxlaike.module.pay.enums.EntityCondtionConstants;


@Data
public class TaskDyDataStatisticsAnalyzeRespVO {

    /**
     * 抖音绑定id
     */
    private Long taskId;

    @Schema(description = "任务名称")
    @BindField(entity = MatrixTaskDO.class,field = MatrixTaskDO.Fields.taskName,condition = EntityCondtionConstants.TASK_DATA_STATISTICS_REQ_BIND_ID)
    private String taskName;

    @Schema(description = "发布数")
    private Integer publishCount;

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


}
