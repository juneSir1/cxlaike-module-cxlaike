package net.hzxzkj.cxlaike.module.cxlaike.service.dypersonalletteruser;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import net.hzxzkj.cxlaike.framework.common.util.json.JsonUtils;
import net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.dypersonalletter.DyPersonalLetterDO;
import net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.memberdybind.MemberDyBindDO;
import net.hzxzkj.cxlaike.module.cxlaike.dal.mysql.dypersonalletter.DyPersonalLetterMapper;
import net.hzxzkj.cxlaike.module.cxlaike.redis.dypersonalletteruser.LetterUserLockRedisDAO;
import net.hzxzkj.cxlaike.module.cxlaike.service.memberdybind.MemberDyBindService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import org.springframework.validation.annotation.Validated;

import java.util.*;

import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.dypersonalletteruser.vo.*;
import net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.dypersonalletteruser.DyPersonalLetterUserDO;
import net.hzxzkj.cxlaike.framework.common.pojo.PageResult;

import net.hzxzkj.cxlaike.module.cxlaike.convert.dypersonalletteruser.DyPersonalLetterUserConvert;
import net.hzxzkj.cxlaike.module.cxlaike.dal.mysql.dypersonalletteruser.DyPersonalLetterUserMapper;

import static net.hzxzkj.cxlaike.framework.common.exception.util.ServiceExceptionUtil.exception;
import static net.hzxzkj.cxlaike.module.cxlaike.enums.ErrorCodeConstants.DATA_ERROR;
import static net.hzxzkj.cxlaike.module.infra.enums.ErrorCodeConstants.FILE_NAME_EXISTS;

/**
 * 抖音私信用户管理 Service 实现类
 *
 * @author 宵征源码
 */
@Service
@Validated
public class DyPersonalLetterUserServiceImpl implements DyPersonalLetterUserService {

    @Resource
    private DyPersonalLetterUserMapper dyPersonalLetterUserMapper;
    @Resource
    private MemberDyBindService memberDyBindService;
    @Resource
    private DyPersonalLetterMapper dyPersonalLetterMapper;
    @Resource
    private LetterUserLockRedisDAO letterUserLockRedisDAO;

    @Override
    public Long createDyPersonalLetterUser(DyPersonalLetterUserCreateReqVO createReqVO) {
        // 插入
        DyPersonalLetterUserDO dyPersonalLetterUser = DyPersonalLetterUserConvert.INSTANCE.convert(createReqVO);
        dyPersonalLetterUserMapper.insert(dyPersonalLetterUser);
        // 返回
        return dyPersonalLetterUser.getId();
    }

    @Override
    public void updateDyPersonalLetterUser(DyPersonalLetterUserUpdateReqVO updateReqVO) {
        // 校验存在
        validateDyPersonalLetterUserExists(updateReqVO.getId());
        // 更新
        DyPersonalLetterUserDO updateObj = DyPersonalLetterUserConvert.INSTANCE.convert(updateReqVO);
        dyPersonalLetterUserMapper.updateById(updateObj);
    }

    @Override
    public void deleteDyPersonalLetterUser(Long id) {
        // 校验存在
        validateDyPersonalLetterUserExists(id);
        // 删除
        dyPersonalLetterUserMapper.deleteById(id);
    }

    private void validateDyPersonalLetterUserExists(Long id) {
        if (dyPersonalLetterUserMapper.selectById(id) == null) {
            throw exception(DATA_ERROR);
        }
    }

    @Override
    public DyPersonalLetterUserDO getDyPersonalLetterUser(Long id) {
        return dyPersonalLetterUserMapper.selectById(id);
    }

    @Override
    public List<DyPersonalLetterUserRespVO> getDyPersonalLetterUserList() {
        List<DyPersonalLetterUserDO> dyPersonalLetterUserDOS = dyPersonalLetterUserMapper.selectList(new LambdaQueryWrapper<DyPersonalLetterUserDO>()
                .orderByDesc(DyPersonalLetterUserDO::getUpdateTime));

        List<DyPersonalLetterUserRespVO> dyPersonalLetterUserRespVOS = DyPersonalLetterUserConvert.INSTANCE.convertList(dyPersonalLetterUserDOS);
        for(DyPersonalLetterUserRespVO letterUserRespVO : dyPersonalLetterUserRespVOS){
            DyPersonalLetterDO letterDO = dyPersonalLetterMapper.selectOne(new LambdaQueryWrapper<DyPersonalLetterDO>()
                    .eq(DyPersonalLetterDO::getConversationShortId,letterUserRespVO.getConversationId())
                    .orderByDesc(DyPersonalLetterDO::getMessageCreateTime)
                    .last("limit 1"));
            if(letterDO != null){
                letterUserRespVO.setText(letterDO.getText());
            }
        }
        return dyPersonalLetterUserRespVOS;
    }

