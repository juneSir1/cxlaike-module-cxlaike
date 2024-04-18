package net.hzxzkj.cxlaike.module.cxlaike.convert.profession;

import java.util.*;


import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.profession.vo.ProfessionRespVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.profession.ProfessionDO;

/**
 * 行业 Convert
 *
 * @author 宵征源码
 */
@Mapper
public interface ProfessionConvert {

    ProfessionConvert INSTANCE = Mappers.getMapper(ProfessionConvert.class);

    ProfessionRespVO convert(ProfessionDO bean);

    List<ProfessionRespVO> convertList(List<ProfessionDO> list);

}
