package net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.accountgroup.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

/**
 * @author jianan.han
 * @date 2023/10/23 下午4:30
 * @description
 */
@Data
public class MatrixAccountListRespVO {

    @Schema(description = "分组id")
    private List<Long> groupId;

    @Schema(description = "账号id")
    private Long accountId;

    @Schema(description = "头像")
    private String dyAvatar;

    @Schema(description = "昵称")
    private String dyNickname;

    @Schema(description = "发布次数")
    private Long publishCount;

}
