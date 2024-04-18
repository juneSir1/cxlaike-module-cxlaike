package net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.accountgroup.vo;

import com.diboot.core.binding.annotation.BindEntityList;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.memberdybind.MemberDyBindDO;
import net.hzxzkj.cxlaike.module.pay.enums.EntityCondtionConstants;

import java.time.LocalDateTime;
import java.util.List;

@Schema(description = "管理后台 - 商户抖音分组管理 Response VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class AccountGroupRespVO extends AccountGroupBaseVO {

    private Long id;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    private LocalDateTime createTime;

    @BindEntityList(entity = MemberDyBindDO.class,condition = EntityCondtionConstants.GROUP_AND_GROUP_CORRELATION_AND_BIND_REF_GROUP_ID)
    private List<MemberDyBindDO> dyBindDOS;

    private Integer accountCount;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    private LocalDateTime updateTime;


    public Integer getAccountCount(){
        if(dyBindDOS != null){
            return dyBindDOS.size();
        }
        return 0;
    }

}
