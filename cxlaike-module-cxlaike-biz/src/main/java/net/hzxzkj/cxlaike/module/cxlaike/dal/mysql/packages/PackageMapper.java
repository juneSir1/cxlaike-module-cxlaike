package net.hzxzkj.cxlaike.module.cxlaike.dal.mysql.packages;

import java.util.*;

import net.hzxzkj.cxlaike.framework.common.pojo.PageResult;
import net.hzxzkj.cxlaike.framework.mybatis.core.query.LambdaQueryWrapperX;
import net.hzxzkj.cxlaike.framework.mybatis.core.mapper.BaseMapperX;
import net.hzxzkj.cxlaike.module.cxlaike.controller.admin.packages.vo.PackageExportReqVO;
import net.hzxzkj.cxlaike.module.cxlaike.controller.admin.packages.vo.PackagePageReqVO;
import net.hzxzkj.cxlaike.module.cxlaike.dal.dataobject.packages.PackageDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 商户套餐 Mapper
 *
 * @author 宵征源码
 */
@Mapper
public interface PackageMapper extends BaseMapperX<PackageDO> {

  default PageResult<PackageDO> selectPage(PackagePageReqVO reqVO) {
    return selectPage(reqVO, new LambdaQueryWrapperX<PackageDO>()
        .likeIfPresent(PackageDO::getName, reqVO.getName())
        .eqIfPresent(PackageDO::getPrice, reqVO.getPrice())
        .eqIfPresent(PackageDO::getDays, reqVO.getDays())
        .eqIfPresent(PackageDO::getSort, reqVO.getSort())
        .eqIfPresent(PackageDO::getStatus, reqVO.getStatus())
        .betweenIfPresent(PackageDO::getCreateTime, reqVO.getCreateTime())
        .orderByDesc(PackageDO::getId));
  }

  default List<PackageDO> selectList(PackageExportReqVO reqVO) {
    return selectList(new LambdaQueryWrapperX<PackageDO>()
        .likeIfPresent(PackageDO::getName, reqVO.getName())
        .eqIfPresent(PackageDO::getPrice, reqVO.getPrice())
        .eqIfPresent(PackageDO::getDays, reqVO.getDays())
        .eqIfPresent(PackageDO::getSort, reqVO.getSort())
        .eqIfPresent(PackageDO::getStatus, reqVO.getStatus())
        .betweenIfPresent(PackageDO::getCreateTime, reqVO.getCreateTime())
        .orderByDesc(PackageDO::getId));
  }

}
