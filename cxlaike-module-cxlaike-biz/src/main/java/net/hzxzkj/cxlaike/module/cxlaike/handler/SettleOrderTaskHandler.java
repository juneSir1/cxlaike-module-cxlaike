package net.hzxzkj.cxlaike.module.cxlaike.handler;

import lombok.extern.slf4j.Slf4j;
import net.hzxzkj.cxlaike.framework.common.util.json.JsonUtils;
import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.taskorder.vo.SettleOrderHandlerReqVO;
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
public class SettleOrderTaskHandler implements TaskHandler {

    @Lazy
    @Resource
    private TaskOrderService taskOrderService;
    @Override
    public String getType() {
        return TaskHandlerEnum.SETTLE_ORDER.getValue();
    }

    @Override
    public TaskHandlerResult invoke(String params) {
        try {
            log.info("结算开始+++++params:{}",params);
            SettleOrderHandlerReqVO reqVO = JsonUtils.parseObject(params,SettleOrderHandlerReqVO.class);
            taskOrderService.settleForTaskOrder(reqVO.getId(),reqVO.getMemberId(), LocalDate.now());
        }catch (Exception e){
            e.printStackTrace();
            return new TaskHandlerResult(false,e.getMessage());
        }
        return new TaskHandlerResult(true,null);
    }

}
