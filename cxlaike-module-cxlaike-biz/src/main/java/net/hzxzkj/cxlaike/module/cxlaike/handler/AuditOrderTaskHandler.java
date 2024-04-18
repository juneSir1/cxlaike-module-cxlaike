package net.hzxzkj.cxlaike.module.cxlaike.handler;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import net.hzxzkj.cxlaike.module.cxlaike.service.taskorder.TaskOrderService;
import net.hzxzkj.cxlaike.module.notify.dto.TaskHandlerResult;
import net.hzxzkj.cxlaike.module.notify.enums.TaskHandlerEnum;
import net.hzxzkj.cxlaike.module.notify.handler.TaskHandler;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDate;

@Slf4j
@Service
public class AuditOrderTaskHandler implements TaskHandler {

    @Lazy
    @Resource
    private TaskOrderService taskOrderService;
    @Override
    public String getType() {
        return TaskHandlerEnum.AUDIT_ORDER.getValue();
    }

    @Override
    public TaskHandlerResult invoke(String params) {
        try {
            JSONObject object = JSONUtil.parseObj(params);
            taskOrderService.auditOrderBySystem(object.getLong("id"),object.getLong("memberId"), LocalDate.now());
        }catch (Exception e){
            e.printStackTrace();
            return new TaskHandlerResult(false,e.getMessage());
        }
        return new TaskHandlerResult(true,null);
    }


}
