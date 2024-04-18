package net.hzxzkj.cxlaike.module.cxlaike.convert.region;



import net.hzxzkj.cxlaike.module.cxlaike.controller.app.region.vo.AppRegionRespVO;
import net.hzxzkj.cxlaike.module.cxlaike.controller.app.region.vo.AppRegionThreeRespVO;
import net.hzxzkj.cxlaike.module.cxlaike.controller.app.region.vo.AppRegionTwoRespVO;
import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.region.vo.RegionRespVO;
import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.region.vo.RegionThreeRespVO;
import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.region.vo.RegionTwoRespVO;
import net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.region.RegionDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;


/**
 * 地区 Convert
 *
 * @author 宵征源码
 */
@Mapper
public interface RegionConvert {

    RegionConvert INSTANCE = Mappers.getMapper(RegionConvert.class);

    List<RegionRespVO> convertList(List<RegionDO> list);

    List<RegionTwoRespVO> convertList02(List<RegionDO> list);

    List<RegionThreeRespVO> convertList03(List<RegionDO> list);

    List<AppRegionRespVO> converApptList(List<RegionDO> list);

    List<AppRegionTwoRespVO> convertAppList02(List<RegionDO> list);

    List<AppRegionThreeRespVO> convertAppList03(List<RegionDO> list);





}
