package net.hzxzkj.cxlaike.module.cxlaike.service.taskordergather;

import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.taskordergather.vo.TaskOrderGatherCreateReqVO;

/**
 * 用户视频集合 Service 接口
 *
 * @author 宵征源码
 */
public interface TaskOrderGatherService {

    void createGather(TaskOrderGatherCreateReqVO reqVO);

    void deleteGather(Long taskId,Long orderId,Integer orderType);
}
