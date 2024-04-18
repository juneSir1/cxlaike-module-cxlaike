package net.hzxzkj.cxlaike.module.cxlaike.convert.systakelevelconfig;

import java.util.*;

import net.hzxzkj.cxlaike.framework.common.pojo.PageResult;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import net.hzxzkj.cxlaike.module.cxlaike.controller.admin.systakelevelconfig.vo.*;
import net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.systakelevelconfig.SysTakeLevelConfigDO;

/**
 * 来客系统等级参数配置 Convert
 *
 * @author 宵征源码
 */
@Mapper
public interface SysTakeLevelConfigConvert {

  SysTakeLevelConfigConvert INSTANCE = Mappers.getMapper(SysTakeLevelConfigConvert.class);

  SysTakeLevelConfigDO convert(SysTakeLevelConfigCreateReqVO bean);

  SysTakeLevelConfigDO convert(SysTakeLevelConfigUpdateReqVO bean);

  SysTakeLevelConfigRespVO convert(SysTakeLevelConfigDO bean);

  List<SysTakeLevelConfigRespVO> convertList(List<SysTakeLevelConfigDO> list);

  PageResult<SysTakeLevelConfigRespVO> convertPage(PageResult<SysTakeLevelConfigDO> page);

  List<SysTakeLevelConfigExcelVO> convertList02(List<SysTakeLevelConfigDO> list);

  List<net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.systakelevelconfig.vo.SysTakeLevelConfigRespVO> convertList03(
      List<SysTakeLevelConfigDO> list);

  List<SysTakeLevelConfigDO> convertList1(List<SysTakeLevelConfigUpdateReqVO> list);
}
