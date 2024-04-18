package net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.matrixaccount.vo;

import com.diboot.core.binding.query.BindQuery;
import com.diboot.core.binding.query.Comparison;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import net.hzxzkj.cxlaike.framework.common.pojo.PageParam;
import net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.accountgroupcorrelation.AccountGroupCorrelationDO;
import net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.memberdybind.MemberDyBindDO;
import net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.memberdytoken.MemberDyTokenDO;
import net.hzxzkj.cxlaike.module.pay.enums.EntityCondtionConstants;

@Schema(description = "商家 - 分发账号列表 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class MatrixAccountPageReqVO extends PageParam {


//    @Schema(description = "内容类型 1-团购推广套餐 2-产品宣传 3-门店推广 4-剧本创作", example = "1")
//    private Integer contentType;
//
//    @Schema(description = "内容描述")
//    private String contentDesc;


    @Schema(description = "所属平台 1-抖音 2快手")
    private Integer platformType;


    @Schema(description = "用户类型 1-达人 2-商户")
    @BindQuery(comparison = Comparison.EQ, field = MemberDyBindDO.Fields.userType)
    private Integer userType;

    @Schema(description = "抖音昵称")
    @BindQuery(comparison = Comparison.LIKE, field = MemberDyBindDO.Fields.dyNickname)
    private String dyNickname;

    @Schema(description = "分组id")
    @BindQuery(entity = AccountGroupCorrelationDO.class,comparison = Comparison.EQ, field = AccountGroupCorrelationDO.Fields.groupId, condition = EntityCondtionConstants.DY_BIND_GROUP_REQ_GROUP_ID)
    private Long groupId;

    @Schema(description = "抖音授权状态 1-已授权 2-授权失效")
    @BindQuery(entity = MemberDyTokenDO.class,comparison = Comparison.EQ, field = MemberDyTokenDO.Fields.dyAccreditStatus, condition = EntityCondtionConstants.DY_BIND_TOKEN_REF_ID)
    private Integer dyAccreditStatus;

}
