package net.hzxzkj.cxlaike.module.cxlaike.service.taskmaterialcorrelation;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import net.hzxzkj.cxlaike.module.infra.api.file.FileApi;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.*;
import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.taskmaterialcorrelation.vo.*;
import net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.taskmaterialcorrelation.TaskMaterialCorrelationDO;
import net.hzxzkj.cxlaike.framework.common.pojo.PageResult;

import net.hzxzkj.cxlaike.module.cxlaike.convert.taskmaterialcorrelation.TaskMaterialCorrelationConvert;
import net.hzxzkj.cxlaike.module.cxlaike.dal.mysql.taskmaterialcorrelation.TaskMaterialCorrelationMapper;

import static net.hzxzkj.cxlaike.framework.common.exception.util.ServiceExceptionUtil.exception;
import static net.hzxzkj.cxlaike.module.cxlaike.enums.ErrorCodeConstants.*;

/**
 * 商家任务与素材关联 Service 实现类
 *
 * @author 宵征源码
 */
@Service
@Validated
public class TaskMaterialCorrelationServiceImpl implements TaskMaterialCorrelationService {

    @Resource
    private TaskMaterialCorrelationMapper taskMaterialCorrelationMapper;

    @Resource
    private FileApi fileApi;

    @Override
    public Long createTaskMaterialCorrelation(TaskMaterialCorrelationCreateReqVO createReqVO) {
        // 插入
        TaskMaterialCorrelationDO taskMaterialCorrelation = TaskMaterialCorrelationConvert.INSTANCE.convert(createReqVO);
        taskMaterialCorrelationMapper.insert(taskMaterialCorrelation);
        // 返回
        return taskMaterialCorrelation.getId();
    }

    @Override
    public void updateTaskMaterialCorrelation(TaskMaterialCorrelationUpdateReqVO updateReqVO) {
        // 校验存在
        validateTaskMaterialCorrelationExists(updateReqVO.getId());
        // 更新
        TaskMaterialCorrelationDO updateObj = TaskMaterialCorrelationConvert.INSTANCE.convert(updateReqVO);
        taskMaterialCorrelationMapper.updateById(updateObj);
    }

    @Override
    public void deleteTaskMaterialCorrelation(Long id) {
        // 校验存在
        validateTaskMaterialCorrelationExists(id);
        // 删除
        taskMaterialCorrelationMapper.deleteById(id);
    }

    private void validateTaskMaterialCorrelationExists(Long id) {
        if (taskMaterialCorrelationMapper.selectById(id) == null) {
            throw exception(DATA_ERROR);
        }
    }

    @Override
    public TaskMaterialCorrelationDO getTaskMaterialCorrelation(Long id) {
        return taskMaterialCorrelationMapper.selectById(id);
    }

    @Override
    public List<TaskMaterialCorrelationDO> getTaskMaterialCorrelationList(Collection<Long> ids) {
        return taskMaterialCorrelationMapper.selectBatchIds(ids);
    }

    @Override
    public PageResult<TaskMaterialCorrelationDO> getTaskMaterialCorrelationPage(TaskMaterialCorrelationPageReqVO pageReqVO) {
        return taskMaterialCorrelationMapper.selectPage(pageReqVO);
    }

    @Override
    public List<TaskMaterialCorrelationDO> getTaskMaterialCorrelationList(TaskMaterialCorrelationExportReqVO exportReqVO) {
        return taskMaterialCorrelationMapper.selectList(exportReqVO);
    }

    @Override
    public void insertByTask(List<Long> materialIds, Long taskId, Integer taskType, Integer platformType, Integer taskStatus) {
        List<TaskMaterialCorrelationDO> correlationDOS = new ArrayList<>();
        for(Long materialId:materialIds){
            TaskMaterialCorrelationDO correlationDO = new TaskMaterialCorrelationDO();
            correlationDO.setTaskType(taskType);
            correlationDO.setPlatformType(platformType);
            correlationDO.setTaskId(taskId);
            correlationDO.setMaterialId(materialId);
            correlationDO.setTaskStatus(taskStatus);
            correlationDOS.add(correlationDO);
        }
        taskMaterialCorrelationMapper.insertBatch(correlationDOS);
        //更新素材被抖音使用过
        fileApi.updateFilesIsUsedByDy(materialIds);
    }

    @Override
    @Transactional
    public void updateByTask(List<Long> materialIds, Long taskId, Integer taskType, Integer platformType, Integer taskStatus) {
        taskMaterialCorrelationMapper.delete(new LambdaQueryWrapper<TaskMaterialCorrelationDO>()
                .eq(TaskMaterialCorrelationDO::getTaskId,taskId));
        this.insertByTask(materialIds,taskId,taskType,platformType,taskStatus);
    }

    @Override
    public Boolean existsUsedByTask(Long materialId, Integer platformType) {
        return taskMaterialCorrelationMapper.exists(new LambdaQueryWrapper<TaskMaterialCorrelationDO>()
                    .eq(TaskMaterialCorrelationDO::getPlatformType,platformType)
                    .eq(TaskMaterialCorrelationDO::getId,materialId));
    }

}
