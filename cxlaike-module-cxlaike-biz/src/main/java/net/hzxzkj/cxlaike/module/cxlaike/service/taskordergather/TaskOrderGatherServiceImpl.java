package net.hzxzkj.cxlaike.module.cxlaike.service.taskordergather;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import net.hzxzkj.cxlaike.framework.mybatis.core.query.LambdaQueryWrapperX;
import net.hzxzkj.cxlaike.framework.tenant.core.aop.TenantIgnore;
import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.taskordergather.vo.TaskOrderGatherCreateReqVO;
import net.hzxzkj.cxlaike.module.cxlaike.convert.taskordergather.TaskOrderGatherConvert;
import net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.taskordergather.TaskOrderGatherDO;
import net.hzxzkj.cxlaike.module.cxlaike.dal.mysql.taskordergather.TaskOrderGatherMapper;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;

/**
 * 用户视频集合 Service 实现类
 *
 * @author 宵征源码
 */
@Service
@Validated
public class TaskOrderGatherServiceImpl implements TaskOrderGatherService {

    @Resource
    private TaskOrderGatherMapper taskOrderGatherMapper;

    @Override
    public void createGather(TaskOrderGatherCreateReqVO reqVO) {
        TaskOrderGatherDO convert = TaskOrderGatherConvert.INSTANCE.convert(reqVO);
        taskOrderGatherMapper.insert(convert);
    }

    @Override
    @TenantIgnore
    public void deleteGather(Long taskId, Long orderId, Integer orderType) {
        taskOrderGatherMapper.delete(new LambdaQueryWrapper<TaskOrderGatherDO>()
                .eq(TaskOrderGatherDO::getTaskId,taskId)
                .eq(TaskOrderGatherDO::getOrderId,orderId)
                .eq(TaskOrderGatherDO::getOrderType,orderType));
    }
}
