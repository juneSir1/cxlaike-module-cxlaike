package net.hzxzkj.cxlaike.module.cxlaike.dal.mysql.dycomment;

import java.util.*;

import net.hzxzkj.cxlaike.framework.common.pojo.PageResult;
import net.hzxzkj.cxlaike.framework.mybatis.core.query.LambdaQueryWrapperX;
import net.hzxzkj.cxlaike.framework.mybatis.core.mapper.BaseMapperX;
import net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.dycomment.DyCommentDO;
import org.apache.ibatis.annotations.Mapper;
import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.dycomment.vo.*;

/**
 * 抖音评论管理 Mapper
 *
 * @author 宵征源码
 */
@Mapper
public interface DyCommentMapper extends BaseMapperX<DyCommentDO> {

    default PageResult<DyCommentDO> selectPage(DyCommentPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<DyCommentDO>()
                .eqIfPresent(DyCommentDO::getTop, reqVO.getTop())
                .likeIfPresent(DyCommentDO::getNickName, reqVO.getNickName())
                .orderByDesc(DyCommentDO::getId));
    }

    default List<DyCommentDO> selectList(DyCommentExportReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<DyCommentDO>()
                .eqIfPresent(DyCommentDO::getCommentId, reqVO.getCommentId())
                .eqIfPresent(DyCommentDO::getCommentUserId, reqVO.getCommentUserId())
                .eqIfPresent(DyCommentDO::getContent, reqVO.getContent())
                .betweenIfPresent(DyCommentDO::getCommentCreateTime, reqVO.getCommentCreateTime())
                .eqIfPresent(DyCommentDO::getDiggCount, reqVO.getDiggCount())
                .eqIfPresent(DyCommentDO::getReplyCommentTotal, reqVO.getReplyCommentTotal())
                .eqIfPresent(DyCommentDO::getTop, reqVO.getTop())
                .eqIfPresent(DyCommentDO::getAvatar, reqVO.getAvatar())
                .likeIfPresent(DyCommentDO::getNickName, reqVO.getNickName())
                .eqIfPresent(DyCommentDO::getVideoLink, reqVO.getVideoLink())
                .eqIfPresent(DyCommentDO::getOwnAvatar, reqVO.getOwnAvatar())
                .likeIfPresent(DyCommentDO::getOwnNickName, reqVO.getOwnNickName())
                .betweenIfPresent(DyCommentDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(DyCommentDO::getId));
    }

}
