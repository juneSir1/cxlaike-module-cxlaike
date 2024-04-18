package net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.matrixaccount.vo;

import com.diboot.core.binding.annotation.BindEntityList;
import com.diboot.core.binding.annotation.BindField;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.accountgroup.AccountGroupDO;
import net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.memberdytoken.MemberDyTokenDO;
import net.hzxzkj.cxlaike.module.pay.enums.EntityCondtionConstants;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author jianan.han
 * @date 2023/10/23 下午4:30
 * @description
 */
@Data
public class MatrixAccountRespVO {

    @Schema(description = "id")
    private Long id;

    @Schema(description = "所属平台 1-抖音")
    private Integer platformType = 1;

    @Schema(description = "头像")
    private String dyAvatar;

    @Schema(description = "昵称")
    private String dyNickname;

    @Schema(description = "openId")
    private String dyOpenId;

    @Schema(description = "抖音授权状态 1-已授权 2-授权失效")
    @BindField(entity = MemberDyTokenDO.class,field = MemberDyTokenDO.Fields.dyAccreditStatus,condition = EntityCondtionConstants.DY_BIND_TOKEN_REF_ID)
    private Integer dyAccreditStatus;

    @Schema(description = "分组列表")
    @BindEntityList(entity = AccountGroupDO.class,condition = EntityCondtionConstants.DY_BIND_GROUP_REF_GROUP_ID)
    private List<AccountGroupVO> groupVOS;

    @Schema(description = "poiId")
    private String poiId;

    @Schema(description = "poi名称")
    private String poiName;

    @Schema(description = "授权时间")
    @BindField(entity = MemberDyTokenDO.class,field = MemberDyTokenDO.Fields.dyAccreditTime,condition = EntityCondtionConstants.DY_BIND_TOKEN_REF_ID)
    private LocalDateTime dyAccreditTime;

    @Schema(description = "授权到期时间")
    @BindField(entity = MemberDyTokenDO.class,field = MemberDyTokenDO.Fields.dyRefreshExpiresTime,condition = EntityCondtionConstants.DY_BIND_TOKEN_REF_ID)
    private LocalDateTime dyExpiresTime;

    /**
     * 账号状态 1-启用 2-停用
     */
    @Schema(description = "账号状态 1-启用 2-停用")
    private Integer status;

    @Schema(description = "省code")
    private Integer provinceCode;
    @Schema(description = "省名称")
    private String provinceName;
    @Schema(description = "市code")
    private Integer cityCode;
    @Schema(description = "市名称")
    private String cityName;
    @Schema(description = "区code")
    private Integer countyCode;
    @Schema(description = "区名称")
    private String countyName;
    @Schema(description = "详细地址")
    private String address;
    @Schema(description = "经纬度，格式：X,Y")
    private String location;

}
