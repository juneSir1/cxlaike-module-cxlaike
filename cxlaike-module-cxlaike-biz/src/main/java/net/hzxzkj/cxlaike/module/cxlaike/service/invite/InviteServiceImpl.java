package net.hzxzkj.cxlaike.module.cxlaike.service.invite;

import cn.binarywang.wx.miniapp.api.WxMaLinkService;
import cn.binarywang.wx.miniapp.api.WxMaQrcodeService;
import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.urllink.GenerateUrlLinkRequest;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.SneakyThrows;
import net.hzxzkj.cxlaike.framework.common.util.date.LocalDateTimeUtils;
import net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.task.TaskDO;
import net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.taskext.TaskExtDO;
import net.hzxzkj.cxlaike.module.cxlaike.dal.mysql.task.TaskMapper;
import net.hzxzkj.cxlaike.module.cxlaike.dal.mysql.taskext.TaskExtMapper;
import net.hzxzkj.cxlaike.module.cxlaike.enums.task.TaskTypeEnum;
import net.hzxzkj.cxlaike.module.cxlaike.utils.GraphUtil;
import net.hzxzkj.cxlaike.module.cxlaike.utils.invite.config.InviteProperties;
import net.hzxzkj.cxlaike.module.cxlaike.utils.wx.config.WxMaProperties;
import net.hzxzkj.cxlaike.module.infra.api.file.FileApi;
import net.hzxzkj.cxlaike.module.member.api.user.MemberUserApi;
import net.hzxzkj.cxlaike.module.member.api.user.dto.MemberUserExtRespDTO;
import net.hzxzkj.cxlaike.module.merchant.api.user.MerchantUserApi;
import net.hzxzkj.cxlaike.module.merchant.api.user.dto.MerchantUserExtRespDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static net.hzxzkj.cxlaike.framework.common.exception.util.ServiceExceptionUtil.exception;
import static net.hzxzkj.cxlaike.module.cxlaike.enums.ErrorCodeConstants.*;

/**
 * @author jianan.han
 * @date 2023/9/22 下午3:11
 * @description
 */
@Service
public class InviteServiceImpl implements InviteService {

    @Resource
    private FileApi fileApi;
    @Value("${placard.personage.file.id}")
    private Long placardFileId;
    @Value("${placard.task.file.id}")
    private Long placardTaskFileId;
    @Resource
    private InviteProperties inviteProperties;

    @Resource
    private MemberUserApi memberUserApi;
    @Resource
    private WxMaService wxMaService;
    @Resource
    private WxMaProperties wxMaProperties;
    @Resource
    private TaskMapper taskMapper;
    @Resource
    private TaskExtMapper taskExtMapper;
    @Resource
    private MerchantUserApi merchantUserApi;

    @Override
    public byte[] getPlacard(Integer type, Long userId) throws Exception {
        MemberUserExtRespDTO extRespDTO = memberUserApi.getUserExtById(userId);
        if (extRespDTO == null) {
            throw exception(DATA_ERROR);
        }
        byte[] placard = fileApi.getPlacard(placardFileId);
        return GraphUtil.generatePlacard(extRespDTO.getInvitationCode(), inviteProperties.getSkipUrls().get(type), placard);
    }

