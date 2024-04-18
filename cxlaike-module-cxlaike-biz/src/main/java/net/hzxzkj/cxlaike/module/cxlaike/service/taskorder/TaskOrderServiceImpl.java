package net.hzxzkj.cxlaike.module.cxlaike.service.taskorder;

import static net.hzxzkj.cxlaike.framework.common.exception.util.ServiceExceptionUtil.exception;
import static net.hzxzkj.cxlaike.framework.common.util.servlet.ServletUtils.getClientIP;
import static net.hzxzkj.cxlaike.framework.tenant.core.context.TenantContextHolder.getTenantId;
import static net.hzxzkj.cxlaike.module.cxlaike.enums.ErrorCodeConstants.*;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.io.FileTypeUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.aliyun.avatar20220130.models.GetVideoTaskInfoResponse;
import com.aliyun.avatar20220130.models.GetVideoTaskInfoResponseBody.GetVideoTaskInfoResponseBodyData;
import com.aliyun.avatar20220130.models.SubmitTextTo2DAvatarVideoTaskRequest;
import com.aliyun.avatar20220130.models.SubmitTextTo2DAvatarVideoTaskRequest.SubmitTextTo2DAvatarVideoTaskRequestAudioInfo;
import com.aliyun.avatar20220130.models.SubmitTextTo2DAvatarVideoTaskRequest.SubmitTextTo2DAvatarVideoTaskRequestAvatarInfo;
import com.aliyun.avatar20220130.models.SubmitTextTo2DAvatarVideoTaskRequest.SubmitTextTo2DAvatarVideoTaskRequestVideoInfo;
import com.aliyun.avatar20220130.models.SubmitTextTo2DAvatarVideoTaskResponse;
import com.aliyun.ice20201109.models.GetMediaProducingJobResponse;
import com.aliyun.ice20201109.models.GetMediaProducingJobResponseBody.GetMediaProducingJobResponseBodyMediaProducingJob;
import com.aliyun.ice20201109.models.SubmitMediaProducingJobRequest;
import com.aliyun.ice20201109.models.SubmitMediaProducingJobResponse;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.common.collect.Lists;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.TemporalAdjusters;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import net.hzxzkj.cxlaike.framework.common.enums.UserTypeEnum;
import net.hzxzkj.cxlaike.framework.common.exception.ErrorCode;
import net.hzxzkj.cxlaike.framework.common.pojo.PageResult;
import net.hzxzkj.cxlaike.framework.common.util.date.DateUtils;
import net.hzxzkj.cxlaike.framework.common.util.date.LocalDateTimeUtils;
import net.hzxzkj.cxlaike.framework.common.util.http.Http2Utils;
import net.hzxzkj.cxlaike.framework.common.util.json.JsonUtils;
import net.hzxzkj.cxlaike.framework.ice.core.client.IceService;
import net.hzxzkj.cxlaike.framework.ice.core.client.VideoService;
import net.hzxzkj.cxlaike.framework.ice.core.entity.timeline.Timeline;
import net.hzxzkj.cxlaike.framework.ice.core.entity.timeline.video.VideoTrackClip;
import net.hzxzkj.cxlaike.framework.ice.core.property.IceProperties;
import net.hzxzkj.cxlaike.framework.mybatis.core.query.LambdaQueryWrapperX;
import net.hzxzkj.cxlaike.framework.security.core.util.SecurityFrameworkUtils;
import net.hzxzkj.cxlaike.framework.tenant.core.aop.TenantIgnore;
import net.hzxzkj.cxlaike.module.cxlaike.controller.admin.walletorderwithdraw.vo.TransferDetailReqVO;
import net.hzxzkj.cxlaike.module.cxlaike.controller.app.taskorder.vo.*;
import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.taskorder.vo.*;
import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.taskordergather.vo.TaskOrderGatherCreateReqVO;
import net.hzxzkj.cxlaike.module.cxlaike.convert.taskmaterial.TaskMaterialConvert;
import net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.aisystemarg.AiSystemArgDO;
import net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.aivideoresult.AiVideoResultDO;
import net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.aivideotask.AiVideoTaskDO;
import net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.aivideotaskmaterial.AiVideoTaskMaterialDO;
import net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.copymanagement.CopyManagementDO;
import net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.memberdybind.MemberDyBindDO;
import net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.metahumanconfig.MetaHumanConfigDO;
import net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.systemarg.SystemArgDO;
import net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.task.TaskDO;
import net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.taskapply.TaskApplyDO;
import net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.taskext.TaskExtDO;
import net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.taskmaterial.TaskMaterialDO;
import net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.taskmaterialdownload.TaskMaterialDownloadDO;
import net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.taskmaterialslice.TaskMaterialSliceDO;
import net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.taskorder.TaskOrderDO;
import net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.videoslice.VideoSliceDO;
import net.hzxzkj.cxlaike.module.cxlaike.dal.mysql.copymanagement.CopyManagementMapper;
import net.hzxzkj.cxlaike.module.cxlaike.dal.mysql.task.TaskMapper;
import net.hzxzkj.cxlaike.module.cxlaike.dal.mysql.taskapply.TaskApplyMapper;
import net.hzxzkj.cxlaike.module.cxlaike.dal.mysql.taskext.TaskExtMapper;
import net.hzxzkj.cxlaike.module.cxlaike.dal.mysql.taskmaterial.TaskMaterialMapper;
import net.hzxzkj.cxlaike.module.cxlaike.dal.mysql.taskmaterialdownload.TaskMaterialDownloadMapper;
import net.hzxzkj.cxlaike.module.cxlaike.dal.mysql.taskmaterialslice.TaskMaterialSliceMapper;
import net.hzxzkj.cxlaike.module.cxlaike.dal.mysql.taskorder.TaskOrderMapper;
import net.hzxzkj.cxlaike.module.cxlaike.dal.mysql.videoslice.VideoSliceMapper;
import net.hzxzkj.cxlaike.module.cxlaike.enums.ActiveTaskType;
import net.hzxzkj.cxlaike.module.cxlaike.enums.AiClipStatus;
import net.hzxzkj.cxlaike.module.cxlaike.enums.AiClipType;
import net.hzxzkj.cxlaike.module.cxlaike.enums.AiSpeechRateAndPitchRateType;
import net.hzxzkj.cxlaike.module.cxlaike.enums.AiSystemArgType;
import net.hzxzkj.cxlaike.module.cxlaike.enums.CopywritingType;
import net.hzxzkj.cxlaike.module.cxlaike.enums.SubtitleType;
import net.hzxzkj.cxlaike.module.cxlaike.enums.TaskApplyStatus;
import net.hzxzkj.cxlaike.module.cxlaike.enums.TaskMaterialFileStatus;
import net.hzxzkj.cxlaike.module.cxlaike.enums.TaskMaterialFileType;
import net.hzxzkj.cxlaike.module.cxlaike.enums.TaskMaterialSliceStatus;
import net.hzxzkj.cxlaike.module.cxlaike.enums.TaskStatus;
import net.hzxzkj.cxlaike.module.cxlaike.enums.TaskType;
import net.hzxzkj.cxlaike.module.cxlaike.enums.UploadFileTaskType;
import net.hzxzkj.cxlaike.module.cxlaike.enums.VideoClipOrderStatus;
import net.hzxzkj.cxlaike.module.cxlaike.enums.VideoType;
import net.hzxzkj.cxlaike.module.cxlaike.enums.douyin.DyUserTypeEnum;
import net.hzxzkj.cxlaike.module.cxlaike.enums.task.TaskTypeEnum;
import net.hzxzkj.cxlaike.module.cxlaike.enums.taskorder.CancelTypeEnum;
import net.hzxzkj.cxlaike.module.cxlaike.enums.taskorder.OrderStatusEnum;
import net.hzxzkj.cxlaike.module.cxlaike.enums.taskorder.SettleStatusEnum;
import net.hzxzkj.cxlaike.module.cxlaike.enums.taskorder.ShowTypeEnum;
import net.hzxzkj.cxlaike.module.cxlaike.enums.template.TemplateTypeEnum;
import net.hzxzkj.cxlaike.module.cxlaike.redis.order.OrderLockRedisDAO;
import net.hzxzkj.cxlaike.module.cxlaike.service.aisystemarg.AiSystemArgService;
import net.hzxzkj.cxlaike.module.cxlaike.service.material.MaterialService;
import net.hzxzkj.cxlaike.module.cxlaike.service.membercorrelation.MemberCorrelationService;
import net.hzxzkj.cxlaike.module.cxlaike.service.memberdybind.MemberDyBindService;
import net.hzxzkj.cxlaike.module.cxlaike.service.metahumanconfig.MetaHumanConfigService;
import net.hzxzkj.cxlaike.module.cxlaike.service.systemarg.SystemArgService;
import net.hzxzkj.cxlaike.module.cxlaike.service.taskordergather.TaskOrderGatherService;
import net.hzxzkj.cxlaike.module.cxlaike.service.template.VideoSynthesisService;
import net.hzxzkj.cxlaike.module.cxlaike.service.template.dto.VideoSynthesisDTO;
import net.hzxzkj.cxlaike.module.cxlaike.service.video.VideoClipHandlerService;
import net.hzxzkj.cxlaike.module.cxlaike.service.video.dto.AiVideoClip;
import net.hzxzkj.cxlaike.module.cxlaike.service.video.dto.MetaHumanSecondVideoClip;
import net.hzxzkj.cxlaike.module.cxlaike.service.video.dto.MetaHumanVideoClip;
import net.hzxzkj.cxlaike.module.cxlaike.service.video.dto.VideoClipOrderDTO;
import net.hzxzkj.cxlaike.module.cxlaike.utils.douyin.FileUtils;
import net.hzxzkj.cxlaike.module.cxlaike.utils.douyin.VideoSlicer;
import net.hzxzkj.cxlaike.module.infra.api.file.dto.MaterialRespVO;
import net.hzxzkj.cxlaike.module.infra.enums.MaterialTypeEnum;
import net.hzxzkj.cxlaike.module.member.api.user.MemberUserApi;
import net.hzxzkj.cxlaike.module.member.api.user.dto.MemberUserExtRespDTO;
import net.hzxzkj.cxlaike.module.member.api.user.dto.MemberUserExtVO;
import net.hzxzkj.cxlaike.module.member.api.user.dto.MemberUserRespDTO;
import net.hzxzkj.cxlaike.module.member.api.user.dto.UserInviteDTO;
import net.hzxzkj.cxlaike.module.merchant.api.user.MerchantUserApi;
import net.hzxzkj.cxlaike.module.merchant.api.user.dto.MerchantUserExtRespDTO;
import net.hzxzkj.cxlaike.module.notify.client.TaskClient;
import net.hzxzkj.cxlaike.module.notify.enums.TaskHandlerEnum;
import net.hzxzkj.cxlaike.module.pay.api.refund.dto.SignUpRefundHandlerReqVO;
import net.hzxzkj.cxlaike.module.pay.api.wallet.WalletApi;
import net.hzxzkj.cxlaike.module.pay.api.walletorder.dto.UpMemberCashOrderDTO;
import net.hzxzkj.cxlaike.module.pay.api.walletorder.WalletOrderApi;
import net.hzxzkj.cxlaike.module.pay.api.walletorder.dto.WalletOrderDTO;
import net.hzxzkj.cxlaike.module.pay.api.walletordertaskaward.WalletOrderTaskAwardApi;
import net.hzxzkj.cxlaike.module.pay.api.walletordertaskaward.dto.WalletOrderTaskAwardDTO;
import net.hzxzkj.cxlaike.module.pay.enums.task.TaskAwardStatusEnum;
import net.hzxzkj.cxlaike.module.pay.enums.task.TaskAwardTypeEnum;
import net.hzxzkj.cxlaike.module.pay.enums.wallet.WalletLogTypeEnum;
import net.hzxzkj.cxlaike.module.pay.enums.wallet.WalletTypeEnum;
import net.hzxzkj.cxlaike.module.pay.enums.wallet.order.WalletOrderStatusEnum;
import net.hzxzkj.cxlaike.module.pay.enums.wallet.order.WalletOrderTypeEnum;
import net.hzxzkj.cxlaike.module.system.api.sms.SmsSendApi;
import net.hzxzkj.cxlaike.module.system.api.sms.dto.send.SmsSendSingleToUserReqDTO;
import net.hzxzkj.cxlaike.module.system.enums.permission.SystemCodeEnum;
import net.hzxzkj.cxlaike.module.system.enums.sms.SmsSceneEnum;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

/**
 * 达人任务订单 Service 实现类
 *
 * @author 宵征源码
 */
@Service
@Validated
@Slf4j
public class TaskOrderServiceImpl implements TaskOrderService {

