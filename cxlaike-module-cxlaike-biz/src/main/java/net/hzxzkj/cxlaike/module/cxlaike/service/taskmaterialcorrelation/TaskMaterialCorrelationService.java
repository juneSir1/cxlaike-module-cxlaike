package net.hzxzkj.cxlaike.module.cxlaike.service.taskmaterialcorrelation;

import java.util.*;
import javax.validation.*;
import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.taskmaterialcorrelation.vo.*;
import net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.taskmaterialcorrelation.TaskMaterialCorrelationDO;
import net.hzxzkj.cxlaike.framework.common.pojo.PageResult;

/**
 * 商家任务与素材关联 Service 接口
 *
 * @author 宵征源码
 */
public interface TaskMaterialCorrelationService {

    /**
     * 创建商家任务与素材关联
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createTaskMaterialCorrelation(@Valid TaskMaterialCorrelationCreateReqVO createReqVO);

    /**
     * 更新商家任务与素材关联
     *
     * @param updateReqVO 更新信息
     */
    void updateTaskMaterialCorrelation(@Valid TaskMaterialCorrelationUpdateReqVO updateReqVO);

    /**
     * 删除商家任务与素材关联
     *
     * @param id 编号
     */
    void deleteTaskMaterialCorrelation(Long id);

    /**
     * 获得商家任务与素材关联
     *
     * @param id 编号
     * @return 商家任务与素材关联
     */
    TaskMaterialCorrelationDO getTaskMaterialCorrelation(Long id);

    /**
     * 获得商家任务与素材关联列表
     *
     * @param ids 编号
     * @return 商家任务与素材关联列表
     */
    List<TaskMaterialCorrelationDO> getTaskMaterialCorrelationList(Collection<Long> ids);

    /**
     * 获得商家任务与素材关联分页
     *
     * @param pageReqVO 分页查询
     * @return 商家任务与素材关联分页
     */
    PageResult<TaskMaterialCorrelationDO> getTaskMaterialCorrelationPage(TaskMaterialCorrelationPageReqVO pageReqVO);

    /**
     * 获得商家任务与素材关联列表, 用于 Excel 导出
     *
     * @param exportReqVO 查询条件
     * @return 商家任务与素材关联列表
     */
    List<TaskMaterialCorrelationDO> getTaskMaterialCorrelationList(TaskMaterialCorrelationExportReqVO exportReqVO);

    void insertByTask(List<Long> materialIds,Long taskId,Integer taskType,Integer platformType,Integer taskStatus);

    void updateByTask(List<Long> materialIds,Long taskId,Integer taskType,Integer platformType,Integer taskStatus);

    Boolean existsUsedByTask(Long materialId,Integer platformType);

}
