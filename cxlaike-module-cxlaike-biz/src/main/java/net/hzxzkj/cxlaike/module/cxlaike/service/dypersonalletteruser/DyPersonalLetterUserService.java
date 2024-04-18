package net.hzxzkj.cxlaike.module.cxlaike.service.dypersonalletteruser;

import java.util.*;
import javax.validation.*;
import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.dypersonalletteruser.vo.*;
import net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.dypersonalletteruser.DyPersonalLetterUserDO;
import net.hzxzkj.cxlaike.framework.common.pojo.PageResult;

/**
 * 抖音私信用户管理 Service 接口
 *
 * @author 宵征源码
 */
public interface DyPersonalLetterUserService {

    /**
     * 创建抖音私信用户管理
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createDyPersonalLetterUser(@Valid DyPersonalLetterUserCreateReqVO createReqVO);

    /**
     * 更新抖音私信用户管理
     *
     * @param updateReqVO 更新信息
     */
    void updateDyPersonalLetterUser(@Valid DyPersonalLetterUserUpdateReqVO updateReqVO);

    /**
     * 删除抖音私信用户管理
     *
     * @param id 编号
     */
    void deleteDyPersonalLetterUser(Long id);

    /**
     * 获得抖音私信用户管理
     *
     * @param id 编号
     * @return 抖音私信用户管理
     */
    DyPersonalLetterUserDO getDyPersonalLetterUser(Long id);

    /**
     * 获得抖音私信用户管理列表
     *
     * @param ids 编号
     * @return 抖音私信用户管理列表
     */
    List<DyPersonalLetterUserRespVO> getDyPersonalLetterUserList();

    /**
     * 获得抖音私信用户管理分页
     *
     * @param pageReqVO 分页查询
     * @return 抖音私信用户管理分页
     */
    PageResult<DyPersonalLetterUserRespVO> getDyPersonalLetterUserPage(DyPersonalLetterUserPageReqVO pageReqVO);

    /**
     * 获得抖音私信用户管理列表, 用于 Excel 导出
     *
     * @param exportReqVO 查询条件
     * @return 抖音私信用户管理列表
     */
    List<DyPersonalLetterUserDO> getDyPersonalLetterUserList(DyPersonalLetterUserExportReqVO exportReqVO);

    void createUserDO(String openId,List<DyPersonalLetterUserInfoVO> userInfoVOS,Long accountId,String conversationId);

    DyPersonalLetterUserDO getByConversationId(String conversationId);

}
