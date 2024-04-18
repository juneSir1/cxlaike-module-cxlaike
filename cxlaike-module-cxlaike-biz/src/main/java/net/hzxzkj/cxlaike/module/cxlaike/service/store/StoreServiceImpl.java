package net.hzxzkj.cxlaike.module.cxlaike.service.store;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import net.hzxzkj.cxlaike.framework.common.enums.CommonStatusEnum;
import net.hzxzkj.cxlaike.framework.common.pojo.PageResult;
import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.store.vo.StoreCreateReqVO;
import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.store.vo.StorePageReqVO;
import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.store.vo.StoreUpdateReqVO;
import net.hzxzkj.cxlaike.module.cxlaike.convert.store.StoreConvert;
import net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.store.StoreDO;
import net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.task.TaskDO;
import net.hzxzkj.cxlaike.module.cxlaike.dal.mysql.store.StoreMapper;
import net.hzxzkj.cxlaike.module.cxlaike.dal.mysql.task.TaskMapper;
import net.hzxzkj.cxlaike.module.cxlaike.enums.TaskStatus;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;

import java.util.List;

import static net.hzxzkj.cxlaike.framework.common.exception.util.ServiceExceptionUtil.exception;
import static net.hzxzkj.cxlaike.module.cxlaike.enums.ErrorCodeConstants.*;

/**
 * 门店管理 Service 实现类
 *
 * @author 宵征源码
 */
@Service
@Validated
public class StoreServiceImpl implements StoreService {

    @Resource
    private StoreMapper storeMapper;

    @Resource
    private TaskMapper taskMapper;

    @Override
    public Long createStore(StoreCreateReqVO createReqVO) {
        // 插入
        StoreDO store = StoreConvert.INSTANCE.convert(createReqVO);
        storeMapper.insert(store);
        // 返回
        return store.getId();
    }

    @Override
    public void updateStore(StoreUpdateReqVO updateReqVO) {
        // 校验存在
        validateStoreExists(updateReqVO.getId());
        //门店存在进行中的任务时不可以编辑
        int count = storeMapper.selectTaskByStoreId("\""+updateReqVO.getId()+"\"");
        if(count > 0) {
            throw exception(STORE_NOT_UPDATE_2);
        }
        // 更新
        StoreDO updateObj = StoreConvert.INSTANCE.convert(updateReqVO);
        storeMapper.updateById(updateObj);
    }

    @Override
    public void deleteStore(Long id) {
        // 校验存在
        validateStoreExists(id);
        //门店存在进行中的任务时不可以删除
        int count = storeMapper.selectTaskByStoreId("\""+id+"\"");
        if(count > 0){
            throw exception(STORE_NOT_DELETE);
        }
        // 删除
        storeMapper.deleteById(id);
    }

    private void validateStoreExists(Long id) {
        if (storeMapper.selectById(id) == null) {
            throw exception(STORE_NOT_EXISTS);
        }
    }

    @Override
    public StoreDO getStore(Long id) {
        return storeMapper.selectById(id);
    }



    @Override
    public PageResult<StoreDO> getStorePage(StorePageReqVO pageReqVO) {
        return storeMapper.selectPage(pageReqVO);
    }

    @Override
    public void updateStoreStatus(Long id , Integer status ) {
        //校验id
        validateStoreExists(id);
        //停用门店的时候,校验门店是否存在未结算任务
        if((CommonStatusEnum.DISABLE.getStatus()).equals(status)){
            boolean exists = taskMapper.exists(new LambdaQueryWrapper<TaskDO>()
                    .eq(TaskDO::getStatus, TaskStatus.NOT_STARTED.getStatus())
                    .or()
                    .eq(TaskDO::getStatus, TaskStatus.IN_PROGRESS.getStatus()));
            if(exists){
                throw exception(STORE_NOT_UPDATE);
            }
        }
        //更新状态
        StoreDO storeDO = new StoreDO().setId(id).setStatus(status);
        storeMapper.updateById(storeDO);
    }


    @Override
    public List<StoreDO> getStoreListByIds(List<Long> ids) {
        return storeMapper.selectList(new LambdaQueryWrapper<StoreDO>().in(StoreDO::getId, ids));
    }
}
