package net.hzxzkj.cxlaike.module.cxlaike.service.dygroupcorrelation;


import net.hzxzkj.cxlaike.framework.mybatis.core.service.BaseCrudService;
import net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.accountgroupcorrelation.AccountGroupCorrelationDO;

import java.util.List;

/**
 * 商户抖音分组关联 Service 接口
 *
 * @author 宵征源码
 */
public interface DyGroupCorrelationService extends BaseCrudService<AccountGroupCorrelationDO> {

    void createCorrelation(List<Long> groupIds,Long bindId);

    void deleteByBindId(Long id);

}