  @Resource
  private TaskOrderMapper taskOrderMapper;
  @Resource
  private TaskExtMapper taskExtMapper;
  @Resource
  private WalletApi walletApi;
  @Resource
  private TaskMapper taskMapper;
  @Resource
  private CopyManagementMapper copyManagementMapper;
  @Resource
  @Lazy
  private TaskClient taskClient;
  @Resource
  @Lazy
  private MemberUserApi memberUserApi;
  @Resource
  private SystemArgService systemArgService;
  @Resource
  private TaskApplyMapper taskApplyMapper;
  @Resource
  private WalletOrderApi walletOrderApi;
  @Resource
  private MetaHumanConfigService metaHumanConfigService;
  @Resource
  private VideoService videoService;
  @Resource
  private TaskMaterialMapper taskMaterialMapper;
  @Resource
  private TaskMaterialSliceMapper taskMaterialSliceMapper;
  @Resource
  private IceService iceService;
  @Resource
  private IceProperties iceProperties;
  @Resource
  private MaterialService materialService;
  @Resource
  private List<VideoSynthesisService> videoSynthesisServices;
  @Resource
  private TaskMaterialDownloadMapper taskMaterialDownloadMapper;
  @Resource
  private WalletOrderTaskAwardApi walletOrderTaskAwardApi;
  @Resource
  @Lazy
  private MerchantUserApi merchantUserApi;
  @Resource
  private SmsSendApi smsSendApi;
  @Resource
  private AiSystemArgService aiSystemArgService;
  @Resource
  private OrderLockRedisDAO orderLockRedisDAO;
  @Resource
  private VideoSliceMapper videoSliceMapper;
  @Resource
  private TaskOrderGatherService taskOrderGatherService;
  @Resource
  private MemberDyBindService memberDyBindService;

  private final VideoClipHandlerService metaHumanVideoClipService;
  private final VideoClipHandlerService aiVideoClipService;
  @Resource
  private MemberCorrelationService memberCorrelationService;

  public TaskOrderServiceImpl(
      @Qualifier("metaHumanVideoClipService") VideoClipHandlerService metaHumanVideoClipService,
      @Qualifier("aiVideoClipService") VideoClipHandlerService aiVideoClipService) {
    this.metaHumanVideoClipService = metaHumanVideoClipService;
    this.aiVideoClipService = aiVideoClipService;
  }

  @Override
  @CacheEvict(cacheNames = "user:month:cancel:count", key = "#memberId+':'+#month")
  public void cancelOrder(Long id, Long memberId, String month) {
    TaskOrderDO taskOrderDO = taskOrderMapper.selectById(id);
    if (taskOrderDO == null) {
      throw exception(DATA_ERROR);
    }
    // 1.判断是否本人
    if (!taskOrderDO.getMemberUserId().equals(memberId)) {
      throw exception(ORDER_AUTHORITY_ERROR);
    }
    // 2.判断订单状态
    if (!Arrays.asList(OrderStatusEnum.UNDERWAY.getCode(), OrderStatusEnum.TURN_DOWN.getCode())
        .contains(taskOrderDO.getStatus())) {
      throw exception(ORDER_STATUS_ERROR);
    }
    taskOrderDO.setStatus(OrderStatusEnum.CANCEL.getCode());
    taskOrderDO.setCancelType(CancelTypeEnum.MEMBER.getCode());
    taskOrderMapper.updateById(taskOrderDO);
    // 增加任务数量
    taskExtMapper.updateSurplusNumOfAdd(taskOrderDO.getTaskExtId());
  }

//    @Override
//    public void turnDownOrder(TaskOrderTurnDownReqVO reqVO) {
//        TaskOrderDO taskOrderDO = taskOrderMapper.selectById(reqVO.getId());
//        if(taskOrderDO == null){
//            throw exception(DATA_ERROR);
//        }
//        if(!OrderStatusEnum.UNDERWAY.getCode().equals(taskOrderDO.getStatus())){
//            throw exception(ORDER_STATUS_ERROR);
//        }
//        taskOrderDO.setStatus(OrderStatusEnum.TURN_DOWN.getCode());
//        taskOrderDO.setRejectReason(reqVO.getRejectReason());
//        taskOrderMapper.updateById(taskOrderDO);
//        //TODO 剩余发布时间
//    }

  @Override
  public void backFillVideoLink(TaskOrderBackFillReqVO reqVO) {
    TaskOrderDO taskOrderDO = taskOrderMapper.selectById(reqVO.getId());
    if (taskOrderDO == null) {
      throw exception(DATA_ERROR);
    }
    if (!Arrays.asList(OrderStatusEnum.UNDERWAY.getCode(), OrderStatusEnum.TURN_DOWN.getCode())
        .contains(taskOrderDO.getStatus())) {
      throw exception(ORDER_STATUS_ERROR);
    }
    taskOrderDO.setStatus(OrderStatusEnum.TO_BE_REVIEWED.getCode());
    taskOrderDO.setAuditInvalidTime(LocalDateTime.now().plusDays(1));
    Pattern pattern = Pattern.compile("(http|https)://[\\w.-]+(/[\\w-./?%&=]*)?");
    Matcher matcher = pattern.matcher(reqVO.getVideoLink());
    String code = "";
    String url = "";
    while (matcher.find()) {
      url = matcher.group();
      Map<String, String> headers = new HashMap<>();
      try {
        HttpResponse response = Http2Utils.doGet(url, headers);
        //获取response的body
        Header url1 = response.getAllHeaders()[4];
        code = url1.getValue().split("/")[5];
      } catch (Exception e) {
        e.printStackTrace();
        throw exception(ORDER_VIDEO_LINK);
      }
    }
    taskOrderDO.setVideoVideoId(code);
    if (StringUtils.isBlank(url)) {
      throw exception(ORDER_VIDEO_LINK);
    }
    taskOrderDO.setVideoLink(url);
    taskOrderMapper.updateById(taskOrderDO);

//        TaskDO taskDO = taskMapper.selectById(taskOrderDO.getTaskId());
//        if(taskDO == null){
//            throw exception(DATA_ERROR);
//        }

    LocalDateTime time = LocalDateTime.now().plusHours(24);
//        if(taskDO.getEndTime().isBefore(time)){
//            time = taskDO.getEndTime();
//        }
    // 24小时之后商家未审核则自动通过
    JSONObject param = new JSONObject();
    param.set("id", reqVO.getId());
    param.set("memberId", taskOrderDO.getMemberUserId());
    taskClient.createTask(TaskHandlerEnum.AUDIT_ORDER.getValue(), time, param.toString());
  }


  @Override
  public PageResult<TaskOrderAuditListRespVO> getListForAudit(TaskOrderAuditListReqVO reqVO) {
    Page<TaskOrderAuditListRespVO> page = new Page<>(reqVO.getPageNo(), reqVO.getPageSize());
    List<TaskOrderAuditListRespVO> respVOS = taskOrderMapper.selectListForAudit(page, reqVO);
    respVOS.forEach(a->{
      MemberDyBindDO memberDyBindDO = memberDyBindService.getByOpenIdAndMemberId(a.getDyOpenId(),a.getMemberUserId());
      if(memberDyBindDO != null){
        a.setDyAvatar(memberDyBindDO.getDyAvatar());
        a.setDyNickname(memberDyBindDO.getDyNickname());
        a.setDyUserId(memberDyBindDO.getDyUserId());
        a.setDyGrade(memberDyBindDO.getDyGrade());
      }
    });


    return new PageResult<>(respVOS, page.getTotal());
  }

  @Override
  public TaskOrderAuditListCountRespVO getCountForAudit() {
    TaskOrderAuditListCountRespVO respVO = new TaskOrderAuditListCountRespVO();
    Long totalCount = taskOrderMapper.selectCountForAudit(null);
    Long toAuditCount = taskOrderMapper
        .selectCountForAudit(OrderStatusEnum.TO_BE_REVIEWED.getCode());
    Long auditCount = taskOrderMapper.selectCountForAudit(OrderStatusEnum.PASS.getCode());
    Long unAuditCount = taskOrderMapper
        .selectCountForAudit(OrderStatusEnum.TURN_DOWN.getCode());
    respVO.setTotalCount(totalCount);
    respVO.setAuditCount(auditCount);
    respVO.setToAuditCount(toAuditCount);
    respVO.setUnAuditCount(unAuditCount);
    return respVO;
  }

  @Override
  @CacheEvict(cacheNames = {"user:task:count:total",
      "user:task:count:month", "user:task:earning:predict"}, key = "#memberId+':'+#date")
  @Transactional(rollbackFor = Exception.class)
  public void auditOrder(TaskOrderAuditReqVO reqVO, Long memberId, LocalDate date,
      TaskOrderDO taskOrderDO) {
    if (!OrderStatusEnum.TO_BE_REVIEWED.getCode().equals(taskOrderDO.getStatus())) {
      throw exception(ORDER_STATUS_ERROR);
    }
    //驳回流程
    if (OrderStatusEnum.TURN_DOWN.getCode().equals(reqVO.getStatus())) {
      LocalDateTime taskInvalidTime = LocalDateTime.now().plusDays(1);//发布截止时间

      LocalDateTime time = LocalDateTime.now().plusDays(1);//handler时间

      String templateCode = SmsSceneEnum.YT_TASK_ORDER_TURN_DOWN.getTemplateCode();
      TaskDO taskDO = taskMapper.selectById(taskOrderDO.getTaskId());
      if (taskDO == null) {
        throw exception(new ErrorCode(400, "任务不存在!"));
      }
      if (TaskTypeEnum.ST.getCode().equals(taskDO.getTaskType())) {
        taskInvalidTime = LocalDateTime.now().plusDays(2);
        time = LocalDateTime.now().plusDays(2);
        templateCode = SmsSceneEnum.ST_TASK_ORDER_TURN_DOWN.getTemplateCode();
      }
      taskOrderDO.setTaskInvalidTime(taskInvalidTime);
      // 通过消息去校验用户是否发布视频(24小时或者任务截止时间)

      Integer cancelType = CancelTypeEnum.MEMBER.getCode();

      CancelOrderHandlerReqVO handlerReqVO = new CancelOrderHandlerReqVO();
      handlerReqVO.setId(taskOrderDO.getId());
      handlerReqVO.setCancelType(cancelType);
      handlerReqVO.setMemberId(taskOrderDO.getMemberUserId());
      taskClient.createTask(TaskHandlerEnum.CANCEL_ORDER.getValue(), time,
          JsonUtils.toJsonString(handlerReqVO));

      //发送驳回短信
      this.sendSmsForTurnDown(templateCode, taskDO.getTitle(), taskOrderDO.getMemberUserId());
    } else {
      taskOrderDO.setSettleStatus(SettleStatusEnum.TO_BE_SETTLE.getCode());
    }
    taskOrderDO.setStatus(reqVO.getStatus());
    taskOrderDO.setAuditInvalidTime(LocalDateTime.now());
    taskOrderDO.setRejectReason(reqVO.getRejectReason());
    taskOrderMapper.updateById(taskOrderDO);
    //审核通过之后流程
    if (OrderStatusEnum.PASS.getCode().equals(reqVO.getStatus())) {
      // 24小时微信退报名费
      SignUpRefundHandlerReqVO refundHandlerReqVO = new SignUpRefundHandlerReqVO();
      refundHandlerReqVO.setId(taskOrderDO.getPayOrderId());
      refundHandlerReqVO.setUserIp(getClientIP());
      taskClient
          .createTask(TaskHandlerEnum.SIGN_UP_REFUND.getValue(), LocalDateTime.now().plusHours(24),
              JsonUtils.toJsonString(refundHandlerReqVO));

      // 48小时后开启结算流程
      SettleOrderHandlerReqVO handlerReqVO = new SettleOrderHandlerReqVO();
      handlerReqVO.setId(taskOrderDO.getId());
      handlerReqVO.setMemberId(taskOrderDO.getMemberUserId());

      taskClient
          .createTask(TaskHandlerEnum.SETTLE_ORDER.getValue(), LocalDateTime.now().plusHours(48),
              JsonUtils.toJsonString(handlerReqVO));
      //视频数据集合和商家与抖音账号的关联关系
      this.createOrderGatherAndCorrelation(taskOrderDO);

    }
  }

  private void createOrderGatherAndCorrelation(TaskOrderDO taskOrderDO){
    MemberDyBindDO memberDyBindDO = memberDyBindService.getByOpenIdAndMemberId(taskOrderDO.getDyOpenId(), taskOrderDO.getMemberUserId());
    TaskOrderGatherCreateReqVO reqVO = new TaskOrderGatherCreateReqVO();
    reqVO.setAccountBindId(memberDyBindDO.getId());
    reqVO.setOrderId(taskOrderDO.getId());
    reqVO.setOrderType(DyUserTypeEnum.DR.getCode());
    reqVO.setTaskId(taskOrderDO.getTaskId());
    reqVO.setVideoId(taskOrderDO.getVideoVideoId());
    taskOrderGatherService.createGather(reqVO);
    memberCorrelationService.createCorrelation(getTenantId(),memberDyBindDO.getId());

  }

  private void sendSmsForTurnDown(String templateCode, String title, Long memberId) {
    MemberUserRespDTO memberUserRespDTO = memberUserApi.getUser(memberId);
    SmsSendSingleToUserReqDTO reqDTO = new SmsSendSingleToUserReqDTO();
    reqDTO.setUserId(memberUserRespDTO.getId());
    reqDTO.setMobile(memberUserRespDTO.getMobile());
    reqDTO.setTemplateCode(templateCode);
    Map<String, Object> params = new HashMap<>();
    params.put("taskname", title);
    reqDTO.setTemplateParams(params);
    smsSendApi.sendSingleSmsToMember(reqDTO);

  }

