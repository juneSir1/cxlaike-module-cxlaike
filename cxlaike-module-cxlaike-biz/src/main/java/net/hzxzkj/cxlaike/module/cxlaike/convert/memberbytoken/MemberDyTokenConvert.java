package net.hzxzkj.cxlaike.module.cxlaike.convert.memberbytoken;


import net.hzxzkj.cxlaike.module.cxlaike.api.memberbytoken.dto.MemberDyTokenRespDTO;
import net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.memberdytoken.MemberDyTokenDO;
import net.hzxzkj.cxlaike.module.member.api.user.dto.DouYinInfoUpdateReqDTO;
import net.hzxzkj.cxlaike.module.member.api.user.dto.DyTokenReqDTO;
import net.hzxzkj.cxlaike.module.member.api.user.dto.DyTokenRespDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 达人抖音token Convert
 *
 * @author 宵征源码
 */
@Mapper
public interface MemberDyTokenConvert {

    MemberDyTokenConvert INSTANCE = Mappers.getMapper(MemberDyTokenConvert.class);


    MemberDyTokenDO convert(DouYinInfoUpdateReqDTO reqDTO);

    List<DyTokenRespDTO> convertList(List<MemberDyTokenDO> doList);

    MemberDyTokenDO convert(DyTokenReqDTO reqDTO);

    MemberDyTokenRespDTO convert(MemberDyTokenDO bean);
}
