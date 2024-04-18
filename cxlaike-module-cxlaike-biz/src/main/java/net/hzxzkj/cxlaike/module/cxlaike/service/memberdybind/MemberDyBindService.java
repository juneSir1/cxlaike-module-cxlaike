package net.hzxzkj.cxlaike.module.cxlaike.service.memberdybind;

import com.diboot.core.service.BaseService;
import net.hzxzkj.cxlaike.framework.common.pojo.PageResult;
import net.hzxzkj.cxlaike.module.cxlaike.controller.admin.memberdybind.vo.MemberDyBindPageReqVO;
import net.hzxzkj.cxlaike.module.cxlaike.controller.admin.memberdybind.vo.MemberDyBindPageRespVO;
import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.matrixaccount.vo.*;
import net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.memberdybind.MemberDyBindDO;
import net.hzxzkj.cxlaike.module.cxlaike.enums.douyin.DyUserTypeEnum;
import net.hzxzkj.cxlaike.module.cxlaike.utils.douyin.vo.AccessTokenRespDTO;
import net.hzxzkj.cxlaike.module.cxlaike.utils.douyin.vo.UserInfoRespDTO;
import net.hzxzkj.cxlaike.module.member.api.user.dto.DouYinInfoUpdateReqDTO;
import net.hzxzkj.cxlaike.module.member.enums.DyAccreditStatusEnum;

import java.util.List;


/**
 * @author jianan.han
 * @date 2023-08-29 11:42
 * @description
 */
public interface MemberDyBindService extends BaseService<MemberDyBindDO> {

    void updateForDouYinInfo(Long id, String dyHomePage, String dyGradeUrl, Integer dyGrade);

    Long updateForDouYinAccredit(DouYinInfoUpdateReqDTO updateReqDTO, String openId);

    MemberDyBindDO getByOpenIdAndMemberId(String openId, Long memberId);

    PageResult<MemberDyBindPageRespVO> getListForAudit(MemberDyBindPageReqVO reqVO);

    Long createDyBindOfForward(Long userId, String openId);

    List<MemberDyBindDO> getListByUserId(Long userId);

    MemberDyBindDO getById(Long id);
    MemberDyBindDO getByIdWithOutTenantId(Long id);

    void update(Long id, String userId, Integer fansCount, Integer giveLikeCount, Integer worksCount, Integer provinceCode, String provinceName, Integer cityCode, String cityName, Integer countyCode
            , String countyName);

    void audit(Long id, Integer auditStatus, String reason, String userId, Integer fansCount, Integer giveLikeCount, Integer worksCount,
               Integer provinceCode, String provinceName, Integer cityCode, String cityName, Integer countyCode
            , String countyName);

    boolean checkDyUser(Long memberId, String openId);
    boolean checkDyUserByOpenId(String openId);

    MemberDyBindDO getDyUserByOpenId(String openId);
    MemberDyBindDO getDyUserByOpenIdWithOutTenantId(String openId,Integer platform);
    /**
     * 获取最新授权数据
     * @author hjn
     * @date 2023/10/20 下午4:51
     * @param memberId
     * @return net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.memberdybind.MemberDyBindDO
     */
    MemberDyBindDO getNewestAccreditById(Long memberId);
    /**
     * 商户分发账号列表
     * @author hjn
     * @date 2023/10/23 下午5:16
     * @return net.hzxzkj.cxlaike.framework.common.pojo.PageResult<net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.matrixaccount.vo.MatrixAccountRespVO>
     */

    void deleteById(Long id);
    /**
     * 修改状态
     *
     */
    void updateStatus(MatrixAccountUpdateStatusReqVO reqVO);
    /**
     * 修改分组
     */
    void updateGroup(MatrixAccountUpdateGroupReqVO reqVO);
    void updateAddress(MatrixAccountUpdateAddressReqVO reqVO);

    List<MemberDyBindDO> getList(Integer platformType);

    Long dealBindAccountByScan(AccessTokenRespDTO tokenRespDTO, UserInfoRespDTO infoRespDTO);
    Long updateByBind(MemberDyBindDO memberDyBindDO, DouYinInfoUpdateReqDTO updateReqDTO);


}
