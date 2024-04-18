package net.hzxzkj.cxlaike.module.cxlaike.convert.clientcase;

import java.util.*;

import net.hzxzkj.cxlaike.framework.common.pojo.PageResult;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.clientcase.vo.*;
import net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.clientcase.ClientCaseDO;

/**
 * 客户案例 Convert
 *
 * @author 宵征源码
 */
@Mapper
public interface ClientCaseConvert {

    ClientCaseConvert INSTANCE = Mappers.getMapper(ClientCaseConvert.class);

    ClientCaseDO convert(ClientCaseCreateReqVO bean);

    ClientCaseDO convert(ClientCaseUpdateReqVO bean);

    ClientCaseRespVO convert(ClientCaseDO bean);

    List<ClientCaseRespVO> convertList(List<ClientCaseDO> list);

    PageResult<ClientCaseRespVO> convertPage(PageResult<ClientCaseDO> page);

    List<ClientCaseExcelVO> convertList02(List<ClientCaseDO> list);

}