  @Override
  public PageResult<TaskOrderDetailListRespVO> getListForDetail(TaskOrderDetailListReqVO reqVO) {
    Page<TaskOrderDetailListRespVO> page = new Page<>(reqVO.getPageNo(), reqVO.getPageSize());
    List<TaskOrderDetailListRespVO> respVOS = taskOrderMapper.selectListForDetail(page, reqVO);
    return new PageResult<>(respVOS, page.getTotal());
  }

  @Override
  @Transactional(rollbackFor = Exception.class)
  @CacheEvict(cacheNames = "user:month:cancel:count", key = "#memberId+':'+#month")
  public void cancelOrderBySystem(Long id, CancelTypeEnum cancelTypeEnum, Long memberId,
      String month) {
    TaskOrderDO taskOrderDO = taskOrderMapper.selectById(id);
    if (taskOrderDO == null) {
      log.warn("【系统取消任务订单失败】,id不存在:{}", id);
      return;
    }
    //进行中的订单需要取消
    if (!OrderStatusEnum.UNDERWAY.getCode().equals(taskOrderDO.getStatus())) {
      log.warn("【系统取消任务订单失败】,任务订单状态错误,id:{},status:{}", id,
          OrderStatusEnum.getEnumByCode(taskOrderDO.getStatus()).getName());
      return;
    }
    taskOrderDO.setStatus(OrderStatusEnum.CANCEL.getCode());
    taskOrderDO.setCancelTime(LocalDateTime.now());
    taskOrderDO.setCancelType(cancelTypeEnum.getCode());
    taskOrderMapper.updateById(taskOrderDO);
    // 增加任务数量
    taskExtMapper.updateSurplusNumOfAdd(taskOrderDO.getTaskExtId());
    // 系统原因取消，退报名费
    if (CancelTypeEnum.SYSTEM.getCode().equals(cancelTypeEnum.getCode())) {
      // 微信退款
      // 24小时微信退报名费
      SignUpRefundHandlerReqVO refundHandlerReqVO = new SignUpRefundHandlerReqVO();
      refundHandlerReqVO.setId(taskOrderDO.getPayOrderId());
      refundHandlerReqVO.setUserIp(getClientIP());
      taskClient
          .createTask(TaskHandlerEnum.SIGN_UP_REFUND.getValue(), LocalDateTime.now().plusHours(24),
              JsonUtils.toJsonString(refundHandlerReqVO));
    }
  }

  @Override
  @Transactional
  @CacheEvict(cacheNames = {"user:task:earning:day",
      "user:task:earning:predict"}, key = "#memberId+':'+#date")
  public void settleForTaskOrder(Long id, Long memberId, LocalDate date) {
    TaskOrderDO taskOrderDO = taskOrderMapper.selectById(id);
    if (taskOrderDO == null) {
      log.warn("【系统结算任务订单失败】,id不存在:{}", id);
      return;
    }
    //已通过的订单需要结算
    if (!OrderStatusEnum.PASS.getCode().equals(taskOrderDO.getStatus())) {
      log.warn("【系统结算任务订单失败】,任务订单状态错误,id:{},status:{}", id,
          OrderStatusEnum.getEnumByCode(taskOrderDO.getStatus()).getName());
      return;
    }
    if (!SettleStatusEnum.TO_BE_SETTLE.getCode().equals(taskOrderDO.getSettleStatus())) {
      log.warn("【系统结算任务订单失败】,结算状态错误,id:{},settleStatus:{}", id,
          SettleStatusEnum.getEnumByCode(taskOrderDO.getSettleStatus()).getName());
      return;
    }
    TaskDO taskDO = taskMapper.selectById(taskOrderDO.getTaskId());
    if (taskDO == null) {
      log.warn("【系统结算任务订单失败】,任务不存在,taskId:{}", taskOrderDO.getTaskId());
      return;
    }
    TaskExtDO taskExtDO = taskExtMapper.selectOne(TaskExtDO::getId, taskOrderDO.getTaskExtId());
    if (taskExtDO == null) {
      log.warn("【系统结算任务订单失败】,任务扩展表不存在,id:{}", taskOrderDO.getTaskExtId());
      return;
    }
    //1.商户结算
    this.settleForMerchant(taskOrderDO, taskDO, taskExtDO);
    //2.达人结算
    this.settleForMember(taskOrderDO, taskDO, taskExtDO);
    //3.通知奖励
    JSONObject object = new JSONObject();
    object.set("id", taskOrderDO.getId());
    object.set("status", TaskAwardStatusEnum.FINISH.getType());
    taskClient.createTask(TaskHandlerEnum.TASK_AWARD.getValue(), null, object.toString());
    //4.修改结算状态
    taskOrderDO.setSettleStatus(SettleStatusEnum.BE_SETTLE.getCode());
    taskOrderDO.setSettleTime(LocalDateTime.now());
    taskOrderMapper.updateById(taskOrderDO);

  }

  @Override
  @CacheEvict(cacheNames = "user:task:earning:predict", key = "#memberId+':'+#date")
  public void auditOrderBySystem(Long id, Long memberId, LocalDate date) {
    TaskOrderDO taskOrderDO = taskOrderMapper.selectById(id);
    if (taskOrderDO == null) {
      log.warn("【系统审核任务订单失败】,任务不存在,taskOrderId:{}", taskOrderDO.getId());
      return;
    }
    if (!OrderStatusEnum.TO_BE_REVIEWED.getCode().equals(taskOrderDO.getStatus())) {
      log.warn("【系统审核任务订单失败】,任务状态错误,status:{}",
          OrderStatusEnum.getEnumByCode(taskOrderDO.getStatus()).getName());
      return;
    }
    taskOrderDO.setStatus(OrderStatusEnum.PASS.getCode());
    taskOrderDO.setSettleStatus(SettleStatusEnum.TO_BE_SETTLE.getCode());
    taskOrderDO.setAuditInvalidTime(LocalDateTime.now());
    taskOrderMapper.updateById(taskOrderDO);
    // 48小时后开启结算流程
    SettleOrderHandlerReqVO handlerReqVO = new SettleOrderHandlerReqVO();
    handlerReqVO.setId(taskOrderDO.getId());
    handlerReqVO.setMemberId(taskOrderDO.getMemberUserId());
    taskClient
        .createTask(TaskHandlerEnum.SETTLE_ORDER.getValue(), LocalDateTime.now().plusHours(48),
            JsonUtils.toJsonString(handlerReqVO));

    taskClient
        .createTask(TaskHandlerEnum.TASK_AWARD.getValue(), LocalDateTime.now().plusHours(48),
            JsonUtils.toJsonString(handlerReqVO));

    // 24小时退报名费
    // 24小时微信退报名费
    SignUpRefundHandlerReqVO refundHandlerReqVO = new SignUpRefundHandlerReqVO();
    refundHandlerReqVO.setId(taskOrderDO.getPayOrderId());
    refundHandlerReqVO.setUserIp(getClientIP());

    taskClient
        .createTask(TaskHandlerEnum.SIGN_UP_REFUND.getValue(), LocalDateTime.now().plusHours(24),
            JsonUtils.toJsonString(refundHandlerReqVO));
  }

  /**
   * 商户结算
   *
   * @param taskOrderDO
   * @param taskDO
   * @param taskExtDO
   * @author hjn
   * @date 2023/9/7 下午2:27
   */
  private void settleForMerchant(TaskOrderDO taskOrderDO, TaskDO taskDO, TaskExtDO taskExtDO) {
    walletApi
        .transfer(SystemCodeEnum.PLATFORM.getCode(), taskExtDO.getFee(), taskDO.getMerchantUserId()
            , WalletTypeEnum.MERCHANT, WalletLogTypeEnum.TASK_SETTLE_FROM_MERCHANT,
            taskOrderDO.getId(),
            taskOrderDO.getId().toString()
            ,
            String.format(WalletLogTypeEnum.TASK_SETTLE_FROM_MERCHANT.getName(), taskDO.getTitle(),
                taskOrderDO.getId()));
    //冻结平台钱
    walletApi.freeze(SystemCodeEnum.PLATFORM.getCode(), taskExtDO.getFee(), WalletTypeEnum.MERCHANT
        , WalletLogTypeEnum.TASK_SETTLE_FREEZE_MERCHANT, taskOrderDO.getId(),
        taskOrderDO.getId().toString()
        , String.format(WalletLogTypeEnum.TASK_SETTLE_FREEZE_MERCHANT.getName(), taskDO.getTitle(),
            taskOrderDO.getId()));
  }

  /**
   * 达人结算
   *
   * @param taskOrderDO
   * @param taskDO
   * @param taskExtDO
   * @author hjn
   * @date 2023/9/7 下午2:27
   */
  private void settleForMember(TaskOrderDO taskOrderDO, TaskDO taskDO, TaskExtDO taskExtDO) {
    Long amount = taskExtDO.getFee() * taskExtDO.getMemberRatio() / 100;
    walletApi.transfer(taskOrderDO.getMemberUserId(), amount, SystemCodeEnum.PLATFORM.getCode()
        , WalletTypeEnum.MEMBER, WalletLogTypeEnum.TASK_SETTLE_TO_MEMBER, taskOrderDO.getId(),
        taskOrderDO.getId().toString()
        , String.format(WalletLogTypeEnum.TASK_SETTLE_TO_MEMBER.getName(), taskDO.getTitle(),
            taskOrderDO.getId()));
  }


  @Override
  public PageResult<TaskOrderRespVO> getListForApp(TaskOrderPageReqVO reqVO) {
    Page<TaskOrderRespVO> page = new Page<>(reqVO.getPageNo(), reqVO.getPageSize());
    List<TaskOrderRespVO> orderRespVOS = taskOrderMapper.selectListForApp(page, reqVO);
    orderRespVOS.forEach(orderRespVO -> {
      if (OrderStatusEnum.UNDERWAY.getCode().equals(orderRespVO.getOrderStatus())
          && StringUtils.isBlank(orderRespVO.getVideoUrl()) && TaskTypeEnum.YT.getCode()
          .equals(orderRespVO.getTaskType())) {
        orderRespVO.setShowType(ShowTypeEnum.SPSCZ.getCode());//视频正在生成中
      } else if (OrderStatusEnum.UNDERWAY.getCode().equals(orderRespVO.getOrderStatus())
          || OrderStatusEnum.TURN_DOWN.getCode().equals(orderRespVO.getOrderStatus())) {
        orderRespVO.setShowType(ShowTypeEnum.FBSYSJ.getCode());//发布剩余时间
      } else if (OrderStatusEnum.CANCEL.getCode().equals(orderRespVO.getOrderStatus())) {
        orderRespVO.setShowType(ShowTypeEnum.YQX.getCode());//已取消
      } else if (OrderStatusEnum.TO_BE_REVIEWED.getCode().equals(orderRespVO.getOrderStatus())) {
        orderRespVO.setShowType(ShowTypeEnum.SJSYSHSJ.getCode());//商家剩余审核时间
      } else if (OrderStatusEnum.PASS.getCode().equals(orderRespVO.getOrderStatus())
          && SettleStatusEnum.TO_BE_SETTLE.getCode().equals(orderRespVO.getSettleStatus())) {
        orderRespVO.setShowType(ShowTypeEnum.DJS.getCode());//待结算
      } else if (OrderStatusEnum.PASS.getCode().equals(orderRespVO.getOrderStatus())
          && SettleStatusEnum.BE_SETTLE.getCode().equals(orderRespVO.getSettleStatus())) {
        orderRespVO.setShowType(ShowTypeEnum.YJS.getCode());//待结算
      }
    });
    return new PageResult<>(orderRespVOS, page.getTotal());
  }

  @Override
  public TaskOrderCountRespVO getListCountForApp(Long userId) {
    TaskOrderCountRespVO respVO = new TaskOrderCountRespVO();
    Integer doingCount = taskOrderMapper
        .selectListCountForApp(userId, OrderStatusEnum.UNDERWAY.getCode());
    Integer passCount = taskOrderMapper
        .selectListCountForApp(userId, OrderStatusEnum.PASS.getCode());
    Integer unPassCount = taskOrderMapper
        .selectListCountForApp(userId, OrderStatusEnum.TURN_DOWN.getCode());
    Integer cancelCount = taskOrderMapper
        .selectListCountForApp(userId, OrderStatusEnum.CANCEL.getCode());
    respVO.setDoingCount(doingCount);
    respVO.setPassCount(passCount);
    respVO.setUnPassCount(unPassCount);
    respVO.setCancelCount(cancelCount);
    respVO.setTotalCount(doingCount + passCount + unPassCount + cancelCount);
    return respVO;
  }

