package net.hzxzkj.cxlaike.module.cxlaike.handler;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import net.hzxzkj.cxlaike.module.cxlaike.enums.UploadFileTaskType;
import net.hzxzkj.cxlaike.module.cxlaike.service.aivideotask.AiVideoTaskService;
import net.hzxzkj.cxlaike.module.cxlaike.service.taskorder.TaskOrderService;
import net.hzxzkj.cxlaike.module.notify.dto.TaskHandlerResult;
import net.hzxzkj.cxlaike.module.notify.enums.TaskHandlerEnum;
import net.hzxzkj.cxlaike.module.notify.handler.TaskHandler;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 描   述: 数字人混剪
 *
 * @author ace
 * @version [版本号, 2023/9/10]
 * @see [相关类/方法]
 * 创建日期: 2023/9/10
 */
@Slf4j
@Service
public class DigitalClipHandler implements TaskHandler {

  @Resource
  private AiVideoTaskService aiVideoTaskService;
  @Resource
  private TaskOrderService taskOrderService;

  @Override
  public String getType() {
    return TaskHandlerEnum.DIGITAL_CLIP.getValue();
  }

  @Override
  public TaskHandlerResult invoke(String params) {
    try {
      JSONObject jsonObject = JSONUtil.parseObj(params);
      UploadFileTaskType taskType = jsonObject.get("taskType", UploadFileTaskType.class);
      Long id = jsonObject.getLong("id");

      if (UploadFileTaskType.AI_VIDEO.equals(taskType)) {
        //ai视频生成任务,数字人混剪处理
        aiVideoTaskService.newDigitalClipHandle(id);
      } else {
        //探店任务,数字人混剪
        taskOrderService.newDigitalClipHandle(id);
      }
    } catch (Exception e) {
      e.printStackTrace();
      return new TaskHandlerResult(false, e.getMessage());
    }
    return new TaskHandlerResult(true, null);
  }
}
