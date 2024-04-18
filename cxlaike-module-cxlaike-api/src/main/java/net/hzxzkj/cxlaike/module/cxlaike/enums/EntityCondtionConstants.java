package net.hzxzkj.cxlaike.module.cxlaike.enums;

public interface EntityCondtionConstants {



    /**
     * 查询邀请的商家数了
     */
    String INVITE_MERCHANT_REF = "this.parentUserId=id and type=3";

    /**
     * 查询邀请的商家数了
     */
    String USER_ID_REF = "this.userId=user_id";


    /**
     * 根据userId用户信息
     */
    String USER_ID_ID_REF = "this.userId=id";

    /**
     * 根据用户id获取套餐信息
     */
    String USER_VIP_PACKAGE_AND_USER_ID_REF = "this.userId=tenant_id and is_master=1 ";


    /**
     * 通过用户id关联套餐名称
     */
    String PACKAGE_AND_USER_ID_REF = "this.userId=merchant_user_vip_package.tenant_id and merchant_user_vip_package.is_master=1 and id=merchant_user_vip_package.merchant_package_id";

}