  @Override
  public void deleteTaskOrder(Long id, Long userId) {
    TaskOrderDO taskOrderDO = taskOrderMapper.selectById(id);
    if (taskOrderDO == null) {
      throw exception(DATA_ERROR);
    }
    // 1.判断是否本人
    if (!taskOrderDO.getMemberUserId().equals(userId)) {
      throw exception(ORDER_AUTHORITY_ERROR);
    }
    // 2.判断订单状态
    if (!Arrays.asList(OrderStatusEnum.CANCEL.getCode()).contains(taskOrderDO.getStatus())) {
      throw exception(ORDER_STATUS_ERROR);
    }
    taskOrderDO.setStatus(OrderStatusEnum.DELETE.getCode());
    taskOrderMapper.updateById(taskOrderDO);

  }

  @Override
  public List<TaskOrderDO> getListForTask() {
    return taskOrderMapper.selectList(new LambdaQueryWrapper<TaskOrderDO>()
        .eq(TaskOrderDO::getStatus, OrderStatusEnum.PASS.getCode())
        .eq(TaskOrderDO::getSettleStatus, SettleStatusEnum.TO_BE_SETTLE.getCode()));
  }

  @Override
  public void updateById(TaskOrderDO taskOrderDO) {
    taskOrderMapper.updateById(taskOrderDO);
  }

  @Override
  public void cancelOrderByDy(Long id,String cancelReason) {
    TaskOrderDO taskOrderDO = taskOrderMapper.selectById(id);
    if (taskOrderDO == null) {
      log.warn("【系统取消任务订单失败】,id不存在:{}", id);
      return;
    }
    if(SettleStatusEnum.BE_SETTLE.getCode().equals(taskOrderDO.getSettleStatus())){
      log.warn("【系统取消任务订单失败】,订单已结算,id:{}", id);
      return;
    }
    if(OrderStatusEnum.CANCEL.getCode().equals(taskOrderDO.getStatus())){
      log.warn("【系统取消任务订单失败】,订单已取消,id:{}", id);
      return;
    }
    taskOrderDO.setStatus(OrderStatusEnum.CANCEL.getCode());
    taskOrderDO.setCancelReason(cancelReason);
    taskOrderDO.setCancelTime(LocalDateTime.now());
    taskOrderDO.setCancelType(CancelTypeEnum.MEMBER.getCode());
    taskOrderMapper.updateById(taskOrderDO);
    // 增加任务数量
    taskExtMapper.updateSurplusNumOfAdd(taskOrderDO.getTaskExtId());
    // 删除订单集合中的订单
    taskOrderGatherService.deleteGather(taskOrderDO.getTaskId(),taskOrderDO.getId(),DyUserTypeEnum.DR.getCode());

  }

  @Override
//  @Cacheable(cacheNames = "user:last:month:cancel:count", key = "#memberId+':'+#date")
  public long getLastMonthCancelCount(Long memberId, LocalDate date) {
    return taskOrderMapper.selectCount(new LambdaQueryWrapper<TaskOrderDO>()
        .eq(TaskOrderDO::getMemberUserId, memberId)
        .in(TaskOrderDO::getStatus,
            Arrays.asList(OrderStatusEnum.CANCEL.getCode(), OrderStatusEnum.DELETE.getCode()))
        .between(TaskOrderDO::getCreateTime, LocalDateTimeUtils.getFirstDayOfMonth(1, date),
            LocalDateTimeUtils.getLastDayOfMonth(1, date))
        .eq(TaskOrderDO::getCancelType, CancelTypeEnum.MEMBER.getCode()));
  }

  @Override
  @Transactional(rollbackFor = Exception.class)
  public Long memberAcceptOrder(TaskOrderAcceptReqVO reqVO) {

    Long memberId = reqVO.getUserId();
//    Integer levelType = reqVO.getLevelType();
    //验证用户信息
    String openId = reqVO.getOpenId();
    Long taskId = reqVO.getTaskId();
    //判断任务是否存在,是否开始
    TaskDO taskDO = taskMapper.selectById(taskId);
    if (taskDO == null || !TaskStatus.IN_PROGRESS.getStatus().equals(taskDO.getStatus())) {
      throw exception(TASK_NOT_EXISTS);
    }
    MemberUserExtVO memberUserExtVO = verifyUser(memberId, openId, taskDO.getAreaRequire(),
        taskDO.getFansNumRequire());

    //验证用户下单条件
    SystemArgDO sysArg = verifyUserOrder(memberId, taskId, taskDO.getTaskType());

    //当前是否有代支付订单
    TaskApplyDO taskApply = taskApplyMapper
        .selectOne(new LambdaQueryWrapperX<TaskApplyDO>().eq(TaskApplyDO::getTaskId, taskId)
            .eq(TaskApplyDO::getMemberId, memberId)
            .eq(TaskApplyDO::getApplyStatus, TaskApplyStatus.APPLY_NOT_PAY));

    if (taskApply != null) {
      return taskApply.getPayOrderId();
    }

    //判断当前订单是否有符合等级的待接订单
    List<TaskExtDO> taskExtList = taskExtMapper
        .selectList(new LambdaQueryWrapperX<TaskExtDO>().eq(TaskExtDO::getTaskId, taskId)
            .orderByDesc(TaskExtDO::getLevel));

    Integer levelType = memberUserExtVO.getDyGrade();
    TaskExtDO taskExtDO = null;
    for (TaskExtDO extDO : taskExtList) {
      if (extDO.getLevel() <= levelType) {
        if (extDO.getSurplusNum() > 0) {
          taskExtDO = extDO;
          break;
        }
      }
    }

    if (taskExtDO == null) {
      throw exception(TASK_NOT_SUPPORT_LEVEL);
    }

    //接单库存减一
    TaskExtDO finalTaskExtDO = taskExtDO;
    orderLockRedisDAO.lock(finalTaskExtDO.getId(), () -> {
      AtomicReference<Long> atomicReference = new AtomicReference<>();

      if (taskExtMapper.updateSurplusNumOfSub(finalTaskExtDO.getId()) != 1) {
        throw exception(TASK_LEVEL_FULL);
      }
      atomicReference.set(finalTaskExtDO.getId());
    });

    //获取需要支付的定金金额
    Long depositAmount = getDepositAmount(taskDO, sysArg, memberId);
    WalletOrderDTO walletOrder = walletOrderApi
        .createWalletOrder(memberId, WalletOrderTypeEnum.DEPOSIT.getType(), depositAmount);

    //支付失效时间
    LocalDateTime invalidTime = LocalDateTime.now().plusMinutes(3);

    TaskApplyDO taskApplyDO = new TaskApplyDO();
    taskApplyDO.setTaskId(taskId);
    taskApplyDO.setTaskExtId(taskExtDO.getId());
    taskApplyDO.setApplyInvalidTime(invalidTime);
    taskApplyDO.setMemberId(memberId);
    taskApplyDO.setOpenId(openId);
    taskApplyDO.setWalletOrderId(walletOrder.getWalletOrderId());
    taskApplyDO.setPayOrderId(walletOrder.getPayOrderId());
    taskApplyDO.setEntryFee(depositAmount);
    taskApplyDO.setApplyStatus(TaskApplyStatus.APPLY_NOT_PAY.getStatus());
    //生成订单
    taskApplyMapper.insert(taskApplyDO);

    //返回payOrderId
    return walletOrder.getPayOrderId();
  }

  private Long getDepositAmount(TaskDO task, SystemArgDO sysArg, Long memberId) {

    if (TaskType.YUN.getType().equals(task.getTaskType())) {
      if (AiClipType.AI_CLIP.getType().equals(task.getClipType())) {
        return sysArg.getYunAiClipFee();
      } else {
        //查看当前用户是否有定制的数字人,没有的话不能接单
        List<MetaHumanConfigDO> metaHumanConfigList = metaHumanConfigService
            .getMetaHumanConfig(UserTypeEnum.MEMBER.getValue(), memberId);

        if (metaHumanConfigList == null || metaHumanConfigList.isEmpty()) {
          throw exception(TASK_NEED_CUSTOM);
        }

        return sysArg.getYunDigitalClipFee();
      }
    } else {
      Boolean isShop = task.getIsShop();
      if (isShop == null || isShop) {
        //不需要真人出镜
        return sysArg.getEntityNotOnCamera();
      } else {
        //需要真人出镜
        return sysArg.getEntityOnCamera();
      }

    }
  }

  private SystemArgDO verifyUserOrder(Long memberId, Long taskId, Integer taskType) {
    LocalDateTime now = LocalDateTime.now();
    SystemArgDO systemArg = systemArgService.getSystemArg();
    if (systemArg == null) {
      log.error("系统参数未配置!");
      throw exception(SYSTEM_ERROR);
    }
    //当天会员的接单数是否超过限制
    Long todayOrderCount = taskOrderMapper.selectCount(new LambdaQueryWrapper<TaskOrderDO>()
        .eq(TaskOrderDO::getMemberUserId, memberId)
        .eq(TaskOrderDO::getTaskType, taskType)
        .between(TaskOrderDO::getCreateTime, LocalDateTimeUtils.getTodayStart(),
            LocalDateTimeUtils.getTodayEnd()));
    if (TaskType.YUN.getType().equals(taskType)) {
      if (todayOrderCount >= systemArg.getYunDayOrderNum()) {
        throw exception(TASK_DAY_FULL);
      }
    } else {
      if (todayOrderCount >= systemArg.getEntityDayOrderNum()) {
        throw exception(TASK_DAY_FULL_2);
      }
    }

    //当前会员是否有正在进行中的当前任务的订单
    TaskOrderDO oldOrder = taskOrderMapper.selectOne(new LambdaQueryWrapper<TaskOrderDO>()
        .eq(TaskOrderDO::getMemberUserId, memberId)
        .eq(TaskOrderDO::getTaskId, taskId)
        .in(TaskOrderDO::getStatus, OrderStatusEnum.TO_BE_REVIEWED.getCode(),
            OrderStatusEnum.TURN_DOWN.getCode(), OrderStatusEnum.TURN_DOWN.getCode())
        .orderByDesc(TaskOrderDO::getCreateTime)
        .last("limit 1"));
    if (oldOrder != null) {
      throw exception(TASK_HAS_ORDER);
    }
    LocalDateTime start;
    if (TaskType.YUN.getType().equals(taskType)) {
      start = LocalDateTimeUtils
          .getMinusDaysTime(now, systemArg.getYunDayNum());
    } else {
      start = LocalDateTimeUtils
          .getMinusDaysTime(now, systemArg.getEntityDayNum());
    }

    //当前用户是否有在之前完成过这个订单,相隔时间是否没有超过规定时间
    Long passOrderNum = taskOrderMapper.selectCount(new LambdaQueryWrapper<TaskOrderDO>()
        .eq(TaskOrderDO::getMemberUserId, memberId)
        .eq(TaskOrderDO::getTaskType, taskType)
        .eq(TaskOrderDO::getTaskId, taskId)
        .eq(TaskOrderDO::getStatus, OrderStatusEnum.PASS.getCode())
        .between(TaskOrderDO::getUpdateTime, start, now)
        .orderByDesc(TaskOrderDO::getCreateTime));

    if (TaskType.YUN.getType().equals(taskType)) {
      if (passOrderNum > systemArg.getYunOrderNum()) {
        throw exception(TASK_NOT_ACCEPT);
      }
//      throw exception(new ErrorCode(400, "当前会员有正在进行中的当前任务的订单!"));
    } else {
      if (passOrderNum > systemArg.getEntityOrderNum()) {
        throw exception(TASK_NOT_ACCEPT);
      }
    }

    return systemArg;
  }

  private MemberUserExtVO verifyUser(Long memberId, String openId,
      String areaRequire, String fansRequire) {
    MemberUserExtVO user = memberUserApi.getUser(memberId, openId);

    //当前会员是否进行实名认证
    if (user.getIsAuth() != 1) {
      throw exception(TASK_NOT_REAL_NAME);
    }
//    //当前会员的带货等级是否满足任务要求
//    if (user.getDyGrade() < level) {
//      throw exception(TASK_NOT_LEVEL);
//    }
    //判断当前粉丝数量是否满足任务要求
    if (StringUtils.isNotBlank(fansRequire)) {
      if (user.getDyFansCount() == null) {
        throw exception(TASK_NOT_ACCEPT_3);
      }
      if (user.getDyFansCount() < Integer.parseInt(fansRequire)) {
        throw exception(TASK_NOT_ACCEPT_3);
      }
    }
    //当前用户是否在任务要求的地区
    if (StringUtils.isNotBlank(areaRequire)) {
      if (user.getCityCode() == null) {
        throw exception(TASK_NOT_ACCEPT_2);
      }
      if (!areaRequire.contains(user.getCityCode().toString())) {
        throw exception(TASK_NOT_ACCEPT_2);
      }
    }
    //当前会员上个月是否取消订单,超过限制次数
    long lastMonthCancelCount = getLastMonthCancelCount(memberId, LocalDate.now());

    if (lastMonthCancelCount > 3) {
      throw exception(TASK_NOT_CANCEL);
    }
    //当前会员当月是否取消订单,超过限制次数
    long monthCancelCount = getMonthCancelCount(memberId, DateUtils.getYearAndMonth());

    if (monthCancelCount > 3) {
      throw exception(TASK_NOT_CANCEL_2);
    }
    return user;
  }


