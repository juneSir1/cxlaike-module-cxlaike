package net.hzxzkj.cxlaike.module.cxlaike.convert.memberdybind;


import net.hzxzkj.cxlaike.module.cxlaike.api.memberdybind.dto.AppUserDyInfoRespVO;
import net.hzxzkj.cxlaike.module.cxlaike.api.memberdybind.dto.MemberDyBindDTO;
import net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.memberdybind.MemberDyBindDO;
import net.hzxzkj.cxlaike.module.member.api.user.dto.DouYinInfoUpdateReqDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 达人扩展 Convert
 *
 * @author 宵征源码
 */
@Mapper
public interface MemberDyBindConvert {

    MemberDyBindConvert INSTANCE = Mappers.getMapper(MemberDyBindConvert.class);

    MemberDyBindDO convert(DouYinInfoUpdateReqDTO reqDTO);

    List<AppUserDyInfoRespVO> convertList(List<MemberDyBindDO> list);

    MemberDyBindDTO convert(MemberDyBindDO bean);
}
