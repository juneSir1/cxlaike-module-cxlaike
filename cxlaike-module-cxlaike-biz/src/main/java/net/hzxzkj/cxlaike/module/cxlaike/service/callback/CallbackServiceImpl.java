package net.hzxzkj.cxlaike.module.cxlaike.service.callback;

import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import net.hzxzkj.cxlaike.module.cxlaike.controller.admin.callback.vo.ProduceMediaCompleteMessageBody;
import net.hzxzkj.cxlaike.module.cxlaike.controller.admin.callback.vo.ProduceMediaCompleteVO;
import net.hzxzkj.cxlaike.module.cxlaike.service.video.VideoClipService;
import org.springframework.stereotype.Service;

/**
 * 描   述:
 *
 * @author ace
 * @version [版本号, 2023/9/19]
 * @see [相关类/方法]
 * 创建日期: 2023/9/19
 */
@Slf4j
@Service
public class CallbackServiceImpl implements CallbackService{

  @Resource
  private VideoClipService videoClipService;

  @Override
  public void aiVideoCallback(ProduceMediaCompleteVO complete) {
    videoClipService.callback(complete);
  }
}