  @Override
//  @Cacheable(cacheNames = "user:month:cancel:count", key = "#memberId+':'+#month")
  public long getMonthCancelCount(Long memberId, String month) {
    return taskOrderMapper.selectCount(new LambdaQueryWrapper<TaskOrderDO>()
        .eq(TaskOrderDO::getMemberUserId, memberId)
        .in(TaskOrderDO::getStatus,
            Arrays.asList(OrderStatusEnum.CANCEL.getCode(), OrderStatusEnum.DELETE.getCode()))
        .between(TaskOrderDO::getCreateTime,
            LocalDateTimeUtils.getFirstDayOfMonth(0, LocalDate.now()),
            LocalDateTime.now())
        .eq(TaskOrderDO::getCancelType, CancelTypeEnum.MEMBER.getCode()));
  }

  @Override
  @Transactional(rollbackFor = Exception.class)
  public void processOrder(UpMemberCashOrderDTO order, LocalDate date) {

    //支付订单Id
    Long walletOrderId = order.getWalletOrderId();
    //支付状态
    Integer status = order.getStatus();

    //报名订单信息
    TaskApplyDO taskApplyDO = taskApplyMapper.selectOne(
        new LambdaQueryWrapperX<TaskApplyDO>().eq(TaskApplyDO::getWalletOrderId, walletOrderId)
            .eq(TaskApplyDO::getApplyStatus, TaskApplyStatus.APPLY_NOT_PAY.getStatus()));

    TaskApplyStatus applyStatus;
    if (taskApplyDO == null) {
      throw exception(TASK_SIGN_ORDER_NOT_EXISTS);
    }

    //查询支付订单信息
    if (WalletOrderStatusEnum.FINISH.getType().equals(status)) {
      //支付成功
      applyStatus = TaskApplyStatus.APPLY_PAY;
      //创建订单
      createOrder(taskApplyDO.getMemberId(), taskApplyDO);
    } else {
      //支付失败
      applyStatus = TaskApplyStatus.APPLY_INVALID;
      //释放剩余数量
      taskExtMapper.updateSurplusNumOfAdd(taskApplyDO.getTaskExtId());
    }
    TaskApplyDO newApply = new TaskApplyDO();
    newApply.setId(taskApplyDO.getId());
    newApply.setApplyStatus(applyStatus.getStatus());
    taskApplyMapper.updateById(newApply);
  }

  private void createOrder(Long memberId, TaskApplyDO taskApplyDO) {
    //查询任务信息
    Long taskExtId = taskApplyDO.getTaskExtId();
    TaskExtDO taskExtDO = taskExtMapper.selectById(taskApplyDO.getTaskExtId());

    TaskDO taskDO = taskMapper.selectById(taskApplyDO.getTaskId());
    if (taskDO == null) {
      throw exception(TASK_SIGN_ORDER_NOT_EXISTS);
    }
    TaskOrderDO taskOrderDO = new TaskOrderDO();
    taskOrderDO.setMemberUserId(memberId);
    taskOrderDO.setMemberLevel(taskExtDO.getLevel());
    taskOrderDO.setTaskType(taskDO.getTaskType());
    taskOrderDO.setTaskId(taskApplyDO.getTaskId());
    taskOrderDO.setTaskExtId(taskExtId);
    taskOrderDO.setDyOpenId(taskApplyDO.getOpenId());

    taskOrderDO.setPayOrderId(taskApplyDO.getPayOrderId());
    //达人服务费
    Long fee = taskExtDO.getMemberFee();
    taskOrderDO.setFee(fee);
    //任务失效时间
    LocalDateTime taskInvalidTime = LocalDateTime.now().plusDays(2);
    if (TaskTypeEnum.ST.getCode().equals(taskDO.getTaskType())) {
      taskInvalidTime = LocalDateTime.now().plusDays(5);
    }
    taskOrderDO.setTaskInvalidTime(taskInvalidTime);
    taskOrderDO.setStatus(OrderStatusEnum.UNDERWAY.getCode());
    taskOrderDO.setTenantId(taskDO.getTenantId());
    taskOrderMapper.insert(taskOrderDO);

    //生成分佣任务
    addWalletOrderTaskAward(taskExtDO, memberId, taskOrderDO.getId());

    //生成宣传视频任务
    taskClient
        .createTask(TaskHandlerEnum.CREATE_UP_MEMBER_TASK_VIDEO.getValue(), null,
            taskOrderDO.getId().toString());

    CancelOrderHandlerReqVO handlerReqVO = new CancelOrderHandlerReqVO();
    handlerReqVO.setId(taskOrderDO.getId());
    handlerReqVO.setCancelType(CancelTypeEnum.MEMBER.getCode());
    handlerReqVO.setMemberId(taskOrderDO.getMemberUserId());
    //失效
    taskClient.createTask(TaskHandlerEnum.CANCEL_ORDER.getValue(), taskInvalidTime,
        JsonUtils.toJsonString(handlerReqVO));
    //报名成功之后发送短信通知
    this.sendSmsForApply(taskDO.getTaskType(), taskDO.getTitle(), memberId);
  }

  private void sendSmsForApply(Integer taskType, String taskName, Long memberId) {
    SmsSendSingleToUserReqDTO reqDTO = new SmsSendSingleToUserReqDTO();
    MemberUserRespDTO memberUserRespDTO = memberUserApi.getUser(memberId);
    reqDTO.setUserId(memberUserRespDTO.getId());
    reqDTO.setMobile(memberUserRespDTO.getMobile());
    reqDTO.setTemplateCode(SmsSceneEnum.YT_TASK_APPLY.getTemplateCode());
    if (TaskTypeEnum.ST.getCode().equals(taskType)) {
      reqDTO.setTemplateCode(SmsSceneEnum.ST_TASK_APPLY.getTemplateCode());
    }
    Map<String, Object> params = new HashMap<>();
    if (taskName.length() > 10) {
      taskName = taskName.substring(0, 10);
    }
    params.put("taskname", taskName);
    reqDTO.setTemplateParams(params);
    smsSendApi.sendSingleSmsToMember(reqDTO);

  }


  private void addWalletOrderTaskAward(TaskExtDO taskExtDO, Long memberId, Long orderId) {
    Long fee = taskExtDO.getFee();
    List<WalletOrderTaskAwardDTO> list = Lists.newArrayList();
    TaskDO taskDO = taskMapper.selectById(taskExtDO.getTaskId());
    UserInviteDTO merchantUserInviteDTO = memberUserApi.getInviteById(taskDO.getMerchantUserId());
    //查询邀请商家的用户(邀请商家的达人)
    if (merchantUserInviteDTO != null&&merchantUserInviteDTO.getParentUserId()!=null) {
      WalletOrderTaskAwardDTO inviteMerchantMemberTaskAwardDTO = newWalletOrderTaskAward(
          taskExtDO.getMerchantRatio(), orderId, fee, merchantUserInviteDTO.getParentUserId(),
          TaskAwardTypeEnum.MERCHANT);
      list.add(inviteMerchantMemberTaskAwardDTO);
    }
    UserInviteDTO userInviteDTO = memberUserApi.getInviteById(memberId);
    //查询一级分佣用户(当前完成达人的上级)
    if (userInviteDTO != null&&userInviteDTO.getParentUserId()!=null) {
      WalletOrderTaskAwardDTO twoMemberTaskAwardDTO = newWalletOrderTaskAward(
          taskExtDO.getMemberTwoRatio(), orderId, fee, userInviteDTO.getParentUserId(), TaskAwardTypeEnum.MEMBER_ONE);
      list.add(twoMemberTaskAwardDTO);
      //查询二级分佣用户(上级的上级)
      if (userInviteDTO.getTopUserId() != null) {
        WalletOrderTaskAwardDTO oneMemberTaskAwardDTO = newWalletOrderTaskAward(
            taskExtDO.getMemberOneRatio(), orderId, fee, userInviteDTO.getTopUserId(), TaskAwardTypeEnum.MEMBER_TWO);
        list.add(oneMemberTaskAwardDTO);
      }
    }

    if (!list.isEmpty()) {
      walletOrderTaskAwardApi.addTaskAwardList(list);
    }

  }

  @NotNull
  private WalletOrderTaskAwardDTO newWalletOrderTaskAward(Integer ratio, Long taskId,
      Long fee, Long userId, TaskAwardTypeEnum type) {
    WalletOrderTaskAwardDTO inviteMerchantMemberTaskAwardDTO = new WalletOrderTaskAwardDTO();
    inviteMerchantMemberTaskAwardDTO.setType(type.getType());
    inviteMerchantMemberTaskAwardDTO.setCxlaikeTaskId(taskId);
    inviteMerchantMemberTaskAwardDTO.setUserId(userId);
    Long serviceFee = fee * ratio / 100;
    inviteMerchantMemberTaskAwardDTO.setRate(ratio);
    inviteMerchantMemberTaskAwardDTO.setAmount(serviceFee);
    inviteMerchantMemberTaskAwardDTO.setStatus(TaskAwardStatusEnum.RUN.getType());
    return inviteMerchantMemberTaskAwardDTO;
  }

  @Override
  @Transactional(rollbackFor = Exception.class)
  public void createMemberTaskVideo(Long taskOrderId) {

    TaskOrderDO taskOrderDO = taskOrderMapper.selectById(taskOrderId);

    if (taskOrderDO == null) {
      throw exception(TASK_SIGN_ORDER_NOT_EXISTS);
    }

    //查询任务信息
    Long taskId = taskOrderDO.getTaskId();
    TaskDO taskDO = taskMapper.selectById(taskId);

    if (taskDO == null) {
      throw exception(TASK_NOT_EXISTS);
    }

    //不是云探不需要生成视频
    if (!TaskType.YUN.getType().equals(taskDO.getTaskType())) {
      log.info("当前任务不需要生成宣传视频,taskOrderId:{}", taskOrderId);
      return;
    }
    //达人Id
    Long memberUserId = taskOrderDO.getMemberUserId();

    //获取口播文案
    CopyManagementDO copyDO = getCopyDO(taskId);

    if (AiClipType.DIGITAL_CLIP.getType().equals(taskDO.getClipType())) {

      TaskMaterialDO humanMaterial = new TaskMaterialDO();
      humanMaterial.setTaskId(taskId);
      humanMaterial.setTaskOrderId(taskOrderId);
      humanMaterial.setType(TaskMaterialFileType.DIGITAL.getType());
      humanMaterial.setMemberUserId(memberUserId);
      humanMaterial.setStatus(TaskMaterialFileStatus.PROCESSING.getType());
      //创建一个数字人素材
      taskMaterialMapper.insert(humanMaterial);
      Long materialId = humanMaterial.getId();
      VideoClipOrderDTO digitalClipVideoTask = createDigitalClipVideoTask(memberUserId, materialId
          , copyDO.getCopywriting());
      TaskMaterialDO updateHumanMaterial = new TaskMaterialDO();
      updateHumanMaterial.setJobId(digitalClipVideoTask.getJobId());
      updateHumanMaterial.setMaterialUrl(digitalClipVideoTask.getMediaUrl());
      updateHumanMaterial.setId(materialId);
      updateHumanMaterial.setVideoOrderId(digitalClipVideoTask.getId());
      taskMaterialMapper.updateById(updateHumanMaterial);
      //添加口播文案信息
      taskOrderMapper.updateById(new TaskOrderDO().setId(taskOrderId).setCopyId(copyDO.getId()));

    } else {
      //ai混剪视频
      //生成任务
      JSONObject param = new JSONObject();
      param.set("id", taskOrderId);
      param.set("type", ActiveTaskType.SHOPPING.getType());
      taskClient.createTask(TaskHandlerEnum.AI_CLIP.getValue(), null, param.toString());
    }
  }

  private VideoClipOrderDTO createDigitalClipVideoTask(Long userId, Long materialId,
      String copy) {

    MetaHumanVideoClip digitalClipVideo = new MetaHumanVideoClip();

    digitalClipVideo.setUserId(userId);
    digitalClipVideo.setMaterialId(materialId);
    digitalClipVideo.setCopywriting(copy);

    //配音语调
    Integer pitchRate = AiSpeechRateAndPitchRateType
        .valueOfType(5).getValue();
    digitalClipVideo.setDubPitchRate(pitchRate);
    //配音员code
    String dubCode = getRandomVoiceCode();
    digitalClipVideo.setDubCode(dubCode);
    //配音语速
    Integer speechRate = AiSpeechRateAndPitchRateType
        .valueOfType(5).getValue();
    digitalClipVideo.setDubSpeechRate(speechRate);
    //数字人code
    String metaHumanCode = getMetaHumanCode(userId);
    digitalClipVideo.setMetaHumanCode(metaHumanCode);
    //数字人背景图
    String metaHumanBackgroundUrl = getRandomImage();
    digitalClipVideo.setMetaHumanBackgroundUrl(metaHumanBackgroundUrl);
    digitalClipVideo.setOrderType(ActiveTaskType.SHOPPING.getType());
    return metaHumanVideoClipService.firstClip(digitalClipVideo);
  }

