package net.hzxzkj.cxlaike.module.cxlaike.service.video;

import com.aliyun.ice20201109.models.GetMediaProducingJobResponse;
import com.aliyun.ice20201109.models.GetMediaProducingJobResponseBody.GetMediaProducingJobResponseBodyMediaProducingJob;
import java.util.List;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import net.hzxzkj.cxlaike.framework.common.util.json.JsonUtils;
import net.hzxzkj.cxlaike.framework.ice.core.client.IceService;
import net.hzxzkj.cxlaike.framework.mybatis.core.query.LambdaQueryWrapperX;
import net.hzxzkj.cxlaike.module.cxlaike.controller.admin.callback.vo.ProduceMediaCompleteMessageBody;
import net.hzxzkj.cxlaike.module.cxlaike.controller.admin.callback.vo.ProduceMediaCompleteVO;
import net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.videocliporder.VideoClipOrderDO;
import net.hzxzkj.cxlaike.module.cxlaike.dal.mysql.videocliporder.VideoClipOrderMapper;
import net.hzxzkj.cxlaike.module.cxlaike.enums.VideoClipOrderStatus;
import net.hzxzkj.cxlaike.module.cxlaike.utils.douyin.FileUtils;
import net.hzxzkj.cxlaike.module.notify.client.TaskClient;
import net.hzxzkj.cxlaike.module.notify.enums.TaskHandlerEnum;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 描   述:
 *
 * @author ace
 * @version [版本号, 2023/10/10]
 * @see [相关类/方法]
 * 创建日期: 2023/10/10
 */
@Slf4j
@Service
public class VideoClipServiceImpl implements VideoClipService {

  @Resource
  @Lazy
  private TaskClient taskClient;

  @Resource
  private VideoClipOrderMapper videoClipOrderMapper;

  @Resource
  private IceService iceService;

  /**
   * 三方回调成功code
   */
  private final static String SUCCESS_CODE = "Success";


  @Override
  @Transactional(readOnly = true)
  public VideoClipOrderDO getVideoClipOrder(Long id) {
    return videoClipOrderMapper.selectById(id);
  }

  @Override
  @Transactional(rollbackFor = Exception.class)
  public void callback(ProduceMediaCompleteVO complete) {

    ProduceMediaCompleteMessageBody messageBody = complete.getMessageBody();
    String jobId = messageBody.getJobId();
    String status = messageBody.getStatus();

    VideoClipOrderDO videoClipOrderDO = videoClipOrderMapper.selectOne(
        new LambdaQueryWrapperX<VideoClipOrderDO>().eq(VideoClipOrderDO::getJobId, jobId));

    if (videoClipOrderDO == null) {
      log.error("视频剪辑订单不存在,jobId:{}", jobId);
      return;
    }
    String response = JsonUtils.toJsonString(complete);
    if (VideoClipOrderStatus.PROCESSING.getStatus().equals(videoClipOrderDO.getStatus())) {
      if (SUCCESS_CODE.equals(status)) {
        //查询文件时长
        Integer fileLength = FileUtils.getFileLength(videoClipOrderDO.getMediaUrl());
        int updateNum = videoClipOrderMapper.update(new VideoClipOrderDO()
                .setStatus(VideoClipOrderStatus.FINISH.getStatus())
                .setDuration(fileLength)
                .setResponse(response),
            new LambdaQueryWrapperX<VideoClipOrderDO>()
                .eq(VideoClipOrderDO::getId, videoClipOrderDO.getId())
                .eq(VideoClipOrderDO::getStatus, VideoClipOrderStatus.PROCESSING.getStatus()));
        if (updateNum != 1) {
          //log.info("视频剪辑订单状态已变更,jobId:{},status:{}", jobId, videoClipOrderDO.getStatus());
          return;
        }
      } else {
        int updateNum = videoClipOrderMapper.update(new VideoClipOrderDO()
                .setStatus(VideoClipOrderStatus.FAIL.getStatus())
                .setResponse(response),
            new LambdaQueryWrapperX<VideoClipOrderDO>()
                .eq(VideoClipOrderDO::getId, videoClipOrderDO.getId())
                .eq(VideoClipOrderDO::getStatus, VideoClipOrderStatus.PROCESSING.getStatus()));
        if (updateNum != 1) {
          //log.info("视频剪辑订单状态已变更,jobId:{},status:{}", jobId, videoClipOrderDO.getStatus());
          return;
        }
      }
      //变更任务状态
      taskClient
          .createTask(TaskHandlerEnum.VIDEO_ORDER_UPDATE.getValue(), null,
              videoClipOrderDO.getId().toString());
    } else {
      log.info("视频剪辑订单状态已变更,jobId:{},status:{}", jobId, videoClipOrderDO.getStatus());
    }

  }

  @Override
  @Transactional(rollbackFor = Exception.class)
  public void checkDeal() throws Exception {
    List<VideoClipOrderDO> videoClipOrderList = videoClipOrderMapper
        .selectList(new LambdaQueryWrapperX<VideoClipOrderDO>().eq(VideoClipOrderDO::getStatus,
            VideoClipOrderStatus.PROCESSING.getStatus()));

    if (videoClipOrderList == null || videoClipOrderList.isEmpty()) {
      // 当前没有待处理的剪辑视频任务
      return;
    }

    for (VideoClipOrderDO videoClipOrderDO : videoClipOrderList) {
      String jobId = videoClipOrderDO.getJobId();
      GetMediaProducingJobResponse mediaProducingJob = iceService.getMediaProducingJob(jobId);
      GetMediaProducingJobResponseBodyMediaProducingJob mediaProducingJob1 = mediaProducingJob
          .getBody().getMediaProducingJob();
      String status = mediaProducingJob1.getStatus();

      String response = JsonUtils.toJsonString(mediaProducingJob);
      if ("Success".equals(status)) {
        //成功,处理当前任务状态
        int updateNum = videoClipOrderMapper.update(new VideoClipOrderDO()
                .setStatus(VideoClipOrderStatus.FINISH.getStatus())
                .setDuration(mediaProducingJob1.duration.intValue())
                .setResponse(response),
            new LambdaQueryWrapperX<VideoClipOrderDO>()
                .eq(VideoClipOrderDO::getId, videoClipOrderDO.getId())
                .eq(VideoClipOrderDO::getStatus, VideoClipOrderStatus.PROCESSING.getStatus()));
        if (updateNum != 1) {
          log.info("视频剪辑订单状态已变更,jobId:{},status:{}", jobId, videoClipOrderDO.getStatus());
          continue;
        }
        //变更任务状态
        taskClient
            .createTask(TaskHandlerEnum.VIDEO_ORDER_UPDATE.getValue(), null,
                videoClipOrderDO.getId().toString());
      }
      if ("Failed".equals(status)) {
        //失败,处理当前任务状态
        int updateNum = videoClipOrderMapper.update(new VideoClipOrderDO()
                .setStatus(VideoClipOrderStatus.FAIL.getStatus())
                .setResponse(response),
            new LambdaQueryWrapperX<VideoClipOrderDO>()
                .eq(VideoClipOrderDO::getId, videoClipOrderDO.getId())
                .eq(VideoClipOrderDO::getStatus, VideoClipOrderStatus.PROCESSING.getStatus()));
        if (updateNum != 1) {
          log.error("视频剪辑订单状态已变更,jobId:{},status:{}", jobId, videoClipOrderDO.getStatus());
          continue;
        }
        //变更任务状态
        taskClient
            .createTask(TaskHandlerEnum.VIDEO_ORDER_UPDATE.getValue(), null,
                videoClipOrderDO.getId().toString());
      }
    }
  }

}
