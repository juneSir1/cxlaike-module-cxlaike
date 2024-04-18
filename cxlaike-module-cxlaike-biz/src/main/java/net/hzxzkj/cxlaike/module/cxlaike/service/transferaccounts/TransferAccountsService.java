package net.hzxzkj.cxlaike.module.cxlaike.service.transferaccounts;



import net.hzxzkj.cxlaike.framework.common.pojo.PageResult;
import net.hzxzkj.cxlaike.module.cxlaike.controller.admin.transferaccounts.vo.TransferAccountsCreateReqVO;
import net.hzxzkj.cxlaike.module.cxlaike.controller.admin.transferaccounts.vo.TransferAccountsExportReqVO;
import net.hzxzkj.cxlaike.module.cxlaike.controller.admin.transferaccounts.vo.TransferAccountsPageReqVO;
import net.hzxzkj.cxlaike.module.cxlaike.controller.admin.transferaccounts.vo.TransferAccountsUpdateReqVO;
import net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.transferaccounts.TransferAccountsDO;

import javax.validation.Valid;
import java.util.Collection;
import java.util.List;

/**
 * 转账账户 Service 接口
 *
 * @author 宵征源码
 */
public interface TransferAccountsService {

    /**
     * 创建转账账户
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createTransferAccounts(@Valid TransferAccountsCreateReqVO createReqVO);

    /**
     * 更新转账账户
     *
     * @param updateReqVO 更新信息
     */
    void updateTransferAccounts(@Valid TransferAccountsUpdateReqVO updateReqVO);

    /**
     * 删除转账账户
     *
     * @param id 编号
     */
    void deleteTransferAccounts(Long id);
    /**
     * 获得转账账户
     *
     * @param id 编号
     * @return 转账账户
     */
    TransferAccountsDO getTransferAccounts(Long id);
    /**
     * 获得转账账户
     *
     * @return 转账账户
     */
    TransferAccountsDO getTransferAccounts();

    /**
     * 获得转账账户列表
     *
     * @param ids 编号
     * @return 转账账户列表
     */
    List<TransferAccountsDO> getTransferAccountsList(Collection<Long> ids);

    /**
     * 获得转账账户分页
     *
     * @param pageReqVO 分页查询
     * @return 转账账户分页
     */
    PageResult<TransferAccountsDO> getTransferAccountsPage(TransferAccountsPageReqVO pageReqVO);

    /**
     * 获得转账账户列表, 用于 Excel 导出
     *
     * @param exportReqVO 查询条件
     * @return 转账账户列表
     */
    List<TransferAccountsDO> getTransferAccountsList(TransferAccountsExportReqVO exportReqVO);

}
