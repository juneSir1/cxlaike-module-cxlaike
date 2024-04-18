package net.hzxzkj.cxlaike.module.cxlaike.service.dygroupcorrelation;

import net.hzxzkj.cxlaike.framework.mybatis.core.service.BaseCrudServiceImpl;
import net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.accountgroupcorrelation.AccountGroupCorrelationDO;
import net.hzxzkj.cxlaike.module.cxlaike.dal.mysql.accountgroupcorrelation.AccountGroupCorrelationMapper;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 商户抖音分组关联 Service 实现类
 *
 * @author 宵征源码
 */
@Service
@Validated
public class DyGroupCorrelationServiceImpl extends BaseCrudServiceImpl<AccountGroupCorrelationMapper, AccountGroupCorrelationDO> implements DyGroupCorrelationService {

    @Resource
    private AccountGroupCorrelationMapper dyGroupCorrelationMapper;

    @Override
    public void createCorrelation(List<Long> groupIds, Long bindId) {
        List<AccountGroupCorrelationDO> correlationDOS = new ArrayList<>();
        for(Long groupId : groupIds){
            AccountGroupCorrelationDO correlationDO = new AccountGroupCorrelationDO();
            correlationDO.setGroupId(groupId);
            correlationDO.setDyBindId(bindId);
            correlationDOS.add(correlationDO);
        }
        dyGroupCorrelationMapper.insertBatch(correlationDOS);
    }

    @Override
    public void deleteByBindId(Long id) {
        dyGroupCorrelationMapper.delete(AccountGroupCorrelationDO::getDyBindId,id);
    }
}
