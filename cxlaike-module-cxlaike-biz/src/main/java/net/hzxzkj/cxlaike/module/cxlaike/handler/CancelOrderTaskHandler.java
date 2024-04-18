package net.hzxzkj.cxlaike.module.cxlaike.handler;

import lombok.extern.slf4j.Slf4j;
import net.hzxzkj.cxlaike.framework.common.util.date.DateUtils;
import net.hzxzkj.cxlaike.framework.common.util.json.JsonUtils;
import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.taskorder.vo.CancelOrderHandlerReqVO;
import net.hzxzkj.cxlaike.module.cxlaike.enums.taskorder.CancelTypeEnum;
import net.hzxzkj.cxlaike.module.cxlaike.service.taskorder.TaskOrderService;
import net.hzxzkj.cxlaike.module.notify.dto.TaskHandlerResult;
import net.hzxzkj.cxlaike.module.notify.enums.TaskHandlerEnum;
import net.hzxzkj.cxlaike.module.notify.handler.TaskHandler;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Slf4j
@Service
public class CancelOrderTaskHandler implements TaskHandler {

    @Lazy
    @Resource
    private TaskOrderService taskOrderService;
    @Override
    public String getType() {
        return TaskHandlerEnum.CANCEL_ORDER.getValue();
    }

    @Override
    public TaskHandlerResult invoke(String params) {
        try {
            CancelOrderHandlerReqVO reqVO = JsonUtils.parseObject(params,CancelOrderHandlerReqVO.class);
            taskOrderService.cancelOrderBySystem(reqVO.getId()
                    , CancelTypeEnum.getEnumByCode(reqVO.getCancelType())
                    ,reqVO.getMemberId(), DateUtils.getYearAndMonth());
        }catch (Exception e){
            e.printStackTrace();
            return new TaskHandlerResult(false,e.getMessage());
        }
        return new TaskHandlerResult(true,null);
    }


}
