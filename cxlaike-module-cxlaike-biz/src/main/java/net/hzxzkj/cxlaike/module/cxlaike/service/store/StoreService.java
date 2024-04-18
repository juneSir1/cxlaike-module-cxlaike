package net.hzxzkj.cxlaike.module.cxlaike.service.store;

import net.hzxzkj.cxlaike.framework.common.pojo.PageResult;
import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.store.vo.StoreCreateReqVO;
import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.store.vo.StorePageReqVO;
import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.store.vo.StoreUpdateReqVO;
import net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.store.StoreDO;

import javax.validation.Valid;
import java.util.List;

/**
 * 门店管理 Service 接口
 *
 * @author 宵征源码
 */
public interface StoreService {

    /**
     * 创建门店
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createStore(@Valid StoreCreateReqVO createReqVO);

    /**
     * 更新门店
     *
     * @param updateReqVO 更新信息
     */
    void updateStore(@Valid StoreUpdateReqVO updateReqVO);

    /**
     * 删除门店
     *
     * @param id 编号
     */
    void deleteStore(Long id);

    /**
     * 获得门店
     *
     * @param id 编号
     * @return 门店管理
     */
    StoreDO getStore(Long id);


    /**
     * 获得门店分页
     *
     * @param pageReqVO 分页查询
     * @return 门店管理分页
     */
    PageResult<StoreDO> getStorePage(StorePageReqVO pageReqVO);

    /**
     * 更新门店状态
     * @param id
     * @param status
     */
    void updateStoreStatus(Long id,Integer status);

    /**
     * 获得门店
     *
     * @param ids 编号
     * @return 门店管理
     */
    List<StoreDO> getStoreListByIds(List<Long> ids);
}