    @Override
    public PageResult<DyPersonalLetterUserRespVO> getDyPersonalLetterUserPage(DyPersonalLetterUserPageReqVO pageReqVO) {

        PageResult<DyPersonalLetterUserDO> dyPersonalLetterUserDOPageResult = dyPersonalLetterUserMapper.selectPage(pageReqVO);

        PageResult<DyPersonalLetterUserRespVO> respVOPageResult = DyPersonalLetterUserConvert.INSTANCE.convertPage(dyPersonalLetterUserDOPageResult);

        for(DyPersonalLetterUserRespVO respVO : respVOPageResult.getList()){
            MemberDyBindDO memberDyBindDO = memberDyBindService.getById(respVO.getAccountId());
            if(memberDyBindDO != null){
                respVO.setUserNickName(memberDyBindDO.getDyNickname());
                respVO.setUserAvatar(memberDyBindDO.getDyAvatar());
            }
            DyPersonalLetterDO letterDO = dyPersonalLetterMapper.selectOne(new LambdaQueryWrapper<DyPersonalLetterDO>()
                    .eq(DyPersonalLetterDO::getConversationShortId,respVO.getConversationId())
                    .orderByDesc(DyPersonalLetterDO::getMessageCreateTime)
                    .last("limit 1"));
            if(letterDO != null){
                respVO.setText(letterDO.getText());
            }
        }

        return respVOPageResult;
    }

    @Override
    public List<DyPersonalLetterUserDO> getDyPersonalLetterUserList(DyPersonalLetterUserExportReqVO exportReqVO) {
        return dyPersonalLetterUserMapper.selectList(exportReqVO);
    }

    @Override
    public void createUserDO(String openId, List<DyPersonalLetterUserInfoVO> userInfoVOS,Long accountId,String conversationId) {
        letterUserLockRedisDAO.lock(openId + accountId + conversationId, () -> {
            DyPersonalLetterUserDO dyPersonalLetterUserDO = dyPersonalLetterUserMapper.selectOne(new LambdaQueryWrapper<DyPersonalLetterUserDO>()
                    .eq(DyPersonalLetterUserDO::getOpenId, openId)
                    .eq(DyPersonalLetterUserDO::getAccountId,accountId)
                    .eq(DyPersonalLetterUserDO::getConversationId,conversationId));
            if (CollectionUtil.isEmpty(userInfoVOS)) {
                return;
            }
            for (DyPersonalLetterUserInfoVO userInfoVO : userInfoVOS) {
                if (openId.equals(userInfoVO.getOpen_id())) {
                    if(dyPersonalLetterUserDO == null) {
                        dyPersonalLetterUserDO = new DyPersonalLetterUserDO();
                        dyPersonalLetterUserDO.setAvatar(userInfoVO.getAvatar());
                        dyPersonalLetterUserDO.setOpenId(userInfoVO.getOpen_id());
                        dyPersonalLetterUserDO.setNickName(userInfoVO.getNick_name());
                        dyPersonalLetterUserDO.setAccountId(accountId);
                        dyPersonalLetterUserDO.setConversationId(conversationId);
                        dyPersonalLetterUserMapper.insert(dyPersonalLetterUserDO);
                        break;
                    }else {
                        dyPersonalLetterUserDO.setAvatar(userInfoVO.getAvatar());
                        dyPersonalLetterUserDO.setOpenId(userInfoVO.getOpen_id());
                        dyPersonalLetterUserDO.setNickName(userInfoVO.getNick_name());
                        dyPersonalLetterUserMapper.updateById(dyPersonalLetterUserDO);
                        break;
                    }
                }
            }
        });




    }

    @Override
    public DyPersonalLetterUserDO getByConversationId(String conversationId) {
        return dyPersonalLetterUserMapper.selectOne(new LambdaQueryWrapper<DyPersonalLetterUserDO>()
                .eq(DyPersonalLetterUserDO::getConversationId,conversationId));
    }

}
