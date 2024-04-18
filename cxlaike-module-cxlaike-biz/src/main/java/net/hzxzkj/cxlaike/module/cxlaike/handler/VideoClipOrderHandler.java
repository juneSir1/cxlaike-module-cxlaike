package net.hzxzkj.cxlaike.module.cxlaike.handler;

import static net.hzxzkj.cxlaike.framework.common.exception.util.ServiceExceptionUtil.exception;
import static net.hzxzkj.cxlaike.module.cxlaike.enums.ErrorCodeConstants.VIDEO_ORDER_NOT_EXISTS;

import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.videocliporder.VideoClipOrderDO;
import net.hzxzkj.cxlaike.module.cxlaike.enums.ActiveTaskType;
import net.hzxzkj.cxlaike.module.cxlaike.enums.AiClipType;
import net.hzxzkj.cxlaike.module.cxlaike.service.aitriallisten.AiTrialListenService;
import net.hzxzkj.cxlaike.module.cxlaike.service.aivideotask.AiVideoTaskService;
import net.hzxzkj.cxlaike.module.cxlaike.service.taskorder.TaskOrderService;
import net.hzxzkj.cxlaike.module.cxlaike.service.video.VideoClipService;
import net.hzxzkj.cxlaike.module.notify.dto.TaskHandlerResult;
import net.hzxzkj.cxlaike.module.notify.enums.TaskHandlerEnum;
import net.hzxzkj.cxlaike.module.notify.handler.TaskHandler;
import org.springframework.stereotype.Service;

/**
 * 描   述:
 *
 * @author ace
 * @version [版本号, 2023/10/11]
 * @see [相关类/方法]
 * 创建日期: 2023/10/11
 */
@Slf4j
@Service
public class VideoClipOrderHandler implements TaskHandler {

  @Resource
  private AiTrialListenService aiTrialListenService;

  @Resource
  private AiVideoTaskService aiVideoTaskService;

  @Resource
  private VideoClipService videoClipService;

  @Resource
  private TaskOrderService taskOrderService;

  @Override
  public String getType() {
    return TaskHandlerEnum.VIDEO_ORDER_UPDATE.getValue();
  }

  @Override
  public TaskHandlerResult invoke(String params) {
    Long videoOrderId = Long.parseLong(params);

    VideoClipOrderDO videoClipOrder = videoClipService.getVideoClipOrder(videoOrderId);

    if (videoClipOrder == null) {
      log.error("视频剪辑订单不存在, videoOrderId:{}", videoOrderId);
      throw exception(VIDEO_ORDER_NOT_EXISTS);
    }
    Integer orderType = videoClipOrder.getOrderType();
    Integer taskType = videoClipOrder.getTaskType();
    //剪辑任务
    if (ActiveTaskType.CLIP.getType().equals(orderType)) {
      if (AiClipType.AUDIO_CLIP.getType().equals(taskType)) {
        //试听音频剪辑
        aiTrialListenService
            .update(videoClipOrder.getMaterialId(), videoClipOrder.getStatus());
      } else if (AiClipType.AI_CLIP.getType().equals(taskType)) {
        //ai剪辑
        aiVideoTaskService
            .updateAiClipVideoStatus(videoClipOrder.getMaterialId(), videoClipOrder.getDuration(),
                videoClipOrder.getStatus());
      }  else if (AiClipType.AI_MATERIAL_CLIP.getType().equals(taskType)) {
        //ai素材剪辑
        aiVideoTaskService
            .updateAiClipVideoStatusForMaterialClip(videoClipOrder.getMaterialId(), videoClipOrder.getDuration(),
                videoClipOrder.getStatus());
      } else if (AiClipType.DIGITAL_CLIP.getType().equals(taskType)) {
        //数字人混剪
        aiVideoTaskService
            .updateDigitalClipStatus(videoClipOrder.getMaterialId(), videoClipOrder.getVideoType(),
                videoClipOrder.getDuration(),
                videoClipOrder.getStatus());
      } else if (AiClipType.DIGITAL_CLIP_ONLY.getType().equals(taskType)) {
        //数字人口播
        aiVideoTaskService.updateDigitalClipOnlyStatus(videoClipOrder.getMaterialId(),
            videoClipOrder.getDuration(),
            videoClipOrder.getStatus());
      }
    } else {
      //探店任务
      if (AiClipType.AI_CLIP.getType().equals(taskType)) {
        //ai剪辑
        taskOrderService
            .updateAiClipVideoStatus(videoClipOrder.getMaterialId(), videoClipOrder.getDuration(),
                videoClipOrder.getStatus());
      } else if (AiClipType.DIGITAL_CLIP.getType().equals(taskType)) {
        //数字人混剪
        taskOrderService
            .updateDigitalClipStatus(videoClipOrder.getMaterialId(), videoClipOrder.getVideoType(),
                videoClipOrder.getDuration(),
                videoClipOrder.getStatus());
      }
    }

    return new TaskHandlerResult(true, null);
  }
}
