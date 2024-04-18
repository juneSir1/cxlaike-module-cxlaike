package net.hzxzkj.cxlaike.module.cxlaike.service.memberdybind;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.diboot.core.service.impl.BaseServiceImpl;
import lombok.extern.slf4j.Slf4j;
import net.hzxzkj.cxlaike.framework.common.enums.CommonStatusEnum;
import net.hzxzkj.cxlaike.framework.common.exception.ErrorCode;
import net.hzxzkj.cxlaike.framework.common.pojo.PageResult;
import net.hzxzkj.cxlaike.framework.tenant.core.aop.TenantIgnore;
import net.hzxzkj.cxlaike.module.cxlaike.controller.admin.memberdybind.vo.MemberDyBindPageReqVO;
import net.hzxzkj.cxlaike.module.cxlaike.controller.admin.memberdybind.vo.MemberDyBindPageRespVO;
import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.matrixaccount.vo.MatrixAccountUpdateAddressReqVO;
import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.matrixaccount.vo.MatrixAccountUpdateGroupReqVO;
import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.matrixaccount.vo.MatrixAccountUpdateStatusReqVO;
import net.hzxzkj.cxlaike.module.cxlaike.convert.memberdybind.MemberDyBindConvert;
import net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.matrixtask.MatrixTaskDO;
import net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.matrixtaskaccount.MatrixTaskAccountDO;
import net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.memberdybind.MemberDyBindDO;
import net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.memberdytoken.MemberDyTokenDO;
import net.hzxzkj.cxlaike.module.cxlaike.dal.mysql.memberdybind.MemberDyBindMapper;
import net.hzxzkj.cxlaike.module.cxlaike.enums.douyin.DyStatusEnum;
import net.hzxzkj.cxlaike.module.cxlaike.enums.douyin.DyUserTypeEnum;
import net.hzxzkj.cxlaike.module.cxlaike.service.dygroupcorrelation.DyGroupCorrelationService;
import net.hzxzkj.cxlaike.module.cxlaike.service.matrixtask.MatrixTaskService;
import net.hzxzkj.cxlaike.module.cxlaike.service.matrixtaskaccount.MatrixTaskAccountService;
import net.hzxzkj.cxlaike.module.cxlaike.service.memberdytoken.MemberDyTokenService;
import net.hzxzkj.cxlaike.module.cxlaike.utils.douyin.vo.AccessTokenRespDTO;
import net.hzxzkj.cxlaike.module.cxlaike.utils.douyin.vo.UserInfoRespDTO;
import net.hzxzkj.cxlaike.module.member.api.user.dto.DouYinInfoUpdateReqDTO;
import net.hzxzkj.cxlaike.module.member.enums.DyAccreditStatusEnum;
import net.hzxzkj.cxlaike.module.member.enums.DyAuditStatusEnum;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static net.hzxzkj.cxlaike.framework.common.exception.util.ServiceExceptionUtil.exception;
import static net.hzxzkj.cxlaike.module.member.enums.ErrorCodeConstants.*;

/**
 * @author jianan.han
 * @date 2023-08-29 11:42
 * @description
 */
@Service
@Slf4j
public class MemberDyBindServiceImpl extends BaseServiceImpl<MemberDyBindMapper, MemberDyBindDO> implements MemberDyBindService {

    @Resource
    private MemberDyBindMapper memberDyBindMapper;
    @Resource
    private MemberDyTokenService memberDyTokenService;
    @Resource
    private MatrixTaskAccountService matrixTaskAccountService;
    @Resource
    private MatrixTaskService matrixTaskService;
    @Resource
    private DyGroupCorrelationService dyGroupCorrelationService;


    @Override
    public void updateForDouYinInfo(Long id, String dyHomePage, String dyGradeUrl, Integer dyGrade) {
        MemberDyBindDO bindDO = memberDyBindMapper.selectById(id);
        if (!DyAuditStatusEnum.PART_PROFILE.getCode().equals(bindDO.getDyAuditStatus()) && !DyAuditStatusEnum.AUDIT.getCode().equals(bindDO.getDyAuditStatus())) {
            throw exception(new ErrorCode(400, "授权状态异常,提交失败"));
        }
        bindDO.setDyHomePage(dyHomePage);
        bindDO.setDyGrade(dyGrade);
        bindDO.setDyGradeUrl(dyGradeUrl);
        bindDO.setDyAuditStatus(DyAuditStatusEnum.TO_AUDIT.getCode());
        memberDyBindMapper.updateById(bindDO);
    }

