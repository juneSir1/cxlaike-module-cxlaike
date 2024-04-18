package net.hzxzkj.cxlaike.module.cxlaike.service.aivideotask;

import java.util.List;
import javax.validation.Valid;
import net.hzxzkj.cxlaike.framework.common.pojo.PageResult;
import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.aivideoresult.vo.AiMaterialClipVideoResultPageReqVO;
import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.aivideoresult.vo.AiMaterialClipVideoResultRespVO;
import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.aivideoresult.vo.AiVideoResultRespVO;
import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.aivideotask.vo.*;
import net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.aivideotask.AiVideoTaskDO;
import org.springframework.transaction.annotation.Transactional;

/**
 * ai视频任务 Service 接口
 *
 * @author 宵征源码
 */
public interface AiVideoTaskService {

  /**
   * 获得ai视频任务
   *
   * @param id 编号
   * @return ai视频任务
   */
  AiVideoTaskRespVO getAiVideoTask(Long id);


  /**
   * 获得ai素材混剪视频任务
   *
   * @param id 编号
   * @return ai素材混剪视频任务
   */
  AiMaterialClipVideoTaskRespVO getAiMaterialClipVideoTask(Long id);


  /**
   * 获得ai视频任务分页
   *
   * @param pageReqVO 分页查询
   * @return ai视频任务分页
   */
  PageResult<AiVideoTaskPageRespVO> getAiVideoTaskPage(AiVideoTaskPageReqVO pageReqVO);


  void uploadFileTask(Long taskId, String oldMediaUrl);

  @Transactional(rollbackFor = Exception.class)
  void updateAiClipVideoStatusForMaterialClip(Long materialId, Integer duration, Integer status);

  PageResult<AiVideoResultRespVO> getAiVideoTaskResultPage(AiVideoTaskResultPageReqVO pageVO);


  AiMaterialClipVideoResultRespVO getAiMaterialClipVideoTaskResultPage(AiMaterialClipVideoResultPageReqVO pageVO);

  void deleteAiVideoTaskResult(List<Long> ids);

  boolean templateAiVideoTaskResult(Long id);

  Long createDigitalClipOnlyVideoTask(DigitalClipOnlyVideoCreateReqVO createReqVO);


  void updateDigitalClipOnlyStatus(Long materialId,Integer duration, Integer status);

  void updateDigitalClipStatus(Long materialId, Integer videoType, Integer duration,Integer status);

  Long createDigitalClipVideoTask(DigitalClipVideoCreateReqVO createReqVO);

  void newDigitalClipHandle(Long taskId)throws Exception;

  /**
   * 创建视频混剪任务 june
   * @param createReqVO
   * @return
   */
  Long createAiClipVideoTask(AiClipVideoTaskCreateReqVO createReqVO);


  Long createAiMaterialClipVideoTask(AiMaterialClipVideoTaskCreateReqVO createReqVO);

  List<Integer> previewCount(AiMaterialClipVideoTaskCreateReqVO createReqVO);

  AiVideoTaskPreviewRespVO previewAiMaterialClipVideoTask(AiMaterialClipVideoTaskCreateReqVO createReqVO);


  void cancelPreview(Long aiTaskId);

  void aiClipVideo(Long taskId);

  /**
   * 视频合成（含全局字幕、随镜头组、固定时长）
   * @param taskId
   */
  void aiMaterialVideoClipVideo(Long taskId);

  void updateAiClipVideoStatus(Long materialId, Integer duration, Integer status);
}
