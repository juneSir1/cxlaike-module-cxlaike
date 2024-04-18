package net.hzxzkj.cxlaike.module.cxlaike.service.clientcase;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import java.util.*;
import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.clientcase.vo.*;
import net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.clientcase.ClientCaseDO;
import net.hzxzkj.cxlaike.framework.common.pojo.PageResult;

import net.hzxzkj.cxlaike.module.cxlaike.convert.clientcase.ClientCaseConvert;
import net.hzxzkj.cxlaike.module.cxlaike.dal.mysql.clientcase.ClientCaseMapper;

import static net.hzxzkj.cxlaike.framework.common.exception.util.ServiceExceptionUtil.exception;
import static net.hzxzkj.cxlaike.module.cxlaike.enums.ErrorCodeConstants.*;

/**
 * 客户案例 Service 实现类
 *
 * @author 宵征源码
 */
@Service
@Validated
public class ClientCaseServiceImpl implements ClientCaseService {

    @Resource
    private ClientCaseMapper clientCaseMapper;

    @Override
    public Long createClientCase(ClientCaseCreateReqVO createReqVO) {
        // 插入
        ClientCaseDO clientCase = ClientCaseConvert.INSTANCE.convert(createReqVO);
        clientCaseMapper.insert(clientCase);
        // 返回
        return clientCase.getId();
    }

    @Override
    public void updateClientCase(ClientCaseUpdateReqVO updateReqVO) {
        // 校验存在
        validateClientCaseExists(updateReqVO.getId());
        // 更新
        ClientCaseDO updateObj = ClientCaseConvert.INSTANCE.convert(updateReqVO);
        clientCaseMapper.updateById(updateObj);
    }

    @Override
    public void deleteClientCase(Long id) {
        // 校验存在
        validateClientCaseExists(id);
        // 删除
        clientCaseMapper.deleteById(id);
    }

    private void validateClientCaseExists(Long id) {
        if (clientCaseMapper.selectById(id) == null) {
            throw exception(DATA_ERROR);
        }
    }

    @Override
    public ClientCaseDO getClientCase(Long id) {
        return clientCaseMapper.selectById(id);
    }

    @Override
    public List<ClientCaseDO> getClientCaseList() {
        return clientCaseMapper.selectList();
    }

    @Override
    public PageResult<ClientCaseDO> getClientCasePage(ClientCasePageReqVO pageReqVO) {
        return clientCaseMapper.selectPage(pageReqVO);
    }

    @Override
    public List<ClientCaseDO> getClientCaseList(ClientCaseExportReqVO exportReqVO) {
        return clientCaseMapper.selectList(exportReqVO);
    }

}
