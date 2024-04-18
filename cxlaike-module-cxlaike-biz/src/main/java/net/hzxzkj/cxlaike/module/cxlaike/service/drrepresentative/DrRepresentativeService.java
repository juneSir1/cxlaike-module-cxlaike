package net.hzxzkj.cxlaike.module.cxlaike.service.drrepresentative;

import java.util.*;
import javax.validation.*;
import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.drrepresentative.vo.*;
import net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.drrepresentative.DrRepresentativeDO;
import net.hzxzkj.cxlaike.framework.common.pojo.PageResult;

/**
 * 达人探店代 Service 接口
 *
 * @author 宵征源码
 */
public interface DrRepresentativeService {

    /**
     * 创建达人探店代
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createDrRepresentative(@Valid DrRepresentativeCreateReqVO createReqVO);

    /**
     * 更新达人探店代
     *
     * @param updateReqVO 更新信息
     */
    void updateDrRepresentative(@Valid DrRepresentativeUpdateReqVO updateReqVO);

    /**
     * 删除达人探店代
     *
     * @param id 编号
     */
    void deleteDrRepresentative(Long id);

    /**
     * 获得达人探店代
     *
     * @param id 编号
     * @return 达人探店代
     */
    DrRepresentativeDO getDrRepresentative(Long id);

    /**
     * 获得达人探店代列表
     *
     * @param ids 编号
     * @return 达人探店代列表
     */
    List<DrRepresentativeDO> getDrRepresentativeList();

    /**
     * 获得达人探店代分页
     *
     * @param pageReqVO 分页查询
     * @return 达人探店代分页
     */
    PageResult<DrRepresentativeDO> getDrRepresentativePage(DrRepresentativePageReqVO pageReqVO);

    /**
     * 获得达人探店代列表, 用于 Excel 导出
     *
     * @param exportReqVO 查询条件
     * @return 达人探店代列表
     */
    List<DrRepresentativeDO> getDrRepresentativeList(DrRepresentativeExportReqVO exportReqVO);

}
