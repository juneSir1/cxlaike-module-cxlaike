package net.hzxzkj.cxlaike.module.cxlaike.service.dycomment;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.extern.slf4j.Slf4j;
import net.hzxzkj.cxlaike.framework.common.exception.ErrorCode;
import net.hzxzkj.cxlaike.framework.common.util.date.LocalDateTimeUtils;
import net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.matrixtaskorder.MatrixTaskOrderDO;
import net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.memberdybind.MemberDyBindDO;
import net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.memberdytoken.MemberDyTokenDO;
import net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.taskordergather.TaskOrderGatherDO;
import net.hzxzkj.cxlaike.module.cxlaike.dal.mysql.matrixtaskorder.MatrixTaskOrderMapper;
import net.hzxzkj.cxlaike.module.cxlaike.dal.mysql.taskordergather.TaskOrderGatherMapper;
import net.hzxzkj.cxlaike.module.cxlaike.enums.douyin.DyUserTypeEnum;
import net.hzxzkj.cxlaike.module.cxlaike.enums.taskorder.VideoStatusEnum;
import net.hzxzkj.cxlaike.module.cxlaike.service.memberdybind.MemberDyBindService;
import net.hzxzkj.cxlaike.module.cxlaike.service.memberdytoken.MemberDyTokenService;
import net.hzxzkj.cxlaike.module.cxlaike.utils.douyin.DouYinApiUtils;
import net.hzxzkj.cxlaike.module.cxlaike.utils.douyin.vo.*;
import org.apache.commons.collections4.ListUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import java.util.*;
import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.dycomment.vo.*;
import net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.dycomment.DyCommentDO;
import net.hzxzkj.cxlaike.framework.common.pojo.PageResult;

import net.hzxzkj.cxlaike.module.cxlaike.convert.dycomment.DyCommentConvert;
import net.hzxzkj.cxlaike.module.cxlaike.dal.mysql.dycomment.DyCommentMapper;

import static net.hzxzkj.cxlaike.framework.common.exception.util.ServiceExceptionUtil.exception;
import static net.hzxzkj.cxlaike.module.cxlaike.enums.ErrorCodeConstants.*;

/**
 * 抖音评论管理 Service 实现类
 *
 * @author 宵征源码
 */
@Service
@Validated
@Slf4j
public class DyCommentServiceImpl implements DyCommentService {

    @Resource
    private DyCommentMapper dyCommentMapper;
    @Resource
    private DouYinApiUtils douYinApiUtils;
    @Resource
    private MemberDyTokenService memberDyTokenService;
    @Resource
    private MemberDyBindService memberDyBindService;
    @Resource
    private TaskOrderGatherMapper taskOrderGatherMapper;
    @Resource
    private MatrixTaskOrderMapper matrixTaskOrderMapper;

    @Override
    public void createDyComment(DyCommentCreateReqVO createReqVO) {
        MemberDyTokenDO memberDyTokenDO = memberDyTokenService.getById(createReqVO.getAccountBindId());
        if(memberDyTokenDO == null){
            throw exception(DATA_ERROR);
        }
        MemberDyBindDO memberDyBindDO = memberDyBindService.getById(createReqVO.getAccountBindId());
        if(memberDyBindDO == null){
            throw exception(DATA_ERROR);
        }
        CommentReplyRespDTO commentReplyRespDTO = douYinApiUtils.commentReply(memberDyTokenDO.getDyAccessToken()
                , memberDyBindDO.getDyOpenId(), createReqVO.getItemId(), null, createReqVO.getContent());
        if(commentReplyRespDTO == null){
            throw exception(COMMENT_REPLY_ERROR);
        }
        if(commentReplyRespDTO.getData().getError_code() != 0){
            throw exception(COMMENT_REPLY_ERROR);
        }
    }

    @Override
    public void updateDyComment(DyCommentUpdateReqVO updateReqVO) {
        // 校验存在
        validateDyCommentExists(updateReqVO.getId());
        // 更新
        DyCommentDO updateObj = DyCommentConvert.INSTANCE.convert(updateReqVO);
        dyCommentMapper.updateById(updateObj);
    }

    @Override
    public void deleteDyComment(Long id) {
        // 校验存在
        validateDyCommentExists(id);
        // 删除
        dyCommentMapper.deleteById(id);
    }

    private void validateDyCommentExists(Long id) {
        if (dyCommentMapper.selectById(id) == null) {
            throw exception(DATA_ERROR);
        }
    }

