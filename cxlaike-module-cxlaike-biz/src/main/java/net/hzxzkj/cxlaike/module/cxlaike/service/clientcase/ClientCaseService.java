package net.hzxzkj.cxlaike.module.cxlaike.service.clientcase;

import java.util.*;
import javax.validation.*;
import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.clientcase.vo.*;
import net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.clientcase.ClientCaseDO;
import net.hzxzkj.cxlaike.framework.common.pojo.PageResult;

/**
 * 客户案例 Service 接口
 *
 * @author 宵征源码
 */
public interface ClientCaseService {

    /**
     * 创建客户案例
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createClientCase(@Valid ClientCaseCreateReqVO createReqVO);

    /**
     * 更新客户案例
     *
     * @param updateReqVO 更新信息
     */
    void updateClientCase(@Valid ClientCaseUpdateReqVO updateReqVO);

    /**
     * 删除客户案例
     *
     * @param id 编号
     */
    void deleteClientCase(Long id);

    /**
     * 获得客户案例
     *
     * @param id 编号
     * @return 客户案例
     */
    ClientCaseDO getClientCase(Long id);

    /**
     * 获得客户案例列表
     *
     * @param ids 编号
     * @return 客户案例列表
     */
    List<ClientCaseDO> getClientCaseList();

    /**
     * 获得客户案例分页
     *
     * @param pageReqVO 分页查询
     * @return 客户案例分页
     */
    PageResult<ClientCaseDO> getClientCasePage(ClientCasePageReqVO pageReqVO);

    /**
     * 获得客户案例列表, 用于 Excel 导出
     *
     * @param exportReqVO 查询条件
     * @return 客户案例列表
     */
    List<ClientCaseDO> getClientCaseList(ClientCaseExportReqVO exportReqVO);

}
