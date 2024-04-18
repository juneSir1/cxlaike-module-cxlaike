package net.hzxzkj.cxlaike.module.cxlaike.convert.aivideoconfig;

import java.util.*;

import net.hzxzkj.cxlaike.framework.common.pojo.PageResult;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import net.hzxzkj.cxlaike.module.cxlaike.controller.admin.aivideoconfig.vo.*;
import net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.aivideoconfig.AiVideoConfigDO;

/**
 * ai视频配置 Convert
 *
 * @author 宵征源码
 */
@Mapper
public interface AiVideoConfigConvert {

    AiVideoConfigConvert INSTANCE = Mappers.getMapper(AiVideoConfigConvert.class);

    AiVideoConfigDO convert(AiVideoConfigCreateReqVO bean);

    AiVideoConfigDO convert(AiVideoConfigUpdateReqVO bean);

    AiVideoConfigRespVO convert(AiVideoConfigDO bean);

    List<AiVideoConfigRespVO> convertList(List<AiVideoConfigDO> list);

    PageResult<AiVideoConfigRespVO> convertPage(PageResult<AiVideoConfigDO> page);

    List<AiVideoConfigExcelVO> convertList02(List<AiVideoConfigDO> list);

}
