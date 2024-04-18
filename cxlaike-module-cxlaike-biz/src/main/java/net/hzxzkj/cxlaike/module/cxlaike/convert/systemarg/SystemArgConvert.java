package net.hzxzkj.cxlaike.module.cxlaike.convert.systemarg;

import java.util.*;

import net.hzxzkj.cxlaike.framework.common.pojo.PageResult;

import net.hzxzkj.cxlaike.module.cxlaike.controller.app.config.vo.AppSystemArgRespVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import net.hzxzkj.cxlaike.module.cxlaike.controller.admin.systemarg.vo.*;
import net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.systemarg.SystemArgDO;

/**
 * 来客系统参数 Convert
 *
 * @author 宵征源码
 */
@Mapper
public interface SystemArgConvert {

    SystemArgConvert INSTANCE = Mappers.getMapper(SystemArgConvert.class);

    SystemArgDO convert(SystemArgCreateReqVO bean);

    SystemArgDO convert(SystemArgUpdateReqVO bean);

    SystemArgRespVO convert(SystemArgDO bean);

    List<SystemArgRespVO> convertList(List<SystemArgDO> list);

    PageResult<SystemArgRespVO> convertPage(PageResult<SystemArgDO> page);

    List<SystemArgExcelVO> convertList02(List<SystemArgDO> list);

    AppSystemArgRespVO convert1(SystemArgDO systemArg);

    //SystemArgRespVO convert1(SystemArgDO systemArg);
}
