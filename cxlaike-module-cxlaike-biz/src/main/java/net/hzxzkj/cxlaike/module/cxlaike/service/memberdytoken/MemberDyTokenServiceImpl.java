package net.hzxzkj.cxlaike.module.cxlaike.service.memberdytoken;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.diboot.core.service.impl.BaseServiceImpl;
import net.hzxzkj.cxlaike.framework.tenant.core.aop.TenantIgnore;
import net.hzxzkj.cxlaike.module.cxlaike.convert.memberbytoken.MemberDyTokenConvert;
import net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.memberdybind.MemberDyBindDO;
import net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.memberdytoken.MemberDyTokenDO;
import net.hzxzkj.cxlaike.module.cxlaike.dal.mysql.memberdybind.MemberDyBindMapper;
import net.hzxzkj.cxlaike.module.cxlaike.dal.mysql.memberdytoken.MemberDyTokenMapper;
import net.hzxzkj.cxlaike.module.member.api.user.dto.DouYinInfoUpdateReqDTO;
import net.hzxzkj.cxlaike.module.member.api.user.dto.DyTokenReqDTO;
import net.hzxzkj.cxlaike.module.member.api.user.dto.DyTokenRespDTO;
import net.hzxzkj.cxlaike.module.member.enums.DyAccreditStatusEnum;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;


/**
 * 达人抖音token Service 实现类
 *
 * @author 宵征源码
 */
@Service
@Validated
public class MemberDyTokenServiceImpl extends BaseServiceImpl<MemberDyTokenMapper, MemberDyTokenDO> implements MemberDyTokenService {

    @Resource
    private MemberDyTokenMapper dyTokenMapper;
    @Resource
    private MemberDyBindMapper memberDyBindMapper;


    @Override
    @TenantIgnore
    public void updateOrInsert(DouYinInfoUpdateReqDTO updateReqDTO,Long id) {
        MemberDyTokenDO memberDyTokenDO = dyTokenMapper.selectById(id);
        if(memberDyTokenDO != null){
            memberDyTokenDO = MemberDyTokenConvert.INSTANCE.convert(updateReqDTO);
            memberDyTokenDO.setId(id);
            memberDyTokenDO.setTenantId(updateReqDTO.getUserId());
            dyTokenMapper.updateById(memberDyTokenDO);
            return;
        }
        MemberDyTokenDO insertDO = MemberDyTokenConvert.INSTANCE.convert(updateReqDTO);
        insertDO.setId(id);
        insertDO.setTenantId(updateReqDTO.getUserId());
        dyTokenMapper.insert(insertDO);
    }

    @Override
    public List<DyTokenRespDTO> getRefreshTokenUsers(LocalDate refreshTime,Integer platformType) {
        List<MemberDyTokenDO> memberExtendDOS = dyTokenMapper.selectList(new LambdaUpdateWrapper<MemberDyTokenDO>()
                .eq(MemberDyTokenDO::getDyAccreditStatus, 1) //授权状态正常的
                .eq(MemberDyTokenDO::getPlatformType, platformType)
                .between(MemberDyTokenDO::getDyRefreshExpiresTime, refreshTime.atStartOfDay(), LocalDateTime.of(refreshTime, LocalTime.MAX))
        );
        List<DyTokenRespDTO> dyTokenRespDTOS = MemberDyTokenConvert.INSTANCE.convertList(memberExtendDOS);
        dyTokenRespDTOS.forEach(respDTO -> {
            MemberDyBindDO memberDyBindDO = memberDyBindMapper.selectById(respDTO.getId());
            respDTO.setMemberId(memberDyBindDO.getMemberId());
        });
        return dyTokenRespDTOS;
    }

    @Override
    public List<DyTokenRespDTO> getRefreshAccessTokenUsers(LocalDate refreshTime,Integer platformType) {
        List<MemberDyTokenDO> memberExtendDOS = dyTokenMapper.selectList(new LambdaUpdateWrapper<MemberDyTokenDO>()
                .eq(MemberDyTokenDO::getDyAccreditStatus, 1) //授权状态正常的
                .eq(MemberDyTokenDO::getPlatformType,platformType)
                .between(MemberDyTokenDO::getDyExpiresTime, refreshTime.atStartOfDay(),LocalDateTime.of(refreshTime, LocalTime.MAX))
        );
        List<DyTokenRespDTO> dyTokenRespDTOS = MemberDyTokenConvert.INSTANCE.convertList(memberExtendDOS);
        dyTokenRespDTOS.forEach(respDTO -> {
            MemberDyBindDO memberDyBindDO = memberDyBindMapper.selectById(respDTO.getId());
            if(memberDyBindDO!=null) {
                respDTO.setMemberId(memberDyBindDO.getMemberId());
            }
        });
        return dyTokenRespDTOS;
    }

    @Override
    public void updateDyToken(DyTokenReqDTO reqDTO) {
        MemberDyTokenDO extendDO = MemberDyTokenConvert.INSTANCE.convert(reqDTO);
        dyTokenMapper.updateById(extendDO);
    }

    @Override
    public MemberDyTokenDO getById(Long id) {
        return dyTokenMapper.selectById(id);
    }

    @Override
    @TenantIgnore
    public MemberDyTokenDO getByIdWithOutTenantId(Long id) {
        return dyTokenMapper.selectById(id);
    }

    @Override
    public List<DyTokenRespDTO> getSmsRemindUsers(LocalDate refreshTime) {
        List<MemberDyTokenDO> memberExtendDOS = dyTokenMapper.selectList(new LambdaUpdateWrapper<MemberDyTokenDO>()
                .eq(MemberDyTokenDO::getDyAccreditStatus, 1) //授权状态正常的
                .between(MemberDyTokenDO::getSmsRemindTime, refreshTime.atStartOfDay(),LocalDateTime.of(refreshTime, LocalTime.MAX))
        );
        List<DyTokenRespDTO> dyTokenRespDTOS = MemberDyTokenConvert.INSTANCE.convertList(memberExtendDOS);
        dyTokenRespDTOS.forEach(respDTO -> {
            MemberDyBindDO memberDyBindDO = memberDyBindMapper.selectById(respDTO.getId());
            respDTO.setMemberId(memberDyBindDO.getMemberId());
            respDTO.setDyUserId(memberDyBindDO.getDyUserId());
            respDTO.setDyNickname(memberDyBindDO.getDyNickname());
        });
        return dyTokenRespDTOS;
    }
    @Override
    public void updateDyAccreditStatus(Long id, DyAccreditStatusEnum statusEnum) {
        MemberDyTokenDO bindDO = new MemberDyTokenDO();
        bindDO.setDyAccreditStatus(statusEnum.getCode());
        dyTokenMapper.update(bindDO,new LambdaQueryWrapper<MemberDyTokenDO>()
                .eq(MemberDyTokenDO::getId,id));
    }
}
