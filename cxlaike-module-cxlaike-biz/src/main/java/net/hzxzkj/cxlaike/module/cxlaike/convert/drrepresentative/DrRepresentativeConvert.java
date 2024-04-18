package net.hzxzkj.cxlaike.module.cxlaike.convert.drrepresentative;

import java.util.*;

import net.hzxzkj.cxlaike.framework.common.pojo.PageResult;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.drrepresentative.vo.*;
import net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.drrepresentative.DrRepresentativeDO;

/**
 * 达人探店代 Convert
 *
 * @author 宵征源码
 */
@Mapper
public interface DrRepresentativeConvert {

    DrRepresentativeConvert INSTANCE = Mappers.getMapper(DrRepresentativeConvert.class);

    DrRepresentativeDO convert(DrRepresentativeCreateReqVO bean);

    DrRepresentativeDO convert(DrRepresentativeUpdateReqVO bean);

    DrRepresentativeRespVO convert(DrRepresentativeDO bean);

    List<DrRepresentativeRespVO> convertList(List<DrRepresentativeDO> list);

    PageResult<DrRepresentativeRespVO> convertPage(PageResult<DrRepresentativeDO> page);

    List<DrRepresentativeExcelVO> convertList02(List<DrRepresentativeDO> list);

}
