package net.hzxzkj.cxlaike.module.cxlaike.convert.metahumanconfig;

import java.util.*;

import net.hzxzkj.cxlaike.framework.common.pojo.PageResult;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import net.hzxzkj.cxlaike.module.cxlaike.controller.admin.metahumanconfig.vo.*;
import net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.metahumanconfig.MetaHumanConfigDO;

/**
 * 来客数字人配置 Convert
 *
 * @author 宵征源码
 */
@Mapper
public interface MetaHumanConfigConvert {

    MetaHumanConfigConvert INSTANCE = Mappers.getMapper(MetaHumanConfigConvert.class);

    MetaHumanConfigDO convert(MetaHumanConfigCreateReqVO bean);

    MetaHumanConfigDO convert(MetaHumanConfigUpdateReqVO bean);

    MetaHumanConfigRespVO convert(MetaHumanConfigDO bean);

    List<MetaHumanConfigRespVO> convertList(List<MetaHumanConfigDO> list);

    PageResult<MetaHumanConfigRespVO> convertPage(PageResult<MetaHumanConfigDO> page);

    List<MetaHumanConfigExcelVO> convertList02(List<MetaHumanConfigDO> list);

}
