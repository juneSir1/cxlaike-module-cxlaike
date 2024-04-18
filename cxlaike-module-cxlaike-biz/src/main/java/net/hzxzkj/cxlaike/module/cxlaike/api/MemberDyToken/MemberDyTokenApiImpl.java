package net.hzxzkj.cxlaike.module.cxlaike.api.MemberDyToken;

import net.hzxzkj.cxlaike.module.cxlaike.api.memberbytoken.MemberDyTokenApi;
import net.hzxzkj.cxlaike.module.cxlaike.api.memberbytoken.dto.MemberDyTokenRespDTO;
import net.hzxzkj.cxlaike.module.cxlaike.api.memberdybind.MemberDyBindApi;
import net.hzxzkj.cxlaike.module.cxlaike.api.memberdybind.dto.AppUserDyInfoRespVO;
import net.hzxzkj.cxlaike.module.cxlaike.convert.memberbytoken.MemberDyTokenConvert;
import net.hzxzkj.cxlaike.module.cxlaike.convert.memberdybind.MemberDyBindConvert;
import net.hzxzkj.cxlaike.module.cxlaike.service.memberdybind.MemberDyBindService;
import net.hzxzkj.cxlaike.module.cxlaike.service.memberdytoken.MemberDyTokenService;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author jianan.han
 * @date 2023/9/11 下午5:23
 * @description
 */
@Service
@Validated
public class MemberDyTokenApiImpl implements MemberDyTokenApi {

    @Resource
    private MemberDyTokenService memberDyTokenService;

    @Override
    public MemberDyTokenRespDTO getById(Long id) {
        return MemberDyTokenConvert.INSTANCE.convert(memberDyTokenService.getById(id));
    }
}
