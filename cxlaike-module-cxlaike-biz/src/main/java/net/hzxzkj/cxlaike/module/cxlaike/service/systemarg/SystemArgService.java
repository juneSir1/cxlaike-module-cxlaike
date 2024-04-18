package net.hzxzkj.cxlaike.module.cxlaike.service.systemarg;

import java.util.*;
import javax.validation.*;
import net.hzxzkj.cxlaike.module.cxlaike.controller.admin.systemarg.vo.*;
import net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.systemarg.SystemArgDO;
import net.hzxzkj.cxlaike.framework.common.pojo.PageResult;

/**
 * 来客系统参数 Service 接口
 *
 * @author 宵征源码
 */
public interface SystemArgService {

    /**
     * 创建来客系统参数
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createSystemArg(@Valid SystemArgCreateReqVO createReqVO);

    /**
     * 更新来客系统参数
     *
     * @param updateReqVO 更新信息
     */
    void updateSystemArg(@Valid SystemArgUpdateReqVO updateReqVO);

    /**
     * 删除来客系统参数
     *
     * @param id 编号
     */
    void deleteSystemArg(Long id);

    /**
     * 获得来客系统参数
     *
     * @return 来客系统参数
     */
    SystemArgDO getSystemArg();

    /**
     * 获得来客系统参数列表
     *
     * @param ids 编号
     * @return 来客系统参数列表
     */
    List<SystemArgDO> getSystemArgList(Collection<Long> ids);

    /**
     * 获得来客系统参数分页
     *
     * @param pageReqVO 分页查询
     * @return 来客系统参数分页
     */
    PageResult<SystemArgDO> getSystemArgPage(SystemArgPageReqVO pageReqVO);

    /**
     * 获得来客系统参数列表, 用于 Excel 导出
     *
     * @param exportReqVO 查询条件
     * @return 来客系统参数列表
     */
    List<SystemArgDO> getSystemArgList(SystemArgExportReqVO exportReqVO);

}