  /**
   * 随机获取配音code
   *
   * @return
   */
  private String getRandomVoiceCode() {
    List<AiSystemArgDO> aiSystemArgList = aiSystemArgService
        .getAiSystemArgList(AiSystemArgType.DUBBING.getType());
    //随机获取配音code
    if (!CollectionUtil.isEmpty(aiSystemArgList)) {
      return aiSystemArgList.get(RandomUtil.randomInt(aiSystemArgList.size())).getCode();
    }
    return "sicheng";
  }

  @Override
  @Transactional(rollbackFor = Exception.class)
  public void checkTaskVideoResults() throws Exception {
    //查看未出结果的素材
    List<TaskMaterialDO> taskMaterialList = taskMaterialMapper
        .selectList(new LambdaQueryWrapperX<TaskMaterialDO>()
            .eq(TaskMaterialDO::getStatus, TaskMaterialFileStatus.PROCESSING.getType()));

    for (TaskMaterialDO taskMaterialDO : taskMaterialList) {
      //判断当前素材是ai混剪还是数字人素材
      if (TaskMaterialFileType.DIGITAL.getType().equals(taskMaterialDO.getType())) {
        //数字人
        digitalClip(taskMaterialDO);
      } else {
        //混剪
        aiClip(taskMaterialDO);
      }
    }


  }

  @Override
  @Transactional(rollbackFor = Exception.class)
  public void uploadFileTask(Long taskOrderId, String oldMediaUrl) throws Exception {
    //上传文件然后修改素材中的url
    TaskOrderDO taskOrderDO = taskOrderMapper.selectById(taskOrderId);

    TaskMaterialDO taskMaterialDO = taskMaterialMapper.selectOne(
        new LambdaQueryWrapperX<TaskMaterialDO>().eq(TaskMaterialDO::getTaskOrderId, taskOrderId)
            .eq(TaskMaterialDO::getType, TaskMaterialFileType.DIGITAL.getType()).last("limit 1"));

    //根据地址上传文件
    byte[] fileBytes = getFileBytes(oldMediaUrl);
    String extension = FileTypeUtil.getType(new ByteArrayInputStream(fileBytes));
    String fileUrl = materialService
        .createFile(taskOrderDO.getId().toString() + "." + extension, "/",
            fileBytes, 4, taskOrderDO.getMemberUserId());
    //修改之前的文件地址
    taskMaterialMapper.updateById(
        new TaskMaterialDO().setId(taskMaterialDO.getId()).setMaterialUrl(fileUrl));

    JSONObject object = new JSONObject();
    object.put("id", taskOrderId);
    object.put("taskType", UploadFileTaskType.EXPLORE_TASK);
    taskClient.createTask(TaskHandlerEnum.DIGITAL_CLIP.getValue(), null, object.toString());

  }


  private byte[] getFileBytes(String oldMediaUrl) throws IOException {
    URL url = new URL(oldMediaUrl);
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    byte[] buffer = new byte[4096];
    int bytesRead;
    InputStream inputStream = url.openStream();
    while ((bytesRead = inputStream.read(buffer)) != -1) {
      outputStream.write(buffer, 0, bytesRead);
    }
    return outputStream.toByteArray();
  }

  @Override
  @Transactional(rollbackFor = Exception.class)
  public void newDigitalClipHandle(Long materialId) throws Exception {

    TaskMaterialDO taskMaterialDO = taskMaterialMapper.selectOne(
        new LambdaQueryWrapperX<TaskMaterialDO>().eq(TaskMaterialDO::getId, materialId)
            .eq(TaskMaterialDO::getStatus, TaskMaterialFileStatus.SUCCESS.getType()));

    if (taskMaterialDO == null) {
      log.error("未查询到生成成功的数字人素材,materialId:{}", materialId);
      return;
    }
    Long taskOrderId = taskMaterialDO.getTaskOrderId();
    //生成视频
    TaskOrderDO taskOrderDO = taskOrderMapper.selectById(taskOrderId);
    if (taskOrderDO == null) {
      log.error("任务订单不存在,taskOrderId:{}", taskOrderId);
      return;
    }
    TaskDO taskDO = taskMapper.selectById(taskOrderDO.getTaskId());
    if (taskDO == null) {
      log.error("任务不存在,taskId:{}", taskOrderDO.getTaskId());
      return;
    }
    //查看是否已经生成结果
    List<TaskMaterialDO> taskMaterialDOList = taskMaterialMapper.selectList(
        new LambdaQueryWrapperX<TaskMaterialDO>().eq(TaskMaterialDO::getTaskOrderId, taskOrderId)
            .eq(TaskMaterialDO::getType, TaskMaterialFileType.AI_CLIP.getType()));

    if (!taskMaterialDOList.isEmpty()) {
      log.info("当前任务已经生成结果,taskOrderId:{}", taskOrderId);
      return;
    }

    Long taskId = taskDO.getId();
    //查素材切片
    List<VideoSliceDO> sliceDOList = videoSliceMapper
        .selectList(new LambdaQueryWrapperX<VideoSliceDO>().eq(VideoSliceDO::getTaskType,
            ActiveTaskType.SHOPPING.getType()).eq(VideoSliceDO::getTaskId, taskId));
    Long memberUserId = taskOrderDO.getMemberUserId();
    String userId = taskOrderDO.getMemberUserId().toString();

    //生成视频结果
    TaskMaterialDO aiClip = new TaskMaterialDO();
    aiClip.setTaskId(taskId);
    aiClip.setCreator(userId);
    aiClip.setUpdater(userId);
    aiClip.setTaskOrderId(taskOrderId);
    aiClip.setType(TaskMaterialFileType.AI_CLIP.getType());
    aiClip.setMemberUserId(memberUserId);
    aiClip.setStatus(TaskMaterialFileStatus.PROCESSING.getType());
    //添加一个ai混剪素材
    taskMaterialMapper.insert(aiClip);

    Integer fileLength = FileUtils.getFileLength(taskMaterialDO.getMaterialUrl());

    //生成切片
    List<VideoTrackClip> videoTrackClipList = VideoSlicer
        .getVideoTrackClipList(sliceDOList, fileLength);
    //生成视频
    VideoClipOrderDTO videoClipOrder = newDigitalClip(memberUserId, aiClip.getId(),
        taskOrderDO.getCopyId(),
        taskDO.getContentTitle(), taskMaterialDO.getMaterialUrl(),
        videoTrackClipList);

    TaskMaterialDO updateAiClip = new TaskMaterialDO();
    updateAiClip.setJobId(videoClipOrder.getJobId());
    updateAiClip.setMaterialUrl(videoClipOrder.getMediaUrl());
    updateAiClip.setVideoOrderId(videoClipOrder.getId());
    updateAiClip.setId(aiClip.getId());
    taskMaterialMapper.updateById(updateAiClip);

    //更新使用次数
    sliceDOList.forEach(videoSliceDO -> {
      if (videoSliceDO.getNum() > 0) {
        videoSliceMapper.updateById(new VideoSliceDO().setId(videoSliceDO.getId())
            .setNum(videoSliceDO.getNum()));
      }
    });

  }

    private VideoClipOrderDTO newDigitalClip(Long memberUserId, Long materialId, Long copyId,
      String contentTitle, String virtualVideoUrl, List<VideoTrackClip> videoTrackClipList)
      throws Exception {
    MetaHumanSecondVideoClip metaHumanSecondVideoClip = new MetaHumanSecondVideoClip();
    metaHumanSecondVideoClip.setUserId(memberUserId);
    metaHumanSecondVideoClip.setMaterialId(materialId);
    metaHumanSecondVideoClip.setVideoTrackClips(videoTrackClipList);
    TemplateTypeEnum templateTypeEnum = TemplateTypeEnum
        .getMetaHumanRandomEnumByWeight(Boolean.TRUE);
    metaHumanSecondVideoClip.setTemplateTypeEnum(templateTypeEnum);
    metaHumanSecondVideoClip.setOrderType(ActiveTaskType.SHOPPING.getType());
    metaHumanSecondVideoClip.setVirtualVideoURL(virtualVideoUrl);
//    String coverUrl = getCoverUrl(aiVideoTask.getCoverId());
//    metaHumanSecondVideoClip.setCoverImage(coverUrl);

    String copywriter = getCopyById(copyId);
    metaHumanSecondVideoClip.setContent(copywriter);
    String randomDubCode = getRandomVoiceCode();
    metaHumanSecondVideoClip.setVoice(randomDubCode);
    String randomPipedMusic = getRandomMusic();
    metaHumanSecondVideoClip.setBgMediaURL(randomPipedMusic);
    String backgroundImageUrl = getRandomImage();
    metaHumanSecondVideoClip.setBgImage(backgroundImageUrl);
    metaHumanSecondVideoClip.setTitle(contentTitle);
    //配音语速
    Integer speechRate = AiSpeechRateAndPitchRateType
        .valueOfType(5).getValue();
    //配音语调
    Integer pitchRate = AiSpeechRateAndPitchRateType
        .valueOfType(5).getValue();
    metaHumanSecondVideoClip.setSpeechRate(speechRate);
    metaHumanSecondVideoClip.setPitchRate(pitchRate);
    metaHumanSecondVideoClip.setVolume(1.0F);
    metaHumanSecondVideoClip.setIsFlowerWord(Boolean.TRUE);

    return metaHumanVideoClipService
        .secondClip(metaHumanSecondVideoClip);
  }

  private String getCopyById(Long copyId) {
    return copyManagementMapper.selectById(copyId).getCopywriting();
  }

  @Override
  @Transactional(rollbackFor = Exception.class)
  public void digitalClipHandle(Long taskOrderId) throws Exception {
    //生成视频
    TaskOrderDO taskOrderDO = taskOrderMapper.selectById(taskOrderId);

    TaskDO taskDO = taskMapper.selectById(taskOrderDO.getTaskId());

    TaskMaterialDO taskMaterialDO = taskMaterialMapper.selectOne(
        new LambdaQueryWrapperX<TaskMaterialDO>().eq(TaskMaterialDO::getTaskOrderId, taskOrderId)
            .eq(TaskMaterialDO::getType, TaskMaterialFileType.DIGITAL.getType()).last("limit 1"));

    //获取切片信息,获取id最小的一条
    TaskMaterialSliceDO taskMaterialSlice =
        taskMaterialSliceMapper
            .selectOne(new LambdaQueryWrapperX<TaskMaterialSliceDO>()
                .eq(TaskMaterialSliceDO::getTaskId, taskMaterialDO.getTaskId())
                .eq(TaskMaterialSliceDO::getMemberUserId, taskOrderDO.getMemberUserId())
                .eq(TaskMaterialSliceDO::getStatus,
                    TaskMaterialSliceStatus.AI_USED.getStatus())
                .orderByAsc(TaskMaterialSliceDO::getId).last("limit 1"));

    if (taskMaterialSlice == null) {
      throw exception(new ErrorCode(400, "当前未使用的切片不存在!"));
    }

    //获取口播内容
    String copy = getCopy(taskDO.getId());

    List<VideoTrackClip> videoTrackClips = JSONUtil
        .toList(taskMaterialSlice.getParam(), VideoTrackClip.class);

    String metaHumanDubCode = getMetaHumanDubCode(taskOrderDO.getMemberUserId());

    //获取随机图片
    String randomImageUrl = getRandomImage();
    //获取随机背景音乐
    String randomMusicUrl = getRandomMusic();
    //获取随机模版
    TemplateTypeEnum templateTypeEnum = TemplateTypeEnum
        .getMetaHumanRandomEnumByWeight(Boolean.TRUE);
    VideoSynthesisService videoSynthesisService = videoSynthesisServices.stream()
        .filter(videoSynthesis -> videoSynthesis.matching(templateTypeEnum)).collect(
            Collectors.toList()).get(0);
    VideoSynthesisDTO videoSynthesisDTO = new VideoSynthesisDTO();
    videoSynthesisDTO.setBgVirtualImage(
        randomImageUrl);
    videoSynthesisDTO.setVirtualVideoURL(taskMaterialDO.getMaterialUrl());
    videoSynthesisDTO.setVideoTrackClips(videoTrackClips);
    videoSynthesisDTO.setContent(copy);
    videoSynthesisDTO.setVoice(metaHumanDubCode);
    videoSynthesisDTO.setIsFlowerWord(Boolean.TRUE);
    videoSynthesisDTO
        .setBgMediaURL(randomMusicUrl);
    videoSynthesisDTO.setTitle(taskDO.getContentTitle());
    Timeline timeline = videoSynthesisService
        .template(templateTypeEnum, videoSynthesisDTO);

    log.info("templateTypeEnum:{},videoSynthesisDTO:{}", templateTypeEnum.getValue(),
        JsonUtils.toJsonString(videoSynthesisDTO));
    log.info("timeline:{}", JsonUtils.toJsonString(timeline));

    String mediaUrl =
        iceProperties.getUrl()
            + taskOrderDO.getMemberUserId() + "/" + UUID.randomUUID() + ".mp4";

    //生成视频
    SubmitMediaProducingJobRequest submitMediaProducingJobRequest = videoSynthesisService
        .submitMediaProducingJobRequest(mediaUrl, null, timeline);

    SubmitMediaProducingJobResponse submitMediaProducingJobResponse = iceService
        .submitMediaProducingJob(submitMediaProducingJobRequest);

    String jobId = submitMediaProducingJobResponse.body.jobId;
    //数字人混剪材料
    TaskMaterialDO humanTaskMaterial = new TaskMaterialDO();
    humanTaskMaterial.setMemberUserId(taskOrderDO.getMemberUserId());
    humanTaskMaterial.setTaskId(taskDO.getId());
    humanTaskMaterial.setTaskOrderId(taskOrderId);
    humanTaskMaterial.setSliceId(taskMaterialSlice.getId());
    humanTaskMaterial.setJobId(jobId);
    humanTaskMaterial.setMaterialUrl(mediaUrl);
    humanTaskMaterial.setType(TaskMaterialFileType.AI_CLIP.getType());
    humanTaskMaterial.setStatus(TaskMaterialFileStatus.PROCESSING.getType());
    taskMaterialMapper.insert(humanTaskMaterial);

    //修改切片状态
    taskMaterialSliceMapper.updateById(
        new TaskMaterialSliceDO().setId(taskMaterialSlice.getId())
            .setMemberUserId(taskOrderDO.getMemberUserId())
            .setStatus(TaskMaterialSliceStatus.USED.getStatus()));
  }

