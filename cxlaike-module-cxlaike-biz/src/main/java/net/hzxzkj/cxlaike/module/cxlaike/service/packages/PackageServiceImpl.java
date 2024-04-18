package net.hzxzkj.cxlaike.module.cxlaike.service.packages;

import net.hzxzkj.cxlaike.framework.mybatis.core.service.BaseCrudServiceImpl;
import net.hzxzkj.cxlaike.module.cxlaike.controller.admin.packages.vo.PackageCreateReqVO;
import net.hzxzkj.cxlaike.module.cxlaike.controller.admin.packages.vo.PackageExportReqVO;
import net.hzxzkj.cxlaike.module.cxlaike.controller.admin.packages.vo.PackagePageReqVO;
import net.hzxzkj.cxlaike.module.cxlaike.controller.admin.packages.vo.PackageUpdateReqVO;
import net.hzxzkj.cxlaike.module.cxlaike.convert.packages.PackageConvert;
import net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.packages.PackageDO;
import net.hzxzkj.cxlaike.module.cxlaike.dal.mysql.packages.PackageMapper;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import java.util.*;
import net.hzxzkj.cxlaike.framework.common.pojo.PageResult;

import static net.hzxzkj.cxlaike.framework.common.exception.util.ServiceExceptionUtil.exception;

/**
 * 商户套餐 Service 实现类
 *
 * @author 宵征源码
 */
@Service
@Validated
public class PackageServiceImpl extends BaseCrudServiceImpl<PackageMapper,PackageDO> implements PackageService {

  @Resource
  private PackageMapper packageMapper;

  @Override
  public Long createPackage(PackageCreateReqVO createReqVO) {
    // 插入
    PackageDO packageDO = PackageConvert.INSTANCE.convert(createReqVO);
    packageMapper.insert(packageDO);
    // 返回
    return packageDO.getId();
  }

  @Override
  public void updatePackage(PackageUpdateReqVO updateReqVO) {
    // 校验存在
    validatePackageExists(updateReqVO.getId());
    // 更新
    PackageDO updateObj = PackageConvert.INSTANCE
        .convert(updateReqVO);
    packageMapper.updateById(updateObj);
  }

  @Override
  public void deletePackage(Long id) {
    // 校验存在
    validatePackageExists(id);
    // 删除
    packageMapper.deleteById(id);
  }

  private void validatePackageExists(Long id) {
    if (packageMapper.selectById(id) == null) {
//      throw exception(PACKAGE_NOT_EXISTS);
    }
  }

  @Override
  public PackageDO getPackage(Long id) {
    return packageMapper.selectById(id);
  }

  @Override
  public List<PackageDO> getPackageList(Collection<Long> ids) {
    return packageMapper.selectBatchIds(ids);
  }

  @Override
  public PageResult<PackageDO> getPackagePage(PackagePageReqVO pageReqVO) {
    return packageMapper.selectPage(pageReqVO);
  }

  @Override
  public List<PackageDO> getPackageList(PackageExportReqVO exportReqVO) {
    return packageMapper.selectList(exportReqVO);
  }

}