    @Override
    public Long updateForDouYinAccredit(DouYinInfoUpdateReqDTO updateReqDTO, String openId) {
        Long id;
        if (DyUserTypeEnum.SJ.getCode().equals(updateReqDTO.getUserType())) {

            MemberDyBindDO memberDyBindDO = this.getDyUserByOpenId(updateReqDTO.getDyOpenId());
            if (memberDyBindDO == null) {
                MemberDyBindDO insertDO = MemberDyBindConvert.INSTANCE.convert(updateReqDTO);
                insertDO.setMemberId(updateReqDTO.getUserId());
                insertDO.setCreator(updateReqDTO.getUserId().toString());
                memberDyBindMapper.insert(insertDO);
                id = insertDO.getId();
            }else {
                memberDyBindDO.setDyAvatar(updateReqDTO.getDyAvatar());
                memberDyBindDO.setDyNickname(updateReqDTO.getDyNickname());
                memberDyBindDO.setTenantId(updateReqDTO.getUserId());
                memberDyBindDO.setUserType(DyUserTypeEnum.SJ.getCode());
                memberDyBindDO.setPoiId(updateReqDTO.getPoiId());
                memberDyBindDO.setPoiName(updateReqDTO.getPoiName());
                memberDyBindDO.setAddress(updateReqDTO.getAddress());
                memberDyBindDO.setLocation(updateReqDTO.getLocation());
                memberDyBindDO.setProvinceName(updateReqDTO.getProvinceName());
                memberDyBindDO.setCityName(updateReqDTO.getCityName());
                memberDyBindDO.setCountyName(updateReqDTO.getCountyName());
                memberDyBindMapper.updateById(memberDyBindDO);
                id = memberDyBindDO.getId();
            }
        } else {
            //更新
            if (StringUtils.isNotBlank(openId)) {
                MemberDyBindDO memberDyBindDO = memberDyBindMapper.selectOne(MemberDyBindDO::getMemberId, updateReqDTO.getUserId(), MemberDyBindDO::getDyOpenId, openId);
                if (memberDyBindDO == null) {
                    throw exception(new ErrorCode(400, "用户信息不存在"));
                }
                memberDyBindDO.setDyAvatar(updateReqDTO.getDyAvatar());
                memberDyBindDO.setDyNickname(updateReqDTO.getDyNickname());
                memberDyBindMapper.updateById(memberDyBindDO);
                id = memberDyBindDO.getId();
            } else {
                MemberDyBindDO insertDO = MemberDyBindConvert.INSTANCE.convert(updateReqDTO);
                insertDO.setMemberId(updateReqDTO.getUserId());
                insertDO.setCreator(updateReqDTO.getUserId().toString());
                memberDyBindMapper.insert(insertDO);
                id = insertDO.getId();

            }
        }
        memberDyTokenService.updateOrInsert(updateReqDTO, id);
        return id;

    }

    @Override
    @TenantIgnore
    public MemberDyBindDO getByOpenIdAndMemberId(String openId, Long memberId) {
        return memberDyBindMapper.selectOne(new LambdaQueryWrapper<MemberDyBindDO>()
                .eq(MemberDyBindDO::getDyOpenId, openId)
                .eq(MemberDyBindDO::getMemberId, memberId)
                .ne(MemberDyBindDO::getDyAuditStatus, DyAuditStatusEnum.PART_PROFILE.getCode()));
    }

    @Override
    public PageResult<MemberDyBindPageRespVO> getListForAudit(MemberDyBindPageReqVO reqVO) {
        Page<MemberDyBindPageRespVO> page = new Page<>(reqVO.getPageNo(), reqVO.getPageSize());
        List<MemberDyBindPageRespVO> respVOS = memberDyBindMapper.selectListForAudit(page, reqVO);
        return new PageResult<>(respVOS, page.getTotal());
    }

