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
 * 描   述:
 *
 * @author ace
 * @version [版本号, 2023/9/9]
 * @see [相关类/方法]
 * 创建日期: 2023/9/9
 */
@Slf4j
@Service
public class UpdateFileTaskHandler implements TaskHandler {

  @Resource
  private AiVideoTaskService aiVideoTaskService;
  @Resource
  private TaskOrderService taskOrderService;

  @Override
  public String getType() {
    return TaskHandlerEnum.ALIYUN_MATERIAL_UPLOAD.getValue();
  }

  @Override
  public TaskHandlerResult invoke(String params) {
    try {
      JSONObject object = JSONUtil.parseObj(params);
      UploadFileTaskType taskType = object.get("taskType", UploadFileTaskType.class);
      Long id = object.getLong("id");
      String fileUrl = object.getStr("fileUrl");
      if (UploadFileTaskType.AI_VIDEO.equals(taskType)) {
        //ai视频生成数字人视频生成
        aiVideoTaskService.uploadFileTask(id,fileUrl);
      } else {
        //探店任务
        taskOrderService.uploadFileTask(id,fileUrl);
      }
    } catch (Exception e) {
      e.printStackTrace();
      return new TaskHandlerResult(false, e.getMessage());
    }
    return new TaskHandlerResult(true, null);
  }
}