    @Override
    public DyCommentDO getDyComment(Long id) {
        return dyCommentMapper.selectById(id);
    }

    @Override
    public List<DyCommentDO> getDyCommentList(Collection<Long> ids) {
        return dyCommentMapper.selectBatchIds(ids);
    }

    @Override
    public PageResult<DyCommentDO> getDyCommentPage(DyCommentPageReqVO pageReqVO) {
        return dyCommentMapper.selectPage(pageReqVO);
    }

    @Override
    public List<DyCommentDO> getDyCommentList(DyCommentExportReqVO exportReqVO) {
        return dyCommentMapper.selectList(exportReqVO);
    }

    @Override
    public void commentTop(DyCommentTopReqVO topReqVO) {
        DyCommentDO dyCommentDO = dyCommentMapper.selectById(topReqVO.getId());
        if(dyCommentDO == null){
            throw exception(DATA_ERROR);
        }
        MemberDyTokenDO memberDyTokenDO = memberDyTokenService.getById(dyCommentDO.getAccountId());
        if(memberDyTokenDO == null){
            throw exception(DATA_ERROR);
        }
        MemberDyBindDO memberDyBindDO = memberDyBindService.getById(dyCommentDO.getAccountId());
        if(memberDyBindDO == null){
            throw exception(DATA_ERROR);
        }
        CommentTopRespDTO commentTopRespDTO = douYinApiUtils.commentTop(memberDyTokenDO.getDyAccessToken()
                , memberDyBindDO.getDyOpenId(), dyCommentDO.getItemId(), dyCommentDO.getCommentId(),topReqVO.getIsTop());
        if(commentTopRespDTO == null){
            throw exception(COMMENT_TOP_ERROR);
        }
        if(commentTopRespDTO.getData().getError_code() != 0){
            log.error("==========置顶失败========={}",commentTopRespDTO);
            throw exception(new ErrorCode(400,"置顶失败，只允许对作者的一级评论进行置顶"));
        }
//        DyCommentDO updateDO = new DyCommentDO();
//        updateDO.setTop(0);
//        dyCommentMapper.update(updateDO,new LambdaQueryWrapper<DyCommentDO>()
//                .eq(DyCommentDO::getItemId,dyCommentDO.getItemId()));
        dyCommentDO.setTop(topReqVO.getIsTop()?1:0);
        dyCommentMapper.updateById(dyCommentDO);
    }

    @Override
    public void commentReply(DyCommentReplyReqVO replyReqVO) {
        DyCommentDO dyCommentDO = dyCommentMapper.selectById(replyReqVO.getId());
        if(dyCommentDO == null){
            throw exception(DATA_ERROR);
        }
        MemberDyTokenDO memberDyTokenDO = memberDyTokenService.getById(dyCommentDO.getAccountId());
        if(memberDyTokenDO == null){
            throw exception(DATA_ERROR);
        }
        MemberDyBindDO memberDyBindDO = memberDyBindService.getById(dyCommentDO.getAccountId());
        if(memberDyBindDO == null){
            throw exception(DATA_ERROR);
        }
        CommentReplyRespDTO commentReplyRespDTO = douYinApiUtils.commentReply(memberDyTokenDO.getDyAccessToken()
                , memberDyBindDO.getDyOpenId(), dyCommentDO.getItemId(), dyCommentDO.getCommentId(), replyReqVO.getContent());
        if(commentReplyRespDTO == null){
            throw exception(COMMENT_REPLY_ERROR);
        }
        if(commentReplyRespDTO.getData().getError_code() != 0){
            throw exception(COMMENT_REPLY_ERROR);
        }

    }

