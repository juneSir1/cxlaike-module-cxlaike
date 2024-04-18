package net.hzxzkj.cxlaike.module.cxlaike.service.teamearnings;

import net.hzxzkj.cxlaike.framework.common.pojo.PageResult;
import net.hzxzkj.cxlaike.framework.mybatis.core.service.BaseCrudService;
import net.hzxzkj.cxlaike.module.cxlaike.controller.app.teamearnings.vo.TeamEarningsPageReqVO;
import net.hzxzkj.cxlaike.module.cxlaike.controller.app.teamearnings.vo.TeamEarningsRespVO;
import net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.teamearnings.TeamEarningsDO;

import java.util.List;

/**
 * 代理商团队收益 Service 接口
 *
 * @author 宵征源码
 */
public interface TeamEarningsService extends BaseCrudService<TeamEarningsDO> {
    /**
     * 根据userId查询代理商团队收益
     */
    TeamEarningsDO getTeamEarningsByUserId(Long userId);


    /**
     * 根据userId查询代理商团队收益
     */
    TeamEarningsRespVO getTeamEarningsDTOByUserId(Long userId);

    /**
     * 根据userId集合查询代理商团队收益
     */
    List<TeamEarningsDO> getTeamEarningsByUserIds(List<Long> userIds);


    /**
     * 根据下属类型查询代理商团队收益
     */
    PageResult<TeamEarningsDO> getTeamEarningsByType(TeamEarningsPageReqVO pageVO);

}
