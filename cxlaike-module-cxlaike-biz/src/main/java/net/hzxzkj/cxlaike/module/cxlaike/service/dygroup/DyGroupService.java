package net.hzxzkj.cxlaike.module.cxlaike.service.dygroup;

import com.diboot.core.service.BaseService;
import net.hzxzkj.cxlaike.framework.common.pojo.PageResult;
import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.accountgroup.vo.*;
import net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.accountgroup.AccountGroupDO;

import javax.validation.Valid;
import java.util.List;

/**
 * 商户抖音分组管理 Service 接口
 *
 * @author 宵征源码
 */
public interface DyGroupService extends BaseService<AccountGroupDO> {

    /**
     * 创建商户抖音分组管理
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createDyGroup(@Valid AccountGroupCreateReqVO createReqVO);

    /**
     * 更新商户抖音分组管理
     *
     * @param updateReqVO 更新信息
     */
    void updateDyGroup(@Valid AccountGroupUpdateReqVO updateReqVO);

    /**
     * 删除商户抖音分组管理
     *
     * @param id 编号
     */
    void deleteDyGroup(Long id);

    /**
     * 获得商户抖音分组管理
     *
     * @param id 编号
     * @return 商户抖音分组管理
     */
    AccountGroupDO getDyGroup(Long id);

    /**
     * 获得商户抖音分组管理列表
     *
     * @param platform 平台
     * @return 商户抖音分组管理列表
     */
    List<AccountGroupDO> getDyGroupList(Integer platform);

    /**
     * 获得商户抖音分组管理分页
     *
     * @param pageReqVO 分页查询
     * @return 商户抖音分组管理分页
     */
    PageResult<AccountGroupDO> getDyGroupPage(AccountGroupPageReqVO pageReqVO);

    /**
     * 获得商户抖音分组管理列表, 用于 Excel 导出
     *
     * @param exportReqVO 查询条件
     * @return 商户抖音分组管理列表
     */
    List<AccountGroupDO> getDyGroupList(AccountGroupExportReqVO exportReqVO);

    void transferDyGroup(@Valid AccountGroupTransferReqVO updateReqVO);

    List<MatrixAccountListRespVO> getAccountByGroup(MatrixAccountListReqVO reqVO);

    AccountGroupDO getByName(String name,Integer platform);

}