    @Override
    public Long createDyBindOfForward(Long userId, String openId) {
        if (StringUtils.isNotBlank(openId)) {
            MemberDyBindDO memberDyBindDO = memberDyBindMapper.selectOne(MemberDyBindDO::getMemberId, userId, MemberDyBindDO::getDyOpenId, openId);
            if (memberDyBindDO == null) {
                throw exception(DATA_ERROR);
            }
            return memberDyBindDO.getId();
        } else {
            MemberDyBindDO memberDyBindDO = memberDyBindMapper.selectOne(MemberDyBindDO::getMemberId, userId, MemberDyBindDO::getDyAuditStatus, 0);
            if (memberDyBindDO != null) {
                return memberDyBindDO.getId();
            }
            MemberDyBindDO dyBindDO = new MemberDyBindDO();
            dyBindDO.setMemberId(userId);
            memberDyBindMapper.insert(dyBindDO);
            return dyBindDO.getId();
        }
    }

    @Override
    public List<MemberDyBindDO> getListByUserId(Long userId) {
        return memberDyBindMapper.selectList(new LambdaQueryWrapper<MemberDyBindDO>()
                .eq(MemberDyBindDO::getMemberId, userId)
                .in(MemberDyBindDO::getDyAuditStatus, Arrays.asList(DyAuditStatusEnum.TO_AUDIT.getCode(), DyAuditStatusEnum.AUDIT.getCode(), DyAuditStatusEnum.UN_AUDIT.getCode())));
    }

    @Override
    public MemberDyBindDO getById(Long id) {
        return memberDyBindMapper.selectById(id);
    }

    @Override
    @TenantIgnore
    public MemberDyBindDO getByIdWithOutTenantId(Long id) {
        return memberDyBindMapper.selectById(id);
    }

    @Override
    public void update(Long id, String userId, Integer fansCount, Integer giveLikeCount, Integer worksCount,
                       Integer provinceCode, String provinceName, Integer cityCode, String cityName, Integer countyCode
            , String countyName) {
        MemberDyBindDO dyBindDO = this.getById(id);
        if (dyBindDO == null) {
            throw exception(DATA_ERROR);
        }
        dyBindDO.setDyUserId(userId);
        dyBindDO.setDyFansCount(fansCount);
        dyBindDO.setDyGiveLikeCount(giveLikeCount);
        dyBindDO.setDyWorksCount(worksCount);
        dyBindDO.setProvinceCode(provinceCode);
        dyBindDO.setProvinceName(provinceName);
        dyBindDO.setCityCode(cityCode);
        dyBindDO.setCityName(cityName);
        dyBindDO.setCountyCode(countyCode);
        dyBindDO.setCountyName(countyName);
        memberDyBindMapper.updateById(dyBindDO);
    }

    @Override
    public void audit(Long id, Integer auditStatus, String reason, String userId, Integer fansCount, Integer giveLikeCount, Integer worksCount,
                      Integer provinceCode, String provinceName, Integer cityCode, String cityName, Integer countyCode
            , String countyName) {
        MemberDyBindDO dyBindDO = this.getById(id);
        if (dyBindDO == null) {
            throw exception(DATA_ERROR);
        }
        dyBindDO.setDyAuditStatus(auditStatus);
        dyBindDO.setReason(reason);
        dyBindDO.setDyUserId(userId);
        dyBindDO.setDyFansCount(fansCount);
        dyBindDO.setDyGiveLikeCount(giveLikeCount);
        dyBindDO.setDyWorksCount(worksCount);
        dyBindDO.setProvinceCode(provinceCode);
        dyBindDO.setProvinceName(provinceName);
        dyBindDO.setCityCode(cityCode);
        dyBindDO.setCityName(cityName);
        dyBindDO.setCountyCode(countyCode);
        dyBindDO.setCountyName(countyName);
        memberDyBindMapper.updateById(dyBindDO);
    }

    @Override
    public boolean checkDyUser(Long memberId, String openId) {
        return memberDyBindMapper.exists(new LambdaQueryWrapper<MemberDyBindDO>()
                .eq(MemberDyBindDO::getMemberId, memberId)
                .eq(MemberDyBindDO::getDyOpenId, openId)
                .ne(MemberDyBindDO::getDyAuditStatus, DyAuditStatusEnum.PART_PROFILE.getCode()));
    }

    @Override
    public boolean checkDyUserByOpenId(String openId) {
        return memberDyBindMapper.exists(new LambdaQueryWrapper<MemberDyBindDO>()
                .eq(MemberDyBindDO::getDyOpenId, openId)
                .ne(MemberDyBindDO::getDyAuditStatus, DyAuditStatusEnum.PART_PROFILE.getCode()));
    }

