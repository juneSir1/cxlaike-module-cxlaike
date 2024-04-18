package net.hzxzkj.cxlaike.module.cxlaike.convert.teamearnings;

import net.hzxzkj.cxlaike.framework.common.pojo.PageResult;
import net.hzxzkj.cxlaike.module.cxlaike.controller.app.teamearnings.vo.TeamEarningsRespVO;
import net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.teamearnings.TeamEarningsDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * 代理商团队收益 Convert
 *
 * @author 宵征源码
 */
@Mapper
public interface TeamEarningsConvert {

    TeamEarningsConvert INSTANCE = Mappers.getMapper(TeamEarningsConvert.class);


    TeamEarningsRespVO convert(TeamEarningsDO bean);


    PageResult<TeamEarningsRespVO> convertPage(PageResult<TeamEarningsDO> pageResult);

}
