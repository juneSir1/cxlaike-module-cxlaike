package net.hzxzkj.cxlaike.module.cxlaike.convert.earnings;

import net.hzxzkj.cxlaike.module.cxlaike.controller.app.teamearnings.vo.EarningsRespVO;
import net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.earnings.EarningsDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 代理商每日汇总 Convert
 *
 * @author 宵征源码
 */
@Mapper
public interface EarningsConvert {

    EarningsConvert INSTANCE = Mappers.getMapper(EarningsConvert.class);

    List<EarningsRespVO> convertList(List<EarningsDO> list);

}