    @Override
    public MemberDyBindDO getDyUserByOpenId(String openId) {
        return memberDyBindMapper.selectOne(new LambdaQueryWrapper<MemberDyBindDO>()
                .eq(MemberDyBindDO::getDyOpenId, openId)
                .ne(MemberDyBindDO::getDyAuditStatus, DyAuditStatusEnum.PART_PROFILE.getCode()));
    }

    @Override
    @TenantIgnore
    public MemberDyBindDO getDyUserByOpenIdWithOutTenantId(String openId,Integer platform) {
        return memberDyBindMapper.selectOne(new LambdaQueryWrapper<MemberDyBindDO>()
                .eq(MemberDyBindDO::getDyOpenId, openId)
                .eq(platform!=null,MemberDyBindDO::getPlatformType, platform)
                .ne(MemberDyBindDO::getDyAuditStatus, DyAuditStatusEnum.PART_PROFILE.getCode()));
    }

    @Override
    public MemberDyBindDO getNewestAccreditById(Long memberId) {
        return memberDyBindMapper.selectOne(new LambdaQueryWrapper<MemberDyBindDO>()
                .eq(MemberDyBindDO::getMemberId, memberId)
                .orderByDesc(MemberDyBindDO::getUpdateTime)
                .last("limit 1"));
    }

    @Override
    public void deleteById(Long id) {
        MemberDyBindDO memberDyBindDO = memberDyBindMapper.selectById(id);
        if (memberDyBindDO == null) {
            throw exception(DATA_ERROR);
        }
        if (!DyUserTypeEnum.SJ.getCode().equals(memberDyBindDO.getUserType())) {
            throw exception(USER_LIMITS_ERROR);
        }
        //校验是否有进行中的任务
        this.checkTask(id);
        //1.绑定数据
        memberDyBindMapper.deleteById(id);
        //2.绑定的token数据
        memberDyTokenService.deleteEntity(id);
        //3.分组关系数据
        dyGroupCorrelationService.deleteByBindId(id);

    }

    @Override
    public void updateStatus(MatrixAccountUpdateStatusReqVO reqVO) {
        MemberDyBindDO memberDyBindDO = memberDyBindMapper.selectById(reqVO.getId());
        if (memberDyBindDO == null) {
            throw exception(DATA_ERROR);
        }
        memberDyBindDO.setStatus(reqVO.getStatus());
        memberDyBindMapper.updateById(memberDyBindDO);
    }

    @Override
    public void updateGroup(MatrixAccountUpdateGroupReqVO reqVO) {
        // 1.删除原有绑定关系
        dyGroupCorrelationService.deleteByBindId(reqVO.getId());
        // 2.绑定
        dyGroupCorrelationService.createCorrelation(reqVO.getGroupIds(), reqVO.getId());
    }

    @Override
    public void updateAddress(MatrixAccountUpdateAddressReqVO reqVO) {
        MemberDyBindDO memberDyBindDO = memberDyBindMapper.selectById(reqVO.getId());
        if (memberDyBindDO == null) {
            throw exception(DATA_ERROR);
        }
        memberDyBindDO.setPoiId(reqVO.getPoiId());
        memberDyBindDO.setPoiName(reqVO.getPoiName());
        memberDyBindDO.setProvinceCode(reqVO.getProvinceCode());
        memberDyBindDO.setProvinceName(reqVO.getProvinceName());
        memberDyBindDO.setCityCode(reqVO.getCityCode());
        memberDyBindDO.setCityName(reqVO.getCityName());
        memberDyBindDO.setCountyCode(reqVO.getCountyCode());
        memberDyBindDO.setCountyName(reqVO.getCountyName());
        memberDyBindDO.setAddress(reqVO.getAddress());
        memberDyBindMapper.updateById(memberDyBindDO);
    }


    private void checkTask(Long id) {
        List<MatrixTaskAccountDO> taskAccountDOS = matrixTaskAccountService.getListByAccountId(id);
        if (CollectionUtil.isNotEmpty(taskAccountDOS)) {
            List<Long> ids = taskAccountDOS.stream().map(MatrixTaskAccountDO::getId).collect(Collectors.toList());
            List<MatrixTaskDO> taskDOList = matrixTaskService.getUnderwayListByIds(ids);
            if (CollectionUtil.isNotEmpty(taskDOList)) {
                throw exception(MATRIX_ACCOUNT_DELETE_ERROR);
            }
        }
    }