  private String getRandomMusic() {
    //获取系统随机音乐
    List<MaterialRespVO> sysMaterialList = materialService
        .getSysMaterialList(MaterialTypeEnum.SYS_YP_HOT.getCode(), null);
    if (CollectionUtil.isNotEmpty(sysMaterialList)) {
      //随机获取一首音乐
      MaterialRespVO materialRespVO = sysMaterialList
          .get(RandomUtil.randomInt(0, sysMaterialList.size()));
      return materialRespVO.getUrl();
    }
    return "";
  }

  private String getRandomImage() {
    //获取系统随机图片
    List<MaterialRespVO> sysMaterialList = materialService
        .getSysMaterialList(MaterialTypeEnum.SYS_TP_B.getCode(), null);
    if (CollectionUtil.isNotEmpty(sysMaterialList)) {
      //随机获取一张图片
      MaterialRespVO materialRespVO = sysMaterialList
          .get(RandomUtil.randomInt(0, sysMaterialList.size()));
      return materialRespVO.getUrl();
    }
    return "";
  }

  @Override
  public List<TaskMaterialDO> getMemberTaskMaterial(Long taskId) {

    return taskMaterialMapper.selectList(
        new LambdaQueryWrapperX<TaskMaterialDO>().eq(TaskMaterialDO::getTaskId, taskId)
            .eq(TaskMaterialDO::getType, TaskMaterialFileType.MATERIAL.getType()));

  }


  private void aiClip(TaskMaterialDO taskMaterialDO) throws Exception {
    String jobId = taskMaterialDO.getJobId();
    //混剪任务
    GetMediaProducingJobResponse mediaProducingJob = iceService
        .getMediaProducingJob(jobId);
    GetMediaProducingJobResponseBodyMediaProducingJob mediaProducingJob1 = mediaProducingJob
        .getBody().getMediaProducingJob();

    String status = mediaProducingJob1.getStatus();
    if ("Success".equals(status)) {
      //成功,处理当前任务状态
      String mediaURL = mediaProducingJob1.getMediaURL();
      taskMaterialMapper.updateById(
          new TaskMaterialDO().setMaterialUrl(mediaURL)
              .setStatus(TaskMaterialFileStatus.SUCCESS.getType())
              .setId(taskMaterialDO.getId()));
      //修改taskOrder 中视频url
      taskOrderMapper.updateById(
          new TaskOrderDO().setVideoUrl(mediaURL)
              .setId(taskMaterialDO.getTaskOrderId()));
    } else if ("Failed".equals(status)) {
      //失败,处理当前任务状态
      taskMaterialMapper.updateById(
          new TaskMaterialDO().setStatus(TaskMaterialFileStatus.FAIL.getType())
              .setId(taskMaterialDO.getId()));
      //todo 生成视频失败
      String message = mediaProducingJob1.getMessage();
      log.error("视频混剪任务失败原因:{},jobId:{}", message, mediaProducingJob1.getJobId());
    } else {
      //处理中,跳出当前循环
      log.info("当前任务还在处理中,jobId:{},mediaProducingJob1:{}", mediaProducingJob1.getJobId(),
          mediaProducingJob1);
    }
  }

  private void digitalClip(TaskMaterialDO taskMaterialDO) throws Exception {
    String jobId = taskMaterialDO.getJobId();
    //根据jobId查询剪辑任务
    GetVideoTaskInfoResponse videoTaskInfo = videoService
        .getVideoTaskInfo(jobId);

    GetVideoTaskInfoResponseBodyData data = videoTaskInfo.body.data;
    String status = data.status;
    log.info("数字人混剪任务查询返回对象:{}", videoTaskInfo);
    //成功
    switch (status) {
      case "3":
        //成功,处理当前任务
        taskMaterialMapper.updateById(
            new TaskMaterialDO().setId(taskMaterialDO.getId())
                .setMaterialUrl(data.taskResult.videoUrl)
                .setStatus(TaskMaterialFileStatus.SUCCESS.getType()));
        //将视频从oss下载,并上传到用户对应的oss桶中
        JSONObject object = new JSONObject();
        object.put("id", taskMaterialDO.getTaskOrderId());
        object.put("taskType", UploadFileTaskType.EXPLORE_TASK);
        object.put("fileUrl", data.taskResult.videoUrl);
        taskClient.createTask(TaskHandlerEnum.ALIYUN_MATERIAL_UPLOAD.getValue(), null,
            object.toString());
        break;
      case "4":
        //失败
      case "5":
        //已取消
      case "6":
        //过期
        //失败,处理当前任务状态
        log.error("数字人混剪数字人生成失败,失败原因:{},taskUuid:{}", videoTaskInfo.body.message,
            jobId);
        taskMaterialMapper.updateById(
            new TaskMaterialDO().setId(taskMaterialDO.getId())
                .setStatus(TaskMaterialFileStatus.FAIL.getType()));
      default:
        break;
    }
  }

  private String getCopy(Long taskId) {
    List<CopyManagementDO> copyManagementDOList = copyManagementMapper
        .selectList(new LambdaQueryWrapperX<CopyManagementDO>()
            .eq(CopyManagementDO::getType, CopywritingType.EXPLORE_TASK.getType())
            .eq(CopyManagementDO::getTaskId, taskId));
    //随机获取一条
    CopyManagementDO copyManagementDO = copyManagementDOList
        .get(RandomUtils.nextInt(0, copyManagementDOList.size()));
    return copyManagementDO.getCopywriting();
  }

  private CopyManagementDO getCopyDO(Long taskId) {
    List<CopyManagementDO> copyManagementDOList = copyManagementMapper
        .selectList(new LambdaQueryWrapperX<CopyManagementDO>()
            .eq(CopyManagementDO::getType, CopywritingType.EXPLORE_TASK.getType())
            .eq(CopyManagementDO::getTaskId, taskId));
    //随机获取一条
    CopyManagementDO copyManagementDO = copyManagementDOList
        .get(RandomUtils.nextInt(0, copyManagementDOList.size()));
    return copyManagementDO;
  }

  private String getMetaHumanCode(Long memberUserId) {
    List<MetaHumanConfigDO> metaHumanConfig = metaHumanConfigService
        .getMetaHumanConfig(UserTypeEnum.MEMBER.getValue(), memberUserId);
    if (CollectionUtils.isNotEmpty(metaHumanConfig)) {
      return metaHumanConfig.get(0).getMetaHumanCode();
    }
    return null;
  }

  private String getMetaHumanDubCode(Long memberUserId) {
    List<MetaHumanConfigDO> metaHumanConfig = metaHumanConfigService
        .getMetaHumanConfig(UserTypeEnum.MEMBER.getValue(), memberUserId);
    if (CollectionUtils.isNotEmpty(metaHumanConfig)) {
      return metaHumanConfig.get(0).getDubCode();
    }
    return null;
  }

  private String submitAudioTo2DAvatarVideoTask(String contentTitle, String copywriting,
      String metaHumanCode,
      SubmitTextTo2DAvatarVideoTaskRequestVideoInfo videoInfo,
      SubmitTextTo2DAvatarVideoTaskRequestAudioInfo audioInfo) throws Exception {

    SubmitTextTo2DAvatarVideoTaskRequest request = new SubmitTextTo2DAvatarVideoTaskRequest();

    request.setTitle(contentTitle);
    request.setText(copywriting);
    request.setVideoInfo(videoInfo);
    request.setAudioInfo(audioInfo);
    request.setAvatarInfo(new SubmitTextTo2DAvatarVideoTaskRequestAvatarInfo()
        .setCode(metaHumanCode));
    //todo 回调地址设置
//    request.setCallback();
    SubmitTextTo2DAvatarVideoTaskResponse submitTextTo2DAvatarVideoTaskResponse = videoService
        .submitTextTo2DAvatarVideoTask(request);
    return submitTextTo2DAvatarVideoTaskResponse.body.data.taskUuid;
  }

  @Override
  public List<TaskMaterialRespVO> getTaskMaterial(Long taskId, Long userId) {
    List<TaskMaterialRespVO> respVOList = new ArrayList<>();
    List<TaskMaterialDO> taskMaterialDOS = this.getMemberTaskMaterial(taskId);
    Map<String, List<TaskMaterialDO>> list = taskMaterialDOS.stream()
        .collect(Collectors.groupingBy(TaskMaterialDO::getFolderName));
    list.forEach((folderName, taskMaterialList) -> {
      List<TaskMaterialListVO> taskMaterialListVOS = TaskMaterialConvert.INSTANCE
          .convertList01(taskMaterialList);
      taskMaterialListVOS.forEach(a -> {
        Long count = taskMaterialDownloadMapper
            .selectCount(new LambdaQueryWrapper<TaskMaterialDownloadDO>()
                .eq(TaskMaterialDownloadDO::getTaskMaterialId, a.getId())
                .eq(TaskMaterialDownloadDO::getMemberUserId, userId));
        a.setIsDownload(count > 0 ? 1 : 0);

        Long totalCount = taskMaterialDownloadMapper
            .selectCount(new LambdaQueryWrapper<TaskMaterialDownloadDO>()
                .eq(TaskMaterialDownloadDO::getTaskMaterialId, a.getId())
            );
        a.setTotalCount(totalCount);

      });
      TaskMaterialRespVO respVO = new TaskMaterialRespVO();
      respVO.setFolderName(folderName);
      respVO.setMaterialListVOS(taskMaterialListVOS);
      respVO.setCount(taskMaterialListVOS.size());
      respVOList.add(respVO);
    });
    return respVOList;
  }


  @Override
  public void materialDownload(Long taskMaterialId, Long userId) {
    Long count = taskMaterialDownloadMapper
        .selectCount(new LambdaQueryWrapper<TaskMaterialDownloadDO>()
            .eq(TaskMaterialDownloadDO::getTaskMaterialId, taskMaterialId)
            .eq(TaskMaterialDownloadDO::getMemberUserId, userId));
    if (count > 0) {
      return;
    }
    TaskMaterialDownloadDO downloadDO = new TaskMaterialDownloadDO();
    downloadDO.setMemberUserId(userId);
    downloadDO.setTaskMaterialId(taskMaterialId);
    taskMaterialDownloadMapper.insert(downloadDO);
  }

  @Override
//  @Cacheable(cacheNames = "user:task:earning:day", key = "#memberId+':'+#date")
  public long getTaskEarningsOfToday(Long memberId, LocalDate date) {
    return taskOrderMapper.selectTaskEarningsOfToday(memberId, date);
  }

  @Override
//  @Cacheable(cacheNames = "user:task:earning:beforetoday", key = "#memberId+':'+#date")
  public long getTaskEarningsOfBeforeToDay(Long memberId, LocalDate date) {
    return taskOrderMapper.selectTaskEarningsOfBeforeToDay(memberId, date);
  }

  @Override
//  @Cacheable(cacheNames = "user:task:earning:predict", key = "#memberId+':'+#date")
  public long getTaskEarningsOfPredict(Long memberId, LocalDate date) {
    return taskOrderMapper.selectTaskEarningsOfPredict(memberId);
  }

  @Override
//  @Cacheable(cacheNames = "user:task:count:total", key = "#memberId+':'+#date")
  public long getTaskCountOfAll(Long memberId, LocalDate date) {
    return taskOrderMapper.selectTaskCountOfAll(memberId);
  }

  @Override
//  @Cacheable(cacheNames = "user:task:count:month", key = "#memberId+':'+#date")
  public long getTaskCountOfMonth(Long memberId, LocalDate date) {
    return taskOrderMapper.selectTaskCountOfMonth(memberId, date);
  }

