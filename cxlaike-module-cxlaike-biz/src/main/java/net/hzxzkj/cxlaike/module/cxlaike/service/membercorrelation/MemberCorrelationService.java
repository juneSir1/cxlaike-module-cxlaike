package net.hzxzkj.cxlaike.module.cxlaike.service.membercorrelation;

import net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.membercorrelation.MemberCorrelationDO;

import java.util.List;

/**
 * 商户与达人视频账号(抖音账号)的任务关联 Service 接口
 *
 * @author 宵征源码
 */
public interface MemberCorrelationService {

    void createCorrelation(Long merchantId,Long bindId);

    List<MemberCorrelationDO> getList();
}
