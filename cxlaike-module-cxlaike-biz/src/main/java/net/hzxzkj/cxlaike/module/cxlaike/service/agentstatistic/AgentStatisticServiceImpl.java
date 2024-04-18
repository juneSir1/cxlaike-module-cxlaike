package net.hzxzkj.cxlaike.module.cxlaike.service.agentstatistic;

import cn.hutool.core.collection.CollectionUtil;
import net.hzxzkj.cxlaike.framework.common.enums.UserTypeEnum;
import net.hzxzkj.cxlaike.module.cxlaike.controller.app.agentstatistic.vo.ExploringStoresEarningsRespVO;
import net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.task.TaskDO;
import net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.taskext.TaskExtDO;
import net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.taskorder.TaskOrderDO;
import net.hzxzkj.cxlaike.module.cxlaike.dal.mysql.taskext.TaskExtMapper;
import net.hzxzkj.cxlaike.module.cxlaike.service.task.TaskService;
import net.hzxzkj.cxlaike.module.cxlaike.service.taskorder.TaskOrderService;
import net.hzxzkj.cxlaike.module.member.api.user.MemberUserApi;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author jianan.han
 * @date 2023/11/3 下午3:44
 * @description
 */
@Service
@Validated
public class AgentStatisticServiceImpl implements AgentStatisticService {

    @Resource
    private MemberUserApi memberUserApi;

    @Resource
    private TaskService taskService;
    @Resource
    private TaskOrderService taskOrderService;
    @Resource
    private TaskExtMapper taskExtMapper;

    @Override
    public ExploringStoresEarningsRespVO getExploringStoresEarnings(Long userId) {
        ExploringStoresEarningsRespVO respVO = new ExploringStoresEarningsRespVO();

        List<Long> merchantIds = memberUserApi.getListByParentUserId(userId, UserTypeEnum.MERCHANT);
        if (CollectionUtil.isEmpty(merchantIds)) {
            return respVO;
        }
        Long merchantCountOfPublishTask = taskService.getMerchantCountOfPublishTask(merchantIds);
        respVO.setMerchantCount(merchantIds.size());
        respVO.setValidMerchantCount(merchantCountOfPublishTask == null ? 0 : merchantCountOfPublishTask.intValue());
        List<TaskDO> taskDOS = taskService.getTaskListByMerchantIds(merchantIds);
        if (CollectionUtil.isEmpty(taskDOS)) {
            return respVO;
        }
        List<TaskOrderDO> taskOrderDOS = taskOrderService.getListForTaskId(taskDOS.stream().map(TaskDO::getId).collect(Collectors.toList()));
        if(CollectionUtil.isEmpty(taskOrderDOS)){
            return respVO;
        }
        Long serviceFee = 0L;
        Long shareBenefit = 0L;
        for (TaskOrderDO taskOrderDO : taskOrderDOS) {
            TaskExtDO taskExtDO = taskExtMapper.selectById(taskOrderDO.getTaskExtId());
            if (taskExtDO != null) {
                serviceFee = serviceFee + taskExtDO.getTotalFee();
                Long merchantServiceFee = taskExtDO.getTotalFee() * taskExtDO.getMerchantRatio() / 100;
                shareBenefit = shareBenefit + merchantServiceFee;
            }

        }
        respVO.setDrReceivingOrdersCount(taskOrderDOS.size());
        respVO.setServiceFee(serviceFee);
        respVO.setShareBenefit(shareBenefit);
        return respVO;
    }
}
