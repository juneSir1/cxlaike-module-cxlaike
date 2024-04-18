package net.hzxzkj.cxlaike.module.cxlaike.service.template;

import cn.hutool.json.JSONUtil;
import com.aliyun.ice20201109.models.SubmitMediaProducingJobRequest;
import net.hzxzkj.cxlaike.framework.ice.core.entity.outputmediaconfig.OutputMediaConfig;
import net.hzxzkj.cxlaike.framework.ice.core.entity.timeline.Timeline;
import net.hzxzkj.cxlaike.module.cxlaike.service.template.dto.VideoSynthesisMetaHumanDTO;

public interface VideoSynthesisMetaHumanService {


  Timeline template(VideoSynthesisMetaHumanDTO videoSynthesisMetaHumanDTO);

  default SubmitMediaProducingJobRequest submitMediaProducingJobRequest(String mediaURL,
      Integer bitrate, Timeline timeline) {
    SubmitMediaProducingJobRequest submitMediaProducingJobRequest = new SubmitMediaProducingJobRequest();
    submitMediaProducingJobRequest.setTimeline(JSONUtil.toJsonStr(timeline));
    OutputMediaConfig outputMediaConfig = new OutputMediaConfig();
    outputMediaConfig.setMediaURL(mediaURL);
    outputMediaConfig.setWidth(1080);
    outputMediaConfig.setHeight(1920);
    outputMediaConfig.setBitrate(bitrate);
    submitMediaProducingJobRequest.setOutputMediaConfig(JSONUtil.toJsonStr(outputMediaConfig));
    return submitMediaProducingJobRequest;
  }


}
