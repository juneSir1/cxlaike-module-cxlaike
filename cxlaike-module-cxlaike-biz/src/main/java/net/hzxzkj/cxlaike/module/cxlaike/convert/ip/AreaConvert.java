package net.hzxzkj.cxlaike.module.cxlaike.convert.ip;

import net.hzxzkj.cxlaike.framework.ip.core.Area;
import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.ip.vo.AppAreaNodeRespVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface AreaConvert {

    AreaConvert INSTANCE = Mappers.getMapper(AreaConvert.class);

    List<AppAreaNodeRespVO> convertList(List<Area> list);

}
