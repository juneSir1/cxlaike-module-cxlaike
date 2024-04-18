package net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.accountdydatastatistics.vo;

import com.diboot.core.binding.annotation.BindField;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.memberdybind.MemberDyBindDO;
import net.hzxzkj.cxlaike.module.pay.enums.EntityCondtionConstants;

import java.time.LocalDate;


@Data
public class AccountDyDataStatisticsAnalyzeRespVO {

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

    @Schema(description = "发布数增量")
    private Integer publishCountIncrement;

    @Schema(description = "播放量增量")
    private Integer playCountIncrement;

    @Schema(description = "粉丝数增量")
    private Integer fansCountIncrement;

    @Schema(description = "点赞数增量")
    private Integer diggCountIncrement;

    @Schema(description = "评论数增量")
    private Integer commentCountIncrement;

    @Schema(description = "转发数(分享)增量")
    private Integer shareCountIncrement;

    @Schema(description = "收藏数增量")
    private Integer collectCountIncrement;

    @Schema(description = "发布数", example = "17991")
    private Integer publishCount;

    @Schema(description = "粉丝数", example = "6019")
    private Integer fansCount;

    @Schema(description = "播放量", example = "5862")
    private Integer playCount;


}
