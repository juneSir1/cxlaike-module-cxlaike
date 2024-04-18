package net.hzxzkj.cxlaike.module.cxlaike.service.agentstatistic;

import net.hzxzkj.cxlaike.module.cxlaike.controller.app.agentstatistic.vo.ExploringStoresEarningsRespVO;

/**
 * @author jianan.han
 * @date 2023/11/3 下午3:44
 * @description
 */
public interface AgentStatisticService {

    ExploringStoresEarningsRespVO getExploringStoresEarnings(Long userId);
}
