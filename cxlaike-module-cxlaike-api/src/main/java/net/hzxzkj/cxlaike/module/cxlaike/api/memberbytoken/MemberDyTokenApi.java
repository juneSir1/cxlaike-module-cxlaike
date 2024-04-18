package net.hzxzkj.cxlaike.module.cxlaike.api.memberbytoken;



import net.hzxzkj.cxlaike.module.cxlaike.api.memberbytoken.dto.MemberDyTokenRespDTO;

/**
 * 会员抖音审核 API 接口
 *
 * @author 宵征源码
 */
public interface MemberDyTokenApi {

    MemberDyTokenRespDTO getById(Long id);

}
