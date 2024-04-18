package net.hzxzkj.cxlaike.module.cxlaike.convert.dypersonalletter;

import java.util.*;

import net.hzxzkj.cxlaike.framework.common.pojo.PageResult;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.dypersonalletter.vo.*;
import net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.dypersonalletter.DyPersonalLetterDO;

/**
 * 抖音私信管理 Convert
 *
 * @author 宵征源码
 */
@Mapper
public interface DyPersonalLetterConvert {

    DyPersonalLetterConvert INSTANCE = Mappers.getMapper(DyPersonalLetterConvert.class);

    DyPersonalLetterDO convert(DyPersonalLetterCreateReqVO bean);

    DyPersonalLetterDO convert(DyPersonalLetterUpdateReqVO bean);

    DyPersonalLetterRespVO convert(DyPersonalLetterDO bean);

    List<DyPersonalLetterRespVO> convertList(List<DyPersonalLetterDO> list);

    PageResult<DyPersonalLetterRespVO> convertPage(PageResult<DyPersonalLetterDO> page);

    List<DyPersonalLetterExcelVO> convertList02(List<DyPersonalLetterDO> list);

}