    @SneakyThrows
    @Override
    public byte[] getMemberPlacard(Integer type, Long userId, Long taskId) {
        MemberUserExtRespDTO extRespDTO = memberUserApi.getUserExtById(userId);
        if (extRespDTO == null) {
            throw exception(DATA_ERROR);
        }
        // 生成二维码
        String scene = "invitationCode=" + extRespDTO.getInvitationCode();
        if (type == 2) {
            scene = "i=" + taskId + "&c=" + extRespDTO.getInvitationCode();
        }
        WxMaQrcodeService qrcodeService = wxMaService.getQrcodeService();
        byte[] wxaCodeUnlimitBytes = qrcodeService.createWxaCodeUnlimitBytes(scene, inviteProperties.getSkipUrls().get(type), false, wxMaProperties.getEnvVersion(), 240, true, null, false);

        if (wxaCodeUnlimitBytes == null) {
            throw exception(INVITE_APPLET_ERROR);
        }
        //个人邀请
        if (type == 1) {
            byte[] placard = fileApi.getPlacard(placardFileId);//海报图
            if (placard == null) {
                throw exception(INVITE_BACKDROP_ERROR);
            }
            return GraphUtil.generatePlacard(placard, wxaCodeUnlimitBytes, 480, 1600);

        } else if (type == 2) { //任务邀请
            byte[] placard = fileApi.getPlacard(placardTaskFileId);//海报图
            if (placard == null) {
                throw exception(INVITE_BACKDROP_ERROR);
            }

            TaskDO taskDO = taskMapper.selectById(taskId);
            if (taskDO == null) {
                throw exception(TASK_NOT_EXISTS);
            }

            MerchantUserExtRespDTO userExtRespDTO = merchantUserApi.getUserExtById(taskDO.getMerchantUserId());
            if (userExtRespDTO == null) {
                throw exception(DATA_ERROR);
            }
            List<TaskExtDO> taskExtDOS = taskExtMapper.selectList(new LambdaQueryWrapper<TaskExtDO>()
                    .eq(TaskExtDO::getTaskId, taskId)
                    .orderByDesc(TaskExtDO::getMemberFee));

            LocalDateTime startTime = taskDO.getStartTime();
            LocalDateTime endTime = taskDO.getEndTime();

            String taskDay = LocalDateTimeUtils.format(startTime, "MM/dd") + "--" + LocalDateTimeUtils.format(endTime, "MM/dd");
            String taskYear = String.valueOf(startTime.getYear());
            String taskTime = LocalDateTimeUtils.format(startTime, "HH:mm") + "--" + LocalDateTimeUtils.format(endTime, "HH:mm");
            String taskTitle = taskDO.getTitle();
            if (taskTitle.length() > 10) {
                taskTitle = taskTitle.substring(0, 10) + "...";
            }
            String taskTypeStr = TaskTypeEnum.getEnumByCode(taskDO.getTaskType()).getContent();


            String taskAmountStr = "¥" + new BigDecimal(taskExtDOS.get(0).getMemberFee()).divide(new BigDecimal(100)).setScale(2, BigDecimal.ROUND_HALF_UP);
            String taskProfession = userExtRespDTO.getProfessionTwo() + "." + userExtRespDTO.getProfessionThree();
            //邀请函
            byte[] generateInvitation = GraphUtil.generateInvitation(placard, taskDay, taskYear, taskTime,
                    taskTitle, taskTypeStr, taskAmountStr, taskProfession);
            return GraphUtil.generatePlacard(generateInvitation, wxaCodeUnlimitBytes, 835, 1680);
        } else {
            return null;
        }


    }


    @SneakyThrows
    @Override
    public String generateUrlLink(Long taskId, Long userId) {
        MemberUserExtRespDTO extRespDTO = memberUserApi.getUserExtById(userId);
        if (extRespDTO == null) {
            throw exception(DATA_ERROR);
        }
        // String pageUrl = String.format(inviteProperties.getSkipUrls().get(3),taskId,extRespDTO.getInvitationCode());
        WxMaLinkService linkService = wxMaService.getLinkService();
        GenerateUrlLinkRequest request = new GenerateUrlLinkRequest();
        request.setPath(inviteProperties.getSkipUrls().get(3));
        String query = "id=" + taskId + "&invitationCode=" + extRespDTO.getInvitationCode();
        request.setQuery(query);
        request.setEnvVersion(wxMaProperties.getEnvVersion());
        request.setIsExpire(true);
        request.setExpireType(1);
        request.setExpireInterval(1);
//        GenerateShortLinkRequest request = GenerateShortLinkRequest.builder().isPermanent(false).pageTitle(pageTitle).pageUrl(pageUrl).build();
        return linkService.generateUrlLink(request);
    }

    @Override
    public String getSjInviteLink(Long userId) {
        MemberUserExtRespDTO extRespDTO = memberUserApi.getUserExtById(userId);
        if (extRespDTO == null) {
            throw exception(DATA_ERROR);
        }
        return String.format(inviteProperties.getSkipUrls().get(4), extRespDTO.getInvitationCode());
    }
}