    @Override
    public void pullCommentList(Long tenantId) {
        log.info("=====同步评论列表========================{}",tenantId);
        //只同步近两天的视频，非近两天的视频评论手工刷新
        List<TaskOrderGatherDO> taskOrderGatherDOS = taskOrderGatherMapper.selectList(new LambdaQueryWrapper<TaskOrderGatherDO>()
                .ge(TaskOrderGatherDO::getCreateTime,LocalDateTimeUtils.getMinusDaysTime(2))
                .eq(tenantId!=null,TaskOrderGatherDO::getTenantId,tenantId));
        List<DyCommentDO> insertDOS = new ArrayList<>();
        for(TaskOrderGatherDO taskOrderGatherDO : taskOrderGatherDOS){
            if(StringUtils.isBlank(taskOrderGatherDO.getItemId())){
                continue;
            }
            MemberDyTokenDO memberDyTokenDO = memberDyTokenService.getByIdWithOutTenantId(taskOrderGatherDO.getAccountBindId());
            if(memberDyTokenDO == null){
                continue;
            }
            MemberDyBindDO memberDyBindDO = memberDyBindService.getByIdWithOutTenantId(taskOrderGatherDO.getAccountBindId());
            if(memberDyBindDO == null){
                continue;
            }

            List<CommentList> commentList = new ArrayList<>();
            commentList = this.getCommentList(commentList,memberDyTokenDO.getDyAccessToken(),memberDyBindDO.getDyOpenId(),taskOrderGatherDO.getItemId(),0L,20);
            Map<String,String> commentMap = new HashMap<>();
            for(CommentList comment : commentList){
                if(StringUtils.isBlank(comment.getContent())){
                    continue;
                }
                DyCommentDO dyCommentDO = dyCommentMapper.selectOne(new LambdaQueryWrapper<DyCommentDO>()
                        .eq(DyCommentDO::getCommentId,comment.getComment_id()));
                if(dyCommentDO != null){
                    dyCommentDO.setReplyCommentTotal(comment.getReply_comment_total());
                    dyCommentDO.setDiggCount(comment.getDigg_count());
                    dyCommentDO.setAvatar(comment.getAvatar());
                    dyCommentDO.setNickName(comment.getNick_name());
                    dyCommentDO.setOwnAvatar(memberDyBindDO.getDyAvatar());
                    dyCommentDO.setOwnNickName(memberDyBindDO.getDyNickname());
                    dyCommentMapper.updateById(dyCommentDO);
                    continue;
                }
                dyCommentDO = new DyCommentDO();
                dyCommentDO.setCommentId(comment.getComment_id());
                dyCommentDO.setCommentUserId(comment.getComment_user_id());
                dyCommentDO.setContent(comment.getContent());
                dyCommentDO.setCommentCreateTime(LocalDateTimeUtils.getTimeByTimestamp(comment.getCreate_time()));
                dyCommentDO.setDiggCount(comment.getDigg_count());
                dyCommentDO.setReplyCommentTotal(comment.getReply_comment_total());
                dyCommentDO.setAvatar(comment.getAvatar());
                dyCommentDO.setNickName(comment.getNick_name());
                MatrixTaskOrderDO matrixTaskOrderDO = matrixTaskOrderMapper.selectById(taskOrderGatherDO.getOrderId());
                if(matrixTaskOrderDO != null){
                    dyCommentDO.setVideoLink(matrixTaskOrderDO.getVideoLink());
                }
                dyCommentDO.setItemId(taskOrderGatherDO.getItemId());
                dyCommentDO.setOwnAvatar(memberDyBindDO.getDyAvatar());
                dyCommentDO.setOwnNickName(memberDyBindDO.getDyNickname());
                dyCommentDO.setAccountId(taskOrderGatherDO.getAccountBindId());
                dyCommentDO.setTenantId(taskOrderGatherDO.getTenantId());
                if(commentMap.get(comment.getComment_id())!=null){
                    continue;
                }
                insertDOS.add(dyCommentDO);
                commentMap.put(comment.getComment_id(),comment.getComment_id());
            }
        }
        if(insertDOS.size() > 1000){
            ListUtils.partition(insertDOS,1000).forEach(p->dyCommentMapper.insertBatch(p));
        }else {
            dyCommentMapper.insertBatch(insertDOS);
        }

    }

    private List<CommentList> getCommentList(List<CommentList> commentList,String accessToken, String openId,String itemId,Long cursor,Integer count){
        CommentListRespDTO respDTO = douYinApiUtils.getCommentList(accessToken, openId, itemId, cursor, count);
//        log.info("=============返回评论数据===={}",respDTO);
        if(respDTO == null){
            log.info("【抖音评论数据刷新】失败————————————");
            return commentList;
        }
        if(respDTO.getData() == null){
            log.info("【抖音评论数据刷新】失败————————————");
            return commentList;
        }
        if(respDTO.getData().getError_code() != 0){
            log.info("【抖音评论数据刷新】失败————————————,message:{}",respDTO.getData().getDescription());
            return commentList;
        }
        List<CommentList> list = respDTO.getData().getList();
        if(CollectionUtil.isNotEmpty(list)){
            commentList.addAll(list);
        }
        if(respDTO.getData().getHas_more()){
            this.getCommentList(commentList,accessToken, openId, itemId, respDTO.getData().getCursor(), count);
        }
        return commentList;
    }

}
