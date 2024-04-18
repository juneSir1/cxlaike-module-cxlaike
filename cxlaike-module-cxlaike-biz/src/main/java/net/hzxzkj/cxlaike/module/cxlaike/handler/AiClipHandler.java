package net.hzxzkj.cxlaike.module.cxlaike.handler;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import net.hzxzkj.cxlaike.module.cxlaike.enums.ActiveTaskType;
import net.hzxzkj.cxlaike.module.cxlaike.service.aivideotask.AiVideoTaskService;
import net.hzxzkj.cxlaike.module.cxlaike.service.taskorder.TaskOrderService;
import net.hzxzkj.cxlaike.module.notify.dto.TaskHandlerResult;
import net.hzxzkj.cxlaike.module.notify.enums.TaskHandlerEnum;
import net.hzxzkj.cxlaike.module.notify.handler.TaskHandler;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class AiClipHandler implements TaskHandler {

  @Lazy
  @Resource
  private AiVideoTaskService aiVideoService;
  @Lazy
  @Resource
  private TaskOrderService taskOrderService;

  @Override
  public String getType() {
    return TaskHandlerEnum.AI_CLIP.getValue();
  }

  @Override
  public TaskHandlerResult invoke(String params) {
    try {
      JSONObject object = JSONUtil.parseObj(params);
      Integer taskType = object.getInt("type");
      Long taskId = object.getLong("id");
      if (ActiveTaskType.CLIP.getType().equals(taskType)) {
        aiVideoService.aiClipVideo(taskId);
      } else if (ActiveTaskType.AI_MATERIAL_CLIP.getType().equals(taskType)) {
        aiVideoService.aiMaterialVideoClipVideo(taskId);
      } else {
        taskOrderService.aiClipVideo(taskId);
      }
    } catch (Exception e) {
      e.printStackTrace();
      return new TaskHandlerResult(false, e.getMessage());
    }
    return new TaskHandlerResult(true, null);
  }


}
