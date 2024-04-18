package net.hzxzkj.cxlaike.module.cxlaike.service.invite;



/**
 * @author jianan.han
 * @date 2023/9/22 下午3:10
 * @description
 */
public interface InviteService {

    byte[] getPlacard(Integer type, Long userId) throws Exception;

    byte[] getMemberPlacard(Integer type, Long userId,Long taskId);

    String generateUrlLink(Long taskId, Long userId);

    String getSjInviteLink(Long userId);
}
