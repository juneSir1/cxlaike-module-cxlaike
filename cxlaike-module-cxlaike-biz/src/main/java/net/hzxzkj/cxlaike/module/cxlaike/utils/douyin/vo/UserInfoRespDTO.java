package net.hzxzkj.cxlaike.module.cxlaike.utils.douyin.vo;

import lombok.Data;

/**
 * @author jianan.han
 * @date 2023-08-17 11:16
 * @description
 */
@Data
public class UserInfoRespDTO {

    private String avatar;

    private String nickname;
    /**
     * 类型：
     *
     * EAccountM：普通企业号
     * EAccountS：认证企业号
     * EAccountK：品牌企业号
     */
    private String e_account_role;

    private Long error_code;

    private Long fan;

    private Long follow;

}
