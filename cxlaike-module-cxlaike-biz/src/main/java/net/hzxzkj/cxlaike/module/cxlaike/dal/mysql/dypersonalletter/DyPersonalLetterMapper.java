package net.hzxzkj.cxlaike.module.cxlaike.dal.mysql.dypersonalletter;

import java.util.*;

import net.hzxzkj.cxlaike.framework.common.pojo.PageResult;
import net.hzxzkj.cxlaike.framework.mybatis.core.query.LambdaQueryWrapperX;
import net.hzxzkj.cxlaike.framework.mybatis.core.mapper.BaseMapperX;
import net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.dypersonalletter.DyPersonalLetterDO;
import org.apache.ibatis.annotations.Mapper;
import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.dypersonalletter.vo.*;

/**
 * 抖音私信管理 Mapper
 *
 * @author 宵征源码
 */
@Mapper
public interface DyPersonalLetterMapper extends BaseMapperX<DyPersonalLetterDO> {

    default PageResult<DyPersonalLetterDO> selectPage(DyPersonalLetterPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<DyPersonalLetterDO>()
                .eqIfPresent(DyPersonalLetterDO::getEvent, reqVO.getEvent())
                .eqIfPresent(DyPersonalLetterDO::getClientKey, reqVO.getClientKey())
                .eqIfPresent(DyPersonalLetterDO::getFromUserId, reqVO.getFromUserId())
                .eqIfPresent(DyPersonalLetterDO::getToUserId, reqVO.getToUserId())
                .eqIfPresent(DyPersonalLetterDO::getConversationShortId, reqVO.getConversationShortId())
                .eqIfPresent(DyPersonalLetterDO::getServerMessageId, reqVO.getServerMessageId())
                .eqIfPresent(DyPersonalLetterDO::getConversationType, reqVO.getConversationType())
                .betweenIfPresent(DyPersonalLetterDO::getMessageCreateTime, reqVO.getMessageCreateTime())
                .eqIfPresent(DyPersonalLetterDO::getMessageType, reqVO.getMessageType())
                .eqIfPresent(DyPersonalLetterDO::getText, reqVO.getText())
                .eqIfPresent(DyPersonalLetterDO::getResourceType, reqVO.getResourceType())
                .eqIfPresent(DyPersonalLetterDO::getResourceWidth, reqVO.getResourceWidth())
                .eqIfPresent(DyPersonalLetterDO::getResourceHeight, reqVO.getResourceHeight())
                .eqIfPresent(DyPersonalLetterDO::getResourceUrl, reqVO.getResourceUrl())
                .eqIfPresent(DyPersonalLetterDO::getItemId, reqVO.getItemId())
                .eqIfPresent(DyPersonalLetterDO::getCardId, reqVO.getCardId())
                .eqIfPresent(DyPersonalLetterDO::getCardStatus, reqVO.getCardStatus())
                .eqIfPresent(DyPersonalLetterDO::getCardData, reqVO.getCardData())
                .eqIfPresent(DyPersonalLetterDO::getSource, reqVO.getSource())
                .eqIfPresent(DyPersonalLetterDO::getSceneType, reqVO.getSceneType())
                .eqIfPresent(DyPersonalLetterDO::getUserInfos, reqVO.getUserInfos())
                .betweenIfPresent(DyPersonalLetterDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(DyPersonalLetterDO::getId));
    }

    default List<DyPersonalLetterDO> selectList(DyPersonalLetterExportReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<DyPersonalLetterDO>()
                .eqIfPresent(DyPersonalLetterDO::getEvent, reqVO.getEvent())
                .eqIfPresent(DyPersonalLetterDO::getClientKey, reqVO.getClientKey())
                .eqIfPresent(DyPersonalLetterDO::getFromUserId, reqVO.getFromUserId())
                .eqIfPresent(DyPersonalLetterDO::getToUserId, reqVO.getToUserId())
                .eqIfPresent(DyPersonalLetterDO::getConversationShortId, reqVO.getConversationShortId())
                .eqIfPresent(DyPersonalLetterDO::getServerMessageId, reqVO.getServerMessageId())
                .eqIfPresent(DyPersonalLetterDO::getConversationType, reqVO.getConversationType())
                .betweenIfPresent(DyPersonalLetterDO::getMessageCreateTime, reqVO.getMessageCreateTime())
                .eqIfPresent(DyPersonalLetterDO::getMessageType, reqVO.getMessageType())
                .eqIfPresent(DyPersonalLetterDO::getText, reqVO.getText())
                .eqIfPresent(DyPersonalLetterDO::getResourceType, reqVO.getResourceType())
                .eqIfPresent(DyPersonalLetterDO::getResourceWidth, reqVO.getResourceWidth())
                .eqIfPresent(DyPersonalLetterDO::getResourceHeight, reqVO.getResourceHeight())
                .eqIfPresent(DyPersonalLetterDO::getResourceUrl, reqVO.getResourceUrl())
                .eqIfPresent(DyPersonalLetterDO::getItemId, reqVO.getItemId())
                .eqIfPresent(DyPersonalLetterDO::getCardId, reqVO.getCardId())
                .eqIfPresent(DyPersonalLetterDO::getCardStatus, reqVO.getCardStatus())
                .eqIfPresent(DyPersonalLetterDO::getCardData, reqVO.getCardData())
                .eqIfPresent(DyPersonalLetterDO::getSource, reqVO.getSource())
                .eqIfPresent(DyPersonalLetterDO::getSceneType, reqVO.getSceneType())
                .eqIfPresent(DyPersonalLetterDO::getUserInfos, reqVO.getUserInfos())
                .betweenIfPresent(DyPersonalLetterDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(DyPersonalLetterDO::getId));
    }

}
