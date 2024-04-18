package net.hzxzkj.cxlaike.module.cxlaike.handler;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import net.hzxzkj.cxlaike.module.cxlaike.enums.SwitchType;
import net.hzxzkj.cxlaike.module.cxlaike.service.task.TaskService;
import net.hzxzkj.cxlaike.module.notify.dto.TaskHandlerResult;
import net.hzxzkj.cxlaike.module.notify.enums.TaskHandlerEnum;
import net.hzxzkj.cxlaike.module.notify.handler.TaskHandler;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 描   述:
 *
 * @author ace
 * @version [版本号, 2023/9/8]
 * @see [相关类/方法]
 * 创建日期: 2023/9/8
 */
@Slf4j
@Service
public class TaskTaskHandler implements TaskHandler {

  @Lazy
  @Resource
  private TaskService taskService;

  @Override
  public String getType() {
    return TaskHandlerEnum.UPDATE_MERCHANT_TASK_STATUS.getValue();
  }

  @Override
  public TaskHandlerResult invoke(String params) {
    try {
      JSONObject object = JSONUtil.parseObj(params);
      taskService
          .updateTaskStatus(object.getLong("taskId"),
              object.get("switchType", SwitchType.class));
    } catch (Exception e) {
      e.printStackTrace();
      return new TaskHandlerResult(false, e.getMessage());
    }
    return new TaskHandlerResult(true, null);
  }
}