  @Override
  @Transactional(readOnly = true)
  public TaskOrderPaymentResultRespVO getTaskOrderPaymentResult(Long payOrderId, Long userId) {
    TaskOrderPaymentResultRespVO taskOrderPaymentResultRespVO = new TaskOrderPaymentResultRespVO();
    Long memberId = userId;
    taskOrderPaymentResultRespVO.setPayOrderId(payOrderId);

    SystemArgDO systemArg = systemArgService.getSystemArg();
    if (systemArg == null) {
      log.error("系统参数未配置!");
      throw exception(SYSTEM_ERROR);
    }


    //查看当前支付状态
    TaskApplyDO taskApplyDO = taskApplyMapper.selectOne(
        new LambdaQueryWrapperX<TaskApplyDO>().eq(TaskApplyDO::getPayOrderId, payOrderId));

    if (taskApplyDO == null) {
      throw exception(TASK_PAY_ORDER_NOT_EXISTS);
    }

    Integer applyStatus = taskApplyDO.getApplyStatus();
    taskOrderPaymentResultRespVO.setStatus(applyStatus);
    if (applyStatus.equals(TaskApplyStatus.APPLY_NOT_PAY.getStatus())) {
      return taskOrderPaymentResultRespVO;
    }

    TaskDO taskDO = taskMapper.selectById(taskApplyDO.getTaskId());
    if (taskDO == null) {
      throw exception(TASK_NOT_EXISTS);
    }
    Integer taskType = taskDO.getTaskType();
    taskOrderPaymentResultRespVO.setTaskType(taskType);
    //当天会员的接单数是否超过限制
    Long todayOrderCount = taskOrderMapper.selectCount(new LambdaQueryWrapper<TaskOrderDO>()
        .eq(TaskOrderDO::getMemberUserId, memberId)
        .eq(TaskOrderDO::getTaskType, taskType)
        .between(TaskOrderDO::getCreateTime, LocalDateTimeUtils.getTodayStart(),
            LocalDateTimeUtils.getTodayEnd()));
    Long aerialNum;
    if (TaskType.YUN.getType().equals(taskType)) {
      aerialNum = systemArg.getYunDayOrderNum() - todayOrderCount;
    } else {
      aerialNum = systemArg.getEntityDayOrderNum() - todayOrderCount;
    }

    taskOrderPaymentResultRespVO.setAerialNum(aerialNum);

    return taskOrderPaymentResultRespVO;
  }

  @Override
  public TaskOrderDO getById(Long id) {
    return taskOrderMapper.selectById(id);
  }

  @Override
  @Transactional(rollbackFor = Exception.class)
  public void aiClipVideo(Long taskOrderId) {

    TaskOrderDO taskOrderDO = taskOrderMapper.selectById(taskOrderId);

    if (taskOrderDO == null) {
      throw exception(TASK_SIGN_ORDER_NOT_EXISTS);
    }

    //查询任务信息
    Long taskId = taskOrderDO.getTaskId();
    TaskDO taskDO = taskMapper.selectById(taskId);

    if (taskDO == null) {
      throw exception(TASK_NOT_EXISTS);
    }

    List<TaskMaterialDO> taskMaterialDOList = taskMaterialMapper
        .selectList(new LambdaQueryWrapperX<TaskMaterialDO>()
            .eq(TaskMaterialDO::getTaskOrderId, taskOrderId)
            .eq(TaskMaterialDO::getType, TaskMaterialFileType.AI_CLIP.getType()));

    if (!CollectionUtils.isEmpty(taskMaterialDOList)) {
      log.info("任务视频已经创建taskOrderId:{}", taskOrderId);
      return;
    }
    Long memberUserId = taskOrderDO.getMemberUserId();
    //查素材切片
    List<VideoSliceDO> sliceDOList = videoSliceMapper
        .selectList(new LambdaQueryWrapperX<VideoSliceDO>().eq(VideoSliceDO::getTaskType,
            ActiveTaskType.SHOPPING.getType()).eq(VideoSliceDO::getTaskId, taskId));
    //口播文案
    String copy = getCopy(taskId);
    //计算大致时长
    Integer duration = FileUtils.getTextReadDuration(copy);
    //生成切片
    List<VideoTrackClip> videoTrackClipList = VideoSlicer.getVideoTrackClipList(sliceDOList,
        duration);
    //生成视频结果
    TaskMaterialDO aiClip = new TaskMaterialDO();
    aiClip.setTaskId(taskId);
    aiClip.setTaskOrderId(taskOrderId);
    aiClip.setType(TaskMaterialFileType.AI_CLIP.getType());
    aiClip.setMemberUserId(memberUserId);
    aiClip.setStatus(TaskMaterialFileStatus.PROCESSING.getType());
    //添加一个ai混剪素材
    taskMaterialMapper.insert(aiClip);
    //生成视频
    VideoClipOrderDTO videoClipOrder = createAiClipVideoTask(memberUserId, aiClip.getId(), copy,
        taskDO.getContentTitle(),
        videoTrackClipList);

    TaskMaterialDO updateAiClip = new TaskMaterialDO();
    updateAiClip.setJobId(videoClipOrder.getJobId());
    updateAiClip.setMaterialUrl(videoClipOrder.getMediaUrl());
    updateAiClip.setVideoOrderId(videoClipOrder.getId());
    updateAiClip.setId(aiClip.getId());
    taskMaterialMapper.updateById(updateAiClip);

    //更新使用次数
    sliceDOList.forEach(videoSliceDO -> {
      if (videoSliceDO.getNum() > 0) {
        videoSliceMapper.updateById(new VideoSliceDO().setId(videoSliceDO.getId())
            .setNum(videoSliceDO.getNum()));
      }
    });


  }

  @Override
  @Transactional(rollbackFor = Exception.class)
  public void updateAiClipVideoStatus(Long materialId, Integer duration, Integer status) {
    Integer aiClipStatus;
    if (VideoClipOrderStatus.FINISH.getStatus().equals(status)) {
      aiClipStatus = TaskMaterialFileStatus.SUCCESS.getType();
    } else {
      aiClipStatus = TaskMaterialFileStatus.FAIL.getType();
    }

    TaskMaterialDO taskMaterial = taskMaterialMapper
        .selectOne(new LambdaQueryWrapperX<TaskMaterialDO>()
            .eq(TaskMaterialDO::getId, materialId));

    if (taskMaterial == null) {
      throw exception(TASK_CREATE_RESULT_NOT_EXISTS);
    }

    if (!TaskMaterialFileStatus.PROCESSING.getType().equals(taskMaterial.getStatus())) {
      log.info("当前ai混剪视频已经处理，materialId:{}", materialId);
      return;
    }

    TaskOrderDO taskOrder = taskOrderMapper.selectById(taskMaterial.getTaskOrderId());
    if (taskOrder == null) {
      throw exception(TASK_NOT_EXISTS);
    }
    //变更ai混剪视频结果
    updateVideoStatus(materialId, aiClipStatus, taskOrder.getId(), taskMaterial.getMaterialUrl());

  }

  private void updateVideoStatus(Long materialId,
      Integer aiClipStatus, Long taskOrderId, String mediaURL) {

    TaskMaterialDO updateAiClip = new TaskMaterialDO();
    updateAiClip.setId(materialId);
    updateAiClip.setStatus(aiClipStatus);
    taskMaterialMapper.updateById(updateAiClip);
    if (TaskMaterialFileStatus.SUCCESS.getType().equals(aiClipStatus)) {
      //修改taskOrder 中视频url
      taskOrderMapper.updateById(
          new TaskOrderDO().setVideoUrl(mediaURL)
              .setId(taskOrderId));
    }
  }

  @Override
  @Transactional(rollbackFor = Exception.class)
  public void updateDigitalClipStatus(Long materialId, Integer videoType, Integer duration,
      Integer status) {

    TaskMaterialDO taskMaterial = taskMaterialMapper
        .selectById(materialId);
    if (taskMaterial == null) {
      throw exception(TASK_CREATE_RESULT_NOT_EXISTS);
    }

    if (!TaskMaterialFileStatus.PROCESSING.getType().equals(taskMaterial.getStatus())) {
      log.info("当前数字人视频已经处理，materialId:{}", materialId);
      return;
    }
    if (VideoType.DIGITAL.getType().equals(videoType)) {

      if (VideoClipOrderStatus.FINISH.getStatus().equals(status)) {
        //数字人素材视频生成成功
        taskMaterialMapper.updateById(new TaskMaterialDO().setId(materialId)
            .setStatus(TaskMaterialFileStatus.SUCCESS.getType()));
        //生成数字人混剪任务
        JSONObject object = new JSONObject();
        object.put("id", taskMaterial.getId());
        object.put("taskType", UploadFileTaskType.EXPLORE_TASK);
        taskClient.createTask(TaskHandlerEnum.DIGITAL_CLIP.getValue(), null, object.toString());
      } else {
        //数字人素材视频生成失败
        taskMaterialMapper.updateById(new TaskMaterialDO().setId(materialId)
            .setStatus(TaskMaterialFileStatus.FAIL.getType()));
      }
    } else {
      //剪辑视频
      Integer aiClipStatus;
      if (VideoClipOrderStatus.FINISH.getStatus().equals(status)) {
        aiClipStatus = TaskMaterialFileStatus.SUCCESS.getType();
      } else {
        aiClipStatus = TaskMaterialFileStatus.FAIL.getType();
      }

      TaskOrderDO taskOrderDO = taskOrderMapper.selectById(taskMaterial.getTaskOrderId());
      if (taskOrderDO == null) {
        throw exception(TASK_NOT_EXISTS);
      }
      //变更数字人混剪视频结果
      updateVideoStatus(materialId,
          aiClipStatus,
          taskOrderDO.getId(), taskMaterial.getMaterialUrl());

    }

  }


  private VideoClipOrderDTO createAiClipVideoTask(Long memberUserId, Long materialId, String copy,
      String contentTitle,
      List<VideoTrackClip> videoTrackClipList) {
    AiVideoClip aiVideoClip = new AiVideoClip();
    aiVideoClip.setUserId(memberUserId);
    aiVideoClip.setMaterialId(materialId);
    aiVideoClip.setCopywriting(copy);

    aiVideoClip.setVideoTrackClips(videoTrackClipList);
    //获取随机模版
    TemplateTypeEnum templateType = TemplateTypeEnum
        .getMetaHumanRandomEnumByWeight(Boolean.FALSE);
    aiVideoClip.setTemplateTypeEnum(templateType);

    aiVideoClip.setOrderType(ActiveTaskType.SHOPPING.getType());
//    //设置封面
//    String coverUrl = getCoverUrl(aiVideoTask.getCoverId());
//    aiVideoClip.setCoverImage(coverUrl);

    //背景图片
    String backgroundImageUrl = getRandomImage();
    aiVideoClip.setBgImage(backgroundImageUrl);
    String voice = getRandomVoiceCode();
    aiVideoClip.setDubCode(voice);

    //配音语速
    Integer speechRate = AiSpeechRateAndPitchRateType
        .valueOfType(5).getValue();
    //配音语调
    Integer pitchRate = AiSpeechRateAndPitchRateType
        .valueOfType(5).getValue();
    aiVideoClip.setDubSpeechRate(speechRate);
    aiVideoClip.setDubPitchRate(pitchRate);
    aiVideoClip.setDubGain(1.0F);
    //设置配音音乐
    String pipedMusicUrl = getRandomMusic();
    aiVideoClip.setPipedMusicUrl(pipedMusicUrl);
    aiVideoClip.setContentTitle(contentTitle);
    aiVideoClip
        .setIsFlowerWord(Boolean.TRUE);
    return aiVideoClipService.firstClip(aiVideoClip);

  }

  @Override
  public List<TaskMaterialRespVO> getTaskMaterialOfDownload(Long taskId, Long userId) {
    List<TaskMaterialRespVO> respVOList = new ArrayList<>();
    List<TaskMaterialDO> taskMaterialDOS = taskMaterialMapper.selectByDownload(taskId, userId);
    Map<String, List<TaskMaterialDO>> list = taskMaterialDOS.stream()
        .collect(Collectors.groupingBy(TaskMaterialDO::getFolderName));
    list.forEach((folderName, taskMaterialList) -> {
      List<TaskMaterialListVO> taskMaterialListVOS = TaskMaterialConvert.INSTANCE
          .convertList01(taskMaterialList);
      TaskMaterialRespVO respVO = new TaskMaterialRespVO();
      respVO.setFolderName(folderName);
      respVO.setMaterialListVOS(taskMaterialListVOS);
      respVO.setCount(taskMaterialListVOS.size());
      respVOList.add(respVO);
    });
    return respVOList;
  }



  @Override
  public List<TaskOrderDO> getListForTaskId(List<Long> taskIds) {
    return taskOrderMapper.selectList(new LambdaQueryWrapper<TaskOrderDO>()
                  .in(TaskOrderDO::getTaskId,taskIds)
                  .eq(TaskOrderDO::getSettleStatus,SettleStatusEnum.BE_SETTLE.getCode()));
  }


}
