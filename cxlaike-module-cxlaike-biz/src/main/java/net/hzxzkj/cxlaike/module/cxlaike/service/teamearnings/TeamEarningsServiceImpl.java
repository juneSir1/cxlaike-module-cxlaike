package net.hzxzkj.cxlaike.module.cxlaike.service.teamearnings;

import net.hzxzkj.cxlaike.framework.common.enums.UserTypeEnum;
import net.hzxzkj.cxlaike.framework.common.pojo.PageResult;
import net.hzxzkj.cxlaike.framework.mybatis.core.service.BaseCrudServiceImpl;
import net.hzxzkj.cxlaike.module.cxlaike.controller.app.teamearnings.vo.TeamEarningsPageReqVO;
import net.hzxzkj.cxlaike.module.cxlaike.controller.app.teamearnings.vo.TeamEarningsRespVO;
import net.hzxzkj.cxlaike.module.cxlaike.convert.teamearnings.TeamEarningsConvert;
import net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.teamearnings.TeamEarningsDO;
import net.hzxzkj.cxlaike.module.cxlaike.dal.mysql.teamearnings.TeamEarningsMapper;
import net.hzxzkj.cxlaike.module.member.api.user.MemberUserApi;
import net.hzxzkj.cxlaike.module.member.enums.UserInviteLevelEnum;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;
import java.util.List;

/**
 * 代理商团队收益 Service 实现类
 *
 * @author 宵征源码
 */
@Service
@Validated
public class TeamEarningsServiceImpl extends BaseCrudServiceImpl<TeamEarningsMapper,TeamEarningsDO> implements TeamEarningsService {

    @Resource
    private TeamEarningsMapper teamEarningsMapper;

    @Resource
    private MemberUserApi memberUserApi;


    @Override
    public TeamEarningsDO getTeamEarningsByUserId(Long userId) {
        return teamEarningsMapper.selectOne(TeamEarningsDO::getUserId,userId);
    }

    @Override
    public TeamEarningsRespVO getTeamEarningsDTOByUserId(Long userId) {
        TeamEarningsDO teamEarningsDO = this.getTeamEarningsByUserId(userId);
        if(teamEarningsDO==null){
            return null;
        }
        TeamEarningsRespVO teamEarningsRespVO = TeamEarningsConvert.INSTANCE.convert(teamEarningsDO);
        teamEarningsRespVO.setOneCount(memberUserApi.countByParentUserId(userId, UserTypeEnum.MEMBER));
        teamEarningsRespVO.setTwoCount(memberUserApi.countByTopUserId(userId, UserTypeEnum.MEMBER));
        return teamEarningsRespVO;
    }

    @Override
    public List<TeamEarningsDO> getTeamEarningsByUserIds(List<Long> userIds) {
        return teamEarningsMapper.selectList(TeamEarningsDO::getUserId,userIds);
    }

    @Override
    public PageResult<TeamEarningsDO> getTeamEarningsByType(TeamEarningsPageReqVO pageVO) {
        List<Long> userids =null;
        if(UserInviteLevelEnum.ONE.getLevel().equals(pageVO.getType())){
            userids=memberUserApi.getListByParentUserId(pageVO.getUserId(), UserTypeEnum.MERCHANT);
        }else {
            userids=memberUserApi.getListByTopUserId(pageVO.getUserId(), UserTypeEnum.MERCHANT);
        }

        if(userids==null){
            return null;
        }
        return null;
    }
}
