package net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.accountgroup.vo;

import com.diboot.core.binding.query.BindQuery;
import com.diboot.core.binding.query.Comparison;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import net.hzxzkj.cxlaike.framework.common.pojo.PageParam;
import net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.accountgroup.AccountGroupDO;
import net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.memberdybind.MemberDyBindDO;

@Schema(description = "商家后台 - 商户抖音分组管理分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class AccountGroupPageReqVO extends PageParam {

    @Schema(description = "分组名称", example = "赵六")
    @BindQuery(comparison = Comparison.LIKE, field = AccountGroupDO.Fields.name)
    private String name;


    @Schema(description = "平台类型 1抖音 2快手", example = "1")
    @BindQuery(comparison = Comparison.EQ, field = AccountGroupDO.Fields.platformType)
    private Integer platformType;

}
