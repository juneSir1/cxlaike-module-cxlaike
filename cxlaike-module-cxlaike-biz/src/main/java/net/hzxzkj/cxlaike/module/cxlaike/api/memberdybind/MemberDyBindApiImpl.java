package net.hzxzkj.cxlaike.module.cxlaike.api.memberdybind;

import net.hzxzkj.cxlaike.module.cxlaike.api.memberdybind.dto.AppUserDyInfoRespVO;
import net.hzxzkj.cxlaike.module.cxlaike.api.memberdybind.dto.MemberDyBindDTO;
import net.hzxzkj.cxlaike.module.cxlaike.convert.memberdybind.MemberDyBindConvert;
import net.hzxzkj.cxlaike.module.cxlaike.service.memberdybind.MemberDyBindService;
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
public class MemberDyBindApiImpl implements MemberDyBindApi {

    @Resource
    private MemberDyBindService memberDyBindService;


    @Override
    public List<AppUserDyInfoRespVO> getListByUserId(Long id) {
        return MemberDyBindConvert.INSTANCE.convertList(memberDyBindService.getListByUserId(id));
    }

    @Override
    public MemberDyBindDTO getByOpenIdAndMemberId(String openId, Long id) {
        return MemberDyBindConvert.INSTANCE.convert(memberDyBindService.getByOpenIdAndMemberId(openId,id));
    }
}
