package net.hzxzkj.cxlaike.module.cxlaike.convert.dypersonalletteruser;

import java.util.*;

import net.hzxzkj.cxlaike.framework.common.pojo.PageResult;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.dypersonalletteruser.vo.*;
import net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.dypersonalletteruser.DyPersonalLetterUserDO;

/**
 * 抖音私信用户管理 Convert
 *
 * @author 宵征源码
 */
@Mapper
public interface DyPersonalLetterUserConvert {

    DyPersonalLetterUserConvert INSTANCE = Mappers.getMapper(DyPersonalLetterUserConvert.class);

    DyPersonalLetterUserDO convert(DyPersonalLetterUserCreateReqVO bean);

    DyPersonalLetterUserDO convert(DyPersonalLetterUserUpdateReqVO bean);

    DyPersonalLetterUserRespVO convert(DyPersonalLetterUserDO bean);

    List<DyPersonalLetterUserRespVO> convertList(List<DyPersonalLetterUserDO> list);

    PageResult<DyPersonalLetterUserRespVO> convertPage(PageResult<DyPersonalLetterUserDO> page);

    List<DyPersonalLetterUserExcelVO> convertList02(List<DyPersonalLetterUserDO> list);



}
