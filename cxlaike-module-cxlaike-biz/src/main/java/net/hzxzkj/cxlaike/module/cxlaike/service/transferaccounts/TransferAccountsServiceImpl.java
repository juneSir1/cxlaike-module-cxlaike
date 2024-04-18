package net.hzxzkj.cxlaike.module.cxlaike.service.transferaccounts;

import net.hzxzkj.cxlaike.framework.common.enums.CommonStatusEnum;
import net.hzxzkj.cxlaike.framework.common.pojo.PageResult;
import net.hzxzkj.cxlaike.module.cxlaike.controller.admin.transferaccounts.vo.TransferAccountsCreateReqVO;
import net.hzxzkj.cxlaike.module.cxlaike.controller.admin.transferaccounts.vo.TransferAccountsExportReqVO;
import net.hzxzkj.cxlaike.module.cxlaike.controller.admin.transferaccounts.vo.TransferAccountsPageReqVO;
import net.hzxzkj.cxlaike.module.cxlaike.controller.admin.transferaccounts.vo.TransferAccountsUpdateReqVO;
import net.hzxzkj.cxlaike.module.cxlaike.convert.transferaccounts.TransferAccountsConvert;
import net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.transferaccounts.TransferAccountsDO;
import net.hzxzkj.cxlaike.module.cxlaike.dal.mysql.transferaccounts.TransferAccountsMapper;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;

import static net.hzxzkj.cxlaike.framework.common.exception.util.ServiceExceptionUtil.exception;
import static net.hzxzkj.cxlaike.module.cxlaike.enums.ErrorCodeConstants.DATA_ERROR;

/**
 * 转账账户 Service 实现类
 *
 * @author 宵征源码
 */
@Service
@Validated
public class TransferAccountsServiceImpl implements TransferAccountsService {

    @Resource
    private TransferAccountsMapper transferAccountsMapper;

    @Override
    public Long createTransferAccounts(TransferAccountsCreateReqVO createReqVO) {
        // 插入
        TransferAccountsDO transferAccounts = TransferAccountsConvert.INSTANCE.convert(createReqVO);
        transferAccountsMapper.insert(transferAccounts);
        // 返回
        return transferAccounts.getId();
    }

    @Override
    public void updateTransferAccounts(TransferAccountsUpdateReqVO updateReqVO) {
        // 校验存在
        validateTransferAccountsExists(updateReqVO.getId());
        // 更新
        TransferAccountsDO updateObj = TransferAccountsConvert.INSTANCE.convert(updateReqVO);
        transferAccountsMapper.updateById(updateObj);
    }

    @Override
    public void deleteTransferAccounts(Long id) {
        // 校验存在
        validateTransferAccountsExists(id);
        // 删除
        transferAccountsMapper.deleteById(id);
    }

    private void validateTransferAccountsExists(Long id) {
        if (transferAccountsMapper.selectById(id) == null) {
            throw exception(DATA_ERROR);
        }
    }

    @Override
    public TransferAccountsDO getTransferAccounts(Long id) {
        return transferAccountsMapper.selectById(id);
    }

    @Override
    public TransferAccountsDO getTransferAccounts() {
        return transferAccountsMapper.selectOne(TransferAccountsDO::getStatus, CommonStatusEnum.ENABLE.getStatus());
    }

    @Override
    public List<TransferAccountsDO> getTransferAccountsList(Collection<Long> ids) {
        return transferAccountsMapper.selectBatchIds(ids);
    }

    @Override
    public PageResult<TransferAccountsDO> getTransferAccountsPage(TransferAccountsPageReqVO pageReqVO) {
        return transferAccountsMapper.selectPage(pageReqVO);
    }

    @Override
    public List<TransferAccountsDO> getTransferAccountsList(TransferAccountsExportReqVO exportReqVO) {
        return transferAccountsMapper.selectList(exportReqVO);
    }

}
