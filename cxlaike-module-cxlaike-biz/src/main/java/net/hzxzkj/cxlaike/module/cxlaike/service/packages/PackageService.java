package net.hzxzkj.cxlaike.module.cxlaike.service.packages;

import java.util.*;
import javax.validation.*;

import com.diboot.core.service.BaseService;
import net.hzxzkj.cxlaike.module.cxlaike.controller.admin.packages.vo.PackageCreateReqVO;
import net.hzxzkj.cxlaike.module.cxlaike.controller.admin.packages.vo.PackageExportReqVO;
import net.hzxzkj.cxlaike.module.cxlaike.controller.admin.packages.vo.PackagePageReqVO;
import net.hzxzkj.cxlaike.module.cxlaike.controller.admin.packages.vo.PackageUpdateReqVO;
import net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.packages.PackageDO;
import net.hzxzkj.cxlaike.framework.common.pojo.PageResult;

/**
 * 商户套餐 Service 接口
 *
 * @author 宵征源码
 */
public interface PackageService extends BaseService<PackageDO> {

    /**
     * 创建商户套餐
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createPackage(@Valid PackageCreateReqVO createReqVO);

    /**
     * 更新商户套餐
     *
     * @param updateReqVO 更新信息
     */
    void updatePackage(@Valid PackageUpdateReqVO updateReqVO);

    /**
     * 删除商户套餐
     *
     * @param id 编号
     */
    void deletePackage(Long id);

    /**
     * 获得商户套餐
     *
     * @param id 编号
     * @return 商户套餐
     */
    PackageDO getPackage(Long id);

    /**
     * 获得商户套餐列表
     *
     * @param ids 编号
     * @return 商户套餐列表
     */
    List<PackageDO> getPackageList(Collection<Long> ids);

    /**
     * 获得商户套餐分页
     *
     * @param pageReqVO 分页查询
     * @return 商户套餐分页
     */
    PageResult<PackageDO> getPackagePage(PackagePageReqVO pageReqVO);

    /**
     * 获得商户套餐列表, 用于 Excel 导出
     *
     * @param exportReqVO 查询条件
     * @return 商户套餐列表
     */
    List<PackageDO> getPackageList(PackageExportReqVO exportReqVO);

}
