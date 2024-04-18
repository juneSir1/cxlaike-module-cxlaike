package net.hzxzkj.cxlaike.module.cxlaike.service.dypersonalletter;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import lombok.extern.slf4j.Slf4j;
import net.hzxzkj.cxlaike.framework.common.util.date.LocalDateTimeUtils;
import net.hzxzkj.cxlaike.framework.common.util.json.JsonUtils;
import net.hzxzkj.cxlaike.framework.tenant.core.util.TenantUtils;
import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.douyin.vo.DealWebhooksReqVO;
import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.dypersonalletteruser.vo.DyPersonalLetterUserInfoVO;
import net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.dypersonalletteruser.DyPersonalLetterUserDO;
import net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.memberdybind.MemberDyBindDO;
import net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.memberdytoken.MemberDyTokenDO;
import net.hzxzkj.cxlaike.module.cxlaike.enums.douyin.WebHooksTypeEnum;
import net.hzxzkj.cxlaike.module.cxlaike.service.dypersonalletteruser.DyPersonalLetterUserService;
import net.hzxzkj.cxlaike.module.cxlaike.service.memberdybind.MemberDyBindService;
import net.hzxzkj.cxlaike.module.cxlaike.service.memberdytoken.MemberDyTokenService;
import net.hzxzkj.cxlaike.module.cxlaike.utils.douyin.DouYinApiUtils;
import net.hzxzkj.cxlaike.module.cxlaike.utils.douyin.vo.SendMsgRespDTO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDateTime;
import java.util.*;

import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.dypersonalletter.vo.*;
import net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.dypersonalletter.DyPersonalLetterDO;
import net.hzxzkj.cxlaike.framework.common.pojo.PageResult;

import net.hzxzkj.cxlaike.module.cxlaike.convert.dypersonalletter.DyPersonalLetterConvert;
import net.hzxzkj.cxlaike.module.cxlaike.dal.mysql.dypersonalletter.DyPersonalLetterMapper;

import static net.hzxzkj.cxlaike.framework.common.exception.util.ServiceExceptionUtil.exception;
import static net.hzxzkj.cxlaike.module.cxlaike.enums.ErrorCodeConstants.*;

/**
 * 抖音私信管理 Service 实现类
 *
 * @author 宵征源码
 */
@Service
@Validated
@Slf4j
public class DyPersonalLetterServiceImpl implements DyPersonalLetterService {

    @Resource
    private DyPersonalLetterMapper dyPersonalLetterMapper;
    @Resource
    private MemberDyBindService memberDyBindService;
    @Resource
    private DyPersonalLetterUserService dyPersonalLetterUserService;
    @Resource
    private DouYinApiUtils douYinApiUtils;
    @Resource
    private MemberDyTokenService memberDyTokenService;


    @Override
    public Long createDyPersonalLetter(DyPersonalLetterCreateReqVO createReqVO) {
        DyPersonalLetterDO letterDO = dyPersonalLetterMapper.selectOne(new LambdaQueryWrapper<DyPersonalLetterDO>()
                .eq(DyPersonalLetterDO::getConversationShortId,createReqVO.getConversationShortId())
                .eq(DyPersonalLetterDO::getEvent,WebHooksTypeEnum.IM_RECEIVE_MSG.getType())
                .orderByDesc(DyPersonalLetterDO::getMessageCreateTime)
                .last("limit 1"));
        if(letterDO == null){
            throw exception(CONVERSATION_ERROR);
        }

        MemberDyBindDO memberDyBindDO = memberDyBindService.getDyUserByOpenId(letterDO.getToUserId());
        if(memberDyBindDO == null){
            throw exception(DATA_ERROR);
        }
        MemberDyTokenDO memberDyTokenDO = memberDyTokenService.getById(memberDyBindDO.getId());
        if(memberDyTokenDO == null){
            throw exception(DATA_ERROR);
        }

        SendMsgRespDTO sendMsgRespDTO = douYinApiUtils.sendMsg(memberDyTokenDO.getDyAccessToken()
                , letterDO.getToUserId(), letterDO.getServerMessageId(), letterDO.getConversationShortId()
                , letterDO.getFromUserId(), createReqVO.getText());
        if (sendMsgRespDTO == null) {
            throw exception(CONVERSATION_SEND_ERROR);
        }
        //用户token是否过期
        if (sendMsgRespDTO.getData().getError_code() != 0) {
            log.warn("【抖音发送私信失败】,错误信息:{}", sendMsgRespDTO.getExtra().getDescription());
            throw exception(CONVERSATION_SEND_ERROR);
        }
        DyPersonalLetterDO createDO = new DyPersonalLetterDO();
        createDO.setEvent(WebHooksTypeEnum.IM_SEND_MSG.getType());
        createDO.setFromUserId(letterDO.getToUserId());
        createDO.setToUserId(letterDO.getFromUserId());
        createDO.setConversationShortId(createReqVO.getConversationShortId());
        createDO.setServerMessageId(sendMsgRespDTO.getMsg_id());
        createDO.setConversationType(1);
        createDO.setMessageCreateTime(LocalDateTime.now());
        createDO.setMessageType("text");
        createDO.setText(createReqVO.getText());
        // 插入
        dyPersonalLetterMapper.insert(createDO);
        // 返回
        return createDO.getId();
    }

