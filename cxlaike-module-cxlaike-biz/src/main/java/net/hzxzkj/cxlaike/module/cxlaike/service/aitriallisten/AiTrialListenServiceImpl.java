package net.hzxzkj.cxlaike.module.cxlaike.service.aitriallisten;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import net.hzxzkj.cxlaike.framework.mybatis.core.query.LambdaQueryWrapperX;
import net.hzxzkj.cxlaike.framework.security.core.util.SecurityFrameworkUtils;
import net.hzxzkj.cxlaike.framework.tenant.core.context.TenantContextHolder;
import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.aitriallisten.vo.AiTrialListenCreateReqVO;
import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.aitriallisten.vo.AiTrialListenRespVO;
import net.hzxzkj.cxlaike.module.cxlaike.convert.aitriallisten.AiTrialListenConvert;
import net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.aitriallisten.AiTrialListenDO;
import net.hzxzkj.cxlaike.module.cxlaike.dal.mysql.aitriallisten.AiTrialListenMapper;
import net.hzxzkj.cxlaike.module.cxlaike.enums.AiClipStatus;
import net.hzxzkj.cxlaike.module.cxlaike.enums.AiSpeechRateAndPitchRateType;
import net.hzxzkj.cxlaike.module.cxlaike.enums.VideoClipOrderStatus;
import net.hzxzkj.cxlaike.module.cxlaike.service.video.VideoClipHandlerService;
import net.hzxzkj.cxlaike.module.cxlaike.service.video.dto.AudioClip;
import net.hzxzkj.cxlaike.module.cxlaike.service.video.dto.VideoClipOrderDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

/**
 * ai语音试听功能 Service 实现类
 *
 * @author 宵征源码
 */
@Slf4j
@Service
@Validated
public class AiTrialListenServiceImpl implements AiTrialListenService {

  @Resource
  private AiTrialListenMapper aiTrialListenMapper;

  private final VideoClipHandlerService audioClipService;

  public AiTrialListenServiceImpl(
      @Qualifier("audioClipService") VideoClipHandlerService audioClipService) {
    this.audioClipService = audioClipService;
  }

  @Override
  @Transactional(rollbackFor = Exception.class)
  public Long createAiTrialListen(AiTrialListenCreateReqVO createReqVO) {
    // 插入
    AiTrialListenDO aiTrialListen = AiTrialListenConvert.INSTANCE.convert(createReqVO);
    Long loginUserId = TenantContextHolder.getTenantId();
    aiTrialListen.setUserId(loginUserId);
    aiTrialListen.setStatus(AiClipStatus.GENERATING.getStatus());
    aiTrialListenMapper.insert(aiTrialListen);
    //创建试听任务
    Long videoOrderId = createAiTrialListenTask(aiTrialListen);
    aiTrialListenMapper.update(new AiTrialListenDO().setVideoOrderId(videoOrderId),
        new LambdaQueryWrapper<AiTrialListenDO>()
            .eq(AiTrialListenDO::getId, aiTrialListen.getId()));
    // 返回
    return aiTrialListen.getId();
  }

  private Long createAiTrialListenTask(AiTrialListenDO aiTrialListen) {
    AiSpeechRateAndPitchRateType speechRateType = AiSpeechRateAndPitchRateType
        .valueOfType(aiTrialListen.getDubSpeechRate());
    AiSpeechRateAndPitchRateType pitchRateType = AiSpeechRateAndPitchRateType
        .valueOfType(aiTrialListen.getDubPitchRate());
    AudioClip audioClip = new AudioClip();
    audioClip.setUserId(aiTrialListen.getUserId());
    audioClip.setMaterialId(aiTrialListen.getId());
    audioClip.setDubPitchRate(pitchRateType.getValue());
    audioClip.setDubSpeechRate(speechRateType.getValue());
    audioClip.setDubCode(aiTrialListen.getDubCode());
    audioClip.setCopywriting(aiTrialListen.getCopywriting());
    audioClip.setDubGain(aiTrialListen.getDubGain());
    VideoClipOrderDTO videoClipOrder = audioClipService.firstClip(audioClip);
    return videoClipOrder.getId();
  }

  @Override
  @Transactional(readOnly = true)
  public AiTrialListenRespVO getAiTrialListen(Long id) {
    AiTrialListenDO aiTrialListenDO = aiTrialListenMapper.selectOne(
        new LambdaQueryWrapper<AiTrialListenDO>().eq(AiTrialListenDO::getId, id));
    if (aiTrialListenDO == null) {
      return null;
    }
    AiTrialListenRespVO aiTrialListenRespVO = AiTrialListenConvert.INSTANCE
        .convert(aiTrialListenDO);

    if (AiClipStatus.SUCCESS.getStatus().equals(aiTrialListenDO.getStatus())) {
      String videoUrl = audioClipService.getVideoUrl(aiTrialListenDO.getId());
      aiTrialListenRespVO.setMediaUrl(videoUrl);
    }
    return aiTrialListenRespVO;
  }


  @Override
  @Transactional(rollbackFor = Exception.class)
  public void update(Long id, Integer status) {
    Integer aiClipStatus;
    if (VideoClipOrderStatus.FINISH.getStatus().equals(status)) {
      aiClipStatus = AiClipStatus.SUCCESS.getStatus();
    } else {
      aiClipStatus = AiClipStatus.FAILURE.getStatus();
    }
    aiTrialListenMapper.update(
        new AiTrialListenDO().setStatus(aiClipStatus),
        new LambdaQueryWrapperX<AiTrialListenDO>().eq(AiTrialListenDO::getId, id)
            .eq(AiTrialListenDO::getStatus, AiClipStatus.GENERATING.getStatus()));
  }

}
