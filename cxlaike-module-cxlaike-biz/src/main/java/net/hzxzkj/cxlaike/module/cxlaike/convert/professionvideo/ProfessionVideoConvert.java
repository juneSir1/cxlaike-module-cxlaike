package net.hzxzkj.cxlaike.module.cxlaike.convert.professionvideo;

import java.util.*;

import net.hzxzkj.cxlaike.framework.common.pojo.PageResult;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.professionvideo.vo.*;
import net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.professionvideo.ProfessionVideoDO;

/**
 * 精选行业视频 Convert
 *
 * @author 宵征源码
 */
@Mapper
public interface ProfessionVideoConvert {

    ProfessionVideoConvert INSTANCE = Mappers.getMapper(ProfessionVideoConvert.class);

    ProfessionVideoDO convert(ProfessionVideoCreateReqVO bean);

    ProfessionVideoDO convert(ProfessionVideoUpdateReqVO bean);

    ProfessionVideoRespVO convert(ProfessionVideoDO bean);

    List<ProfessionVideoRespVO> convertList(List<ProfessionVideoDO> list);

    PageResult<ProfessionVideoRespVO> convertPage(PageResult<ProfessionVideoDO> page);

    List<ProfessionVideoExcelVO> convertList02(List<ProfessionVideoDO> list);

}