    @Override
    public void updateDyPersonalLetter(DyPersonalLetterUpdateReqVO updateReqVO) {
        // 校验存在
        validateDyPersonalLetterExists(updateReqVO.getId());
        // 更新
        DyPersonalLetterDO updateObj = DyPersonalLetterConvert.INSTANCE.convert(updateReqVO);
        dyPersonalLetterMapper.updateById(updateObj);
    }

    @Override
    public void deleteDyPersonalLetter(Long id) {
        // 校验存在
        validateDyPersonalLetterExists(id);
        // 删除
        dyPersonalLetterMapper.deleteById(id);
    }

    private void validateDyPersonalLetterExists(Long id) {
        if (dyPersonalLetterMapper.selectById(id) == null) {
            throw exception(DATA_ERROR);
        }
    }

    @Override
    public DyPersonalLetterDO getDyPersonalLetter(Long id) {
        return dyPersonalLetterMapper.selectById(id);
    }

    @Override
    public List<DyPersonalLetterRespVO> getDyPersonalLetterList(String conversationId) {
        List<DyPersonalLetterDO> dyPersonalLetterDOS = dyPersonalLetterMapper.selectList(new LambdaUpdateWrapper<DyPersonalLetterDO>()
                .eq(DyPersonalLetterDO::getConversationShortId, conversationId)
                .orderByAsc(DyPersonalLetterDO::getId));
        List<DyPersonalLetterRespVO> dyPersonalLetterRespVOS = DyPersonalLetterConvert.INSTANCE.convertList(dyPersonalLetterDOS);
        for(DyPersonalLetterRespVO respVO : dyPersonalLetterRespVOS){
            DyPersonalLetterUserDO personalLetterUserDO = dyPersonalLetterUserService.getByConversationId(respVO.getConversationShortId());
            if(personalLetterUserDO == null){
                continue;
            }
            MemberDyBindDO memberDyBindDO = memberDyBindService.getById(personalLetterUserDO.getAccountId());
            if(memberDyBindDO != null){
                respVO.setToUserAvatar(memberDyBindDO.getDyAvatar());
                respVO.setToNickName(memberDyBindDO.getDyNickname());
            }
            respVO.setFromUserAvatar(personalLetterUserDO.getAvatar());
            respVO.setFromNickName(personalLetterUserDO.getNickName());
        }
        return dyPersonalLetterRespVOS;
    }

    @Override
    public PageResult<DyPersonalLetterDO> getDyPersonalLetterPage(DyPersonalLetterPageReqVO pageReqVO) {
        return dyPersonalLetterMapper.selectPage(pageReqVO);
    }

    @Override
    public List<DyPersonalLetterDO> getDyPersonalLetterList(DyPersonalLetterExportReqVO exportReqVO) {
        return dyPersonalLetterMapper.selectList(exportReqVO);
    }



