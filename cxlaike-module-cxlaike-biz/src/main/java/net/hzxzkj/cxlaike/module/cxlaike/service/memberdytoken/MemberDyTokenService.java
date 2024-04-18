package net.hzxzkj.cxlaike.module.cxlaike.service.memberdytoken;


import com.diboot.core.service.BaseService;
import net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.memberdytoken.MemberDyTokenDO;
import net.hzxzkj.cxlaike.module.member.api.user.dto.DouYinInfoUpdateReqDTO;
import net.hzxzkj.cxlaike.module.member.api.user.dto.DyTokenReqDTO;
import net.hzxzkj.cxlaike.module.member.api.user.dto.DyTokenRespDTO;
import net.hzxzkj.cxlaike.module.member.enums.DyAccreditStatusEnum;

import java.time.LocalDate;
import java.util.List;

/**
 * 达人抖音token Service 接口
 *
 * @author 宵征源码
 */
public interface MemberDyTokenService extends BaseService<MemberDyTokenDO> {

    void updateOrInsert(DouYinInfoUpdateReqDTO updateReqDTO, Long id);

    List<DyTokenRespDTO> getRefreshTokenUsers(LocalDate refreshTime,Integer platformType);

    List<DyTokenRespDTO> getRefreshAccessTokenUsers(LocalDate refreshTime,Integer platformType);

    void updateDyToken(DyTokenReqDTO reqDTO);

    MemberDyTokenDO getById(Long id);
    MemberDyTokenDO getByIdWithOutTenantId(Long id);

    List<DyTokenRespDTO> getSmsRemindUsers(LocalDate refreshTime);

    void updateDyAccreditStatus(Long id, DyAccreditStatusEnum statusEnum);

}
