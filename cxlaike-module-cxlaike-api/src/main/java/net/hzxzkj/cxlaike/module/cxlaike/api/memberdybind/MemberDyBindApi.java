package net.hzxzkj.cxlaike.module.cxlaike.api.memberdybind;



import net.hzxzkj.cxlaike.module.cxlaike.api.memberdybind.dto.AppUserDyInfoRespVO;
import net.hzxzkj.cxlaike.module.cxlaike.api.memberdybind.dto.MemberDyBindDTO;

import java.util.List;

/**
 * 会员抖音审核 API 接口
 *
 * @author 宵征源码
 */
public interface MemberDyBindApi {

    List<AppUserDyInfoRespVO> getListByUserId(Long id);

    MemberDyBindDTO getByOpenIdAndMemberId(String openId, Long id);

}