    @Override
    @Transactional(rollbackFor = Exception.class)
    public void createPersonalLetter(DealWebhooksReqVO reqVO) {
        DyPersonalLetterDO letterDO = new DyPersonalLetterDO();
        letterDO.setEvent(reqVO.getEvent());
        letterDO.setClientKey(reqVO.getClient_key());
        letterDO.setFromUserId(reqVO.getFrom_user_id());
        letterDO.setToUserId(reqVO.getTo_user_id());

        MemberDyBindDO memberDyBindDO = memberDyBindService.getDyUserByOpenId(reqVO.getTo_user_id());
        if (memberDyBindDO == null) {
            log.info("【抖音回调事件】用户信息不存在:openId:{}",reqVO.getTo_user_id());
            return;
        }
        Map<String, Object> content = reqVO.getContent();
        /**
         * 会话 ID
         */
        String conversationShortId = content.get("conversation_short_id") == null ? "" : (String) content.get("conversation_short_id");
        letterDO.setConversationShortId(conversationShortId);
        /**
         * 消息 ID
         */
        String serverMessageId = content.get("server_message_id") == null ? "" : (String) content.get("server_message_id");
        letterDO.setServerMessageId(serverMessageId);
        /**
         * 会话类型（1：私聊）
         */
        Integer conversationType = content.get("conversation_type") == null ? null : (Integer) content.get("conversation_type");
        letterDO.setConversationType(conversationType);

        Long createTime = content.get("create_time") == null ? null : (Long) content.get("create_time");
        /**
         * 消息创建时间
         */
        if(createTime != null){
            LocalDateTime messageCreateTime = LocalDateTimeUtils.getTimeByTimestamp(createTime);
            letterDO.setMessageCreateTime(messageCreateTime);
        }

        /**
         * text：文本 image：图片 emoji：表情 video：视频 retain_consult_card：留资卡片 other：其他 不同类型消息参数见下方介绍
         */
        String messageType = content.get("message_type") == null ? "" : (String) content.get("message_type");
        letterDO.setMessageType(messageType);
        /**
         * 内容
         */
        String text = content.get("text") == null ? "" : (String) content.get("text");
        letterDO.setText(text);
        /**
         * 资源类型
         */
        String resourceType = content.get("resource_type") == null ? "" : (String) content.get("resource_type");
        letterDO.setResourceType(resourceType);
        /**
         * 资源宽度
         */
        Integer resourceWidth = content.get("resource_width") == null ? null : (Integer) content.get("resource_width");
        letterDO.setResourceWidth(resourceWidth);
        /**
         * 资源高度
         */
        Integer resourceHeight = content.get("resource_height") == null ? null : (Integer) content.get("resource_height");
        letterDO.setResourceHeight(resourceHeight);
        /**
         * 资源链接
         */
        String resourceUrl = content.get("resource_url") == null ? "" : (String) content.get("resource_url");
        letterDO.setResourceUrl(resourceUrl);
        /**
         * 加密后的视频ID
         */
        String itemId = content.get("item_id") == null ? "" : (String) content.get("item_id");
        letterDO.setItemId(itemId);
        /**
         * 卡片id
         */
        String cardId = content.get("card_id") == null ? "" : (String) content.get("card_id");
        letterDO.setCardId(cardId);
        /**
         * 1:空白态；2:完成态
         */
        Integer cardStatus = content.get("resource_height") == null ? null : (Integer) content.get("resource_height");
        letterDO.setCardStatus(cardStatus);
        /**
         * 卡片信息
         */
        String cardData = content.get("card_data") == null ? "" : (String) content.get("card_data");
        letterDO.setCardData(cardData);
        /**
         * 区分发出应用通过发送私信消息接口发送，会显示具体的 clientkey通过端上主动发送，该字段默认为空
         */
        String source = content.get("source") == null ? "" : (String) content.get("source");
        letterDO.setSource(source);
        /**
         * 场景类型1:关键词自动回复 2:欢迎语 100:其他
         */
        Integer sceneType = content.get("scene_type") == null ? null : (Integer) content.get("scene_type");
        letterDO.setSceneType(sceneType);
        /**
         * 用户基本信息，包括：昵称和头像
         */

        String user_infos = JsonUtils.toJsonString(content.get("user_infos"));

        List<DyPersonalLetterUserInfoVO> userInfoVOS = content.get("user_infos") == null ? null : JsonUtils.parseArray(user_infos,DyPersonalLetterUserInfoVO.class);

        letterDO.setUserInfos(JsonUtils.toJsonString(userInfoVOS));
        letterDO.setTenantId(memberDyBindDO.getTenantId());
        dyPersonalLetterMapper.insert(letterDO);

        TenantUtils.execute(memberDyBindDO.getTenantId(), () -> {
            dyPersonalLetterUserService.createUserDO(reqVO.getFrom_user_id(),userInfoVOS,memberDyBindDO.getId(),conversationShortId);
        });



    }


}