    @Override
    public List<MemberDyBindDO> getList(Integer platformType) {
        List<MemberDyBindDO> memberDyBindDOS = memberDyBindMapper.selectList(new LambdaQueryWrapper<MemberDyBindDO>()
                .eq(MemberDyBindDO::getDyAuditStatus, DyAuditStatusEnum.AUDIT.getCode())
                .eq(MemberDyBindDO::getPlatformType, platformType)
                .eq(MemberDyBindDO::getStatus, 1)//启用
        );
        List<MemberDyBindDO> respDO = new ArrayList<>();
        for (MemberDyBindDO memberDyBindDO : memberDyBindDOS) {
            MemberDyTokenDO memberDyTokenDO = memberDyTokenService.getById(memberDyBindDO.getId());
            if (memberDyTokenDO == null) {
                continue;
            }
            if (DyAccreditStatusEnum.UN_ACCREDIT.getCode().equals(memberDyTokenDO.getDyAccreditStatus())) {
                continue;
            }
            if (DyAccreditStatusEnum.CANCEL_ACCREDIT.getCode().equals(memberDyTokenDO.getDyAccreditStatus())) {
                continue;
            }
            respDO.add(memberDyBindDO);
        }
        return respDO;
    }

    @Override
    public Long dealBindAccountByScan(AccessTokenRespDTO tokenRespDTO, UserInfoRespDTO infoRespDTO) {
        MemberDyBindDO memberDyBindDO = memberDyBindMapper.selectOne(new LambdaQueryWrapper<MemberDyBindDO>()
                .eq(MemberDyBindDO::getDyOpenId,tokenRespDTO.getOpen_id()));
        LocalDateTime now = LocalDateTime.now();
        if(memberDyBindDO == null){
            memberDyBindDO = new MemberDyBindDO();
            memberDyBindDO.setUserType(DyUserTypeEnum.WB.getCode());
            memberDyBindDO.setDyNickname(infoRespDTO.getNickname());
            memberDyBindDO.setDyAvatar(infoRespDTO.getAvatar());
            memberDyBindDO.setDyOpenId(tokenRespDTO.getOpen_id());
            memberDyBindDO.setDyAuditStatus(DyAuditStatusEnum.AUDIT.getCode());
            memberDyBindMapper.insert(memberDyBindDO);
        }
        MemberDyTokenDO memberDyTokenDO = new MemberDyTokenDO();
        memberDyTokenDO.setId(memberDyBindDO.getId());
        memberDyTokenDO.setDyAccessToken(tokenRespDTO.getAccess_token());
        memberDyTokenDO.setDyExpiresTime(now.plusSeconds(tokenRespDTO.getExpires_in()));
        memberDyTokenDO.setDyRefreshToken(tokenRespDTO.getRefresh_token());
        memberDyTokenDO.setDyRefreshExpiresTime(now.plusSeconds(tokenRespDTO.getRefresh_expires_in()));
        memberDyTokenDO.setDyAccreditStatus(DyAccreditStatusEnum.ACCREDIT.getCode());
        memberDyTokenService.createOrUpdateEntity(memberDyTokenDO);

        return memberDyBindDO.getId();
    }

    @Override
    @TenantIgnore
    public Long updateByBind(MemberDyBindDO memberDyBindDO, DouYinInfoUpdateReqDTO updateReqDTO) {
        if(memberDyBindDO == null){
            memberDyBindDO = new MemberDyBindDO();
        }
        memberDyBindDO.setDyAuditStatus(1);//1已授权 2已失效
        memberDyBindDO.setDyAvatar(updateReqDTO.getDyAvatar());
        memberDyBindDO.setDyNickname(updateReqDTO.getDyNickname());
        memberDyBindDO.setTenantId(updateReqDTO.getUserId());
        memberDyBindDO.setUserType(DyUserTypeEnum.SJ.getCode());
        memberDyBindDO.setPoiId(updateReqDTO.getPoiId());
        memberDyBindDO.setPoiName(updateReqDTO.getPoiName());
        memberDyBindDO.setAddress(updateReqDTO.getAddress());
        memberDyBindDO.setLocation(updateReqDTO.getLocation());
        memberDyBindDO.setProvinceName(updateReqDTO.getProvinceName());
        memberDyBindDO.setCityName(updateReqDTO.getCityName());
        memberDyBindDO.setCountyName(updateReqDTO.getCountyName());
        memberDyBindMapper.updateById(memberDyBindDO);
        return memberDyBindDO.getId();
    }


}
