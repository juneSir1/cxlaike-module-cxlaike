package net.hzxzkj.cxlaike.module.cxlaike.convert.settlementlog;

import java.util.*;

import net.hzxzkj.cxlaike.framework.common.pojo.PageResult;

import net.hzxzkj.cxlaike.module.cxlaike.controller.app.settlementlog.vo.SettlementLogCreateReqVO;
import net.hzxzkj.cxlaike.module.cxlaike.controller.app.settlementlog.vo.SettlementLogExcelVO;
import net.hzxzkj.cxlaike.module.cxlaike.controller.app.settlementlog.vo.SettlementLogRespVO;
import net.hzxzkj.cxlaike.module.cxlaike.controller.app.settlementlog.vo.SettlementLogUpdateReqVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.settlementlog.SettlementLogDO;

/**
 * 结算流水 Convert
 *
 * @author 宵征源码
 */
@Mapper
public interface SettlementLogConvert {

    SettlementLogConvert INSTANCE = Mappers.getMapper(SettlementLogConvert.class);

    SettlementLogDO convert(SettlementLogCreateReqVO bean);

    SettlementLogDO convert(SettlementLogUpdateReqVO bean);

    SettlementLogRespVO convert(SettlementLogDO bean);

    List<SettlementLogRespVO> convertList(List<SettlementLogDO> list);

    PageResult<SettlementLogRespVO> convertPage(PageResult<SettlementLogDO> page);

    List<SettlementLogExcelVO> convertList02(List<SettlementLogDO> list);

}
