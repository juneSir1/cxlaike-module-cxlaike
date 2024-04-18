package net.hzxzkj.cxlaike.module.cxlaike.job;

import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import net.hzxzkj.cxlaike.framework.quartz.core.handler.JobHandler;
import net.hzxzkj.cxlaike.framework.tenant.core.job.TenantJob;
import net.hzxzkj.cxlaike.module.cxlaike.service.taskorder.TaskOrderService;
import org.springframework.stereotype.Component;

/**
 * 描   述:
 *
 * @author ace
 * @version [版本号, 2023/9/13]
 * @see [相关类/方法]
 * 创建日期: 2023/9/13
 */
@Component("taskVideoTaskJob")
@TenantJob // 标记多租户
@Slf4j
public class TaskVideoTaskJob implements JobHandler {

  @Resource
  private TaskOrderService taskOrderService;

  @Override
  public String execute(String param) throws Exception {
    taskOrderService.checkTaskVideoResults();
    return "SUCCESS";
  }
}
